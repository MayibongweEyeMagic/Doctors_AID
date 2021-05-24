<?php
require "conn.php";
// this file will be used to access bookings that have been accepted and those that have been fulfilled
//$email = $_POST["email"];
$dr_email = $_POST["dr_email"];

if(!empty($dr_email)){
    $select="SELECT PATIENT_FNAME, PATIENT_LNAME, PATIENT_DOB, PATIENT_ADDRESS,PATIENT_PHONE, BOOKINGS.PATIENT_EMAIL,REASON, BOOKING_DATE FROM PATIENT RIGHT JOIN BOOKINGS ON PATIENT.PATIENT_EMAIL=BOOKINGS.PATIENT_EMAIL WHERE DOCTOR_EMAIL LIKE '$dr_email' AND STATUS LIKE 'PENDING'";
    $sql = mysqli_query($conn, $select);
    $output = array();
        if($result=$sql){
            
            while($row = $result->fetch_assoc()){
                $output [] = $row;
            }
            echo "Good";
        }
    
        else{
            echo "Not Good";
        }
     echo json_encode($output);
}

else{
    echo "There is no email input from POST";
}

mysqli_query($conn); 
?>
