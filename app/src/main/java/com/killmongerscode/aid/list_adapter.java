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

    public list_adapter(ArrayList<Patient>usersList){
        this.usersList = usersList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView Nametxt;

        public MyViewHolder(final View view){
            super(view);
            Nametxt = view.findViewById(R.id.textView5);
        }
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
        holder.Nametxt.setText(name);


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
