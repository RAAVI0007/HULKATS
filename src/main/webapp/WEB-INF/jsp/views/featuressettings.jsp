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

<link href="<c:url value='/resources/css/mCustomScrollbar.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-toggle.min.css' />"
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
					<h3 align="center">Features</h3>
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
						<div class="col-sm-6">
						<div class="panel panel-primary">
							<div class="form-group">

								<div class="input-group">
									<label for="location">Select Company</label>
									<select id="companylist" class="form-control select2-single">
										<option>Select Company</option>
									</select>
								</div>
							</div>
						</div>
					</div>
						
					<form id="registration" name="registration" style="display: none;">

						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="location">Subscription</label> <select
										name="location" id="subscription"
										class="form-control select2-single">
										<option>Select Subscription type</option>
										<option value="6">Basic</option>
										<option value="1">Enterprise</option>
									</select>
								</div>
								<div class="col-md-6">
									<label for="location" style="visibility: hidden">Subscription</label>
									<button id="reg" onclick="checkdisable();" type="button"
										class="btn btn-success" style="height: 35px;margin-top: 32px;">Change</button>
								</div>
							</div>
						</div>
						<div id="basic" style="display:none;">
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">Sourcing</label>
								</div>
								<div class="col-md-6">
									<input id="sourcing" type="checkbox" >
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">API's</label>
								</div>
								<div class="col-md-6">
									<input id="api" type="checkbox" >
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">CUSTOMIZATION Package</label>
								</div>
								<div class="col-md-6">
									<input id="package" type="checkbox" >
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">Support</label>
								</div>
								<div class="col-md-6">
									<input id="support" type="checkbox">
								</div>
							</div>
						</div>
						
						</div>
						<div id="enterprise" style="display:none;">
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">Sourcing</label>
								</div>
								<div class="col-md-6">
									<input id="sourcing" type="checkbox" >
								</div>
							</div>
						</div>

						
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="firstname">CUSTOMIZATION Package</label>
								</div>
								<div class="col-md-6">
									<input id="package" type="checkbox" >
								</div>
							</div>
						</div>
						
						</div>
						
						<div class="form-group" style="text-align: center">
							<div class="col-xs-12" style="margin-top: 5px">
								<button id="reg" onclick="checkAjaxCall();" type="button"
									class="btn btn-success" style="height: 35px">Submit</button>
							</div>
						</div>

					</form>

					<!-- Error Message Ending -->

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
		src="<c:url value='/resources/js/select2.min.js' />"></script>
	<script type="text/JavaScript"
		src="<c:url value='/resources/js/bootstrap-toggle.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
	<script
		src="<c:url value='/resources/js/jquery.mCustomScrollbar.min.js' />"></script>
	<script src="<c:url value='/resources/js/slick.js' />"></script>
	<script src="<c:url value='/resources/js/easyResponsiveTabs.js' />"></script>

	<script>
	var roleid ;
		function logout() {
			$
					.ajax({
						type : "GET",
						url : "${pageContext.servletContext.contextPath}/login/adminsessionLogout",
						dataType : "json",
						complete : function(data) {
							if (data.responseText == 'success') {
								window.location = "${pageContext.servletContext.contextPath}/";
							}
						}
					});
		}
		$(document)
				.ready(
						function() {
							$
									.ajax({
										type : "POST",
										url : "${pageContext.servletContext.contextPath}/admin/getCompanies",
										dataType : "json",
										complete : function(data) {
											console.log(data);

											for (var i = 0; i < data.responseJSON.length; i++) {
												$('#companylist')
														.append(
																$(
																		'<option>',
																		{
																			text : data.responseJSON[i].organization,
																			value : data.responseJSON[i].roleid
																		}));
											}
										}
									})
							$('#companylist').select2();
						});
		
		function checkAjaxCall(){
			var features = {};
			var sourcing = $('#sourcing').is(':checked');
			var api = $('#api').is(':checked');
			var package = $('#package').is(':checked');
			var support = $('#support').is(':checked');
			if(sourcing == true){
				features.sourcing = 1;
				
				
			}else{
				features.sourcing = 0;
				
				
			}
			if(api == true){
				features.api = 1;
							
						}else{
							
							features.api = 0;	
						}
			if(package == true){
				features.customizeduserinteface = 1;
			}else{
				
				features.customizeduserinteface = 0;
			}
			if(support == true){
				features.support = 1;
				
			}else{
				
				features.support = 0;
			}
			features.companyname = $('#companylist :selected').text();
			features.subscriptiontype = $("#subscription").val();
			
			var jsonfile={featuresInfo:JSON.stringify(features)}; 
		    console.log(jsonfile);
			$
			.ajax({
				type : "POST",
				url : "${pageContext.servletContext.contextPath}/admin/featuressettings",
				dataType : "json",
				 data: jsonfile,
				complete : function(data) {
					console.log(data);
					if(data.responseText == "success"){
						 $('#error').text('');
						 $('#error1').text('');
						 $('#success').text('Features creation success');
						// window.location.replace('/customerapp/login/success');
						$('#reg').attr("disabled", true);
					}
					else if(data.responseText == "fail"){
						 $('#error').text('Features creation failed');
						
					
					}
				}
			});
		}
		
		$('#companylist').on('change', function() {
			  $('#registration').css('display','block');
			  $("#subscription").val($('#companylist').val());
			  if($('#companylist').val() == 6){
				  
				  
			  }else{
				  
				  
			  }
			  
			 $("#subscription").prop("disabled", true);
			
		})
		
		function checkdisable(){
			$("#subscription").prop("disabled", false);
			
		}
	</script>

</body>
</html>