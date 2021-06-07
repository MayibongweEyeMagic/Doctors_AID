package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    private ArrayList<Patient> patientList;
    private RecyclerView recyclerView;
    private pending_bookings_adapter.RecyclerViewClickListner Lister;
    String booking_no = "";
     pending_bookings_adapter adapter ;


    private Button accept;
    private Button decline;

    private ArrayList<Patient> usersList =new ArrayList<>();
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

        usersList.remove(position);
        adapter.notifyItemRemoved(position);

    }

    public void setAccept(int position ){

        booking_no = getBooking_no(position);

        usersList.remove(position);
        adapter.notifyItemRemoved(position);

    }


    public void refresh_function(String json) throws JSONException {
        ArrayList<String>temp = new ArrayList<>();

        temp = verification.getSeenPatients(json);

            usersList.clear();
        for(int i =0; i<temp.size();++i){

            String [] thing = temp.get(i).split(":");
            String thing1 = thing[0];
            String thing2 = thing[1];
            String thing3 = thing[2];
            String thing4 = thing[3];
            String thing5 = thing[4];
            String thing6 = thing[5];
            String thing7 = thing[6];
            String thing8 = thing[7];
            String thing9 = thing[8];

            usersList.add(new Patient(thing1, thing2, thing3, thing8, thing9, thing4, thing5, thing6, thing7));

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
                               Toast.makeText(pending_bookings.this, responseData, Toast.LENGTH_SHORT).show();
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
        String temp = usersList.get(position).getPatient_reason();

        return temp;
    }







}