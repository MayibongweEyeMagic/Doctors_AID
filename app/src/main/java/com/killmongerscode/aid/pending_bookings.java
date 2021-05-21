package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class pending_bookings extends AppCompatActivity {
    private ArrayList<Patient> patientList;
    private RecyclerView recyclerView;
    private list_adapter.RecyclerViewClickListner Listener;

    private Button accept;
    private Button decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_bookings);
        recyclerView =findViewById(R.id.pending_bookings);
        patientList = new ArrayList<>();

        setPatientInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListner();
        list_adapter adapter = new list_adapter(patientList, Listener);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListner() {
        Listener = new list_adapter.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getApplicationContext(), Doctor_appointments.class);
                intent.putExtra("Patient Name",patientList.get(position).getPatient_name());
                startActivity(intent);
            }

        };
    }

    private void setPatientInfo() {
       /* patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));
        patientList.add(new Patient("Phindulo Makhado"));*/
    }

}