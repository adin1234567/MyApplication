package com.example.v5636.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InstantActivity extends AppCompatActivity {

    int devideOfTime = 5;
    final String TAG = "InstantActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private String voltage, ampere, power, back_temp;
    TextView txtVoltage, txtTime, txtPower, txtAmpere, txtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String nowFilename = new SimpleDateFormat("MMdd_HH").format(new Date());
        final int nowTimeDivide = (Integer.parseInt(new SimpleDateFormat("mm").format(new Date()))) / devideOfTime;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant);
        Toast.makeText(getApplicationContext(), "即時數據", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Filename=" + nowFilename + "\nTimedivide:" + nowTimeDivide);


        txtVoltage=findViewById(R.id.insVoltage);
        txtAmpere=findViewById(R.id.insAmpere);
        txtPower=findViewById(R.id.insPower);
        txtTime=findViewById(R.id.insTime);
        txtTemp=findViewById(R.id.insTemp);



        //[Firebase]
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        DatabaseReference reference = myRef.child(nowFilename).child("" + nowTimeDivide);


        reference.child("Ampere").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.d(TAG, dataSnapshot.getValue().toString());
                    txtAmpere.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    Log.d(TAG, "null");
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("Voltage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.d(TAG, dataSnapshot.getValue().toString());
                    txtVoltage.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    Log.d(TAG, "null");
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("Power").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.d(TAG, dataSnapshot.getValue().toString());
                    txtPower.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    Log.d(TAG, "null");
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("Time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.d(TAG, dataSnapshot.getValue().toString());
                    txtTime.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    Log.d(TAG, "null");
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("Temp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.d(TAG, dataSnapshot.getValue().toString());
                    txtTemp.setText(dataSnapshot.getValue().toString());
                } catch (Exception ex) {
                    Log.d(TAG, "null");
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

