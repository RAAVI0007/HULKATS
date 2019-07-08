<!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
       <!-- Error Messages -->
      <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
       <!-- Error Message Ending -->
      <!-- Breadcrumbs-->
    <div class="row" style="overflow-x:auto;">
    
				        <div id="table" class="col-lg-12">
                        <strong>Jobs Title: <span id="title"></span></strong>
                        <br>
                        <strong>List of candidates found: <span id="count"></span></strong>
                        
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                       <th class="table_heading"> Name</th>
                                       <th class="table_heading">Email</th>
                                       	<th class="table_heading">Mobile</th>
                                    	<th class="table_heading">Location</th>
                                    	<th class="table_heading">Education</th>
                                    	<th class="table_heading">Current/Last Job</th>
                                    	
                                        <th class="table_heading" data-priority="1">Actions</th> 
                                    </tr>
                                </thead> 
                            </table>
                            </div>
    				</div>
            </div>
   
        
   <!-- popupmodal -->
   
    <div class="modal fade" id="popupmodal" tabindex="-1" role="dialog" aria-labelledby="popupmodal" aria-hidden="true">
      <div class="modal-dialog modal-lg"" role="document">
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
          <option value="1">View Attachments</option>
           <option value="2">Create Application</option>
           
             
          </select>
          <br>
          <br>
         
         
			 
			 <!--Attachments  -->
			<div id="documents" style="display:none">
			 
        <label><strong>Job Documents</strong></label>
        <br>
        <div id="doc1">
			  <i class="fa fa-fw fa fa-file-text-o" ></i>
			<a id="jobdoc1" target="iframe_a" onclick="orginals1()"></a>
			</div>
			
			<div id=doc2>
			  <i class="fa fa-fw fa fa-file-text-o" ></i>
			<a id="jobdoc2" target="iframe_a" onclick="orginals2()"></a>
			</div>
			
			<div id=app1>
			<label><strong>Applicant Documents</strong></label>
			<br>
			  <i class="fa fa-fw fa fa-file-text-o" ></i>
			<a id="appdoc1"  onclick="apporginals1()"></a>
			<br>
			</div>
			<div id=app2>
			  <i class="fa fa-fw fa fa-file-text-o" ></i>
			<a id="appdoc2"  onclick="apporginals2()"></a>
			</div>
			
			</div>
			 <!-- Attachments End -->
			
			   <!-- Create Application -->
			   <div class="form-group" id="appstart" style="text-align:center;display:none;">
                    <div class="col-xs-12" style="margin-top:5px">
			      <button type="button" class="btn btn-success" id="register" onclick="createapp();" style="margin-left:auto;margin-right:auto;">Submit</button>
			   </div>
			   <!--Create Application End -->
			  
			  
          </div>
          <div class="modal-footer">
           
          </div>
        </div>
      </div>
    </div>
    </div>
   <!-- popupmodal -->
   </div>
        
      </div>
     
    
   
	
  <%@include file="./shared/footer.jsp" %>
	<script>
	var recname;
	var applicantid;
	var applicationid;
	var x = window.location.href;
	console.log(x.substring(x.lastIndexOf(':') + 1));
        var jobid =x.substring(x.lastIndexOf(':') + 1);
      $(document).ready(function() {
        var table;
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/user/getApplicants",
            dataType: "json",
            data:{jobid:jobid},
            complete: function(data) {
                console.log(data); 
                $('#count').text(data.responseJSON.length);
              table=   $('#example').dataTable({
               data : data.responseJSON, 
               rowReorder: {
                   selector: 'td:nth-child(2)'
               },
               responsive: {
                   details: {
                       type: 'column',
                       target: -1
                   }
               },
              
            columns:[
            	{ "data": null,
           		 "mRender" : function ( data, type, full ) {
                        return full['firstname']+' '+full['lastname'];
           		 }
            	},
            	{ "data": "email" },
                { "data": "contactno" },
                {"data":"location"},
                {"data":"highesteducation"},
                { "data": "lastjobtitle" },
               
                        //  {"data": "applicantid", "width": "50px", "render": function (data) {
   								// return '<div id="controls"><a id="arrow"  onclick="popupmodal('+data+')"><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a></div>';	
                               // }
                               // }//
                                { "data": null,
                              		 "mRender" : function ( data, type, full ) {
                              			 var set = full['applicantid']+'.'+full['applicationid'];
                              				if(full['status'] == 1 ){
                                            return '<div id="controls"><a id="arrow" ><img src="<c:url value='/resources/images/Button-Next-icon.png' />" class="disabled" alt="alternative text" title="Application has been already created" /></a></div>';    
                              				}
                              				else{
                              					 return '<a id="arrow"  onclick="popupmodal('+ set +') "><img src="<c:url value='/resources/images/Button-Next-icon.png' />" /></a>';
                              				}
                              				}
                                }
                                ],
               responsive: true,
               columnDefs: [
                   { responsivePriority: 1, targets: 0 },
                   { responsivePriority: 2, targets: -1 }
               ]
            })
          }
             
        });
       
        
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/user/getjobtitle",
            dataType: "json",
            data:{jobid:jobid},
            complete: function(data) {
                console.log(data);
                $('#title').text(data.responseJSON.jobtitle); 
            }
        });
        });
      
      //popupmodal function
      function popupmodal(data){
     	var id = data.toString().split('.');
    	applicantid = id[0];
    	applicationid = id[1];
    	  $('#popupmodal').modal('show'); 
    	  $("#jobdoc1").text("");
          $('#jobdoc2').text("");
          $('#appdoc1').text("");
          $('#appdoc2').text("");
    	  $.ajax({
              type: "POST",
              url: "${pageContext.servletContext.contextPath}/user/getAllDocuments",
              dataType: "json",
              data:{jobid:jobid,applicantid:applicantid},
              complete: function(data) {
                  console.log(data);
                  if(data.responseJSON.uploaddocname2 == null){
		            	$("#doc2").css('display','none');
		            }
		            else{
		            	 $('#jobdoc2').append(data.responseJSON.uploaddocname2.replace(/[\d_]+/g, ''));
		            }
		            if(data.responseJSON.otheruploadname == null){
		            	$("#app2").css('display','none');
		            }
		            else{
		            	 $('#appdoc2').append(data.responseJSON.otheruploadname.replace(/[\d_]+/g, ''));
		            }
                  
                  
                  $("#jobdoc1").append(data.responseJSON.uploaddocname1.replace(/[\d_]+/g, ''));
                 
                  $('#appdoc1').append(data.responseJSON.uploadcvname.replace(/[\d_]+/g, ''));
                 
              }
    	  }); 
    	  
      }
      
      $('#action').on('change', function() {
 		 
       	if(this.value == 1){
       		
       		$('#documents').css('display','block');
       		$('#appstart').css('display','none');
       			
       	}
       	else if(this.value == 2){
       		
       		$('#documents').css('display','none');	
       		$('#appstart').css('display','block');
       	}
       	
      })
       function orginals1(){
    	 
    	  $.ajax({
              type: "POST",
              url: "${pageContext.servletContext.contextPath}/user/sendJobsDocuments",
              dataType: "json",
              data:{jobid:jobid},
              complete: function(data) {
                  console.log(data);
                  var uploaddocument1 = "data:application/pdf;base64,";
		           
                  uploaddocument1 += data.responseJSON.uploaddocument1;
		          
					var win = window.open();
					win.document.write('<iframe src="' + uploaddocument1 + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
      }
    	  })
      }
      function orginals2(){
    	 
    	  $.ajax({
              type: "POST",
              url: "${pageContext.servletContext.contextPath}/user/sendJobsDocuments",
              dataType: "json",
              data:{jobid:jobid},
              complete: function(data) {
                  console.log(data);
                  var uploaddocument1 = "data:application/pdf;base64,";
		           
                  uploaddocument1 += data.responseJSON.uploaddocument2;
		          
					var win = window.open();
					win.document.write('<iframe src="' + uploaddocument1 + '" name="iframe_a" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
      }
    	  })
      }
      
      function apporginals1(){
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
	
	
		        }
		       
		});
		}
		function apporginals2(data){
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
	
		        }
			})
		}
		 $('#success').text('');
 		 $('#error').text('');
	 
	  
		function createapp(){
			 $.ajax({
				 type: "POST",
			        url: "${pageContext.servletContext.contextPath}/user/createApplicantDirectly",
			        dataType: "json",
			        data:{jobid:jobid,applicantid:applicantid},
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
		 var openMailBox = function (self) {
		        $(self).addClass("effect");
		        setTimeout(function () {
		            $(self).addClass("active");
		            $(self).removeClass("effect");
		            setTimeout(function () {
		                $(self).find(".content").show();
		            }, 250)
		        }, 200);
		    }

		    var closeMailBox = function (self) {
		        $(self).find(".content").hide();
		        $(self).removeClass("active");
		        setTimeout(function () {
		            $(self).removeClass("effect");
		        }, 100);
		    }

		    var sendMail = function (self) {
		        $(self).find(".send").click(function () {
		            $(self).addClass("sent");
		            closeMailBox(self);
		            setTimeout(function () {
		                $(self).removeClass("sent");
		            }, 1200)
		        })
		    }

		    var mail = function ($this) {
		        var self = $($this).closest(".mail-box");
		        var isActive = $(self).hasClass("active");
		        if (!isActive) {
		            openMailBox(self);
		            sendMail(self);
		        } else {
		            closeMailBox(self);
		        }
		    }
    </script>
    <style>
   img.disabled
{
    opacity:0.5;
}
    
    </style>
   
