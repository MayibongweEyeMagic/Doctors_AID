<?php
require "conn.php";
//this is a file for updating the statusof the booking to track its progress
//$dr = $_POST["email"];
$status =$_POST["status"];
$booking_no =$_POST["booking_number"];
//$reason_why =$_POST["why_reject"];
$token=$_POST["token"];

function notify($to,$data){

    $api_key="AIzaSyBVEqD8Ar4QgNETZEDPG2r3NZ-ykttyDWM";
    $url="https://fcm.googleapis.com/fcm/send";
    $fields=json_encode(array('to'=>$to,'notification'=>$data));
    // Generated by curl-to-PHP: http://incarnate.github.io/curl-to-php/
    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, ($fields));

    $headers = array();
    $headers[] = 'Authorization: key ='.$api_key;
    $headers[] = 'Content-Type: application/json';
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

    $result = curl_exec($ch);
    if (curl_errno($ch)) {
        echo 'Error:' . curl_error($ch);
    }
    curl_close($ch);
}


if(!empty($status) && !empty($booking_no) && !empty($token)){
    if($status == "ACCEPTED"){
        $query = "UPDATE BOOKINGS SET STATUS='$status' WHERE BOOKING_NO='$booking_no' AND STATUS='PENDING'";
        $sql =mysqli_query($conn, $query);
        if($sql){
            echo "Status has been updated to accepted";

	$data=array(
        'title'=>'BOOKING STATUS',
        'body'=>'BOOKING HAS BEEN REJECTED'
        );

	notify($token,$data);
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
	$data=array(
        'title'=>"BOOKING STATUS",
        'body'=>"BOOKING HAS BEEN REJECTED"
        );

	notify($token,$data);
        }
        else {
            echo "Status did not change to rejected";
        }
    }



}

else{
    echo "No input";
}

mysqli_close($conn);

?>
