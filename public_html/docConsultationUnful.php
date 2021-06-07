<?php

require "conn.php";
$doc_email = $_POST["email"];
$outcome=$_POST["outcome"];
$booking_no=$_POST["booking_no"];
if(!empty($doc_email)){
    $change="UPDATE BOOKINGS SET OUTCOME='$outcome' WHERE BOOKING_NO='$booking_no';";
    $query="SELECT P.PATIENT_FNAME,P.PATIENT_LNAME,P.PATIENT_PHONE,P.PATIENT_DOB,B.BOOKING_DATE FROM DOCTOR D INNER JOIN BOOKINGS B ON D.DOCTOR_EMAIL=B.DOCTOR_EMAIL INNER JOIN PATIENT P ON B.PATIENT_EMAIL=P.PATIENT_EMAIL WHERE B.DOCTOR_EMAIL='$doc_email' AND STATUS='ACCEPTED' AND BOOKING_NO='$booking_no'";
    $change="UPDATE BOOKINGS SET OUTCOME='$outcome' WHERE DOCTOR_EMAIL='$doc_email'";
    $Sql=mysqli_query($conn,$query);
    $output =array();
    $ts=mysqli_query($conn,$change);
    if($result=$Sql){
        while($row =$result->fetch_assoc()){
            $output[]=$row;
        }
    }else{
            die('Could not get data: '.mysql_error());
        }
        echo json_encode($output);
    
}else{
    echo "Doctor's email cannot be found";
}
mysqli_close($conn);
?>
