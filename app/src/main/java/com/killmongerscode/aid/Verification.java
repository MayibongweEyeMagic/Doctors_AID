package com.killmongerscode.aid;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static androidx.core.content.ContextCompat.startActivity;

public class Verification extends Activity {

    private ArrayList<String>acesskey = new ArrayList<>();
    private  ArrayList<String>biography = new ArrayList<>();
    private  String bio, patBio, patientBio;




    // method that retrives data from the database and stores it in an array

    public String JSONFUCTION(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String first_name = jsonObject.getString("PATIENT_FNAME");
            String surname = jsonObject.getString("PATIENT_LNAME");
            String home_address = jsonObject.getString("PATIENT_ADDRESS");
            String patient_phone = jsonObject.getString("PATIENT_PHONE");
            String patient_dob = jsonObject.getString("PATIENT_DOB");

             patBio =  first_name + ":" + surname + ":"+ home_address + ":" + patient_phone + ":" + patient_dob ;
        }
        return patBio;
    }

    public String JSONFUCTION_DOCTOR(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i){

            JSONObject jsonObject = jsonArray.getJSONObject(i);


            String first_name = jsonObject.getString("DOCTOR_FNAME");
            String surname = jsonObject.getString("DOCTOR_LNAME");
            String specialization = jsonObject.getString("DOCTOR_SPEC");
            String doctor_phone_phone = jsonObject.getString("DOCTOR_PHONE");
            String doctor_qlf = jsonObject.getString("DOCTOR_QLF");
            String grad_at = jsonObject.getString("GRAD_AT");


             bio =  first_name + ":" + surname + ":"+ specialization + ":" + doctor_phone_phone + ":" + doctor_qlf + ":" + grad_at;
        }

        return bio;
    }






    // method of track user and access biographical information using password and email combination


    public ArrayList<String> getSeenPatients(String json) throws JSONException{
        ArrayList<String> getPatients =new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String first_name = jsonObject.getString("PATIENT_FNAME");
            String surname = jsonObject.getString("PATIENT_LNAME");
            String patient_dob = jsonObject.getString("PATIENT_DOB");
            String home_address = jsonObject.getString("PATIENT_ADDRESS");
            String patient_email =jsonObject.getString("PATIENT_EMAIL");
            String patient_phone = jsonObject.getString("PATIENT_PHONE");
            String reason = jsonObject.getString("REASON");
            String booking_no = jsonObject.getString("BOOKING_NO");
            String booking_date = jsonObject.getString("BOOKING_DATE");
            String booking_time = jsonObject.getString("BOOKING_TIME");
            String token = jsonObject.getString("TOKEN");

           String patientBio = first_name + "#" + surname + "#" + patient_email + "#" + reason+ "#" + booking_no + "#" + patient_phone + "#" + booking_date +"#" + booking_time + "#" + patient_dob + "#" + home_address +"#"+ token;
            getPatients.add(patientBio);
        }
        return getPatients;
    }


    // method to get users biographical information from the database

    public  String get_doctor_name(){

        String [] temp= bio.split(":");
        String thing = temp[0];
            return thing;
        }

    public  String get_doctor_qualification(){

        String [] temp= bio.split(":");
        String thing = temp[4];
        return thing;
    }

    public  String get_doctor_spectialization(){

        String [] temp= bio.split(":");
        String thing = temp[2];
        return thing;
    }

    public  String get_doctor_phone(){

        String [] temp= bio.split(":");
        String thing = temp[3];
        return thing;
    }


        public  String get_doctor_surname(){

        String [] temp = bio.split(":");
        String thing = temp[1];
        return thing;
    }

    public  String get_doctor_gad_at(){

        String [] temp= bio.split(":");
        String thing = temp[5];
        return thing;
    }

    public  String get_patient_name(){

        String [] temp= patBio.split(":");
        String thing = temp[0];
        return thing;
    }


    public  String get_patient_surname(){

        String [] temp = patBio.split(":");
        String thing = temp[1];
        return thing;
    }

    public  String get_patient_phone(){

        String [] temp = patBio.split(":");
        String thing = temp[3];
        return thing;
    }

    public  String get_patient_dob(){

        String [] temp = patBio.split(":");
        String thing = temp[4];
        return thing;
    }

    public  String get_patient_location(){

        String [] temp = patBio.split(":");
        String thing = temp[2];
        return thing;
    }

    public ArrayList getDoctorEmails(String json) throws JSONException{

            ArrayList<String> emails = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String email = jsonObject.getString("DOCTOR_EMAIL");

                emails.add(email);
            }
            return emails;
    }

    public ArrayList<String> getPatientEmails(String json) throws JSONException{

        ArrayList<String> emails = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String email = jsonObject.getString("PATIENT_EMAIL");

            emails.add(email);
        }
        return emails;
    }








}
