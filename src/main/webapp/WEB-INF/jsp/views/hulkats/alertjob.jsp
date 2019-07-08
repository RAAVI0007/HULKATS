
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
			<sec:authorize access="hasRole('ROLE_BASICMANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/basicmanager/dashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<li class="breadcrumb-item"><a href="">Job</a></li>
			<li class="breadcrumb-item"><a href="">Job View</a></li>

		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center">
					Job ID : <span id="jobid"> </span>
				</h2>
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
							colspan="2"></span> <b><span id="jobid" style="color: green;"
							colspan="2"></span></b>

					</h5>
				</div>
				<!-- Error Message Ending -->

				<form id="jobregistration" name="jobregistration">
					<label for="jobtitle"><strong>Job Title</strong></label> <input
						class="form-control" type="text" name="jobtitle" id="jobtitle"
						placeholder="" required> <br> <br> <label
						for="companyname"><strong>Company</strong></label> <input
						class="form-control" type="text" name="companyname"
						id="companyname" placeholder="" required> <br> <label
						for="department"><strong>Department</strong></label> <input
						class="form-control" type="text" name="department" id="department"
						placeholder=""> <br>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="country"><strong>Country</strong></label> <select
									name="location" id="location"
									class="form-control select2-single">
									<option>Select Country</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="Location"><strong>State</strong></label> <select
									name="state" id="state" class="form-control select2-single">
									<option>Select State</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="Location"><strong>City</strong></label> <select
									name="city" id="city" class="form-control select2-single">
									<option>Select City</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="eStatus"><strong>Employment Type</strong></label> <select
									id="emptype" class="form-control">
									<option>Select Type</option>
									<option>Part Time</option>
									<option>Full Time</option>
									<option>Contract</option>
									<option>Internship</option>
								</select>
							</div>

						</div>
					</div>
					<br>

					<div class="formRow">
						<strong>Salary</strong> <a></a>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">

								<input type="text" id="salary"
									class="form-control salary-text form-control" placeholder="$">
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
					<br> <label for="jobrole"><strong>Job
							Keywords</strong></label>
					<textarea class="form-control" name="jobrole" id="jobrole"
						placeholder="HTML5,CSS3,JAVA,JQUERY" rows="5" cols="50"
						maxlength="200"></textarea>
					<span style="margin-left: 0px; color: green;">*Please give
						comma seperated </span> <br> <br> <label for="eStatus"><strong>Job
							Description</strong></label>
					<div class="grid-full">
						<textarea id="udescription2" name="udescription2" class="gre"
							cols="100" maxlength="50"></textarea>
					</div>
					<br> <label for="eStatus"><strong>Job
							Requirement</strong></label>

					<div class="grid-full">
						<textarea id="udescription" name="udescription" class="gre"
							cols="50" maxlength="50"></textarea>
					</div>
					<div id="videocontent" style="display: none">
						<label for="companyname"><strong>Upload Video
								Description</strong></label> <input id="videoupload" type="file"
							class="form-control" name="video" required accept="video/mp4"
							onchange="GetFileSize();" /> <input id="response3" type="hidden"
							class="form-control" required /> <span
							style="margin-left: 0px; color: red;" id="fp"></span> <label
							for="videourl" id="videourllabel"><strong>Video
								URL</strong></label>
						<textarea rows="6" cols="75" id="videourl"
							placeholder='<iframe width="560" height="315" src="https://www.youtube.com/embed/xI-xVTBMGvk" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'></textarea>
					</div>
					<br>
					<div id="videocont">

						<label><strong>Upload Video Description</strong></label> <br>
						<div id="video">
							<input type="checkbox" id="myCheckbox" name="myCheckbox5">
							<i class="fa fa-fw fa fa-file-text-o"></i> <span id="videodoc"
								onclick="videoname()"></span>
						</div>

						<div id=video12>
							<input type="checkbox" id="myCheckbox4" name="myCheckbox1">
							</i> <span id="videourldoc" onclick="youtube()">Youtube Link</span>

						</div>
					</div>

					<div id="documents" style="display: none">

						<label><strong>Job Documents</strong></label> <br>
						<div id="doc1">
							<input type="checkbox" id="myCheckbox" name="myCheckbox">
							<i class="fa fa-fw fa fa-file-text-o"></i> <span id="jobdoc1"
								onclick="orginals1()"></span> <a id="cross"
								onclick='firstdocdel();'>&#x274C;</a>
						</div>

						<div id=doc2>
							<input type="checkbox" id="myCheckbox1" name="myCheckbox1">
							<i class="fa fa-fw fa fa-file-text-o"></i> <span id="jobdoc2"
								onclick="orginals2()"></span> <a id="cross"
								onclick='seconddocdel();'>&#x274C;</a>
						</div>
					</div>
					<div id="upload">
						<div id="jobup">
							<label for="cvupload"><strong>Upload Job
									Description</strong></label> <input id="cvupload" type="file"
								class="form-control" name="cvupload" required
								onchange="GetFileSize1();" /> <input id="response"
								type="hidden" class="form-control" required /> <span
								style="margin-left: 0px; color: red;" id="fp1"></span> <span
								style="margin-left: 0px; color: green;">*Please Upload
								extension .docx or .pdf </span> <br>
						</div>
						<div id="controls">
							<a id="save" onclick="upload();"><img
								src="<c:url value='/resources/images/add_2x.png' />"></a>
						</div>
						<div id="checkotp" style="display: none">
							<label for="cvupload"><strong>Upload Other
									Document</strong></label> <input id="cvupload1" type="file" class="form-control"
								name="cvupload" required onchange="GetFileSize2();" /> <input
								id="response1" type="hidden" class="form-control" required /> <br>
							<span style="margin-left: 0px; color: red;" id="fp2"></span>

						</div>
					</div>

					<!-- Error Messages -->
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success1' style="color: green;"></span> <span
								id='error1'
								style="color: red; margin-left: auto; margin-right: auto;"></span>
						</h5>
					</div>
					<!-- Error Message Ending -->


					<div class="form-group" style="text-align: center">
						<div class="col-xs-12" style="margin-top: 5px">

							<button type="button" class="btn btn-success" id="register"
								onclick="checkedit();"
								style="margin-left: auto; margin-right: auto;">Edit</button>
							<button type="button" class="btn btn-success" id="update"
								onclick="updatejob();"
								style="display: none; margin-left: auto; margin-right: auto;">Submit</button>
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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-body" id="youtubevideomodal">
				<video width="80%" height="5%" id="jobvideodata" id="bVideo"
					controls="controls" muted="true"
					class="img-responsive center-block">
					<source type="video/mp4" autostart="false">
				</video>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
	var x = window.location.href;
	var jobid = x.substring(x.lastIndexOf(':') + 1);
	$('#jobid').text(" " + jobid);
	var image = 'data:video/mp4;base64,';
	 var country;
     var state ;
     var city ;
     var flag1 = false;
	 var flag2 = false;
	 var flag3 = false;
	 var type1;
	 var type2;
	 var type3;
	 var youtubeframe;
	
	function upload(){
        $("#controls").css("display", "none");
       $("#checkotp").css("display", "block");
         
  }
	function upload1(){
        $("#controls1").css("display", "none");
       $("#checkotp1").css("display", "block");   
  }
	
	function youtube(){
		$('#jobvideodata').css('display','none');
		$("#myModal").modal("show");
	}
	function videoname(){
		$("#myModal").modal("show");
			$('#youtubelink').css('display','none');
			$("#jobvideodata").attr("src", image);
			
			}

	$(document)
			.ready(
					function() {
						
						 $('input[type="text"], date, select,file').prop("disabled", true);
						 $('#myCheckbox1').attr('disabled', true);
						 $('#myCheckbox').attr('disabled', true);
						 $('#upload').css('display','none');
						 $('#documents').css('display','block');
						 
						$('.gre')
								.gre(
										{
											content_css_url : '<c:url value='/resources/css/gre.css' />',
											height : 250
										});
						$
								.ajax({
									type : "POST",
									url : "${pageContext.servletContext.contextPath}/user/alertjobdetails",
									dataType : "json",
									data : {
										jobid : jobid
									},
									complete : function(data) {
										console.log(data);
										
										$('#jobtitle').val(
												data.responseJSON.jobtitle);
										$('#companyname').val(
												data.responseJSON.company);
										$('#location').val(
												data.responseJSON.location);
										$('#salary').val(
												data.responseJSON.salary);
										$('#salarycriteria')
												.val(
														data.responseJSON.salarycriteria);
										$('#emptype').val(
												data.responseJSON.jobtype);
										$("#salarycriteria").attr('readonly',
												'readonly');
										$("#emptype").attr('readonly',
												'readonly');
										$("#department").val(data.responseJSON.department)
										$("#jobrole").val(data.responseJSON.jobtags)
										$("iframe#udescription2")
												.contents()
												.find('body')
												.html(
														data.responseJSON.jobdescription);
										$("iframe#udescription")
												.contents()
												.find('body')
												.html(
														data.responseJSON.jobrequirement);
										 var array = data.responseJSON.location;
									      var res = array.split(",");
									      console.log(res);
									      console.log(res[2]);
									      country = res[2];
									      state = res[1];
									      city = res[0];
									      getdatas();
										$("#videodoc").append(
												data.responseJSON.videoname);
										image  += data.responseJSON.videoencoded;
										if(data.responseJSON.videourl ==""){
											$("#video12").css("display","none");
										}else{
											youtubeframe =data.responseJSON.videourl;
										$("#youtubevideomodal").append(data.responseJSON.videourl);
										}
										if (data.responseJSON.uploaddocument2 == null) {
											$("#doc2").css('display', 'none');
										} else {
											$('#jobdoc2')
													.append(
															data.responseJSON.uploaddocument2
																	.replace(
																			/[\d_]+/g,
																			''));
										}
										$("#jobdoc1")
												.append(
														data.responseJSON.uploaddocument1
																.replace(
																		/[\d_]+/g,
																		''));

									}
								})
								 $("#response").val("undefined");
							        	 $("#response1").val("undefined");
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
													
													$("#videoupload").change(function(){
											     var filesSelected = document.getElementById("videoupload").files;
											     if(filesSelected.length > 0){
											    var fileToLoad = filesSelected[0];
											    var fileReader = new FileReader();
											    fileReader.onload = function(fileLoadedEvent){
											    var base64value = fileLoadedEvent.target.result;
												base64 = base64value.split(',')[1];
												var type = base64value.split('/')[1];
												 type3 = type.split(';')[0];
												 
												$("#response3").val(base64);
											    };
											     fileReader.readAsDataURL(fileToLoad);
											    }
											    });
					})
					
		 								function updatejob(){
			
														var joblist = {};
														$('#jobtitle').attr('style', "");
														$('#companyname').attr('style', "");
														$('#location').attr('style', "");
														$('#salary').attr('style', "");
														$('#salarycriteria').attr('style', "");
														$('#department').attr('style',"");
														$('#emptype').attr('style', "");
														$('#cvupload').attr('style', "");

														if ($('#jobtitle').val() == '' || $('#jobtitle').val().trim() == '') {
															$('#error').text('Job Title is required !');
															$('#error1').text('Job Title is required !');
															$('#jobtitle').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															joblist.jobtitle = $('#jobtitle').val();
														}

														if ($('#companyname').val() == ''
																|| $('#companyname').val().trim() == '') {
															$('#error').text('Company Name is required !');
															$('#error1').text('Company Name is required !');
															$('#companyname').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															joblist.company = $('#companyname').val();

														}
														if ($('#department').val() == ''
															|| $('#department').val().trim() == '') {
														$('#error').text('Department is required !');
														$('#error1').text('Department is required !');
														$('#department').attr('style',
																"border-radius: 5px; border:#FF0000 1px solid;");
														return;
													} else {
														joblist.department = $('#department').val();

													}
														if ($('#location option:selected').text() == 'Select Country'
															|| $('#location option:selected').text().trim() == 'Select Country') {
														$('#error').text('Country  is required !');
														$('#error1').text('Country is required !');

														$('#location').attr('style',
																"border-radius: 5px; border:#FF0000 1px solid;");
														return;
													} else {
													}
													if ($('#state option:selected').text() == 'Select State'
															|| $('#state option:selected').text().trim() == 'Select State') {
														$('#error').text('State is required !');
														$('#state').attr('style',
																"border-radius: 5px; border:#FF0000 1px solid;");
														return;
													} else {

													}
													if ($('#city option:selected').text() == 'Select City'
															|| $('#city option:selected').text().trim() == 'Select City') {
														$('#error').text('City is required !');
														$('#city').attr('style',
																"border-radius: 5px; border:#FF0000 1px solid;");
														return;
													} else {

													}

													var array = [ $('#city option:selected').text(),
															$('#state option:selected').text(),
															$('#location option:selected').text() ];

													console.log(array);
													joblist.location = array.join(', ');

														

														if ($('#emptype option:selected').text() == 'Select Type') {
															$('#error').text('Employment Type is required !');
															$('#error1').text('Employment Type is required !');
															$('#jobtitle').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															joblist.jobtype = $('#emptype option:selected').text();
														}

														if ($('#salary').val() == '' || $('#salary').val().trim() == '') {
															$('#error').text('Salary is required !');
															$('#error1').text('Salary is required !');
															$('#salary').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															joblist.salary = $('#salary').val();
														}
														if ($("#salarycriteria option:selected").text() == 'Select') {
															$('#error').text('Salary Criteria is required !');
															$('#error1').text('Salary Criteria is required !');
															$('#salarycriteria').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															joblist.salarycriteria = $("#salarycriteria option:selected").val();
														}
														
														if ($('textarea#jobrole').val() == '' || $('textarea#jobrole').val().trim() =="") {
															$('#error').text('Job keywords is required!');
															$('#error1').text('Job keywords is required!');
															$('#jobrole').attr('style',
																	"border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
														   joblist.jobtags = $('#jobrole').val();
														}
														
														if ($("iframe#udescription2").contents().find('body').html() == ''
																|| $("iframe#udescription2").contents().find('body').html() == '') {
															$('#error').text('Job Description is required !');
															$('#error1').text('Job Description is required !');
															$("iframe#udescription2").contents().find('body').html().attr(
																	'style', "border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															var content = $("iframe#udescription2").contents().find('body')
																	.html();
															joblist.jobdescription = content;
														}
														if ($("iframe#udescription").contents().find('body').html() == ''
																|| $("iframe#udescription").contents().find('body').html() == '') {
															$('#error').text('Job Requirement is required !');
															$('#error1').text('Job Requirement is required !');
															$("iframe#udescription").contents().find('body').html().attr(
																	'style', "border-radius: 5px; border:#FF0000 1px solid;");
															return;
														} else {
															var content = $("iframe#udescription").contents().find('body')
																	.html();
															joblist.jobrequirement = content;
														}
														/*	if($('#videoupload').val()=='' || $('#videoupload').val()=='' || size > 2){
																$('#error').text('Video Upload is required !');
																$('#error1').text('Video Upload  is required !');
																//$('#videourl').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
																$('#videoupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
																return;	
															}else{
																joblist.videoupload =$('#response3').val();	
																				
															}*/
														joblist.videoupload = $('#response3').val();
														joblist.videourl = $('#videourl').val();

														if ($('#myCheckbox').is(':checked')) {

														} else {
															/* if($('#cvupload').val()=='' || $('#cvupload').val().trim()==''){
																	$('#error').text('Upload is required !');
																	$('#cvupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
																	return;	
																}else{
																		
																}*/
															var path = $('#cvupload').val();
															var filename = path.match(/[^\\/]*$/)[0];
															var res = filename.split(".");
															joblist.docname1 = res[0];
															joblist.document1 = $('#response').val();
															joblist.filetype1 = type1;
														}

														if ($('#myCheckbox1').is(':checked')) {

														} else {
															var path1 = $('#cvupload1').val();
															var filename1 = path1.match(/[^\\/]*$/)[0];
															var res1 = filename1.split(".");
															joblist.docname2 = res1[0];
															joblist.document2 = $('#response1').val();
															joblist.filetype2 = type2;
														}
														joblist.jobid = jobid;
														var jsonfile = {
															jobcreation : JSON.stringify(joblist)
														};
														console.log(jsonfile);
														$('#loading').show();
														$
																.ajax({
																	url : "${pageContext.servletContext.contextPath}/user/updateJobDetails",
																	type : 'POST',
																	dataType : "json",
																	data : jsonfile,
																	complete : function(response) {
																		$('#loading').hide();
																		console.log(response);
																		if ("success" == response.responseText) {
																			$('#reg').attr("disabled", true);
																			$("#reg").css("display", "none");
																			$("#update").css("display", "none");
																			$('#register').css("display", "block");
																			$('input[type="text"], date, select,file').prop(
																					"disabled", true);
																			$('#myCheckbox1').attr('disabled', true);
																			$('#myCheckbox').attr('disabled', true);
																			$('#upload').css('display', 'none');
																			$("#cvupload1").val('');
																			$("#cvupload").val('');
																			$("#response1").val('');
																			$('#response').val('');
																			jobid = $('#jobid').text().replace(/^\D+/g, "");
																			documents(jobid);
																			$('#documents').css('display', 'block');
																			$('#success').text('Job is updated succesfully!');
																			$('#success1').text('Job is updated succesfully!');
																			$('#error').text('');
																			$('#error1').text('');
																			type1 = null;
																			type2 = null;
																		} else if ("fail" == response.responseText) {
																			$('#error').text('Job update failed!');
																			$('#error1').text('Job update failed!');
																			$('#success').text('');
																			$('#success1').text('');

																		}

																	}
																});
														
		return false;	
		}		
	
										function documents(jobid){
											 
											 $('#jobdoc2')
												.text("");
											 $('#jobdoc1')
												.text("");
												$
												.ajax({
													type : "POST",
													url : "${pageContext.servletContext.contextPath}/user/getJobDocuments",
													dataType : "json",
													data : {
														jobid : jobid
													},
													complete : function(data) {
														
														console.log(data);	
														if(data.responseJSON.uploaddocname1 == null){
															$("#doc1").css('display',
															'none');
														}
														else{
															$("#doc1").css('display',
															'block');
															doc1 = data.responseJSON.uploaddocname1;
															$("#jobdoc1")
															.text(data.responseJSON.uploaddocname1.replace(/[\d_]+/g, ''));
															
														}
														if (data.responseJSON.uploaddocname2 == null) {
															
															$("#doc2").css('display',
																	'none');
														} else {
															if (data.responseJSON.uploaddocname2 == null) {
																$("#doc2").css('display',
																		'none');
																
															}
															else{
																
																$("#doc2").css('display',
																'block');
																doc2 = data.responseJSON.uploaddocname2;
															$('#jobdoc2')
																	.text(data.responseJSON.uploaddocname2.replace(/[\d_]+/g, ''));
															}
														}
														

													}
												});
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
	function checkedit() {
		$('input[type="text"], date, select,textarea').prop("disabled", false);
		$("#reg").css("display", "none");
		$("#update").css("display", "block");
		$('#upload').css('display', 'block');
		$("#register").css("display", "none");
		$("#videocontent").css('display', 'block');
		$('#myCheckbox1').attr('disabled', false);
		$('#myCheckbox').attr('disabled', false);
		$('#success').text('');
		$('#success1').text('');
	}
	
	function GetFileSize() {
        var fi = document.getElementById('videoupload'); // GET THE FILE INPUT.
        // VALIDATE OR CHECK IF ANY FILE IS SELECTED.
        if (fi.files.length > 0) {
            // RUN A LOOP TO CHECK EACH SELECTED FILE.
            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fsize = fi.files.item(i).size;    
				// THE SIZE OF THE FILE.
				size = Math.round((fsize/1024)/1024 );
				
				if(size > 2){
                document.getElementById('fp').innerHTML =
                   
                        '*Please Upload extension with size' + Math.round((fsize/1024)/1024 ) + 'MB';
						$('#videoupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			}
			
            }
        }
    }
	function getdatas(){
		var flag = false;
		
		if(!flag){
			// Countries calling based on phone code
		 $.ajax({
			 url: "${pageContext.servletContext.contextPath}/user/getCountries",
   	        type: 'POST',
			contentType : "application/json",
   	        dataType: "json",
       	        complete:function(response){
       	        	console.log(response);
       	        
       	        	 for (var i = 0; i < response.responseJSON.length; i++)
       	   	         { 
       	        		
       	        		if(i == (response.responseJSON.length - 1)){
       	        			
         	        		  flag=true; // call the related function
         	        		 }
       	   	              $('#location').append($('<option>',
       	   	              {
       	   	                 value: response.responseJSON[i].id,
       	   	                 text : response.responseJSON[i].name
       	   	             }));
       	   	         }
       	        	console.log(flag);
       	        	 if(flag){
       	          flag = false;
       	          $(function() {	
 			      $('[name=location] option').filter(function() {
 			    	 
  		    			if($(this).text().toLowerCase() ==  $.trim(country).toLowerCase()){
  		    				// state callling based on selected country id 
  		    				
  		    				$.ajax({
  		    	       	       url: "${pageContext.servletContext.contextPath}/user/getStates",
  		    	       	        type: 'POST',
  		    	       	        dataType: "json",
  		    	       	        data: {countryId:$(this).val()},
  		    	       	        complete:function(response){
  		    	       	        	console.log(response);
  		    	       	        	 for (var i = 0; i < response.responseJSON.length; i++)
  		    	       	   	         { 
  		    	       	        		if(i == (response.responseJSON.length - 1)){
  		    	       	        			
  		    	         	        		  flag=true; // call the related function
  		    	         	        		 }
  		    	       	   	              $('#state').append($('<option>',
  		    	       	   	              {
  		    	       	   	                 value: response.responseJSON[i].id,
  		    	       	   	                 text : response.responseJSON[i].statename
  		    	       	   	             }));
  		    	       	   	         }
  		    	       	        if(flag){
  		    	       	        	flag = false;
  		    		       	    	 $(function() {
  		    		       	    	 $('[name=state] option').filter(function() { 
  		    		  		    			if($(this).text().toLowerCase() ==  $.trim(state).toLowerCase()){
  		    		  		    			// city calling based on selected state id 
  		    		  		    			console.log($(this).val());
  		    		  		    			 $.ajax({
  		    		  		        	       url: "${pageContext.servletContext.contextPath}/user/getAllCities",
  		    		  		        	        type: 'POST',
  		    		  		        	        dataType: "json",
  		    		  		        	        data: {state_id:$(this).val()},
  		    		  		        	        complete:function(response){
  		    		  		        	        	console.log(response);
  		    		  		        	        	 for (var i = 0; i < response.responseJSON.length; i++)
  		    		  		        	   	         { 
  		    		  		        	        		if(i == (response.responseJSON.length - 1)){
  		    		  		         	        			
  		    		  		           	        		  flag=true; // call the related function
  		    		  		           	        		 }
  		    		  		        	   	              $('#city').append($('<option>',
  		    		  		        	   	              {
  		    		  		        	   	                 value: response.responseJSON[i].id,
  		    		  		        	   	                 text : response.responseJSON[i].citiesname
  		    		  		        	   	             }));
  		    		  		        	   	         }
  		    		  		        	        	 if(flag){
  		    		  		        	        		 flag = false; 
  		    		  		        	        		$(function() {	
  		    		  		        	        		$('[name=city] option').filter(function() { 
  		    		  		        	        			
  		    		  		        	            	return ($(this).text().toLowerCase() ==  $.trim(city).toLowerCase()); //To select city
  		    		  		        	        		}).prop('selected', true);
  		    		  		        	    			})
  		    		  		        	        	 }
  		    		  		        	        }
  		    		  		  		  })
  		    		  		    			}
  		    			  		        	return ($(this).text().toLowerCase() ==  $.trim(state).toLowerCase()); //To select state
  		    			  		    		}).prop('selected', true);
  		    			  					}) 
  		    		       	        	 }
  		    	       	        }
  		    			  })
  		    			}
  		        	return ($(this).text().toLowerCase() ==  $.trim(country).toLowerCase()); //To select country
  		    		}).prop('selected', true);
  					})
       	        	 }
       	       }
       	        })
		}
	}
	
	
	 function getstate(){
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
			 getstate();
		 })

		 $('#state').on('change', function() {
			 var stateid = $('#state').val();
			 getcity(stateid);
			 
		 })
		 
		  function getcity(data){
			 
			 var stateid = $('#state').val();
			 $("#city").empty();
			  $.ajax({
	      	       url: "${pageContext.servletContext.contextPath}/user/getAllCities",
	      	        type: 'POST',
	      	        dataType: "json",
	      	        data: {state_id:data},
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
			
			 }
		 
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