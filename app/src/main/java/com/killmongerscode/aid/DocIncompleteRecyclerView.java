package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocIncompleteRecyclerView extends RecyclerView.Adapter<DocIncompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private Context context;

    public DocIncompleteRecyclerView(Context context, ArrayList<InOrComplete> usersList) {
        this.context =context;
        this.usersList =usersList;
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

    public void addDocAppointment(InOrComplete inOrComplete){
        usersList.add(inOrComplete);

        notifyItemInserted(usersList.size()-1);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;

        public MyViewHolder(final View view){
            super(view);

            Name =view.findViewById(R.id.doctor_incomplete_Name);
            Surname =view.findViewById(R.id.doctor_incomplete_surname);
            Email =view.findViewById(R.id.doctor_incomplete_email);
        }

    }
}
