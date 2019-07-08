<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
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
    
        <li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/alerts">Alerts</a>
			</li>
			<li class="breadcrumb-item"><a>View Alerts</a></li>
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
	
		<div class="row" style="overflow-x: auto;">
			<div id="table" class="col-lg-12">
				<h2>Alerts Overview</h2>
				<div id="usertable" class="table-responsive1">
					<table id="example"
						class="table table-striped table-bordered nowrap" cellspacing="0"
						width="100%">
						<thead style="background: #ff6707; color: #fff">
							<tr>
								<th class="table_heading">Alert Type</th>
								<th class="table_heading">Created_at</th>
								<th class="table_heading">Creator</th>
								<th class="table_heading">Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="./shared/footer.jsp"%>
<script>
$('document').ready(function(){
	 var table;
  $.ajax({
	 type: "POST",
     url: "${pageContext.servletContext.contextPath}/user/getallAlertslist",
     dataType: "json",
     complete: function(data) {
     console.log(data);
						table = $('#example')
								.dataTable(
										{
											data : data.responseJSON,
											rowReorder : {
												selector : 'td:nth-child(2)'
											},
											colReorder : true,
											columns : [
													{
														"data" : "alert_type"
													},
													{
														"data" : "created_at"
													},
													{
														"data" : "Name"
													},
													{ "data": null,
				                                		 "mRender" : function ( data, type, full ) {
				                                			 var alert_type=full["alert_type"];
				                                			 var jobid =full["ref_jobid"];
				                                			 var app_id=full["ref_app_id"];
				                                			 var applicantid=full["ref_applicantid"];
				                                			 if(alert_type == 'Applicant Alert'){
				                                				 return '<a id="arrow"  onclick="applicant('
																	+ applicantid
																	+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
				                                			 }else if(alert_type == 'Job Alert'){
				                                				 return '<a id="arrow"  onclick="job('
																	+ jobid
																	+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
				                                			 }
				                                			 else if(alert_type == 'Application Alert'){
				                                				 return '<a id="arrow"  onclick="app('
																	+ app_id
																	+ ')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
				                                			 }
				                                			 
															
														}
													}						
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
})
function job(jobid){
	  var url = "${pageContext.servletContext.contextPath}/user/alertjob?jobid:"+jobid;	
	  window.open(url, '_blank');
}
function app(app_id){
	 var url = "${pageContext.servletContext.contextPath}/user/applicationsettings?app_id:"+app_id;	
	  window.open(url, '_blank');
}
function applicant(applicantid){
	var url = "${pageContext.servletContext.contextPath}/user/alertapplicant?applicantid:"+applicantid;	
	  window.open(url, '_blank');
}
</script>
</body>

</html>