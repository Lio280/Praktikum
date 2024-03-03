<?php
ini_set("display_errors",0);
//phpinfo();

//function DB(){ 
global $servername;
$servername = "127.0.0.1";
global $username;
$username = "gaeste";
global $password;
$password = "abc!?123456";
global $dbname;
$dbname ="gaestebuch";
global $conn;
$conn = new mysqli($servername, $username, $password);
global $select_db;
$select_db = mysqli_select_db($conn, $dbname);
//}
//DB();

function insertQuery($name,$text,$conn) {


    $insert = "INSERT INTO eintraege (name, text, datum) VALUES (?,?, NOW())";
    // insertQuery($insert);

    $stm = $conn->prepare($insert);
    $stm->bind_param('ss', $name, $text);
    $stm->execute();
    $stm->close();

}

function löschen($id,$conn) {
    //noch ein sql befehll
    $sql = "DELETE FROM eintraege WHERE id = ?";
    //sql ausführen die zweite 
    $stm = $conn->prepare($sql);
    $stm->bind_param("s", $id);
    $stm->execute();
    $stm->close();
}





// if (!$select_db) {
//    die("DB select Faild" . mysqli_error($connection));
//}

//verbindung Check

if ($conn->connect_error) {
    //eingaben validieren
    $name = trim($_POST["name"]);
    $text = trim($_POST["text"]);
    $name = strip_tags($name);
    $text = strip_tags($text);
    $name = htmlspecialchars($name);
    $text = htmlspecialchars($text);

    //SQL-Befehle vorbereiten
	
	// $insert = "INSERT INTO eintraege (name, text, datum) VALUES (?, ?, NOW())";
	// insertQuery($insert);
    
}

$name ="";
$text ="";

//if($_GET['name'] != "" && $_GET['text'] != "") {
if(isset($_POST['name']) && $_POST['name'] !== "" && isset($_POST['text']) && $_POST['text'] !== "") {

	$name = $_POST['name'];
	$text = $_POST['text'];

	insertQuery($name,$text,$conn);
}



//if ($_servername["REQUEST_METHOD"] == "POST") {
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    // Überprüfe, ob der Submit-Button mit dem Namen "submit" geklickt wurde
    if (isset($_POST["submit"])) {
        // Rufe die Funktion auf
		echo "wurde gesendet!";
        // insertQuery();
    }
}


// delete knopf

if (isset($_POST['delete'])) {
    //id erhalten 
	if(isset($_POST['id'])){
	
		löschen($_POST['id'],$conn);
	}  
}



//Datenbank abfragen 
$sql = "SELECT * FROM eintraege ORDER BY datum DESC";
$result = $conn->query($sql);

//verbindunk kappen
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">

<head>
	<link href="style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Einfaches Gästebuch mit PHP</title>

</head>
<body>
<div class="container">
	
	<header class="Header">
	<div class="ribbon">
		<div class="ribbon-stitches-top"></div>
		<div class="ribbon-content"><p><b>Gästebuch Praktikum Woche Drei</b></p></div>
		<div class="ribbon-stitches-bottom"></div>
	</div>
	</header>
   

    <!-- <form method="post" action="<?php //echo $_localhost["PHP_SELF"];?>"> -->
    <div class="top">
	<form method="post" action="http://localhost/myapp/index.php">
	<div class="input">
        <lable for="name">Name:</lable>
        <input type="text" id="name" name="name" required value="<?php echo $name; ?>" />
        <br /> <br />
        <lable for="text"> Hier schreiben:</lable><br>
        <textarea name="text" id="text" rows="5" cols="50" required><?php echo $text; ?></textarea>
        <input class="bntt" type="submit" name="submit" value="Eintrag hinzufügen">
	</div>
	</form>
	</div>
<hr>
<hr>


<h2>Frühere Einträge:<h2>
<?php
    #anzeigen wenn es welche gibt 
    if ($result->num_rows > 0)
	{
        //jeden eintrag darstellen
        echo "<div>"; 
		echo "<table  border='0'";
        echo "<tr><th>Name</th><th>Eintrag</th><th>Datum</th></tr>";
        while($row = $result->fetch_assoc())
		{
			echo '<form action="http://localhost/myapp/index.php" method="POST">';
            echo '<tr>';
            echo '<td class="input-container">' . $row["name"] . "</td>";
            echo '<td class="input-container">' . $row["text"] . "</td>";
            echo '<td class="input-container">' . $row["datum"] . "</td>";
            echo '<td class="input-container"><input type="hidden" name="id" value="'.$row['id'].'">';
            echo '<input type="submit" class="" name="delete" value="Eintrag Löschen" class="Button"/> </td>';
            echo '</form>';
			echo '</td>';
            echo '</tr>';
			echo "</div>";
        }
        echo "</table>";
	} else {
		//wenn keine einträge
		echo '<p>Es giebt noch keine Einträge</p>';
    }
?>
 </div>   
</body>
</html>