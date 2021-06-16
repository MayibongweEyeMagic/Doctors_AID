package com.killmongerscode.aid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class ViewDetailsOfBooking extends AppCompatDialogFragment {

    String email;
    String booking_no;

    TextView emailOfBookie, date, time, reason;

    public ViewDetailsOfBooking(String email, String booking_no) {
        this.email = email;
        this.booking_no =booking_no;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.view_details, null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        emailOfBookie =view.findViewById(R.id.view_bookie_email);
        date =view.findViewById(R.id.date_booking);
        time =view.findViewById(R.id.view_time_of_booking);
        reason =view.findViewById(R.id.view_reason_for_booking);

        OkHttpClient client =new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("booking_number", booking_no)
                .add("email_doc",email)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/show.php")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


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
                                String mail =jsonObject.getString("PATIENT_EMAIL");
                                String vreason =jsonObject.getString("REASON");
                                String vdate =jsonObject.getString("BOOKING_DATE");
                                String vtime =jsonObject.getString("BOOKING_TIME");


                                emailOfBookie.setText(mail);
                                date.setText(vdate);
                                time.setText(vtime);
                                reason.setText(vreason);

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
