<?php
require "conn.php";
//in this file a patient is supposed to view their feedback on an appointment
$booking_no = $_POST["booking number"];
$patient = $_POST["email"];

if(!empty($booking_no) && !empty($patient)){
    $query="SELECT OUTCOME FROM BOOKINGS WHERE PATIENT_EMAIL='$patient' AND BOOKING_NO='$booking_no'";
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