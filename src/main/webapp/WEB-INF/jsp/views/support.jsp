
  <!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>

  <div class="content-wrapper">
    <div class="container-fluid">
       <ol class="breadcrumb" id='supportcrumb'>
        <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/manager/dashBord">Dashboard</a>
        </li>
        <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/user/support">Support</a>
        </li>
        <li class="breadcrumb-item active">Support</li>
      </ol>
     <div class="form-group">
            <div class="form-row">
   <div class="col-md-3">
   <select id="role" class="form-control">
   <option value="1" selected>Support</option>
   <option value="2" >Support List</option>
  </select>  
   </div>
   </div>
   </div>  
     
   <div class="card card-register mx-auto mt-5">
      <div class="card-header"><h3 align="center">Support</h3></div>
      <div class="card-body">
      
      <!-- Loading Starts -->
      <div id="loading" style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8;  display:none;">
		<p style="position: absolute; color: White; top: 50%; left: 45%;">
        <img src="<c:url value='/resources/images/ajax-loader.gif'/>" id="img" / >
       </div> 
       <!-- Loading End -->
       
       
        <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       <!-- Error Message Ending -->
       
        <form id="supportreg" name="supportreg" action="/register" >
          <div class="form-group">
            <label for="comment"><strong>Comment</strong></label>
            <textarea rows="10" cols="50" class="form-control" type="text" name="comment" id="comment"  placeholder="Enter Comment"></textarea>
          </div>
          <div class="form-group">
            <label for="comment"><strong>Problem URL</strong></label>
            <textarea rows="10" cols="50" class="form-control" type="text" name="url" id="url"  placeholder="Enter URL [optional]"></textarea>
          </div>
          <label for="cvupload"><strong>Upload Picture
							</strong></label> <input id="cvupload" type="file" class="form-control"
						name="cvupload" required /> <input id="response" type="hidden"
						class="form-control" required /> <span
						style="margin-left: 0px; color: green;" colspan="2">*Please
						Upload extension .jpg or .png </span> <br>
					<div id="controls">

						<a id="save" onclick="upload();"><img
							src="<c:url value='/resources/images/add_2x.png' />"></a>
					</div>
					<div id="checkotp" style="display: none">
						<label for="cvupload"><strong>Upload Other
								Picture [optional]</strong></label> <input id="cvupload1" type="file" class="form-control"
							name="cvupload" required /> <input id="response1" type="hidden"
							class="form-control" required /> <br>

					</div>
           
         
		  
		  
			 
			 
          
		  
		   <br>
		    <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success1' style="color:green;" colspan="2"></span>
       		<span id='error1' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       
       <!-- Error Message Ending -->
             <div class="form-group" style="text-align:center">
                 <div class="col-xs-12" style="margin-top:5px">
					   <button id="reg" onclick="checkAjaxCall();" type="button" class="btn btn-success" style="height:35px">Submit</button>
					   </div>
		                    </div>
		                    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>  
        </form>
   
        </div>
        
      </div>
	   
   <!--   footer -->
   <%@include file="./shared/footer.jsp" %>
   
	</div>
	</div>

	<script>
	var type1;
	var type2;
	function upload(){
        $("#controls").css("display", "none");
       $("#checkotp").css("display", "block");
         
  }
	function upload1(){
        $("#controls1").css("display", "none");
       $("#checkotp1").css("display", "block");
         
  }
	function showDiv() {
   document.getElementById('welcomeDiv').style.display = "block";
}
 
      function checkAjaxCall(){
    	  var user ={};
    	  $('#comment').attr('style', "");
    	  $('#cvupload').attr('style', "");
    	
    	  
    	  if($('#comment').val()=='' || $('#comment').val().trim()==''){
    			$('#error').text('Comment is required !');
    			$('#error1').text('Comment is required !');
    			$('#comment').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
    			return;	
    		}else{
    			user.comment =$('#comment').val();		
    		}
    	 
  			user.problemurl =$('#url').val();
  			if($('#cvupload').val()==''){
				$('#error').text('Upload is required !');
				$('#error1').text('Upload is required !');
				$('#cvupload').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;
			}else{
				
			}
			var  path =$('#cvupload').val();
			var filename = path.match(/[^\\/]*$/)[0];
			var res = filename.split(".");
			user.imageName = res[0];
			var  path1 =$('#cvupload1').val();
			var filename1 = path1.match(/[^\\/]*$/)[0];
			var res1 = filename1.split(".");
			user.imageName1 = res1[0];
			
			
			user.uploadImgEncoded =$('#response').val();		
			user.uploadImgEncoded1 =$('#response1').val();
			
			user.imageType=type1;
			user.imageType1=type2;
  		
    	 
          
          $('#error').text('');
          $('#error1').text('');
      
      	var jsonfile={supportInfo:JSON.stringify(user)}; 
          console.log(jsonfile);
    $('#loading').show();   
    $.ajax({
       url: "${pageContext.servletContext.contextPath}/user/saveSupportInfo",
        type: 'POST',
        dataType: "json",
        data: jsonfile,
        complete:function(response){
        	 $('#loading').hide(); 
			console.log(response);
			if(response.responseText == "success"){
				 $('#error').text('');
				 $('#error1').text('');
				 $('#success').text('Our HULKLOGICIAN resolve the issue as soon as possible.');
				 $('#success1').text('Our HULKLOGICIAN resolve the issue as soon as possible.');
				
			
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
      $(document).ready(function(){
     	 $("#response").val("undefined");
     	 $("#response1").val("undefined");
     	
     	
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
  else{
 	 $("#response1").val("undefined"); 
 	 
  }
 });

 });
    </script>

</body>

</html>