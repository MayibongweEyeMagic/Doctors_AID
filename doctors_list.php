<?php
//get doctors list based on their medical specialization field when patient creates a booking

$patient_email = $_POST["email"];
$dr_spec = $_POST["specialisation"];

if(empty($patient_email)){
    echo "Patient's email not found"; //this means that the data cannot be accessed from the database because
                                        //we do not know whose information we need
}
elseif(empty($dr_spec)){
    echo "Which specialisation is it?"; // specialisation has not been chosen yet
}
else{

    $list = "SELECT DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_EMAIL, DOCTOR_EXP FROM DOCTOR WHERE DOCTOR_SPEC = '$dr_spec'";

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