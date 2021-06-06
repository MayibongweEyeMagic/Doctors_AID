package com.killmongerscode.aid;

import android.os.Bundle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import junit.framework.TestCase;

@RunWith(AndroidJUnit4.class)
public class Booking_PatientTest extends TestCase {

    @Rule
    public ActivityTestRule<Booking_Patient> activityTestRule = new ActivityTestRule<>(Booking_Patient.class);


    @Test
    public void onCreate() {


        try{
            runOnUiThread(() -> {
                activityTestRule.getActivity().onCreate(new Bundle());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }





    }



    @Test
    public void selectDate() {

        try{
            runOnUiThread(() -> {
                activityTestRule.getActivity().selectDate();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void selectTime() {

        try{
            runOnUiThread(() -> {
                activityTestRule.getActivity().selectTime();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void postToLamp() {

        try{
            runOnUiThread(() -> {
                activityTestRule.getActivity().postTotheLamp("2090040@students.wits.ac.za");
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}