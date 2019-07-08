<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Agent Dashboard</title>
        <script type="text/JavaScript" src="<c:url value='/resources/vendor/popper/popper.js' />"></script>
      <script type="text/JavaScript" src="<c:url value='/resources/vendor/popper/popper.min.js' />"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.min.js' />"></script>
     <script type="text/JavaScript" src="<c:url value='/resources/js/bootstrap-show-password.js' />"></script>
      <script type="text/JavaScript" src="<c:url value='/resources/js/bootstrap-show-password.min.js' />"></script>

      <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery-easing/jquery.easing.min.js' />"></script>
    <!-- Page level plugin JavaScript-->
   
    <script type="text/JavaScript" src="<c:url value='/resources/js/sb-admin.min.js' />"></script>
 
  <!-- Bootstrap core CSS-->
  <link href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="<c:url value='/resources/vendor/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="<c:url value='/resources/vendor/datatables/dataTables.bootstrap4.css' />" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="<c:url value='/resources/css/sb-admin.css' />" rel="stylesheet">
</head>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.html">Hulk Logic</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="${pageContext.servletContext.contextPath}/agent/getDetails">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Demo">
          <a class="nav-link" href="${pageContext.servletContext.contextPath}/agent/demo">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Demo</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Report Issue">
          <a class="nav-link"  href="${pageContext.servletContext.contextPath}/agent/reportIssue">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Report Issue Page</span>
          </a>
          
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Task in Backlog">
          <a class="nav-link"  href="${pageContext.servletContext.contextPath}/agent/taskinBacklog">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Task In Backlog</span>
          </a>
          
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Visit Update">
          <a class="nav-link"  href="${pageContext.servletContext.contextPath}/agent/visitupdate">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Visit Update/ Status</span>
          </a>
         
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Invite Agent">
          <a class="nav-link" href="${pageContext.servletContext.contextPath}/agent/inviteAgent">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Invite Another Agent</span>
          </a>
        </li>
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="My Payment ">
          <a class="nav-link" href="${pageContext.servletContext.contextPath}/agent/payment">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">My Payments & Registration</span>
          </a>
        </li>
      </ul>
	  
	  
	  
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
	  
      <ul class="navbar-nav ml-auto"> 
		<li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-user"></i>
          </a>	
		  <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            
            <a class="dropdown-item" href="">
            Profile
            </a>
			<a class="dropdown-item" href="${pageContext.servletContext.contextPath}/agent/changePwd">
              Change Password
            </a>
		</li>		  
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">
    <div class="container-fluid">
	<div class="card card-register mx-auto mt-5">
      <div class="card-header"><h2 align="center">Change Password</h2></div>
      <div class="card-body">
	<form action="" name="forgotpassword">

				<label>Enter Current Password</label>
                <input type="text" id="currentpwd" class="form-control" placeholder="Enter Current Password" required autofocus>
				  
				 <label>New Password </label>
				<input name="password" id="password" class="form-control" type="password" placeholder="Enter New Password"  data-toggle="password">


<label>Confirm password</label>
  <input type="password" name="confirm_password" id="confirm_password" class="form-control"   placeholder="Re-enter New Password"/> 
  <span id='message'></span>
<br>
                  <button type="button" onclick="changePassword();">Submit</button>
  </form>
</div>
</div>
</div>
</div>
	<script>
	
	
	
	$('#password, #confirm_password').on('keyup', function () {
		  if ($('#password').val() == $('#confirm_password').val()) {
		    $('#message').html('Matching').css('color', 'green');
		  } else 
		    $('#message').html('Not Matching').css('color', 'red');
		});
	function changePassword(){
		var currentpwd = $('#currentpwd').val();
		var newpwd = $('#password').val();
		alert(currentpwd);
		alert(newpwd);
		$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/updatePassword",
          data:"oldPassword="+currentpwd + "&newPassword="+newpwd,
            dataType :"json",
            complete: function(result) {
				console.log(result);
				if("success" == result.responseJSON.status)
					{
					alert(result.responseJSON.comment);
					}
				else("fail"== result.responseJSON.status)
				{
					alert(result.responseJSON.comment);
				}
		
	}
		});
	}
	
</script>

        </body>
</html>