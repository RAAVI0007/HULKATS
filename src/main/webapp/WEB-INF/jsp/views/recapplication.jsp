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
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/recruiter/application">Application
					Overview</a></li>

		</ol>
		<div id="loading"
			style="margin: 0px; padding: 0px; position: fixed; right: 0px; top: 0px; width: 100%; height: 100%; background-color: rgb(102, 102, 102); z-index: 30001; opacity: 0.8; display: none;">
			<p style="position: absolute; color: White; top: 50%; left: 45%;">
				<img src="<c:url value='/resources/images/ajax-loader.gif'/>"
					id="img" />
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<!-- Error Messages -->
		<div class="form-group" style="text-align: center">
			<h5>
				<span id='success' style="color: green;" ></span> <span
					id='error'
					style="color: red; margin-left: auto; margin-right: auto;"
					></span> <b><span id="jobid" style="color: green;"
					></span></b>

			</h5>
		</div>
		<!-- Error Message Ending -->
		<br>
		<div class="col-sm-4">
			<div class="panel panel-primary">
				<div class="form-group">
					<div class="input-group">
					
					 <select class="form-control select2-single" id="joblist">
								<option>Select Job</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<br> <a class="nav-link" href="" id="emailhref"
			data-toggle="modal" data-target="#mailModal" style="display: none">
			<button class="nav-link-text btn btn-success">Bulk Email</button>
		</a> <br>
		<div class="row" style="overflow-x: auto;">
			<div id="table" class="col-lg-12" style="display:none;">
				<h4>Application Overvie</h4>
				<div id="usertable" class="table-responsive1">
					<table id="example"
						class="table table-striped table-bordered nowrap">
						<thead style="background: #ff6707; color: #fff">
							<tr>
								<!-- <th class="table_heading"><input type="checkbox"  id="ckbCheckAll" name="heading" ></th> -->
								<th class="table_heading"></th>
								<th class="table_heading">Name</th>
								<th class="table_heading">Email</th>
								<th class="table_heading">Mobile</th>
								<th class="table_heading">Location</th>
								<th class="table_heading">Last Job Title</th>
								<th class="table_heading">Current Status</th>
								<th class="table_heading">Source</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="mailModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bulk Email</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- Error Messages -->
					<div class="form-group" style="text-align: center">
						<h5>
							<span id='success1' style="color: green;" ></span> <span
								id='error1'
								style="color: red; margin-left: auto; margin-right: auto;"
								></span>

						</h5>
					</div>
					<!-- Error Message Ending -->
					<label for="template"><strong>Template Name</strong></label>
					<div class="input-group">
						<span class="input-group-btn">
							
						</span> <select id="template" class="form-control select2-single"
							name="template"><option>Select Template</option></select>
					</div>
					<br> <br> <label for="subject"><strong>Subject</strong></label>
					<input id="subject3" name="subject" class="form-control"
						placeholder="Enter Subject" /> <br> <label for="body"><strong>Body</strong></label>
					<textarea rows="10" cols="5" id="body3" name="body"
						class="form-control" placeholder="Enter Body" required></textarea>
					<br>
					<div id="documentstemp" style="display: none;">
						<label><strong>Template Documents</strong></label>
						<div id=tem1>
							<input type="checkbox" id="myCheckbox" name="myCheckbox">
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc2"
								onclick="temporg()"></a>
									<a id="cross" onclick='firstdocdel();'>&#x274C;</a>
						</div>

						<div id=tem2>
							<input type="checkbox" id="myCheckbox1" name="myCheckbox1">
							<i class="fa fa-fw fa fa-file-text-o"></i> <a id="temdoc1"
								onclick="temporg1()"></a>
								<a id="cross" onclick='seconddocdel();'>&#x274C;</a>
						</div>
					</div>
					<div id="controls">
						<a id="save" onclick="upload();"><img
							src="<c:url value='/resources/images/add_2x.png' />"></a>
					</div>
					<div id="checkotp" style="display: none">
						<label for="cvupload"><strong>Upload Other
								Document</strong></label> <input id="cvupload1" type="file" class="form-control"
							name="cvupload" required /> <input id="response1" type="hidden"
							class="form-control" required /> <br>
						<div id="controls2">
							<a id="save" onclick="upload1();"><img
								src="<c:url value='/resources/images/add_2x.png' />"></a>
						</div>
					</div>
					<div id="checkotp1" style="display: none">
						<label for="cvupload"><strong>Upload Other
								Document</strong></label> <input id="cvupload" type="file" class="form-control"
							name="cvupload" required /> <input id="response" type="hidden"
							class="form-control" required /> <br>
					</div>
					<div class="form-group" style="text-align: center">
						<div class="col-xs-12" style="margin-top: 5px">
							<button type="button" class="btn btn-success" id="register"
								onclick="bulkemail();"
								style="margin-left: auto; margin-right: auto;">Send</button>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	</div>
	<!--   footer -->
	<%@include file="./shared/footer.jsp"%>
	<script>
	var templatedata;
	var rowsel = [];
	var app_id;
	var mytable;
	var template_id;
	 var type1;
	 var type2;
	 var temp;
	 var temp1;
	 var doc1;
	 var doc2;
	  $('#joblist').on('change', function() {
		  $('#table').css('display','block');
		 $.ajax({
			 type:"POST",
			 dataType: "json",
			 data:{jobid:$('#joblist').val()},
			 url:"${pageContext.servletContext.contextPath}/recruiter/getApplication",
			 complete:function(data){
    		 console.log(data);
    		  mytable =   $('#example').dataTable({ 
                 data : data.responseJSON,
                 "bDestroy": true,
          				columns:[
          					{"data":"app_id"},
                       	{ "data": null,
                       		 "mRender" : function ( data, type, full ) {
                                    var  name= full['firstname']+' '+full['lastname'];
                                    var id = full['app_id'];
                                    return "<a href='${pageContext.servletContext.contextPath}/user/applicationsettings?app_id:" + id +"' target='_blank'>"+name +"</a>"
                                                       }
                       	}, 
                              { "data": "email" },
                              { "data": "contactno" },
                              {"data":"location"},
                              { "data": "lastjobtitle" },
                              { "data": "status" },
                              {"data":"source"}
                              ],
                              responsive: true,
                              columnDefs: [
                            	  {
                                      targets: 0,
                                      checkboxes: {
                                          seletRow: true
                                      }
                                  },
                                  { responsivePriority: 1, targets: 0 },
                                  { responsivePriority: 2, targets: -1 }
                              ],
                              select:{
                                  style: 'multi'
                              },
                              order: [[1, 'asc']]
    		 })
    			$('#emailhref').css('display','block');
			 }
		 })
	})
	$('#myCheckbox1').click(function() {
			 doc2 =temp1; 
});
	  $('#myCheckbox').click(function() {
			
			 doc1 =temp; 	
});
	 function bulkemail(){
		  var table = $('#example').DataTable();
			 var user = {};
	   		 rowsel = table.column(0).checkboxes.selected();
	     		
	     		var bulkemail = []; 
	     		for(var i = 0; i< rowsel.length; i++){
	     			bulkemail.push(rowsel[i]);
	     		}
	     		console.log(bulkemail); 
				 var  path =$('#cvupload').val();
					var filename = path.match(/[^\\/]*$/)[0];
					var res = filename.split(".");
					var documentName = res[0];
					var  path1 =$('#cvupload1').val();
					var filename1 = path1.match(/[^\\/]*$/)[0];
					var res1 = filename1.split(".");
					var documentName1 = res1[0];	
		 if(bulkemail.length!=0){	
		 var emailtemplate = $('#template').val();
		 if($('#body3').val()=='' || $('#body3').val().trim()==''){
				$('#error').text('Body is required !');
				$('#error1').text('Body is required !');
				$('#body3').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				return;	
			}else{
				 user.body = $('#body3').val();		
			}
		  if($('#subject3').val()=='' || $('#subject3').val().trim()==''){
			$('#error').text('Subject is required !');
			$('#error1').text('Subject is required !');
			$('#subject').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
			return;	
		}else{
			user.subject = $('#subject3').val();	
		}
			 	user.templateDocument = $('#response1').val();
			 	 user.fileType = type1;
			  	user.templateDocument1 = $('#response').val();
			  	user.fileType1 = type2;
			  	user.attachment = documentName1;
	     		user.attachment1 = documentName;
	     		user.attachment2 = doc1;
	     		user.attachment3 = doc2;
			  console.log(user);
			  $('#loading').show();
		 $.ajax({
			 type:"POST",
			 dataType: "json",
			 data:{appIds:bulkemail,bulk:JSON.stringify(user)},
			 url:"${pageContext.servletContext.contextPath}/user/sendbulkemail",
			 complete:function(data){
				  $('#loading').hide();
				 console.log(data);
				 if(data.responseText == 'success'){
					 $('#mailModal').modal('hide');
					 $('#success').text('Emails has been sent successfully');
					 $('#error').text('');
					 doc1 = null;
					 doc2 = null;
				 }
				 else if(data.responseText == 'fail'){			
					 $('#error1').text('Emails delivery failed');
					 $('#success').text('');
				 }
				 else if(data.responseText == 'no signature'){
					 $('#error1').text('Please assign auto signature in Settings');
					 $('#success').text(''); 
					
				 }
	 }
		 })
	 }
	 else{
		 $('#error').text('Please select the table checkbox');
		 $('#mailModal').modal('hide');
		 $('#success').text('');
	 }
	 }
		$("#cvupload1").change(function(){
		     var filesSelected = document.getElementById("cvupload1").files;
		     if(filesSelected.length > 0){
		    var fileToLoad = filesSelected[0];
		    var fileReader = new FileReader();
		    fileReader.onload = function(fileLoadedEvent){
		    var base64value = fileLoadedEvent.target.result;
			base64 = base64value.split(',')[1];
			var type = base64value.split('/')[1];
			 type1 = type.split(';')[0];
			$("#response1").val(base64);
		    };
		     fileReader.readAsDataURL(fileToLoad);
		    }
		    });
		$("#cvupload").change(function(){
		     var filesSelected = document.getElementById("cvupload").files;
		     if(filesSelected.length > 0){
		    var fileToLoad = filesSelected[0];
		    var fileReader = new FileReader();
		    fileReader.onload = function(fileLoadedEvent){
		    var base64value = fileLoadedEvent.target.result;
			base64 = base64value.split(',')[1];
			var type = base64value.split('/')[1];
			 type2 = type.split(';')[0];
			$("#response").val(base64);
		    };
		     fileReader.readAsDataURL(fileToLoad);
		    }
		    });
     $(document).ready(function(){
         $.ajax({
   	         type: "POST",
   	         url: "${pageContext.servletContext.contextPath}/recruiter/getJobtitle",
   	         dataType :"json",
   	         complete:function(data){
   	         console.log(data);
   	         
   	         for (var i = 0; i < data.responseJSON.length; i++)
   	         { 
   	              $('#joblist').append($('<option>',
   	              {
   	                 value: data.responseJSON[i].jobid,
   	                 text : data.responseJSON[i].jobtitle
   	             }));
   	         }
   	         }
         })
          $('#joblist').select2();
          
         $.ajax({
   	         type: "POST",
   	         url: "${pageContext.servletContext.contextPath}/user/getAllTemplateNames",
   	         dataType :"json",
   	         complete:function(data){
   	         console.log(data);
   	     	 templatedata=data;
   	         for (var i = 0; i < data.responseJSON.length; i++)
   	         { 
   	              $('#template').append($('<option>',
   	              {
   	                 value: data.responseJSON[i].template_id,
   	                 text : data.responseJSON[i].template_name
   	             }));
   	         }
   	         }
         })
     })
      $('#template').select2();
    	 $("#template").on("change", function(){
    		 $("#temdoc2").text("");
    		 $("#temdoc1").text("");
        		var selected = $(this).val();
        	for(var i=0;i<templatedata.responseJSON.length;i++){
			if(selected ==templatedata.responseJSON[i].template_id){
				template_id =  templatedata.responseJSON[i].template_id;
		    	$('#subject3').val(templatedata.responseJSON[i].subject);
		    	$('#body3').val(templatedata.responseJSON[i].body);
		    	 $('#documentstemp').css('display','block');
		    	 if(templatedata.responseJSON[i].attachement== null && templatedata.responseJSON[i].attachement1 == null ){
		    		 $('#documentstemp').css('display','none');
		    		
		    	 }
		    	 if(templatedata.responseJSON[i].attachement== null ){
		    	       $("#tem1").css("display", "none");
		    	       
		    	     }else{
		    	    	 temp = templatedata.responseJSON[i].attachement;
		    	         $("#temdoc2").text(templatedata.responseJSON[i].attachement.replace(/[\d_]+/g, ''));
		    	         $("#tem1").css("display", "block");
		    	        }
		    	        if(templatedata.responseJSON[i].attachement1 == null){
		    	         $('#tem2').css('display','none');
		    	        
		    	        }
		    	        else {
		    	        	
		    	        	$('#tem2').css('display','block');
		    	        	temp1 = templatedata.responseJSON[i].attachement1;
		    	        	$("#temdoc1").text(templatedata.responseJSON[i].attachement1.replace(/[\d_]+/g, ''));
		    	       
		    	        }   	   	       
			}
		 }
      }) 
  
      function upload(){
	          $("#controls").css("display", "none");
	         $("#checkotp").css("display", "block");      
	    }
     function upload1(){
         $("#controls2").css("display", "none");
         $("#controls").css("display", "none");
        $("#checkotp1").css("display", "block");      
   }
     function temporg(){
			 $.ajax({
			 type: "POST",
		        url: "${pageContext.servletContext.contextPath}/user/sendTemplateDocument",
		        dataType: "json",
		        data:{templateId:template_id},
		        complete: function(data) {
		            console.log(data);
		            var voterimage = "data:application/pdf;base64,";
	                voterimage += data.responseJSON.attachement;
	 var win = window.open();
	 win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
		        }  
		});
		}
     function temporg1(){
		 $.ajax({
		 type: "POST",
	        url: "${pageContext.servletContext.contextPath}/user/sendTemplateDocument",
	        dataType: "json",
	        data:{templateId:template_id},
	        complete: function(data) {
	            console.log(data);
	            var voterimage = "data:application/pdf;base64,";
                voterimage += data.responseJSON.attachement1;
 var win = window.open();
 win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
	        }  
	});
	}
     function firstdocdel(){
 		$
 		.ajax({
 			type : "POST",
 			url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
 			data : {templateId:template_id,filename:temp,fileNumber:1},
 			dataType : "json",
 			complete : function(data) {
 				console.log(data);
 				documents(template_id);
 	}
 		})
 		}
     	 
     	 
     	 function documents(templateid){
     		 $('#jobdoc2')
     			.text("");
     		 $('#jobdoc1')
     			.text("");
     			$
     			.ajax({
     				type : "POST",
     				url : "${pageContext.servletContext.contextPath}/user/getAttachments",
     				dataType : "json",
     				data : {
     					template_id : templateid
     				},
     				complete : function(data) {
     					  console.log(data);
     					  if(templatedata.responseJSON[i].attachement== null ){
     			    	       $("#tem1").css("display", "none");
     			    	       
     			    	     }else{
     			    	    	 temp = templatedata.responseJSON[i].attachement;
     			    	         $("#temdoc2").text(templatedata.responseJSON[i].attachement.replace(/[\d_]+/g, ''));
     			    	         $("#tem1").css("display", "block");
     			    	        }
     			    	 	
     			    	        if(templatedata.responseJSON[i].attachement1 == null){
     			    	         $('#tem2').css('display','none');
     			    	        
     			    	        }
     			    	        else {
     			    	        	
     			    	        	$('#tem2').css('display','block');
     			    	        	temp1 = templatedata.responseJSON[i].attachement1;
     			    	        	$("#temdoc1").text(templatedata.responseJSON[i].attachement1.replace(/[\d_]+/g, ''));
     			    	       
     			    	        }   	       
     					}
     			});
     		}
 	
 	function seconddocdel(){
 		$
 		.ajax({
 			type : "POST",
 			url : "${pageContext.servletContext.contextPath}/user/deleteTemplateFile",
 			data : {templateId:template_id,filename:temp1,fileNumber:2},
 			dataType : "json",
 			complete : function(data) {
 				console.log(data);
 				documents(template_id);
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
</style>
