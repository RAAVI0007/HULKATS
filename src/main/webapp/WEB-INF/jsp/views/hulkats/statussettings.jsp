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
					href="${pageContext.servletContext.contextPath}/recruiter/dashboard">Dashboard</a>

				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_BASICMANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/basicmanager/dashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<li class="breadcrumb-item active">Settings</li>
		</ol>
		<!-- Bread crumbs-->
		
		<!-- Loading Starts -->
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<!-- Loading End -->
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
				<div class="col-md-1">

					<label for="role"><strong>Select Settings</strong></label>
				</div>
				<div class="col-md-3">
					<select id="role" class="form-control">
						<option value="0">Select</option>
						
						<option value="1">Default</option>
						<option value="2">Change Source</option>
						

					</select>

				</div>
			</div>
		</div>
		
		<div class="card card-register mx-auto mt-5" id="target">
			<div class="card-header">
				<h2 align="center">Target Setting</h2>
			</div>
			<div class="card-body">
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />


				<form id="targetregistration" name="targetregistration">

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="target"><strong>Team Target</strong></label> <input
									class="form-control" name="teamtarget" id="teamtarget"
									type="text" placeholder="" required>
							</div>
							<div class="col-md-6">
								<label for="target"><strong>Recruiter Target</strong></label> <input
									class="form-control" name="rectarget" id="rectarget"
									type="text" placeholder="" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for=""><strong>Periodic</strong></label> <select
									id="report" name="periodic" class="form-control">

									<option value="1">Monthly</option>
									<option value="2">Quarterly</option>
									<option value="4">Yearly</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="target"><strong>Start Date</strong></label> <input
									type="text" class="form-control" id="start" name="dob"
									placeholder="mm-dd-yyyy">
							</div>
						</div>
					</div>
					<div class="form-group" style="text-align: center">

						<div class="col-xs-12" style="margin-top: 5px">
							<button id="regedit" onclick="checkAjaxCall();" type="button"
								class="btn btn-success "
								style="display: none; margin-left: auto; margin-right: auto;">Submit</button>
							<button id="editreg" onclick="targetedit();" type="button"
								class="btn btn-success "
								style="height: 35px; margin-left: auto; margin-right: auto;">Edit</button>
						</div>
					</div>
				</form>
			</div>

		</div>

<!--   footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>
