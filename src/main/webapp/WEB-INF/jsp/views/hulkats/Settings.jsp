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
						<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_RECRUITER')">
						<option value="1" selected>Target</option>
						</sec:authorize>
						<option value="2">Auto Signature</option>
						<option value="3">Change Team name</option>
						<option value="4">Email Templates</option>

					</select>

				</div>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_RECRUITER')">
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
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>

		</div>
</sec:authorize>

		<div class="card card-register mx-auto mt-5" id="signature">
			<div class="card-header">
				<h2 align="center">Auto Signature</h2>
			</div>
			<div class="card-body">
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <span id='error'
					style="margin-left: 137px; color: red;"></span>
				<form id="signature" name="signature">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="name"><strong>Name</strong></label> <input
									class="form-control" type="text" name="name" id="name"
									placeholder="" required disabled> <br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="namestyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="designation"><strong>Designation</strong></label> <input
									class="form-control" type="text" name="designation"
									id="designation" placeholder="Manager" required> <br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="desigstyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="mobile"><strong>Mobile</strong></label> <input
									class="form-control" type="text" name="mobile" id="mobile"
									placeholder="65********" required disabled> <br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="mobilestyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="mobile"><strong>Email</strong></label> <input
									class="form-control" type="text" name="mobile" id="email"
									placeholder="" required disabled> <br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="emailstyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="company"><strong>Company Name</strong></label> <input
									class="form-control" type="text" name="company" id="company"
									placeholder="HULKLOGIC LLC" required> <br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="companystyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="company"><strong>Company Address</strong></label>
								<textarea class="form-control" name="company" id="companyadd"
									placeholder="DALLAS, TEXAS, USA 75001" required></textarea>
								<br>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="addrstyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-8">
								<label for="weburl"><strong>Company Website</strong></label> <input
									class="form-control" type="text" name="weburl" id="weburl"
									placeholder="www.hulklogic.com" required>
							</div>
							<div class="col-md-3">
								<label for="style"><strong>Select Style</strong></label> <select
									class="form-control" name="style" id="urlstyle">
									<option value='bold'>Bold</option>
									<option value='italic'>Italic</option>
									<option value='underlined'>Underlined</option>
								</select><br>
							</div>
						</div>
					</div>

					<input type='button' class="btn btn-success" onclick='preview();'
						value='Preview'> <br /> <br />
					<div class="preview " id="theme-1" style="display: none">
						<div class="default_signature">
							<br />
							<table>
								<tbody>
									<tr>
										<td style="padding-left: 0px; padding-top: 0;" valign="top"
											class="change_theme_p"><br />
											<h1 class="name-target"
												style="font-size: 16px; margin: 0px; text-transform: uppercase; font-weight: bold;">
												<span id="signame"></span>
											</h1> <span class="signature_jobtitle-target change_font txt"
											style="color: #333333; padding-bottom: 0; padding-top: 2px; font-weight: 500; margin: 0px;"
											id="sigdest"></span>
											<ul class="change_theme_font"
												style="margin-top: 10px; margin-left: 0; margin-right: 0; margin-bottom: 0; padding: 0; list-style: none; text-align: left; margin-bottom: 10px;">
												<li
													style="margin-bottom: 0; margin-right: 10px; margin-left: 0; display: inline-block; color: #333;">
													<span class="" style="color: #4fb218; font-weight: bold;">Mobile:</span>
													<span class="mobilephone-target txt" id="sigmobile"></span>
												</li>
												<li
													style="margin-bottom: 0; margin-right: 0; margin-left: 0; display: inline-block; color: #333;">
													<span class="" style="color: #4fb218; font-weight: bold;">Email:</span>
													<a href="" class="email-target link"
													style="color: #333; text-decoration: none;" id="sigemail"></a>
												</li>
											</ul>
											<p class="change_theme_font"
												style="text-align: left; font-weight: 600; color: #333333; margin-top: 0; margin-left: 0; margin-right: 0; margin-bottom: 0;">
												<span class="change_theme_font" style="display: block;"
													id="sigcompany"></span> <span class="txt" id="sigaddress"></span>
											</p>
											<p class="change_theme_font"
												style="text-align: left; font-weight: 400; color: #333333; margin-top: 0; margin-left: 0; margin-right: 0; margin-bottom: 0;">
												<span class="change_theme_font"
													style="color: #4fb218; font-weight: bold;">Website:</span>
												<a href="" style="color: #333; text-decoration: none;"
													class="link" id="sigurl"></a>
											</p></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div id="demo"></div>
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success1' style="color: green;"></span> <span
								id='error1'
								style="color: red; margin-left: auto; margin-right: auto;"></span>
						</h5>
					</div>
					<div class="form-group" style="text-align: center">
						<div class="col-xs-12 btn-group" style="margin-top: 5px">
							<input id="sigregs" onclick="signatures();"
								class="btn btn-success" type="button"
								style="display: none; margin-right: 16px" value="Submit" />
							<button type="button" class="btn btn-success"
								onclick='testmail();'
								style="margin-left: auto; margin-right: 16px;">Test
								Mail</button>
							<button type="button" class="btn btn-success" id="sigregister"
								onclick="checkeditsig();" style="margin-right: auto;">Edit</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>

		</div>
		<div class="card card-register mx-auto mt-5" id="teamname">
			<div class="card-header">
				<h2 align="center">Change Teamname</h2>
			</div>
			<div class="card-body">
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <span id='error'
					style="margin-left: 137px; color: red;"></span>

				<form id="teamname" name="teamname">
					<div class="form-group">
						<div class="form-row">
							<label for="teamname"><strong>Team Name</strong></label> <input
								class="form-control" id="teamnames" placeholder="" required>
						</div>
					</div>
					<div class="form-group" style="text-align: center">

						<div class="col-xs-12" style="margin-top: 5px">
							<input id="regteam" onclick="teamnamechange();"
								class="btn btn-success" type="button"
								style="display: none; height: 35px; margin-left: auto; margin-right: auto;"
								value="Submit" />
							<button type="button" class="btn btn-success" id="registerteam"
								onclick="checkeditteamname();"
								style="margin-left: auto; margin-right: auto;">Edit</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>

		</div>

		<div class="row" id="emailtemplate" style="display: none;">
			<div class="form-group">
				<div class="form-row">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Template One</a>
								</h4>
							</div>
							<div class="card-footer">
								<button type="button" class="btn" data-toggle="modal"
									data-target="#product_view1">Preview</button>
								<button type="button" class="btn btn-default" id="uptemp1" onclick="updatetemplate(1)">Select</button>
								<!--<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>-->
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">Template Two</a>
								</h4>
							</div>
							<div class="card-footer">
								<button type="button" class="btn" data-toggle="modal"
									data-target="#product_view2">Preview</button>
								<button type="button" class="btn btn-default" id="uptemp2" onclick="updatetemplate(2)">Select</button>
								<!--<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade product_view" id="product_view1">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Template One</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="X">
							<span aria-hidden="true">X</span>
						</button>
					</div>
					<div class="modal-body">

						<div bgcolor="#E1E1E1" leftmargin="0" marginwidth="0"
							topmargin="0" marginheight="0" offset="0">
							<center style="background-color: #E1E1E1;">
								<table border="0" cellpadding="0" cellspacing="0" height="100%"
									width="100%" id="bodyTable"
									style="table-layout: fixed; max-width: 100% !important; width: 100% !important; min-width: 100% !important;">
									<tr>
										<td align="center" valign="top" id="bodyCell">
											<table bgcolor="#FFFFFF" border="0" cellpadding="0"
												cellspacing="0" width="500" id="emailBody">
												<tr>
													<td align="center" valign="top">
														<table border="0" cellpadding="0" cellspacing="0"
															width="100%" style="color: #FFFFFF;" bgcolor="#FFFFFF">
															<tr>
																<td align="center" valign="top">
																	<table border="0" cellpadding="0" cellspacing="0"
																		width="500" class="flexibleContainer">
																		<tr>
																			<td align="center" valign="top" width="500"
																				class="flexibleContainerCell">
																				<table border="0" cellpadding="30" cellspacing="0"
																					width="100%">
																					<tr>
																						<td align="center" valign="top"
																							class="textContent"><h1
																								style="color: #FFA500; line-height: 100%; font-family: Helvetica, Arial, sans-serif; font-size: 30px; font-weight: normal; margin-bottom: 5px; text-align: center;">WELCOME</h1>
																							<h2
																								style="text-align: center; font-weight: normal; font-family: Helvetica, Arial, sans-serif; font-size: 23px; margin-bottom: 10px; color: #000; line-height: 135%;">to</h2>
																							<div
																								style="text-align: center; font-family: Helvetica, Arial, sans-serif; font-size: 15px; margin-bottom: 0; color: #FFFFFF; line-height: 135%;">
																								<img
																									src='data:image/png;base64,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'
																									style="width: 300px; width: 300px; margin-left: 55px;" />
																							</div></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td align="center" valign="top"><table border="0"
															cellpadding="0" cellspacing="0" width="100%"
															bgcolor="#F8F8F8">
															<tr>
																<td align="center" valign="top"><table border="0"
																		cellpadding="0" cellspacing="0" width="500"
																		class="flexibleContainer">
																		<tr>
																			<td align="center" valign="top" width="500"
																				class="flexibleContainerCell"><table border="0"
																					cellpadding="30" cellspacing="0" width="100%">
																					<tr>
																						<td align="center" valign="top"><table
																								border="0" cellpadding="0" cellspacing="0"
																								width="100%">
																								<tr>
																									<td valign="top" class="textContent">

																										<h3 mc:edit="header"
																											style="color: #5F5F5F; line-height: 125%; font-family: Helvetica, Arial, sans-serif; font-size: 20px; font-weight: normal; margin-top: 0; margin-bottom: 3px; text-align: center;">Welcome
																											[Name],</h3> <br />
																										<div mc:edit="body"
																											style="text-align: center; font-family: Helvetica, Arial, sans-serif; font-size: 15px; margin-bottom: 0; color: #5F5F5F; line-height: 135%;">
																											<p class="justify">
																												Thanks for choosing the HULKLOGIC product!!!
																												We are delighted to serve and empower
																												[Company Name] in your recruiting
																												challenges. <br /> Please follow the below
																												link and complete the registration process.
																											</p>
																											<br />
																										</div>
																									</td>
																								</tr>
																								<tr></tr>
																								<tr>
																								</tr>
																							</table></td>
																					</tr>
																					<td align="center" valign="top">
																						<!-- CENTERING TABLE // -->
																						<table border="0" cellpadding="0" cellspacing="0"
																							width="100%">
																							<tr style="padding-top: 0;">
																								<td valign="top">
																									<!-- FLEXIBLE CONTAINER // -->
																									<table border="0" cellpadding="30"
																										cellspacing="0" width="500"
																										class="flexibleContainer">
																										<tr>
																											<td style="padding-top: 0;" valign="top"
																												width="500" class="flexibleContainerCell">
																												<!-- CONTENT TABLE // --> <b>Warm
																													Regards,</b><br>
																												<div class="default_signature">
																													<table>
																														<tbody>
																															<tr>
																																<td
																																	style="padding-left: 0px; padding-top: 0;"
																																	valign="top;" class="change_theme_p">
																																	<h1 class="name-target"
																																		style="font-size: 16px; margin: 0px; text-transform: uppercase; font-weight: bold;">
																																		<span id="signame"
																																			style="font-weight: bold; font-style: italic; text-decoration: none;">[Name]</span>
																																	</h1> <span
																																	class="signature_jobtitle-target change_font txt"
																																	style="color: rgb(51, 51, 51); padding-bottom: 0px; padding-top: 2px; font-weight: bold; margin: 0px; font-style: italic; text-decoration: none;"
																																	id="sigdest">Manager</span>
																																	<ul class="change_theme_font"
																																		style="margin-top: 10px; margin-left: 0; margin-right: 0; margin-bottom: 0; padding: 0; list-style: none; text-align: left; margin-bottom: 10px;">
																																		<li
																																			style="margin-bottom: 0; margin-right: 10px; margin-left: 0; display: inline-block; color: #333;">
																																			<span class=""
																																			style="color: #4fb218; font-weight: bold;">Mobile:</span>
																																			<span class="mobilephone-target txt"
																																			id="sigmobile"
																																			style="font-weight: bold; text-decoration: none;">[Mobile
																																				Number]</span>
																																		</li>
																																		<li
																																			style="margin-bottom: 0; margin-right: 0; margin-left: 0; display: inline-block; color: #333;">
																																			<span class=""
																																			style="color: #4fb218; font-weight: bold;">Email:</span>
																																			<a href="" class="email-target link"
																																			style="color: rgb(51, 51, 51); text-decoration: none; font-weight: bold; font-style: italic;"
																																			id="sigemail">[Email]</a>
																																		</li>
																																	</ul>
																																	<p class="change_theme_font"
																																		style="text-align: left; font-weight: 600; color: #333333; margin-top: 0; margin-left: 0; margin-right: 0; margin-bottom: 0;">
																																		<span class="change_theme_font"
																																			style="display: block; font-weight: bold; font-style: italic; text-decoration: none;"
																																			id="sigcompany">[Company
																																			Nmae3]</span> <span class="txt"
																																			id="sigaddress"
																																			style="font-weight: bold; font-style: italic; text-decoration: none;">[Location]</span>
																																	</p>
																																	<p class="change_theme_font"
																																		style="text-align: left; font-weight: 400; color: #333333; margin-top: 0; margin-left: 0; margin-right: 0; margin-bottom: 0;">
																																		<span class="change_theme_font"
																																			style="color: #4fb218; font-weight: bold;">Website:</span>
																																		<a href="http://www.hulklogic.com/"
																																			style="color: rgb(51, 51, 51); text-decoration: none; font-weight: bold; font-style: italic;"
																																			class="link" id="sigurl">[Website]</a>
																																	</p>
																																</td>
																															</tr>
																														</tbody>
																													</table>
																												</div>
																											</td>
																										</tr>
																									</table>

																								</td>
																							</tr>
																						</table>

																					</td>
																				</table></td>
																		</tr>
																	</table></td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td align="center" valign="top"></td>
												</tr>

												<tr>
													<td align="center" valign="top"></td>
												</tr>
											</table>

											<table bgcolor="#E1E1E1" border="0" cellpadding="0"
												cellspacing="0" width="500" id="emailFooter">

												<tr>
													<td align="center" valign="top">

														<table border="0" cellpadding="0" cellspacing="0"
															width="100%">
															<tr>
																<td align="center" valign="top">

																	<table border="0" cellpadding="0" cellspacing="0"
																		width="500" class="flexibleContainer">
																		<tr>
																			<td align="center" valign="top" width="500"
																				class="flexibleContainerCell">
																				<table border="0" cellpadding="30" cellspacing="0"
																					width="100%">
																					<tr>
																						<td valign="top">

																							<div
																								style="font-family: Helvetica, Arial, sans-serif; font-size: 13px; color: #828282; text-align: center; line-height: 120%;">
																								<div>
																									Copyright &#169; 2018 <a
																										href="http://www.hulklogic.com"
																										target="_blank"
																										style="text-decoration: none; color: #828282;"><span
																										style="color: #828282;">HULKLOGIC LLC</span></a>.
																									All&nbsp;rights&nbsp;reserved.
																								</div>
																								<!--<div>If you do not want to receive emails from us, you can <a href="#" target="_blank" style="text-decoration:none;color:#828282;"><span style="color:#828282;">unsubscribe</span></a>.</div> -->
																							</div>

																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>

																</td>
															</tr>
														</table>

													</td>
												</tr>

											</table>


										</td>
									</tr>
								</table>
							</center>
						</div>


					</div>
				</div>
			</div>
		</div>
		<div class="modal fade product_view" id="product_view2">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Template Two</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="X">
							<span aria-hidden="true">X</span>
						</button>
					</div>
					<div class="modal-body">
						

							<div bgcolor="#E1E1E1" leftmargin="0" marginwidth="0"
							topmargin="0" marginheight="0" offset="0">
								<table style="min-width: 320px;" width="100%" cellspacing="0"
									cellpadding="0" bgcolor="#eaeced">
									<!-- fix for gmail -->
									<tr>
										<td class="hide">
											<table width="600" cellpadding="0" cellspacing="0"
												style="width: 600px !important;">
												<tr>
													<td style="min-width: 600px; font-size: 0; line-height: 0;">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="wrapper" style="padding: 0 10px;">
											<table data-module="module-1" data-thumb="thumbnails/01.png"
												width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td data-bgcolor="bg-module" bgcolor="#eaeced">
														<table class="flexible" width="600" align="center"
															style="margin: 0 auto;" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding: 29px 0 30px;">
																	<table width="100%" cellpadding="0" cellspacing="0">
																		<tr>
																			<th class="flex" width="113" align="left"
																				style="padding: 0;">
																				<table class="center" cellpadding="0"
																					cellspacing="0">
																					<tr>
																						<td style="line-height: 0;"><a
																							target="_blank" style="text-decoration: none;"
																							href="https://www.hulkats.com/"><img
																								src='data:image/png;base64,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'
																								border="0"
																								style="font: bold 12px/12px Arial, Helvetica, sans-serif; color: #606060;"
																								align="center" vspace="0" hspace="0" width="200"
																								height="100" alt="hulkats.COM" />
																							</div> </a></td>
																					</tr>
																				</table>
																			</th>

																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table> <!-- module 2 --> <!-- module 3 -->
											<table data-module="module-3" data-thumb="thumbnails/03.png"
												width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td data-bgcolor="bg-module" bgcolor="#eaeced">
														<table class="flexible" width="600" align="center"
															style="margin: 0 auto;" cellpadding="0" cellspacing="0">

															<tr>
																<td data-bgcolor="bg-block" class="holder"
																	style="padding: 65px 60px 50px;" bgcolor="#f9f9f9">
																	<table width="100%" cellpadding="0" cellspacing="0">
																		<tr>
																			<td data-color="title" data-size="size title"
																				data-min="20" data-max="40"
																				data-link-color="link title color"
																				data-link-style="text-decoration:none; color:#292c34;"
																				class="title" align="center"
																				style="font: 30px/33px Arial, Helvetica, sans-serif; color: #292c34; padding: 0 0 24px;">
																				Hello</td>
																		</tr>
																		<tr>
																			<td data-color="text" data-size="size text"
																				data-min="10" data-max="26"
																				data-link-color="link text color"
																				data-link-style="font-weight:bold; text-decoration:underline; color:#40aceb;"
																				align="center"
																				style="font: 16px/29px Arial, Helvetica, sans-serif; color: #888; padding: 0 0 21px;">
																				Lorem Ipsum is simply dummy text of the printing and
																				typesetting industry. Use this offer has been the
																				industry's standard dummy text ever since the 1500s,
																				when an unknown printer took a galley of type and
																				scrambled it to make.</td>
																		</tr>
																		<tr>
																			<td style="padding: 0 0 20px;">
																				<table width="134" align="center"
																					style="margin: 0 auto;" cellpadding="0"
																					cellspacing="0">
																					
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="28"></td>
															</tr>
														</table>
													</td>
												</tr>
											</table> <!-- module 4 --> <!-- module 5 --> <!-- module 6 --> <!-- module 7 -->
											<table data-module="module-7" data-thumb="thumbnails/07.png"
												width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td data-bgcolor="bg-module" bgcolor="#eaeced">
														<table class="flexible" width="600" align="center"
															style="margin: 0 auto;" cellpadding="0" cellspacing="0">
															<tr>
																<td class="footer" style="padding: 0 0 10px;">
																	<table width="100%" cellpadding="0" cellspacing="0">
																		<tr class="table-holder">
																			<th class="tfoot" width="400" align="left"
																				style="vertical-align: top; padding: 0;">
																				<table width="100%" cellpadding="0" cellspacing="0">
																					<tr>
																						<td data-color="text"
																							data-link-color="link text color"
																							data-link-style="text-decoration:underline; color:#797c82;"
																							class="aligncenter"
																							style="font: 12px/16px Arial, Helvetica, sans-serif; color: #797c82; padding: 0 0 10px;">
																							HULKLOGIC TECHNOLOGIES PVT LTD. &nbsp; All Rights
																							Reserved.</td>
																					</tr>
																				</table>
																			</th>

																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<!-- fix for gmail -->
									<tr>
										<td style="line-height: 0;"><div
												style="display: none; white-space: nowrap; font: 15px/1px courier;">&nbsp;
												&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
												&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
												&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
												&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div></td>
									</tr>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>


		<!--   footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>



<script>
	  $('#signature').css('display','none');
	  $('#teamname').css('display','none');
	  edittarget();
	
		$('input[type="text"], date, select').prop("disabled", true); 
		  $('#role').prop('disabled',false);
	  $('document').ready(function(){
		  checktemplate();
		  $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/getdetails",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	            	console.log(data);
	            	str1 = data.responseJSON.firstname+' ';
	            	str2 = data.responseJSON.lastname;
	            	var name = str1.concat(str2)
	            	$('#name').val(name.toUpperCase());	
	    		   	$('#mobile').val(data.responseJSON.contactno);
	    		   	$('#email').val(data.responseJSON.email);
	            }
	            })
	            
	           
	  })
	   function checktemplate(){
	            $.ajax({
	 	           url: "${pageContext.servletContext.contextPath}/user/gettemplateid",
	 	            type: 'POST',
	 	            dataType: "json",
	 	            complete:function(data){
	 	            	console.log(data);
	 	            	
	 	            	if(data.responseText == 1){
	 	            		$("#uptemp1").css('visibility', 'hidden');
	 	            		$("#uptemp2").css('visibility', 'visible');
	 	            		
	 	            	}else if(data.responseText == 2){
	 	            		$("#uptemp2").css('visibility', 'hidden');
	 	            		$("#uptemp1").css('visibility', 'visible');
	 	            	}
	 	            }
	            })
		  }
	function checkAjaxCall(){
	var user = {};
	
	if($('#teamtarget').val()=='' || $('#teamtarget').val().trim()==''||Validate($('#teamtarget').val())){
		$('#error').text('Team Target is required !');
		return;	
	}else{
		user.teamtarget =$('#teamtarget').val();		
	}
	if($('#rectarget').val()=='' || $('#rectarget').val().trim()==''||Validate($('#rectarget').val())){
		$('#error').text('Recruiter Target is required !');
		return;	
	}else{
		user.rectarget =$('#rectarget').val();		
	}
	
	if($( "#report option:selected" ).text()=='Select' || $( "#report option:selected" ).text().trim()=='Select'){
		$('#error').text('Level is required !');
		return;	
	}else{
		if($('#report').val() == 1){
			user.periodic = 'MONTH';
		}
		else if($('#report').val() == 2){
			user.periodic = 'QUATER';
		}
		else{
			user.periodic = 'YEAR';
		}
		if($('#start').val()=='' || $('#start').val().trim()==''||Validate($('#start').val())){
			$('#error').text('Team Target is required !');
			return;	
		}else{
			user.startdate =changeDate($('#start').val());		
		}
	}
	  $('#error').text('');
    	var jsonfile={target:JSON.stringify(user)}; 
        console.log(jsonfile);
        $('#loading').show();   
        $.ajax({
           url: "${pageContext.servletContext.contextPath}/manager/targets",
            type: 'POST',
            dataType: "json",
            data: jsonfile,
            complete:function(data){
            	 $('#loading').hide(); 
    			console.log(data);
    			if(data.responseText == 'success'){
    				$('input[type="text"], date, select').prop("disabled", true); 
    				  $('#role').prop('disabled',false);
    				  $('#editreg').css('display','block');
    		  	   	 	$('#regedit').css('display','none');
    				$('#success').text('Target added successfully');
    				$('#error').text('');
    			}
    			else if(data.responseText == 'fail'){
    				$('#error').text('Target added failed');
    				$('#success').text('');
    			}
    			
            }
        })
	
	}
	  function edittarget(){
		  $.ajax({
	           url: "${pageContext.servletContext.contextPath}/manager/existTargets",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	    			console.log(data);
	    			$('#teamtarget').val(data.responseJSON.teamtarget);
	    			$('#rectarget').val(data.responseJSON.rectarget);
	    			$('#start').val(formatDate(data.responseJSON.startdate));
	    			$(function() {
	    			  $('[name=periodic] option').filter(function() { 
	    			        return ($(this).text() == data.responseJSON.periodic); //To select Qualification
	    			    }).prop('selected', true);
	    			});
	    			
	            }
		  })
		  
		  
	  }
	  
	
	 function Validate(data) {
	        
         var pattern = /^\d{5}$/;
         if (pattern.test(data)) {
        	 $('#error').text("Invalid Target");
             return 
         }
         }
	 function formatDate(date) {
	        var d = new Date(date),
	            month = '' + (d.getMonth() + 1),
	            day = '' + d.getDate(),
	            year = d.getFullYear();

	        if (month.length < 2) month = '0' + month;
	        if (day.length < 2) day = '0' + day;

	        return [month, day, year].join('-');
	    }
	 function changeDate(date) {
	        var d = new Date(date),
	            month = '' + (d.getMonth() + 1),
	            day = '' + d.getDate(),
	            year = d.getFullYear();

	        if (month.length < 2) month = '0' + month;
	        if (day.length < 2) day = '0' + day;

	        return [year, month, day].join('-');
	    }
	   $("#start").datepicker( {
			changeMonth: true,
			changeYear: true,
			 dateFormat : "mm-dd-yy",
			
		});
	   
	   $('#role').change(function(){
		   $('#error').text('');
			$('#success').text('');
			$('#error1').text('');
			$('#success1').text('');
			  $('#role').prop('disabled',false);
		   if(this.value == 1){
			   edittarget();
			   $('input[type="text"], date, select').prop("disabled", true);
			   $('#role').prop('disabled',false);
			   $('#target').css('display','block');
			   $('#signature').css('display','none');
			   $('#teamname').css('display','none');
			   $('#emailtemplate').css('display','none');
		   }
		   else if((this.value == 2)){
			   editautosignature();
			   $('input[type="text"], date, select').prop("disabled", true);
			   $('#role').prop('disabled',false);
			 	 $('#checkeditsig').css('display','block');
			 	$("textarea").prop('disabled', true);
			   $('#target').css('display','none');
			   $('#signature').css('display','block');
			   $('#teamname').css('display','none');
			   $('#emailtemplate').css('display','none');
		   }
		   else if((this.value == 3)){
			   editteamname();
			   $('#role').prop('disabled',false);
			   $('#role').prop('disabled',false);
			   $('#target').css('display','none');
			   $('#signature').css('display','none');
			   $('#teamname').css('display','block');
			   $('#emailtemplate').css('display','none');
			   
		   }
		   else if((this.value == 4)){
			 
			   $('#role').prop('disabled',false);
			   $('#teamnames').prop('disabled',true);
			   $('#target').css('display','none');
			   $('#signature').css('display','none');
			   $('#teamname').css('display','none');
			   $('#emailtemplate').css('display','block');
		   }
		   
	   })
	   
	   
	   function signatures(){
		   user ={};
		   preview();
		   $('#error').text('');
			$('#success').text('');
			$('#error1').text('');
			$('#success1').text('');
			$('#name').attr('style', "");
			$('#weburl').attr('style',"");
			$('#designation').attr('style',"");
			$('#company').attr('style',"");
			$('#companyadd').attr('style',"");
			$('#weburl').attr('style',"");
   			user.autosin_name=$('#name').val();	
   			
   		
		   if($('#designation').val()=='' || $('#designation').val().trim()==''){
	   			$('#error').text(' Designation is required !');
	   			$('#error1').text('Designation is required !');
	   			$('#designation').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
	   			return;	
	   		}else{
	   			user.autosin_designation = $('#designation').val();
	   		} 
		  
		   user.autosin_contact= $('#mobile').val();
		   if($('#company').val()=='' || $('#company').val().trim()==''){
	   			$('#error').text('Company is required !');
	   			$('#error1').text('Company is required !');
	   			$('#company').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
	   			return;	
	   		}else{
	   			user.autosin_companyname = $('#company').val();
	   		}
		   
		   
		   if($('#companyadd').val()=='' || $('#companyadd').val().trim()==''){
	   			$('#error').text('Address is required !');
	   			$('#error1').text('Address  is required !');
	   			$('#companyadd').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
	   			return;	
	   		}else{
	   			user.address= $('#companyadd').val();
	   		}
		   if($('#weburl').val()=='' || $('#weburl').val().trim()==''){
	   			$('#error').text('Company URL is required !');
	   			$('#error1').text('Company URL  is required !');
	   			$('#weburl').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
	   			return;	
	   		}else{
	   			user.autosin_companyurl = $('#weburl').val() ;
	   		}
		   		var strhtml = $('#theme-1').html();
		   		user.sightml = strhtml.trim();
		   	
		   		var jsonfile={auto_signature:JSON.stringify(user)};
		   		console.log(jsonfile);
		   	  $('#loading').show(); 
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/autoSignature",
	            type: 'POST',
	            dataType: "json",
	            data: jsonfile,
	            complete:function(data){
	            	 $('#loading').hide(); 
	    			console.log(data);
	    			 $('#loading').hide();  
	    			if(data.responseText == 'success'){
	    				 editautosignature();
	    				   $('input[type="text"], date, select').prop("disabled", true);
	    				   $('#role').prop('disabled',false);
	    				   $("textarea").prop('disabled', true);
	    				   $('#sigregs').css('display','none');
	    		   	   	 	$('#sigregister').css('display','block');
	    				$('#success').text('Signature added successfully');
	    				$('#error').text('');
	    				$('#success1').text('Signature added successfully');
	    				$('#error1').text('');
	    				
	    			}
	    			else if(data.responseText == 'fail'){
	    				$('#error').text('Signature added fail');
	    				$('#success').text('');
	    				$('#error1').text('Signature added fail');
	    				$('#success1').text('');
	    			}
	            }
	        })
	   }
	   function editautosignature(){
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/existAutoSign",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	            console.log(data);
	           if(data.responseJSON.sightml == null ||data.responseJSON.sightml ==  ""||$.trim(data.responseJSON.sightml) =="" ){
	        	   
	           }
	           else{
	            $( "div" ).remove( ".default_signature" );
	            
	            $(".default_signature").remove();
	           }
	            $('#theme-1').css('display','block');
	            console.log(data.responseJSON.sightml);
	            $('#theme-1').append(data.responseJSON.sightml);
	        /*    var desig = $('#designation').val();
			var companyname = $('#company').val(); 
	   		var companyurl = $('#weburl').val() ;
	   		var companyadd = $('#companyadd').val();*/
			
	   		$('#designation').val($('#sigdest').text());
	   	 	$('#company').val($('#sigcompany').text());
	   		$('#companyadd').val($('#sigaddress').text());
			 $('#weburl').val($('#sigurl').text());
			
			
			//$("#sigurl").attr("href", "http://"+companyurl+"/")
	           
	            }
		   })
	   }
	   
	   
	   function teamnamechange(){
			
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/manager/changeTeamname",
	            type: 'POST',
	            dataType: "json",
	            data: {teamname:$('#teamnames').val()},
	            complete:function(data){
	            	 $('#loading').hide(); 
	    			console.log(data);
	    			if(data.responseText == 'success'){
	    				$('#success').text('Teamname updated successfully');
	    				$('#error').text('');
	    				 $('#registerteam').css('display','block');
	    			   	 $('#teamnames').prop('disabled',true);
	    				 $('#regteam').css('display','none');
	    			   	 
	    				
	    			}
	    			else if(data.responseText == 'fail'){
	    				$('#error').text('Teamname updated fail');
	    				$('#success').text('');
	    			}
	    			
	            }
	        })
	   }
	   
	   function editteamname(){
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/manager/existTemaname",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	            	console.log(data);
	            	$('#teamnames').val(data.responseText);
	            
	            }
		   })  
	   }
	   function preview(){
		   $('.preview').css('display','block');
		   //name style
		   if($('#namestyle').val() == 'bold'){
			   $('#signame').css('text-decoration','none')
			   $('#signame').css('font-weight', 'bold');
			   $('#signame').css('font-style', 'none');
		   }
		   else if($('#namestyle').val() == 'italic'){
			   $('#signame').css('text-decoration','none')
			   $('#signame').css('font-style', 'italic');
		   }
		   else{
			   $('#signame').css('text-decoration', 'underline');
			   $('#signame').css('font-style', 'none');
			   $('#signame').css('font-weight', 'none');
		   }
		   
		   //designation style
 			if($('#desigstyle').val() == 'bold'){
 				 $('#sigdest').css('text-decoration','none');
			   $('#sigdest').css('font-weight', 'bold');
			   $('#sigdest').css('font-style', 'none');
		   }
		   else if($('#desigstyle').val() == 'italic'){
			   $('#sigdest').css('text-decoration','none');
			   $('#sigdest').css('font-weight', 'none');
			   $('#sigdest').css('font-style', 'italic');
		   }
		   else{
			   $('#sigdest').css('text-decoration', 'underline');
			   $('#sigdest').css('font-weight', 'none');
			   $('#sigdest').css('font-style', 'none');
		   }
		   
		   //mobile style
 			if($('#mobilestyle').val() == 'bold'){
 				 $('#sigmobile').css('text-decoration','none');
 			   	 $('#sigmobile').css('font-weight', 'bold');
 			     $('#sigmobile').css('font-style', 'none');
 		   }
 		   else if($('#mobilestyle').val() == 'italic'){
 			   $('#sigmobile').css('text-decoration','none');
 			   $('#sigmobile').css('font-style', 'italic');
 			  $('#sigmobile').css('font-weight', 'none');
 		   }
 		   else{
 			   $('#sigmobile').css('text-decoration', 'underline');
 			   $('#sigmobile').css('font-weight', 'none');
			   $('#sigmobile').css('font-style', 'none');
 		   }
		   //email style
 			if($('#emailstyle').val() == 'bold'){
 				$('#sigemail').css('text-decoration','none');
  			    $('#sigemail').css('font-weight', 'bold');
  			    $('#sigemail').css('font-style', 'none');
  		   }
  		   else if($('#emailstyle').val() == 'italic'){
  			   $('#sigemail').css('text-decoration','none');
  			   $('#sigemail').css('font-style', 'italic');
  			   $('#sigemail').css('font-weight', 'none');
  		   }
  		   else{
  			   $('#sigemail').css('text-decoration', 'underline');
  			   $('#sigemail').css('font-style', 'none');
			   $('#sigemail').css('font-weight', 'none');
  		   }
		   //company style
 			if($('#companystyle').val() == 'bold'){
 				$('#sigcompany').css('text-decoration','none');
   			    $('#sigcompany').css('font-weight', 'bold');
   			    $('#sigcompany').css('font-style', 'none');
   		   }
   		   else if($('#companystyle').val() == 'italic'){
   				$('#sigcompany').css('text-decoration','none');
   			    $('#sigcompany').css('font-style', 'italic');
   			    $('#sigcompany').css('font-weight', 'none');
   		   }
   		   else{
   			   $('#sigcompany').css('text-decoration', 'underline');
   			   $('#sigcompany').css('font-style', 'none');
			   $('#sigcompany').css('font-weight', 'none');
   		   }
		   //address style
 			if($('#addrstyle').val() == 'bold'){
 				  $('#sigaddress').css('text-decoration','none');
    			  $('#sigaddress').css('font-weight', 'bold');
    			  $('#sigaddress').css('font-style', 'none');
    		   }
    		   else if($('#addrstyle').val() == 'italic'){
    			   $('#sigaddress').css('text-decoration','none');
    			   $('#sigaddress').css('font-style', 'italic');
    			   $('#sigaddress').css('font-weight', 'none');
    		   }
    		   else{
    			   $('#sigaddress').css('text-decoration', 'underline');
    			   $('#sigaddress').css('font-style', 'none');
    			   $('#sigaddress').css('font-weight', 'none');
    		   }
		   //url style
 			if($('#urlstyle').val() == 'bold'){
 				 $('#sigurl').css('text-decoration','none');
 			   $('#sigurl').css('font-weight', 'bold');
 			  $('#sigurl').css('font-style', 'none');
 		   }
 		   else if($('#urlstyle').val() == 'italic'){
 			  	$('#sigurl').css('text-decoration','none');
 			   $('#sigurl').css('font-style', 'italic');
 			  $('#sigurl').css('font-weight', 'none');
 		   }
 		   else{
 			   $('#sigurl').css('text-decoration', 'underline');
 			   $('#sigurl').css('font-style', 'none');
			   $('#sigurl').css('font-weight', 'none');
 		   }
		   var name =   $('#name').val();	
		 	var mobile =  	$('#mobile').val();
		 	var email = 	$('#email').val();
			var desig = $('#designation').val();
			var companyname = $('#company').val(); 
	   		var companyurl = $('#weburl').val() ;
	   		var companyadd = $('#companyadd').val();
			$('#signame').text(name);
			$('#sigdest').text(desig);
			$('#sigmobile').text(mobile);
			$('#sigemail').text(email);
			$('#sigcompany').text(companyname);
			$('#sigaddress').text(companyadd);
			$('#sigurl').text(companyurl);
			$("#sigurl").attr("href", "http://"+companyurl+"/")
			var html = $('#theme-1').html();
			console.log(html);
	   }
	   
	   
	   //Mobile Validation
	      function Validate(data) {
	          
	          var pattern = /^\d{10}$/;
	          if (pattern.test(data)) {
	         	
	              return data;
	          }
	      }
	   function checkeditteamname(){
		   $('#success').text('');
    		$('#error').text('');

   	 $('#regteam').css('display','block');
   	 $('#registerteam').css('display','none');
   	 $('#teamnames').prop('disabled',false);
   	
   	 
	   }
	   function checkeditsig(){
		   $('#success').text('');
   			$('#error').text('');
   			$('#success1').text('');
   			$('#error1').text('');
   		 	$('input[type="text"], date, select').prop("disabled", false); 
   		 	$('#sigregs').css('display','block');
   	   	 	$('#sigregister').css('display','none');
   	  		$("textarea").prop('disabled', false);
   			
	   }
	   function targetedit(){
		   $('#success').text('');
  			$('#error').text('');
  			$('#success1').text('');
  			$('#error1').text('');
  		 	$('input[type="text"], date, select').prop("disabled", false); 
  		 	$('#editreg').css('display','none');
  	   	 	$('#regedit').css('display','block');  
	   }
	   
	   function testmail(){
		   $('#loading').show(); 
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/sendAutoSignature",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	            	 $('#loading').hide(); 
	    			console.log(data);
	    			if(data.responseText == 'success'){
	    				$('#success').text('Email sent successfully');
	    				$('#success1').text('Email sent successfully');
	    				$('#error').text('');
	    			
	    			}
	    			else if(data.responseText == 'fail'){
	    				$('#error').text('Email sent fail');
	    				$('#success').text('');
	    			}
	    			
	            }
	        })
	   }
	   
	   
	   function updatetemplate(data){
		  
		   $('#loading').show(); 
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/updatetemplateid",
	            type: 'POST',
	            dataType: "json",
	            data:{templatedesignid:data},
	            complete:function(data){
	            	 $('#loading').hide(); 
	    			console.log(data);
	    			if(data.responseText == 'success'){
	    				$('#success').text('Updated successfully');
	    				 checktemplate();
	    				$('#error').text('');
	    			
	    			}
	    			else if(data.responseText == 'fail'){
	    				$('#error').text('Update failed');
	    				$('#success').text('');
	    			}
	    			
	            }
	        })
		   
		   
	   }
	</script>
