<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='/resources/images/favicon.png' />">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>HULKATS</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


<link href="<c:url value='/resources/css/styles.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/simple-line-icons.css' />"
	rel="stylesheet">
	<style>
	.hulklogic {
   
    font-size: 18px;
    font-weight: bold;
}
	
	</style>
</head>
<body>
	<div class="page-wrapper flex-row align-items-center">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="card p-4">
						<div
							style="display: table-cell; vertical-align: middle; text-align: center">
							<img src="<c:url value='/resources/images/HulkATS_logo.png'/>"
								style="width: 200px;" />
						</div>
						<div
							class="card-header text-center text-uppercase h4 font-weight-light">
							Forgot Password</div>
							 <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" ></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" ></span>
       		<b><span id="jobid"  style="color:green;" ></span></b>
       		
       		</h5>
       </div>
       <!-- Error Message Ending -->
						
							<div class="card-body py-5">
								<div class="form-group">
									<label class="form-control-label">Email</label>  
									<input type="text" id="inputEmail" class="form-control" placeholder="john@gmail.com" required autofocus>
								</div>
								<div class="row">
									<div class="col-6">
									 <button id="submit" onclick="getotp()" class="btn btn-primary px-5" type="button" >Submit</button>
										
									</div>

									<div class="col-6">
										<a
											href="${pageContext.servletContext.contextPath}"
											class="btn btn-link">Login</a>
									</div>
								</div>
							</div>
							
						
					</div>
					<div class="hulklogic text-center text-uppercase">
							<a href="https://www.hulklogic.com/" target="_blank">HULKLOGIC TECHNOLOGIES</a></div>
							<div class="externalLinks text-center" >
			<a href="" target="_blank" style="margin-right: 10px; text-decoration: none;">
				<span class="text-12 color-lightgrey">Help</span>
			</a>
			<a href="" target="_blank" style="margin-right: 10px; text-decoration: none;">
				<span class="text-12 color-lightgrey">Privacy</span>
			</a>
			<a href="" target="_blank" style="text-decoration: none;">
				<span class="text-12 color-lightgrey">Terms</span>
			</a>
		</div>
					
				</div>
			</div>
		</div>
		
	</div>
	<script src="<c:url value='/resources/js/jquery.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/demo.js' />"
		type="text/javascript"></script>
	



	<script>
	var email;
			function getotp(){
				 
		if($('#inputEmail').val() == ''|| $('#inputEmail').val().trim()==""){
			$('#error').text('Enter Email id !');
			return;
		}else{
		 email = $('#inputEmail').val();
		}
		  $('#error').text('');
	      	
	      	
	    $('#loading').show();   
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/login/forgetPwd",
            data:"mailId="+email,
            dataType :"json",
            complete: function(result) {
            	console.log(result);
            	 $('#loading').hide(); 
				if("success"==result.responseText){
				$('#success').text('Password reset link has been sent successfully!')
				$('#submit').attr("disabled", true);
			}else if("fail"==result.responseText){
				
				$('#error').text("Please Enter Registered Email!");
			}else{
				
				$('#error').text("Email is not registered!");
			}
        }
             
			  
		
});
		}
		
		
	
	
	
	</script>
	
</body>
</html>