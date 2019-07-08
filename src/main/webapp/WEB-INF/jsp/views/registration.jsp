<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='/resources/images/favicon.png' />">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>HULKATS</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<link href="<c:url value='/resources/css/styles.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/simple-line-icons.css' />	"
	rel="stylesheet">
	<link href="<c:url value='/resources/css/intlTelInput.css' />"
	rel="stylesheet">
	<link href="<c:url value='/resources/vendor/font-awesome/css/font-awesome.css' />"
	rel="stylesheet">
<style>
.hulklogic {
	font-size: 18px;
	font-weight: bold;
}

 .intl-tel-input.allow-dropdown input, .intl-tel-input.allow-dropdown input[type=text], .intl-tel-input.allow-dropdown input[type=tel], .intl-tel-input.separate-dial-code input, .intl-tel-input.separate-dial-code input[type=text], .intl-tel-input.separate-dial-code input[type=tel] {
    padding-right: 200px;
    padding-left: 47px;
    margin-left: 0; }
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
							<b>REGISTRATION</b>
						</div>
						<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
						<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;"></span> <span id='error'
							style="color: red; margin-left: auto; margin-right: auto;"></span>
					</h5>
				</div>
						<form id="registration" name="registration">
						<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="firstname">First Name</label> <input
									class="form-control" type="text" name="firstname"
									id="firstname" placeholder="Enter First Name">
							</div>
							<div class="col-md-6">
								<label for="lastname">Last Name</label> <input
									class="form-control" type="text" name="lastname" id="lastname"
									placeholder="Enter Last Name">
							</div>
						</div>
					</div>
							<div class="form-group">
								<label for="firstname">Organization Name</label> <input
									class="form-control" type="text" name="firstname"
									id="organization" placeholder="Enter Organization Name">
							</div>
							<div class="form-group">
								<label for="email">Email</label> <input class="form-control"
									type="email" name="email" id="email" placeholder="Enter Email">
							</div>
							<div class="form-group">
									<label for="password">Password</label> <input
										title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"
										type="password" id="password" class="form-control" required
										pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="pwd1"
										onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
  if(this.checkValidity()) form.pwd2.pattern = RegExp.escape(this.value);
">
								
							</div>
							<div class="form-group">
								<label>Contact Number</label> <input type="text"
									class="form-control" name="phoneNumber"
									id="mobile" />
							</div>
							<br>
						
							
							  <p>By clicking Register, you agree on our <a href="#">terms and condition</a>.</p>

						
							<div class="card-footer">
								<div class="row">
									<div class="col-sm-6">
									<button type="button" class="btn btn-primary px-5" onclick="checkAjaxCall();">Register</button>
										
									</div>

									<div class="col-sm-6">
										<a
											href="${pageContext.servletContext.contextPath}/login/login"
											class="btn btn-link">Login?</a>
									</div>
								</div>
							</div>
						</form>

					</div>
					<div class="hulklogic text-center text-uppercase">
						<a href="https://www.hulklogic.com/" target="_blank">HULKLOGIC
							TECHNOLOGIES</a>
					</div>
					<div class="externalLinks text-center">
						<a href="" target="_blank"
							style="margin-right: 10px; text-decoration: none;"> <span
							class="text-12 color-lightgrey">Help</span>
						</a> <a href="" target="_blank"
							style="margin-right: 10px; text-decoration: none;"> <span
							class="text-12 color-lightgrey">Privacy</span>
						</a> <a href="" target="_blank" style="text-decoration: none;"> <span
							class="text-12 color-lightgrey">Terms</span>
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
		<script src="<c:url value='/resources/js/intlTelInput.min.js' />" type="text/javascript"></script>

<script>
$(document).ready(function() {
	 $('#firstname').val("Veerendra "); //firstname
     $('#lastname').val("Basappa"); //lastname
     $("#mobile").val( "+19722007848"); //contact number
    $('#organization').val("AAB Insurance Group "); 
     $('#email').val('vbasappa@allstate.com')
	 
	 
    $('#registration')
        .find('[name="phoneNumber"]')
            .intlTelInput({
                utilsScript: '${pageContext.servletContext.contextPath}/resources/js/utils.js',
                autoPlaceholder: true,
                preferredCountries: ['us','fr', 'gb','in']
               
            });
});


var telInput = $("#mobile");
        telInput.blur(function() {
	var countrydata = telInput.intlTelInput("getSelectedCountryData");
	//phonecode =countrydata.dialCode;
	  if ($.trim(telInput.val())) {
	    if (telInput.intlTelInput("isValidNumber")) {
	    	var number =  $('input[name="phoneNumber"]').val();
	    	
	    	var classf = $(".selected-flag > div").attr("class");
	    	var flag = classf.slice(-2);
	    	console.log(number);
	    	console.log(classf);
	    	console.log(flag);
	    	 var formattedNumber = intlTelInputUtils.formatNumber(number, flag, intlTelInputUtils.numberFormat.INTERNATIONAL);
	    	 
	    	$('input[name="phoneNumber"]').val(formattedNumber);
	    	 $('#mobile').attr('style','');
	     
	    } else {
	    	 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
	    }
	  }
	});

function checkAjaxCall(){
	  var user ={};
	  $('#firstname').attr('style', "");
	  $('#lastname').attr('style',"");
	  $('#email').attr('style',"");
	  $('#password').attr('style',"");
	  $('#organization').attr('style','');
	  $('#mobile').attr('style',"");
	  
	  $('#success').text('');
	  $('#error1').text('');
	  $('#error').text('');
	  if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
			$('#error').text('First Name is required !');
			$('#error1').text('First Name is required !');
			$('#firstname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			user.firstname =$('#firstname').val();		
		}
	  if($('#lastname').val()=='' || $('#lastname').val().trim()=='' ){
		$('#error').text('Last Name is required !');
		$('#error1').text('Last Name is required !');
		$('#lastname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
		return;	
	}else{
		user.lastname =$('#lastname').val();		
	}
	  if($('#email').val()=='' || $('#email').val().trim()=='' ){
			$('#error').text('Email is required !');
			$('#error1').text('Email is required !');
			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
	  }
			else if(!validateEmail($('#email').val())){
				$('#error').text('Invalid Email');
  			$('#error1').text('Invalid Email');
  			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");	
			return
		}else{
			user.email =$('#email').val();		
		}
	  if($('#password').val()=='' || $('#password').val().trim()==''){
			$('#error').text('Password is required !');
			$('#error1').text('Password is required !');
			$('#password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			user.password =$('#password').val();		
		}
	  if($('#organization').val()=='' || $('#organization').val().trim()==''){
			$('#error').text('Organization is required !');
			$('#error1').text('Organization is required !');
			$('#organization').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			user.organization =$('#organization').val();		
		}
		
		if($('#mobile').val()=='' || $('#mobile').val().trim()==''){
			$('#error').text('Mobile Number is required !');
			$('#error1').text('Mobile Number is required !');
			$('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			var telInput = $("#mobile");
			 if ($.trim(telInput.val())) {
				    if (telInput.intlTelInput("isValidNumber")) {
				    	
				    	 $('#mobile').attr('style','');
				     
				    } else {
				    	 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				    	 return;
				    }
				  }
			user.contactno =$('#mobile').val();	
		}
    $('#error').text('');
    $('#error1').text('');

	var jsonfile={customerInfo:JSON.stringify(user)}; 
    console.log(jsonfile);
$('#loading').show();   
$.ajax({
 url: "${pageContext.servletContext.contextPath}/login/customerregistration",
  type: 'POST',
  dataType: "json",
  data: jsonfile,
  complete:function(response){
  	 $('#loading').hide(); 
		console.log(response);
		if(response.responseText == "success"){
			 $('#error').text('');
			 $('#error1').text('');
			
			 window.location.replace('${pageContext.servletContext.contextPath}/login/success');
			$('#reg').attr("disabled", true);
		}
		else if(response.responseText == "fail"){
			 $('#error').text('Account Creation Failed');
			
		
		}
		else if(response.responseText == 'Email is already registered! Please choose another one'){
			$('#error').text('Email is already registered! Please choose another one');
			 $('#error1').text('Email is already registered! Please choose another one');
			 $('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			 
		}
		else if(response.responseText == 'Mobile Number is registered! Please choose another one'){
			$('#error').text('Mobile Number is registered! Please choose another one');
			 $('#error1').text('Mobile Number is registered! Please choose another one');
			 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
		}
		
  }
});

return false;
}

function validateEmail(email) {
	  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	  return emailReg.test( email );
	}
</script>

</body>
</html>
