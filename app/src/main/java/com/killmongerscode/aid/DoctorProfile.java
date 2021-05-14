package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DoctorProfile extends AppCompatActivity {

    Verification verification = new Verification();
    TextView name, surname , cellphone , email, specialization, qualification,title;

    String message, get_name, get_surname,get_cellphone, get_specialization, get_qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);


        name = findViewById(R.id.doctor_name);
        surname = findViewById(R.id.doctor_surname);
        cellphone = findViewById(R.id.doctor_cell);
        email = findViewById(R.id.doctor_email_address);
        specialization = findViewById(R.id.doctor_spectialization);
        qualification = findViewById(R.id.doctor_qualification);
        //title = findViewById(R.id.toolbar_title);



        Bundle bundle = getIntent().getExtras();
        setFields(bundle);


    }


    public void setFields(Bundle bundle){


        message = bundle.getString("email");
        get_name = bundle.getString("name");
        get_surname = bundle.getString("surname");
        get_cellphone = bundle.getString("cellNo");
        get_specialization = bundle.getString("specialization");
        get_qualification = bundle.getString("qualification");

        name.setText("Name: "+get_name);
        surname.setText("Surname: " + get_surname);
        cellphone.setText("Cell phone No: " + get_cellphone);
        specialization.setText("Specialization: "+get_specialization);
        email.setText("Email Address: " + message);
        qualification.setText("Qualification: " + get_qualification);
      //  title.setText(name + " " + surname);
    }

}