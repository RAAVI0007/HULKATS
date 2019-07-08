
<%@include file="./shared/nav.jsp"%>

<div class="content-wrapper">
	<div class="container-fluid">
	
	<ol class="breadcrumb">
       
        <li class="breadcrumb-item">
          <a href="${pageContext.servletContext.contextPath}/manager/dashBord">Dashboard</a>
        </li>
        <li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/user/allmessages">Messages</a>
			</li>
			<li class="breadcrumb-item"><a href="">View Messages</a></li>
		</ol>
		<!-- Error Messages -->
		<div class="form-group" style="text-align: center">
			<h5>
				<span id='success' style="color: green;" colspan="2"></span> <span
					id='error'
					style="color: red; margin-left: auto; margin-right: auto;"
					colspan="2"></span> <b><span id="jobid" style="color: green;"
					colspan="2"></span></b>

			</h5>
		</div>
		<!-- Error Message Ending -->
		<!-- Breadcrumbs-->
		<div class="col-sm-4">
			<div class="panel panel-primary">

				<div class="form-group">

					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button"
								data-select2-open="single-prepend-text">
								<span class="fa fa-search"></span>
							</button>
						</span> <select class="form-control select2-single" id="reclist">
							<option>Select Recruiters</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<h2>Chat Messages</h2>
<div id="chat">


</div>
<div class="mail-box right-middle" id="mailicon" style="display:none;">
  <i class="fa" onclick="mails(this)"></i>
<form name="form" class="content" novalidate>
        <textarea rows="7" class="form-control" id="messages" placeholder="Say Something"></textarea>
        <div class="form-group" style="text-align:center">
                    <div class="col-xs-12" style="margin-top:5px">
        <button type="button" class="btn send" onclick="sendmsg();" style="margin-left:auto;margin-right:auto;">Send</button>
					   </div>
		                    </div>
    </form>
</div>
</div>
</div>
</div>
<%@include file="./shared/footer.jsp"%>
</body>
	
<style>
        
#container {
    border: 2px solid #dedede;
    background-color: #f1f1f1;
    border-radius: 5px;
    padding: 10px;
    margin: 10px 0;
     border-color: #ccc;
    background-color: #ddd;
}

#darker {
 border: 2px solid #dedede;
    background-color: #f1f1f1;
    border-radius: 5px;
    padding: 10px;
    margin: 10px 0;
     border-color: #ccc;
    background-color: #ddd;
    
}

#container::after {
    content: "";
    clear: both;
    display: table;
}

#darker::after{
 content: "";
    clear: both;
    display: table;

}

#container img {
    float: left;
    max-width: 60px;
    width: 100%;
    margin-right: 20px;
    border-radius: 50%;
}

#container img.right {
    float: right;
    margin-left: 20px;
    margin-right:0;
}

.time-right {
    float: right;
    color: #aaa;
}

.time-left {
    float: left;
    color: #999;
}
.mail-box {
            background-color: #fff;
            position: fixed;
            border-radius: 100%;
            height: 50px;
            width: 50px;
            text-align: center;
            cursor: pointer;
            transition: all 0.50s;
            line-height: 55px;
            box-shadow: #000 1px 1px 7px 0px;
            overflow: hidden;
            z-index: 1000;
        }

        .mail-box.effect {
            cursor: default;
            opacity: 0.2;
        }

        .mail-box.active {
            cursor: default;
            line-height: inherit;
            padding: 15px;
            width: 300px;
            height: 230px;
            border-radius: 5px;
            opacity: 1;
        }
        
        .mail-box.right-middle {
            right: 15px;
            top: 50%;
            transform: translate(0, -50%)
        }

        .mail-box.right-middle.effect {
            right: 100px;
            top: 50%;
            transform: translate(0, -50%)
        }

        .mail-box.right-middle.active {
            right: 30px;
            top: 50%;
            transform: translate(0, -50%)
        }
        .mail-box.sent {
            background-color: #40c38d !important;
            line-height: 60px;
        }

        .mail-box.sent i:before {
            content: "\f00c";
            color: #fff;
            font-size: x-large;
        }

        .mail-box i:before {
            content: "\f0e0";
            color: #ff8f00;
            font-size: x-large;
        }
.mail-box.active i {
            cursor: pointer;
            position: absolute;
            right: 15px;
            z-index: 10;
        }
        .mail-box.active i:before {
            content: "\f00d";
        }
        .mail-box.active .content {
            text-align: left;
            color: #888;
            position: relative;
            height: 100%;
            overflow: hidden;
        }
 .mail-box.active .content input.form-control, .mail-box.active .content textarea.form-control {
            color: #888;
            border: none;
            resize: none;
            outline: none;
            font-family: inherit;
            font-size: medium;
            box-shadow: none;
            margin: 0;
        }
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

<script>
var userid;
$('#reclist').select2();
var i=0;
  $(document).ready(function() {
            
             $.ajax({
      	type:"POST",
     	dataType :"json",
      	url:"${pageContext.servletContext.contextPath}/recruiter/getRecruiter",
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
	   $('#mailicon').css("display","block");
	 	 userid = $('#reclist').val()
	  	$("#chat").empty();
	 	 
	 	 msggets();
	 	setInterval(function(){
	 		
	 		msggets() // this will run after every 5 seconds
	 	}, 5000);
	 	 function msggets(){
     $.ajax({
        type: "POST",
        url: "${pageContext.servletContext.contextPath}/user/getUserMessage",
        dataType: "json",
        data:{userid:userid},
        complete: function(data) {
            console.log(data); 
            for(;i<data.responseJSON.length; i++){
            	if(data.responseJSON[0].Name == data.responseJSON[i].Name )
            	$("#chat").append('<div class="container" id="container"><strong><span>'+data.responseJSON[i].Name+'</span></strong><p>'+data.responseJSON[i].message+'</p><span class="time-right">'+data.responseJSON[i].created_at +'</span></div>');
            	else{
            		 $("#chat").append('<div class="container darker" id="darker"><strong><span style="float:right">'+data.responseJSON[i].Name+'</span></strong><br/><p align="right">'+data.responseJSON[i].message+'</p><span class="time-left">'+data.responseJSON[i].created_at +'</span></div>');	
            	}       
            }
        }
            })
   }
   })
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
    var mails = function ($this) {
        var self = $($this).closest(".mail-box");
        var isActive = $(self).hasClass("active");
        if (!isActive) {
            openMailBox(self);
           
        } else {
            closeMailBox(self);
        }
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
 	        	  sendMail(self);
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
    
</script>

</html>