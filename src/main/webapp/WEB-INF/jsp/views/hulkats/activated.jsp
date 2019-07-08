<!--  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<label style="text-align: left; font-size: 22px; color: #ff6707;">${activationDone}</label>
<a href="${pageContext.servletContext.contextPath}/login/login">Click here login</a>
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<c:url value='/resources/assets/img/favicon.png' />" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>HULKSMASH</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


<link href="<c:url value='/resources/css/styles.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/simple-line-icons.css' />	"
rel="stylesheet">
	<style>
	.hulklogic {
   
    font-size: 18px;
    font-weight: bold;
}
	
	</style>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
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
							<b>Activate</b></div>
     				<div class="card-body py-5">
								<div class="form-group">
									<label style="text-align: left; font-size: 22px; color: green;">${activationDone}</label>
								</div>
								<div class="row">
									<div class="col-6">
								
										
									</div>

									<div class="col-6">
										<a href="${pageContext.servletContext.contextPath}/">Click here to login</a>
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

</body>
</html>

