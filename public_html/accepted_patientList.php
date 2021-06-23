<?php
require "conn.php";
// this file will be used to access bookings that have been accepted and those that have been fulfilled
//$dr_email = "Rodgers@gmail.com";
$dr_email = $_POST["patient_email"];

if(!empty($dr_email)){
    $select="SELECT PATIENT_FNAME, PATIENT_LNAME, PATIENT_DOB, PATIENT_ADDRESS,PATIENT_PHONE,BOOKING_NO, BOOKINGS.PATIENT_EMAIL,REASON, OUTCOME, BOOKING_DATE,BOOKING_TIME FROM PATIENT RIGHT JOIN BOOKINGS ON PATIENT.PATIENT_EMAIL=BOOKINGS.PATIENT_EMAIL WHERE DOCTOR_EMAIL='$dr_email' AND STATUS='ACCEPTED' ORDER BY CREATED_AT DESC";
    $sql = mysqli_query($conn, $select);
    $output = array();
        if($result=$sql){
            
            while($row = $result->fetch_assoc()){
                $output [] = $row;
            }
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
