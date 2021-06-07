package com.killmongerscode.aid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

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

public class DocComplete_form extends AppCompatDialogFragment {

    public int position;
    public String ID;
    public DocIncompleteRecyclerView docIncompleteRecyclerView;
    public EditText et_date, et_time, diagnosis, treatment;
    public Activity activity;

    int t1Hour, t1Minute;

    public DocComplete_form(int position, DocIncompleteRecyclerView docIncompleteRecyclerView, String ID, Activity activity) {
        this.position = position;
        this.docIncompleteRecyclerView =docIncompleteRecyclerView;
        this.ID =ID;
        this.activity =activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.activity_appointment_detail, null);

        et_date = view.findViewById(R.id.date_visit);
        et_time =view.findViewById(R.id.add_time);
        diagnosis =view.findViewById(R.id.add_diagnosis);
        treatment = view.findViewById(R.id.add_treatment);

        et_time.setInputType(0);
        et_date.setInputType(0);

        selectDate();
        selectTime();

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

                        String diagnose =diagnosis.getText().toString();
                        String treats =treatment.getText().toString();
                        String date =et_date.getText().toString();
                        String time =et_time.getText().toString();

                        String OUTCOME = diagnose+";"+treats+";"+date+";"+time;

                        OkHttpClient client = new OkHttpClient();

                        RequestBody body = new FormBody.Builder()
                                .add("booking_number", ID)
                                .add("status", "FULFILLED")
                                .add("outcome", OUTCOME)
                                .build();

                        Request request = new Request.Builder()
                                .url("https://lamp.ms.wits.ac.za/home/s2090040/update_to_fulfilled.php")
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
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(activity, responseData, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                        docIncompleteRecyclerView.removeFromList(position);
                    }
                });




        return builder.create();
    }

    public void selectDate(){


        Calendar calendar = Calendar.getInstance();
        final  int year =calendar.get(Calendar.YEAR);
        final  int month =calendar.get(Calendar.MONTH);
        final  int day =calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month =month+1;
                        String date = year+"-"+month+"-"+day;
                        et_date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    public void selectTime(){

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(
                        getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute =minute;

                        Calendar calendar1 =Calendar.getInstance();
                        calendar1.set(0,0,0,t1Hour,t1Minute);
                        et_time.setText(DateFormat.format("hh:mm aa", calendar1));
                    }
                },12,0,false
                );

                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });
    }
}
