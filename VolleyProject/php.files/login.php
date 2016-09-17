<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		require_once('dbConnect.php');
		
		$sql = "SELECT * FROM users WHERE name = '$username' AND encrypted_password ='$password'";
		
		$result = mysqli_query($con,$sql);
		
		$check = mysqli_fetch_array($result);
		
		if(isset($check)){
			echo 'success';
		}else{
			echo 'failure';
		}
	}