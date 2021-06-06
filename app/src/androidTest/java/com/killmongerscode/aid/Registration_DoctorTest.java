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



@RunWith(AndroidJUnit4.class)
public class Registration_DoctorTest {


    @Rule
    public ActivityTestRule<Registration_Doctor> activityTestRule = new ActivityTestRule<>(Registration_Doctor.class);





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
    public void dummyFunctionWithParams(){
        try{
            runOnUiThread(() -> {
                activityTestRule.getActivity().registration_function("Stringresponse");
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}
