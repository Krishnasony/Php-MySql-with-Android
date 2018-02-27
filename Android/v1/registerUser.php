<?php
  require_once '../include/DbOperation.php';
  $response = array();

  if($_SERVER['REQUEST_METHOD']=='POST'){

  		if(
  			isset($_POST['username'])and
  			isset($_POST['email'] )and
  			isset($_POST['password'])){

  			//code....
            $db =new DbOperation();
             $result =$db ->createUser(
                $_POST['username'],
                $_POST['password'],
                $_POST['email']);

           if($result == 1){
           	$response['error']=false;
           	$response['message']="User registered successfully";
           }elseif($result == 2){
           	$response['error']=true;
           	$response['message']="some error occur please try again";
           }
           elseif($result == 0) {
             $response['error']=true;
            $response['message']="user already exist! please choose different email and username";
           }


  		}else{
  			$response['error']=true;
  			$response['message']="Required field are missing";
  		}
  }
  else{
  	$response['error']=true;
  	$response['message']="Invalid Request";
  }
