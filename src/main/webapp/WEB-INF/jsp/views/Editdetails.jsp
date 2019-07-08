<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>

<!-- Navigation End -->

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
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/editdetails">Profile</a>
			</li>
		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h1 align="center">Profile</h1>
			</div>
			<div class="card-body">

				<!-- Loading  -->
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<!-- Loading End -->

				<!-- Error  -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;"></span> <span id='error'
							style="color: red; margin-left: auto; margin-right: auto;"></span>
					</h5>
				</div>

				<!-- Error End -->

				<form id="profileedit" name="registration" action="/register">

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
							<label for="email">Email</label> <input class="form-control"
								type="text" name="email" id="email" disabled>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">

								<label class="col-xs-3 control-label">Mobile</label> <input
									type="text" class="form-control" class="form-control"
									name="phoneNumber" id="mobile" /> <span id="message1"></span>
							</div>
							<div class="col-md-6">
								<label for="dob" class="formlabel">Date of Birth</label> <input
									type="text" class="form-control" id="dob" name="dob" disabled>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="exampleQualification">Qualification</label> <select
									id="qual" name="qual" class="form-control" required>
									<option value="select" selected>Select</option>
									<option value="bca">High School</option>
									<option value="bcom">College</option>
									<option value="bsc">Graduate School</option>
									<option value="ba">Professional</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="addr1">Address</label> <input class="form-control"
									type="text" name="addr1" id="addr1" placeholder="Enter Address"
									required />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="location">Country</label> <select name="location"
									id="location" class="form-control select2-single">

								</select>
							</div>
							<div class="col-md-6">
								<label for="state">State</label> <select
									class="form-control select2-single" name="state" id="state">

								</select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="cities">City</label> <select
									class="form-control select2-single" name="cities" id="city">

								</select>

							</div>
							<div class="col-md-6">
								<label for="addr2">Zip Code</label> <input class="form-control"
									type="text" name="zipcode" id="zipcode"
									placeholder="Enter Zip Code" required />
							</div>
						</div>
					</div>
					<!-- Button Submit -->
					<div class="text-center">
						<button type="button" class="btn btn-success" id="register"
							onclick="checkAjaxCall();"
							style="display: none; margin-left: auto; margin-right: auto;">Update</button>
						<button type="button" class="btn btn-success" id="reg"
							onclick="checkedit();"
							style="margin-left: auto; margin-right: auto;">Edit</button>
					</div>

					<!-- Button Submit End -->
				</form>
			</div>
		</div>
		<!--   footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>
</div>



<!-- Custom scripts for this page-->
<script>
	var userid;
	  $('#success').text('');
 		$('#error').text('');
 		 var country;
	     var state ;
	     var city ;
	     var contactno;
	     var flag1 = false;
		 var flag2 = false;
		 var flag3 = false
	$( document ).ready(function() {
		$("#profileedit input").prop("disabled", true);
		 $('input[type="text"], date, select').prop("disabled", true); 
		     $.ajax({
		          url: "${pageContext.servletContext.contextPath}/user/getUserInfo",
		          type: 'POST',
		          dataType: "json",
		         success:function(response){
		      	console.log(response);  
			     userid = response.userid;
			     firstname = response.FirstName;
			     contactno = response.contactno;
			     //country(contactno.substr(1,contactno.indexOf(' ')))
			     getdata(contactno.substr(1,contactno.indexOf(' ')));
			     
       		    		
       	        	 
			     $('#firstname').val(response.firstname); //firstname
			     $('#lastname').val(response.lastname); //lastname
			     $("#mobile").intlTelInput("setNumber", response.contactno); //contact number
			    // $('#mobile').val(response.contactno); 
			     $('#dob').val(formatDate(response.dob)); //date of bith
			     $('#addr1').val(response.addrline1); //address
			     $('#zipcode').val(response.pincode); //pincode
			     
			     // city, country,state
			      var array = response.city;
			      var res = array.split(",");
			      country = res[2];
			      state = res[1];
			      city = res[0];
		         //Ending
		       
		   $(function() {	
    		$('[name=qual] option').filter(function() { 
    			
        	return ($(this).text() == response.qualification); //To select Qualification
    		}).prop('selected', true);
			})
		         
		     $('#email').val(response.email);
		     userid=response.userid;
		      },
		      error:function(){
		       $('#error').text("Server Problem! Please try after some time!");
		       
		      }
		   
		          })
		  });
		     
		     
		 
	
	 function formatDate(date) {
	        var d = new Date(date),
	            month = '' + (d.getMonth() + 1),
	            day = '' + d.getDate(),
	            year = d.getFullYear();

	        if (month.length < 2) month = '0' + month;
	        if (day.length < 2) day = '0' + day;

	        return [month, day, year].join('-');
	    }
	
	
	
   
    $("#location").keyup(function(){
    	 var location = $("#location").val();
    	 $("#location").autocomplete({
    		 minLength :2,
    		 source:function(request,response){
    			 if(location.length > 1){
    				 $.ajax({
    					 type:"POST",
    					 data:{city:location},
    					 dataType: "json",
    					 url:"${pageContext.servletContext.contextPath}/user/getCities",
    					 complete:function(data){
    						 var values = [];
    						 console.log(data);
    						 for(var i=0;i<data.responseJSON.length;i++){
    							 values.push(data.responseJSON[i].city_state)
    						 }
    						 response(values);
    					 } 
    				 })	 
    			 } 
    		 }	 
    	 })
 
     })
     $( "#dob" ).datepicker( {
				changeMonth: true,
				changeYear: true,
				 dateFormat : "yy-mm-dd",
				yearRange: "-100:+0",
				maxDate: "+0D"
			} );
     
     function checkAjaxCall(){
    	  $('#firstname').attr('style', "");
	   	  $('#lastname').attr('style',"");
	   	  $('#email').attr('style',"");
	   	  $('#password').attr('style',"");
	   	  $('#confirmpassword').attr('style',"");
	   	  $('#mobile').attr('style',"");
	   	  $('#dob').attr('style',"");
	   	  $('#qual').attr('style',"");
	   	  $('#veterans').attr('style',"");
	   	  $('#addr1').attr('style',"");
	   	  $('#zipcode').attr('style',"");
	   	  $('#success').text('');
 		  $('#error').text('');
 		  $('#location').attr('style',"");
 		  $('#state').attr('style',"");
 		  $('#city').attr('style',"");
    	 var user ={};
   	  //validation Starts
   	  if($('#firstname').val()=='' || $('#firstname').val().trim()=='' ){
   			$('#error').text('First Name is required !');
   			return;	
   		}else{
   			user.firstname =$('#firstname').val();		
   		}
   	  if($('#lastname').val()=='' || $('#lastname').val().trim()==''){
 			$('#error').text('Last Name is required !');
 			return;	
 		}else{
 			user.lastname =$('#lastname').val();		
 		}
   	    		if($('#mobile').val()=='' || $('#mobile').val().trim()=='' ||Validate($('#mobile').val())){
   			$('#error').text('Mobile Number is required !');
   			$('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			
   			
   			user.contactno =$('#mobile').val();	
   			
   		}
         
   		
   		if($( "#qual option:selected" ).text()=='Select' || $( "#qual option:selected" ).text().trim()=='Select'){
   			$('#error').text('Qualification is required !');
   			return;	
   		}else{
   			user.qualification =$( "#qual option:selected" ).text();		
   		}
   		
   		if($('#addr1').val()=='' || $('#addr1').val().trim()==''){
   			$('#error').text('Address is required !');
   			return;	
   		}else{
   			user.addrline1 =$('#addr1').val();
   		}
   		if($('#location').text()=='' || $('#location').text().trim()==''){
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
   		
   		if($('#zipcode').val()=='' || $('#zipcode').val().trim()==''){
   			$('#error').text('Zipcode is required !');
   			return;	
   		}else{
   			user.pincode =$('#zipcode').val();		
   		}
   		user.userid=userid;
   		
   		//validation ending
        $('#success').text('');
   		$('#error').text('');
     	var jsonfile={user:JSON.stringify(user)};  //convert user to json format
         
   $('#loading').show();   //lading starts
      //ajax calling for update   
   $.ajax({
      url: "${pageContext.servletContext.contextPath}/user/updateUserInfo",
       type: 'POST',
       dataType: "json",
       data: jsonfile,
       complete:function(response){
       	 $('#loading').hide();  //loading hide
			
			if(response.responseText == "success"){
				 $('input[type="text"], date, select').prop("disabled", true); 
				 $("#reg").css("display", "block");
				 $('#register').css("display","none");
				 $('#success').text('Profile Updated Successfully!')
			}
			else if(response.responseText == "fail"){
				$('#success').text('');
				$('#error').text('Profile Updated Failed!');
			}
			else if(response.responseText == 'Mobile Number is registered! Please choose another one'){
				$('#error').text('Mobile Number is registered! Please choose another one');
				 $('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			}
			else{
				$('#success').text('');
				$('#error').text('Internal Server Error!');
			}
       }
   });
	
   return false;
}
     //Enable the fields
     function checkedit(){
    	  $('#success').text('');
     		$('#error').text('');
    	 $('input[type="text"], date, select').prop("disabled", false); 
    	 $('#email').prop('disabled',true);
    	 $('#dob').prop('disabled',true);
    	 $("#reg").css("display", "none");
    	 $("#register").css("display", "block");
    	 var number =  $('input[name="phoneNumber"]').val();
	     var classf = $(".selected-flag > div").attr("class");
	     var flag = classf.slice(-2);
	     var formattedNumber = intlTelInputUtils.formatNumber(number, flag, intlTelInputUtils.numberFormat.INTERNATIONAL);
	     $('input[name="phoneNumber"]').val(formattedNumber);
     }
    //Mobile Validation
     function Validate(data) {
        
         var pattern = /^\d{10}$/;
         if (pattern.test(data)) {
        	
             return 
         }
     }
    
     function country(data){
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
       	        	
       	        	getstate(); 
       	        	 
       	        }
       	      
       	        })
       	      
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
	 
	 
    
     $(document).ready(function() {
     $('#profileedit')
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
    	 $("#location").empty();
      	  $.ajax({
      	       url: "${pageContext.servletContext.contextPath}/user/getAllCountries",
      	        type: 'POST',
      	        dataType: "json",
      	        data: {phoneCode:countrydata.dialCode},
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
      	        	 getstate();
      	        }
      	        })
        
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
     
		function getdata(data){
			var flag = false;
			
			if(!flag){
				// Countries calling based on phone code
			 $.ajax({
	       	       url: "${pageContext.servletContext.contextPath}/user/getAllCountries",
	       	        type: 'POST',
	       	        dataType: "json",
	       	        data: {phoneCode:data},
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
	       	        	 if(flag){
	       	          flag = false;
	       	          $(function() {	
	 			      $('[name=location] option').filter(function() { 
	  		    			if($(this).text() ==  $.trim(country)){
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
	  		    		  		    			if($(this).text() ==  $.trim(state)){
	  		    		  		    			// city calling based on selected state id 
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
	  		    		  		        	        		$('[name=cities] option').filter(function() { 
	  		    		  		        	        			
	  		    		  		        	            	return ($(this).text() ==  $.trim(city)); //To select city
	  		    		  		        	        		}).prop('selected', true);
	  		    		  		        	    			})
	  		    		  		        	        	 }
	  		    		  		        	        }
	  		    		  		  		  })
	  		    		  		    			}
	  		    			  		        	return ($(this).text() ==  $.trim(state)); //To select state
	  		    			  		    		}).prop('selected', true);
	  		    			  					}) 
	  		    		       	        	 }
	  		    	       	        }
	  		    			  })
	  		    			}
	  		        	return ($(this).text() ==  $.trim(country)); //To select country
	  		    		}).prop('selected', true);
	  					})
	       	        	 }
	       	       }
	       	        })
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

.select2-container--default .select2-selection--single {
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, .15);
	border-radius: 4px;
}
</style>
</body>
</html>