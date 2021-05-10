package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class PatientProfile extends AppCompatActivity {

    private EditText Name, Surname, Cellphone, Email, Location, Lastvisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        Name = findViewById(R.id.name);
        Surname = findViewById(R.id.surname);
        Cellphone = findViewById(R.id.cellphone);
        Email = findViewById(R.id.email);
        Location = findViewById(R.id.location);
        Lastvisit = findViewById(R.id.lastvisit);
    }
}