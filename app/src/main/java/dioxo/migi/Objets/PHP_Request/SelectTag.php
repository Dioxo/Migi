<?php
require_once('./DB_Connect.php');

$id_user = $_POST['id_user'];
$sql = "SELECT text_tag FROM tag WHERE id_user = ".$id_user." GROUP BY text_tag ORDER BY text_tag";

$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    $compt = 0;
    $Notes_id = array();
    while($row = $result->fetch_assoc()) {
      $Notes_id[$compt++] = utf8_encode($row["text_tag"]);
    }
  } else {
        $res += array("Result" => "Error");
}

if(!isset( $$Notes_id )){
  echo json_encode(array());
}else{
  echo json_encode($Notes_id);
}

$conn->close();

?>
