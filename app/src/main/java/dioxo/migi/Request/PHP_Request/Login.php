<?php

require_once('./DB_Connect.php');


$email = $_POST['email'];
$sql = "SELECT password from user where email ='".$email."'";

$result = $conn->query($sql) ;
if($result){
    while($row = $result->fetch_assoc()) {
        $mdpBDD = $row;
    }
    if(isset($mdpBDD)){ 
        echo json_encode($mdpBDD);    
    }else{
        $error = ['Error'=>'User not Found'];
        echo json_encode($error);
    }
    
}else{
echo 'Erreur pas de resultat '. $conn->error;
}


$conn->close();

?>