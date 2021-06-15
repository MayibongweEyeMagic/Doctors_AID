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
    public void testinggetpatientname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_name());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientlname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_lname());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientemail(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_email());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientreason(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_reason());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientdob(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_dob());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientphone(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_phone());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatienthomeaddress(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_home_address());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientoutcome(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_outcome());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientboookingdate(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getPatient_booking_date());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}