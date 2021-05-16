package com.killmongerscode.aid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pending_bookings_adapter extends RecyclerView.Adapter<pending_bookings_adapter.MyViewHolder>{

    private ArrayList<Patient> patientList;
    private RecyclerViewClickListner Listner;

    public pending_bookings_adapter(ArrayList<Patient>patientList, RecyclerViewClickListner Listner){
        this.patientList = patientList;
        this.Listner = Listner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView Nametext;

        public MyViewHolder(final View view){
            super(view);
            Nametext = view.findViewById(R.id.textView8);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listner.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public pending_bookings_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_bookings, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull pending_bookings_adapter.MyViewHolder holder, int position) {
        String name = patientList.get(position).getPatient_name();
        holder.Nametext.setText(name);


    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }
}