<?php
        //Code to connect to localhost, change to serverAdress
  $servername = "127.0.0.1";
  $username = "root";
  $password = "";
  $dbname = "Migi_DB";

  // Create connection
  $conn = new mysqli($servername, $username, $password, $dbname);
  // Check connection
  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  }else{
      $res = array("Connection" => "Success");
  }

  ?>