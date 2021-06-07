<?php
require "conn.php";
//this is a file for updating the statusof the booking to track its progress
//$dr = $_POST["email"];
$status = $_POST["status"];
$booking_no = $_POST["booking number"];


if(!empty($status) && !empty($booking_no)){
    if($status == "ACCEPTED"){
        $query = "UPDATE BOOKINGS SET STATUS='$status' WHERE BOOKING_NO='$booking_no' AND STATUS='PENDING'";
        $sql =mysqli_query($conn, $query);
        if($sql){
            echo "Status has been updated to accepted";
        }
        else {
            echo "Status did not change to accepted";
        }
    }
    elseif($status == "REJECTED"){//i.e 
        $query = "UPDATE BOOKINGS SET STATUS='$status' WHERE BOOKING_NO='$booking_no' AND STATUS='PENDING'";
        $sql =mysqli_query($conn, $query);
        if($sql){
            echo "Status has been updated to rejected";
        }
        else {
            echo "Status did not change to rejected";
        }
    }
    /*else{
        echo "Status input is neither 'rejected' nor 'accepted'";
    }*/

}
    
else{
    echo "No input";
}

mysqli_close($conn);

?>
