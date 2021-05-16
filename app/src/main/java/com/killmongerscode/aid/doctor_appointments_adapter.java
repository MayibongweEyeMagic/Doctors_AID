package com.killmongerscode.aid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctor_appointments_adapter extends RecyclerView.Adapter<doctor_appointments_adapter.MyViewHolder>{

    private ArrayList<Patient> usersList;
    private RecyclerViewClickListner Listner;

    public doctor_appointments_adapter(ArrayList<Patient>usersList, RecyclerViewClickListner Listner){
        this.usersList = usersList;
        this.Listner = Listner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView Nametext;

        public MyViewHolder(final View view){
            super(view);
            Nametext = view.findViewById(R.id.firm);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listner.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public doctor_appointments_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appointments, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull doctor_appointments_adapter.MyViewHolder holder, int position) {
        String name = usersList.get(position).getPatient_name();
        holder.Nametext.setText(name);


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }
}