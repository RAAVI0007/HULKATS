<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<c:url value='/resources/images/favicon.png' />" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>HULKATS</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


<link href="<c:url value='/resources/css/styles.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/simple-line-icons.css' />	"
rel="stylesheet">
	<style>
	.hulklogic {
   
    font-size: 18px;
    font-weight: bold;
}
	
	</style>
</head>
<body>
	<div class="page-wrapper flex-row align-items-center">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="card p-4">
						<div
							style="display: table-cell; vertical-align: middle; text-align: center">
							<img src="<c:url value='/resources/images/HulkATS_logo.png'/>"
								style="width: 200px;" />
						</div>
						<div id="loading"
					style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
					<p style="position: absolute; color: White; top: 50%; left: 45%;">
						<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
							id="img" />
				</div>
				<div class="form-group" style="text-align: center">
					<h5>
						<span id='success' style="color: green;"></span> <span id='error'
							style="color: red; margin-left: auto; margin-right: auto;"></span>
					</h5>
				</div>
						<div
							class="card-header text-center text-uppercase h4 font-weight-light">
							<b>Upload Contract</b></div>
						<form name="registration" id='registration'
							>
							<div class="card-body py-5">
								<div class="form-group">
									<label for="cvupload"><strong>Upload Contract</strong></label>
				 <input id="cvupload" type="file" class="form-control" name="cvupload" required />
				 <input id="response" type="hidden" class="form-control" required />
								</div>
</div>
<div class="card-footer">	
   <div class="row">
									<div class="col-sm-6">
									<button type="button" onclick="checkAjaxCall();" class="btn btn-primary px-5">Upload</button>
									</div>
									<div class="col-sm-6">
										<a
											href="${pageContext.servletContext.contextPath}/login/login"
											class="btn btn-link"><b>Login?</b></a>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="hulklogic text-center text-uppercase">
							<a href="https://www.hulklogic.com/" target="_blank">HULKLOGIC TECHNOLOGIES</a></div>
							<div class="externalLinks text-center" >
			<a href="" target="_blank" style="margin-right: 10px; text-decoration: none;">
				<span class="text-12 color-lightgrey">Help</span>
			</a>
			<a href="" target="_blank" style="margin-right: 10px; text-decoration: none;">
				<span class="text-12 color-lightgrey">Privacy</span>
			</a>
			<a href="" target="_blank" style="text-decoration: none;">
				<span class="text-12 color-lightgrey">Terms</span>
			</a>
		</div>
					
				</div>
			</div>
		</div>
		
	</div> 
	
	
	<script src="<c:url value='/resources/js/jquery.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/demo.js' />"
		type="text/javascript"></script>
	
 <script>
 var inputChanged = false;
 var x = window.location.href;	
 var id =x.substring(x.lastIndexOf(':') + 1);
 var type1;
 var jobid;
 var doc1;

 $("#response").val("undefined");
 
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
	   function checkAjaxCall(){
		   var customer ={};
			$('#cvupload').attr('style', "");
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
			customer.docuname = res[0];
			customer.encodeDocument =$('#response').val();
			customer.docType=type1;
			customer.customerid = id;
			var jsonfile={customer:JSON.stringify(customer)}; 
	           console.log(jsonfile);
          $('#loading').show(); 
		  $.ajax({
       url: "${pageContext.servletContext.contextPath}/login/uploadContractDocument",
       type: 'POST',
       dataType: "json",
       data: jsonfile,
       complete:function(response){
       	 $('#loading').hide(); 
			console.log(response);
			if("success" ==response.responseText ){
				
				$('#success').text('File Uploaded Successfully');
				
			}else if("fail"==response.responseText){
				$('#error').text('File Uploaded failed');
				
				$('#success').text('');
				$('#success1').text('');
				
			}
			
       }
   });
	return false;	
	}
 
 </script>

</body>
</html>
