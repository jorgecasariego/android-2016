<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		require_once('dbConnect.php');
		
		$sql = "INSERT INTO users (name,email,encrypted_password) VALUES ('$username','$email','$password')";
		
		
		if(mysqli_query($con,$sql)){
			echo "Registrado exitosamente";
		}else{
			echo "El usuario no pudo ser registrado. ".mysqli_error($con);

		}
	}else{
echo 'error';
}