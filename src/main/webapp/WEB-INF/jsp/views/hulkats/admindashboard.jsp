<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/admin/adminregistration">Dashboard</a>
			</li>
			<li class="breadcrumb-item active">My Dashboard</li>
		</ol>
		<!-- Icon Cards-->
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h3 align="center">New Account</h3>
			</div>
			<div class="card-body">

				<!-- Loading Starts -->
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="../images/ajax-loader.gif" id="img" />
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

				<form id="registration" name="registration" action="/register">

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="firstname">First Name</label> <input
									class="form-control" type="text" name="firstname"
									id="firstname" placeholder="Enter First Name">
							</div>
							<div class="col-md-6">
								<label for="lastname">Last Name</label> <input
									class="form-control" type="text" name="lastname" id="lastname"
									placeholder="Enter Last Name">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="firstname">Organization Name</label> <input
									class="form-control" type="text" name="firstname"
									id="organization" placeholder="Enter Organization Name">
							</div>
							<div class="col-md-6">
								<label for="email">Email</label> <input class="form-control"
									type="email" name="email" id="email" placeholder="Enter Email">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">

							<label for="password">Password</label> <input
								title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"
								type="password" id="password" class="form-control" required
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="pwd1"
								onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
  if(this.checkValidity()) form.pwd2.pattern = RegExp.escape(this.value);">

						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<label>Contact Number</label> <input type="text"
								class="form-control" name="phoneNumber" id="mobile" />
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<label>Trail Period(In days)</label> <input type="text"
								class="form-control" name="phoneNumber" id="trail" value="5" />
						</div>
					</div>
					<br> <br>
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
							<button id="reg" onclick="checkAjaxCall();" type="button"
								class="btn btn-success" style="height: 35px">Submit</button>
						</div>
					</div>

				</form>
				<br>
				<!-- Icon Cards-->

			</div>
		</div>
	</div>
</div>

<%@include file="./shared/footer.jsp"%>
<script>
		$(document).ready(function() {
			
		    $('#registration')
		        .find('[name="phoneNumber"]')
		            .intlTelInput({
		            	 utilsScript: '${pageContext.servletContext.contextPath}/resources/js/utils.js',
		                 autoPlaceholder: true,
		                 preferredCountries: ['us','fr', 'gb','in']
		               
		            });
		});


		var telInput = $("#mobile");
		        telInput.blur(function() {
			var countrydata = telInput.intlTelInput("getSelectedCountryData");
			//phonecode =countrydata.dialCode;
			  if ($.trim(telInput.val())) {
			    if (telInput.intlTelInput("isValidNumber")) {
			    	var number =  $('input[name="phoneNumber"]').val();
			    	
			    	var classf = $(".selected-flag > div").attr("class");
			    	var flag = classf.slice(-2);
			    	console.log(number);
			    	console.log(classf);
			    	console.log(flag);
			    	 var formattedNumber = intlTelInputUtils.formatNumber(number, flag, intlTelInputUtils.numberFormat.INTERNATIONAL);
			    	 
			    	$('input[name="phoneNumber"]').val(formattedNumber);
			    	 $('#mobile').attr('style','');
			     
			    } else {
			    	 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			    }
			  }
			});

		function checkAjaxCall(){
			  var user ={};
			  $('#firstname').attr('style', "");
			  $('#lastname').attr('style',"");
			  $('#email').attr('style',"");
			  $('#password').attr('style',"");
			  $('#organization').attr('style','');
			  $('#mobile').attr('style',"");
			    $('#trail').attr('style',"");
			  
			  $('#success').text('');
			  $('#error1').text('');
			  $('#error').text('');
			  if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
					$('#error').text('First Name is required !');
					$('#error1').text('First Name is required !');
					$('#firstname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
				}else{
					user.firstname =$('#firstname').val();		
				}
			  if($('#lastname').val()=='' || $('#lastname').val().trim()=='' ){
				$('#error').text('Last Name is required !');
				$('#error1').text('Last Name is required !');
				$('#lastname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;	
			}else{
				user.lastname =$('#lastname').val();		
			}
			  if($('#email').val()=='' || $('#email').val().trim()=='' ){
					$('#error').text('Email is required !');
					$('#error1').text('Email is required !');
					$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
			  }
					else if(!validateEmail($('#email').val())){
						$('#error').text('Invalid Email');
		  			$('#error1').text('Invalid Email');
		  			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");	
					return
				}else{
					user.email =$('#email').val();		
				}
			  if($('#password').val()=='' || $('#password').val().trim()==''){
					$('#error').text('Password is required !');
					$('#error1').text('Password is required !');
					$('#password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
				}else{
					user.password =$('#password').val();		
				}
			  if($('#organization').val()=='' || $('#organization').val().trim()==''){
					$('#error').text('Organization is required !');
					$('#error1').text('Organization is required !');
					$('#organization').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
				}else{
					user.organization =$('#organization').val();		
				}
				
				if($('#mobile').val()=='' || $('#mobile').val().trim()==''){
					$('#error').text('Mobile Number is required !');
					$('#error1').text('Mobile Number is required !');
					$('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
				}else{
					var telInput = $("#mobile");
					 if ($.trim(telInput.val())) {
						    if (telInput.intlTelInput("isValidNumber")) {
						    	
						    	 $('#mobile').attr('style','');
						     
						    } else {
						    	 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
						    	 return;
						    }
						  }
					user.contactno =$('#mobile').val();	
				}
				
				 if($('#trail').val()=='' || $('#trail').val().trim()==''){
					$('#error').text('Trail peroid in days is required !');
					$('#error1').text('Trail peroid in days is required !');
					$('#trail').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					return;	
				}else{
					user.trail =$('#trail').val();		
				}
		    $('#error').text('');
		    $('#error1').text('');

			var jsonfile={customerInfo:JSON.stringify(user)}; 
		    console.log(jsonfile);
		$('#loading').show();   
		$.ajax({
		 url:url+"admin/customerregistration",
		  type: 'POST',
		  dataType: "json",
		  data: jsonfile,
		  complete:function(response){
		  	 $('#loading').hide(); 
				console.log(response);
				if(response.responseJSON.status == "SUCCESS"){
					 $('#error').text('');
					 $('#error1').text('');
					 $('#success').text('Account Creation Success');
					// window.location.replace('/customerapp/login/success');
					$('#reg').attr("disabled", true);
				}
				else if(response.responseJSON.status == "FAIL"){
					 $('#error').text('Account Creation Failed');
					
				
				}
				else if(response.responseJSON.status == 'EMAIL_EXISTED'){
					$('#error').text('Email is already registered! Please choose another one');
					 $('#error1').text('Email is already registered! Please choose another one');
					 $('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					 
				}
				else if(response.responseJSON.status == 'PHONE_NUMBER_EXISTED'){
					$('#error').text('Mobile Number is registered! Please choose another one');
					 $('#error1').text('Mobile Number is registered! Please choose another one');
					 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				}
				
		  }
		});

		return false;
		}

		function validateEmail(email) {
			  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			  return emailReg.test( email );
			}
		
		
		</script>