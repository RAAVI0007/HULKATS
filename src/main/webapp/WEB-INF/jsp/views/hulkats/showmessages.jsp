<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>

<div class="content-wrapper">
	<div class="container-fluid">
	
	<ol class="breadcrumb">
       
      
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/manager/managerdashboard">Dashboard</a>
				</li>
			
				
        <li class="breadcrumb-item" ><a
			id="msghref" href="">Message</a>
			</li>
			<li class="breadcrumb-item"><a>Message</a></li>
		</ol>
		<!-- Error Messages -->
		
		<!-- Error Message Ending -->
		<!-- Breadcrumbs-->
		
<div class="card card-register mx-auto mt-20" id="msgreg">
      			<div class="card-header"><h3 align="center">Messages</h3></div>
      				<div class="card-body">
                  <div class="panel-body body-panel">
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
</body>
<style>
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
var x = window.location.href;
console.log(x.substring(x.lastIndexOf(':') + 1));
    var id =x.substring(x.lastIndexOf(':') + 1);
    var i=0;
    $('a#msghref').attr('href','${pageContext.servletContext.contextPath}/user/showmessages/?id:'+id );
  $(document).ready(function() {
    var table;
    msggets();
  })
 function msggets(){
     $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/user/getUserMessage",
        dataType: "json",
        data:{userid:id},
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
    user.to_userid = id;
    var jsonfile={messageinfo:JSON.stringify(user)}
 	   $.ajax({
 	        type: "POST",
 	        url: "${pageContext.servletContext.contextPath}/user/messagesending",
 	        data:jsonfile,
 	        dataType: "json",
 	        complete: function(data) {
 	           console.log(data);
 	           if(data.responseText == 'success'){
 	        	  msggets();
 	        	 $('#messages').val("");
 	        	 $('#success1').text('Message sent successfully');
 	        	 $('#error').text("");       		
 	           }
 	           else{
 	        	   $('#error').text('Message sent failed');
 	        	   $('#success').text("");
 	           }
    }
 	   })
    }
    setInterval(function(){
 		msggets() // this will run after every 5 seconds
 	}, 5000);
</script>
</html>
