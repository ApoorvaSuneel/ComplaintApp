package com.example.user.complaintapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by USER on 29-03-2016.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {
    Button dismiss;

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);
        getDialog().setTitle("Search");

        dismiss = (Button)rootView.findViewById(R.id.b);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Search");
        builder.setMessage("If search by priority then type 0 else type 1 followed by specification");
        EditText ed=new EditText(getActivity());
        Context context = getActivity();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("type 0 if Priority/1 if Tag");
        layout.addView(titleBox);

        final EditText descriptionBox = new EditText(context);
        descriptionBox.setHint("type the specification");
        layout.addView(descriptionBox);

        builder.setView(layout);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Profile.str = titleBox.getText().toString() + descriptionBox.getText().toString();
                Profile.r=1;
                MyComplaintsf.hey();
                dismiss();
                //JSON_URL1 = Login.ip + "/filter.json?opt=" + Character.toString(str.charAt(0)) + "&para=" + str.substring(1);


            }
        });




        return builder.create();
    }



}
