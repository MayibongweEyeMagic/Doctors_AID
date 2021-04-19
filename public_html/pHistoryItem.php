<?php

require conn.php;

if($conn){
    $output=array();
    if($result = mysqli_query($conn, 'CALL ORDER_HISTORY()' )){
        while($row=$result->fetch_assoc()){
            $output [] = $row;
        }
    }
}
mysqli_close($conn);
echo json_encode($output);

?>