package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PatientProfile extends AppCompatActivity {

    String name, surname, DateOfbirth, phoneNumber, emailAddress, location;
    private TextView fname, lname, DoB,cellNumber, emailsAddress,homeAddress, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        fname =findViewById(R.id.name);
        lname =findViewById(R.id.surname);
        DoB = findViewById(R.id.date_birth);
        cellNumber =findViewById(R.id.phone_number);
        emailsAddress =findViewById(R.id.patient_email_address);
        homeAddress =findViewById(R.id.Location);
        title =findViewById(R.id.toolbar_title);

        Bundle bundle =getIntent().getExtras();
        setFields(bundle);

    }

    public void setFields(Bundle bundle){
        name = bundle.getString("name");
        surname =bundle.getString("surname");
        DateOfbirth =bundle.getString("dateOfbirth");
        phoneNumber =bundle.getString("cellNo");
        emailAddress =bundle.getString("emailAdd");
        location =bundle.getString("homeAddress");

        fname.setText("Name: "+name);
        lname.setText("Surname: " + surname);
        DoB.setText("Date of Birth: " + DateOfbirth);
        cellNumber.setText("Phone: "+phoneNumber);
        emailsAddress.setText("Email Address: " + emailAddress);
        homeAddress.setText("Location: " + location);
        title.setText(name + " " + surname);
    }
}