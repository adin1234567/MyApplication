package com.example.v5636.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftpconnect Iotftp=new ftpconnect();


        boolean result= true;
        result= Iotftp.ftpConnect("192.72.189.223","admin","Admin",21);
        Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_LONG).show();

    }





    }

