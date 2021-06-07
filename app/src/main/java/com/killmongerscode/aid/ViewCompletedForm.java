package com.killmongerscode.aid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

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

public class ViewCompletedForm extends AppCompatDialogFragment{

    TextView diagnosis, treatment, date, time;
    public String ID, patientEmail;

    public ViewCompletedForm(String ID, String patientEmail) {
        this.ID = ID;
        this.patientEmail = patientEmail;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.doc_completion_form, null);

        diagnosis = view.findViewById(R.id.doc_view_diagnosis);
        treatment =view.findViewById(R.id.doc_view_treatment);
        date =view.findViewById(R.id.doc_view_date);
        time =view.findViewById(R.id.doc_view_time);

        builder.setView(view)
                .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("booking_number", ID)
                .add("email",patientEmail)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/display.php")
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
