package com.killmongerscode.aid;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String NAME = "Dr MAKHADO";
    String SURNAME = "surname ";
    String PHONENUM = "079765455";
    String EMAIL = "doerr@gmail.com";
    String SPECIALIZATION = "DEntist";
    String UNIVERSITY = "wits";
    String QUALIFICATION = "MBchB";

    Doctor doc = new Doctor();
    @Test
    public void NameTest(){
        doc.setName(NAME);
        assertEquals(doc.getName(), NAME);
    }
    @Test
    public void surnameTest(){
        doc.setSurname(SURNAME);
        assertEquals(doc.getSurname(), SURNAME);
    }
    @Test
    public void phonenumTest(){
        doc.setTel(PHONENUM);
        assertEquals(doc.getTel(), PHONENUM);
    }
    @Test
    public void emailTest(){
        doc.setEmail(EMAIL);
        assertEquals(doc.getEmail(), EMAIL);
    }
    @Test
    public void specializationTest(){
        doc.setSpecialite(SPECIALIZATION);
        assertEquals(doc.getSpecialite(), SPECIALIZATION);
    }
    @Test
    public void universityTest(){
        doc.setUniversity(UNIVERSITY);
        assertEquals(doc.getUniversity(), UNIVERSITY);
    }
    @Test
    public void qualificationTest(){
        doc.setQualification(QUALIFICATION);
        assertEquals(doc.getQualification(), QUALIFICATION);
    }
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.killmongerscode.aid", appContext.getPackageName());
    }
}