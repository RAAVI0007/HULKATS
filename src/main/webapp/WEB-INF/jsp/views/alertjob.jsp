
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
				href="">Job</a>
			</li>
			<li class="breadcrumb-item"><a href="">Job View</a></li>

		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center"> Job ID : <span id="jobid"> </span></h2>
			</div>
			<div class="card-body">

				<!-- Loading -->
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<!-- Loading Ends -->

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<!-- Error Messages -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;" colspan="2"></span> <span
							id='error'
							style="color: red; margin-left: auto; margin-right: auto;"
							></span> <b><span id="jobid" style="color: green;"
							></span></b>

					</h5>
				</div>
				<!-- Error Message Ending -->

				<form id="jobregistration" name="jobregistration">
					<label for="jobtitle"><strong>Job Title</strong></label>
					 <input class="form-control" type="text" name="jobtitle" id="jobtitle" placeholder="" required readonly> <br> <br> 
					 <label	for="companyname"><strong>Company</strong></label> 
					 <input	class="form-control" type="text" name="companyname" id="companyname" placeholder="" required readonly> <br> 
					 <label	for="Location"><strong>City & State</strong></label>
					  <input class="form-control" type="text" name="location" id="location" placeholder="" required readonly> <br>
						 <label for="jobrole"><strong>Keywords</strong>[Optional]</label>
						  <input class="form-control" type="text" name="jobrole" id="jobrole" placeholder="HTML5,CSS3" required readonly> <br>
					<div class="formRow">
						<label for="eStatus"><strong>Employment Type</strong></label> <select
							id="emptype" class="form-control">
							<option>Select Type</option>
							<option>Part Time</option>
							<option>Full Time</option>
						</select>
						<!--   <p class="w260">
                            <input type="radio" class="chkRadio" id="ftime" name="employmentStatus_F"   value="Full Time" > <label for="ftime">Full Time</label> &nbsp; &nbsp; 
                            <input type="radio" class="chkRadio" id="ptime" name="employmentStatus_F"  value="Part Time" > <label for="ptime">Part Time</label>   </p>-->
					</div>
					<br>
					<div class="formRow">
						<strong>Salary</strong><a name="dja"></a>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">

								<input type="text" id="salary"
									class="form-control salary-text form-control" placeholder="$" readonly>
							</div>
							<div class="col-md-6">
								<select id="salarycriteria"
									class="form-control salary-duration form-control">
									<option value="Select">Select</option>
									<option value="YEARLY">per year</option>
									<option value="MONTHLY">per month</option>
									<option value="WEEKLY">per week</option>
									<option value="DAILY">per day</option>
									<option value="HOURLY">per hour</option>
								</select>
							</div>
						</div>
					</div>
					<br>
					<label for="eStatus"><strong>Job
							Description</strong></label>
					<div class="grid-full">
						<textarea id="udescription2" name="udescription2" class="gre"
							rows="50" cols="100"></textarea>
					</div>
					<br> <label for="eStatus"><strong>Job
							Requirement</strong></label>

					<div class="grid-full">
						<textarea id="udescription" name="udescription" class="gre"
							rows="50" cols="50"></textarea>
					</div>
					<div id="documents">

					<label><strong>Job Documents</strong></label> <br>
					<div id="doc1">
						<i class="fa fa-fw fa fa-file-text-o"></i> <a id="jobdoc1"
							target="iframe_a" onclick="orginals1()"></a>
					</div>

					<div id=doc2>
						<i class="fa fa-fw fa fa-file-text-o"></i> <a id="jobdoc2"
							target="iframe_a" onclick="orginals2()"></a>
					</div>
				</div>
					
					
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<!--   footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>
</div>

<script>
var x = window.location.href;	
var jobid =x.substring(x.lastIndexOf(':') + 1);
$('#jobid').text(" "+jobid);
$(document).ready(function(){

	 $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/user/alertjobdetails",
        dataType: "json",
        data:{jobid:jobid},
        complete: function(data) {
        	console.log("HELLO");
        	console.log(data);
        	
        	$('#jobtitle').val( data.responseJSON.jobtitle);
			$('#companyname').val( data.responseJSON.company);
			$('#location').val( data.responseJSON.location);
			$('#salary').val( data.responseJSON.salary);
			$('#salarycriteria').val( data.responseJSON.salarycriteria);
			$('#emptype').val( data.responseJSON.jobtype);
			$("#salarycriteria").attr('readonly', 'readonly');
			$("#emptype").attr('readonly', 'readonly');
			
			$("iframe#udescription2").contents().find('body').html(data.responseJSON.jobdescription);
			$("iframe#udescription").contents().find('body').html(data.responseJSON.jobrequirement);
			if (data.responseJSON.uploaddocument2 == null) {
				$("#doc2").css('display',
						'none');
			} else {
				$('#jobdoc2')
						.append(
								data.responseJSON.uploaddocument2.replace(/[\d_]+/g, ''));
			}
			$("#jobdoc1")
					.append(
							data.responseJSON.uploaddocument1.replace(/[\d_]+/g, ''));
			
        }
	 })
})
	 
function orginals1(data) {
		var id = data;
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/sendJobsDocuments",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseJSON.uploaddocument1;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}
				})
	}
	function orginals2(data) {
		var id = data;
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/sendJobsDocuments",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseJSON.uploaddocument2;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}
				})
	}



</script>