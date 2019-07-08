<!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>
  
  
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
        <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/user/changePassword">Change Password</a>
        </li>
      </ol>
	<div class="card card-register mx-auto mt-5">
      <div class="card-header"><h2 align="center">Change Password</h2></div>
      <div class="card-body">
        <div id="loading" style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;  display:none;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
        <img src="<c:url value='/resources/images/ajax-loader.gif'/>" id="img" / >
       </div> 
      
      
       <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       
       <!-- Error Message Ending -->
       
       
       
	<form action="" name="forgotpassword">

				<label>Enter Current Password</label>
                <input type="password" id="currentpwd" class="form-control" placeholder="Enter Current Password" required autofocus>
				 <br> 
				 <label>New Password </label>
				<input name="password" id="password" class="form-control" type="password" placeholder="Enter New Password"  data-toggle="password">
				<br>
				<label>Confirm password</label>
  				<input type="password" name="confirm_password" id="confirm_password" class="form-control"   placeholder="Re-enter New Password"/> 
  				<span id='message'></span>
				<br>
                   <div class="form-group" style="text-align:center">
                              
					   <div class="col-xs-12" style="margin-top:5px">
					   <input id="reg"  onclick="changePassword();" class="btn btn-success" style="height:35px" type="button" value="Update">
					   </div>
		                    </div>
		                    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> 
  </form>
</div>
</div>
</div>
</div>
   
       <!--   footer -->
   <%@include file="./shared/footer.jsp" %>
      
	
    
   
	<script>
    function logout() {
		
		$.ajax({
			type : "GET",
			url : "/j_spring_security_logout",
		});
	}
	
	function changePassword(){
		var currentpwd;
		var newpwd;
		$('#currentpwd').attr('style', "");
		$('#password').attr('style', "");
		$('#confirm_password').attr('style', "");
		 $('#error').text('');
		 $('#success').text('');
		if($('#currentpwd').val()=='' || $('#currentpwd').val().trim()==''){
			$('#error').text('Password is required !');
			$('#currentpwd').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			currentpwd =$('#currentpwd').val();		
		}
		if($('#password').val()=='' || $('#password').val().trim()==''){
			$('#error').text('Password is required !');
			$('#password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			newpwd =$('#password').val();		
		}
		if($('#confirm_password').val()=='' || $('#confirm_password').val().trim()==''){
			$('#error').text('Please Retype Password !');
			$('#confirm_password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			var repass = $('#confirm_password').val();
			if(newpwd!=repass){
				$('#error').text('Password and Re-Type should be same !');
				$('#confirm_password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				$('#password').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				
				return;
			}
		}
		 $('#loading').show();  
		 $('#error').text('');
		 $('#success').text('');
		$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/user/updatePassword",
          	data:"oldPassword="+currentpwd + "&newPassword="+newpwd,
            dataType :"json",
            complete: function(result) {
            	 $('#loading').hide(); 
            	 console.log(result);
				if(result.responseText == 'Password updated' ){
				$('#success').text("Updated Successfully");
				$('#error').text('');
				$('#reg').attr("disabled", true);
				}
				else if(result.responseText == 'Password updated fail'){
					$('#error').text("Password update process failed");
					$('#success').text('');
				}
				else if(result.responseText == "Password doesn't match"){
					$('#error').text("Wrong Information");
					$('#currentpwd').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
					$('#success').text('');
				}
		
	}
		});
	}
	
</script>
</body>
</html>