package com.killmongerscode.aid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Completion_form extends AppCompatDialogFragment {

    EditText diagnose, treats, time, date;
    private DocIncompleteRecyclerView docIncompleteRecyclerView;
    ArrayList<InOrComplete> userList =new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.complete_appointment_form, null);

        diagnose =view.findViewById(R.id.add_diagnosis);
        treats =view.findViewById(R.id.add_treatment);
        time =view.findViewById(R.id.add_time);
        date =view.findViewById(R.id.date_visit);

        docIncompleteRecyclerView =new DocIncompleteRecyclerView(userList, getContext());


        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.setView(view)
                .setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        docIncompleteRecyclerView.setDocCLickLster(new DocIncompleteRecyclerView.DocRecyclerClickListner() {
                            @Override
                            public void DocOnCLickLister(int position) {
                                    docIncompleteRecyclerView.removeFromList(position);
                            }
                        });

                    }
                });



        return builder.create();
    }

    /*public void addView(){
        View view = getLayoutInflater().inflate(R.layout.add_diagnosis_treatment, null, false);

        ImageView itemclose = view.findViewById(R.id.image_remove);

        itemclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTheView(view);
            }
        });

        linearLayout.addView(view);
    }

    public void removeTheView(View view){
        linearLayout.removeView(view);
    }*/


}
