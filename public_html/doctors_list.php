<?php
//get doctors list based on their medical specialization field when patient creates a booking
require "conn.php";
$dr_spec =$_POST["specialisation"];

if(empty($dr_spec)){
    echo "Which specialisation is it?"; // specialisation has not been chosen yet
}
else{

    $list = "SELECT DOCTOR_FNAME, DOCTOR_LNAME,DOCTOR_SPEC,DOCTOR_QLF, DOCTOR_PHONE, DOCTOR_EMAIL,GRAD_AT, DOCTOR_EXP, TOKEN FROM DOCTOR WHERE DOCTOR_SPEC = '$dr_spec' ORDER BY DOCTOR_FNAME";

    $sql_Q = mysqli_query($conn,$list);
    $output = array();
    if($result=$sql_Q){
        
        while($row = $result->fetch_assoc()){
            
            $output [] = $row;
        }
    }
    else {
        # code...TESTING IF THIS WORKS
        die('Could not get data: ' . mysql_error());
        //echo "Could not obtain data from the database tables";
    }
    echo json_encode($output);
}
mysqli_close($conn); 
?>
