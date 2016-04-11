package com.example.user.complaintapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
public class NotifyV extends Fragment {
    private Button b1,b2;
    private TextView t1,t3,t4,t5,t6,t8,t9;
    private String i1,i2,i3,i4,i5,i6,i7,i8,i9;
    private String JSON1;
    private String JSON2;
    public NotifyV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_notify_v, container, false);
        t1=(TextView)v.findViewById(R.id.cv1);
        //t2=(TextView)v.findViewById(R.id.cv2);
        t3=(TextView)v.findViewById(R.id.cv3);
        t4=(TextView)v.findViewById(R.id.cv4);
       // t5=(TextView)v.findViewById(R.id.cv5);
        t6=(TextView)v.findViewById(R.id.cv6);
        //t7=(TextView)v.findViewById(R.id.cv7);
        t9=(TextView)v.findViewById(R.id.cv9);
        b1=(Button)v.findViewById(R.id.b1);
        b2=(Button)v.findViewById(R.id.b2);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i1 = bundle.getString("txt");
             i2 = bundle.getString("id");
             i3 = bundle.getString("prio");
             i4 = bundle.getString("comp");
             i5 = bundle.getString("poll");
             i6 = bundle.getString("res");
             i7 = bundle.getString("thread");
             i8 = bundle.getString("anon");
             i9= bundle.getString("status");

            t1.setText(i1);
          //  t2.setText(i2);
            t3.setText(i3);
            t4.setText(i4);
            t5.setText(i5);
            t6.setText(i6);
          //  t7.setText(i7);


        }
        JSON1=Login.ip+"default/follow.json/"+i2+"?follow=1";
        JSON2=Login.ip+"default/follow.json/"+i2+"?follow=0";

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                   follow();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                unfollow();

            }
        });


        return v;
    }

    private void follow() {
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
                                "Followed",
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
    private void unfollow() {
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
                                "UnFollowed",
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Inflate the layout for this fragment

    }
    }

