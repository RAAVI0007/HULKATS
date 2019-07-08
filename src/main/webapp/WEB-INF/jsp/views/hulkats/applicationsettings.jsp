<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
<div id="loading"
	style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
	<p style="position: absolute; color: White; top: 50%; left: 45%;">
		<img src="<c:url value='/resources/images/ajax-loader.gif'/>" id="img" />
</div>
<div class="content-wrapper">
	<div class="container-fluid">
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
			<sec:authorize access="hasRole('ROLE_MANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/manager/applications">Application
						Overview</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_RECRUITER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/application">Application
						Overview</a></li>
			</sec:authorize>
		</ol>



		<div class="tab-nav">
			<ul id="industry-carousel" class="fetjob-lists">


				<li class="wow fadeInLeft fst-li" data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="Reviewed"><span></span>
						<h6 id="h1">Reviewed</h6></a></li>


				<li class="wow fadeInLeft " data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="PhoneScreened"><span></span>
						<h6 id="h2">Phone Screened</h6></a></li>


				<li class="wow fadeInLeft " data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="Interviewed"><span></span>
						<h6 id="h3">Interviewed</h6></a></li>


				<li class="wow fadeInLeft " data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="OfferMade"><span></span>
						<h6 id="h4">Offer Made</h6></a></li>


				<li class="wow fadeInLeft " data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="Rejected"><span></span>
						<h6 id="h5">Rejected</h6></a></li>


				<li class="wow fadeInLeft" data-wow-duration="0.8s"
					data-wow-delay="0.3s"><a id="Hired"><span></span>
						<h6 id="h6">Hired</h6></a></li>
			</ul>


		</div>




		<div class="form-group">
			<div class="form-row">
				<div class="col-md-3">
					<label for="firstname"><strong>Job Title</strong></label> <span
						id="title"></span>
				</div>
				<div class="col-md-3">
					<label for="lastname"><strong>Applicant Name</strong></label> <span
						id="name"></span>
				</div>
				<div class="col-md-3">
					<label for="lastname"><strong>Mobile</strong></label> <span
						id="mobile"></span>
				</div>
				<div class="col-md-3">
					<label for="lastname"><strong>Email</strong></label> <span
						id="email"></span>
				</div>
			</div>
		</div>
		<div id="documents">
			<div class="form-group">
				<div class="form-row">
					<div class="col-md-4">
						<label><strong>Job Documents</strong></label> <br>
						<div id="docs1">
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="jobdocs1"
								target="iframe_a" onclick="orginals1()"></a>
						</div>


						<div id=docs2>
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="jobdocs2"
								target="iframe_a" onclick="orginals2()"></a>
						</div>
					</div>
					<div class="col-md-4">
						<label><strong>Applicant Documents</strong></label>
						<div id=apps1>
							<br> <i class="fa fa-fw fa fa-file-text-o"></i> <a
								id="appdocs1" onclick="apporginals1()"></a> <br>
						</div>
						<div id=apps2>
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="appdocs2"
								onclick="apporginals2()"></a>
						</div>
						<div id=apps4>
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="appdocs4"
								onclick="apporginals4()"></a> <br>
						</div>
						<div id=apps5>
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="appdocs5"
								onclick="apporginals5()"></a>
						</div>

					</div>
					<div class="col-md-4">
						<div id=apps6>
							<label><strong>Profile</strong></label> <br>
							<div id=apps7>
								<i class="fa fa-fw fa fa-file-text-o"></i> <a id="appdocs3"
									onclick="apporginals3()"></a>
							</div>
						</div>
					</div>
				</div>
				<!-- Error Messages -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;"></span> <span id='error'
							style="color: red; margin-left: auto; margin-right: auto;"></span>
					</h5>
				</div>
				<!-- Error Message Ending -->
				<div class="form-group">
					<div class="form-row">
						<form id="actionsform" name="actionsform">
							<label for="role"><strong>Select Action</strong></label> <select
								id="actions" class="form-control">
								<option value="1">Send Message</option>
								<option value="3">Change Status</option>
								<option value="5">Add Comment</option>
								<option value="7" selected>View Application</option>
								<option value="9">Update Source</option>
							</select>
						</form>
					</div>
				</div>
				<div class="card card-register mx-auto mt-5" id="appstatuscard"
					style="display: none">
					<div class="card-body">
						<div id="statusedit">
							<label><strong>Application Status</strong></label> <select
								class="form-control" id="applicantionstatus">
								<option value='0'>Select Status</option>
								<option>Reviewed</option>
								<option>Phone Screened</option>
								<option>Interviewed</option>
								<option>Offer Made</option>
								<option>Rejected</option>
								<option>Hired</option>
							</select> <br> <label><strong>Comment</strong></label>
							<textarea rows="10" cols="70" class="form-control"
								id="addcommentstatus"></textarea>
							<div class="form-group" style="text-align: center">
								<div class="col-xs-12" style="margin-top: 5px">
									<button type="button" id="reg" class="btn btn-success "
										onclick="statuschange()"
										style="margin-left: auto; margin-right: auto;">Submit</button>

								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>
				<div class="card card-register mx-auto mt-5" id="appsourcecard"
					style="display: none">
					<div class="card-body">
						<div id="sourceupdate">
							<label><strong>Source Update</strong></label> <select
								class="form-control" id="applicantionsource">
								<option value='0'>Select Source</option>
								<option>Indeed</option>
								<option>Linkedin</option>
								<option>Monster</option>
								<option>Referral</option>
								<option>Internal</option>
								<option>Others</option>
							</select> <br>

							<div class="form-group" style="text-align: center">
								<div class="col-xs-12" style="margin-top: 5px">
									<button type="button" id="reg" class="btn btn-success "
										onclick="sourcechange()"
										style="margin-left: auto; margin-right: auto;">Submit</button>

								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>

				<div class="card card-register mx-auto mt-5" id="apptempcard"
					style="display: none">
					<div class="card-body">

						<form id="templateform" name="template">
							<label for="template"><strong>Template Name</strong></label>
							<div class="input-group">

								<select id="template" class="form-control select2-single"
									name="template"><option>Select Template</option></select>
							</div>
							<br> <br> <label for="subject"><strong>Subject</strong></label>
							<input id="subject3" name="subject" class="form-control"
								placeholder="Enter Subject" /> <br> <label for="body"><strong>Body</strong></label>
							<textarea rows="10" cols="5" id="body3" name="body"
								class="form-control" placeholder="Enter Body" required></textarea>
							<br>
							<div id="documentstemp" style="display: none;">
								<label><strong>Template Documents</strong></label>
								<div id=tem1>
									<input type="checkbox" id="myCheckbox" name="myCheckbox">
									<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc2"
										onclick="temporg()"></a> <a id="cross"
										onclick='firstdocdel();'>&#x274C;</a>
								</div>

								<div id=tem2>
									<input type="checkbox" id="myCheckbox1" name="myCheckbox1">
									<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc1"
										onclick="temporg1()"></a> <a id="cross"
										onclick='seconddocdel();'>&#x274C;</a>
								</div>
							</div>
							<div id="controls">
								<a id="save" onclick="upload();"><img
									src="<c:url value='/resources/images/add_2x.png' />"></a>
							</div>
							<div id="checkotp" style="display: none">
								<label for="cvupload"><strong>Upload Other
										Document</strong></label> <input id="cvupload1" type="file"
									class="form-control" name="cvupload" required /> <input
									id="response1" type="hidden" class="form-control" required />
								<br>
								<div id="controls2">
									<a id="save" onclick="upload1();"><img
										src="<c:url value='/resources/images/add_2x.png' />"></a>
								</div>
							</div>
							<div id="checkotp1" style="display: none">
								<label for="cvupload"><strong>Upload Other
										Document</strong></label> <input id="cvupload" type="file" class="form-control"
									name="cvupload" required /> <input id="response" type="hidden"
									class="form-control" required /> <br>
							</div>
							<div class="form-group" style="text-align: center">
								<h5>
									<span id='success1' style="color: green;"></span> <span
										id='error1'
										style="color: red; margin-left: auto; margin-right: auto;"></span>
								</h5>
							</div>
							<div class="form-group" style="text-align: center">
								<div class="col-xs-12" style="margin-top: 5px">
									<button type="button" class="btn btn-success" id="register"
										onclick="bulkemail();"
										style="margin-left: auto; margin-right: auto;">Send</button>
								</div>
							</div>
						</form>
					</div>
				</div>


				<div class="card card-register mx-auto mt-5" id="appcommentcard"
					style="display: none">
					<div class="card-body">
						<div id="statusedit">
							<form>
								<label><strong>Add Comment</strong></label>
								<textarea rows="10" cols="70" class="form-control"
									id="addcomment"></textarea>
							</form>
							<br>

							<div class="form-group" style="text-align: center">
								<div class="col-xs-12" style="margin-top: 5px">
									<button type="button" id="reg" class="btn btn-success "
										onclick="appcomment()"
										style="margin-left: auto; margin-right: auto;">Submit</button>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="commenttable" style="display: none">
					<div class="row" style="overflow-x: auto;">
						<div id="table" class="col-lg-12">
							<h2>Application History</h2>
							<div id="usertable" class="table-responsive1">

								<div id="usertable" class="table-responsive1">
									<table id="comment"
										class="table table-striped table-bordered nowrap">
										<thead style="background: #ff6707; color: #fff">
											<tr>
												<th class="table_heading">Date</th>
												<th class="table_heading">Owner</th>
												<th class="table_heading">Action Type</th>
												<th class="table_heading">Details</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="emailhistory" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Email History</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="X">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<label for="subject"><strong>Subject</strong></label> <input
						id="subjectemail" name="subject" class="form-control"
						placeholder="Enter Subject" /> <br> <label for="body"><strong>Body</strong></label>
					<textarea rows="10" cols="5" id="bodyemail" name="body"
						class="form-control" placeholder="Enter Body" required></textarea>
					<br>
					<div id="documentstemps" style="display: none">
						<label><strong>Template Documents</strong></label>
						<div id=tem55>

							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc55"
								onclick="history1()"></a>
						</div>

						<div id=tem66>

							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc66"
								onclick="history2()"></a>
						</div>
						<div id=tem3>
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc3"
								onclick="history3()"></a>
						</div>
						<div id=tem4>

							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc4"
								onclick="history4()"></a>
						</div>
					</div>

				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="statushistory" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Status History</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="X">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="statusedit">
						<label><strong>Application Status</strong></label> <input
							id="statushis" name="statushis" class="form-control" /> <br>
						<label><strong>Comment</strong></label>
						<textarea rows="10" class="form-control" id="commentstatus"></textarea>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="sourcehistory" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Source History</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="X">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="sourceedit">
						<label><strong>Application Source</strong></label> <input
							id="sourcehis" name="sourcehis" class="form-control" /> <br>


					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="commenthistory" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Comment History</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="X">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<label><strong>Add Comment</strong></label>
						<textarea rows="10" class='form-control' id="commenthis"></textarea>
					</form>

				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</div>
<%@include file="./shared/footer.jsp"%>
<script>
	var temp1;
	var temp;
	var type1;
	var type2;
	var id;
	var doc1;
	var doc2;
	var his1;
	var his2;
	var his3;
	var his4;
	var template_id;
	var doc1;
	var doc2;
	var applicantdetails_id;
	var x = window.location.href;
	var app_id = x.substring(x.lastIndexOf(':') + 1);
	var applicantid;
	var jobid;
	var obj;
	var cvname;
	var cvencoded;
	var cvtype;
	var covername;
	var coverencoded;
	var covertype;
	$(document)
			.ready(
					function() {
						currentstatus();
						apptable(app_id);
						$('#commenttable').css('display', 'block');
						$
								.ajax({
									type : "POST",
									url : "${pageContext.servletContext.contextPath}/user/getByAppIdprofilename",
									dataType : "json",
									data : {
										app_id : app_id
									},
									complete : function(data) {
										console.log("hello");
										console.log(data);
										$('#appdocs3').append(
												data.responseJSON.profilename);
										obj = JSON
												.parse(data.responseJSON.profilejson);
										applicantdetails_id = data.responseJSON.applicantdetails_id;
										cvname = obj.cvname;
										cvencoded = obj.cvencoded;
										cvtype = obj.cvtype;
										covername = obj.covername;
										coverencoded = obj.coverencoded;
										covertype = obj.covertype;
										if (cvname == "" || cvname == null) {
											$("#apps4").css('display', 'none');

										} else {

											if (cvtype == "vnd.openxmlformats-officedocument.wordprocessingml.document") {
												$('#appdocs4').append(
														obj.cvname + ".docx");
											} else {
												$('#appdocs4').append(
														obj.cvname + "."
																+ obj.cvtype);
											}
										}
										if (obj.covername == ""
												|| obj.covername == null) {
											$("#app5").css('display', 'none');

										} else {

											if (obj.covertype == "vnd.openxmlformats-officedocument.wordprocessingml.document") {
												$('#appdocs5')
														.append(
																obj.covername
																		+ ".docx");
											} else {
												$('#appdocs5')
														.append(
																obj.covername
																		+ "."
																		+ obj.covertype);
											}
										}

									}
								})

						$
								.ajax({
									type : "POST",
									url : "${pageContext.servletContext.contextPath}/user/getByAppId",
									dataType : "json",
									data : {
										app_id : app_id
									},
									complete : function(data) {
										$('#title')
												.html(
														": "
																+ data.responseJSON.jobtitle);
										$('#name').html(
												": " + data.responseJSON.name);
										$('#mobile')
												.html(
														": "
																+ data.responseJSON.contactno);
										$('#email').html(
												": " + data.responseJSON.email);
										//  $('#appdocs3').append(data.responseJSON.profilename);
										applicantdetails_id = data.responseJSON.applicantdetails_id;
										console.log(data);
									}
								})

						$
								.ajax({
									type : "POST",
									url : "${pageContext.servletContext.contextPath}/user/getAllTemplateNames",
									dataType : "json",
									complete : function(data) {
										console.log(data);
										templatedata = data;
										for (var i = 0; i < data.responseJSON.length; i++) {
											$('#template')
													.append(
															$(
																	'<option>',
																	{
																		value : data.responseJSON[i].template_id,
																		text : data.responseJSON[i].template_name
																	}));
										}
									}
								})
					})

	function currentstatus() {
		$('#Reviewed').removeClass('activenav');
		$('#PhoneScreened').removeClass('activenav');
		$('#Interviewed').removeClass('activenav');
		$('#OfferMade').removeClass('activenav');
		$('#Rejected').removeClass('activenav');
		$('#Hired').removeClass('activenav');

		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAppStatus",
					dataType : "json",
					data : {
						appId : app_id
					},
					complete : function(data) {
						console.log(data);

						if (data.responseText == 'Awaiting Review') {
							$('#Reviewed').addClass('activenav');
							$('#h1').css('font-weight', 'bold');
						} else if (data.responseText == 'Phone Screened') {
							$('#PhoneScreened').addClass('activenav');
							$('#h2').css('font-weight', 'bold');
						} else if (data.responseText == 'Interviewed') {
							$('#Interviewed').addClass('activenav');
							$('#h3').css('font-weight', 'bold');
						} else if (data.responseText == 'Offer Made') {
							$('#OfferMade').addClass('activenav');
							$('#h4').css('font-weight', 'bold');
						} else if (data.responseText == 'Rejected') {
							$('#Rejected').addClass('activenav');
							$('#h5').css('font-weight', 'bold');
						} else if (data.responseText == 'Hired') {
							$('#Hired').addClass('activenav');
							$('#h6').css('font-weight', 'bold');
						}
					}
				})
	}

	function emailhistory(historyid) {
		$('#emailhistory').modal('show');
		$('#subjectemail').val('');
		$('#bodyemail').val('');
		id = historyid;
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/emailHistory",
					dataType : "json",
					data : {
						historyId : historyid
					},
					complete : function(data) {
						console.log(data);
						$('#subjectemail').val(data.responseJSON.subject);
						$('#bodyemail').val(data.responseJSON.body);
						$('#subjectemail').prop('readonly', true);
						$('#bodyemail').prop('readonly', true);
						$('#documentstemps').css('display', 'block');
						if (data.responseJSON.attachement4 == null
								&& data.responseJSON.attachement1 == null
								&& data.responseJSON.attachement2 == null
								&& data.responseJSON.attachement3 == null) {
							$('#documentstemp').css('display', 'none');

						}
						if (data.responseJSON.attachement1 == null) {
							$("#tem55").css("display", "none");

						} else {

							$("#tem55").css("display", "block");
							his1 = data.responseJSON.attachement1;
							$("#temdoc55").text(
									data.responseJSON.attachement1.replace(
											/[\d_]+/g, ''));
						}
						if (data.responseJSON.attachement2 == null) {
							$('#tem66').css('display', 'none');
						} else {
							his2 = data.responseJSON.attachement2;
							$('#tem66').css('display', 'block');
							$("#temdoc66").text(
									data.responseJSON.attachement2.replace(
											/[\d_]+/g, ''));

						}
						if (data.responseJSON.attachement3 == null) {
							$("#tem3").css("display", "none");

						} else {
							his3 = data.responseJSON.attachement3;
							$("#temdoc3").text(
									data.responseJSON.attachement3.replace(
											/[\d_]+/g, ''));
							$("#tem3").css("display", "block");
						}
						if (data.responseJSON.attachement4 == null) {
							$('#tem4').css('display', 'none');

						} else {
							his4 = data.responseJSON.attachement4;
							$('#tem4').css('display', 'block');
							$("#temdoc4").text(
									data.responseJSON.attachement4.replace(
											/[\d_]+/g, ''));

						}
					}
				})
	}

	function statushistory(historyid) {
		$('#statushistory').modal('show');
		$('#statushis').val('');
		$('#commentstatus').val('');
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/statusHistory",
					dataType : "json",
					data : {
						historyId : historyid
					},
					complete : function(data) {
						console.log(data);
						$('#statushis').val(data.responseJSON.status);
						$('#commentstatus')
								.val(data.responseJSON.statuscomment);
						$('#statushis').prop('readonly', true);
						$('#commentstatus').prop('readonly', true);
					}
				})
	}

	function sourcehistory(historyid) {
		$('#sourcehistory').modal('show');
		$('#sourcehis').val('');

		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/sourceHistory",
					dataType : "json",
					data : {
						historyId : historyid
					},
					complete : function(data) {
						console.log(data);
						$('#sourcehis').val(data.responseJSON.source);

						$('#sourcehis').prop('readonly', true);

					}
				})
	}

	function commenthistory(historyid) {
		$('#commenthistory').modal('show');
		$('#commenthis').val('');
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/commentHistory",
					dataType : "json",
					data : {
						historyId : historyid
					},
					complete : function(data) {
						console.log(data);
						$('#commenthis').val(data.responseJSON.comment);
						$('#commenthis').prop('readonly', true);
					}
				})
	}

	$('#template').select2();
	$("#template")
			.on(
					"change",
					function() {
						$("#temdoc2").text("");
						$("#temdoc1").text("");
						var selected = $(this).val();
						for (var i = 0; i < templatedata.responseJSON.length; i++) {
							if (selected == templatedata.responseJSON[i].template_id) {
								template_id = templatedata.responseJSON[i].template_id;
								$('#subject3').val(
										templatedata.responseJSON[i].subject);
								$('#body3').val(
										templatedata.responseJSON[i].body);
								$('#documentstemp').css('display', 'block');
								if (templatedata.responseJSON[i].attachement == null
										&& templatedata.responseJSON[i].attachement1 == null) {
									$('#documentstemp').css('display', 'none');

								}
								if (templatedata.responseJSON[i].attachement == null) {
									$("#tem1").css("display", "none");

								} else {
									temp = templatedata.responseJSON[i].attachement;
									$("#temdoc2")
											.text(
													templatedata.responseJSON[i].attachement
															.replace(/[\d_]+/g,
																	''));
									$("#tem1").css("display", "block");
								}
								if (templatedata.responseJSON[i].attachement1 == null) {
									$('#tem2').css('display', 'none');

								} else {
									temp1 = templatedata.responseJSON[i].attachement1;
									$('#tem2').css('display', 'block');
									$("#temdoc1")
											.text(
													templatedata.responseJSON[i].attachement1
															.replace(/[\d_]+/g,
																	''));

								}
							}
						}
					})
	$
			.ajax({
				type : "POST",
				url : "${pageContext.servletContext.contextPath}/user/getDocumentsByAppId",
				dataType : "json",
				data : {
					app_id : app_id
				},
				complete : function(data) {
					console.log(data);
					if (data.responseJSON.uploaddocument2 == null) {
						$("#docs2").css('display', 'none');
					} else {
						$('#jobdocs2').append(
								data.responseJSON.uploaddocument2.replace(
										/[\d_]+/g, ''));
					}
					if (data.responseJSON.otheruploads == null) {
						$("#apps2").css('display', 'none');
					} else {
						$('#appdocs2').append(
								data.responseJSON.otheruploads.replace(
										/[\d_]+/g, ''));
					}
					if (data.responseJSON.uploaddocument1 == null) {
						$("#docs1").css('display', 'none');

					} else {
						$("#jobdocs1").append(
								data.responseJSON.uploaddocument1.replace(
										/[\d_]+/g, ''));

					}
					if (data.responseJSON.uplaodcv == null) {

						$("#apps1").css('display', 'none');
					} else {

						$('#appdocs1').append(
								data.responseJSON.uplaodcv.replace(/[\d_]+/g,
										''));
					}
					jobid = data.responseJSON.jobid;
					applicantid = data.responseJSON.applicantid;
				}
			})

	var apptable = function(app_id) {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getApplicationHistory",
					dataType : "json",
					data : {
						app_id : app_id
					},
					complete : function(data) {
						console.log(data);
						$('#comment')
								.dataTable(
										{
											data : data.responseJSON,
											"bDestroy" : true,
											columns : [
													{
														"data" : "created_at"
													},
													{
														"data" : "createdemail"
													},
													{
														"data" : "particulars"
													},
													{
														"data" : null,
														"mRender" : function(
																data, type,
																full) {
															var particulars = full['particulars'];
															var historyid = full['historyid'];
															if (particulars == 'Email') {
																return '<a id="arrow"  onclick="emailhistory('
																		+ historyid
																		+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
															} else if (particulars == 'change status') {
																return '<a id="arrow"  onclick="statushistory('
																		+ historyid
																		+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
															} else if (particulars == 'Comment') {
																return '<a id="arrow"  onclick="commenthistory('
																		+ historyid
																		+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
															} else if (particulars == 'Source Update') {
																return '<a id="arrow"  onclick="sourcehistory('
																		+ historyid
																		+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
															} else {
																return '<a id="arrow"><img class="disabled" src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
															}
														}
													} ],
											responsive : true,
											columnDefs : [ {
												responsivePriority : 1,
												targets : 0
											}, {
												responsivePriority : 2,
												targets : -1
											} ]
										})
					},
					error : function() {
						alert('Error');
					}
				})
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
						console.log("job" + data);
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
										+ '" name="iframe_a" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}
				})
	}

	function apporginals1() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/cvUploadDocument",
					dataType : "json",
					data : {
						applicantid : applicantid
					},
					complete : function(data) {
						console.log(data);
						var voterimage = "data:application/pdf;base64,";

						voterimage += data.responseJSON.uplaodcv;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ voterimage
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}

				});
	}
	function apporginals2(data) {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/cvOtherUploadDocument",
					dataType : "json",
					data : {
						applicantid : applicantid
					},
					complete : function(data) {
						console.log(data);
						var voterimage = "data:application/pdf;base64,";

						voterimage += data.responseJSON.otheruploads;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ voterimage
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

					}
				})
	}

	function apporginals3() {
		window
				.open(
						'${pageContext.servletContext.contextPath}/user/profilepage?applicantdetails_id='
								+ applicantdetails_id + '&app_id=' + app_id,
						'_blank');

	}
	function apporginals4() {
		if (cvtype == "pdf") {
			var voterimage = "data:application/pdf;base64,";
			voterimage += cvencoded;
			var win = window.open();
			win.document
					.write('<iframe src="'
							+ voterimage
							+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

		} else {
			var voterimage = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,";
			voterimage += cvencoded;
			var win = window.open();
			win.document
					.write('<iframe src="'
							+ voterimage
							+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

		}
	}

	function apporginals5() {
		if (covertype == "pdf") {
			var voterimage = "data:application/pdf;base64,";
			voterimage += coverencoded;
			var win = window.open();
			win.document
					.write('<iframe src="'
							+ voterimage
							+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

		} else {
			var voterimage = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,";
			voterimage += coverencoded;
			var win = window.open();
			win.document
					.write('<iframe src="'
							+ voterimage
							+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')

		}
	}

	$('#actions')
			.on(
					'change',
					function() {
						$('#success').text('');
						$('#error').text('');
						$('#success1').text('');
						$('#error1').text('');
						if (this.value == 1) {
							$('#apptempcard').css('display', 'block');
							$('#appdoccard').css('display', 'none');
							$('#statustable').css('display', 'none');
							$('#appstatuscard').css('display', 'none');
							$('#appcommentcard').css('display', 'none');
							$('#commenttable').css('display', 'none');
							$('#appsourcecard').css('display', 'none');
							$
									.ajax({
										type : "POST",
										url : "${pageContext.servletContext.contextPath}/user/getDocumentsByAppId",
										dataType : "json",
										data : {
											app_id : app_id
										},
										complete : function(data) {
											console.log(data);
											if (data.responseJSON.uploaddocument2 == null) {
												$("#doc2").css('display',
														'none');
											} else {
												$('#jobdoc2')
														.append(
																data.responseJSON.uploaddocument2);
											}
											if (data.responseJSON.otheruploads == null) {
												$("#app2").css('display',
														'none');
											} else {
												$('#appdoc2')
														.append(
																data.responseJSON.otheruploads);
											}
											$("#jobdoc1")
													.append(
															data.responseJSON.uploaddocument1);

											$('#appdoc1').append(
													data.responseJSON.uplaodcv);

											jobid = data.responseJSON.jobid;
											applicantid = data.responseJSON.applicantid;
										}
									})

						}

						else if (this.value == 3) {
							$('#appdoccard').css('display', 'none');
							$('#appcommentcard').css('display', 'none');
							$('#appstatuscard').css('display', 'block');
							$('#statustable').css('display', 'none');
							$('#commenttable').css('display', 'none');
							$('#apptempcard').css('display', 'none');
							$('#appsourcecard').css('display', 'none');
						} else if (this.value == 7) {
							$('#appstatuscard').css('display', 'none');
							$('#appdoccard').css('display', 'none');
							$('#appcommentcard').css('display', 'none');
							$('#commenttable').css('display', 'block');
							$('#apptempcard').css('display', 'none');
							$('#appsourcecard').css('display', 'none');
							apptable(app_id); //function to call the application history table
						} else if (this.value == 5) {
							$('#appcommentcard').css('display', 'block');
							$('#statustable').css('display', 'none');
							$('#appstatuscard').css('display', 'none');
							$('#appdoccard').css('display', 'none');
							$('#commenttable').css('display', 'none');
							$('#apptempcard').css('display', 'none');
							$('#appsourcecard').css('display', 'none');
						} else if (this.value == 9) {
							$('#appsourcecard').css('display', 'block');
							$('#appcommentcard').css('display', 'none');
							$('#statustable').css('display', 'none');
							$('#appstatuscard').css('display', 'none');
							$('#appdoccard').css('display', 'none');
							$('#commenttable').css('display', 'none');
							$('#apptempcard').css('display', 'none');
						}

					})
	//Status Change
	function statuschange() {
		user = {};
		$('#success').text('');
		$('#error').text('');
		if ($("#applicantionstatus option:selected").text() == 'Select Status'
				|| $("#applicantionstatus option:selected").text().trim() == 'Select Status') {
			$('#error').text('Please select status!');
			return;
		} else {
			user.status = $("#applicantionstatus option:selected").text();
		}
		user.app_id = app_id;
		user.comment = $('#addcommentstatus').val();
		console.log(user);
		var jsonfile = {
			application : JSON.stringify(user)
		};
		$
				.ajax({
					type : "POST",
					dataType : "json",
					data : jsonfile,
					url : "${pageContext.servletContext.contextPath}/user/changeStatus",
					complete : function(data) {
						console.log(data);
						if (data.responseText == "success") {

							$('#success').text("Status edit Successfully!");
							currentstatus();
						} else if (data.responseText == "fail") {
							$('#error').text("Status edit failed!");
						}
					}
				})
	}

	function appcomment() {
		$('#success').text('');
		$('#error').text('');
		if ($("#addcomment").val() == '' || $("#addcomment").val().trim() == '') {
			$('#error').text('Comment is required!')
			return;
		} else {
			var appcomment = $("#addcomment").val();
		}
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {
				app_id : app_id,
				addComment : appcomment
			},
			url : "${pageContext.servletContext.contextPath}/user/addComment",
			complete : function(data) {
				console.log(data);
				if (data.responseText == "success") {
					$('#success').text("Comment added Successfully!");
				} else if (data.responseText == "fail") {
					$('#error').text("Comment added failed!");
				}
			}
		})
	}

	$("#template").on("change", function() {

		var selected = $(this).val();
		for (var i = 0; i < templatedata.responseJSON.length; i++) {
			if (selected == templatedata.responseJSON[i].template_name) {
				$('#documentstemp').css('display', 'none');
				$('#subject').val(templatedata.responseJSON[i].subject);
				$('#body').val(templatedata.responseJSON[i].body);
				$('#documentstemp').css('display', 'block');
				$("#temdoc2").text(templatedata.responseJSON[i].attachement);
			}
		}
	})
	$("#myCheckbox1").change(function() {
		var ischecked = $(this).is(':checked');
		doc2 = temp1;
		if (!ischecked)

			doc2 = null;
	});

	/*$('#myCheckbox1').click(function() {
			 doc2 =temp1; 
	}); */
	$("#myCheckbox").change(function() {
		var ischecked = $(this).is(':checked');
		doc1 = temp;
		if (!ischecked)

			doc1 = null;
	});
	function bulkemail() {
		var user = {};
		$('#success').text('');
		$('#error').text('');
		$('#success1').text('');
		$('#error1').text('');
		user.body = $('#body3').val();
		user.subject = $('#subject3').val();

		if ($('#body3').val() == '' || $('#body3').val().trim() == '') {
			$('#error').text('Body is required !');
			$('#error1').text('Body is required !');
			$('#body3').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			user.body = $('#body3').val();
		}
		if ($('#subject3').val() == '' || $('#subject3').val().trim() == '') {
			$('#error').text('Subject is required !');
			$('#error1').text('Subject is required !');
			$('#subject').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			user.subject = $('#subject3').val();
		}

		user.attachment2 = doc1;
		user.attachment3 = doc2;
		var path = $('#cvupload').val();
		var filename = path.match(/[^\\/]*$/)[0];
		var res = filename.split(".");
		var documentName = res[0];
		var path1 = $('#cvupload1').val();
		var filename1 = path1.match(/[^\\/]*$/)[0];
		var res1 = filename1.split(".");
		var documentName1 = res1[0];

		user.attachment = documentName1;
		user.attachment1 = documentName;

		user.templateDocument = $('#response1').val();
		user.fileType = type1;
		user.templateDocument1 = $('#response').val();
		user.fileType1 = type2;
		console.log(user);

		user.applicationid = app_id;
		var applicants = JSON.stringify(user);

		$('#loading').show();

		$.ajax({
			type : "POST",
			dataType : "json",
			data : {
				applicants : applicants
			},
			url : "${pageContext.servletContext.contextPath}/user/sendemail",
			complete : function(data) {
				$('#loading').hide();
				console.log(data);
				if (data.responseText == 'sucess') {
					$('#mailModal').modal('hide');
					$('#success').text('Email has been sent successfully');
					$('#success1').text('Email has been sent successfully');
					$('#error').text('');
					$('#error1').text('');
					$('#register').prop('disabled', true);
				} else if (data.responseText == 'fail') {

					$('#error').text('Email delivery failed');
					$('#success').text('');
					$('#error1').text('Email delivery failed');
					$('#success1').text('');
				}
			}

		})
	}
	function upload() {
		$("#controls").css("display", "none");
		$("#checkotp").css("display", "block");
	}
	function upload1() {
		$("#controls2").css("display", "none");
		$("#controls").css("display", "none");
		$("#checkotp1").css("display", "block");
	}
	function temporg() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/sendTemplateDocument",
					dataType : "json",
					data : {
						templateId : template_id
					},
					complete : function(data) {
						console.log(data);
						var voterimage = "data:application/pdf;base64,";
						voterimage += data.responseJSON.attachement;
						var win = window.open();
						win.document
								.write('<iframe src="'
										+ voterimage
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}
				});
	}
	function temporg1() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/sendTemplateDocument",
					dataType : "json",
					data : {
						templateId : template_id
					},
					complete : function(data) {
						console.log(data);
						var voterimage = "data:application/pdf;base64,";
						voterimage += data.responseJSON.attachement1;
						var win = window.open();
						win.document
								.write('<iframe src="'
										+ voterimage
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
					}
				});
	}
	$("#cvupload1").change(function() {
		var filesSelected = document.getElementById("cvupload1").files;
		if (filesSelected.length > 0) {
			var fileToLoad = filesSelected[0];
			var fileReader = new FileReader();
			fileReader.onload = function(fileLoadedEvent) {
				var base64value = fileLoadedEvent.target.result;
				base64 = base64value.split(',')[1];
				var type = base64value.split('/')[1];
				type1 = type.split(';')[0];
				$("#response1").val(base64);
			};
			fileReader.readAsDataURL(fileToLoad);
		}
	});
	$("#cvupload").change(function() {
		var filesSelected = document.getElementById("cvupload").files;
		if (filesSelected.length > 0) {
			var fileToLoad = filesSelected[0];
			var fileReader = new FileReader();
			fileReader.onload = function(fileLoadedEvent) {
				var base64value = fileLoadedEvent.target.result;
				base64 = base64value.split(',')[1];
				var type = base64value.split('/')[1];
				type2 = type.split(';')[0];
				$("#response").val(base64);
			};
			fileReader.readAsDataURL(fileToLoad);
		}
	});
	function history1() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAttachment1",
					dataType : "json",
					data : {
						historyid : id,
						attachment : his1
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseText;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');

					}
				})
	}
	function history2() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAttachment2",
					dataType : "json",
					data : {
						historyid : id,
						attachment : his2
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseText;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');

					}
				})
	}
	function history3() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAttachment3",
					dataType : "json",
					data : {
						historyid : id,
						attachment : his3
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseText;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');

					}
				})
	}
	function history4() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAttachment4",
					dataType : "json",
					data : {
						historyid : id,
						attachment : his4
					},
					complete : function(data) {
						console.log(data);
						var uploaddocument1 = "data:application/pdf;base64,";

						uploaddocument1 += data.responseText;

						var win = window.open();
						win.document
								.write('<iframe src="'
										+ uploaddocument1
										+ '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');

					}
				})
	}

	function sourcechange() {
		user = {};
		$('#success').text('');
		$('#error').text('');
		if ($("#applicantionsource option:selected").text() == 'Select Source'
				|| $("#applicantionsource option:selected").text().trim() == 'Select Source') {
			$('#error').text('Please select source!');
			return;
		} else {
			user.source = $("#applicantionsource option:selected").text();
		}
		user.app_id = app_id;
		var source = JSON.stringify(user);
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/updateSource",
					dataType : "json",
					data : {
						application : source
					},
					complete : function(data) {
						console.log(data);
						if (data.responseText == "success") {

							$('#success').text("Source update Successfully!");
						} else if (data.responseText == "fail") {
							$('#error').text("Source update failed!");
						}

					}
				})
	}

	function firstdocdel() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
					data : {
						templateId : template_id,
						filename : temp,
						fileNumber : 1
					},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						documents(template_id);
					}
				})
	}

	function documents(templateid) {
		$('#jobdoc2').text("");
		$('#jobdoc1').text("");
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getAttachments",
					dataType : "json",
					data : {
						template_id : templateid
					},
					complete : function(data) {
						console.log(data);
						if (templatedata.responseJSON[i].attachement == null) {
							$("#tem1").css("display", "none");

						} else {
							temp = templatedata.responseJSON[i].attachement;
							$("#temdoc2").text(
									templatedata.responseJSON[i].attachement
											.replace(/[\d_]+/g, ''));
							$("#tem1").css("display", "block");
						}

						if (templatedata.responseJSON[i].attachement1 == null) {
							$('#tem2').css('display', 'none');

						} else {

							$('#tem2').css('display', 'block');
							temp1 = templatedata.responseJSON[i].attachement1;
							$("#temdoc1").text(
									templatedata.responseJSON[i].attachement1
											.replace(/[\d_]+/g, ''));

						}
					}
				});
	}

	function seconddocdel() {
		$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
					data : {
						templateId : template_id,
						filename : temp1,
						fileNumber : 2
					},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						documents(template_id);
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

img.disabled {
	opacity: 0.5;
}

a{
cursor: pointer;
}
</style>

