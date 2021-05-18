package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Patient_appointments extends AppCompatActivity {
    private ArrayList<DoctorName> doctorList;
    private RecyclerView recyclerView;
    private patient_appointments_adapter.RecyclerViewClickListner Lister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);
        recyclerView =findViewById(R.id.patient_appointments);
        doctorList = new ArrayList<>();

        setPatientInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListr();
        patient_appointments_adapter adapter = new patient_appointments_adapter(doctorList, Lister);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListr() {
        Lister = new patient_appointments_adapter.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getApplicationContext(), Appointment_detail.class);
                intent.putExtra("Patient Name",doctorList.get(position).getDoctorName());
                startActivity(intent);
            }

        };
    }

    private void setPatientInfo() {
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
        doctorList.add(new DoctorName("Phindulo"));
    }
}