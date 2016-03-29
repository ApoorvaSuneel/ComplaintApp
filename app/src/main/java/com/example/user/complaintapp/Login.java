package com.example.user.complaintapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class Login extends AppCompatActivity {
     Button b;
    public static String ip="http://10.237.23.150:8000/";     //public static ip for all classes
    private static String JSON_URL;
    public static ArrayList<String> logchoice=new ArrayList<String>();
    public static String[] res = new String[5]  ;
    private EditText username, password;
    public static String usernamestr, passwordstr,jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b=(Button)findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
             //to profile activity
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
               // JSON_URL = ip + "default/login.json?userid=" + usernamestr + "&password=" + passwordstr;
                JSON_URL = ip + "default/login.json?userid=" + "cs5110000" + "&password=" + "jim";

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
                    Boolean s=response.getBoolean("success");
                    Toast.makeText(getApplicationContext(),
                            s.toString(),
                            Toast.LENGTH_LONG).show();
                    JSONObject u=response.getJSONObject("user");
                    //Boolean s=response.getBoolean("success");
                    String name = "";
                    name = u.getString("first_name") + "  " + u.getString("last_name");
                    jsonResponse = "Hello "+ name ;
                    //arraylist to get the data from one activity to another and also to set the adapter class
                    logchoice.add(u.getString("first_name"));
                    logchoice.add(u.getString("last_name"));
                    logchoice.add(u.getString("email"));
                    logchoice.add(u.getString("entry_no"));
                    logchoice.add(u.getString("id"));
                    res = logchoice.toArray(new String[logchoice.size()]);
                    if (s)
                    {
                        //define intent
                        Toast.makeText(Login.this,
                                jsonResponse,
                                Toast.LENGTH_LONG).show();
                        //intent for another class
                        Intent myIntent = new Intent(
                                Login.this, Profile.class);
                        myIntent.putExtra("data",res);
                        startActivity(myIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "INVALID LOGIN"+e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //request queue
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(jreq);



    }
}
