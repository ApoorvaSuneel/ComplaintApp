package com.example.user.complaintapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
* This is the main splash page for the andorid application
*
*
* */

public class MainActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        //for splash page
        Thread timer = new Thread() {
            @Override
            //method to display the splash page for 800 ns.
            public void run() {
                try
                {
                    sleep(700);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent myIntent1 = new Intent(
                            MainActivity0.this,LogRegActivity.class);
                    startActivity(myIntent1);
                }
            }
        };
        timer.start();
    }
}
