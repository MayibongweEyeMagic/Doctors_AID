<?php

require "conn.php";
$doc_email = $_POST["email"];
$outcome=$_POST["outcome"];
if(!empty($doc_email)){
    $query="SELECT P.PATIENT_FNAME,P.PATIENT_LNAME,P.PATIENT_PHONE,P.PATIENT_DOB,B.BOOKING_DATE FROM DOCTOR D INNER JOIN BOOKINGS B ON D.DOCTOR_EMAIL=B.DOCTOR_EMAIL INNER JOIN PATIENT P ON B.PATIENT_EMAIL=P.PATIENT_EMAIL WHERE B.DOCTOR_EMAIL='$doc_email'";
    $change="UPDATE BOOKINGS SET OUTCOME='$outcome' WHERE DOCTOR_EMAIL='$doc_email'";
    $Sql=mysqli_query($conn,$query);
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
    echo "Doctor's email cannot be found";
}
mysqli_close($conn);
?>
