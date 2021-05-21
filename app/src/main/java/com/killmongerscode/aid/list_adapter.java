package com.killmongerscode.aid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class list_adapter extends RecyclerView.Adapter<list_adapter.MyViewHolder>{

    private ArrayList<Patient> usersList;
    private RecyclerViewClickListner Listner;

    public list_adapter(ArrayList<Patient>usersList, RecyclerViewClickListner Listner){
        this.usersList = usersList;
        this.Listner = Listner;
    }

    @NonNull
    @Override
    public list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_patients, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull list_adapter.MyViewHolder holder, int position) {
        String name = usersList.get(position).getPatient_name();
        String lname = usersList.get(position).getPatient_lname();
        String email = usersList.get(position).getPatient_email();
        holder.Name.setText(name);
        holder.Surname.setText(lname);
        holder.Email.setText(email);


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView Name, Surname, Email;

        public MyViewHolder(final View view){
            super(view);
            Name = view.findViewById(R.id.Patient_Name);
            Surname =view.findViewById(R.id.Surname);
            Email =view.findViewById(R.id.patient_email);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listner.onClick(view, getAdapterPosition());
        }
    }
}
