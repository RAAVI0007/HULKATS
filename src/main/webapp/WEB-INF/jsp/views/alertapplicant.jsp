<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<sec:authorize access="hasRole('ROLE_MANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/manager/managerdashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_RECRUITER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
			</sec:authorize>

			<li class="breadcrumb-item"><a
				>Applicant</a>
			</li>

			<li class="breadcrumb-item"><a href="">View</a></li>

		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center"> Applicant ID :<span id="applicantid"></span></h2>
			</div>
			<div class="card-body">
				<form id="taskregistration" name="taskregistration">
					<label for="firstname"><strong>First Name</strong></label> <input
						class="form-control" type="text" name="firstname" id="firstname"
						placeholder="" required> <br> <label for="lastname"><strong>Last
							Name</strong></label> <input class="form-control" type="text" name="lastname"
						id="lastname" placeholder="" required> <br> <label
						for="sellermobile"><strong>Mobile</strong></label> <input
						class="form-control" type="text" name="mobile" id="mobile"
						pattern="[789][0-9]{9}" placeholder="98xxxxxxxx"> <br>
					<label for="email"><strong>Email</strong></label> <input
						class="form-control" type="text" name="email" id="email"
						placeholder="johndoe@gmail.com"> <br> <label
						for="aadress"><strong>City & State</strong></label> <input
						class="form-control" id="location" type="text" name="address"
						placeholder="Enter Address" /> <br> <label
						for="exampleQualification"><strong>Qualification</strong></label>
					<select id="qual" name="qual" class="form-control" required>
						<option value="">Select</option>
						<option value="bca">High School</option>
						<option value="bcom">College</option>
						<option value="bsc">Graduate School</option>
						<option value="ba">Professional</option>
					</select> <br> <label for="lastjob"><strong>Last Job
							Title</strong></label> <input class="form-control" id="lastjob" type="text"
						name="address" placeholder="Enter Current Job Role" /> <br>
					<div id="documents">
			<div id="doc1">
    		 <i class="fa fa-fw fa fa-file-text-o"></i>
			<a id="appdoc1" target="iframe_a" onclick="getoriginals()"></a>
			</div>
			<br>
			<div id="doc2">
			  <i class="fa fa-fw fa fa-file-text-o"></i>
			<a id="appdoc2" target="iframe_a" onclick="getoriginals()"></a>
			</div>
			</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>


			</div>

		</div>
	</div>
</div>


<!--   footer -->
<%@include file="./shared/footer.jsp"%>

<script>
var x = window.location.href;	
var applicantid =x.substring(x.lastIndexOf(':') + 1);
$('#applicantid').text(" "+applicantid)
$(document).ready(function(){

	 $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/user/alertapplicantdetails",
        dataType: "json",
        data:{applicantid :applicantid},
        complete: function(data) {
        	console.log(data);
        	 $('input[type="text"], date, select').prop("disabled", true); 
        	 $('#firstname').val(data.responseJSON.firstname);
    		 $('#lastname').val(data.responseJSON.lastname);
    		 $('#mobile').val(data.responseJSON.contactno);
    		 $('#email').val(data.responseJSON.email);
    		 $('#location').val(data.responseJSON.location);
    		
    		 $('#lastjob').val(data.responseJSON.lastjobtitle);
    		  $(function() {
    			    $('[name=qual] option').filter(function() { 
    			        return ($(this).text() == data.responseJSON.highesteducation); //To select Qualification
    			    }).prop('selected', true);
    			    if (data.responseJSON.otheruploads == null) {
    					$("#doc2").css('display',
    							'none');
    				} else {
    					$('#appdoc2').append(data.responseJSON.otheruploads.replace(/[\d_]+/g, ''));
    				}
    	        	 $('#appdoc1').append(data.responseJSON.uplaodcv.replace(/[\d_]+/g, ''));
        })
        }
	 })
});
function getoriginals(){
	 $.ajax({
	 type: "POST",
       url: "${pageContext.servletContext.contextPath}/user/cvUploadDocument",
       dataType: "json",
       data:{applicantid:applicantid},
       complete: function(data) {
           console.log(data);
           var voterimage = "data:application/pdf;base64,";
           voterimage += data.responseJSON.uplaodcv;
var win = window.open();
win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');


       }
      
});
}
function getoriginals1(){
	$.ajax({
		 type: "POST",
	        url: "${pageContext.servletContext.contextPath}/user/cvOtherUploadDocument",
	        dataType: "json",
	        data:{applicantid:applicantid},
	        complete: function(data) {
	            console.log(data);
	            var otheruploadsimg = "data:application/pdf;base64,";
	           
               voterimage += data.responseJSON.uplaodcv;
	            otheruploadsimg += data.responseJSON.otheruploads;
				var win = window.open();
				 win.document.write('<iframe src="' + otheruploadsimg + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

       }
	})
}

</script>
