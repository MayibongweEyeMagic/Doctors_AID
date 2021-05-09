<?php

    require "conn.php";
        //WHILE LOGGED IN, USE THE USER'S UNIQUE EMAIL TO FETCH THEIR INFORMATION FROM DATABASE
        $doctor_email = $_POST["doctor_email"];
    //if(!emp(ty$doctor_email)){

        $doctor_query = "SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL = '$doctor_email'";

        $sql_doctorQ = mysqli_query($conn,$doctor_query);
        $output = array();
        //THE OUTPUT ARRAY SHOULD CONTAIN:
        //DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_SPEC, DOCTOR_QLF, DOCTOR_PHONE, DOCTOR_EMAIL, DOCTOR_PASS, GRAD_AT
        if($result=$sql_doctorQ){
            while($row = $result->fetch_assoc()){
                $output [] = $row;
            }
        }
        //WHAT HAPPENS IF !$sql_doctorQ, i.e doctor's information is not obtained???
        echo json_encode($output);
    //}
 
mysqli_close($conn);


?>
