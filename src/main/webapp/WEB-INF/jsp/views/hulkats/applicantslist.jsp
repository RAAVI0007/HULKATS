<!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
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
				<sec:authorize access="hasRole('ROLE_BASICMANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/basicmanager/dashboard">Dashboard</a>
				</li>
			</sec:authorize>
        <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/user/applicantslist">Applicant</a>
        </li>
        <li class="breadcrumb-item">
          <a href="">View Applicants</a>
        </li>
        
      </ol>
                  <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       <!-- Error Message Ending -->
    				<div class="row" style="overflow-x:auto;">
				        <div id="table" class="col-lg-12">
                        <h2>Applicants Overview</h2>
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                     <th class="table_heading">Name</th>
                                       <th class="table_heading">Location</th>
                                        <th class="table_heading">Mobile</th>
                                       	<th class="table_heading">Email</th>
                                        <th class="table_heading">Education</th>
                                       
                                       <th class="table_heading">Action</th>
                                        
                                 </tr>
                                </thead> 
                            </table>
                            </div>
   						 </div>
            </div>
        </div>
      </div>
     <!--   footer -->
   <%@include file="./shared/footer.jsp" %>
	
   
     <!-- popupmodal -->
   
    <div class="modal fade" id="popupmodal"  role="dialog" aria-labelledby="popupmodal" aria-hidden="true">
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Action</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error1' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       <!-- Error Message Ending -->
          <label>Select Action</label>
          <select id="action" class="form-control">
          <option>Select</option>
         <!--  <option value="1">View Attachment</option>--> 
           <option value="2">Create Application</option>
            
          </select>
          <br>
          <br>
          <!-- Assign Job to recruiter -->
          
			 
			 <!--Attachments  -->
			<div id="documents" style="display:none">
			<div id="doc1">
    		 <i class="fa fa-fw fa fa-file-text-o"></i>
			<a id="appdoc1" target="iframe_a" onclick="getoriginals()"></a>
			</div>
			<br>
			<div id="doc2">
			  <i class="fa fa-fw fa fa-file-text-o"></i>
			<a id="appdoc2" target="iframe_a" onclick="getoriginals1()"></a>
			</div>
			</div>
			 <!-- Attachments End -->
			
			   <!-- Insert Application -->
			   <div id="applications" style="display:none">
			   <label>Select Job Title</label>
			   <br>
			 <select class="form-control input-lg" id="jobtitle" style="width:300px;">
			 <option>Select Job Title</option>
			 </select>
			   <br>
			  <div class="form-group" style="text-align:center">
                    <div class="col-xs-12" style="margin-top:5px">
					  <button type="button" id="reg"  class="btn btn-success " onclick="insertapp();" style="margin-left:auto;margin-right:auto;">Submit</button>
					   
					   </div>
		                    </div>
			 
			   <!-- Insert Application -->
			   
			  
			   
			
          </div>
          <div class="modal-footer">
           
          </div>
        </div>
      </div>
    </div>
    </div>
   <!-- popupmodal -->
   
 
	<script>
	var applicantid;
	var otheruploadsimg = "data:application/pdf;base64,"
		
		 $('#success').text('');
	 	$('#error').text('');   
      $(document).ready(function() {
        var table;
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/user/getAllApplicants",
            dataType: "json",
            complete: function(data) {
                
                console.log(data);
               
                
              table=   $('#example').dataTable({
              
                   data : data.responseJSON,
                   rowReorder: {
                       selector: 'td:nth-child(2)'
                   },
               
                   colReorder: true,
                
            columns:[
            	
               	{ "data": null,
              		 "mRender" : function ( data, type, full ) {
                           var  name= full['firstname']+' '+full['lastname'];
                           var id = full['applicantid'];
                           return '<a  href="${pageContext.servletContext.contextPath}/user/alertapplicant?applicantid:'+id+'" target="_blank">' +name +'</a>';
              		 }
				},
                                {"data":"location"},
                                { "data": "contactno" },
                                { "data": "email" },
                                {"data":"highesteducation"},
                              
                                {"data": "applicantid", "width": "50px", "render": function (data) {
      								 return '<a id="arrow"  onclick="popupmodal('+data+')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';	
                                   }
                                }
                            /*	{"data": "applicantid", "width": "50px", "render": function (data) {
         								 return '<input id="btn-view" type="submit" value="Keywords" data-toggle="modal" data-target="#documentModal" onclick="getuploads('+data+')" />';
                            				
                            	}
                            	},
                            	{"data": "applicantid", "width": "50px", "render": function (data) {
    								 return '<input id="btn-view" type="submit" value="View Originals" data-toggle="modal" data-target="#" onclick="getoriginals('+data+')" />' 
    								 +  '<input id="btn-view" type="submit" value="View Originals1" data-toggle="modal" data-target="#" onclick="getoriginals1('+data+')" />';
                       				
                       	}
                       	} */
                                ],
                                responsive: true,
                                columnDefs: [
                                    { responsivePriority: 1, targets: 0 },
                                    { responsivePriority: 2, targets: -1 }
                                ]
                                
                               
            });
            
              
            },
            error: function () {
             alert('Error');
            }
             
        });
         
        });
     
      
      function popupmodal(data){
    	  $('#error1').text('');
    	  $('#success').text('');
	 		 $('#error').text('');
  		applicantid = data;
    	  $('#popupmodal').modal('show');   
    	 $('#appdoc1').text("");
    	 $('#appdoc2').text("");
    	  $.ajax({
 	         type: "POST",
 	         url: "${pageContext.servletContext.contextPath}/user/getAppDocuments",
 	        data:{applicantid:applicantid},
 	         dataType :"json",
 	         complete:function(data){
 	         console.log(data);
 	       
        	if (data.responseJSON.otheruploadname == null) {
				$("#doc2").css('display',
						'none');
			} else {
				$('#appdoc2').append(data.responseJSON.otheruploadname.replace(/[\d_]+/g, ''));
			}
        	 $('#appdoc1').append(data.responseJSON.uploadcvname.replace(/[\d_]+/g, ''));

		}
 	         
    	  })
      }
      $(document).ready(function(){
          $.ajax({
    	         type: "POST",
    	         url: "${pageContext.servletContext.contextPath}/user/getJobtitleNames",
    	         dataType :"json",
    	         complete:function(data){
    	         console.log(data);
    	         for (var i = 0; i < data.responseJSON.length; i++)
    	         { 
    	              $('#jobtitle').append($('<option>',
    	              {
    	                 value: data.responseJSON[i].jobid,
    	                 text : data.responseJSON[i].jobtitle
    	             }));
    	         }
    	         }
          })
           $('#jobtitle').select2();
      })
	         
	        
	         
	         
      function addcomment(){
    	  var comment = $('#comm').val();
    	  $.ajax({
  	         type: "POST",
  	         url: "${pageContext.servletContext.contextPath}/user/appaddComment",
  	        data:{applicantid:applicantid,addComment:comment},
  	         dataType :"json",
  	         complete:function(data){
  	         console.log(data);
  	         if(data.responseText == 'success'){
  	        	 $('#success').text('Comment added successfully!');
  	        	$('#popupmodal').modal('hide');
  	        	 $('#error').text('');
  	         }
  	         else if(data.responseText == 'fail'){
  	        	 $('#error1').text('Comment added failed!')
  	        	 $('#success').text('');
  	         }
  	         }
    	  })
    	  
    	  
      }
      
      function insertapp(){
    	  $('#success').text('');
	 		 $('#error').text('');
    	  $.ajax({
 			 type: "POST",
 		        url: "${pageContext.servletContext.contextPath}/user/createApplicantDirectly",
 		        dataType: "json",
 		        data:{applicantid:applicantid,jobid:$('#jobtitle').val()},
 		        complete: function(data) {
 		            console.log(data);
 		           if(data.responseText == 'success'){
 		  	        	 $('#success').text('Application created successfully!');
 		  	        	$('#popupmodal').modal('hide');
 		  	        	 $('#error').text('');
 		  	         }
 		  	         else if(data.responseText == 'fail'){
 		  	        	 $('#error1').text('Application is in progress!')
 		  	        	 $('#success').text('');
 		  	         }
 		        }
    	  })
    	  
    	  
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
		            otheruploadsimg += data.responseJSON.otheruploads;
	 var win = window.open();
	 win.document.write('<iframe src="' + voterimage + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
	
	
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
			           
		                voterimage += data.responseJSON.uplaodcv;
			            otheruploadsimg += data.responseJSON.otheruploads;
	 					var win = window.open();
						 win.document.write('<iframe src="' + otheruploadsimg + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>')
	
		        }
			})
		}
		       
		  $('#action').on('change', function() {
		 		 $('#success').text('');
		 		 $('#error').text('');
		 		  $('#error1').text('');
		       	if(this.value == 1){
		       	 $('#success').text('');
		 		 $('#error').text('');
		 		  $('#error1').text('');
		       		$('#documents').css('display','block');
		       		$('#comment').css('display','none');
		       		$('#applications').css('dispaly','none');
		       		
		       	}
		       	else if(this.value == 2){
		       	 $('#success').text('');
		 		 $('#error').text('');
		 		  $('#error1').text('');
		       		$('#applications').css('display','block');
		       		$('#documents').css('display','none');	
		       		$('#comment').css('display','none');
		       		
		       	}
		       	else if(this.value == 3){
		       	 $('#success').text('');
		 		 $('#error').text('');
		       		$('#documents').css('display','none');	
		       		$('#comment').css('display','block');
		       		$('#applications').css('dispaly','none');
		       			
		       	}
		       	
		       	
		      })
		      
      
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
   
