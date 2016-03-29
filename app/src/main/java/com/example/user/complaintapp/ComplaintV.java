package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ComplaintV extends Fragment {

    Button b2,b3,b4,b5,b6;
    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
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
        t2=(TextView)v.findViewById(R.id.cv2);
        t3=(TextView)v.findViewById(R.id.cv3);
        t4=(TextView)v.findViewById(R.id.cv4);
        t5=(TextView)v.findViewById(R.id.cv5);
        t6=(TextView)v.findViewById(R.id.cv6);
        t7=(TextView)v.findViewById(R.id.cv7);
        t8=(TextView)v.findViewById(R.id.cv8);
        t9=(TextView)v.findViewById(R.id.cv9);
        t10=(TextView)v.findViewById(R.id.cv10);
        t11=(TextView)v.findViewById(R.id.cv11);
        t12=(TextView)v.findViewById(R.id.cv12);
        t13=(TextView)v.findViewById(R.id.cv13);
        b2=(Button)v.findViewById(R.id.b2);
        b3=(Button)v.findViewById(R.id.b3);
        b4=(Button)v.findViewById(R.id.b4);
        b5=(Button)v.findViewById(R.id.b5);
       // b6=(Button)v.findViewById(R.id.b6);
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
            i10= bundle.getString("teamid");
            i11= bundle.getString("imageid");
            i12= bundle.getString("red");
            i13= bundle.getString("pers");

            t1.setText(i1);
            t2.setText(i2);
            t3.setText(i3);
            t4.setText(i4);
            t5.setText(i5);
            t6.setText(i6);
            t7.setText(i7);
            t8.setText(i8);
            t9.setText(i9);
            t10.setText(i10);
            t11.setText(i11);
            t12.setText(i12);
            t13.setText(i13);
        }

        JSON1=Login.ip+"default/upvotec.json/"+i2+"?up=1";//upvote
        JSON2=Login.ip+"default/upvotec.json/"+i2+"?up=0";//downvote
        JSON3=Login.ip+"viewpoll.json/"+i2;//poll
        JSON4=Login.ip+"viewthread.json/"+i2;//thread
        //JSON5=Login.ip+"markred.json/"+i2;//search
       /*b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                upvote();

            }
        });*/
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
        /*b6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                markred();
            }
        });
        */

        return v;
    }

    private void viewthreads() {
//request to createpolls
        ThreadView myDetailFragment = new ThreadView();
        Bundle bundle = new Bundle();
        bundle.putString("id", i2);
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
        myDetailFragment.setArguments(bundle);
        FragmentManager fm =getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            // FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frag, myDetailFragment);
            ft.commit();
        }}


    private void search() {
    }

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
