package com.example.user.complaintapp;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import  java.util.StringTokenizer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class setpoll extends Fragment {
  EditText ed1,ed2;
    Button b;
  String o1,o2,JSON_URL,id;
    public setpoll() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_setpoll, container, false);
        b=(Button)v.findViewById(R.id.b);
        ed1 = (EditText)v.findViewById(R.id.ed1);
        ed2 = (EditText)v.findViewById(R.id.ed2);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
        }
        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //to profile activity
                o1 = ed1.getText().toString();
                o2 = ed2.getText().toString();
                // JSON_URL = ip + "default/login.json?userid=" + usernamestr + "&password=" + passwordstr;
                JSON_URL = Login.ip + "default/pollset.json/" + id +"/"+o1+"?" ;
                //JSON_URL = Login.ip + "default/pollset.json/" + id + "/2?o1="+o1+"&o2="+o2 ;
                String[] a = o2.split(",");
                for (int i=0;i<a.length;i++)
                {
                    JSON_URL=JSON_URL+"o"+Integer.toString(i+1) +"="+a[i]+"&";
                }
                JSON_URL=JSON_URL.substring(0,JSON_URL.length()-1);

                registerUser();//method handling the request sending part


            }
        });
    }

    private void registerUser() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "New Poll Created",
                                Toast.LENGTH_SHORT).show();
                    }}
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Not able to create a poll" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }}}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }});
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);


    }
}
