<?php
//create booking
//after patient confirms booking, send the booking information to the database
$patient = $_POST["email"]; //use email to uniquely identify individual
// DATE MUST BE YYYY-MM-DD HH:MM:SS
$booking_date = $_POST["date"]; //this is the time and date that the user has made the appointment to be
/*$doctor_lname = $_POST["lastname"];
$doctor_fname = $_POST["firstname"];*/
$dr_email = $_POST["email"];
$reason = $_POST["reason"]; //why visit
$doctor_spec = $_POST["specialisation"];
//should I add doctor experience?

if(!empty($patient) && !empty($booking_date) && /*!empty($doctor_fname) && !empty($doctor_lname) &&*/ !empty($doctor_spec) && !empty($dr_email)){
    /*$drQuery = "SELECT DOCTOR_EMAIL FROM DOCTOR WHERE DOCTOR_FNAME LIKE '$doctor_fname' AND DOCTOR_LNAME LIKE '$doctor_lname' AND DOCTOR_SPEC LIKE '$doctor_spec'";
    if (mysql_num_rows($drQuery)==1) {*/

    if (empty($reason)) {
        $insert = "INSERT INTO BOOKINGS(PATIENT_EMAIL, DOCTOR_EMAIL, BOOKING_DATE ) VALUES('$patient', '$dr_email', '$booking_date')";
    }
    else{
        $insert = "INSERT INTO BOOKINGS(PATIENT_EMAIL, DOCTOR_EMAIL, BOOKING_DATE, REASON) VALUES('$patient', '$dr_email', '$booking_date', '$reason')";
    }
    
    $sql = mysqli_query($conn, $insert);
    if ($sql) {
        echo "Booking was created successfully!";
            //notify the doctor about the newly created booking
            
            //notify the patient about the booking invite that has been accepted in a different file
    } 
    else{
        echo "Booking was not created succesfully!";
    }
    

    /*else{
     echo "Check again"; //I really don't know what to say here. just NOTE it is possible that mysql_num_rows($drQuery)>1, since we are using non unique data to access email.
    }*/
}

else{
    echo "There may be empty inputs";
}
mysqli_close($conn); 
?>
