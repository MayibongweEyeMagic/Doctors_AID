package com.killmongerscode.aid;

import android.app.admin.SecurityLog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Choose_Doctor  extends RecyclerView.Adapter<Choose_Doctor.MyViewHolder> {

    private ArrayList<SelectByProfession> usersList;
    private Context context;

    public Choose_Doctor(ArrayList<SelectByProfession>usersList, Context context){
        this.usersList = usersList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_doc, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Name.setText(usersList.get(position).getDocName());
        holder.Surname.setText(usersList.get(position).getDoSurname());
        holder.Email.setText(usersList.get(position).getDocEmail());

    }

    @Override
    public int getItemCount()
    {
        return usersList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;

        RelativeLayout parentLayout;

        public MyViewHolder(final View view){
            super(view);

            Name =view.findViewById(R.id.Doctor_Name);
            Surname =view.findViewById(R.id.doc_surname);
            Email =view.findViewById(R.id.doc_mail);

            parentLayout =view.findViewById(R.id.parent_layout);
        }

    }
}
