<?php

    require "conn.php";

        $patient_email = $_POST["patient_email"];
    //if(!empty($patient_email)){
        $patient_query = "SELECT * FROM PATIENT WHERE PATIENT_EMAIL= '$patient_email'";

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
