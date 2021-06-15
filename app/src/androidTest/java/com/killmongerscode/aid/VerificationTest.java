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

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)

public class VerificationTest {

    @Rule
    public ActivityTestRule<Verification> activityTestRule = new ActivityTestRule<>(Verification.class);

    @Test
    public void testinggetdoctorname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_doctor_name());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetdoctorqualification(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_doctor_qualification());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetdoctorspecialization(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_doctor_spectialization());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetdoctorphone(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_doctor_phone());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetdoctorsurname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_doctor_surname());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_patient_name());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientsurname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_patient_surname());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientphone(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_patient_phone());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientdob(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_patient_dob());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetpatientlocation(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().get_patient_location());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}