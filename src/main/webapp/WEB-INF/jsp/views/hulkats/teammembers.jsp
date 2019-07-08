
<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>


<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
			

			<li class="breadcrumb-item active">View Teams</li>
		</ol>
		<br>
		 <div class="form-group" style="text-align:center" >
        <h5><span id='success1' style="color:green;" colspan="2"></span>
       		<span id='error1' style="color:red; margin-left:auto;margin-right:auto;" colspan="2"></span></h5>
       </div>
        <div id="table" class="col-lg-12">
                        <strong>Team Name : <span id="title">${teamname1}</span></strong></div>
                        <br>
		<div class="row" id="recruiters">
			<div id="table" class="col-lg-12">
				<h2>Recruiters Overview</h2>
				<div id="usertable" class="table-responsive1">
					<table id="example1"
						class="table table-striped table-bordered nowrap" cellspacing="0"
						width="100%">
						<thead style="background: #ff6707; color: #fff">
							<tr>
								  <th class="table_heading"></th>
								<th class="table_heading">First Name</th>
								<th class="table_heading">Last Name</th>
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
	</div>
</div>
<!--   footer -->
<%@include file="./shared/footer.jsp"%>
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
    	$.ajax({
            type: "GET",
            url: "${pageContext.servletContext.contextPath}/user/teamname",
            dataType: "json",
            complete: function(data) {
            
            }
		})
       
 		$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/recruiter/getTeam",
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
                         		{ "data": "firstname"},
                                {"data":"lastname"},
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
    })
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

</body>

</html>