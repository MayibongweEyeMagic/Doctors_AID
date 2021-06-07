package com.killmongerscode.aid;

import android.content.Context;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pending_bookings_adapter extends RecyclerView.Adapter<pending_bookings_adapter.MyViewHolder>{



    private ArrayList<Patient> patientList;
    private RecyclerViewClickListner Listner;


    public pending_bookings_adapter(ArrayList<Patient>patientList, RecyclerViewClickListner listner){
        this.patientList = patientList;
        this.Listner =listner;

    }



        public interface  RecyclerViewClickListner{

        void onItemClick(int position);
       void onItemDelete(int position );


    }

    public void setClicklister(RecyclerViewClickListner listner){

        Listner = listner;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private TextView Nametext, surname, email;
        private Button reject, accept;

        public MyViewHolder(final View view){
            super(view);
            Nametext = view.findViewById(R.id.Patient_Name);
            surname = view.findViewById(R.id.Surname);
            email = view.findViewById(R.id.patient_email);
            reject = view.findViewById(R.id.Decline);
            accept = view.findViewById(R.id.Accept);

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Listner !=null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Listner.onItemClick( position);
                        }

                    }
                }
            });
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Listner !=null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Listner.onItemClick( position);
                        }

                    }
                }
            });

        }

        @Override
        public void onClick(View view) {
            if(Listner !=null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                Listner.onItemDelete(position);
                }

            }
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