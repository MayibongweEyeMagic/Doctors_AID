<?php

require "conn.php";

$doctor_userid = $_POST["doctor_userid"];
$d_id = (int)$doctor_userid;

if($conn){
    $doctor_query = "SELECT * FROM DOCTOR WHERE DOCTOR_NO= '$d_id'";

    $sql_doctorQ = mysqli_query($conn,$doctor_query);
    $output = array();
    if($result=$sql_doctorQ){
        while($row = $result->fetch_assoc()){
            $output [] = $row;
        }
    }
}
mysqli_close($conn);
echo json_encode($output);

?>