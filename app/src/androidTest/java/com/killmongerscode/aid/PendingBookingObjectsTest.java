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

public class PendingBookingObjectsTest {

    String NAME = "name";
    String SURNAME = "surname";
    String EMAIL = "user@gmail.com";
    String booking_no = "cardiologist";
    String patient_dob = "MBchB";
    String home_address = "0794166035";
    String patient_phone = "Med school";
    String reason = "sick";
    String booking_date = "05-05-2021";
    String Time = "12:32";
    String Token = "45254";

    @Rule
    public ActivityTestRule<PendingBookingObjects> activityTestRule = new ActivityTestRule<>(PendingBookingObjects.class);
    PendingBookingObjects pendingBookings;

    @Before

    public void BuildPendingObject(){
        pendingBookings = new PendingBookingObjects("","","","","","","","","","","");
    }

    @Test
    public void isExapandable(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().isExpandableBooking());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void getPatientTime(){
        assertEquals(Time, pendingBookings.getPatientTime());
    }

    @Test
    public void getPatient_name(){
        assertEquals(NAME, pendingBookings.getPatient_name());
    }

    @Test
    public void getPatient_lname(){
        assertEquals(SURNAME, pendingBookings.getPatient_lname());
    }

    @Test
    public void getPatient_email(){
        assertEquals(EMAIL, pendingBookings.getPatient_email());
    }

    @Test
    public void getPatient_dob(){
        assertEquals(patient_dob, pendingBookings.getPatient_dob());
    }

    @Test
    public void getPhone_number(){
        assertEquals(patient_phone, pendingBookings.getPatient_phone());
    }

    @Test
    public void getaddress(){
        assertEquals(home_address, pendingBookings.getPatient_home_address());
    }

    @Test
    public void getreason(){
        assertEquals(reason, pendingBookings.getPatient_reason());
    }

    @Test
    public void getbooking_date(){
        assertEquals(booking_date, pendingBookings.getPatient_booking_date());
    }

    @Test
    public void getToken(){
        assertEquals(Token, pendingBookings.getToken());
    }

    @Test
    public void getBooking_no(){
        assertEquals(booking_no, pendingBookings.getBooking_no());
    }

}