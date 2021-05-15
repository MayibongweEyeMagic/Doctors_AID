package com.killmongerscode.aid;

public class Patient {
    private String Patient_name;

    public Patient(String patient_name) {
        Patient_name = patient_name;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.Patient_name = patient_name;
    }
}
