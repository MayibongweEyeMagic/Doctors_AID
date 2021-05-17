package com.killmongerscode.aid;

public class Doctor {
    private String name;
    private String surname;
    private String tel;
    private String email;
    private String specialite;
    private String university;
    private String qualification;


    public Doctor(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.university = university;
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getUniversity() {
        return university;
    }

    public String getQualification() {
        return qualification;
    }

    }
