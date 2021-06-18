package com.killmongerscode.aid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class patient_appointments_adapter extends RecyclerView.Adapter<patient_appointments_adapter.MyViewHolder>{

    private ArrayList<DoctorName> usersList;
    private RecyclerViewClickListner Listner;

    public patient_appointments_adapter(ArrayList<DoctorName> usersList, RecyclerViewClickListner Listner){
        this.usersList = usersList;
        this.Listner = Listner;
    }

    @NonNull
    @Override
    public patient_appointments_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.patients_appointments, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull patient_appointments_adapter.MyViewHolder holder, int position) {
        String name = usersList.get(position).getDoctorName();
        holder.Nametext.setText(name);


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView Nametext;

        public MyViewHolder(final View view){
            super(view);
            Nametext = view.findViewById(R.id.doctor_display);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listner.onClick(view, getAdapterPosition());
        }
    }
}
