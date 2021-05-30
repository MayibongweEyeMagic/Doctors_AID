package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pending_bookings_adapter extends RecyclerView.Adapter<pending_bookings_adapter.MyViewHolder>{

    private ArrayList<Patient> patientList;
    private Context context;

    public pending_bookings_adapter(ArrayList<Patient>patientList, Context context){
        this.patientList = patientList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView Nametext, surname, email;


        public MyViewHolder(final View view){
            super(view);
            Nametext =view.findViewById(R.id.Patient_Name);
            surname =view.findViewById(R.id.Surname);
            email =view.findViewById(R.id.patient_email);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_bookings, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Nametext.setText(patientList.get(position).getPatient_name());
        holder.surname.setText(patientList.get(position).getPatient_lname());
        holder.email.setText(patientList.get(position).getPatient_email());



    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

}