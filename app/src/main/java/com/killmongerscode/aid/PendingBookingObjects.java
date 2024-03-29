package com.killmongerscode.aid;

import android.app.Activity;

public class PendingBookingObjects extends Activity {

    private final String Patient_name, Patient_lname, Patient_email,token,Booking_no;
    private final String Patient_dob, home_address, patient_phone,reason,booking_date, time;

    private boolean expandable;

    public PendingBookingObjects(String Patient_name,String Patient_lname, String Patient_email,String Patient_dob,String home_address,
                   String patient_phone, String reason, String booking_no,String booking_date,String time,String token) {

        this.Patient_name = Patient_name;
        this.Patient_lname = Patient_lname;
        this.Patient_email = Patient_email;
        this.Patient_dob =Patient_dob;
        this.home_address =home_address;
        this.patient_phone =patient_phone;
        this.reason = reason;
        this.booking_date =booking_date;
        this.time =time;
        this.Booking_no =booking_no;
        this.token = token;
        this.expandable =false;
    }


    public String getPatientTime() {
        return time;
    }

    public void setExpandableBooking(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isExpandableBooking() {
        return expandable;
    }

    public String getPatient_name()  {
        return Patient_name;
    }


    public String getPatient_lname() {
        return Patient_lname;
    }

    public String getPatient_email() {
        return Patient_email;
    }


    public String getPatient_dob() {
        return Patient_dob;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public String getPatient_home_address() {
        return home_address;
    }

    public String getPatient_reason() {
        return reason;
    }


    public String getPatient_booking_date() {
        return booking_date;
    }

    public String getToken(){
        return  token;
    }

    public String getBooking_no() {
        return Booking_no;
    }
}
