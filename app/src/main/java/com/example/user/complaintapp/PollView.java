package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 * A simple {@link Fragment} subclass.
 */
public class PollView extends Fragment {

     String Json,i2,m;
    RadioButton r;
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
        l=(ListView)v.findViewById(R.id.poll);
        if (bundle != null) {
           //id of the complaint for the view to show
            i2 = bundle.getString("id");
            Json= Login.ip + "viewpoll.json/"+i2;
                      li=new ArrayList<String>();//view poll
            request();
        }
        return v;
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
                    for (int i = 0; i < glist.length(); i++) {

                        int note =glist.getInt(i);
                        String  note2 =g1list.getString(i);
                        m=note2+"    "+note;
                        li.add(m);

                    }
                    String[] array1 = li.toArray(new String[li.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(getActivity(),R.layout.pv,R.id.radioButton,array1);
                    l.setAdapter(t);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            " not ableto display the polls" + e.getMessage(),
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


}
