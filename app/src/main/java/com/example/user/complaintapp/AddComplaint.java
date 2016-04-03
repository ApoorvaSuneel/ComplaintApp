package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
public class AddComplaint extends Fragment {
    EditText details,anon,prio,addto,image,tags,team,ispers;
    ImageButton b;
    String JSON1;
    String fn,ln,tm,des,n,pri,isp,isa;

    public AddComplaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_complaint, container, false);
        details=(EditText)v.findViewById(R.id.editText2);
        prio=(EditText)v.findViewById(R.id.editText4);
         anon=(EditText)v.findViewById(R.id.editText3);
         team=(EditText)v.findViewById(R.id.editText9);
        image=(EditText)v.findViewById(R.id.editText);
        tags=(EditText)v.findViewById(R.id.editText7);
        ispers=(EditText)v.findViewById(R.id.editText8);
        addto=(EditText)v.findViewById(R.id.editText5);
        b=(ImageButton)v.findViewById(R.id.imageButton3);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addcomplaint();

            }
        });
        JSON1=Login.ip+"/default/addcomplaint.json?fn="+fn+"&ln="+ln+"&team="+tm+"&desc="+des+"&numtag="+n;
        String[] a = tags.getText().toString().split(",");
        for (int i=0;i<a.length;i++)
        {
            JSON1=JSON1+"&tag"+Integer.toString(i+1) +"="+a[i];
        }
        JSON1=JSON1+"&priority="+prio.getText().toString()+"&personal"+ispers.getText().toString()+"anony="+anon.getText().toString();
         addcomplaint();
        //JSON1=Login.ip+"/default/addcomplaint.json?fn=bob&ln=martin&team=cs&desc=no%20marker%20in%20room&numtag=1&tag1=internet&priority=0&personal=0&anony=0";
        return v;
    }

    private void addcomplaint() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.POST,
                JSON1, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Complaint added successfully",
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
