<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>HULKATS</title>
  <meta charset="utf-8">
  <link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='/resources/images/favicon.png' />">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.4/css/mdb.min.css" rel="stylesheet">
<!-- JQuery -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.4/js/mdb.min.js"></script>
  <style>
   .blink {
      animation: blink 2s steps(5, start) infinite;
      -webkit-animation: blink 1s steps(5, start) infinite;
    }
    @keyframes blink {
      to {
        visibility: hidden;
      }
    }
    @-webkit-keyframes blink {
      to {
        visibility: hidden;
      }
    }
    
    @media only screen and (max-width: 720px) {
    #left {
        margin-right: 85%;
        
    }
}
 @media only screen and (max-width: 400px) {
    #left {
        margin-right: 80%;
        
    }
}
 @media only screen and (min-width: 720px) {
    #left {
        margin-right: 87%;
    }
}
p{
font-size: 18px;
}


  </style>
</head>
<body>


 
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
       <div class="modal-header" style="background-color: #00c851;">
            <p class="heading lead" align="center" ><b><h4>Success</h4></b></p>
        </div>
        <div class="modal-body">
            <div class="text-center">    
               <b> <p id="matter">Welcome to <br/><img src="<c:url value='/resources/images/HulkATS_logo.png'/>"
								style="width: 250px;" /><br/>Your social recruiting partner</p></b>
            </div>
        </div>

        <!--Footer-->
        <div class="modal-footer" id="footer">
         
          <a onclick="next();" id="right"><i class="fa fa-arrow-circle-right fa-2x mb-3 animated rotateIn blink"  style="color:green"></i></a>
             
        </div>
    </div>
      </div>
      
    </div>
 
<script>
var count = 1;
$('#myModal').modal("show");

function next(){
	$("#tick").remove();
	$("#images").remove();
	count++;
	 if(count == 2){
		$('#matter').empty();
		$('#footer').prepend('<a onclick="last();"  id="left"><i class="fa fa-arrow-circle-left fa-2x mb-3 animated rotateIn "  style="color:green"></i></a>')
		$('.text-center').prepend('<i class="fa fa-check fa-4x mb-3 animated rotateIn" id="tick" style="color:green"></i>');
		$('#matter').text('Your registration has been successful');	
	}else if(count == 3){
		$('#matter').empty();
		$("#tick").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/freetrial.jpg'/>" style="width: 200px;">');
		$('#matter').html('<h3>Congratulations!...</h3> <br/>You have been selectively categaroized for  free trial <br/>Under special customer feedback improvment program');	
	}
	else if(count == 4){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/gift.gif'/>" style="width: 200px;">');
		$('#matter').text("Let's see what is waiting for you...");	
	}
	else if(count == 5){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/images.png'/>" style="width: 200px;">');
		$('#matter').text('Full access to HULKATS platform');	
	}
	else if(count == 6){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/tenhour.png'/>" style="width: 200px;">');
		$('#matter').text('Customer specific development ');	
	}
	else if(count == 7){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/three.png'/>" style="width: 200px;">');
		$('#matter').text('customer consultation meeting');	
	}
	else if(count == 8){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/seven.jpg'/>" style="width: 200px;">');
		$('#matter').text('Technical Support');	
	}else if(count == 9){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<i class="fa fa-check fa-4x mb-3 animated rotateIn" id="tick" style="color:green"></i>');
		$('#matter').text('Online training and user guide');	
	}else if(count == 10){
		$("#tick").remove();
		$("#images").remove();
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/email.jpg'/>" style="width: 200px;">');
		$('#matter').text('Email will be awaiting in your inbox. Please follow the instructions for next steps.');	
	}else if(count == 11){
		$('#right').empty();
		$('#matter').empty();
		$('#right').html('<a href="${pageContext.servletContext.contextPath}/login/login" id="right">Login</a>');
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/thanksicon.jpg'/>" style="width: 200px;">');
		$('#matter').html('A Big thanks <br/>from <br /><img id="images" src="<c:url value='/resources/images/Hulklogic.gif'/>" style="width: 200px;">');	
	}
	
	
	
}
function last(){
	$("#tick").remove();
	$("#images").remove();
	count--;
	if(count == 1){
		$('#matter').empty();
		$('#matter').html('Welcome to <br/><img src="<c:url value='/resources/images/HulkATS_logo.png'/>" style="width: 250px;" /><br/>Your social recruiting partner');	
	}
	if(count == 2){
		$('#matter').empty();
		$('.text-center').prepend('<i class="fa fa-check fa-4x mb-3 animated rotateIn" id="tick" style="color:green"></i>');
		$('#matter').text('Your registration has been successful');	
	}else if(count == 3){
		$('#matter').empty();
		$("#tick").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/images.png'/>" style="width: 200px;">');
		$('#matter').html('<h3>Congratulations!...</h3> <br/>You have been selectively categaroized for  free trial <br/>Under special customer feedback improvment program');	
	}
	else if(count == 4){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/gift.gif'/>" style="width: 200px;">');
		$('#matter').text("Let's see what is waiting for you...");	
	}
	else if(count == 5){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/images.png'/>" style="width: 200px;">');
		$('#matter').text('Full access to HULKATS platform');	
	}
	else if(count == 6){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/tenhour.png'/>" style="width: 200px;">');
		$('#matter').text('Customer specific development ');	
	}
	else if(count == 7){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/three.png'/>" style="width: 200px;">');
		$('#matter').text('customer consultation meeting');	
	}
	else if(count == 8){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/seven.jpg'/>" style="width: 200px;">');
		$('#matter').text('Technical Support');	
	}else if(count == 9){
		$('#matter').empty();
		$("#images").remove();
		$('.text-center').prepend('<i class="fa fa-check fa-4x mb-3 animated rotateIn" id="tick" style="color:green"></i>');
		$('#matter').text('Online training and user guide');	
	}else if(count == 10){
		$("#tick").remove();
		$("#images").remove();
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/email.jpg'/>" style="width: 200px;">');
		$('#matter').text('Email will be awaiting in your inbox. Please follow the instructions');	
	}else if(count == 11){
		$('#matter').empty();
		$('.text-center').prepend('<img id="images" src="<c:url value='/resources/images/thanksicon.jpg'/>" style="width: 200px;">');
		$('#matter').text('A Big Thanks for letting the whole hulklogic team to serve you');	
	}
	
	
}
</script>
</body>
</html>