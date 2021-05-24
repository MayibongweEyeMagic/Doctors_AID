<?php
require "conn.php";
//this is a file for updating the statusof the booking to track its progress
$dr = $_POST["email"];
$status = $_POST["status"];

if(!empty($status)){
    if($status == "ACCEPTED"){
        $query = "UPDATE BOOKINGS SET STATUS='$status' WHERE DOCTOR_EMAIL='$dr' AND STATUS='PENDING'";
        $sql =mysqli_query($conn, $query);
        if($sql){
            echo "Status has been updated to accepted";
        }
        else {
            echo "Status did not change";
        }
    }
    elseif($status == FULFILLED){//i.e 
        $query = "UPDATE BOOKINGS SET STATUS='$status' WHERE DOCTOR_EMAIL='$dr' AND STATUS='ACCEPTED'";
        $sql =mysqli_query($conn, $query);
        if($sql){
            echo "Status has been updated to fulfilled";
        }
        else {
            echo "Status did not change";
        }
    }
}
mysqli_close($conn);

?>