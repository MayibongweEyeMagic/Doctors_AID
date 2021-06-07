package com.killmongerscode.aid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteRecyclerView extends RecyclerView.Adapter<CompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private Context context;
    private CompleteRecyclerClickListner listner;


    public interface CompleteRecyclerClickListner{
        void OnCLickLister(int position);
    }

    public void setDocCLickLster(CompleteRecyclerClickListner docCLickLster){
        listner =docCLickLster;
    }


    public CompleteRecyclerView(Context context, ArrayList<InOrComplete>usersList){
        this.usersList = usersList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View completeview = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_items, parent, false);
        return new MyViewHolder(completeview, listner);
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

    public void addAppointmentComp(InOrComplete inOrComplete){
        usersList.add(inOrComplete);

        notifyItemInserted(usersList.size()-1);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;
        private Button view_form;
        public MyViewHolder(final View view, CompleteRecyclerClickListner listner){
            super(view);

            Name =view.findViewById(R.id.doc_complete_Name);
            Surname =view.findViewById(R.id.doc_complete_surname);
            Email =view.findViewById(R.id.doc_complete_email);
            view_form =view.findViewById(R.id.view_details);

            view_form.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner !=null){
                        int position =getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.OnCLickLister(position);
                        }
                    }
                }
            });

        }

    }
}
