package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IncompleteRecyclerView extends RecyclerView.Adapter<IncompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList;
    private Context context;

    public IncompleteRecyclerView(ArrayList<InOrComplete>usersList, Context context){
        this.usersList = usersList;
        this.context =context;
    }

    @NonNull
    @Override
    public IncompleteRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View incompleteview = LayoutInflater.from(parent.getContext()).inflate(R.layout.incomplete_item, parent, false);
        return new IncompleteRecyclerView.MyViewHolder(incompleteview);
    }

    @Override
    public void onBindViewHolder(@NonNull IncompleteRecyclerView.MyViewHolder holder, int position) {

        holder.Name.setText(usersList.get(position).getName());
        holder.Surname.setText(usersList.get(position).getSurname());
        holder.Email.setText(usersList.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;

        public MyViewHolder(final View view){
            super(view);

            Name =view.findViewById(R.id.doc_incomplete_Name);
            Surname =view.findViewById(R.id.doc_incomplete_surname);
            Email =view.findViewById(R.id.doc_incomplete_email);
        }

    }
}
