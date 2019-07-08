<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="../#" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>HULKATS</title>

<!-- Bootstrap core CSS-->
<link
	href="../vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="../vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->

<!-- Custom styles for this template-->
<link href="../css/sb-admin.css"
	rel="stylesheet">
<link href="../css/rowrecorder.css"
	rel="stylesheet">
<link href="../css/responsive.css"
	rel="stylesheet">
<link href="../css/jquery-ui.css"
	rel="stylesheet">
<link href="../css/jquery-ui.min.css"
	rel="stylesheet">
<link href="../css/select2.css"
	rel="stylesheet">
<link href="../css/select2.min.css"
	rel="stylesheet">
<link href="../css/sample.css"
	rel="stylesheet">
<link href="../css/navigation.css"
	rel="stylesheet">

	<link href="../css/intlTelInput.css"
	rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-light" id="page-top">
	<div id="header"></div>
<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			
			<li class="breadcrumb-item active">Dashboard</li>
		</ol>
		<!-- Icon Cards-->

		<br>
	
            <div class="row">
            
			<div id="table" class="col-md-3">
			<label></label>
				<select id="days" name="days" class="form-control" required>
					
					<option value="7">Last 7 Days</option>
					<option value="30">Last 30 Days</option>
					<option value="3" selected>Till Now</option>
				</select>
			</div>
			
			<div id="table" class="col-md-6">
			<label></label>
				<select id="recruiters" class="form-control" required>
					<option value="All" selected>All Recruiter</option>
				</select>
		
			</div>
	
		</div>
		<br>
		<div class="row">
			  <div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-danger o-hidden h-100">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw  fa-group"></i>
						</div>
						<div class="mr-5 ">
						<font size="6"><b>
							<span id="recruiter"></span></b></font>
							<div><strong>Live/Total!</strong></div> 
						</div>

					</div>
					<a class="card-footer text-white clearfix small z-1"
						href="../${pageContext.servletContext.contextPath}/manager/users">
						<span class="float-left"><h5>Recruiters</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>
				</div>
			</div> 
			 
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-primary o-hidden h-100">
				 	<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa fa-file-text"></i>
						</div>
						<div class="col-xs-3">
						<font size="6"><b><span
									id="open"></span></b></font>
							<div><strong>Open/Total!</strong></div> 
						</div>
					</div>
					
					<a class="card-footer text-white clearfix small z-1"
						href="../${pageContext.servletContext.contextPath}/manager/joblist">
						<span class="float-left"><h5>Jobs</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>

				</div>
			</div>  
			
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-warning o-hidden h-100">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa fa-group"></i>
						</div>
						<div class="mr-5">
							 <font size="6"><b><span
									id="applicant"></span></b></font>
									<div><strong>Hired/Target!</strong></div>
						</div>
					</div>
					<a class="card-footer text-white clearfix small z-1"
						href="../${pageContext.servletContext.contextPath}/user/applicantslist">
						<span class="float-left"><h5>Applicants</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>
				</div>
			</div>
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-success o-hidden h-100">
					 <div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa fa-files-o"></i>
						</div>
						<div class="mr-5 ">
							<font size="6"><b><span
									id="application"></span></b></font>
									<div><strong>In-Process/New!</strong></div>
						</div>
					</div>
					<a class="card-footer text-white clearfix small z-1"
						href="../${pageContext.servletContext.contextPath}/manager/applications">
						<span class="float-left"><h5>Applications</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>
				</div>
			</div>

		</div>
		<br> <br>

		<div class="row">
			<div class="col-lg-6">
				<h5> Status Analytics</h5>

				<div id="donutchart" style="width: 500px; height: 300px;"></div>
				<div id="cnt" class="overlay" style="width:399px;height:42px;"></div>


			</div>
			<div class="col-lg-6">
				<h5>Source Analytics</h5>

				<div id="donutchartsrc" style="width: 500px; height: 300px;"></div>
				<div id="cntsrc" class="overlay" style="width:399px;height:42px;"></div>
				
			</div>
		</div>

		<div class="row" id="performance">
			<div id="tablerec" class="col-lg-6" >
				<h5>
					Recruiters
					<i id="up" class="fa fa fa-thumbs-up" style="color: #1e731e;font-size:2em;"></i>
				</h5>
				<br/>
				<div id="usertable">
					<table id="example1"
						class="table table-striped table-bordered nowrap" cellspacing="0"
						width="100%">
						<thead style="background-color: #1e731e; color: #fff">

							<tr>
								<th class="table_heading">Name</th>
								<th class="table_heading">Hired</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div id="tablerec1" class="col-lg-6">
				<h5>
					Recruiters
					<i id="down" class="fa fa-thumbs-down fa-6" aria-hidden="true"
						style="color: #c32c3b;font-size:2em"></i>
				</h5>
					<br/>
				<div id="usertable">
					<table id="example2"
						class="table table-striped table-bordered nowrap" cellspacing="0"
						width="100%">
						<thead style="color: #fff; background-color: #c32c3b;">
							<tr>
								<th class="table_heading">Name</th>
								<th class="table_heading">Hired</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Terms & Conditions</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
      <div class="modal-body">
	<div class="form-group" style="text-align: center">
			<h5>
				<span id='success' style="color: green;" ></span> <span
					id='error'
					style="color: red; margin-left: auto; margin-right: auto;"
					></span> <b><span id="jobid" style="color: green;"
					></span></b>

			</h5>
		</div>
        <input type="checkbox" name="checkbox" value="check" id="agree" /> I have read and agree to the Terms and Conditions and Privacy Policy.
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-success" onclick="terms();">Submit</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
<div id="alertlogin" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Login Alert</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
      <div class="modal-body">
      	<h5>You have successfully logged in through this device and have been logged out from another recently used device.</h5>
         </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-success" onclick="alertlogin();">Ok</button>
      </div>
    </div>

  </div>
</div>
		<!--   footer -->
		
	</div>
</div>

<div id="footer"></div>



<!-- Bootstrap core JavaScript-->
<script type="text/JavaScript"
	src="../vendor/jquery/jquery.min.js"></script>
<script type="text/JavaScript"
	src="../vendor/popper/popper.min.js"></script>
<script type="text/JavaScript"
	src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Core plugin JavaScript-->
<script type="text/JavaScript"
	src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Page level plugin JavaScript-->

<script type="text/JavaScript"
	src="../js/sb-admin.min.js"></script>
<script type="text/JavaScript"
	src="../js/raphael.min.js"></script>
	<script type="text/JavaScript"
	src="../js/select2.min.js"></script>
<script type="text/JavaScript"
	src="../js/jquery-ui.js"></script>
<script src="../js/intlTelInput.min.js"></script>
<script src="../tempjs/registration.js"></script>
<script src="../tempjs/config.js"></script>
<script type="text/JavaScript"
	src="../js/loader.js"></script>
	<script
	src="../vendor/datatables/jquery.dataTables.min.js"></script>
<script
	src="../vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="../js/dataTables.checkboxes.min.js"></script>
<script type="text/JavaScript"
	src="../js/jsapi.js"></script>
    <script type="text/JavaScript"
	src="../tempjs/admindashboard.js"></script>
	<script type="text/JavaScript"
	src="../js/bootstrap-notify.js"></script>
<script>
		$(function(){
			$("#header").load("nav.html"); 
			$("#footer").load("footer.html"); 
		});
	</script>



<style>
.overlay {
	display: block;
	width:399px;
	height:42px;
	text-align: center;
	vertical-align: middle;
	position: absolute;
	top: 0px; /* chartArea top  */
	left: 0px; /* chartArea left */
	font-size: 30px;
	margin-top: 160px;
}


.fa fa-thumbs-down fa-6 {
    font-size: 50em;
}

</style>

</body>

</html>
