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
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Completion_form extends AppCompatDialogFragment {

    TextView diagnosis, treatment, date, time;


    public String ID, patientEmail;

    public Completion_form(String ID, String patientEmail) {
        this.ID = ID;
        this.patientEmail =patientEmail;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.complete_appointment_form, null);

        diagnosis = view.findViewById(R.id.view_diagnosis);
        treatment =view.findViewById(R.id.view_treatment);
        date =view.findViewById(R.id.view_date);
        time =view.findViewById(R.id.view_time);


        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("booking number", ID)
                .add("email",patientEmail)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/display_form.php")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(
                    @NotNull Call call, @NotNull Response response) throws IOException {


                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                final String responseData = response.body().string();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONArray jsonArray = new JSONArray(responseData);
                            for(int i=0; i< jsonArray.length();++i) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String outcome =jsonObject.getString("OUTCOME");
                                String[] formDetails =outcome.split(";");

                                diagnosis.setText(formDetails[0]);
                                treatment.setText(formDetails[1]);
                                date.setText(formDetails[2]);
                                time.setText(formDetails[3]);

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }

        });

        return builder.create();
    }



}
