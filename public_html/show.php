<?php
require "conn.php";
//in this file a patient is supposed to view their feedback on an appointment
$booking_no =$_POST["booking_number"];
$dr =$_POST["email_doc"];

if(!empty($booking_no) && !empty($dr)){
    $query="SELECT PATIENT_EMAIL, REASON, BOOKING_DATE, BOOKING_TIME FROM BOOKINGS WHERE DOCTOR_EMAIL='$dr' AND BOOKING_NO='$booking_no'";
    $sql = mysqli_query($conn,$query);
    $output = array();
    if($result=$sql){
           
        while($row = $result->fetch_assoc()){
            $output [] = $row;
        }
    }
       
    else {      
        die('Could not get data: ' . mysql_error());
    }

    echo json_encode($output);

}

else{
    echo "No input data";
}

mysqli_close($conn);
?>
