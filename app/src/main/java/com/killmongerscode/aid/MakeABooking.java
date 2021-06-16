package com.killmongerscode.aid;

import android.app.Activity;

public class MakeABooking extends Activity {

    private String name, surname, email, specialization, qualification, phone_number, graduated_at, token;

    private boolean expandable;

    public MakeABooking(String name, String surname, String email, String specialization,
                        String qualification, String phone_number, String graduated_at, String token) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.specialization = specialization;
        this.qualification = qualification;
        this.phone_number = phone_number;
        this.graduated_at = graduated_at;
        this.token = token;
        this.expandable =false;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public String getBookName() {
        return name;
    }

    public String getBookSurname() {
        return surname;
    }

    public String getBookEmail() {
        return email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getGraduated_at() {
        return graduated_at;
    }

    public String getToken() {
        return token;
    }
}
