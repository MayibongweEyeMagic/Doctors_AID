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
        

    }


}