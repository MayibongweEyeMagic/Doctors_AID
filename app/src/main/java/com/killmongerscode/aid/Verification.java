package com.killmongerscode.aid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Verification {

    private ArrayList<String>acesskey = new ArrayList<>();
    private  ArrayList<String>biography = new ArrayList<>();



    // method that retrives data from the database and stores it in an array

    public ArrayList JSONFUCTION(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        String temp = "";

        for(int i=0; i< jsonArray.length();++i){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String email_address = jsonObject.getString("PATIENT_EMAIL");
            String first_name = jsonObject.getString("PATIENT_FNAME");
            String surname = jsonObject.getString("PATIENT_LNAME");
            String home_address = jsonObject.getString("PATIENT_ADDRESS");
            String patient_phone = jsonObject.getString("PATIENT_PHONE");
            String patient_dob = jsonObject.getString("PATIENT_DOB");
            String patient_pass = jsonObject.getString("PATIENT_PASS");
            String pass_salt = jsonObject.getString("PASS_SALT");

            temp = email_address + patient_pass;
             String bio =  first_name + ":" + surname + ":"+ home_address + ":" + patient_phone + ":" + patient_dob + ":" + pass_salt;
            acesskey.add(temp);
        }

        return acesskey;
    }


    public  boolean  CHECKS(String one , String two){

        boolean check = false;

        if(one.equals(two)){

            check = true;

        }


        else {

            check = false;
        }

        return check;
    }


    // method of track user and access biographical information using password and email combination
    private String [] Track_user(String access){

        int index = 0;
        ArrayList<String>temp = new ArrayList<>();

        if(acesskey.contains(access)){

            index = acesskey.indexOf(access);

        }


        String temp1 = biography.get(index);

        String [] array = temp1.split(":");


        return  array;
    }



    // method to get users biographical information from the database

    public  String get_patient_name(String access){

        String [] temp = Track_user(access);
        String thing = temp[0];
            return thing;
        } public  String get_patient_home_address(String access){

        String [] temp = Track_user(access);
        String thing = temp[2];
        return thing;
    }


    public  String get_patient_surname(String access){

        String [] temp = Track_user(access);
        String thing = temp[1];
        return thing;
    }


    public  String get_patient_phone_num(String access){

        String [] temp = Track_user(access);
        String thing = temp[3];
        return thing;
    }

    public  String get_patient_dob(String access){

        String [] temp = Track_user(access);
        String thing = temp[4];
        return thing;
    }





}
