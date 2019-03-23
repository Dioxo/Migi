<?php
require_once('./DB_Connect.php');
//echo $_POST['email'];
$sql = "SELECT id_user, password FROM user where email = '" . $_POST['email'] . "'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        //echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
        //AJouter l'id utilisateur
        $res += array("id_user" => $row["id_user"]);
        $res += array("password" => $row["password"]);
    }
} else {
        $res += array("password" => "Error");
}

echo json_encode($res);
$conn->close();

?>