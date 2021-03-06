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

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResolveD extends Fragment {

    Button b1,b3,b6;
    ImageButton b7,b5,b2,b4;
    private TextView t1,t2,t3,t4,t9,t10,t11,t12,t13;
    private String i1,i2,i3,i4,i8,i9,i10,i11,i12,i13;
    private String JSON1,JSON2,JSON3,JSON4,JSON5,JSON6 ;
    public ResolveD() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resolve_d, container, false);
        t1 = (TextView) v.findViewById(R.id.cv1);
        t3 = (TextView) v.findViewById(R.id.cv3);
        t4 = (TextView) v.findViewById(R.id.cv4);
        //t5 = (TextView) v.findViewById(R.id.cv5);
        //t6 = (TextView) v.findViewById(R.id.cv6);
        //t7 = (TextView) v.findViewById(R.id.cv7);
        //t8 = (TextView) v.findViewById(R.id.cv8);
        t9 = (TextView) v.findViewById(R.id.cv9);
       // t10 = (TextView) v.findViewById(R.id.cv10);
        t11 = (TextView) v.findViewById(R.id.cv11);
        t13 = (TextView) v.findViewById(R.id.cv13);
        b1 = (Button) v.findViewById(R.id.b1);
        b2 = (ImageButton) v.findViewById(R.id.b2);
        b3 = (Button) v.findViewById(R.id.b3);
        b4 = (ImageButton) v.findViewById(R.id.b4);
        b5 = (ImageButton) v.findViewById(R.id.b5);
        b6 = (Button) v.findViewById(R.id.b6);
        b7 = (ImageButton) v.findViewById(R.id.b7);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i1 = bundle.getString("txt");
            i2 = bundle.getString("id");
            i3 = bundle.getString("prio");
            i4 = bundle.getString("comp");
            //i5 = bundle.getString("poll");
            //i6 = bundle.getString("res");
           // i7 = bundle.getString("thread");
            //i8 = bundle.getString("anon");
            i9 = bundle.getString("teamid");
            i11 = bundle.getString("red");
            i13 = bundle.getString("status");

            t1.setText(i1);
            t3.setText(i3);
            t4.setText(i4);
            t9.setText(i9);
            t11.setText(i11);
            t13.setText(i13);
        }

        JSON1 = Login.ip + "default/upvotec.json/" + i2 + "?up=1";//upvote
        JSON2 = Login.ip + "default/upvotec.json/" + i2 + "?up=0";//downvote
        JSON3 = Login.ip + "markred.json/" + i2;//mark red
        JSON4=Login.ip+"default/resolve.json/"+i2;
       // JSON5= Login.ip + "viewpoll.json/"+i2;            //view poll
        //set the complaint as resolved
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                resolve();

            }
        });
        //create polls
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                createpolls();

            }
        });
        //view threads
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                viewThreads();

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
        b6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                markred();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                viewPolls();

            }
        });


        return v;
    }

    private void viewThreads() {

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
    private void createpolls() {
        //request to createpolls
        //go to the next fragment and send json request for further process
        setpoll myDetailFragment = new setpoll();
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


    private void viewPolls() {
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



    private void resolve() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON4, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Complaint resolved",
                                Toast.LENGTH_SHORT).show();
                    }}
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Not able to change the resolve status" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }}}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }});
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);

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
                            "Not able to upvote the complaint" + e.getMessage(),
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
                            "Not able to downvote the complaint" + e.getMessage(),
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
