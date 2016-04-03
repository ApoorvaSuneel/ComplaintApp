package com.example.user.complaintapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreadView extends ListFragment {
String m,JSON;
    ArrayAdapter<String> t;
    private static ArrayList<String> thread_user,thread_comment;
    String[] array1;
    ImageButton b1;
    int tid,counts;

    public ThreadView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_thread_view, container, false);
        Bundle b=this.getArguments();
        ImageButton b1=(ImageButton)v.findViewById(R.id.imageButton2);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                clicked();
                }


        });
        if (b!=null)
        {
            m=b.getString("id");
            JSON=b.getString("ip");
        }
        viewT();
        return v;
    }

    private void clicked() {
        AddComment myDetailFragment = new AddComment();
        Bundle bundle =new Bundle();
        bundle.putString("id",m);
        myDetailFragment.setArguments(bundle);
        FragmentManager fm =getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            // FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frag, myDetailFragment);
            ft.commit();
        }
    }

    private void viewT() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    JSONObject complist = response.getJSONObject("threads");
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {
                        tid=complist.getInt("id");

                        counts=complist.getInt("ccounts");
                        JSONArray j1= complist.getJSONArray("comusers");
                        JSONArray j2=complist.getJSONArray("comments");
                        for (int i = 0; i < counts; i++) {

                            thread_comment.add(j2.getString(i) + " by user of id : " + j1.getString(i) );

                        }
                        array1 = thread_comment.toArray(new String[thread_comment.size()]);
                        t = new ArrayAdapter<String>(getActivity(), R.layout.subject,R.id.sub,array1);
                        setListAdapter(t);

                    }

                    //udone set to 1 for non repetative json results
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: NOT WORKING " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }}}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }});
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);
    }



}
