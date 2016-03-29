package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class setpoll extends Fragment {
  EditText ed1,ed2;
    ImageButton b;
  String o1,o2,JSON_URL,id;
    public setpoll() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_setpoll, container, false);
        b=(ImageButton)v.findViewById(R.id.button);
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
                JSON_URL = Login.ip + "default/pollset.json/" + id + "/2?o1="+o1+"&o2="+o2 ;

                registerUser();//method handling the request sending part


            }
        });
    }

    private void registerUser() {
    }
}
