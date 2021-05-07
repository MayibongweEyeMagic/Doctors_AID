package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PatientDetail extends AppCompatActivity {

    TextView Patientname,name,surname,textnumber,location,pvd,rfpv;
    int cellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        Patientname= (TextView)findViewById(R.id.name);
        name=(TextView)findViewById(R.id.name);
        surname=(TextView)findViewById(R.id.surname);
        textnumber=(TextView)findViewById(R.id.cellphone);
        location=(TextView)findViewById(R.id.location);
        pvd=(TextView)findViewById(R.id.previousvisitdate);
        rfpv=(TextView)findViewById(R.id.reason);

        Intent intent = getIntent();
        Patientname.setText(intent.getStringExtra("select"));
        String person= Patientname.getText().toString();


    }
}