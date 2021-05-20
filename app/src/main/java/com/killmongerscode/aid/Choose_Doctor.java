package com.killmongerscode.aid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Choose_Doctor  extends RecyclerView.Adapter<Choose_Doctor.MyViewHolder> {

    private ArrayList<Patient> usersList;
    private RecyclerViewClickListner Listner;

    public Choose_Doctor(ArrayList<Patient>usersList, RecyclerViewClickListner Listner){
        this.usersList = usersList;
        this.Listner = Listner;
    }

    @NonNull
    @Override
    public Choose_Doctor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_doc, parent, false);
        return new Choose_Doctor.MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull Choose_Doctor.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public MyViewHolder(final View view){
            super(view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listner.onClick(view, getAdapterPosition());
        }
    }
}
