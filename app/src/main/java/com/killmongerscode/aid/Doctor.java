package com.killmongerscode.aid;

import android.app.Activity;

public class Doctor{
  private String name;
  private String surname;
  private String tel;
  private String email;
  private String specialite;
  private String university;
  private String qualification;

  // trying some stuff out

  public Doctor() {
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

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSpecialite() {
    return specialite;
  }

  public void setSpecialite(String specialite) {
    this.specialite = specialite;
  }

  public String getUniversity() {
    return university;
  }

  public void setUniversity(String university) {
    this.university = university;
  }

  public String getQualification() {
    return qualification;
  }

  public void setQualification(String qualification) {
    this.qualification = qualification;
  }
}
