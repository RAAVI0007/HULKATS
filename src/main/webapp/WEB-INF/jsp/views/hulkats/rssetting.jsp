<!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
        <li class="breadcrumb-item active">Settings</li>
      </ol>
       <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" ></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" ></span></h5>
       </div>
       <!-- Error Message Ending -->
     
      
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
					style="margin-left: 137px; color: red;" ></span>
				<form id="signature" name="signature">
					 <div class="form-group">
            <div class="form-row">
              <div class="col-md-8">
               <label for="name"><strong>Name</strong></label>
				<input class="form-control" type="text" name="name" id="name" placeholder="" required disabled> <br>  
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="namestyle" >
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
                <label	for="designation"><strong>Designation</strong></label> 
					 <input	class="form-control" type="text" name="designation" id="designation" placeholder="Manager" required> <br>   
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="desigstyle" >
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
              <label for="mobile"><strong>Mobile</strong></label>
				<input class="form-control" type="text" name="mobile" id="mobile" placeholder="65********" required disabled> <br>
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="mobilestyle" >
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
              <label for="mobile"><strong>Email</strong></label>
				<input class="form-control" type="text" name="mobile" id="email" placeholder="" required disabled> <br>
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="emailstyle" >
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
               <label	for="company"><strong>Company Name</strong></label>
					  <input class="form-control" type="text" name="company" id="company" placeholder="HULKLOGIC LLC" required> <br>   
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="companystyle" >
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
               <label	for="company"><strong>Company Address</strong></label>
					  <textarea class="form-control"  name="company" id="companyadd" placeholder="DALLAS, TEXAS, USA 75001" required></textarea> <br>   
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="addrstyle" >
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
               <label for="weburl"><strong>Company Website</strong></label>
				<input class="form-control" type="text" name="weburl" id="weburl" placeholder="www.hulklogic.com" required>   
              </div>
              <div class="col-md-3">
               <label for="style"><strong>Select Style</strong></label>
							 <select class="form-control" name="style" id="urlstyle" >
							 <option value='bold'>Bold</option>
							  <option value='italic'>Italic</option>
							   <option value='underlined'>Underlined</option>
							 </select><br>
              </div>
            </div>
          </div>
       <input class="btn btn-success" onclick='preview();'  type='button' value='Preview'  />
			 <br>
			 <br>
			<div class="preview " id="theme-1" style="display:none">
            <div class="default_signature">
            <table >
                <tbody><tr>
                    <td style="padding-left:0px;padding-top:0;" valign="top" class="change_theme_p">
                        <h1 class="name-target" style="font-size: 16px;margin: 0px;text-transform: uppercase;font-weight:bold;">
                        <span id="signame"></span></h1>
                        <span class="signature_jobtitle-target change_font txt" style="color: #333333;padding-bottom: 0;padding-top: 2px;font-weight: 500;margin: 0px;" id="sigdest"></span>
                        <ul class="change_theme_font" style="margin-top: 10px;margin-left: 0;margin-right: 0;margin-bottom: 0;padding: 0;list-style: none;text-align:left;margin-bottom:10px;">
                            <li style="margin-bottom: 0;margin-right: 10px;margin-left: 0;display:inline-block;color:#333;">
                            <span class="" style="color: #4fb218; font-weight:bold;">Mobile:</span> 
							<span class="mobilephone-target txt" id="sigmobile"></span></li>
                            <li style="margin-bottom: 0;margin-right: 0;margin-left: 0;display:inline-block;color:#333;">
                            <span class="" style="color: #4fb218; font-weight:bold;">Email:</span> 
							<a href="" class="email-target link" style="color:#333;text-decoration:none;" id="sigemail"></a></li>
                        </ul>
                        <p class="change_theme_font" style="text-align:left;font-weight: 600;color: #333333;margin-top: 0;margin-left: 0;margin-right: 0;margin-bottom: 0;">
                        <span class="change_theme_font" style="display:block;" id="sigcompany"></span>
                        <span class="txt" id="sigaddress"></span></p>
                        <p class="change_theme_font" style="text-align:left;font-weight: 400;color: #333333;margin-top: 0;margin-left: 0;margin-right: 0;margin-bottom: 0;">
                        <span class="change_theme_font" style="color: #4fb218; font-weight:bold;">Website:</span> 
                        <a href="" style="color:#333;text-decoration:none;" class="link" id="sigurl"></a></p>
                    </td>
                </tr>
            </tbody></table>
			</div>
			</div>
			
							<div id="demo"></div>
							<div class="form-group" style="text-align: center">
			<h5>
				<span id='success1' style="color: green;" ></span> <span
					id='error1'
					style="color: red; margin-left: auto; margin-right: auto;"
					></span>
			</h5>
		</div>
					<div class="form-group" style="text-align: center">
						<div class="col-xs-12 btn-group" style="margin-top: 5px">
							<input id="sigregs" onclick="signatures();"
								class="btn btn-success" type="button"
								style="display:none; margin-right: 16px"
								value="Submit" />
								
								<button type="button" class="btn btn-success" 
								onclick='testmail();'
								style="margin-right: 16px">Test Mail</button>
								<button type="button" class="btn btn-success" id="sigregister"
								onclick="checkeditsig();"
								>Edit</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>

		</div>
	
    <!--   footer -->
   <%@include file="./shared/footer.jsp" %>
	</div>
	</div>
	
	<script>
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
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/autoSignature",
	            type: 'POST',
	            dataType: "json",
	            data: jsonfile,
	            complete:function(data){
	            	 $('#loading').hide(); 
	    			console.log(data);
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
	   
	 $('document').ready(function(){
		 editautosignature();
		 $('input[type="text"], date, select').prop("disabled", true);
		   $('#role').prop('disabled',false);
		 	 $('#checkeditsig').css('display','block');
		 	$("textarea").prop('disabled', true);
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
	  })
	  
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
			
	   }
	   
	 function testmail(){
		   $.ajax({
	           url: "${pageContext.servletContext.contextPath}/user/sendAutoSignature",
	            type: 'POST',
	            dataType: "json",
	            complete:function(data){
	            	 
	    			console.log(data);
	    			if(data.responseText == 'success'){
	    				$('#success').text('Email sent successfully');
	    				$('#error').text('');
	    			
	    			}
	    			else if(data.responseText == 'fail'){
	    				$('#error').text('Email sent fail');
	    				$('#success').text('');
	    			}
	    			
	            }
	        })
	   }
	   
	   //Mobile Validation
	      function Validate(data) {
	          
	          var pattern = /^\d{10}$/;
	          if (pattern.test(data)) {
	         	
	              return data;
	          }
	      }
	</script>
    