<?php
require "conn.php";
// this file will be used to access bookings that have been accepted and those that have been fulfilled
//$email = $_POST["email"];
$dr_email =$_POST["patient_email"];

if(!empty($dr_email)){
    $select="SELECT PATIENT_FNAME, PATIENT_LNAME, PATIENT_DOB, PATIENT_ADDRESS,PATIENT_PHONE,BOOKINGS.PATIENT_EMAIL, BOOKING_NO, REASON, OUTCOME, BOOKING_DATE, BOOKING_TIME FROM PATIENT RIGHT JOIN BOOKINGS ON PATIENT.PATIENT_EMAIL=BOOKINGS.PATIENT_EMAIL WHERE DOCTOR_EMAIL='$dr_email' AND STATUS='FULFILLED' ORDER BY CREATED_AT DESC";
    $sql = mysqli_query($conn, $select);
    $output = array();
        if($result=$sql){
            //if($sql_patientQ){
            while($row = $result->fetch_assoc()){
                //while($row = $sql_patientQ){
                $output [] = $row;
            }
           // echo "Good";
        }
    
        else{
            echo "Not Good";
        }
     echo json_encode($output);
}
else {
	echo "The email posted is null or invalid";
}

mysqli_close($conn);
?>
