<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="#" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='/resources/images/favicon.png' />">
<title>HULKATS</title>

<!-- Bootstrap core CSS-->
<link
	href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="<c:url value='/resources/vendor/font-awesome/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link
	href="<c:url value='/resources/vendor/datatables/dataTables.bootstrap4.css' />"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="<c:url value='/resources/css/sb-admin.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/rowrecorder.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/responsive.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/jquery-ui.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/jquery-ui.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/select2.css' />"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/css/select2.min.css"
	rel="stylesheet" />
<link href="<c:url value='/resources/css/select2.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/sample.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/intlTelInput.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/mCustomScrollbar.css' />"
	rel="stylesheet">
<style>
.select2-container .select2-selection--single {
	height: 38px;
}

.select2-container--default .select2-selection--single .select2-selection__rendered
	{
	color: #444;
	line-height: 34px;
}

.select2-container {
	width: 100% !important;
	padding: 0;
}

.ui-autocomplete {
	z-index: 5000;
}

form .error {
	color: #ff0000;
}

#save, #save img {
	height: 5vh;
}

#save {
	opacity: 0.25;
}

#save[download] {
	opacity: 1;
}

#controls {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	height: 20%;
	width: 100%;
}

#arrow, #arrow img {
	height: 5vh;
}

#arrow[download] {
	opacity: 1;
}

<
style>.flot-chart {
	display: block;
	height: 400px;
}

label.disabled {
	color: #FFFFFF;
}

.flot-chart-content {
	width: 100%;
	height: 100%;
}

/* Custom Colored Panels */
.huge {
	font-size: 40px;
}

.panel-green {
	border-color: #5cb85c;
}

.panel-green>.panel-heading {
	border-color: #5cb85c;
	color: #fff;
	background-color: #5cb85c;
}

.panel-green>a {
	color: #5cb85c;
}

.panel-green>a:hover {
	color: #3d8b3d;
}

.panel-red {
	border-color: #d9534f;
}

.panel-red>.panel-heading {
	border-color: #d9534f;
	color: #fff;
	background-color: #d9534f;
}

.panel-red>a {
	color: #d9534f;
}

.panel-red>a:hover {
	color: #b52b27;
}

.panel-yellow {
	border-color: #f0ad4e;
}

.panel-yellow>.panel-heading {
	border-color: #f0ad4e;
	color: #fff;
	background-color: #f0ad4e;
}

.panel-yellow>a {
	color: #f0ad4e;
}

.panel-yellow>a:hover {
	color: #df8a13;
}

@
-webkit-keyframes spinnerRotate {
	from {-webkit-transform: rotate(0deg);
}

to {
	-webkit-transform: rotate(360deg);
}

}
@
-moz-keyframes spinnerRotate {
	from {-moz-transform: rotate(0deg);
}

to {
	-moz-transform: rotate(360deg);
}

}
@
-ms-keyframes spinnerRotate {
	from {-ms-transform: rotate(0deg);
}

to {
	-ms-transform: rotate(360deg);
}

}
#spin {
	display: inline;
	margin-top: -8px;
	margin-left: -14px;
	-webkit-animation-name: spinnerRotate;
	-webkit-animation-duration: 5s;
	-webkit-animation-iteration-count: infinite;
	-webkit-animation-timing-function: linear;
	-moz-animation-name: spinnerRotate;
	-moz-animation-duration: 5s;
	-moz-animation-iteration-count: infinite;
	-moz-animation-timing-function: linear;
	-ms-animation-name: spinnerRotate;
	-ms-animation-duration: 5s;
	-ms-animation-iteration-count: infinite;
	-ms-animation-timing-function: linear;
}

@media screen and (max-width: 600px) {
	#issues {
		visibility: hidden;
		clear: both;
		float: left;
		margin: 10px auto 5px 20px;
		width: 28%;
		display: none;
	}
	#copyright {
		visibility: hidden;
		clear: both;
		float: left;
		margin: 10px auto 5px 20px;
		width: 28%;
		display: none;
	}
}

@media ( min-width : 768px) {
	.navbar-top-links {
		margin-right: 0;
	}
	.navbar-top-links li {
		display: inline-block;
	}
	.navbar-top-links li:last-child {
		margin-right: 15px;
	}
	.navbar-top-links li a {
		padding: 15px;
		min-height: 50px;
	}
	.navbar-top-links .dropdown-menu li {
		display: block;
	}
	.navbar-top-links .dropdown-menu li:last-child {
		margin-right: 0;
	}
	.navbar-top-links .dropdown-menu li a {
		padding: 3px 20px;
		min-height: 0;
	}
	.navbar-top-links .dropdown-menu li a div {
		white-space: normal;
	}
}

@media ( min-width : 768px) {
	.navbar-nav .dropdown, .navbar-nav .dropdown, .navbar-nav .dropdown {
		margin-left: auto;
	}
}
/*#alertcount1, #msgcount1 {
   font-size: 15px;
    font-weight: bold;
    color: #FFF;
    background: #007bff;
    line-height: 12px;
    width: 22px;
    height: 22px;
     border-radius: 50%;
    -webkit-border-radius: 2em;
    -moz-border-radius: 2em;
    -o-border-radius: 2em;
    text-align: center;
    display: inline-block;
    position: absolute;
    top: -5px;
    right: -23px;
    padding: 4px 0 0;
     text-align: center;  
}*/
#alertcount1, #msgcount1 {
	font-style: normal;
	font-size: 11px;
	margin-right: -7px;
	margin-top: -10px;
	padding: 5px 0px;
	border-radius: 50%;
	height: 20px;
	width: 20px;
}

#msgcount1 {
	background: #007bff;
}

#alertcount1 {
	background: #ffc107;
}

@media screen and (max-width: 768px) {
	#alertcount1, #msgcount1 {
		visibility: hidden;
	}
}

@media screen and (max-width: 768px) {
	.dropdown-toggle::after {
		display: inline-block;
		width: 0;
		height: 0;
		margin-left: 46.255em;
		vertical-align: .255em;
		content: "";
		border-top: .3em solid;
		border-right: .3em solid transparent;
		border-left: .3em solid transparent;
	}
}

#down {
	float: left;
	position: relative;
	left: 80%;
}

#up {
	float: left;
	position: relative;
	left: 80%;
}
</style>

</head>

<body class="fixed-nav sticky-footer bg-light " id="page-top"
	oncontextmenu="return false;">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light"
		id="mainNav">

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a class="navbar-brand"
				href="${pageContext.servletContext.contextPath}/admin/adminregistration"><img
				src="<c:url value='/resources/images/HulkATS_logo.png'/>"
				style="width: 170px;" /></a>
		</sec:authorize>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">


				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item" data-toggle="tooltip" data-placement="right"
						title="Teams"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/adminregistration">
							<i class="fa fa-fw fa fa-user"></i> <span class="nav-link-text"><b>Registration</b></span>
					</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item" data-toggle="tooltip" data-placement="right"
						title="Teams"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/emailsettings">
							<i class="fa fa-fw fa fa-user"></i> <span class="nav-link-text"><b>Email
									Settings</b></span>
					</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item" data-toggle="tooltip" data-placement="right"
						title="Teams"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin/features">
							<i class="fa fa-fw fa fa-user"></i> <span class="nav-link-text"><b>Features
									Settings</b></span>
					</a></li>
				</sec:authorize>



			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>

			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i><b>Logout</b>
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/admin/adminregistration">Dashboard</a>
				</li>
				<li class="breadcrumb-item active">My Dashboard</li>
			</ol>
			<!-- Icon Cards-->
			<div class="card card-register mx-auto mt-5">
				<div class="card-header">
					<h3 align="center">New Account</h3>
				</div>
				<div class="card-body">

					<div id="loading"
						style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
						<p style="position: absolute; color: White; top: 50%; left: 45%;">
							<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
								id="img" />
					</div>
					<!-- Error Messages -->
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success' style="color: green;"></span> <span id='error'
								style="color: red; margin-left: auto; margin-right: auto;"></span>
						</h5>
					</div>
					<!-- Error Message Ending -->

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
							<label for="organization">Organization</label> <input
								class="form-control" type="email" name="organization"
								id="organization" placeholder="Enter Organization">
						</div>
						<div class="form-group">
							<label for="email">Email</label> <input class="form-control"
								type="email" name="email" id="email" placeholder="Enter Email">
						</div>

						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label>Mobile</label> <input type="text" class="form-control"
										class="form-control" name="phoneNumber" id="mobile" />
								</div>
								<div class="col-md-6">
									<label for="addr1">Address</label> <input class="form-control"
										type="text" name="addr1" id="addr1"
										placeholder="Enter Address" required />

								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="location">Country</label> <select name="location"
								id="location" class="form-control select2-single">
								<option>Select Country</option>
							</select>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="state">State</label> <select
										class="form-control select2-single" name="state" id="state">
										<option>Select State</option>
									</select>
								</div>
								<div class="col-md-6">
									<label for="password">City</label> <select
										class="form-control select2-single" name="city" id="city">
										<option>Select City</option>
									</select>

								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="location">Logo Image</label> <input
								class="form-control" type="file" name="logo" id="logo" required /><input
								id="response" type="hidden" class="form-control" required />

						</div>
						<div class="form-group">
						<label
						for="videourl" id="videourllabel"><strong>Video
							URL</strong></label>
						<textarea rows="6" cols="75" id="videourl"
							placeholder='<iframe width="560" height="315" src="https://www.youtube.com/embed/xI-xVTBMGvk" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'></textarea>
						</div>
						<div class="form-group">
							<label for="subscription">Subscription type</label> <select name="subscription"
								id="subscription" class="form-control select2-single">
								<option value="6">Select subscription type</option>
								<option value="6">Basic</option>
								<option value="1">Enterprise</option>
							</select>
						</div>
						<div class="form-group" style="text-align: center">
							<div class="col-xs-12" style="margin-top: 5px">
								<button id="reg" onclick="checkAjaxCall();" type="button"
									class="btn btn-success" style="height: 35px">Submit</button>
							</div>
						</div>
					</form>
					<br>
				</div>
			</div>
		</div>
	</div>
	<footer class="sticky-footer">
		<div class="container">
			<div class="text-right">
				<strong><span id='copyright'>Copyright </span>© HULKLOGIC
					2017 <span style="float: left; color: red;" id="issues">Any
						issues</span><span style="float: left; color: red;">?</span> <a
					href="${pageContext.servletContext.contextPath}/user/support"
					style="float: left; color: #ff8f00"><strong>&nbsp;HULKSupport</strong></a>
				</strong>
			</div>
		</div>
	</footer>

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="X">
						<span aria-hidden="true">&#10006</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">

					<a class="btn btn-primary" onclick="logout();">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fa fa-angle-up"></i>
	</a>



	<!-- Bootstrap core JavaScript-->
	<script type="text/JavaScript"
		src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/vendor/popper/popper.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.min.js' />"></script>
	<!-- Core plugin JavaScript-->
	<script type="text/JavaScript"
		src="<c:url value='/resources/vendor/jquery-easing/jquery.easing.min.js' />"></script>
	<!-- Page level plugin JavaScript-->

	<script type="text/JavaScript"
		src="<c:url value='/resources/js/sb-admin.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/raphael.min.js'/>"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/jquery-ui.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/loader.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/jsapi.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/Chart.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/select2.min.js' />"></script>
	<script
		src="<c:url value='/resources/vendor/datatables/jquery.dataTables.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/datatableresponsive.js' />"></script>
	<script
		src="<c:url value='/resources/vendor/datatables/dataTables.bootstrap4.js' />"></script>
	<script src="<c:url value='/resources/js/intlTelInput.min.js' />"></script>

	<script
		src="<c:url value='/resources/js/dataTables.checkboxes.min.js' />"></script>

	<script src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
	<script src="<c:url value='/resources/js/wow.min.js' />"></script>
	<script src="<c:url value='/resources/js/owl.carousel.js' />"></script>
	<script
		src="<c:url value='/resources/js/jquery.mCustomScrollbar.min.js' />"></script>
	<script src="<c:url value='/resources/js/slick.js' />"></script>
	<script src="<c:url value='/resources/js/easyResponsiveTabs.js' />"></script>
	<script src="<c:url value='/resources/js/gre.js' />"></script>
	<script>
var type1;
$(document).ready(function() {
    $('#registration')
        .find('[name="phoneNumber"]')
            .intlTelInput({
            	  utilsScript: '${pageContext.servletContext.contextPath}/resources/js/utils.js',
                  autoPlaceholder: true,
                  preferredCountries: ['us','fr', 'gb','in']
                 
               
            });
    
  
    
    $("#logo").change(function(){
	     var filesSelected = document.getElementById("logo").files;
	     if(filesSelected.length > 0){
	    var fileToLoad = filesSelected[0];
	    var fileReader = new FileReader();
	    fileReader.onload = function(fileLoadedEvent){
	    var base64value = fileLoadedEvent.target.result;
		base64 = base64value.split(',')[1];
		var type = base64value.split('/')[1];
		 type1 = type.split(';')[0];
		 
		$("#response").val(base64);
	    };
	     fileReader.readAsDataURL(fileToLoad);
	    }
	    });

});

function logout() {
	$.ajax({
		type : "GET",
		url : "${pageContext.servletContext.contextPath}/login/adminsessionLogout",
		 dataType: "json",
		complete: function(data) {
		if(data.responseText == 'success'){
			window.location = "${pageContext.servletContext.contextPath}/";
		}
		}	
	});
}




function checkAjaxCall(){
	  var user ={};
	  $('#firstname').attr('style', "");
	  $('#lastname').attr('style',"");
	  $('#email').attr('style',"");
	  $('#organization').attr('style','');
	  $('#mobile').attr('style',"");
	  $('#location').attr('style',"");
	  $('#addr1').attr('style',"");
	  
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
		if($('#addr1').val()=='' || $('#addr1').val().trim()==''){
			$('#error').text('Address is required !');
			$('#addr1').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			user.addrline1 =$('#addr1').val();
		}
		if($('#location').text()=='Select Country' || $('#location').text().trim()=='Select Country'){
			$('#error').text('Country is required !');
			$('#location').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			
		}
	if($('#state').text()=='Select State' || $('#state').text().trim()=='Select State'){
		$('#error').text('State is required !');
		$('#state').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
		return;	
	}else{
			
	}
	if($('#city').text()=='Select City' || $('#city').text().trim()=='Select City'){
		$('#error').text('City is required !');
		$('#city').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
		return;	
	}else{
				
	}
	var array = [$('#city option:selected').text(), $('#state option:selected').text(), $('#location option:selected').text()];
	
	console.log(array);
	user.city = array.join(', ');
		
		 
		 if($('#logo').val()=='' || $('#logo').val()==''){
				$('#error').text('Logo is required !');
				$('#error1').text('Logo is required !');
				$('#logo').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;	
			}else{
				user.logoencoded =$('#response').val();		
			}
		 if($('#subscription').text()=='Select subscription type' || $('#subscription').text().trim()=='Select subscription type'){
				$('#error').text('Subscription is required !');
				$('#subscription').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;	
			}else{
				user.roleid = $('#subscription option:selected').val()		
			}
		
		 var  path =$('#logo').val();
			var filename = path.match(/[^\\/]*$/)[0];
			var res = filename.split(".");
			user.logoencoded =$('#response').val();	
			user.logoname = res[0];
			user.logotype=type1;
			user.videourl = $('textarea#videourl').val();
		
    $('#error').text('');
    $('#error1').text('');

	var jsonfile={customerInfo:JSON.stringify(user)}; 
    console.log(jsonfile);
$('#loading').show(); 

$.ajax({
 url:"${pageContext.servletContext.contextPath}/admin/customerregistration",
  type: 'POST',
  dataType: "json",
  data: jsonfile,
  complete:function(response){
  	 $('#loading').hide(); 
		console.log(response);
		if(response.responseText == "success"){
			 $('#error').text('');
			 $('#error1').text('');
			 $('#success').text('Account Creation Success');
			// window.location.replace('/customerapp/login/success');
			$('#reg').attr("disabled", true);
		}
		else if(response.responseText == "fail"){
			 $('#error').text('Account Creation Failed');
			
		
		}
		else if(response.responseJSON.status == 'EMAIL_EXISTED'){
			$('#error').text('Email is already registered! Please choose another one');
			 $('#error1').text('Email is already registered! Please choose another one');
			 $('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			 
		}
		else if(response.responseJSON.status == 'PHONE_NUMBER_EXISTED'){
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
function statecity(data){
	  $("#location").empty();
  	  $.ajax({
  	       url: "${pageContext.servletContext.contextPath}/user/getAllCountries",
  	        type: 'POST',
  	        dataType: "json",
  	        data: {phoneCode:data},
  	        complete:function(response){
  	        	console.log(response);
  	        	 for (var i = 0; i < response.responseJSON.length; i++)
  	   	         { 
  	   	              $('#location').append($('<option>',
  	   	              {
  	   	                 value: response.responseJSON[i].id,
  	   	                 text : response.responseJSON[i].name
  	   	             }));
  	   	         }
  	        	 getcities();
  	        }
  	      
  	        })
    }

function getcities(){
	var countryid = $('#location').val();
	
	 $("#state").empty();
	  $.ajax({
  	       url: "${pageContext.servletContext.contextPath}/user/getStates",
  	        type: 'POST',
  	        dataType: "json",
  	        data: {countryId:countryid},
  	        complete:function(response){
  	        	console.log(response);
  	        	 for (var i = 0; i < response.responseJSON.length; i++)
  	   	         { 
  	   	              $('#state').append($('<option>',
  	   	              {
  	   	                 value: response.responseJSON[i].id,
  	   	                 text : response.responseJSON[i].statename
  	   	             }));
  	   	         }
  	        }
	  })
}

$("#location").on("change", function(){
	 getcities();
})

$('#state').on('change', function() {
	 var stateid = $('#state').val();
	$("#city").empty();
	  $.ajax({
 	       url: "${pageContext.servletContext.contextPath}/user/getAllCities",
 	        type: 'POST',
 	        dataType: "json",
 	        data: {state_id:stateid},
 	        complete:function(response){
 	        	console.log(response);
 	        	 for (var i = 0; i < response.responseJSON.length; i++)
 	   	         { 
 	   	              $('#city').append($('<option>',
 	   	              {
 	   	                 value: response.responseJSON[i].id,
 	   	                 text : response.responseJSON[i].citiesname
 	   	             }));
 	   	         }
 	        }
	  })
})

 $('#city').select2();
$('#state').select2();



var telInput = $("#mobile");



telInput.blur(function() {
var countrydata = telInput.intlTelInput("getSelectedCountryData");
	statecity(countrydata.dialCode);
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
	
</script>
</body>
</html>