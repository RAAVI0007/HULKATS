<!-- Navigation-->
  <%@include file="./shared/nav.jsp" %>
  <div class="content-wrapper">
    <div class="container-fluid">
  <ol class="breadcrumb">
        <li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
      <!-- Icon Cards-->
     
      <br>
       <div class="row">
      <div id="table" class="col-lg-3">
      <select id="days" name="days" class="form-control" required>
                    
                                    <option value="">Select Days</option>
                                    <option value="7" >Last 7 Days</option>
								     <option value="30">Last 30 Days</option>
                                    <option value="3" selected>Till Now</option>
								    
                                   
                                   </select>
      
      </div>
      
      </div>
      <br>
      <div class="row">
     
        <div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-primary o-hidden h-100">
				 	<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa fa-file-text"></i>
						</div>
						<div class="col-xs-3">
						<font size="6"><b><span
									id="open"></span></b></font>
							<div><strong>Open/Total!</strong></div> 
						</div>
					</div>
					
					<a class="card-footer text-white clearfix small z-1"
						href="${pageContext.servletContext.contextPath}/recruiter/jobview">
						<span class="float-left"><h5>Jobs</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>

				</div>
			</div>  
			
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-warning o-hidden h-100">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa fa-group"></i>
						</div>
						<div class="mr-5">
							 <font size="6"><b><span
									id="applicant"></span></b></font>
									<div><strong>Hired/Target!</strong></div>
						</div>
					</div>
					<a class="card-footer text-white clearfix small z-1"
						href="${pageContext.servletContext.contextPath}/user/applicantslist">
						<span class="float-left"><h5>Applicants</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>
				</div>
			</div>
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-success o-hidden h-100">
					 <div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa fa-files-o"></i>
						</div>
						<div class="mr-5 ">
							<font size="6"><b><span
									id="application"></span></b></font>
									<div><strong>In-Process/New!</strong></div>
						</div>
					</div>
					<a class="card-footer text-white clearfix small z-1"
						href="${pageContext.servletContext.contextPath}/recruiter/application">
						<span class="float-left"><h5>Applications</h5></span> <span
						class="float-right"> <i class="fa fa-arrow-circle-right"></i>
					</span>
					</a>
				</div>
			</div>

		</div>
		<br> <br>
  	
   <div class="row" >
   <div  class="col-lg-6">
    <h5>Candidate Status Analytics</h5>
             
            <div id="donutchart" style="width: 500px; height: 300px;"></div>
            <div id="cnt" class="overlay"></div>
			
           
            </div>
            <div  class="col-lg-6">
             <h5>Source Analytics</h5>
             
            <div id="donutchartsrc" style="width: 500px; height: 300px;"></div>
             <div id="cntsrc" class="overlay"></div>
            </div>
            </div>
            
            
  <div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Terms & Conditions</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
      <div class="modal-body">
      	<div class="form-group" style="text-align: center">
			<h5>
				<span id='success' style="color: green;" colspan="2"></span> <span
					id='error'
					style="color: red; margin-left: auto; margin-right: auto;"
					></span> <b><span id="jobid" style="color: green;"
					></span></b>

			</h5>
		</div>
        <input type="checkbox" name="checkbox" value="check" id="agree" /> I have read and agree to the Terms and Conditions and Privacy Policy.
      </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-success" onclick="terms();">Submit</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div id="alertlogin" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Login Alert</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="X">
              <span aria-hidden="true">X</span>
            </button>
          </div>
      <div class="modal-body">
      	<h5>You have successfully logged in through this device and have been logged out from another recently used device.</h5>
         </div>
      <div class="modal-footer">
      <button type="button" class="btn btn-success" onclick="alertlogin();">Ok</button>
      </div>
    </div>

  </div>
</div>
     <!--   footer -->
   <%@include file="./shared/footer.jsp" %>
  </div>
  </div>
	<script>
	var a1;
	var recname;
	var month7;
	var month30;
	var year7;
	var year30;
	var quater30;
	var quater7;
	  
   $(document).ready(function() {
	   login();
	   var days = $('#days').val();
	   	statusanalyticsper(days)
   		sourceper(days)
   		hiredper(days);
   		app(days);
    	sevenfunc(days);
      $.ajax({
       	type:"POST",
      	dataType :"json",
       	url:"${pageContext.servletContext.contextPath}/user/getTermsConditions",
     		complete: function (data) {
     		console.log(data);
      	   if(data.responseJSON == 0){
      		   $('#myModal').modal('show'); 
      	   }  
     	}
        })
      
   })
   function alertlogin(){
    	 $.ajax({
         	type:"GET",
        	dataType :"json",
         	url:"${pageContext.servletContext.contextPath}/login/loginthirdDevice",
       		complete: function (data) {
		 $('#alertlogin').modal('toggle');
       	 }
       	})
    }
  function login(){
 		
 		 $.ajax({
 				type : "POST",
 				url : "${pageContext.servletContext.contextPath}/login/checkLogin",
 			        dataType: "json",
 			      
 			        complete: function(data) {
 			           console.log(data);
 			          
 			        }
 				
 	 })
 	 }
   var statusanalyticsper = function(days){
		  
		  $.ajax({
		        type: "POST",
		        url: "${pageContext.servletContext.contextPath}/recruiter/statusAnalytics",
		       data:{days:days},
		        dataType :"json",
		        complete:function(data){ 
				console.log(data);
				if(data.responseJSON!=null){
				var status = [['Status','Count']];
				var count =0;
				for(var i=0; i<data.responseJSON.length; i++){
					status.push([ data.responseJSON[i].status,data.responseJSON[i].stat]);
					count += data.responseJSON[i].stat
				}
	    		 google.charts.load('current', {'packages':['corechart']});
	    	      google.charts.setOnLoadCallback(drawChart);
	    	      function drawChart() {
	   	      var data = google.visualization.arrayToDataTable(status);
	    	      
	   	   var options = {
	   			 chartArea : { left: "5%" },
	   			  legend: { position: 'top', maxLines: 3 },
	   	          pieHole: 0.5,
	   	       	pieSliceText: "value",
	   	       	tooltip: {
	               text: 'value'
	           }
	   	        };

	   	        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
	   	     if(count == 0){
	   	         $('#cnt').text(" ");
	   	        }
	   	        else{
	   	     $('#cnt').text(count);
	   	        }
	   	        chart.draw(data, options);
	   	      
	   	    }
				}
				else{
					var status = [['Status','Count']];
					var count =0;
					 google.charts.load('current', {'packages':['corechart']});
		    	      google.charts.setOnLoadCallback(drawChart);
		    	      function drawChart() {
		   	      var data = google.visualization.arrayToDataTable(status);
		    	      
		   	   var options = {
		   			 chartArea : { left: "5%" },
		   			  legend: { position: 'top', maxLines: 3 },
		   	          pieHole: 0.5,
		   	       	pieSliceText: "value",
		   	       	tooltip: {
		               text: 'value'
		           }
		   	        };

		   	        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
		   	     if(count == 0){
		   	         $('#cnt').text(" ");
		   	        }
		   	        else{
		   	     $('#cnt').text(count);
		   	        }
		   	        chart.draw(data, options);
		    	      }
				}
		        }
		       })
   }
   
  
   var sourceper = function(days){
		  
		  $.ajax({
		        type: "POST",
		        url: "${pageContext.servletContext.contextPath}/recruiter/sourceAnalytics",
		       data:{days:days},
		        dataType :"json",
		        complete:function(data){ 
				console.log(data);
				if(data.responseJSON!=null){
					var status = [['Status','Count']];
					var count =0;
					for(var i=0; i<data.responseJSON.length; i++){
						status.push([ data.responseJSON[i].source,data.responseJSON[i].stat]);
						count += data.responseJSON[i].stat
					}
		    		 google.charts.load('current', {'packages':['corechart']});
		    	      google.charts.setOnLoadCallback(drawChart);
		    	      function drawChart() {
		   	      var data = google.visualization.arrayToDataTable(status);
		    	      
		   	   var options = {
		   			 chartArea : { left: "5%" },
		   			 legend: { position: 'top', maxLines: 3 },
		   	          pieHole: 0.5,
		   	       	pieSliceText: "value",
		   	       	tooltip: {
		               text: 'value'
		           }
		   	        };

		   	        var chart = new google.visualization.PieChart(document.getElementById('donutchartsrc'));
		   	        if(count == 0){
		   	     	$('#cntsrc').text(" ");
		   	        	
		   	        }else{
		   	        	$('#cntsrc').text(count);
		   	        }
		   	        chart.draw(data, options);
		   	      
		   	    }
					}
					else{
						var status = [['Status','Count']];
						var count =0;
						 google.charts.load('current', {'packages':['corechart']});
			    	      google.charts.setOnLoadCallback(drawChart);
			    	      function drawChart() {
			   	      var data = google.visualization.arrayToDataTable(status);
			    	      
			   	   var options = {
			   			 chartArea : { left: "5%" },
			   			 legend: { position: 'top', maxLines: 3 },
			   	          pieHole: 0.5,
			   	       	pieSliceText: "value",
			   	       	tooltip: {
			               text: 'value'
			           }
			   	        };

			   	        var chart = new google.visualization.PieChart(document.getElementById('donutchartsrc'));
			   	     if(count == 0){
			   	    	$('#cntsrc').text(" ");
			   	        }else{
			   	        $('#cntsrc').text(count);
			   	     	
			   	        }
			   	        chart.draw(data, options);
			    	      }
					}
		        }
		  })
   }

		  var app = function(days){
			  
			  $.ajax({
			        type: "POST",
			        url: "${pageContext.servletContext.contextPath}/recruiter/applicationprocessNew",
			        data:{days:days},
			        dataType :"json",
			        complete:function(data){ 
					console.log(data);
					$('#application').html(" "+data.responseJSON[0].progress+"/" + data.responseJSON[0].total);
		  }
			  })
		  };
		  

   var sevenfunc = function(days){
	   $.ajax({
	        type: "POST",
	        url: "${pageContext.servletContext.contextPath}/recruiter/jobsOpenAndClosed",
	        data:{days:days},
	        dataType :"json",
	        complete:function(data){ 
			console.log(data);
			$('#open').html(" "+data.responseJSON[0].jobs_open+"/" + data.responseJSON[0].total);
   }
	   })
   };
 
 var hiredper = function(days){
	   $.ajax({
			
	        type: "POST",
	        url: "${pageContext.servletContext.contextPath}/recruiter/applicantHiredAndTarget",
	        data:{days:days},
	        dataType :"json",
	        complete:function(data){ 
			console.log(data);
			var days = $('#days').val();
			if(data.responseJSON["0"].periodic == 'MONTH'){
				
				if(days == 7){
					var result = (data.responseJSON[0].targetvalue/30)*7;
					var month7 = result.toFixed();
					$('#applicant').html(data.responseJSON[0].hired+"/" + month7);
					
				}
				else if(days == 30){
					var result = (data.responseJSON[0].targetvalue/30)*30;
					var month30 = result.toFixed();
					$('#applicant').html(data.responseJSON[0].hired+"/" + month30);
				}
				else{
					$('#applicant').html(data.responseJSON[0].hired+"/" +data.responseJSON[0].targetvalue);
				}
			}
				else if(data.responseJSON["0"].periodic == 'YEAR'){
					
					if(days == 7){
						var result = (data.responseJSON[0].targetvalue/365)*7;
						var year7 = result.toFixed();
						$('#applicant').html(data.responseJSON[0].hired+"/" + year7);
					}
					else if(days == 30){
						var result = (data.responseJSON[0].targetvalue/365)*30;
						var year30 = result.toFixed();
						$('#applicant').html(data.responseJSON[0].hired+"/" + year30);
					}
					else{
						
						$('#applicant').html(data.responseJSON[0].hired+"/" +data.responseJSON[0].targetvalue);
					}
					
				}
					else{
						if(days == 7){
							var result = (data.responseJSON[0].targetvalue/120)*7;
							var quater7 = result.toFixed();
							$('#applicant').html(data.responseJSON[0].hired+"/" + quater7);
						}
						else if(days == 30){
							var result = (data.responseJSON[0].targetvalue/120)*30;
							var quater30 = result.toFixed();
							$('#applicant').html(data.responseJSON[0].hired+"/" + quater30);
						}
						else{
							
							$('#applicant').html(data.responseJSON[0].hired+"/" +data.responseJSON[0].targetvalue);
						}
					}
	        }
			
	   })
};

function login(){	 
	 $.ajax({
			type : "POST",
			url : "${pageContext.servletContext.contextPath}/login/checkLogin",
		        dataType: "json",			      
		        complete: function(data) {
		           console.log(data);
		          if(data.responseText == 'Out of limit'){
				$('#alertlogin').modal('show');
				$('#alertlogin').modal({backdrop: 'static', keyboard: false}) 
				}
		        }
			
})
}
    $('#days').on('change', function() {
    	
    	var days = this.value;	
    	statusanalyticsper(days)
    	sourceper(days)
    	hiredper(days);
    	app(days);
       	sevenfunc(days);
    	
    })
    function terms(){
    	 $('#success').text('');
    	 $('#error').text('');
        	  if($("#agree").prop('checked') == true){
        		   
        		
           $.ajax({
         	type:"POST",
        	dataType :"json",
         	url:"${pageContext.servletContext.contextPath}/user/termsconditions",
       	complete: function (data) {
       		console.log(data);
       		$('#myModal').modal('hide');
       	}
          })
        	  }
        	  else{
        		  $('#error').text('Please accept our terms and condition');
        		  $('#success').text('');
        	  }
       }
    setTimeout(function(){
   window.location.reload(1);
}, 120000);    
    
</script>

   
   <style>


.overlay {
display:block;
width:395px;
height:240px;
text-align:center;
vertical-align: middle;
position: absolute;
top: 0px;  /* chartArea top  */
left: 0px; /* chartArea left */
font-size: 30px;
margin-top: 160px;
}
.select2-dropdown.select2-dropdown--below{
      width: 380px !important;
}

.select2-container--default .select2-selection--single{
    padding:6px;
    height: 37px;
    width: 380px; 
    font-size: 1.2em;  
    position: relative;
}


   </style>
   
</body>

</html>