package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
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
 * A fragment class which views the poll data related to the particular complaint fethced using its complaint id using bundle
 */
public class PollView extends ListFragment {

     String Json,JSOn,i2,i5,m;
    RadioButton r;
    Button b;
    ListView l;
    ArrayList<String> li;
    public PollView() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_poll_view, container, false);
        Bundle bundle = this.getArguments();
        //l=(ListView)v.findViewById(R.id.poll);

        if (bundle != null) {
           //id of the complaint for the view to show
            i2 = bundle.getString("id");
            i5=bundle.getString("pollid");
            Json= Login.ip + "viewpoll.json/"+i2;
                      li=new ArrayList<String>();//view poll
           request();
        }
        return v;
    }


    private void Vote() {

    }

    private void request() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                Json, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Parsing json object response
                    JSONObject clist = response.getJSONObject("poll");
                    JSONArray glist =clist.getJSONArray("ocounts");
                    JSONArray g1list = clist.getJSONArray("optionlist");
                    String id = clist.getString("id");
                    for (int i = 0; i < g1list.length(); i++) {

                        int note =glist.getInt(i);
                        String  note2 =g1list.getString(i);
                        m= "     "+note2+ "     ->     "+Integer.toString(note);
                        li.add(m);

                    }
                    String[] array1 = li.toArray(new String[li.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(getActivity(),R.layout.pv,R.id.radioButton,array1);
                    setListAdapter(t);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            " not ableto display the polls\n"+" ERROR : " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);


    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        JSOn =Login.ip+"/pollvote.json/"+i2+"?opt="+position;
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSOn, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Voted successfully",
                                Toast.LENGTH_SHORT).show();
                    }}
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Could not vote" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }}}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }});
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);




    }



}
