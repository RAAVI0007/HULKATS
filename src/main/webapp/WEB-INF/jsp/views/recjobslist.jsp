<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
<div class="content-wrapper">
	<div class="container-fluid">
	<ol class="breadcrumb">
			<li class="breadcrumb-item">
         <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/recruiter/dashboard">Dashboard</a>
        </li>
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/recruiter/jobview">Job</a>
			</li>
			<li class="breadcrumb-item"><a href="">View Jobs</a></li>

		</ol>
		<!-- Error Messages -->
		<div class="form-group" style="text-align: center">
			<h5>
				<span id='success' style="color: green;" colspan="2"></span> <span
					id='error'
					style="color: red; margin-left: auto; margin-right: auto;"
					colspan="2"></span>
			</h5>
		</div>
		<!-- Error Message Ending -->
		<!-- Breadcrumbs-->
		
		<div class="row" style="overflow-x: auto;">

			<div id="table" class="col-lg-12">
				<h2>Jobs Overview</h2>
				<div id="usertable" class="table-responsive1">
					<table id="example"
						class="table table-striped table-bordered nowrap" cellspacing="0"
						width="100%">
						<thead style="background: #ff6707; color: #fff">
							<tr>
								<th class="table_heading">Job Title</th>
								<th class="table_heading">Company</th>
								<th class="table_heading">Location</th>
								<th class="table_heading">Type</th>
								<th class="table_heading">Status</th>
								<th class="table_heading" data-priority="1">Actions</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- popupmodal -->
<div class="modal fade" id="popupmodal" tabindex="-1" role="dialog"
	aria-labelledby="popupmodal" aria-hidden="true">
	<div class="modal-dialog modal-lg" " role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Action</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="X">
					<span aria-hidden="true">X</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- Error Messages -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;" colspan="2"></span> <span
							id='error1'
							style="color: red; margin-left: auto; margin-right: auto;"
							colspan="2"></span>
					</h5>
				</div>
				<!-- Error Message Ending -->
				<label>Select Action</label> <select id="action"
					class="form-control">
					<option>Select</option>
					<option value="2">Attachment</option>
					<option value="3">Add Keywords</option>
					<option value="4">Change Status</option>
					<option value="5">Find Applicants</option>
				</select> <br> <br>
				<!-- Assign Job to recruiter -->
				<div id="emails" style="display: none">
					<form>
						<label for="emails">Recruiter Name/Email</label> 
						<select id="recruiters" class="form-control" required>
							<option value="All" selected>All Recruiter</option>
						</select>

						<div class="form-group" style="text-align: center">
							<div class="col-xs-12" style="margin-top: 5px">
								<button type="button" id="reg" class="btn btn-success"
									onclick="assign()"
									style="margin-left: auto; margin-right: auto;">Assign</button>
							</div>
						</div>
					</form>
				</div>
				<!-- Assign Job to recruiter End -->

				<!--Attachments  -->
				<div id="documents" style="display: none">

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
				<!-- Attachments End -->

				<!--Add Keywords  -->
				<div id="keywords" style="display: none">
					<form id="taskregistration" name="taskregistration">
						<label for="jobdescription"><strong>Keywords</strong></label>
						<textarea rows="10" cols="50" class="form-control" id="keyword"></textarea>
						<br>
						<!-- 	<label for ="experience"><strong>Keywords</strong></label>
			<textarea rows="10" cols="50" class="form-control" id="keyword"></textarea>
		 <select id="experience"  class="form-control" >
			  <option value="">Select</option>
                                    <option value="0" >Fresher</option>
								     <option value="1">0-1</option>
                                    <option value="2" >1-5</option>
								     <option value="3">5-10</option>
								     <option value="4">10-15</option>
								     <option value="5">15-20</option>
								      <option value="6">20-25</option>
								      <option value="7">25-30</option>
								       <option value="8">30+</option>			     
			</select> -->
						<br>
						<button class="btn btn-secondary" type="button"
							onclick="insertKeywords()">Add</button>
					</form>
				</div>
				<!--Add Keywords End  -->

				<!--Status Start  -->
				<div id="status" style="display: none">
					<label>Status</label> <select id="statuslabel" class="form-control">
						<option value="">Select</option>
						<option value="OPEN">OPEN</option>
						<option value="CLOSED">CLOSED</option>
					</select>
					<div class="form-group" style="text-align: center">
						<div class="col-xs-12" style="margin-top: 5px">
							<button type="button" id="reg" class="btn btn-success"
								onclick="assignstatus()"
								style="margin-left: auto; margin-right: auto;">Submit</button>
						</div>
					</div>

				</div>
				<!--Status End  -->
				<!-- Find Applicant -->
				<div id="applicant" style="display: none">
				<div class="form-group" style="text-align: center">
						<div class="col-xs-12" style="margin-top: 5px">
							<button type="button" id="reg" class="btn btn-success"
								onclick="findapp('+data+')"
								style="margin-left: auto; margin-right: auto;">Submit</button>
						</div>
					</div>	
				</div>
				<!-- Applicant End -->
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- popupmodal -->

<%@include file="./shared/footer.jsp"%>
<script>
	var recname;
	$('#error').text('');
	var jobid;
	$('#jobdoc1').text('');
	$('#jobdoc2').text('');
	$(document).ready(function() {
						var table;
						$.ajax({
									type : "POST",
									url : "${pageContext.servletContext.contextPath}/recruiter/getJobs",
									dataType : "json",
									complete : function(data) {
										console.log(data);
										table = $('#example')
												.dataTable(
														{
															data : data.responseJSON,
															rowReorder : {
																selector : 'td:nth-child(2)'
															},
															/*  responsive: {
															      details: {
															          type: 'column',
															          target: -1
															      }
															  }, */
															colReorder : true,
															columns : [
																	{ "data": null,
										                       		 "mRender" : function ( data, type, full ) {
										                                    var  name= full['jobtitle'];
										                                    var id = full['jobid'];
										                                    return '<a  href="${pageContext.servletContext.contextPath}/user/alertjob?jobid:'+id+'" target="_blank">' +name +'</a>';
										                       		 }
																},
																	{
																		"data" : "company"
																	},
																	{
																		"data" : "location"
																	},
																	{
																		"data" : "jobtype"
																	},
																	{
																		"data" : "status"
																	},
																	{
																		"data" : "jobid",
																		"width" : "50px",
																		"render" : function(
																				data) {
																			return '<div id="controls"><a id="arrow"  onclick="popupmodal('
																					+ data
																					+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a></div>';
																		}
																	}
															/*	{"data": "jobid", "width": "50px", "render": function (data) {
																 return ' <input id="btn-view" type="submit" value="View Originals1"  onclick="orginals1('+data+')" />'
																 + ' <input id="btn-view" type="submit" value="View Originals2"   onclick="orginals2('+data+')" />'+
																' <input id="btn-view" type="submit" value="View Originals3"   onclick="orginals3('+data+')" />' +
																' <input id="btn-view" type="submit" value="View Originals4"   onclick="orginals4('+data+')" />'+
																' <input id="btn-view" type="submit" value="View Originals5"   onclick="orginals5('+data+')" />';
																   }
															 },
															 {"data": "jobid", "width": "50px", "render": function (data) {
																 return '<input id="btn-view" type="submit" value="View" data-toggle="modal" data-target="#anotherModal" onclick="openMe('+data+')" />';  }
															 },
															{"data": "jobid", "width": "50px", "render": function (data) {
																 return '<input id="btn-view" type="submit" value="Application"  onclick="findapp('+data+')" />';  }
																 }*/
															],
															responsive : true,
															columnDefs : [
																	{
																		responsivePriority : 1,
																		targets : 0
																	},
																	{
																		responsivePriority : 2,
																		targets : -1
																	} ]
														})
									}

								});

					});

	//popupmodal function
	function popupmodal(data) {
		jobid = data;
		$('#jobdoc1').text('');
		$('#jobdoc2').text('');
		$('#popupmodal').modal('show');

		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getjobKeywords",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {
						console.log(data);
						$('#keyword').val(data.responseJSON["0"].jobkeywords);
					}
				})
				$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getJobDocuments",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {

						if (data.responseJSON.uploaddocname2 == null) {
							$("#doc2").css('display',
									'none');
						} else {
							$('#jobdoc2')
									.append(
											data.responseJSON.uploaddocname2.replace(/[\d_]+/g, ''));
						}
						$("#jobdoc1")
								.append(
										data.responseJSON.uploaddocname1.replace(/[\d_]+/g, ''));

					}
				});
	}

	function addKeywords() {
		$("#keywords").css("display", "block");
		$("#emails").css("display", "none");
	}
	function addrecuiter() {
		$("#keywords").css("display", "none");
		$("#emails").css("display", "block");
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/manager/allRecruiterEmails",
					dataType : "json",

					complete : function(data) {
						console.log(data);
						for (var i = 0; i < data.responseJSON.length; i++) {

							var links = $('<option>' + data.responseJSON[i]
									+ '</option>');
							$("#email").append(links);

						}
					}
				});
	}
	$(document).ready(function() {
	 $.ajax({
      	type:"POST",
     	dataType :"json",
      	url:"${pageContext.servletContext.contextPath}/manager/getRecruiter",
    	complete: function (data) {
    		console.log(data);
     	   console.log("recruiters" +data);
     	   for(var i = 0; i<data.responseJSON.length; i++){
     		var search = $('#recruiters').append($('<option>', { 
     		        value: data.responseJSON[i].email,
     		        text : data.responseJSON[i].name 
     		    }));
     		}
     	  
    	}
       })
    
    $('#recruiters').select2({
		  placeholder: "Select Recruiters",  
   });
	});
	//Job Assign to Recruiter
/*	function assign() {
		var recuiteremail = $('#recruiters').val();
			$.ajax({
						type : "POST",
						url : "${pageContext.servletContext.contextPath}/user/JobAssigns",
						dataType : "json",
						data : {
							recuiteremail : recuiteremail,
							jobid : jobid
						},
						complete : function(data) {
							console.log(data);
							$("#keywords").css("display", "none");
							$("#emails").css("display", "none");
							$('#name').val('');
							if (data.responseText == "success") {
								$('#success').text(
										"Job is succesfully assigned to "
												+ recuiteremail);
								$('#error').text('');
								$('#popupmodal').modal('hide');
							} else if (data.responseText == "fail") {
								$('#error1')
										.text(
												'Assignment process failed! Please reassign');

							}

						}

					});
		}*/
	//Job Assign to Recruiter function ending

	function getuploads(data) {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getParserTextByJobId",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {
						console.log(data);
						$('#jobdocuments')
								.val(data.responseJSON[0].parsertext1);
						$('#jobdocuments1').val(
								data.responseJSON[0].parsertext2);
						$('#jobdocuments2').val(
								data.responseJSON[0].parsertext3);
						$('#jobdocuments3').val(
								data.responseJSON[0].parsertext4);
						$('#jobdocuments4').val(
								data.responseJSON[0].parsertext5);

					},
					error : function() {
						alert('Error');
					}
				});

	}
	function findapp(data) {
		var id = data;
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getJobAndApplicant",
					dataType : "json",
					data : {
						jobid : jobid
					},
					complete : function(data) {
						console.log(data);
						if ("success" == data.responseText) {
							//alert("Applicants find and added to Application Added Successfully!")
							window.open(
									'${pageContext.servletContext.contextPath}/user/selectapplicant?jobid:'
											+ jobid, '_blank');
						} else if ("fail" == data.responseText) {
							$('#error1').text(
									"No applicants find for this job !");

							$('#success').text('');
						} else if (data.responseJSON == "Please assign job to the recruiter !") {
							$('#error1').text(
									"Please assign job to the recruiter !");
							$('#success').text('');
						} else if (data.responseText == "Already Application is in Progress") {
							$('#error1').text(
									"Already Application is in Progress!");
							window.open(
									'${pageContext.servletContext.contextPath}/user/selectapplicant?jobid:'
											+ jobid, '_blank');
							$('#success').text('');
						}

					},
				});
	}

	function assignstatus() {
		if ($('#statuslabel option:selected').text() == 'Select'
				|| $('#statuslabel').val().trim() == 'Select') {
			$('#error1').text('Status is required!');

			$('#statuslabel').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			var status = $('#statuslabel option:selected').text();
		}

		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/jobChanageStatus",
					dataType : "json",
					data : {
						jobid : jobid,
						status : status
					},
					complete : function(data) {
						console.log(data);
						if (data.responseText == "success") {
							setTimeout(function() {// wait for 5 secs(2)
								location.reload(); // then reload the page.(3)
							}, 2000);
							$('#success').text('Status updated successfully');
							$('#popupmodal').modal('hide');
							$('#status').css('display', 'none');
						} else if (data.responseText == "fail") {
							$('#error').text('Status updated successfully');
						}
					}
				})

	}
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

	

	function insertKeywords() {
		var jobskeywords = $('#keyword').val();

		var e1 = document.getElementById("experience");
		var experience = e1.options[e1.selectedIndex].value;

		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/manager/saveJobsKeyWord",
					data : {
						jobskeyword : jobskeywords,
						jobid : jobid,
						experience : experience
					},
					dataType : "json",
					complete : function(data) {
						if (data.responseText == "success") {
							$('#success').text('Keywords Added Successfully!');
							$('#error1').text('');
							$('#popupmodal').modal('hide');
						} else if (data.responseText == "fail") {
							$('#error1').text("keywords added failed !");
							$('#success').text('');
						}

					}
				})
	}

	$('#action')
			.on(
					'change',
					function() {
						$('#error').text('');
						$('#success').text('');
						$('#jobdoc1').text('');
						$('#jobdoc2').text('');
						if (this.value == 1) {
							$('#emails').css('display', 'block');
							$('#documents').css('display', 'none');
							$('#keywords').css('display', 'none');
							$('#status').css('display', 'none');
							$('#applicant').css('display', 'none');
						} else if (this.value == 2) {
							$('#emails').css('display', 'none');
							$('#documents').css('display', 'block');
							$('#keywords').css('display', 'none');
							$('#status').css('display', 'none');
							$('#applicant').css('display', 'none');
							$
									.ajax({
										type : "POST",
										url : "${pageContext.servletContext.contextPath}/user/getJobDocuments",
										dataType : "json",
										data : {
											jobid : jobid
										},
										complete : function(data) {

											if (data.responseJSON.uploaddocname2 == null) {
												$("#doc2").css('display',
														'none');
											} else {
												$('#jobdoc2')
														.append(
																data.responseJSON.uploaddocname2.replace(/[\d_]+/g, ''));
											}
											$("#jobdoc1")
													.append(
															data.responseJSON.uploaddocname1.replace(/[\d_]+/g, ''));

										}
									});
						} else if (this.value == 3) {
							keywords
							$('#emails').css('display', 'none');
							$('#documents').css('display', 'none');
							$('#keywords').css('display', 'block');
							$('#status').css('display', 'none');
							$('#applicant').css('display', 'none');
						} else if (this.value == 4) {
							keywords
							$('#emails').css('display', 'none');
							$('#documents').css('display', 'none');
							$('#keywords').css('display', 'none');
							$('#status').css('display', 'block');
							$('#applicant').css('display', 'none');
						} else if (this.value == 5) {
							keywords
							$('#emails').css('display', 'none');
							$('#documents').css('display', 'none');
							$('#applicant').css('display', 'block');
							$('#status').css('display', 'none');
							$('#keywords').css('display', 'none');
						}

					})
</script>
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

</style>
</body>

</html>
