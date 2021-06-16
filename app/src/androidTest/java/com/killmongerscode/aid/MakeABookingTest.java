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
public class MakeABookingTest {

    String NAME = "name";
    String SURNAME = "surname";
    String EMAIL = "user@gmail.com";
    String specialization = "cardiologist";
    String qualification = "MBchB";
    String phone_number = "0794166035";
    String graduated_at = "Med school";
    String Token = "45254";

    @Rule
    public ActivityTestRule<MakeABooking> activityTestRule = new ActivityTestRule<>(MakeABooking.class);
    MakeABooking makebooking;

    @Before

    public void BuildBookingObject(){
        makebooking = new MakeABooking("","","","","","","","");
    }

    @Test
    public void setExapandable(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().isExpandable());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void getBookName(){
        assertEquals(NAME, makebooking.getBookName());
    }

    @Test
    public void getBookSurname(){
        assertEquals(SURNAME, makebooking.getBookSurname());
    }

    @Test
    public void getBookEmail(){
        assertEquals(EMAIL, makebooking.getBookEmail());
    }

    @Test
    public void getBookSpecialization(){
        assertEquals(specialization, makebooking.getSpecialization());
    }

    @Test
    public void getQualification(){
        assertEquals(qualification, makebooking.getQualification());
    }

    @Test
    public void getPhone_number(){
        assertEquals(phone_number, makebooking.getPhone_number());
    }

    @Test
    public void getgradat(){
        assertEquals(graduated_at, makebooking.getGraduated_at());
    }

    @Test
    public void getToken(){
        assertEquals(Token, makebooking.getToken());
    }

}