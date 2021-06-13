package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class pending_bookings extends AppCompatActivity {

    private static final String CHANNEL_ID = "101";
    private ArrayList<Patient> patientList;
    private RecyclerView recyclerView;
    private pending_bookings_adapter.RecyclerViewClickListner Lister;
    String booking_no = "";
    String token ="";
     pending_bookings_adapter adapter ;


    private Button accept;
    private Button decline;

    private ArrayList<PendingBookingObjects> usersList =new ArrayList<>();
    String emailAddress;


    Verification verification = new Verification();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_bookings);


        BuildRecyclerView();




         Bundle bundle = getIntent().getExtras();
          emailAddress = bundle.getString("email");




        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("dr_email",emailAddress)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/pending_patientList.php")
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
                pending_bookings.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            refresh_function(responseData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }



        });


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View view) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("dr_email",emailAddress)
                    .build();

            Request request = new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/home/s2090040/pending_patientList.php")
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
                    pending_bookings.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                refresh_function(responseData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }



            });



            }
        });

        setOnClickListr();


       // OkHttpClient client = new OkHttpClient();










    }

    public  void RemoveItem( int position){
        booking_no = getBooking_no(position);
        token = getToken(position);

        usersList.remove(position);
        adapter.notifyItemRemoved(position);

    }

    public void setAccept(int position ){

        booking_no = getBooking_no(position);
        token  = getToken(position);

        usersList.remove(position);
        adapter.notifyItemRemoved(position);

    }


    public void refresh_function(String json) throws JSONException {
        ArrayList<String>temp = new ArrayList<>();

        temp = verification.getSeenPatients(json);

            usersList.clear();
        for(int i =0; i<temp.size();++i){

            String [] thing = temp.get(i).split("#");
            String first_name = thing[0];
            String surname = thing[1];
            String patient_email = thing[2];
            String reason = thing[3];
            String booking_no = thing[4];
            String patient_phone = thing[5];
            String booking_date = thing[6];
            String booking_time =thing[7];
            String patient_dob = thing[8];
            String home_address = thing[9];
            String token = thing[10];

            usersList.add(new PendingBookingObjects(first_name, surname, patient_email,
                    patient_dob, home_address, patient_phone, reason, booking_no,booking_date, booking_time ,token));
        }

         adapter =new pending_bookings_adapter(usersList, Lister);
         recyclerView.setAdapter(adapter);

    }


    private void setOnClickListr() {

       Lister = new pending_bookings_adapter.RecyclerViewClickListner() {
           @Override
           public void onItemClick(int position) {
               RemoveItem(position);

               OkHttpClient client = new OkHttpClient();
               RequestBody body1 = new FormBody.Builder()
                       .add("booking_number",booking_no)
                       .add("token", token)
                       .add("status","REJECTED")
                       .build();

               Request request1 = new Request.Builder()
                       .url("https://lamp.ms.wits.ac.za/home/s2090040/update_status.php")
                       .post(body1)
                       .build();

               client.newCall(request1).enqueue(new Callback() {
                   @Override
                   public void onFailure(@NotNull Call call, @NotNull IOException e) {

                   }

                   @Override
                   public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                       if (!response.isSuccessful()) {
                           throw new IOException("Unexpected code " + response);
                       }

                       final String responseData = response.body().string();
                       pending_bookings.this.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(pending_bookings.this, responseData, Toast.LENGTH_SHORT).show();



                           }
                       });

                   }

               });


           }

           @Override
           public void onItemDelete(int position) {

               setAccept(position);
               OkHttpClient client = new OkHttpClient();
                   RequestBody body1 = new FormBody.Builder()
                       .add("booking_number",booking_no)
                           .add("token",token)
                       .add("status","ACCEPTED")
                       .build();

               Request request1 = new Request.Builder()
                       .url("https://lamp.ms.wits.ac.za/home/s2090040/update_status.php")
                       .post(body1)
                      .build();

               client.newCall(request1).enqueue(new Callback() {
                   @Override
                   public void onFailure(@NotNull Call call, @NotNull IOException e) {

                   }

                   @Override
                   public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                       if (!response.isSuccessful()) {
                           throw new IOException("Unexpected code " + response);
                       }


                       final String responseData = response.body().string();
                       pending_bookings.this.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {


                           }
                       });

                   }

               });
           }
       };

    }






    public void BuildRecyclerView(){

        recyclerView =findViewById(R.id.pending_bookings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        patientList = new ArrayList<>();
    }

    public String getBooking_no(int position){
        return usersList.get(position).getBooking_no();
    }

    public  String getToken(int position){

        return usersList.get(position).getToken();
    }


    private void createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FirebaseNotificationChannel";

            String description = "Receive Firebase Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }







}