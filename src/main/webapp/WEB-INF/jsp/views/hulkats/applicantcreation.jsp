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
				href="${pageContext.servletContext.contextPath}/user/applicants">Applicant</a>
			</li>
			<sec:authorize access="hasRole('ROLE_BASICMANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/basicmanager/dashboard">Dashboard</a>
				</li>
			</sec:authorize>

			<li class="breadcrumb-item"><a href="">Add New</a></li>

		</ol>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">
				<h2 align="center">Add New Applicant</h2>
			</div>
			<div class="card-body">
				<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<!-- Error Messages -->
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;" ></span> <span
							id='error'
							style="color: red; margin-left: auto; margin-right: auto;"
							></span> <b><span id="applicantid"
							style="color: green;" ></span></b>
					</h5>
				</div>
				<!-- Error Message Ending -->
				<form id="taskregistration" name="taskregistration">


					<label for="firstname"><strong>First Name</strong></label> <input
						class="form-control" type="text" name="firstname" id="firstname"
						placeholder="" required> <br> <label for="lastname"><strong>Last
							Name</strong></label> <input class="form-control" type="text" name="lastname"
						id="lastname" placeholder="" required> <br> <label
						for="email"><strong>Email</strong></label> <input
						class="form-control" type="text" name="email" id="email"
						placeholder="johndoe@gmail.com"> <br>
					<div class="form-group">
						<div class="form-row">

							<div class="col-md-6">
								<label class="col-xs-3 control-label"><strong>Mobile</strong></label>
								<input type="text" class="form-control" class="form-control"
									name="phoneNumber" id="mobile" />

							</div>
							<div class="col-md-6">
								<label for="address"><strong>Country</strong></label> <select
									class="form-control select2-single" name="location"
									id="location">
									<option>Select Country</option>
								</select>
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="state"><strong>State</strong></label> <select
									class="form-control select2-single" name="state"
									id="state">
									<option>Select State</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="password"><strong>City</strong></label> <select
									class="form-control select2-single" name="city"
									id="city">
									<option>Select City</option>
								</select>

							</div>
						</div>
					</div>
					<br> <label for="exampleQualification"><strong>Qualification</strong></label>
					<select id="qual" name="qual" class="form-control" required>

						<option value="">Select</option>
						<option value="bca">High School</option>
						<option value="bcom">College</option>
						<option value="bsc">Graduate School</option>
						<option value="ba">Professional</option>

					</select> <br> <label for="jobrole"><strong>Applicant Skills
							Keywords</strong></label><textarea class="form-control" name="jobrole" id="jobrole"
						placeholder="HTML5,CSS3,JAVA,JQUERY" rows="5" cols="50"
						maxlength="200"></textarea> <br>
					<div id="documents" style="display: none">
						<div id="doc1">
							<input type="checkbox" id="myCheckbox" name="myCheckbox">
							<i class="fa fa-fw fa fa-file-text-o"></i><span
								id="appdoc1"  onclick="getoriginals()"></span>
								 <a id="cross" onclick='firstdocdel();'>&#x274C;</a>
						</div>
						<br>
						<div id="doc2">
							<input type="checkbox" id="myCheckbox1" name="myCheckbox1">
							<i class="fa fa-fw fa fa-file-text-o"></i><span
								id="appdoc2"  onclick="getoriginals1()"></span>
								 <a id="cross" onclick='seconddocdel();'>&#x274C;</a>
						</div>
					</div>

					<div id="upload">
						<br>
						<div id="applicantup">
							<label for="cvupload"><strong>Upload Document</strong></label> <input
								id="cvupload" type="file" class="form-control" name="cvupload"
								required /> <input id="response" type="hidden"
								class="form-control" required /> <span
								style="margin-left: 0px; color: green;" >*Please
								Upload extension .docx or .pdf </span> <br>
						</div>
						<br>
						<div id="controls">
							<a id="save" onclick="upload();"><img
								src="<c:url value='/resources/images/add_2x.png' />"></a>
						</div>
						<div id="checkotp" style="display: none">
							<label for="cvupload"><strong>Upload Other
									Document</strong></label> <input id="cvupload1" type="file" class="form-control"
								name="cvupload" required /> <input id="response1" type="hidden"
								class="form-control" required />

						</div>
					</div>

					<!-- Error Messages -->
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success1' style="color: green;"></span> <span
								id='error1'
								style="color: red; margin-left: auto; margin-right: auto;"
								></span>
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
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</div>
<!--   footer -->

<%@include file="./shared/footer.jsp"%>

<script>
	var type1;
	var type2;
	var applicantid;
	var doc1;
	var doc2;
	 $('#myCheckbox').click(function() {
		 if ($('#myCheckbox').is(':checked')) {
			 $('#applicantup').css('display','none');
		     
		 }
		    	else{
		    		 $('#applicantup').css('display','block');
			    } 
		});
	 $('#myCheckbox1').click(function() {
		 if($('#myCheckbox1').is(':checked')){
			  
			  $('#checkotp').css('display','none');
		    }
		    	else {
		    		$("#checkotp").css("display", "block");
    	 			
			    }
		});
 function upload(){
          $("#controls").css("display", "none");
         $("#checkotp").css("display", "block");
           
    }
	 $(document).ready(function(){
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
    });
	 })
	
	function checkAjaxCall(){
		 var user ={};
		 
		 $('#firstname').attr('style', "");
		 $('#lastname').attr('style', "");
		 $('#mobile').attr('style', "");
		 $('#email').attr('style', "");
		 $('#location').attr('style', "");
		 $('#qual').attr('style', "");
		 $('#lastjob').attr('style', "");
		 $('#cvupload').attr('style', "");
		
		 
   	  
   	  if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
   			$('#error').text('First Name is required !');
   			$('#error1').text('First Name is required !');
   			$('#firstname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			user.firstname =$('#firstname').val();		
   		}
   	  if($('#lastname').val()=='' || $('#lastname').val().trim()==''){
 			$('#error').text('Last Name is required !');
 			$('#error1').text('Last Name is required !');
 			$('#lastname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
 			return;	
 		}else{
 			user.lastname =$('#lastname').val();		
 		}

 		if($('#mobile').val()=='' || $('#mobile').val().trim()==''){
 			$('#error').text('Mobile Number is required !');
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
 				
   	  if($('#email').val()=='' || $('#email').val().trim()==''){
   			$('#error').text('Email is required !');
   			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			user.email =$('#email').val();		
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
user.location = array.join(', ');
	
		
   		
   		if($( "#qual option:selected" ).text()=='Select' || $( "#qual option:selected" ).text().trim()=='Select'){
   			$('#error').text('Qualification is required !');
   			$('#qual').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			user.highesteducation =$( "#qual option:selected" ).text();		
   		}
   		
   	
   		if ($('textarea#jobrole').val() == '' || $('textarea#jobrole').val().trim() =="") {
			$('#error').text('Applicant Skills keywords is required!');
			$('#error1').text('Applicant Skills is required!');
			$('#jobrole').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
   		}else{
   			user.applicanttags =$('#jobrole').val();		
   		}
   	
   		user.uplaodcv =$('#response').val();
   		 user.otheruploads=$('#response1').val();
   		var  path =$('#cvupload').val();
		var filename = path.match(/[^\\/]*$/)[0];
		var res = filename.split(".");
		user.docname1 = res[0];
		var  path1 =$('#cvupload1').val();
		var filename1 = path1.match(/[^\\/]*$/)[0];
		var res1 = filename1.split(".");
		user.docname2 = res1[0];
		
		
		user.document1 =$('#response').val();		
		user.document2 =$('#response1').val();
   		
   		
   		user.filetype1 =type1;
   		user.filetype2=type2;
   		
   		
         $('#error').text('');
         $('#error1').text('');
     	
     	var jsonfile={applicantsCreations:JSON.stringify(user)}; 
         console.log(jsonfile);
   $('#loading').show();   
	
		  $.ajax({
        url: "${pageContext.servletContext.contextPath}/user/applicantsCreations",
        type: 'POST',
        dataType: "json",
        data: jsonfile,
        complete:function(response){
        	 $('#loading').hide(); 
			console.log(response);
			var data = response.responseText;
			var spliting = data.split(" ");
			applicantid = spliting[1];
			if("success"==spliting[0]){
				$('#reg').attr("disabled", true);
			 	 $("#reg").css("display", "none");
				 $('#register').css("display","block");
				 $('input[type="text"], date, select,file').prop("disabled", true);
				 $('#myCheckbox1').attr('disabled', true);
				 $('#myCheckbox').attr('disabled', true);
				 $('#upload').css('display','none');
				 $('#documents').css('display','block');
				 $("#cvupload1").val('');
				 $("#cvupload").val('');
				 $("#response1").val('');
				 $('#response').val('');
				 documents(applicantid);
			 	$('#success').text('Applicant registered succesfully!');
			 	$('#success1').text('Applicant registered succesfully!');
			 	$('#applicantid').text('Applicant ID:'+ applicantid);
			 	$('#error').text('');
				  $('#error1').text('');
			}else if("fail"==response.responseText){
			
				$('#error').text("Applicant Registration failed!");
				 $('#error1').text("Applicant Registration failed!");
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
     						 for(var i=0; i<data.responseJSON.length;i++){
     							 values.push(data.responseJSON[i].city_state)
     						 }
     						 response(values);
						 }
     				 })
     			 } 
     		 } 
     	 }) 
      })	
     function checkedit(){
			 $('input[type="text"], date, select').prop("disabled", false); 
			 $("#reg").css("display", "none");
	    	 $("#update").css("display", "block"); 
	    	 $("#register").css("display", "none");
	    	 $('#myCheckbox1').attr('disabled', false);
			 $('#myCheckbox').attr('disabled', false);
			 $('#success').text('');
	    	 $('#success1').text('');
	    	 $('#upload').css('display','block');
	    	 
		 }
	 
	 function updatejob(){
		 var user ={};
		 $('#firstname').attr('style', "");
		 $('#lastname').attr('style', "");
		 $('#mobile').attr('style', "");
		 $('#email').attr('style', "");
		 $('#location').attr('style', "");
		 $('#qual').attr('style', "");
		 $('#lastjob').attr('style', "");
		 $('#cvupload').attr('style', "");
   	  if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
   			$('#error').text('First Name is required !');
   			$('#error1').text('First Name is required !');
   			$('#firstname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			user.firstname =$('#firstname').val();		
   		}
   	  if($('#lastname').val()=='' || $('#lastname').val().trim()==''){
 			$('#error').text('Last Name is required !');
 			$('#error1').text('Last Name is required !');
 			$('#lastname').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
 			return;	
 		}else{
 			user.lastname =$('#lastname').val();		
 		}

 		if($('#mobile').val()=='' || $('#mobile').val().trim()=='' ){
 			$('#error').text('Mobile Number is required !');
 			$('#mobile').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
 			return;	
 		}
 		else{
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
   	  if($('#email').val()=='' || $('#email').val().trim()==''){
   			$('#error').text('Email is required !');
   			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else if(!validateEmail($('#email').val())){
				$('#error').text('Invalid email format');
  			$('#email').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");	
			return
		}else{
   			user.email =$('#email').val();		
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
	user.location = array.join(', ');
		
   		
   		if($( "#qual option:selected" ).text()=='Select' || $( "#qual option:selected" ).text().trim()=='Select'){
   			$('#error').text('Qualification is required !');
   			$('#qual').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
   			return;	
   		}else{
   			user.highesteducation =$( "#qual option:selected" ).text();		
   		}
   		
   	
   		if ($('textarea#jobrole').val() == '' || $('textarea#jobrole').val().trim() =="") {
			$('#error').text('Applicant Skills keywords is required!');
			$('#error1').text('Applicant Skills is required!');
			$('#jobrole').attr('style',
					"border-radius: 5px; border:#FF0000 1px solid;");
			return;
   		}else{
   			user.applicanttags =$('#jobrole').val();		
   		}
   		
	if($('#myCheckbox').is(':checked')){
		
	 }
		else{
			if($('#cvupload').val()=='' || $('#cvupload').val().trim()==''){
				$('#error').text('Upload is required !');
				$('#cvupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;	
			}else{
					
			}
   		var  path =$('#cvupload').val();
		var filename = path.match(/[^\\/]*$/)[0];
		var res = filename.split(".");
		user.docname1 = res[0];
		user.document1 =$('#response').val();
		user.uplaodcv =$('#response').val();
		user.filetype1 =type1;
}
   	 
   		if($('#myCheckbox1').is(':checked')){
			
		 }
   		else{
		var  path1 =$('#cvupload1').val();
		var filename1 = path1.match(/[^\\/]*$/)[0];
		var res1 = filename1.split(".");
		user.docname2 = res1[0];	
		user.document2 =$('#response1').val();
   		user.filetype2=type2;
   		 user.otheruploads=$('#response1').val();
   		}
   		user.applicantid = applicantid;
   		
         $('#error').text('');
         $('#error1').text('');
     	
     	var jsonfile={applicantsCreations:JSON.stringify(user)}; 
         console.log(jsonfile);
   $('#loading').show();   
	
		  $.ajax({
        url: "${pageContext.servletContext.contextPath}/user/updateApplicantDetails",
        type: 'POST',
        dataType: "json",
        data: jsonfile,
        complete:function(response){
        	 $('#loading').hide(); 
			 console.log(response);
			if("success"==response.responseText){
				$('#reg').attr("disabled", true);
			 	 $("#reg").css("display", "none");
				 $('#register').css("display","block");
				 $("#update").css("display", "none");
				 $('input[type="text"], date, select,file').prop("disabled", true);
				 $('input[type="text"], date, select,file').prop("disabled", true);
				 $('#myCheckbox1').attr('disabled', true);
				 $('#myCheckbox').attr('disabled', true);
				 $('#upload').css('display','none');
				 $('#documents').css('display','block');
				 documents(applicantid);
			 	$('#success').text('Applicant Updated succesfully!');
			 	$('#success1').text('Applicant Updated succesfully!');
			 	$('#error').text('');
				  $('#error1').text('');
				  $("#cvupload1").val('');
					 $("#cvupload").val('');
					 $("#response1").val('');
					 $('#response').val('');
			}else if("fail"==response.responseText){
				$('#error').text("Applicant update failed!");
				$('#error1').text("Applicant updated failed!");
				$('#success').text('');
			}
			
        }
    });
	
    return false;
		
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
	  $("#state").on("change", function(){ //on change event of state
		 getdata();
	 })
	 
	 
	 function getdata() {
 		 var stateid = $('#state').val();
		 console.log(stateid);
 		$("#city").empty();
		
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
	 }
	 
	  $('#city').select2();
	 $('#state').select2();
	 
	
       //Mobile Validation
      function Validate(data) {
          
          var pattern = /^\d{10}$/;
          if (pattern.test(data)) {
         	
              return 
          }
      }
      function getoriginals(){
			 $.ajax({
			 type: "POST",
		        url: "${pageContext.servletContext.contextPath}/user/cvUploadDocument",
		        dataType: "json",
		        data:{applicantid:applicantid},
		        complete: function(data) {
		            console.log(data);
		            var voterimage = "data:application/pdf;base64,";
		           
	                voterimage += data.responseJSON.uplaodcv;
		          
	 var win = window.open();
	 win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
	
	 documents(applicantid);
		        }      
		});
		}
		function getoriginals1(){
			$.ajax({
				 type: "POST",
			        url: "${pageContext.servletContext.contextPath}/user/cvOtherUploadDocument",
			        dataType: "json",
			        data:{applicantid:applicantid},
			        complete: function(data) {
			            console.log(data);
			            var voterimage = "data:application/pdf;base64,";
			           
		                voterimage += data.responseJSON.otheruploads;
			          
	 					var win = window.open();
						 win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')
						 documents(applicantid);
		        }
			})
		}
		       function  documents(applicantid){
		    	   $('#appdoc1')
					.text("");
				 $('#appdoc2')
					.text("");
		    	   $.ajax({
		   	         type: "POST",
		   	         url: "${pageContext.servletContext.contextPath}/user/getAppDocuments",
		   	        data:{applicantid:applicantid},
		   	         dataType :"json",
		   	         complete:function(data){
		   	         console.log(data);
		   	       if(data.responseJSON.uploadcvname == null){
		   	    	$("#doc1").css('display',
						'none');
		   	       }
		   	       else{
		   	    	$("#doc1").css('display',
					'block');
		   	    	doc1 = data.responseJSON.uploadcvname;
		   	    	 $('#appdoc1').text(data.responseJSON.uploadcvname.replace(/[\d_]+/g, '')); 
		   	       }
		          	if (data.responseJSON.otheruploadname == null) {
		  				$("#doc2").css('display',
		  						'none');
		  			} else {
		  				if (data.responseJSON.otheruploadname == null) {
			  				$("#doc2").css('display',
			  						'none');
		  				}
		  				else{
		  					doc2 = data.responseJSON.otheruploadname;
		  					$("#doc2").css('display',
	  						'block');
		  				$('#appdoc2').text(data.responseJSON.otheruploadname.replace(/[\d_]+/g, ''));
		  			}
		  			}
		          	

		  		}
		   	         
		      	  })
		       }
   
		       $(document).ready(function() {
		    		
		    	    $('#taskregistration')
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
		    		  if($('#firstname').val()=='' || $('#firstname').val().trim()==''){
		    	   			
		    	   		}else{
		    	   			inputChanged=true;	
		    	   		}
		    	   	  if($('#lastname').val()=='' || $('#lastname').val().trim()==''){
		    	 			
		    	 		}else{
		    	 			inputChanged=true;	
		    	 		}

		    	 		if($('#mobile').val()=='' || $('#mobile').val().trim()=='' ){
		    	 			
		    	 		}
		    	 		else{
		    	 			inputChanged=true;
		    	 			
		    	 		}
		    	   	  if($('#email').val()=='' || $('#email').val().trim()==''){
		    	   			
		    	   		}else{
		    	   			inputChanged=true;		
		    	   		}
		    	   	 
		    	   	if($('#location  option:selected').text()=='Select Country' || $('#location  option:selected').text().trim()=='Select Country'){
		    				
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
				
		    		 if(inputChanged) {
		    			 
		    			 
		    		  }
		    		  else{
		    			  inputChanged =false;
		    			    var message = "Are you sure you want leave?";
		    			    e.returnValue = message;
		    			    return message;  
		    			  
		    		  }
			  return null;
			}; 
			
			//Deleting the file
			function firstdocdel(){
				$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteApplicantFile",
					data : {applicantId:applicantid,filename:doc1,fileNumber:1},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						 documents(applicantid);
			}
				})
				}
			
			function seconddocdel(){
				$
				.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/deleteApplicantFile",
					data : {applicantId:applicantid,filename:doc2,fileNumber:2},
					dataType : "json",
					complete : function(data) {
						console.log(data);
						 documents(applicantid);
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
.select2-container--default .select2-selection--single {
    background-color: #fff;
    border: 1px solid rgba(0,0,0,.15);;
    border-radius: 4px;
}

function validateEmail(email) {
	  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	  return emailReg.test( email );
	}
</style>
</body>

</html>
