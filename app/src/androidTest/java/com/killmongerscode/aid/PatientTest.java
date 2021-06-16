package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import androidx.fragment.app.FragmentActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)

public class PatientTest {


    String NAME = "name";
    String SURNAME = "surname";
    String EMAIL = "user@gmail.com";
    String Patient_dob = "18-05-2000";
    String home_address = "Wits";
    String patient_phone = "0794166035";
    String reason = "isisu";
    String outcome = "dfg";
    String booking_date = "05-05-2021";


    @Rule
    public ActivityTestRule<Patient> activityTestRule = new ActivityTestRule<>(Patient.class);
    Patient patient;

    @Before

    public void BuildPatientObject(){
        patient = new Patient(NAME,SURNAME, EMAIL, "", "", "","", "","");
    }

    @Test
    public void getPatientName(){
        assertEquals(NAME, patient.getPatient_name());
    }


    @Test
    public void getpatientlname(){
        assertEquals(SURNAME,patient.getPatient_lname());
    }

    @Test
    public void getpatientemail(){
        assertEquals(EMAIL,patient.getPatient_email());
    }

    @Test
    public void getpatientdob(){
        assertEquals(Patient_dob,patient.getPatient_dob());
    }

    @Test
    public void getpatienthomeaddress(){
        assertEquals(home_address,patient.getPatient_home_address());
    }

    @Test
    public void getpatientphone(){
        assertEquals(patient_phone,patient.getPatient_phone());
    }

    @Test
    public void getpatientreson(){
        assertEquals(reason,patient.getPatient_reason());
    }

    @Test
    public void getpatientoutcome(){
        assertEquals(outcome,patient.getPatient_outcome());
    }

    @Test
    public void getpatientbookingdate(){
        assertEquals(booking_date,patient.getPatient_booking_date());
    }
}