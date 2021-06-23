<?php
require "conn.php";
//this file contains the details of the booking for the patient
$patient =$_POST["patient_email"];

if(!empty($patient)){
    $select="SELECT DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_QLF, DOCTOR_SPEC, DOCTOR_EXP, DOCTOR_PHONE, BOOKINGS.DOCTOR_EMAIL,BOOKING_NO, REASON, BOOKING_DATE,BOOKING_TIME FROM DOCTOR RIGHT JOIN BOOKINGS ON DOCTOR.DOCTOR_EMAIL=BOOKINGS.DOCTOR_EMAIL WHERE BOOKINGS.PATIENT_EMAIL='$patient' AND STATUS='FULFILLED' ORDER BY CREATED_AT DESC";
    $sql = mysqli_query($conn, $select);
    $output = array();
        if($result=$sql){

            while($row = $result->fetch_assoc()){
                $output [] = $row;
            }
//            echo "Good";
        }
        else{
            echo "Not Good";
        }
     echo json_encode($output);
}

else{
    echo "There is no email input from POST";
}

mysqli_close($conn); 

?>
