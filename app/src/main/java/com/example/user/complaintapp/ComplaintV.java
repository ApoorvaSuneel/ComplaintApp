package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintV extends Fragment {

    ImageButton b2,b3,b4,b5;
    private TextView t1,t2,t3,t4,t5,t6,t9,t10,t11,t12,t13;
    private String i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
    private String JSON1,JSON2,JSON3,JSON4,JSON5,JSON6 ;

    public ComplaintV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_complaint_v, container, false);
        t1=(TextView)v.findViewById(R.id.cv1);

        t3=(TextView)v.findViewById(R.id.cv3);
        t4=(TextView)v.findViewById(R.id.cv4);
       // t5=(TextView)v.findViewById(R.id.cv5);
        t6=(TextView)v.findViewById(R.id.cv6);
        t9=(TextView)v.findViewById(R.id.cv9);
       // t10=(TextView)v.findViewById(R.id.cv10);
        t11=(TextView)v.findViewById(R.id.cv11);
        t12=(TextView)v.findViewById(R.id.cv12);
        t13=(TextView)v.findViewById(R.id.cv13);
        b2=(ImageButton)v.findViewById(R.id.b2);
        b3=(ImageButton)v.findViewById(R.id.b3);
        b4=(ImageButton)v.findViewById(R.id.b4);
        b5=(ImageButton)v.findViewById(R.id.b5);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i1 = bundle.getString("txt");
            i2=bundle.getString("id");
            i3 = bundle.getString("prio");
            i4 = bundle.getString("comp");
            i6 = bundle.getString("res");
            i9= bundle.getString("teamid");
            i11= Boolean.toString(bundle.getBoolean("red"));
            i12= Boolean.toString(bundle.getBoolean("pers"));
            i13=bundle.getString("status");

            t1.setText(i1);
            t3.setText(i3);
            t4.setText(i4);
            t6.setText(i6);
            t9.setText(i9);
            t11.setText(i11);
            t12.setText(i12);
            t13.setText(i13);

        }

        JSON1=Login.ip+"default/upvotec.json/"+i2+"?up=1";//upvote
        JSON2=Login.ip+"default/upvotec.json/"+i2+"?up=0";//downvote
        JSON3=Login.ip+"viewpoll.json/"+i2;//poll
        JSON4=Login.ip+"viewthread.json/"+i2;//thread

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                viewpolls();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                viewthreads();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                upvote();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                downvote();

            }
        });


        return v;
    }

    private void viewthreads() {
//request to createpolls
        ThreadView myDetailFragment = new ThreadView();
        Bundle bundle = new Bundle();
        bundle.putString("id", i2);
        bundle.putString("ip",JSON4);
        myDetailFragment.setArguments(bundle);
        FragmentManager fm =getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            // FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frag, myDetailFragment);
            ft.commit();
        }}




    private void viewpolls() {
        //request to createpolls
        //go to the next fragment and send json request for further process

        PollView myDetailFragment = new PollView();
        Bundle bundle = new Bundle();
        bundle.putString("id", i2);
        bundle.putString("pollid",i5);
        myDetailFragment.setArguments(bundle);
        FragmentManager fm =getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            // FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frag, myDetailFragment);
            ft.commit();
        }}




    private void upvote() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON1, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Upvoted",
                                Toast.LENGTH_SHORT).show();
                    }}
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
    private void downvote() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON2, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "DownVoted",
                                Toast.LENGTH_SHORT).show();
                    }}
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
    private void markred() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON3, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Marked Redundant",
                                Toast.LENGTH_SHORT).show();
                    }}
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
