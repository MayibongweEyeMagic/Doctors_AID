<?php

require "conn.php";
/*in this file:
we must get the patient's fname, lname, last visited 
this file is for doctor to get information about the patients they have consulted */
    $email = $_POST["email"];
//if(!empty($patient_email)){
    $query = "SELECT PATIENT_FNAME, PATIENT_LNAME, CREATED_AT FROM PATIENT INNER JOIN BOOKINGS WHERE DOCTOR_EMAIL LIKE '$email' AND STATUS LIKE 'FULFILLED'";

    $sql_Q = mysqli_query($conn,$query);
    $output = array();
    if($result=$sql_Q){
        //if($sql_Q){
        while($row = $result->fetch_assoc()){
            //while($row = $sql_Q){
            $output [] = $row;
        }
    }
    /*WHAT HAPPENS WHEN  */
    else {
        # code...TESTING IF THIS WORKS
        die('Could not get data: ' . mysql_error());
    }
    echo json_encode($output);
//}
mysqli_close($conn); 
?>
?>