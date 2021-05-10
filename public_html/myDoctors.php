<?php
    require "conn.php";
/*in this file:
we must get the doctor's fname, lname, speciality and last visited 
this file is for patient to get information about the doctors they have consulted */
        $patient_email = $_POST["patient_email"];
    //if(!empty($patient_email)){
        $patient_query = "SELECT DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_SPEC, CREATED_AT FROM DOCTOR INNER JOIN BOOKINGS WHERE PATIENT_EMAIL LIKE '$patient_email' AND STATUS LIKE 'FULFILLED'";

        $sql_patientQ = mysqli_query($conn,$patient_query);
        $output = array();
        if($result=$sql_patientQ){
            //if($sql_patientQ){
            while($row = $result->fetch_assoc()){
                //while($row = $sql_patientQ){
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