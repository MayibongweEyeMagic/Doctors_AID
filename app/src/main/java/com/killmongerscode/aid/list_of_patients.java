package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class list_of_patients extends AppCompatActivity {
    private ArrayList<Patient> usersList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patients);
        recyclerView = findViewById(R.id.Lists);
        usersList = new ArrayList<>();

        setPatientInfo();
        setAdpter();
    }

    private void setAdpter() {
        list_adapter adapter = new list_adapter(usersList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setPatientInfo() {
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
        usersList.add(new Patient("Phindulo"));
    }
}