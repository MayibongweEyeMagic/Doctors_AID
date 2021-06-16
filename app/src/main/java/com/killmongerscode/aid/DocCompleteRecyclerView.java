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

public class DocCompleteRecyclerView  extends RecyclerView.Adapter<DocCompleteRecyclerView.MyViewHolder> {

    private ArrayList<InOrComplete> usersList;
    private Context context;
    private DocCompleteRecyclerClickListner listner;


    public interface DocCompleteRecyclerClickListner{
        void OnCLickLister(int position);
    }

    public void setDocCompleteCLickLster(DocCompleteRecyclerClickListner docCLickLster){
        listner =docCLickLster;
    }

    public DocCompleteRecyclerView(Context context, ArrayList<InOrComplete> usersList) {
        this.context =context;
        this.usersList =usersList;
    }

    @NonNull
    @Override
    public DocCompleteRecyclerView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View completeview = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_complete_items, parent, false);
        return new DocCompleteRecyclerView.MyViewHolder(completeview, listner);

    }

    @Override
    public void onBindViewHolder(@NonNull DocCompleteRecyclerView.MyViewHolder holder, int position) {
        holder.Name.setText(usersList.get(position).getName());
        holder.Surname.setText(usersList.get(position).getSurname());
        //holder.Email.setText(usersList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void addDocAppointmentComp(InOrComplete inOrComplete){
        usersList.add(inOrComplete);

        notifyItemInserted(usersList.size()-1);
    }

    public String getIdNumber(int position){
        return usersList.get(position).getId();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Surname, Email;
        Button button;
        public MyViewHolder(final View view, DocCompleteRecyclerClickListner listner){
            super(view);

            Name =view.findViewById(R.id.doctor_complete_Name);
            Surname =view.findViewById(R.id.doctor_complete_surname);
            button =view.findViewById(R.id.doc_view_details);

            button.setOnClickListener(new View.OnClickListener() {
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
