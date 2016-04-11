package com.example.user.complaintapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Detailsf extends Fragment {
String[] s1;
    public static String fn,ln;
TextView t1,t3,t4,t5;
    public Detailsf() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_detailsf, container, false);
        t1=(TextView)v.findViewById(R.id.t1);
        t3=(TextView)v.findViewById(R.id.t3);
        t4=(TextView)v.findViewById(R.id.t4);
        t5=(TextView)v.findViewById(R.id.t5);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
           s1=bundle.getStringArray("data");
        }
        fn=s1[0];
        ln=s1[1];
        t1.setText(s1[0].toUpperCase()+ "  "+s1[1].toUpperCase());
        t3.setText(s1[2]);
        t4.setText(s1[3]);
        t5.setText(s1[4]);
        return v;

    }


}
