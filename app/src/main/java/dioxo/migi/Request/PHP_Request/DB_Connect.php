<?php

$servername = "127.0.0.1";

//Changer Nom DB
$database = "Migi_DB";
$username = "root";
$password = "";
// Create connection
$conn =new mysqli($servername, $username, $password, $database);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
    echo "Connected ERROR.";

}
$conn->query("SET NAMES 'utf8'");
echo "Connected successfully.";

//mysqli_close($conn);



?>