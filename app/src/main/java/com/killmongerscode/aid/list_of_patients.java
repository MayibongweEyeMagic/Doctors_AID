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

public class list_of_patients extends AppCompatActivity {
    private ArrayList<Patient> usersList;
    private RecyclerView recyclerView;
    private list_adapter.RecyclerViewClickListner Listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patients);
        recyclerView =findViewById(R.id.list);
        usersList = new ArrayList<>();

        setPatientInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListner();
        list_adapter adapter = new list_adapter(usersList, Listner);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListner() {
        Listner = new list_adapter.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getApplicationContext(), PatientDetail.class);
                intent.putExtra("Patient Name",usersList.get(position).getPatient_name());
                startActivity(intent);
            }

        };
    }

    private void setPatientInfo() {
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
        usersList.add(new Patient("Phindulo", "Makhado", "Makhado@gmail.com"));
    }
}