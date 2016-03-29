package com.example.user.complaintapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddComplaint extends Fragment {
    EditText fn,ln,desc;
    Spinner team,priority,ispersonal,isanon;
    Button b;
    String json;

    public AddComplaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_complaint, container, false);
        /*fn=(EditText)v.findViewById(R.id.fn);

         ln=(EditText)v.findViewById(R.id.ln);
         desc=(EditText)v.findViewById(R.id.desc);
         team=(Spinner)v.findViewById(R.id.team);
         priority=(Spinner)v.findViewById(R.id.priority);
         ispersonal=(Spinner)v.findViewById(R.id.ispersonal);
         isanon=(Spinner)v.findViewById(R.id.isanon);*/
        b=(Button)v.findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addcomplaint();

            }
        });

        return v;
    }

    private void addcomplaint() {


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
