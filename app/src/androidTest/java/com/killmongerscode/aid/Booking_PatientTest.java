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
public class Booking_PatientTest {

    @Rule
    public ActivityTestRule<Booking_Patient> activityTestRule = new ActivityTestRule<>(Booking_Patient.class);


    @Test
    public void testingfieldsclickable(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().findViewById(R.id.doctor_spec).performClick();
                activityTestRule.getActivity().findViewById(R.id.doctors).performClick();
                activityTestRule.getActivity().findViewById(R.id.Reason).performClick();
                activityTestRule.getActivity().findViewById(R.id.select_date).performClick();
                activityTestRule.getActivity().findViewById(R.id.select_time).performClick();
                activityTestRule.getActivity().findViewById(R.id.button_show2).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsselectdate(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().selectDate());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsselectTime(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().selectTime());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingposttotheThelamp(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().postTotheLamp("phieze9@gmail.com"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingssetSelected(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setSelected("5665416","Dr Luqhide"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsgetselectedemail(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getSelectedEmail());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsgetselectdToken(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getSelectedToken());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}