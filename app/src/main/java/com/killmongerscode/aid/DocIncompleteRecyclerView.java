package com.killmongerscode.aid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocIncompleteRecyclerView extends RecyclerView.Adapter<DocIncompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private DocRecyclerClickListner listner;
    private Context context;



    public DocIncompleteRecyclerView(Context context,DocRecyclerClickListner listner) {
        this.context = context;
        this.listner = listner;
    }

    public interface DocRecyclerClickListner {
        void DocOnCLickLister(int position);
    }

    public void setDocCLickLster(DocRecyclerClickListner docCLickLster) {
        listner = docCLickLster;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name, Surname, Email;

        Button complete_appointment, view_details;

        public MyViewHolder(final View view) {
            super(view);

            Name = view.findViewById(R.id.doctor_incomplete_Name);
            Surname = view.findViewById(R.id.doctor_incomplete_surname);
            Email = view.findViewById(R.id.doctor_incomplete_email);
            complete_appointment = view.findViewById(R.id.doctor_complete_appointment);
            view_details = view.findViewById(R.id.doctor_view);

            complete_appointment.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (listner != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listner.DocOnCLickLister(position);
                }
            }
        }


    }

    @NonNull
    @Override
    public DocIncompleteRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View incompleteview = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_incomplete_items, parent, false);
        return new DocIncompleteRecyclerView.MyViewHolder(incompleteview);
    }

    @Override
    public void onBindViewHolder(@NonNull DocIncompleteRecyclerView.MyViewHolder holder, int position) {
        holder.Name.setText(usersList.get(position).getName());
        holder.Surname.setText(usersList.get(position).getSurname());
        holder.Email.setText(usersList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void addDocAppointment(InOrComplete inOrComplete) {
        usersList.add(inOrComplete);
        notifyItemInserted(usersList.size() - 1);
    }

    public String getIdNumber(int position){
        return usersList.get(position).getId();
    }

    public void removeFromList(int position){
        usersList.remove(position);
        notifyItemRemoved(position);
    }


}