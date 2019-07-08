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
          <a href="${pageContext.servletContext.contextPath}/manager/users">Registration</a>
        </li>
        <li class="breadcrumb-item active">View Accounts</li>
      </ol>
   <br>
    <div class="form-group" style="text-align:center" >
        <h5><span id='success1' style="color:green;" colspan="2"></span>
       		<span id='error1' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
        <div id="teamnames" class="col-lg-12">
                        <strong>Team Name : <span id="teamtitle"></span></strong></div>
                        <br>
 <div class="form-group">
            <div class="form-row">
           
			  <div class="col-md-1">
	
  <label for="role"><strong>Select Role</strong></label>
  </div>
   <div class="col-md-2">
   <select id="role" class="form-control">
  
   <option value="1">Manager</option>
   <option value="2" selected>Recruiter</option>
    <option value="3" >New Recruiter</option>
   
    </select>
   
   </div>
   </div>
   </div>
   <!-- Dropdown 
       <div class="dropdown">   
  	<button class=" dropdown-toggle" type="button" data-toggle="dropdown">Select Role<span class="caret"></span></button>
  	<ul class="dropdown-menu" id="role">
    <li><a href="#" id="manager">Manager</a></li>
    <li><a href="#" id="recruiter">Recruiter</a></li>
   
  </ul>
</div> -->
<br>
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <!-- Breadcrumbs-->
    <div class="row" id="managers" style="overflow-x:auto; display:none;">
				        <div id="table" class="col-lg-12">
                        <h2>Managers Overview</h2>
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                      	<th class="table_heading">First Name</th>
                                        <th class="table_heading">Last Name</th>
                                        <th class="table_heading">Email</th>
                                        <th class="table_heading">Mobile</th>
                                        <th class="table_heading">Team</th>
									   	<th class="table_heading">Location</th>
									   	
                                    </tr>
                                </thead> 
                            </table>
                            </div>
    	</div>
            </div>
            <div class="row" id="recruiters" style="overflow-x:auto;display:none;" >
            			
				        <div id="table" class="col-lg-12">
                        <h2>Recruiters Overview</h2>
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example1" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                        <th class="table_heading"></th>
                                      	<th class="table_heading">Name</th>
                                        <th class="table_heading">Email</th>
                                        <th class="table_heading">Mobile</th>
									   	<th class="table_heading">Location</th>
											<th class="table_heading">Message</th>

                                    </tr>
                                </thead> 
                            </table>
                            </div>
    	</div>
            </div>
            <div class="row" id="recruitersbench" style="overflow-x:auto;display:none;" >
				        <div id="table" class="col-lg-12">
                        <h2>Recruiters Overview</h2>
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example2" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                    
                                      	<th class="table_heading">First Name</th>
                                        <th class="table_heading">Last Name</th>
                                        <th class="table_heading">Email</th>
                                        <th class="table_heading">Mobile</th>
                                        <th class="table_heading">Team</th>
									   	<th class="table_heading">Location</th>
									   	<th class="table_heading">Actions</th>

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
    
    <div class="modal fade" id="emailassign" tabindex="-1" role="dialog" aria-labelledby="emailassign" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Assign Manager</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">X</span>
            </button>
          </div>
          <div class="modal-body">
          <h5><span id='error1' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
          <p>Are you ready to take this Recruiter?</p>
          </div>
          <div class="modal-footer">
           <input id="btn-view" class="btn btn-success" type="submit" value="Confirm"  onclick="assignrec()" /> 
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="emailassign" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Message</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">X</span>
            </button>
          </div>
          <div class="modal-body">
          <div class="form-group" style="text-align:center" >
        <h5><span id='success' style="color:green;" colspan="2"></span>
       		<span id='error' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
    	<form name="form" class="content" novalidate>
        <textarea rows="7" class="form-control" id="messages"placeholder="Say Something"></textarea>
        <br>
        <div class="form-group" style="text-align:center">
                    <div class="col-xs-12" style="margin-top:5px">
        <button type="button" class="btn btn-success" onclick="sendmsg();" style="margin-left:auto;margin-right:auto;">Send</button>
					   </div>
		                    </div>
    	</form>
		</div>

          </div>
          <div class="modal-footer">
          
          </div>
        </div>
      </div>
    
	

	<script>
	
	var userid;
	var managerdata;  
    $('document').ready(function(){
    	$('#managers').css('display','none');
 		$('#recruiters').css('display','block');
 		$('#recruitersbench').css('display','none');
 		$('#teamnames').css('display','block');
 		
 			   $.ajax({
 		           url: "${pageContext.servletContext.contextPath}/manager/existTemaname",
 		            type: 'POST',
 		            dataType: "json",
 		            complete:function(data){
 		            	$('#teamtitle').text(data.responseText);
 		            }
 			   })  
 		
 		recruiterfuc();
 		 $.ajax({
             type: "GET",
             url: "${pageContext.servletContext.contextPath}/user/teamname",
             dataType: "json",
             complete: function(data) {
             
             }
 		})
        
    })
	   $('#role').on('change', function() {
		 $('#success').text('');
		 $('#error').text('');
     	if(this.value == 1){
     		$('#teamnames').css('display','none');
     		$('#managers').css('display','block');
     		$('#recruiters').css('display','none');
     		$('#recruitersbench').css('display','none');
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/manager/getManagers",
            data:{"rolename":"ROLE_MANAGER"},
            dataType: "json",
            complete: function(data) {
               console.log(data);
              table=   $('#example').dataTable({
               data : data.responseJSON,
               "bDestroy": true,
               columns:[
                         		{ "data": "firstname"},
                                {"data":"lastname"},
                                { "data": "email" },
                                { "data": "contactno" },
                                { "data": "teamname1" },
                                {"data":"city"},
                                
                                ],
								rowReorder: {
					            selector: 'td:nth-child(2)'
					        },
					        responsive: true,
					        "order":[[3,"desc"]]
            })
          },
            error: function () {
             alert('Error');
            }
             
        });
     	} 
     	else if(this.value == 2){
     		$('#teamnames').css('display','block');
     		$('#managers').css('display','none');
     		$('#recruiters').css('display','block');
     		$('#recruitersbench').css('display','none');
     		recruiterfuc()
     	}
     	else if(this.value == 3){
     		$('#teamnames').css('display','none');
     		$('#managers').css('display','none');
     		$('#recruiters').css('display','none');
     		$('#recruitersbench').css('display','block');
     		recruiterbench();
        }
     	})
        
    function assign(data){
    	console.log(data);
    	userid = data;
    }
    
    $("#manageremail").keyup(function(){
   	 var name = $("#manageremail").val();
   	 $("#manageremail").autocomplete({
   		 minLength :2,
   		 source:function(request,response){
   			 if(name.length > 1){
   $.ajax({
       type: "POST",
       url: "${pageContext.servletContext.contextPath}/manager/getManagerNames",
      data:{managername:name},
       dataType :"json",
       complete:function(data){
			 var values = [];
			 console.log(data);
			 managerdata = data;
			 for(var i=0;i<data.responseJSON.length;i++){
				 values.push(data.responseJSON[i].name)
			 }
			 response(values);
		 } 
	 })	 
} 
}	 
})
    });
    function assignrec(){
    	
    $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/manager/assignteam",
       data:{userid:userid},
        dataType :"json",
        complete:function(data){
        	console.log(data);
			 if(data.responseText == 'success'){
				 $('#success1').text( 'Recruiter is assigned successfully');
				 $('#emailassign').modal('hide');
				 setTimeout(function() {
					    location.reload();
					}, 5000);
			 }	
			 else if(data.responseText == 'fail'){
				 $('#success1').text('');
				$('#error1').text('Recruiter assignment failed!');
			 }

}
 })
    };
    var recruiterbench =function(){
 		$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/manager/getRecruitersBench",
            data:{"rolename":"ROLE_RECRUITER"},
            dataType: "json",
            complete: function(data) {
              
              table=   $('#example2').dataTable({
               data : data.responseJSON,
               "bDestroy": true,
               columns:[
                         		{ "data": "firstname"},
                                {"data":"lastname"},
                                { "data": "email" },
                                { "data": "contactno" },
                                { "data": "teamname" },
                                {"data":"city"},
                                {"data": "userid", "width": "50px", "render": function (data) {
      								 return '<input id="btn-view" type="submit" value="Assign Manager" data-toggle="modal" data-target="#emailassign" onclick="assign('+data+')" />'; 
      								
                         	}
                         	}
                                ],
								rowReorder: {
					            selector: 'td:nth-child(2)'
					        },
					        responsive: true,
					        "order":[[3,"desc"]]
            })
          },
            error: function () {
             alert('Error');
            }
             
        });
    	
    }
   var recruiterfuc = function(){
	   
	   $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/manager/getRecruiters",
        data:{"rolename":"ROLE_RECRUITER"},
        dataType: "json",
        complete: function(data) {
           console.log(data);
          table=   $('#example1').dataTable({
           data : data.responseJSON,
           "bDestroy": true,
           columns:[
        	   				 { "data": null,
                       		 "mRender" : function ( data, type, full ) {
                    			 var active1 = full['active1'];
                    			 var active2 = full['active2'];
                    			 if((active1 == null|| active1 == 0) && (active2 == null|| active2 == 0)){
                    				
                    				 return '<div id="controls"><i class="fa fa-circle text-danger"></i></div>';
                    			 }else if(active1 == 1 && (active2 == null|| active2 == 0||active2 == 2)) {
                    				
                    				 return '<div id="controls"><i class="fa fa-circle text-success"></i></div>';
                    			 }
                    			 else if((active1 == null|| active1 == 0||active1 == 2)&& active2 == 1) {
                    				
                    				 return '<div id="controls"><i class="fa fa-circle text-success"></i></div>';
                    			 }
                    			 else if(active1 == 1 && active2 == 1) {
                    				
                    				 return '<div id="controls"><i class="fa fa-circle text-success"></i></div>';
                    			 }
                    			 else if(active1 == 2 && (active2 == null|| active2 == 0||active2 == 2)){
                    				 
                    				 return '<div id="controls"><i class="fa fa-circle text-warning"></i></div>';
                    			 }
                    			 else if((active1 == null|| active1 == 0||active1 == 2) && active2 == 2){
                    				 
                    				 return '<div id="controls"><i class="fa fa-circle text-warning"></i></div>';
                    			 }
                      }
                         },
                         { "data": null,
                       		 "mRender" : function ( data, type, full ) {
                                    return full['firstname']+' '+full['lastname'];
                       		 }
                        	},
                            { "data": "email" },
                            { "data": "contactno" },
                            {"data":"city"},
                            { "data": null,
                                  "render": function(data, type, full, meta){
                            	var a = '<div id="controls"><a id="arrow"  onclick="msg('+full["userid"]+')"><img src="<c:url value='/resources/images/message.png' />" /></a></div>';
                            	return a;
                            	 }
                            }
                            ],
							rowReorder: {
				            selector: 'td:nth-child(2)'
				        },
				        responsive: true,
				        "order":[[3,"desc"]]
        })
      },
        error: function () {
         alert('Error');
        } 
    });
    }
    	
   function msg(set){
	   userid = set;
	   $('#error').text('');
	   $('#success').text("");
	   $('#error1').text('');
	   $('#success1').text("");
   	  $('#message').modal('show');  
   }
   function sendmsg(){
	   $('#error').text('');
	   $('#success').text("");
	   $('#error1').text('');
	   $('#success1').text("");
	   $('#messages').attr('style',"");
 	  user={}
 	  
 	  if($('#messages').val()=='' || $('#messages').val().trim()==''){
 			$('#error').text('Message is required !');
 			$('#error1').text('Message is required !');
 			$('#messages').attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
 			return;	
 		}else{
 			user.message =$('#messages').val();		
 		}
 	  
   user.to_userid = userid;
   var jsonfile={messageinfo:JSON.stringify(user)}
	 
	   $.ajax({
	        type: "POST",
	        url: "${pageContext.servletContext.contextPath}/user/messagesending",
	        data:jsonfile,
	        dataType: "json",
	        complete: function(data) {
	           console.log(data);
	           if(data.responseText == 'success'){
	        	 $('#success1').text('Message sent successfully');
	        	 $('#error').text("");
	        	 $('#message').modal('hide');
	           }
	           else{
	        	   $('#error').text('Message sent failed');
	        	   $('#success').text("");
	           }
   }
	   })
   }
   
    </script>
   <style>
    .content {
            text-align: left;
            color: #343a40;
            position: relative;
            height: 100%;
            overflow: hidden;
        }

         .content input.form-control,  .content textarea.form-control {
            color: #343a40;
            border: none;
            resize: none;
            outline: none;
            font-family: inherit;
            font-size: medium;
            box-shadow: none;
            margin: 0;
        }

        .content hr {
            margin: 5px 0;
        }

         .content .send {
            position: absolute;
            bottom: 0;
        }

         .content .send.btn-link {
            color: #343a40;
        }
   
   </style>
</body>

</html>