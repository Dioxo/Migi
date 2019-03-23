<?php
require_once('./DB_Connect.php');
require_once('./Tag.php');

//echo $_POST['email'];
//echo 'holasad';
$id_user = $_POST['id_user'];
//$id_user = -1;
$sql = "select id_note,title, description,have_revision from note where id_user = ".$id_user ;
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    $compt = 0;
    $Notes_id = array();
    while($row = $result->fetch_assoc()) {
        $Tags = Tag::chercherTags($id_user,$row["id_note"],$conn);

      	if(isset($Tags)){
              $Notes_id[$compt++] = array("id_note" => utf8_encode($row["id_note"]),
                                  "title" => utf8_encode($row["title"]),
                                  "description" => utf8_encode($row["description"]),
                                  "have_revision" => utf8_encode($row["have_revision"]),
                                  "Tags" => $Tags
                              );
      	}else{
      	        $Notes_id[$compt++] = array("id_note" => utf8_encode($row["id_note"]),
                                  "title" => utf8_encode($row["title"]),
                                  "description" => utf8_encode($row["description"]),
                                  "have_revision" => utf8_encode($row["have_revision"]),
                              );
      	}
        //Ajouter les tags à la reponse
    }
} else {
        $res += array("result" => "Error");
}

//  print_r($Notes_id);
if(!isset( $$Notes_id )){
  echo json_encode(array());
}else{

  echo json_encode($Notes_id);

}//echo (json_encode($Notes_id));
$conn->close();

?>
