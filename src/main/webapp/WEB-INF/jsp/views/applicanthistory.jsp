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
                        <div id="usertable"  class="table-responsive1" >
                            <table id="example" class="table table-striped table-bordered nowrap" cellspacing="0" width="100%">
                                <thead style="background:#ff6707;color:#fff">
                                    <tr>
                                      
                                       <th class="table_heading">Created At</th>
                                       	<th class="table_heading">Comment</th>
                                    	<th class="table_heading">Created By</th>
                                    	 
                                    </tr>
                                </thead> 
                            </table>
                            </div>
    				</div>
            </div>
   
        </div>
        
      </div>
  
     
    
   
	
  <%@include file="./shared/footer.jsp" %>
  <script>
  var x = window.location.href;
	console.log(x.substring(x.lastIndexOf(':') + 1));
      var applicantid =x.substring(x.lastIndexOf(':') + 1);
  
  
  </script>