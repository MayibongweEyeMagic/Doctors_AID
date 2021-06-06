package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocIncompleteRecyclerView extends RecyclerView.Adapter<DocIncompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList;
    private DocRecyclerClickListner listner;
    private Context context;

    public void removeFromList(int positionToDelete){
        usersList.get(positionToDelete);
        notifyItemRemoved(positionToDelete);
    }

    public DocIncompleteRecyclerView(ArrayList<InOrComplete> usersList, Context context) {
        this.usersList =usersList;
        this.context =context;
    }

    public interface DocRecyclerClickListner{
        void DocOnCLickLister(int position);
    }

    public void setDocCLickLster(DocRecyclerClickListner docCLickLster){
        listner =docCLickLster;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;

        Button complete_appointment, view_details;
        public MyViewHolder(final View view, DocRecyclerClickListner listner){
            super(view);

            Name =view.findViewById(R.id.doctor_incomplete_Name);
            Surname =view.findViewById(R.id.doctor_incomplete_surname);
            Email =view.findViewById(R.id.doctor_incomplete_email);
            complete_appointment =view.findViewById(R.id.doctor_complete_appointment);
            view_details =view.findViewById(R.id.doctor_view);

            complete_appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null){
                        int position =getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.DocOnCLickLister(position);
                        }
                    }
                }
            });


        }

    }

    @NonNull
    @Override
    public DocIncompleteRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View incompleteview = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_incomplete_items, parent, false);
        return new DocIncompleteRecyclerView.MyViewHolder(incompleteview, listner);
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

    public void addDocAppointment(InOrComplete inOrComplete){
        usersList.add(inOrComplete);
        notifyItemInserted(usersList.size()-1);
    }

}
