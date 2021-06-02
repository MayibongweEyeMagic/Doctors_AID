package com.killmongerscode.aid;

public class Patient {
    private final String Patient_name, Patient_lname, Patient_email;
    private final String Patient_dob, home_address, patient_phone,reason,outcome,booking_date;

    public Patient(String Patient_name,String Patient_lname, String Patient_email,String Patient_dob,String home_address,
                   String patient_phone, String reason,String outcome, String booking_date) {
        this.Patient_name = Patient_name;
        this.Patient_lname = Patient_lname;
        this.Patient_email = Patient_email;
        this.Patient_dob =Patient_dob;
        this.home_address =home_address;
        this.patient_phone =patient_phone;
        this.reason = reason;
        this.outcome =outcome;
        this.booking_date =booking_date;
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

    public String getPatirnt_home_address() {
        return home_address;
    }

    public String getPatient_reason() {
        return reason;
    }

    public String getPatient_outcome() {
        return outcome;
    }

    public String getPatient_booking_date() {
        return booking_date;
    }

}
