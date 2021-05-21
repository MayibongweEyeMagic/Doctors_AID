package com.killmongerscode.aid;

public class Patient {
    private final String Patient_name, Patient_lname, Patient_email;

    public Patient(String patient_name,String patient_lname, String patient_email ) {
        this.Patient_name = patient_name;
        this.Patient_lname = patient_lname;
        this.Patient_email = patient_email;
    }

    public String getPatient_name() {
        return Patient_name;
    }
    public String getPatient_lname() {
        return Patient_lname;
    }
    public String getPatient_email() {
        return Patient_email;
    }

    //public void setPatient_name(String patient_name) {
      //  this.Patient_name = patient_name;
   // }
}
