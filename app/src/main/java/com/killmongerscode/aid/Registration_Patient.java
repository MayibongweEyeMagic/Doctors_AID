package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registration_Patient extends AppCompatActivity {

    String FNAME, LNAME, EMAILADRESS,PASSWORD, CONFIRMPASSWORD, HOMEADDRESS,dateofbirth,PHONENUM,TOKEN, SECurity_question, security_answer;
    EditText name, surname, email, password, homeadress, dob, confirmpassword,answer;

    Button registration_button;
    String TAG ="pushnotification";
    private static final String CHANNEL_ID = "101";

    Random random = new Random();

    private EditText number;


    String[] predefined ={"what was your first dog's name?", "what is your favourite colour", "what is your dream car's brand name", "what is your favourite shoe brand"};
    private AutoCompleteTextView special;
    private ImageView views;



    int t1Hour, t1Minute;

    DatePickerDialog.OnDateSetListener listener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__patient);

        // a new spinner instance
        //spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);


        getToken();

        registration_button = (Button)findViewById(R.id.patient_create_account);
        int temp = random.nextInt(500);
        String temp1 = Integer.toString(temp);

        name = findViewById(R.id.first_name_patient);
        surname = findViewById(R.id.last_name_patient);
        email =findViewById(R.id.patient_email_address);
        password = findViewById(R.id.patient_password);
        confirmpassword = findViewById(R.id.patient_pass_confirm);
        homeadress =findViewById(R.id.location);
        dob = findViewById(R.id.date_of_birth);
        number =findViewById(R.id.phone_no);
        answer = findViewById(R.id.work);

        special = (AutoCompleteTextView) findViewById(R.id.safe1);
        views = (ImageView) findViewById(R.id.drop_down2);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        special.setThreshold(1);
        special.setAdapter(adapter);

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                special.showDropDown();
            }
        });
        // answer to security question

        // question selected from the options


        dob.setInputType(0);
        Calendar calendar = Calendar.getInstance();
        final  int year =calendar.get(Calendar.YEAR);
        final  int month =calendar.get(Calendar.MONTH);
        final  int day =calendar.get(Calendar.DAY_OF_MONTH);


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(
                        Registration_Patient.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month =month+1;
                        String date = year+"-"+month+"-"+day;
                        dob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        OkHttpClient client = new OkHttpClient();


        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                FNAME = name.getText().toString();
                LNAME = surname.getText().toString();
                EMAILADRESS = email.getText().toString();
                PASSWORD = password.getText().toString();
                CONFIRMPASSWORD = confirmpassword.getText().toString();
                HOMEADDRESS = homeadress.getText().toString();
                dateofbirth = dob.getText().toString();
                SECurity_question = special.getText().toString();
                PHONENUM = number.getText().toString();
                security_answer = answer.getText().toString();
                if( PHONENUM.trim().length() != 10)
                {
                    number.setError("Please Enter a 10 digit number");
                    return;
                }

                RequestBody body = new FormBody.Builder()
                        .add("fullname",FNAME)
                        .add("lastname",LNAME)
                        .add("address", HOMEADDRESS)
                        .add("phone",PHONENUM)
                        .add("dob",dateofbirth)
                        .add("email",EMAILADRESS)
                        .add("password",PASSWORD)
                        .add("token",TOKEN)
                        .add("security_question",SECurity_question)
                        .add("answer_question",security_answer)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/registerPatient.php")
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }


                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }

                        final String responseData = response.body().string();
                        Registration_Patient.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                registration_function(responseData);

                            }
                        });


                    }
                });





            }
        });







    }

  /*  public void registration_cherker(){

        ArrayList<String>temp = new ArrayList<>();
        temp.add(FNAME);
        temp.add(LNAME);
        temp.add(EMAILADRESS);
        temp.add(PASSWORD);
        temp.add(CONFIRMPASSWORD);
        temp.add(HOMEADDRESS);
        temp.add(PHONENUM);
        temp.add(dateofbirth);

        if(temp.contains("")){

            Toast.makeText(Registration_Patient.this,
                    "SOME FIELDS MIGHT BE EMPTY", Toast.LENGTH_LONG).show();
        }


    }*/

    private void getToken(){


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete( Task<String> task) {

                if(!task.isSuccessful()){


                }

                TOKEN = task.getResult();
                Log.d(TAG,TOKEN);



            }
        });

    }



    public void registration_function(String response){


        if (CONFIRMPASSWORD.equals(PASSWORD)){
            if(response.equals("Successfully Registered")){

                Toast.makeText(Registration_Patient.this,
                        response, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(Registration_Patient.this,MainActivity.class);
                startActivity(intent);


            }

            else if (response.equals("Can't register one of the fields are empty")){
                Toast.makeText(Registration_Patient.this,
                        "One of the fields is empty", Toast.LENGTH_LONG).show();
            }
            else if (response.equals("Invalid email")){

                Toast.makeText(Registration_Patient.this,
                        "Invalid email", Toast.LENGTH_LONG).show();

            }
            else if(response.equals("Email already exists")){
                Toast.makeText(Registration_Patient.this,
                        "Email already exist", Toast.LENGTH_LONG).show();
            }
            else if(response.equals("under 18 years of age.")){
                Toast.makeText(Registration_Patient.this, "User need to be above 18 to register", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(Registration_Patient.this,
                        "Failed to register", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(Registration_Patient.this,
                    "Password does not match", Toast.LENGTH_LONG).show();

        }
    }

}