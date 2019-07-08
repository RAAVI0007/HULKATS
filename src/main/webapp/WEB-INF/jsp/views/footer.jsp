<footer class="sticky-footer">
      <div class="container">
        <div class="text-right">
          <strong><span id='copyright'>Copyright </span>© HULKLOGIC 2017
         <span style="float:left;color:red;" id="issues">Any issues</span><span style="float:left;color:red;">?</span>
         <a href="${pageContext.servletContext.contextPath}/user/support" style="float:left; color:#ff8f00"><strong>&nbsp;HULKSupport</strong></a>
        </strong>
          </div>
      </div>
    </footer>
  
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
           
			<a  class="btn btn-primary" onclick="logout();">Logout</a>
          </div>
        </div>
      </div>
    </div>
    
     <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
           
			<a href="${pageContext.servletContext.contextPath}/login/logout" class="btn btn-primary" onclick="logout();">Logout</a>
          </div>
        </div>
      </div>
    </div>
    
    <div>
    </div>
       
      
    <!-- Bootstrap core JavaScript-->
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/popper/popper.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.min.js' />"></script>
    <!-- Core plugin JavaScript-->
    <script type="text/JavaScript" src="<c:url value='/resources/vendor/jquery-easing/jquery.easing.min.js' />"></script>
    <!-- Page level plugin JavaScript-->
   
    <script type="text/JavaScript" src="<c:url value='/resources/js/sb-admin.min.js' />"></script>
    <script type="text/JavaScript" src="<c:url value='/resources/js/raphael.min.js'/>"></script>
 <!--   <script type="text/JavaScript" src="<c:url value='/resources/js/morris.min.js'/> "></script>
	<script type="text/JavaScript" src="<c:url value='/resources/js/morris-data.js'/> "></script> -->
     <script type="text/JavaScript" src="<c:url value='/resources/js/jquery-ui.js' />"></script> 
     <script type="text/JavaScript" src="<c:url value='/resources/js/loader.js' />"></script>
     <script type="text/JavaScript" src="<c:url value='/resources/js/jsapi.js' />"></script>
     <script type="text/JavaScript" src="<c:url value='/resources/js/Chart.min.js' />"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/js/select2.min.js"></script>
      <script src="<c:url value='/resources/vendor/datatables/jquery.dataTables.min.js' />"></script>
       <script type="text/JavaScript" src="<c:url value='/resources/js/datatableresponsive.js' />"></script>
    <script src="<c:url value='/resources/vendor/datatables/dataTables.bootstrap4.js' />"></script>
    
    <script src="<c:url value='/resources/js/dataTables.checkboxes.min.js' />"></script>
    
    
    <script>
    function logout() {
		$.ajax({
			type : "GET",
			url : "${pageContext.servletContext.contextPath}/login/logout",
			 dataType: "json",
			complete: function(data) {
			
			if(data.responseText == 'success'){
			
				window.location = "${pageContext.servletContext.contextPath}/login/login";
			}
			else{
				
				
			}
			}
			       
			
		});
	}
    
    function idealstate() {
		$.ajax({
			type : "GET",
			url : "${pageContext.servletContext.contextPath}/login/idleStatus",
			 dataType: "json",
			complete: function(data) {
			
			if(data.responseText == 'success'){
				alert(ideal);
			}
			else{
				
				
			}
			}
			       
			
		});
	}
   
	 $('document').ready(function(){
		    message();
		    alertapplicant();
		    jobalert();
		    appalert();
		    })
		    var acount =0;
	 var acount1 =0;
	 var acount2 =0;
		    function message(){
		    	$.ajax({
					type : "POST",
					url : "${pageContext.servletContext.contextPath}/user/getMessage",
				        dataType: "json",
				        complete: function(data) {
				           console.log(data);
				           var count =0;
				           for(var i=data.responseJSON.length -1; i >= 0; i--){ 
				        	  
				        	 if(data.responseJSON[i].status == 0){
				        		 count++;
				        	 }
				        	 
				        	  $("#topmsg").prepend('<div class="dropdown-divider"></div><a class="dropdown-item" id="tmsg" href= "${pageContext.servletContext.contextPath}/user/showmessages/?id:'+data.responseJSON[i].userid+'" target="_blank" onclick="update('+data.responseJSON[i].message_id+')"><strong>'+data.responseJSON[i].Name+'</strong><span class="small float-right text-muted">'+data.responseJSON[i].created_at+'</span><br/><br/><div class="dropdown-message small"><p>'+data.responseJSON[i].message+'</p></div></a>');
				        	  
				           }
				           $('#msgcount').text(count);
				           $('#msgcount1').text(count);
				        }
		    	})
		    }
	 function update(data){
		 
		 $.ajax({
				type : "POST",
				url : "${pageContext.servletContext.contextPath}/user/msgstatus",
			        dataType: "json",
			        data:{message_id:data},
			        complete: function(data) {
			           console.log(data);
			          
			        }
				
	 })
	 }
	 function alertapplicant(){
		  $.ajax({
		   type : "POST",
		   url : "${pageContext.servletContext.contextPath}/user/getapplicantAlerts",
		          dataType: "json",
		          complete: function(data) {
		             console.log(data);
		             for(var i=0; i < data.responseJSON.length; i++){
		            	 if(data.responseJSON[i].status == 0){
			        		 acount++;
			        	 }
		              $("#alertmsg").prepend(
		              '<a class="dropdown-item" href="${pageContext.servletContext.contextPath}/user/alertapplicant?applicantid:'+data.responseJSON[i].ref_applicantid+'" target="_blank"  onclick="alertupdate('+data.responseJSON[i].alert_id+')"><span class="text-success"><strong>'+
		                    '<i class="fa fa-long-arrow-up fa-fw"></i>'+data.responseJSON[i].alert_type+'</strong>'+
		                '</span><span class="small float-right text-muted">'+data.responseJSON[i].created_at+'</span><br/><br/>'+
		                '<div class="dropdown-message small">'+data.responseJSON[i].Name+' created applicant</div>'+
		              '</a><div class="dropdown-divider"></div>');
		             }
		             var count = acount + acount1 + acount2
		             $('#alertcount').text(count);
			           $('#alertcount1').text(count);
		             }
		  }) 
		 }
		 function jobalert(){
		  $.ajax({
		   type : "POST",
		   url : "${pageContext.servletContext.contextPath}/user/getjobAlerts",
		          dataType: "json",
		          complete: function(data) {
		             console.log(data);
		             for(var i=0; i< data.responseJSON.length; i++){
		            	 if(data.responseJSON[i].status == 0){
			        		 acount1++;
			        	 }
		              $("#alertmsg").prepend('<div class="dropdown-divider"></div>' +
		              '<a class="dropdown-item" href="${pageContext.servletContext.contextPath}/user/alertjob?jobid:'+data.responseJSON[i].ref_jobid+'"target="_blank" onclick="alertupdate('+data.responseJSON[i].alert_id+')"><span class="text-success"><strong>'+
		                    '<i class="fa fa-long-arrow-up fa-fw"></i>'+data.responseJSON[i].alert_type+'</strong>'+
		                '</span><span class="small float-right text-muted">'+data.responseJSON[i].created_at+'</span><br/><br/>'+
		                '<div class="dropdown-message small">'+data.responseJSON[i].Name+' created job and assigned to you!</div>'+
		              '</a>');
		             }
		             var count = acount + acount1 + acount2
		             $('#alertcount').text(count);
			           $('#alertcount1').text(count);
		             }
		  }) 
		 }
		 function appalert(){
		  $.ajax({
		   type : "POST",
		   url : "${pageContext.servletContext.contextPath}/user/getappAlerts",
		          dataType: "json",
		          complete: function(data) {
		             console.log(data);
		             for(var i=0; i< data.responseJSON.length; i++){
		            	 if(data.responseJSON[i].status == 0){
			        		 acount2++;
			        	 }
		              $("#alertmsg").prepend('<div class="dropdown-divider"></div>' +
		              '<a class="dropdown-item" href="${pageContext.servletContext.contextPath}/user/applicationsettings?app_id:'+data.responseJSON[i].ref_app_id+'" target="_blank" onclick="alertupdate('+data.responseJSON[i].alert_id+')"><span class="text-success"><strong>'+
		                    '<i class="fa fa-long-arrow-up fa-fw"></i>'+data.responseJSON[i].alert_type+'</strong>'+
		                '</span><span class="small float-right text-muted">'+data.responseJSON[i].created_at+'</span><br/><br/>'+
		                '<div class="dropdown-message small">'+data.responseJSON[i].Name+' created application!</div>'+
		              '</a>');
		             }
		             var count = acount + acount1 + acount2
		             $('#alertcount').text(count);
			           $('#alertcount1').text(count);
		             }
		  }) 
		 }
 function alertupdate(data){
		 
		 $.ajax({
				type : "POST",
				url : "${pageContext.servletContext.contextPath}/user/alertStatus",
			        dataType: "json",
			        data:{alert_id:data},
			        complete: function(data) {
			           console.log(data);
			          
			        }
				
	 })
	 }
	
 setInterval(function(){
	 $("#alertmsg").empty();
	 $("#topmsg").empty();

	 	message();
	    alertapplicant();
	    jobalert();
	    appalert();	
		 // this will run after every 5 seconds
	}, 5000);
 !function(n){"use strict";n.fn.idle=function(e){var t,i,o={idle:6e4,events:"mousemove keydown mousedown touchstart",onIdle:function(){},onActive:function(){},onHide:function(){},onShow:function(){},keepTracking:!0,startAtIdle:!1,recurIdleCall:!1},c=e.startAtIdle||!1,d=!e.startAtIdle||!0,l=n.extend({},o,e),u=null;return n(this).on("idle:stop",{},function(){n(this).off(l.events),l.keepTracking=!1,t(u,l)}),t=function(n,e){return c&&(e.onActive.call(),c=!1),clearTimeout(n),e.keepTracking?i(e):void 0},i=function(n){var e,t=n.recurIdleCall?setInterval:setTimeout;return e=t(function(){c=!0,n.onIdle.call()},n.idle)},this.each(function(){u=i(l),n(this).on(l.events,function(){u=t(u,l)}),(l.onShow||l.onHide)&&n(document).on("visibilitychange webkitvisibilitychange mozvisibilitychange msvisibilitychange",function(){document.hidden||document.webkitHidden||document.mozHidden||document.msHidden?d&&(d=!1,l.onHide.call()):d||(d=!0,l.onShow.call())})})}}(jQuery);

//<-- THIS IS THE JS SOURCE CODE OF Jquery.Idle --------

$(document).idle({
onIdle: function(){
	alert(ideal);
	idealstate();	
	
},
idle: 120000
})
    </script>
    <script language="JavaScript" type="text/javascript">
    window.history.forward();              
 </script> 