<?php
//update to fulfilled
$booking_no =$_POST["booking number"];
$status =$_POST["status"];
$outcome=$_POST["outcome"];

if(!empty($booking_no) && !empty($status)){
    if($status=='FULFILLED'){
        $query = "UPDATE BOOKINGS SET STATUS='$status', OUTCOME='$outcome' WHERE BOOKING_NO='$booking_no' AND STATUS='ACCEPTED'";
        $sql =mysqli_query($conn, $query);
        if ($sql) {
            echo "Status has been updated to fulfilled";
        } else {
            echo "Status did not change to fulfilled";
        }
    }
    else{
        echo "Status input is not equal to 'fulfilled'";
    }
}
else{
    echo "No input";
}

?>