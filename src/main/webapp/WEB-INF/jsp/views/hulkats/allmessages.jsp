
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
				href="${pageContext.servletContext.contextPath}/user/allmessages">Messages</a>
			</li>
			<li class="breadcrumb-item"><a href="">View Messages</a></li>
		</ol>
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
		<!-- Breadcrumbs-->
		
		<div class="col-sm-4">
			<div class="panel panel-primary">
				<div class="form-group">
					<div class="input-group">
					 <select class="form-control select2-single" id="reclist">
							<option>Select Recruiters</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		
		
	
    		<div class="card card-register mx-auto mt-20" id="msgreg" style="display:none;">
      			<div class="card-header"><h3 align="center">Messages</h3></div>
      				<div class="card-body">
                  <div class="panel-body body-panel">
                    <!--  <ul class="chat" id="chat">
                    </ul>
                    -->
                       <ul class="chat" id="chat">
			</ul>
                    
                </div>
                <div class="panel-footer clearfix">
                   <br/> <textarea class="form-control" rows="3" id="messages"></textarea>
                   <br/>
                        <button class="btn btn-success btn-lg btn-block" id="btn-chat" onclick="sendmsg()">Send</button>
                   
                </div>
            </div>
        </div>
   </div>
   </div>
  
<%@include file="./shared/footer.jsp"%>

	


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
panel-primary {
    border-color: #337ab7;
}
/*.chat
{
    list-style: none;
    margin: 0;
    padding: 0;
}

.chat li
{
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-bottom: 1px dotted #B3A9A9;
}

.chat li.left .chat-body
{
    margin-left: 60px;
}

.chat li.right .chat-body
{
    margin-right: 100px;
}


.chat li .chat-body p
{
    margin: 0;
    color:#000000;
}*/
ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

.chat  li{
  display:inline-block;
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.him{
  background: #eee;
  float: left;
   word-wrap: break-word;
}

.me{
  float: right;
  background: #0084ff;
  color: #fff;
   word-wrap: break-word;
}

.him + .me{
  border-bottom-right-radius: 5px;
}

.me + .me{
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.me:last-of-type {
  border-bottom-right-radius: 30px;
}

.panel .slidedown .glyphicon, .chat .glyphicon
{
    margin-right: 5px;
}

.body-panel
{
    overflow-y: scroll;
    height: 400px;
}

::-webkit-scrollbar-track
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    background-color: #F5F5F5;
}

::-webkit-scrollbar
{
    width: 12px;
    background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb
{
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
    background-color: #555;
}
.card-register {
    max-width: 50rem;
}
p.test {
    width: 15em; 
    word-wrap: break-word;
}
</style>
<script>
 var userid;
 var recid;
$('#reclist').select2();
var i=0;
  $(document).ready(function() {
	  
         $.ajax({
      	type:"POST",
     	dataType :"json",
      	url:"${pageContext.servletContext.contextPath}/user/getRecruiter",
    	complete: function (data) {
    		console.log(data);
     	   console.log("recruiters" +data);
     	   for(var j = 0; j<data.responseJSON.length; j++){
     		var search = $('#reclist').append($('<option>', { 
     		        value: data.responseJSON[j].userid,
     		        text : data.responseJSON[j].name 
     		    }));
     		}
    	}
       })
       
  })
    
 
   $('#reclist').on('change', function() {
	   i =0;
	   $('#msgreg').css("display","block");
	   userid =  $('#reclist').val();
	   recid = $('#reclist').val();
	 	
	  	$("#chat").empty();
	 	 msggets(recid);
	 	setInterval(function(){
	 		msggets(recid) // this will run after every 5 seconds
	 	}, 5000);
		
   })
   function msggets(recid){
     $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/user/getUserMessage",
        dataType: "json",
        data:{userid:recid},
        complete: function(data) {
            console.log(data); 
           
            for(;i<data.responseJSON.length; i++){
            	
            	if(data.responseJSON[0].Name == data.responseJSON[i].Name){
            		console.log(i);
            		
                     
            	/*$("#chat").append(  '<li class="clearfix">'+
                        '<div class="chat-body clearfix"><div class="header">'+
                        ' <strong class="primary-font" id="name">'+data.responseJSON[i].Name+'</strong> <small class="text-muted" id="time"><span class="glyphicon glyphicon-time"></span>'+data.responseJSON[i].created_at +'</small>'+
                     '</div><p id="msg">'+data.responseJSON[i].message+'</p></div></li>')*/
                     $("#chat").append('<li class="him"><strong class="primary-font" id="name">'+data.responseJSON[i].Name+'</strong><br><p class="test">'+data.responseJSON[i].message+'</p><span class="fa fa-clock-o"></span> '+data.responseJSON[i].created_at +'</small></li>')}
            	else{
            		
            		/* $("#chat").append('<li class="clearfix"><div class="chat-body clearfix"><div class="header">'+
                             ' <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>'+data.responseJSON[i].created_at +'</small>'+
                             '<strong class="pull-right primary-font">'+data.responseJSON[i].Name+'</strong>'+
                         '</div><p align="right">'+data.responseJSON[i].message+'</p></div></li>')
                         */
                         
                         $("#chat").append('<li class="me"><strong class="primary-font" id="name">'+data.responseJSON[i].Name+'</strong><br><p class="test">'+data.responseJSON[i].message+'</p><span class="fa fa-clock-o"></span> '+data.responseJSON[i].created_at +'</small></li>')
            	} 
            	
            	
            }
            $(function() {
         	   var wtf    = $('.body-panel');
         	   var height = wtf[0].scrollHeight;
         	   wtf.scrollTop(height);
         	 });
        }
            })
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
 	        	
 	        	 $('#messages').val("");
 	        	 $('#success').text('Message sent successfully');
 	        		msggets();
 	        	 $('#error').text("");       		
 	           }
 	           else{
 	        	   $('#error').text('Message sent failed');
 	        	   $('#success').text("");
 	           }
    }
 	   })
    }
    var input = document.getElementById("messages");
    input.addEventListener("keyup", function(event) {
      event.preventDefault();
      if (event.keyCode === 13) {
        document.getElementById("btn-chat").click();
      }
    });
</script>

</html>
