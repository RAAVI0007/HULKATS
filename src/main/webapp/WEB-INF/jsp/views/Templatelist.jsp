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
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/templateview">Template</a>
			</li>
			<li class="breadcrumb-item active">View Templates</li>
		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center">Template Overview</h2>
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
						<span id='success' style="color: green;" ></span> <span
							id='error'
							style="color: red; margin-left: auto; margin-right: auto;"
							></span>
					</h5>
				</div>
				<!-- Error Message Ending -->

				<form id="templateform" name="template">
					<label for="templatename"><strong>Template Name</strong></label> <br>
					<select id="template" class="form-control select2-single"
							name="template"><option>Select Template</option></select>

					<label for="subject"><strong>Subject</strong></label> <input
						id="subject" name="subject" type="text" class="form-control"
						placeholder="Enter Subject" /> <br> <label for="body"><strong>Body</strong></label>
					<textarea rows="10" cols="50" id="body" name="body"
						class="form-control" placeholder="Enter Body" required></textarea>
					<br>

					<div id="documents" style="display: none">

						<label><strong>Job Documents</strong></label> <br>
						<div id="doc1">
							<input type="checkbox" id="myCheckbox" name="myCheckbox">
							<i class="fa fa-fw fa fa-file-text-o"></i> <span id="jobdoc1"
								target="iframe_a" onclick="temporg()"></span>
								<a id="cross" onclick='firstdocdel();'>&#x274C;</a>
						</div>

						<div id=doc2>
							<input type="checkbox" id="myCheckbox1" name="myCheckbox1">
							<i class="fa fa-fw fa fa-file-text-o"></i> <span id="jobdoc2"
								target="iframe_a" onclick="temporg1()"></span>
								<a id="cross" onclick='seconddocdel();'>&#x274C;</a>
						</div>
					</div>
					<div id="upload">
						<div id="jobup">
							<label for="body"><strong>Upload Attachment</strong></label> <input
								id="cvupload" name="file" type="file" class="form-control"
								required> <input id="response" type="hidden"
								class="form-control" required /> <span
								style="margin-left: 0px; color: green;" >*Please
								Upload extension .docx or .pdf </span> <br>
						</div>
						<div id="controls">

							<a id="save" onclick="upload();"><img
								src="<c:url value='/resources/images/add_2x.png' />"></a>
						</div>
						<div id="checkotp" style="display: none">
							<label for="cvupload1"><strong>Upload Other
									Attachment</strong></label> <input id="cvupload1" type="file"
								class="form-control" name="cvupload" required /> <input
								id="response1" type="hidden" class="form-control" required /> <br>
						</div>
					</div>
					<!-- Error Message Starting -->
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success1' style="color: green;" ></span> <span
								id='error1'
								style="color: red; margin-left: auto; margin-right: auto;"
								></span>
						</h5>
					</div>
					<!-- Error Message Ending -->

					<div class="form-group" style="text-align: center">
						<div class="col-xs-12" style="margin-top: 5px">
					   <button type="button" class="btn btn-success" id="register" onclick="checkedit();" style="margin-left:auto;margin-right:auto;">Edit</button>
					   <button type="button" class="btn btn-success" id="update" onclick="updatejob();" style="display:none;margin-left:auto;margin-right:auto;">Submit</button>

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
	var type1;
	var type2;
	var templatedata;
	var template_id;
	var doc1;
	var doc2;
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
		}
	});
	
	
	$('#myCheckbox').click(function() {
		if ($('#myCheckbox').is(':checked')) {
			$('#jobup').css('display', 'none');
			$('#controls').css('display', 'none');

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
	
	$(document)
			.ready(
					function() {
						$("textarea").prop('disabled', true);
						$('input[type="text"], date').prop("disabled", true);
						$("#templatename").prop('disabled', false);
						$('#myCheckbox1').attr('disabled', true);
						$('#myCheckbox').attr('disabled', true);
						$("#upload").css("display", 'none');
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

						$('#template').select2();
					})
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
								$('#subject').val(
										templatedata.responseJSON[i].subject);
								$('#body').val(
										templatedata.responseJSON[i].body);
								$('#documents').css('display', 'block');
								if (templatedata.responseJSON[i].attachement == null) {
									$("#doc1").css('display', 'none');
								} else {
									doc1 = templatedata.responseJSON[i].attachement;
									$("#jobdoc1")
									.text(
											templatedata.responseJSON[i].attachement
													.replace(/[\d_]+/g, ''));
								}
								if (templatedata.responseJSON[i].attachement1 == null) {
									$("#doc2").css('display', 'none');
								} else {
									doc2 = templatedata.responseJSON[i].attachement1;
									$('#jobdoc2')
											.text(
													templatedata.responseJSON[i].attachement1
															.replace(/[\d_]+/g,
																	''));
								}
								
							}
						}
					});

	function checkedit() {
		$('input[type="text"], date, select').prop("disabled", false);
		$("#reg").css("display", "none");
		$("#update").css("display", "block");
		$("#register").css("display", "none");
		$('#upload').css('display', 'block');
		$('#checkotp').css('display', 'block');
		$('#myCheckbox1').attr('disabled', false);
		$('#myCheckbox').attr('disabled', false);
		$("textarea").prop('disabled', false);
		$('#success').text('');
		$('#success1').text('');
		$('#jobid').text("");
	}

	function updatejob() {

		if ($('#template').text() == ''
				|| $('#template').text().trim() == '') {
			$('#error').text('Template Name is required !');
			$('#error1').text('Template Name is required !');
			$('#template').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			var name = $('#template').text();
		}
		if ($('#subject').val() == '' || $('#subject').val().trim() == '') {
			$('#error').text('Subject is required !');
			$('#error1').text('Subject is required !');
			$('#subject').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			var subject = $('#subject').val();
		}
		if ($('#body').val() == '' || $('#body').val().trim() == '') {
			$('#error').text('Body is required !');
			$('#error1').text('Body is required !');
			$('#body').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
		} else {
			var body = $('#body').val();
		}

		if ($('#myCheckbox').is(':checked')) {

		} else {
			if ($('#cvupload').val() == '' || $('#cvupload').val().trim() == '') {
				$('#error').text('Upload attachment is required !');
				$('#error1').text('Upload attachment is required !');
				$('#body').attr('style',
						"border-radius: 5px; border:#FF0000 1px solid;");
				return;
			} else {

			}
		}
		var path = $('#cvupload').val();
		var filename = path.match(/[^\\/]*$/)[0];
		var res = filename.split(".");
		var documentName = res[0];
		var resfile = $('#response').val();
		var filetype = type1;

		if ($('#myCheckbox1').is(':checked')) {

		} else {
			var path1 = $('#cvupload1').val();
			var filename1 = path1.match(/[^\\/]*$/)[0];
			var res1 = filename1.split(".");
			var documentName1 = res1[0];
			var resfile2 = $('#response1').val();
			var filetype1 = type2;
		}

		var json = {
			template_name : name,
			subject : subject,
			body : body,
			attachement : resfile,
			attachement1 : resfile2,
			fileType : filetype,
			fileType1 : filetype1,
			documentName : documentName,
			documentName1 : documentName1,
			template_id : template_id
		}
		console.log(json);
		$
				.ajax({
					type : "POST",
					data : {
						createtemplate : JSON.stringify(json)
					},
					dataType : "json",
					url : "${pageContext.servletContext.contextPath}/user/updateTemplate",
					complete : function(response) {
						console.log(response);
						if ("success" == response.responseText) {
							$('#reg').attr("disabled", true);
							$("#reg").css("display", "none");
							$("#update").css("display", "none");
							$('#register').css("display", "block");
							$('input[type="text"], date, select,file').prop(
									"disabled", true);
							$("textarea").prop('disabled', true);
							$('#myCheckbox1').attr('disabled', true);
							$('#myCheckbox').attr('disabled', true);
							$('#upload').css('display', 'none');
							$('#documents').css('display', 'block');
							$('#checkotp').css('display', 'none');
							$("#cvupload1").val('');
							$("#cvupload").val('');
							$("#response1").val('');
							$('#response').val('');
						 	$('#jobid').text(template_id);	
							documents(template_id)
							type1 = null;
							type2 = null;
							$('#success').text(
									'Template is updated succesfully!');
							$('#success1').text(
									'Template is updated succesfully!');
							$('#error').text('');
							$('#error1').text('');

						} else if ("fail" == response.responseText) {
							$('#error').text('Template update failed!');
							$('#error1').text('Template update failed!');
							$('#success').text('');
							$('#success1').text('');

						}

					}
				});
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
	
	function firstdocdel(){
		$
		.ajax({
			type : "POST",
			url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
			data : {templateId:template_id,filename:doc1,fileNumber:1},
			dataType : "json",
			complete : function(data) {
				console.log(data);
				documents(template_id);
	}
		})
		}
	
	function seconddocdel(){
		alert(doc2);
		$
		.ajax({
			type : "POST",
			url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
			data : {templateId:template_id,filename:doc2,fileNumber:2},
			dataType : "json",
			complete : function(data) {
				console.log(data);
				documents(template_id);
	}
		})
		}
	
	
	function upload() {
		$("#controls").css("display", "none");
		$("#checkotp").css("display", "block");
	}
	
	function documents(templateid){
		 $('#jobdoc2')
			.text("");
		 $('#jobdoc1')
			.text("");
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
					  if (data.responseJSON[0].attachement == null) {
							$("#doc1").css('display',
									'none');
						} 
						else {
							doc1 = data.responseJSON[0].attachement;
							$("#jobdoc1")
									.text(
											data.responseJSON[0].attachement.replace(/[\d_]+/g, ''));
						}
					if (data.responseJSON[0].attachement1 == null) {
						$("#doc2").css('display',
								'none');
					} 
					else {
						if (data.responseJSON[0].attachement1 == null) {
							$("#doc2").css('display',
									'none');
						}
						else{
							$("#doc2").css('display',
							'block');
							doc2 = data.responseJSON[0].attachement1;
						$('#jobdoc2')
								.text(
										data.responseJSON[0].attachement1.replace(/[\d_]+/g, ''));
						}
					}
					

				}
			});
		}
</script>
<style>
.ui-autocomplete {
	cursor: pointer;
	height: 120px;
	overflow-y: scroll;
}

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
