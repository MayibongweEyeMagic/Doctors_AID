package com.killmongerscode.aid;

import androidx.fragment.app.Fragment;

public class DoctorName extends Fragment {

    private String Doctor_name;

    public DoctorName(String doctor_name) {
        Doctor_name = doctor_name;
    }

    public String getDoctorName() {
        return Doctor_name;
    }

    public void setDoctorName(String doctor_name) {
        this.Doctor_name = doctor_name;
    }

}
