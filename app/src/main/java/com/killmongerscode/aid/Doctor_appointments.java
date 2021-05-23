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

public class Doctor_appointments extends AppCompatActivity {
    private ArrayList<Patient> confirmedList;
    private RecyclerView recyclerView;
    private doctor_appointments_adapter.RecyclerViewClickListner Listr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);
        recyclerView =findViewById(R.id.list_appointments);
        confirmedList = new ArrayList<>();

        setPatientInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListr();
        doctor_appointments_adapter adapter = new doctor_appointments_adapter(confirmedList, Listr);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListr() {
        Listr = new doctor_appointments_adapter.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getApplicationContext(), PatientBookingInfo.class);
                intent.putExtra("Patient Name",confirmedList.get(position).getPatient_name());
                startActivity(intent);
            }

        };
    }

    private void setPatientInfo() {
        /*confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));
        confirmedList.add(new Patient("Phindulo"));*/
    }
}