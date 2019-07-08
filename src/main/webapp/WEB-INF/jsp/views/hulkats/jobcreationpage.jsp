

<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Bread crumbs-->
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
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/jobcreation">Job</a>
			</li>
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/jobcreation">Add
					New</a></li>

		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center">Add & Publish Job</h2>
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
				<!-- Error Messages -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;"></span> <span id='error'
							style="color: red; margin-left: auto; margin-right: auto;"></span>
						<b><span id="jobid" style="color: green;"></span></b>

					</h5>
				</div>
				<!-- Error Message Ending -->

				<form id="jobregistration" name="jobregistration">
					<label for="jobtitle"><strong>Job Title</strong></label> <input
						class="form-control" type="text" name="jobtitle" id="jobtitle"
						placeholder="" required> <br> <br> 
						<label for="department"><strong>Department</strong></label> <input
						class="form-control" type="text" name="department" id="department"
						placeholder="" > <br>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="country"><strong>Country</strong></label> <select
									name="country" id="location"
									class="form-control select2-single">
									<option>Select Country</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="Location"><strong>State</strong></label> <select
									name="location" id="state" class="form-control select2-single">
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
					<br> <label for="companyname"><strong>Upload
							Video Description</strong></label> <input id="videoupload" type="file"
						class="form-control" name="video" required accept="video/mp4"
						onchange="GetFileSize();" /> <input id="response3" type="hidden"
						class="form-control" required /> <span
						style="margin-left: 0px; color: red;" id="fp"></span> <label
						for="videourl" id="videourllabel"><strong>Video
							URL</strong></label>
					<textarea class="form-control" rows="6" cols="75" id="videourl"
						placeholder='<iframe width="560" height="315" src="https://www.youtube.com/embed/xI-xVTBMGvk" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'></textarea>
					<br>
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
							<button type="button" id="reg" class="btn btn-success "
								onclick="checkAjaxCall();"
								style="margin-left: auto; margin-right: auto;">Submit</button>
							<button type="button" class="btn btn-success" id="register"
								onclick="checkedit();"
								style="display: none; margin-left: auto; margin-right: auto;">Edit</button>
							<button type="button" class="btn btn-success" id="update"
								onclick="updatejob();"
								style="display: none; margin-left: auto; margin-right: auto;">Submit</button>
						</div>
					</div>
				
				</form>
			</div>
		</div>
		<!--   footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>
</div>


<!-- Custom scripts for this page-->

<script>
	var inputChanged = false;
	var type1;
	var type2;
	var type3;
	var jobid;
	var doc1;
	var doc2;

	$('textarea').keyup(updateCount);
	$('textarea').keydown(updateCount);

	$('#myCheckbox').click(function() {
		if ($('#myCheckbox').is(':checked')) {
			$('#jobup').css('display', 'none');

		} else {
			$('#jobup').css('display', 'block');
		}
	});
	$('#myCheckbox1').click(function() {
		if ($('#myCheckbox1').is(':checked')) {

			$('#checkotp').css('display', 'none');
		} else {
			$("#checkotp").css("display", "block");

		}
	});
	function updateCount() {
		var cs = [ 4000 - $(this).val().length ];

		if (cs == 0) {
			$('#characters').text("Reached Limit");
		} else {
			$('#characters').text(cs);
		}
	}
	function upload() {
		$("#controls").css("display", "none");
		$("#checkotp").css("display", "block");

	}
	function upload1() {
		$("#controls1").css("display", "none");
		$("#checkotp1").css("display", "block");

	}
	$(document).ready(function() {
		statecity();
		$('.gre').gre({
			content_css_url : '<c:url value='/resources/css/gre.css' />',
			height : 250
		});

		$("#response").val("undefined");
		$("#response1").val("undefined");
		$("#cvupload").change(function() {
			var filesSelected = document.getElementById("cvupload").files;
			if (filesSelected.length > 0) {
				var fileToLoad = filesSelected[0];
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoadedEvent) {
					var base64value = fileLoadedEvent.target.result;
					base64 = base64value.split(',')[1];
					var type = base64value.split('/')[1];
					type1 = type.split(';')[0];

					$("#response").val(base64);
				};
				fileReader.readAsDataURL(fileToLoad);
			}
		});

		$("#cvupload1").change(function() {
			var filesSelected = document.getElementById("cvupload1").files;
			if (filesSelected.length > 0) {
				var fileToLoad = filesSelected[0];
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoadedEvent) {
					var base64value = fileLoadedEvent.target.result;
					base64 = base64value.split(',')[1];
					var type = base64value.split('/')[1];
					type2 = type.split(';')[0];

					$("#response1").val(base64);
				};
				fileReader.readAsDataURL(fileToLoad);
			} else {
				$("#response1").val("undefined");

			}
		});

		$("#videoupload").change(function() {
			var filesSelected = document.getElementById("videoupload").files;
			if (filesSelected.length > 0) {
				var fileToLoad = filesSelected[0];
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoadedEvent) {
					var base64value = fileLoadedEvent.target.result;
					base64 = base64value.split(',')[1];
					var type = base64value.split('/')[1];
					type3 = type.split(';')[0];

					$("#response3").val(base64);
				};
				fileReader.readAsDataURL(fileToLoad);
			}
		});

	});

	function statecity() { //getting country data

		$("#location").empty();
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/user/getCountries",
					type : 'POST',
					contentType : "application/json",
					dataType : "json",
					complete : function(response) {
						console.log(response);
						var obj = response.responseJSON;

						for (var i = 0; i < obj.length; i++) {
							$('#location').append($('<option>', {
								value : obj[i].id,
								text : obj[i].name
							}));
						}
						getcities(); //calling states data
					}

				})
	} // ending getting country data

	function getcities() { ///getting state
		var countryid = $('#location').val();

		$("#state").empty();
		$.ajax({
			url : "${pageContext.servletContext.contextPath}/user/getStates",
			type : 'POST',
			dataType : "json",
			data : {
				countryId : countryid
			},
			complete : function(response) {

				var obj = response.responseJSON;
				//console.log(obj);
				for (var i = 0; i < obj.length; i++) {
					$('#state').append($('<option>', {
						value : obj[i].id,
						text : obj[i].statename
					}));
				}
				getdata();
			}
		})

	} // ending 

	$("#location").on("change", function() { //on change event of location
		getcities();
	})

	$("#state").on("change", function() { //on change event of state
		getdata();
	})

	function getdata() {
		var stateid = $('#state').val();
		console.log(stateid);
		$("#city").empty();
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/user/getAllCities",
					type : 'POST',
					dataType : "json",
					data : {
						state_id : stateid
					},
					complete : function(response) {
						//console.log(response);
						var obj = response.responseJSON;
						//console.log(obj);
						for (var i = 0; i < obj.length; i++) {
							$('#city').append($('<option>', {
								value : obj[i].id,
								text : obj[i].citiesname
							}));
						}
					}
				})
	}

	$('#city').select2();
	$('#location').select2();
	$('#state').select2();

	function checkAjaxCall() {
		var joblist = {};
		$('#jobtitle').attr('style', "");
		
		$('#location').attr('style', "");
		$('#salary').attr('style', "");
		$('#jobrole').attr('style', "");
		$('#salarycriteria').attr('style', "");
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

		
		
		if ($('#department').val() == ''
			|| $('#department').val().trim() == '') {
		
			joblist.department = "other";
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
			$('#emptype').attr('style',
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
		/*if($('#videoupload').val()=='' || $('#videoupload').val()=='' || size > 2){
			$('#error').text('Video Upload is required !');
			$('#error1').text('Video Upload  is required !');
			//$('#videourl').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			$('#videoupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			joblist.videoupload =$('#response3').val();	
							
		}*/
		joblist.videoupload = $('#response3').val();
		joblist.videourl = $('textarea#videourl').val();

		/*if($('#cvupload').val()==''){
			$('#error').text('Upload is required !');
			$('#error1').text('Upload is required !');
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

		var path1 = $('#cvupload1').val();
		var filename1 = path1.match(/[^\\/]*$/)[0];
		var res1 = filename1.split(".");
		joblist.docname2 = res1[0];
		joblist.document2 = $('#response1').val();
		joblist.filetype2 = type2;

		var path2 = $('#videoupload').val();
		var filename2 = path2.match(/[^\\/]*$/)[0];
		var res2 = filename2.split(".");
		joblist.videoname = res2[0];
		joblist.filetype3 = type3;

		var jsonfile = {
			jobcreation : JSON.stringify(joblist)
		};
		console.log(jsonfile);
		$('#loading').show();
		$.ajax({
			url : "${pageContext.servletContext.contextPath}/user/jobcreation",
			type : 'POST',
			dataType : "json",
			data : {
				jobcreation : JSON.stringify(joblist)
			},
			complete : function(response) {
				$('#loading').hide();
				console.log(response);
				var data = response.responseText;
				var spliting = data.split(" ");
				jobid = spliting[1];

				if ("success" == spliting[0]) {
					$('#reg').attr("disabled", true);
					$("#reg").css("display", "none");
					$('#register').css("display", "block");
					$('input[type="text"], date, select,file').prop("disabled",
							true);
					$('#myCheckbox1').attr('disabled', true);
					$('#myCheckbox').attr('disabled', true);
					$('#upload').css('display', 'none');
					$('#documents').css('display', 'block');
					documents(jobid);
					$('#success').text('Job is registered succesfully!');
					$('#success1').text('Job is registered succesfully!');
					$('#jobid').text("Job ID : " + jobid);
					$('#error').text('');
					$('#error1').text('');
					$("#cvupload1").val('');
					$("#cvupload").val('');
					$("#response1").val('');
					$('#response').val('');
					type1 = null;
					type2 = null;
				} else if ("fail" == response.responseText) {
					$('#error').text('Job Registration failed!');
					$('#error1').text('Job Registration failed!');
					$('#success').text('');
					$('#success1').text('');

				}

			}
		});
		return false;
	}
	$("#location")
			.keyup(
					function() {
						var location = $("#location").val();
						$("#location")
								.autocomplete(
										{
											minLength : 2,
											source : function(request, response) {
												if (location.length > 1) {
													$
															.ajax({
																type : "POST",
																data : {
																	city : location
																},
																dataType : "json",
																url : "${pageContext.servletContext.contextPath}/user/getCities",
																complete : function(
																		data) {
																	var values = [];
																	console
																			.log(data);
																	for (var i = 0; i < data.responseJSON.length; i++) {
																		values
																				.push(data.responseJSON[i].city_state)
																	}
																	response(values);
																}
															})
												}
											}
										})
					})

	function checkedit() {
		$('input[type="text"], date, select').prop("disabled", false);
		$("#reg").css("display", "none");
		$("#update").css("display", "block");
		$('#upload').css('display', 'block');
		$("#register").css("display", "none");
		$('#myCheckbox1').attr('disabled', false);
		$('#myCheckbox').attr('disabled', false);
		$('#success').text('');
		$('#success1').text('');
	}

	function updatejob() {

		var joblist = {};
		$('#jobtitle').attr('style', "");
		
		$('#location').attr('style', "");
		$('#salary').attr('style', "");
		$('#salarycriteria').attr('style', "");
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
	function documents(jobid) {

		$('#jobdoc2').text("");
		$('#jobdoc1').text("");
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
						if (data.responseJSON.uploaddocname1 == null) {
							$("#doc1").css('display', 'none');
						} else {
							$("#doc1").css('display', 'block');
							doc1 = data.responseJSON.uploaddocname1;
							$("#jobdoc1").text(
									data.responseJSON.uploaddocname1.replace(
											/[\d_]+/g, ''));

						}
						if (data.responseJSON.uploaddocname2 == null) {

							$("#doc2").css('display', 'none');
						} else {
							if (data.responseJSON.uploaddocname2 == null) {
								$("#doc2").css('display', 'none');

							} else {

								$("#doc2").css('display', 'block');
								doc2 = data.responseJSON.uploaddocname2;
								$('#jobdoc2').text(
										data.responseJSON.uploaddocname2
												.replace(/[\d_]+/g, ''));
							}
						}

					}
				});
	}
	function orginals1() {

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
						documents(jobid);
					}
				})
	}
	function orginals2() {
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
						documents(jobid);
					}
				})
	}

	window.onbeforeunload = function(e) {
		if ($('#jobtitle').val() == '' || $('#jobtitle').val().trim() == '') {

		} else {
			inputChanged = true;
		}

		if ($('#companyname').val() == ''
				|| $('#companyname').val().trim() == '') {

		} else {
			inputChanged = true;

		}

		if ($('#location option:selected').text() == 'Select City and State'
				|| $('#location  option:selected').text().trim() == 'Select City and State') {

		} else {
			inputChanged = true;
		}

		if ($('#emptype option:selected').text() == 'Select Type') {

		} else {

			inputChanged = true;
		}

		if ($('#salary').val() == '' || $('#salary').val().trim() == '') {

		} else {
			inputChanged = true;
		}

		if ($("#salarycriteria option:selected").text() == 'Select') {

		} else {
			inputChanged = true;
		}

		if (inputChanged) {

		} else {
			inputChanged = false;
			var message = "Are you sure you want leave?";
			e.returnValue = message;
			return message;

		}
		return null;
	};

	//Deleting the file
	function firstdocdel() {
	
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteJobFile",
					data : {
						jobId : jobid,
						filename : doc1,
						fileNumber : 1
					},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						documents(jobid);
					}
				})
	}

	function seconddocdel() {
		
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteJobFile",
					data : {
						jobId : jobid,
						filename : doc2,
						fileNumber : 2
					},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						documents(jobid);
					}
				})
	}
	function GetFileSize() {
		var fi = document.getElementById('videoupload'); // GET THE FILE INPUT.
		$('#videourl').css('display', 'none');
		$('#videourllabel').css('display', 'none');
		$('#fp').text("");
		$('#videoupload').attr("");
		document.getElementById('fp').innerHTML = "";
		// VALIDATE OR CHECK IF ANY FILE IS SELECTED.
		if (fi.files.length > 0) {
			// RUN A LOOP TO CHECK EACH SELECTED FILE.
			for (var i = 0; i <= fi.files.length - 1; i++) {

				var fsize = fi.files.item(i).size;
				// THE SIZE OF THE FILE.
				size = Math.round((fsize / 1024));

				if (size > 2000) {
					document.getElementById('fp').innerHTML =

					'*Please Upload video size with less than 2 MB';
					$('#videoupload').attr('style',
							"border-radius: 5px; border:#FF0000 1px solid;");
				}

			}
		}
	}

	function GetFileSize1() {
		var fi = document.getElementById('cvupload'); // GET THE FILE INPUT.

		$('#fp1').text("");
		$('#cvupload').attr("");
		document.getElementById('fp1').innerHTML = "";
		// VALIDATE OR CHECK IF ANY FILE IS SELECTED.
		if (fi.files.length > 0) {
			// RUN A LOOP TO CHECK EACH SELECTED FILE.
			for (var i = 0; i <= fi.files.length - 1; i++) {

				var fsize = fi.files.item(i).size;
				// THE SIZE OF THE FILE.
				size = Math.round((fsize / 1024));

				if (size > 2000) {
					document.getElementById('fp1').innerHTML =

					'*Please Upload the document size with less than 150 KB';
					$('#cvupload').attr('style',
							"border-radius: 5px; border:#FF0000 1px solid;");
				}

			}
		}
	}

	function GetFileSize2() {
		var fi = document.getElementById('cvupload1'); // GET THE FILE INPUT.

		$('#fp2').text("");
		$('#cvupload1').attr("");
		document.getElementById('fp2').innerHTML = "";
		// VALIDATE OR CHECK IF ANY FILE IS SELECTED.
		if (fi.files.length > 0) {
			// RUN A LOOP TO CHECK EACH SELECTED FILE.
			for (var i = 0; i <= fi.files.length - 1; i++) {

				var fsize = fi.files.item(i).size;
				// THE SIZE OF THE FILE.
				size = Math.round((fsize / 1024));

				if (size > 150) {
					document.getElementById('fp2').innerHTML =

					'*Please Upload the document size with less than 150 KB';
					$('#cvupload1').attr('style',
							"border-radius: 5px; border:#FF0000 1px solid;");
				}

			}
		}
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
</body>
</html>
