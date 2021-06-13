package com.killmongerscode.aid;

import android.content.Context;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pending_bookings_adapter extends RecyclerView.Adapter<pending_bookings_adapter.MyViewHolder>{



    private ArrayList<PendingBookingObjects> patientList;
    private RecyclerViewClickListner Listner;


    public pending_bookings_adapter(ArrayList<PendingBookingObjects>patientList, RecyclerViewClickListner listner){
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


    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView Nametext, surname, email, date, time, reason;
         Button reject, accept;

        LinearLayout expand_layout;
        RelativeLayout expandable_desc;
        public MyViewHolder(final View view){
            super(view);
            Nametext = view.findViewById(R.id.bookie_Name);
            surname = view.findViewById(R.id.bookie_surname);
            email = view.findViewById(R.id.bookie_email);
            date =view.findViewById(R.id.date_booking);
            time =view.findViewById(R.id.time_of_booking);
            reason =view.findViewById(R.id.reason_for_booking);
            reject = view.findViewById(R.id.Decline);
            accept = view.findViewById(R.id.Accept);
            expand_layout =view.findViewById(R.id.expanding_layout);
            expandable_desc =view.findViewById(R.id.expandable_desc_bookings);


            expand_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PendingBookingObjects pendingBookingObjects =patientList.get(getAdapterPosition());
                    pendingBookingObjects.setExpandableBooking(!pendingBookingObjects.isExpandableBooking());
                    notifyItemChanged(getAdapterPosition());
                }
            });

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
                            Listner.onItemDelete( position);
                        }

                    }
                }
            });

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
        holder.date.setText(patientList.get(position).getPatient_booking_date());
        holder.time.setText(patientList.get(position).getPatientTime());
        holder.reason.setText(patientList.get(position).getPatient_reason());

        boolean isExpandable =patientList.get(position).isExpandableBooking();
        holder.expandable_desc.setVisibility(isExpandable ? View.VISIBLE : View.GONE);



    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }






}