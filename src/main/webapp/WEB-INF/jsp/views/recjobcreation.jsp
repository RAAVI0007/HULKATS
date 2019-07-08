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
  
  <!-- Bootstrap core CSS-->
  <link href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="<c:url value='/resources/vendor/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="<c:url value='/resources/vendor/datatables/dataTables.bootstrap4.css' />" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="<c:url value='/resources/css/sb-admin.css' />" rel="stylesheet">
  <link href="<c:url value='/resources/css/jquery-ui.css' />" rel="stylesheet">
   <link href="<c:url value='/resources/css/jquery-ui.min.css' />" rel="stylesheet">
  
  <style>
  
  form .error {
  color: #ff0000;
}
      #save, #save img { height: 5vh; }
	#save { opacity: 0.25;}
	#save[download] { opacity: 1;}
      #controls {
		display: flex;
		flex-direction: row;
		
		justify-content: space-around;
		height: 20%;
		width: 100%;
	}
	
	
  </style>
  
  
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/manager/dashBord">Hulk Logic</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="${pageContext.servletContext.contextPath}/recruiter/dashboard">
		   
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Registartion">
         <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa fa-user"></i>
            <span class="nav-link-text">Registration</span>
          </a>
		   <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="${pageContext.servletContext.contextPath}/recruiter/registration">Create Account</a>
            </li>
            <li>
		    <a href="${pageContext.servletContext.contextPath}/recruiter/recruiters">View Details</a>
            </li>
          </ul>
          </li>
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Job">
           <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents2" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Job</span>
          </a>
		  <ul class="sidenav-second-level collapse" id="collapseComponents2">
            <li>
              <a href="${pageContext.servletContext.contextPath}/recruiter/jobcreation">Add New</a>
            </li>
            <li>
			 
              <a href="${pageContext.servletContext.contextPath}/recruiter/joblist">List</a>
            </li>
          </ul>
		  
        </li>
		
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Applicant">
         <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents3" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa fa-user"></i>
            <span class="nav-link-text">Applicant</span>
          </a>
		  <ul class="sidenav-second-level collapse" id="collapseComponents3">
            <li>
              <a href="${pageContext.servletContext.contextPath}/recruiter/applicants">Registration</a>
            </li>
            <li>
			 
              <a href="${pageContext.servletContext.contextPath}/recruiter/applicantslist">List</a>
            </li>
          </ul>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Application">
         <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents4" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa fa-file-text-o"></i>
            <span class="nav-link-text">Application</span>
          </a>
		  <ul class="sidenav-second-level collapse" id="collapseComponents4">
            <li>
              <a href="${pageContext.servletContext.contextPath}/recruiter/applications">Overview</a>
            </li>
            
          </ul>
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
			<span class="d-lg-none">Account Details
            
            </span>
          </a>	
		  <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            
            <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/editdetails">
            Profile
            </a>
			<a class="dropdown-item" href="${pageContext.servletContext.contextPath}/changePassword">
              Change Password
            </a>
            </div>
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
      <!-- Breadcrumbs-->
	  <div class="card card-register mx-auto mt-5">
      <div class="card-header"><h2 align="center">Add and Publish Job</h2></div>
      <div class="card-body">
      <div id="loading" style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;  display:none;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
        <img src="<c:url value='/resources/images/ajax-loader.gif'/>" id="img" / >
       </div> 
   
  <form id="jobregistration" name="jobregistration">
	<label for="jobtitle"><strong>Job Title</strong></label>
    <input class="form-control"  name="jobtitle" id="jobtitle" placeholder="" required>
    <br>
    <br>
	<label for="companyname"><strong>Company</strong></label>
    <input class="form-control"  name="companyname" id="companyname" placeholder="" required>
	<br>
	<label for="Location"><strong>City & State</strong></label>
    <input class="form-control"  name="location" id="location" placeholder="" required>
	<br>
	<div class="formRow"><strong>Desired Job Details</strong><a name="dja"></a></div>
	<br>
	<label for="jobrole"><strong>Job Role</strong></label>
    <input class="form-control"  name="jobrole" id="jobrole" placeholder="" required>
	<br>
							<div class="formRow">
                            <label for="eStatus">Employment Type:</label>
                            <p class="w260">
                            <input type="radio" class="chkRadio" id="ftime" name="employmentStatus_F"   value="Full Time"> <label for="ftime">Full Time</label> &nbsp; &nbsp; 
                            <input type="radio" class="chkRadio" id="ptime" name="employmentStatus_F"  value="Part Time" > <label for="ptime">Part Time</label>   </p>
                        </div>
						<div class="formRow"><strong>Salary Details</strong><a name="dja"></a></div>
						<div class="form-group">
					<div class="form-row">
					<div class="col-md-6">
					
					 <input type="text" id="salary" class="form-control salary-text form-control" placeholder="$">
					 </div>
			  <div class="col-md-6">
			
                <select type="select" id="salarycriteria" class="form-control salary-duration form-control">
					 <option selected="" value="YEARLY" >per year</option>
					 <option value="MONTHLY">per month</option>
					 <option value="WEEKLY" >per week</option>
					 <option value="DAILY" >per day</option>
					 <option value="HOURLY" >per hour</option>
					 </select>
              </div>
            </div>
			</div>
			<label for="jobdescription"><strong>Job Description</strong></label>
			<textarea rows="15" cols="100" id="jobdescription" name="jobdescription" maxlength="4000" class="form-control" placeholder=" About Job Description ..." required></textarea>
				<span id="characters"></span>
				 
				<br>
				<label for="cvupload"><strong>Upload Document</strong></label>
				<input id="cvupload" type="file" class="form-control"  name="cvupload" required/>
				<input id="response" type="hidden" class="form-control"  required />
      <br>
				<div id="controls">
		       
		              <a id="save" onclick="upload();"><img src="<c:url value='/resources/images/add_2x.png' />" >Add</a>
	               </div>
               <div id="checkotp" name="other" style="display:none">
                  <label for="cvupload"><strong>Upload Other Document</strong></label>
				<input id="cvupload1" type="file" class="form-control" name="cvupload" required/>
				<input id="response1" type="hidden" class="form-control"  required />
				<br>
				<div id="controls1">
		       
		              <a id="save" onclick="upload1();"><img src="<c:url value='/resources/images/add_2x.png' />" >Add</a>
	               </div>
            </div>
			<div id="checkotp1" name="other" style="display:none">
                  <label for="cvupload"><strong>Upload Other Document</strong></label>
				<input id="cvupload2" type="file" class="form-control" name="cvupload" />
				<input id="response2" type="hidden" class="form-control"  required />
				<br>
				<div id="controls2">
		       
		              <a id="save" onclick="upload2();"><img src="<c:url value='/resources/images/add_2x.png' />" >Add</a>
	               </div>
            </div>
				
				
				<div id="checkotp2" name="other" style="display:none">
                  <label for="cvupload"><strong>Upload Other Document</strong></label>
				<input id="cvupload3" type="file" class="form-control" name="cvupload" required/>
				<input id="response3" type="hidden" class="form-control"  required />
				<br>
				<div id="controls3">
		       
		              <a id="save" onclick="upload3();"><img src="<c:url value='/resources/images/add_2x.png' />" >Add</a>
	               </div>
            </div>
			<div id="checkotp3" name="other" style="display:none">
                  <label for="cvupload"><strong>Upload Other Document</strong></label>
				<input id="cvupload4" type="file" class="form-control" name="cvupload" />
				<input id="response4" type="hidden" class="form-control"  required />
				<br>
				</div>
				<button type="button" id="reg" onclick="checkAjaxCall();" >Register</button>
                 	 
  </form>

   
        </div>
        
      </div>
	  
	  
   
     <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Hulk Logic 2017</small>
        </div>
      </div>
    </footer>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">Close</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
			<a href="${pageContext.servletContext.contextPath}/login/logout" class="btn btn-primary" onclick="logout();">Logout</a>
          </div>
        </div>
      </div>
    </div>
	</div>
	</div>
    
    <!-- Bootstrap core JavaScript-->
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/popper/popper.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.min.js' />"></script>
    <!-- Core plugin JavaScript-->
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery-easing/jquery.easing.min.js' />"></script>
    <!-- Page level plugin JavaScript-->
    <script type="text/JavaScript" src="<c:url value='/resources/js/jquery.validate.js' />"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/js/sb-admin.min.js' />"></script>
      <script type="text/JavaScript" src="<c:url value='/resources/js/jquery-ui.js' />"></script>
       <script type="text/JavaScript" src="<c:url value='/resources/js/jobvalidation.js' />"></script>
      
    <!-- Custom scripts for this page-->
	  
<script>
	
    function logout() {
		
		$.ajax({
			type : "GET",
			url : "/j_spring_security_logout",
		});
	}
	$('textarea').keyup(updateCount);
	$('textarea').keydown(updateCount);

function updateCount() {
    var cs =  [4000- $(this).val().length];
    
	if(cs == 0){
		 $('#characters').text("Reached Limit");
	}
	else{
		$('#characters').text(cs);
	}
	}
    function upload(){
          $("#controls").css("display", "none");
         $("#checkotp").css("display", "block");
           
    }
	function upload1(){
          $("#controls1").css("display", "none");
         $("#checkotp1").css("display", "block");
           
    }
	function upload2(){
          $("#controls2").css("display", "none");
         $("#checkotp2").css("display", "block");
           
    }
	function upload3(){
          $("#controls3").css("display", "none");
         $("#checkotp3").css("display", "block");
           
    }


    </script>
   <script>
   var type1;
	var type2;
	var type3;
	var type4;
	var type5;
       $(document).ready(function () {
 
  $("#issueregistration").validate({

    rules: {
      clientname:"required",
      issuename: "required",
      isassignedfrom:"required",
      isassignedto:"required",
      agentemail: {
        required: true,
        email: true
      },
      issuemessage:"required",
	  autocomplete:"required"
      
    },
  
    messages: {
	  clientname:"Please enter Client Name",
      issuename: "Please enter issuename",
      isassignedfrom: "Please enter  AssignedFrom",
      isassignedto:"Please enter IsAssignedTo",
      agentemail: "Please enter a valid email address",
	  issuemessage:"Please enter the Issue Message",
	  autocomplete:"Please enter the Pincode"
    },
    onfocusout: function(element) {
            this.element(element);
        },
   
    submitHandler: function(form) {
      form.submit();
    }
  });
 return false;   
     
   
   
});
     
        $(document).ready(function(){
        	 $("#response").val("undefined");
        	 $("#response1").val("undefined");
        	 $("#response2").val("undefined");
        	 $("#response3").val("undefined");
        	 $("#response4").val("undefined");
        	
   $("#cvupload").change(function(){
     var filesSelected = document.getElementById("cvupload").files;
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
 
   $("#cvupload1").change(function(){
     var filesSelected = document.getElementById("cvupload1").files;
     if(filesSelected.length > 0){
    var fileToLoad = filesSelected[0];
    var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent){
    var base64value = fileLoadedEvent.target.result;
	base64 = base64value.split(',')[1];
	var type = base64value.split('/')[1];
	 type2 = type.split(';')[0];
	
	 $("#response1").val(base64);
    };
     fileReader.readAsDataURL(fileToLoad);
    }
     else{
    	 $("#response1").val("undefined"); 
    	 
     }
    });
    $("#cvupload2").change(function(){
     var filesSelected = document.getElementById("cvupload2").files;
     if(filesSelected.length > 0){
    var fileToLoad = filesSelected[0];
    var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent){
    var base64value = fileLoadedEvent.target.result;
	base64 = base64value.split(',')[1];
	var type = base64value.split('/')[1];
	 type3 = type.split(';')[0];
	$("#response2").val(base64);
    };
     fileReader.readAsDataURL(fileToLoad);
    }
     else{
    	 $("#response2").val("undefined"); 
    	 
     }
    });
   $("#cvupload3").change(function(){
     var filesSelected = document.getElementById("cvupload3").files;
     if(filesSelected.length > 0){
    var fileToLoad = filesSelected[0];
    var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent){
    var base64value = fileLoadedEvent.target.result;
	base64 = base64value.split(',')[1];
	var type = base64value.split('/')[1];
	 type4 = type.split(';')[0];
	$("#response3").val(base64);
    };
     fileReader.readAsDataURL(fileToLoad);
    }
     else{
    	 $("#response3").val("undefined"); 
    	 
     }
    });
   $("#cvupload4").change(function(){
     var filesSelected = document.getElementById("cvupload4").files;
     if(filesSelected.length > 0){
    var fileToLoad = filesSelected[0];
    var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent){
    var base64value = fileLoadedEvent.target.result;
	base64 = base64value.split(',')[1];
	var type = base64value.split('/')[1];
	 type5 = type.split(';')[0];
	$("#response4").val(base64);
    };
     fileReader.readAsDataURL(fileToLoad);
    }
     else{
    	 $("#response4").val("undefined"); 
    	 alert('hi');
    	 
     }
    });
    
    });

		function checkAjaxCall(){
			 $('#loading').show();
		var jobtitle = $('#jobtitle').val(); 
		var jobrole = $('#jobrole').val();
		var companyname = $('#companyname').val();
		var email = $('#email').val();
        var location = $('#location').val();
		var	salary = $('#salary').val();
		var	jobdescription = $('#jobdescription').val();
		var	jobtype = $('input[name=employmentStatus_F]:checked').val();
        var e = document.getElementById("salarycriteria");
        
        var salarycriteria = e.options[e.selectedIndex].text;
          var uploaddoc = $('#response').val();
          var uploaddoc1 = $('#response1').val();
         
          var uploaddoc2 = $('#response2').val();
          var uploaddoc3 = $('#response3').val();
          var uploaddoc4 = $('#response4').val();
         var json = {"jobtitle":jobtitle,"company":companyname,"jobtype":jobtype,"location":location,"jobrole":jobrole,"jobdescription":jobdescription,"salarycriteria":salarycriteria,"document1":uploaddoc,"document2":uploaddoc1,"document3":uploaddoc2,"document4":uploaddoc3,"document5":uploaddoc4
        		 ,"salary":salary,"filetype1":type1, "filetype2":type2,"filetype3":type3,"filetype4":type4,"filetype5":type5}
		console.log(type1);
         console.log(json);
		  $.ajax({
        url: "${pageContext.servletContext.contextPath}/manager/jobcreation",
        type: 'POST',
        dataType: "json",
        data: {jobcreation:JSON.stringify(json)},
        complete:function(response){
        	 $('#loading').hide(); 
			console.log(response);
			if("success"==response.responseText){
				alert("Job Registration succesfully!")
			}else if("fail"==response.responseText){
				alert("Job Registration failed !");
			}
			
        }
    });
	
    return false;
		
	}
		 $("#location").keyup(function(){
	     	 var location = $("#location").val();
	     	 $("#location").autocomplete({
	     		 minLength :2,
	     		 source:function(request,response){
	     			 if(location.length > 1){
	     				 $.ajax({
	     					 type:"POST",
	     					 data:{city:location},
	     					 dataType: "json",
	     					 url:"${pageContext.servletContext.contextPath}/manager/getCities",
	     					 complete:function(data){
	     						 var values = [];
	     						 console.log(data);
	     						 for(var i=0;i<data.responseJSON.length;i++){
	     							 values.push(data.responseJSON[i].city_state)
	     						 }
	     						 response(values);

	     					 }
	     				 
	     				 })
	     				 
	     			 }
	     			 
	     		 }
	     		 
	     		 
	     	 })
	     	 
	     	 
	     	 
	      })
    </script>
   
</body>

</html>