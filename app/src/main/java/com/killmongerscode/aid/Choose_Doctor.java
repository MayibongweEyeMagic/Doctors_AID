package com.killmongerscode.aid;

import android.app.admin.SecurityLog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Choose_Doctor  extends RecyclerView.Adapter<Choose_Doctor.MyViewHolder> {

    private ArrayList<MakeABooking> usersList =new ArrayList<>();

    public Choose_Doctor(){
    }

    public void addDocAppointment(MakeABooking makeABooking) {
        usersList.add(makeABooking);
        notifyItemInserted(usersList.size() - 1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View patientview = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_doctors_items, parent, false);
        return new MyViewHolder(patientview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(usersList.get(position).getBookName());
        holder.surname.setText(usersList.get(position).getBookSurname());
        holder.email.setText(usersList.get(position).getBookEmail());
        holder.specialization.setText(usersList.get(position).getSpecialization());
        holder.qualification.setText(usersList.get(position).getQualification());
        holder.phoneNumber.setText(usersList.get(position).getPhone_number());
        holder.graduated_at.setText(usersList.get(position).getGraduated_at());

        boolean isExpandable =usersList.get(position).isExpandable();
        holder.expandable_desc.setVisibility(isExpandable ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount()
    {
        return usersList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, surname, email, specialization, qualification, phoneNumber, graduated_at;

        LinearLayout expand_layout;
        RelativeLayout expandable_desc;
        public MyViewHolder(final View view){
            super(view);

            name =view.findViewById(R.id.doc_select_Name);
            surname =view.findViewById(R.id.doc_select_surname);
            email =view.findViewById(R.id.doc_booking_email);
            specialization =view.findViewById(R.id.doc_specialization);
            qualification =view.findViewById(R.id.doc_qualification);
            phoneNumber =view.findViewById(R.id.cellNumber);
            graduated_at =view.findViewById(R.id.grad_at);
            expand_layout =view.findViewById(R.id.expandable_layout);
            expandable_desc =view.findViewById(R.id.expandable_descriptions);

            expand_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MakeABooking makeABooking =usersList.get(getAdapterPosition());
                    makeABooking.setExpandable(!makeABooking.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

    }
}
