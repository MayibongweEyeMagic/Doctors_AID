<?php
require "conn.php";
// this file will be used to access bookings that have been accepted and those that have been fulfilled
//$email = "Rodgers@gmail.com";
$email =$_POST["dr_email"];

if(!empty($email)){
    $select="SELECT PATIENT_FNAME, PATIENT_LNAME, PATIENT_DOB, PATIENT_ADDRESS,PATIENT_PHONE,BOOKING_NO, BOOKINGS.PATIENT_EMAIL,REASON, BOOKING_DATE,BOOKING_TIME,TOKEN FROM PATIENT RIGHT JOIN BOOKINGS ON PATIENT.PATIENT_EMAIL=BOOKINGS.PATIENT_EMAIL WHERE DOCTOR_EMAIL='$email' AND STATUS='PENDING' ORDER BY CREATED_AT DESC";
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
