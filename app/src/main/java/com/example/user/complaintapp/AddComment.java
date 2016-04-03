package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.LastOwnerException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddComment extends Fragment {

    ImageButton b1;
    EditText t1;
    String m;
    String JSON;
    public AddComment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_comment, container, false);
        Bundle b=this.getArguments();
        b1=(ImageButton)v.findViewById(R.id.imageButton4);
        t1=(EditText)v.findViewById(R.id.editText10);
        String s=t1.getText().toString();
        if (b!=null)
        {
            m=b.getString("id");

        }
        JSON= Login.ip+"/default/addcomment.json/"+m+"?desc="+s;
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clicked();
            }


        });
        return v;

    }

    private void clicked() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    Boolean b=response.getBoolean("success");
                    if(b)
                    {

                        Toast.makeText(getActivity(),
                                "Comment Added successfully",
                                Toast.LENGTH_SHORT).show();
                    }}
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: NOT WORKING " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }}}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }});
        RequestQueue RequestP = Volley.newRequestQueue(getActivity());
        RequestP.add(jreq);
    }

}
