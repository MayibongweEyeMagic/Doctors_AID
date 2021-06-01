package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteRecyclerView extends RecyclerView.Adapter<CompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList;
    private Context context;

    public CompleteRecyclerView(ArrayList<InOrComplete>usersList, Context context){
        this.usersList = usersList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View completeview = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_items, parent, false);
        return new MyViewHolder(completeview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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

            Name =view.findViewById(R.id.doc_complete_Name);
            Surname =view.findViewById(R.id.doc_complete_surname);
            Email =view.findViewById(R.id.doc_complete_email);
        }

    }
}
