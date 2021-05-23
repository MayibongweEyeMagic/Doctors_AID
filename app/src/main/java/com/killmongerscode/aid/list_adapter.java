package com.killmongerscode.aid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PrivateKey;
import java.util.ArrayList;

public class list_adapter extends RecyclerView.Adapter<list_adapter.MyViewHolder>{

    private ArrayList<Patient> usersList;
    private Context context;

    public list_adapter(ArrayList<Patient>usersList, Context context){
        this.usersList = usersList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_patients, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Name.setText(usersList.get(position).getPatient_name());
        holder.Surname.setText(usersList.get(position).getPatient_lname());
        holder.Email.setText(usersList.get(position).getPatient_email());


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;

        RelativeLayout parentLayout;

        public MyViewHolder(final View view){
            super(view);

            Name =view.findViewById(R.id.Patient_Name);
            Surname =view.findViewById(R.id.Surname);
            Email =view.findViewById(R.id.patient_email);

            parentLayout =view.findViewById(R.id.Lists);
        }

    }

}
