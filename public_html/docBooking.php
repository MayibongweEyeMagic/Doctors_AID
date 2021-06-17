<?php

require "conn.php";
$patient_email = $_POST["email"];
$booking_no=$_POST["booking_no"];

if(!empty($booking_no)){
    $query ="SELECT P.PATIENT_FNAME,P.PATIENT_LNAME,B.BOOKING_DATE,B.REASON FROM BOOKINGS B INNER JOIN PATIENT P ON B.PATIENT_EMAIL=P.PATIENT_EMAIL WHERE P.PATIENT_EMAIL='$patient_email' AND B.BOOKING_NO='$booking_no' ";
    $Sql = mysqli_query($conn,$query);
    $output =array();

    
    if($result=$Sql){
        while($row =$result->fetch_assoc()){
            $output[]=$row;
        }
    }else{
            die('Could not get data: '.mysql_error());
        }
        echo json_encode($output);
    
}else{
    echo "Booking could not be found";
}
mysqli_close($conn);
?>
