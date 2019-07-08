
<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>

<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/manager/managerdashboard">Dashboard</a>
			</li>
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/manager/register">Registration</a>
			</li>
			<li class="breadcrumb-item active">New Account</li>
		</ol>

		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h3 align="center">New Account</h3>
			</div>
			<div class="card-body">

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
						<label for="email">Email</label> <input class="form-control"
							type="email" name="email" id="email" placeholder="Enter Email">
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="password">Password</label> <input
									class="form-control" type="password" name="password"
									id="password" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;">
							</div>
							<div class="col-md-6">
								<label for="password">Confirm Password</label> <input
									class="form-control" type="password" name="password"
									id="confirmpassword"
									placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;"> <span
									id='message'></span>

							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">

							<div class="col-md-6">
								<label class="col-xs-3 control-label">Mobile</label> <input
									type="text" class="form-control" class="form-control"
									name="phoneNumber" id="mobile" />


								<!--  <label for="mobile">Mobile</label>
                <input class="form-control" type="text" name="mobile" id="mobile"  pattern="\d{3}[\-]\d{3}[\-]\d{4}" required />
              	<span id="message1"></span>-->
							</div>
							<div class="col-md-6">
								<label for="dob" class="formlabel">Date of Birth</label> <input
									type="text" class="form-control" id="dob" name="dob"
									placeholder="mm-dd-yyyy">

							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">

							<div class="col-md-6">
								<label for="exampleQualification">Qualification</label> <select
									id="qual" name="qual" class="form-control" required>

									<option value="">Select</option>
									<option value="bca">High School</option>
									<option value="bcom">College</option>
									<option value="bsc">Graduate School</option>
									<option value="ba">Professional</option>

								</select>
							</div>
							<div class="col-md-6">
								<label for="veterans">Veterans</label> <select id="veterans"
									name="veterans" class="form-control">
									<option value="">Select</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
									<option value="N/A">N/A</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="addr1">Address</label> <input class="form-control"
							type="text" name="addr1" id="addr1" placeholder="Enter Address"
							required />
					</div>
					<div class="form-group">
						<label for="location">Country</label> 
						   <select  name="location" id="location" class="form-control select2-single">
							<option>Select Country</option>
					</select>  
					</div>
				<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="state">State</label> <select
									class="form-control select2-single" name="state"
									id="state">
									<option>Select State</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="password">City</label> <select
									class="form-control select2-single" name="city"
									id="city">
									<option>Select City</option>
								</select>

							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="addr2">Zip Code</label> <input class="form-control"
									type="text" name="zipcode" id="zipcode"
									placeholder="Enter Zip Code" required />
							</div>
							<div class="col-md-6">
								<label for="role">Role</label> <select id="role" name="role"
									class="form-control" required>
									<option value="">Select</option>
									<option value="1">Manager</option>
									<option value="2">Recruiter</option>

								</select>

							</div>
						</div>
					</div>
					<br>
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
	var phonecode;
	//areYouReallySure=true;
	function showDiv() {
   document.getElementById('welcomeDiv').style.display = "block";
}
	var inputChanged = false;
	//Form Registration Ajax Calling with validation
 var formatmobile;
      function checkAjaxCall(){
    	  var user ={};
    	  $('#firstname').attr('style', "");
    	  $('#lastname').attr('style',"");
    	  $('#email').attr('style',"");
    	  $('#password').attr('style',"");
    	  $('#confirmpassword').attr('style',"");
    	  $('#mobile').attr('style',"");
    	  $('#dob').attr('style',"");
    	  $('#qual').attr('style',"");
    	  $('#location').attr('style',"");
    	  $('#veterans').attr('style',"");
    	  $('#addr1').attr('style',"");
    	  $('#zipcode').attr('style',"");
    	  $('#role').attr('style',"");
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
    		if($('#confirmpassword').val()=='' || $('#confirmpassword').val().trim()==''){
    			$('#error').text('Please Retype Password !');
    			$('#error1').text('Please Retype Password !');
    			$('#confirmpassword').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			var repass = $('#confirmpassword').val();
    			if(user.password!=repass){
    				$('#error').text('Password and Re-Type should be same !');
    				$('#error1').text('Password and Re-Type should be same !');
    				$('#confirmpassword').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    				$('#password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    				return;
    			}
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
          
    		if($('#dob').val()=='' || $('#dob').val().trim()==''){
    			$('#error').text('Date of Birth is required !');
    			$('#error1').text('Date of Birth is required !');
    			$('#dob').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			var date =	formatDate($('#dob').val())
    			user.dob =date;	
    		}
    		if($( "#qual option:selected" ).text()=='Select' || $( "#qual option:selected" ).text().trim()=='Select'){
    			$('#error').text('Qualification is required !');
    			$('#error1').text('Qualification is required !');
    			$('#qual').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			user.qualification =$( "#qual option:selected" ).text();		
    		}
    		if($( "#veterans option:selected" ).text()=='Select' || $( "#veterans option:selected" ).text().trim()=='Select'){
    			$('#error').text('Veterans is required !');
    			$('#error1').text('Veterans is required !');
    			$('#veterans').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			user.veterans =$( "#veterans option:selected" ).text();		
    		
    		}
    		if($('#addr1').val()=='' || $('#addr1').val().trim()==''){
    			$('#error').text('Address is required !');
    			$('#addr1').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			user.addrline1 =$('#addr1').val();
    		}
    		if($('#location').text()=='Select Country' || $('#location').text().trim()=='Select Country'){
    			$('#error').text('Country is required !');
    			$('#location').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			
    		}
    	if($('#state').text()=='Select State' || $('#state').text().trim()=='Select State'){
    		$('#error').text('State is required !');
    		$('#state').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    		return;	
    	}else{
    			
    	}
    	if($('#city').text()=='Select City' || $('#city').text().trim()=='Select City'){
    		$('#error').text('City is required !');
    		$('#city').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    		return;	
    	}else{
    				
    	}
    	var array = [$('#city option:selected').text(), $('#state option:selected').text(), $('#location option:selected').text()];
    	
    	console.log(array);
    	user.city = array.join(', ');
    		
    		if($('#zipcode').val()=='' || $('#zipcode').val().trim()=='' ||zipcode($('#zipcode').val())){
    			$('#error').text('Zipcode is required !');
    			$('#zipcode').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			var m = $('#zipcode').val();
    			if(m.length!=5){
    				$('#error').text('Valid Zip Code is required !!');	
    				$('#error1').text('Valid Zip Code is required !!');
    				$('#zipcode').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    				return;
    			}
    			user.pincode =$('#zipcode').val();		
    		}
    		
    		
    		if($( "#role option:selected" ).text()=='Select' || $( "#role option:selected" ).text().trim()=='Select'){
    			$('#error').text('Role is required !');
    			$('#role').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			user.roleid =$('#role').val();		
    		}
    		
          
          $('#error').text('');
          $('#error1').text('');
      
      	var jsonfile={userInfo:JSON.stringify(user)}; 
          console.log(jsonfile);
    $('#loading').show();   
    $.ajax({
       url: "${pageContext.servletContext.contextPath}/login/save",
        type: 'POST',
        dataType: "json",
        data: jsonfile,
        complete:function(response){
        	 $('#loading').hide(); 
			console.log(response);
			if(response.responseText == "success"){
				 $('#error').text('');
				 $('#error1').text('');
				 $('#success').text('Account Created! Check email for activation link');
				 $('#success1').text('Account Created! Check email for activation link');
				
			
				$('#reg').attr("disabled", true);
			}
			else if(response.responseText == "fail"){
				 $('#error').text('Account Creation Failed');
				 $('#error1').text('Account Creation Failed');
			
			}
			else if(response.responseText == 'Email is already registered! Please choose another one'){
				$('#error').text('Email is already registered! Please choose another one');
				 $('#error1').text('Email is already registered! Please choose another one');
				 $('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				 
			}
			else if(response.responseText == 'Mobile Number is registered! Please choose another one'){
				$('#error').text('Mobile Number is registered! Please choose another one');
				 $('#error1').text('Mobile Number is registered! Please choose another one');
				 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			}
			
        }
    });
	
    return false;
}
     
      //location autocomplete
      $("#location").keyup(function(){
     	 var location = $("#location").val();
     	 $("#location").autocomplete({
     		 minLength :2,
     		 source:function(request,response){
     			 if(location.length > 1){
     				 $.ajax({
     					 type:"POST",
     					 data:{state:location,phonecode:phonecode},
     					 dataType: "json",
     					 url:"${pageContext.servletContext.contextPath}/user/getCities",
     					 complete:function(data){
     						 var values = [];
     						 console.log(data);
     						 for(var i=0;i<data.responseJSON.length;i++){
     							 var arr = [data.responseJSON[i].citiesname,data.responseJSON[i].statename,data.responseJSON[i].sortname];
     							 values.push(arr.join(', '))
     						 }
     						 response(values);
     					 } 
     				 })	 
     			 } 
     		 }	 
     	 })
  
      })
      //Date of Birth format changing
      $( "#dob" ).datepicker( {
				changeMonth: true,
				changeYear: true,
				 dateFormat : "mm-dd-yy",
				yearRange: "-100:+0",
				maxDate: "+0D"
			} );
      
      
      //Mobile Validation
      function Validate(data) {
          
          var pattern = /^\d{10}$/;
          if (pattern.test(data)) {
         	
              return 
          }
      }
      //Zip Code Validation
		function zipcode(data) {
          var pattern =  /^\d{5}$/;
          if (pattern.test(data)) {
         	
              return 
          }
      }
      
      
      //Format date changing
      function formatDate(date) {
	        var d = new Date(date),
	            month = '' + (d.getMonth() + 1),
	            day = '' + d.getDate(),
	            year = d.getFullYear();

	        if (month.length < 2) month = '0' + month;
	        if (day.length < 2) day = '0' + day;

	        return [year, month, day].join('-');
	    }
      function validateEmail(email) {
    	  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    	  return emailReg.test( email );
    	}
      
      function statecity(data){
    	  $("#location").empty();
        	  $.ajax({
        	       url: "${pageContext.servletContext.contextPath}/user/getAllCountries",
        	        type: 'POST',
        	        dataType: "json",
        	        data: {phoneCode:data},
        	        complete:function(response){
        	        	console.log(response);
        	        	 for (var i = 0; i < response.responseJSON.length; i++)
        	   	         { 
        	   	              $('#location').append($('<option>',
        	   	              {
        	   	                 value: response.responseJSON[i].id,
        	   	                 text : response.responseJSON[i].name
        	   	             }));
        	   	         }
        	        	 getcities();
        	        }
        	      
        	        })
          }
    
 	 function getcities(){
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
		 getcities();
	 })

 	 $('#state').on('change', function() {
 		 var stateid = $('#state').val();
 		$("#city").empty();
 		  $.ajax({
       	       url: "${pageContext.servletContext.contextPath}/user/getAllCities",
       	        type: 'POST',
       	        dataType: "json",
       	        data: {state_id:stateid},
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
 	 })
 	 
 	  $('#city').select2();
 	 $('#state').select2();
 	 
 	
 //Map with flag with intl-input
$(document).ready(function() {
	//start();
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
		statecity(countrydata.dialCode);
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
	
    window.onbeforeunload = function (e) { 
    	
    	
    	/* if($('#firstname').val()=='' && $('#lastname').val()=='' && $('#email').val()=='' && $('#password').val()=='' && $('#mobile').val()=='' && $('#dob').val()=='' && $( "#qual option:selected" ).text()=='Select' && $( "#veterans option:selected" ).text()=='Select'  && $('#addr1').val()=='' && $('#location').text()=='Select Country' && $('#state').text()=='Select State' && $('#city').text()=='Select City' && $("#role option:selected").text()=='Select'  && $('#zipcode').val() == '' )  {
 			alert('hello1');
 		  
 	      if( ($('#firstname').val().trim()=='') && ( $('#lastname').val().trim()=='' ) && ($('#email').val().trim()=='' ) &&( $('#password').val().trim()=='') && ( $('#mobile').val().trim()=='') && ( $('#dob').val().trim()=='') && ( $( "#qual option:selected" ).text().trim()=='Select') && ( $( "#veterans option:selected" ).text().trim()=='Select') && ( $('#addr1').val().trim()=='') && ( $('#location').text().trim()=='Select Country') && ( $('#state').text().trim()=='Select State') && ($('#city').text().trim()=='Select City') && ( $( "#role option:selected" ).text().trim()=='Select') && ($('#zipcode').val().trim()=='') )  {
 	    	  alert('hello2');
 		   
 	    }
 	   }
 	   else{
 			  
 			 inputChanged=true;		
 		 } */
 	
	 
	   if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
		
		  }else{
			 inputChanged=true;		
		 }
	  if($('#lastname').val()=='' || $('#lastname').val().trim()=='' ){
		  
		}else{
			inputChanged=true;		
		}
	  if($('#email').val()=='' || $('#email').val().trim()=='' ){
		
	  
		}else{
			inputChanged=true;		
		}
	  if($('#password').val()=='' || $('#password').val().trim()==''){
		 
		}else{
			inputChanged=true;			
		}
		
		if($('#mobile').val()=='' || $('#mobile').val().trim()==''){
			
		}else{
			inputChanged=true;	
		}
      
		if($('#dob').val()=='' || $('#dob').val().trim()==''){
			
		}else{
			inputChanged=true;	
		}
		if($( "#qual option:selected" ).text()=='Select' || $( "#qual option:selected" ).text().trim()=='Select'){
		
		}else{
			inputChanged=true;			
		}
		if($( "#veterans option:selected" ).text()=='Select' || $( "#veterans option:selected" ).text().trim()=='Select'){
			
		}else{
			inputChanged=true;			
		
		}
		if($('#addr1').val()=='' || $('#addr1').val().trim()==''){
				
		}else{
			inputChanged=true;
		}
		if($('#location').text()=='Select Country' || $('#location').text().trim()=='Select Country'){
			
		}else{
			inputChanged=true;
		}
	if($('#state').text()=='Select State' || $('#state').text().trim()=='Select State'){
		
	}else{
		inputChanged=true;
	}
	if($('#city').text()=='Select City' || $('#city').text().trim()=='Select City'){
		
	}else{
		inputChanged=true;		
	}
	
		if($('#zipcode').val()=='' || $('#zipcode').val().trim()=='' ||zipcode($('#zipcode').val())){
			
		}else{
			inputChanged=true;
			}
			
		
		if($( "#role option:selected" ).text()=='Select' || $( "#role option:selected" ).text().trim()=='Select'){
			
		}else{
			inputChanged=true;		
		} 
		
	  if(inputChanged) {
		  inputChanged =false;
	    var message = "Are you sure you want leave?";
	    e.returnValue = message;
	    return message;
	  }
	  return null;
	}; 
	

	
	
	
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
.select2-container--default .select2-selection--single {
    background-color: #fff;
    border: 1px solid rgba(0,0,0,.15);
    border-radius: 4px;
}
</style>
</body>

</html>
