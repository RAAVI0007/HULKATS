package com.web.hulklogic.controllers;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.DashBordService;

@Controller
public class DashBordController {
	
	private static Logger logger = Logger.getLogger(DashBordController.class);
	
	@Autowired
	private DashBordService dashBordService;
	
	@RequestMapping(value="/manager/countApplicantHired",method=RequestMethod.POST)
	public  @ResponseBody String  getCountApplicantHired() {
	 List<Map<String, Object>> countApplicantiredH=null;
	 try {
		 countApplicantiredH= dashBordService.countApplicantHired();
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(countApplicantiredH); 
}
	
	@RequestMapping(value="/manager/jobOpenAndTotal",method=RequestMethod.POST)
	public  @ResponseBody String  getCountJobOpenAndTotal(HttpSession session) {
	 List<Map<String, Object>> jobsOpenAndTotal=null;
	 String teamname= (String)session.getAttribute("teamname");
	 System.out.println("jobcon"+teamname);
	 try {
		 jobsOpenAndTotal= dashBordService.countJobsOpenAndTotal(teamname);
		 
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(jobsOpenAndTotal); 
}
	
	 @RequestMapping(value="/manager/getRecruiter",method=RequestMethod.POST)
	    public @ResponseBody  List<User> getRecruiterNames(HttpSession session){
	   
	    List<User> recruitername=null;
	    String teamname= (String)session.getAttribute("teamname");
	    try{
	     recruitername= dashBordService.getRecruitereNamesByAutoComplate(teamname);
	    
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return recruitername;
	}
	 
	 @RequestMapping(value="/user/getRecruiter",method=RequestMethod.POST)
	    public @ResponseBody  List<User> getemail(HttpSession session){
	   
	    List<User> recruitername=null;
	    String teamname= (String)session.getAttribute("teamname");
	    Integer userid = (Integer)session.getAttribute("userid");
	    
	    try{
	     recruitername= dashBordService.email(userid, teamname);
	    
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return recruitername;
	}
	   
	@RequestMapping(value="/manager/applicationInprocess",method=RequestMethod.POST)
	public  @ResponseBody String applicationInprocessAndNew() {
	 List<Map<String, Object>> applicationInprocessAndNew=null;
	 try {
		 applicationInprocessAndNew= dashBordService.countApplicationInprocessAndNew();
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(applicationInprocessAndNew); 
}

	
	@RequestMapping(value="/manager/recruiterHiredAndTarget",method=RequestMethod.POST)
	public  @ResponseBody String recruiterHiredAndTarget(@RequestParam(value="hired", required=false) int countHired) {
		logger.info("hired:"+countHired);
	 List<Map<String, Object>> countHiredAndTarget=null;
	 int value=30;
	 int value1=7;
	 try {
		 if(value==countHired){
			 countHiredAndTarget= dashBordService.recruiterCountHiredThirty();
		 }else if(value1==countHired){
			 countHiredAndTarget= dashBordService.recruiterCountHiredSeven();
		 }else{
			 countHiredAndTarget=dashBordService.recruiterCountHiredTillnow();
		 }
		
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(countHiredAndTarget); 
}
	
	
	@RequestMapping(value="/manager/sourceAnalytics",method=RequestMethod.POST)
	public  @ResponseBody String sourceAnalytics(@RequestParam(value="sourceCount", required=false) int sourceCount) {
	 logger.info("sourceCount:"+sourceCount);
	 List<Map<String, Object>> countSourceAnalytics=null;
	 int value=30;
	 int value1=7;
	 try {
		 if(value==sourceCount){
			 countSourceAnalytics= dashBordService.sourceAnalyticsThirty();
		 }else if(value1==sourceCount){
			 countSourceAnalytics=dashBordService.sourceAnalyticsSeven();
		 }else{
			 countSourceAnalytics=dashBordService.sourceAnalyticsTillNow();
		 }
		
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(countSourceAnalytics); 
}
	
	
	@RequestMapping(value="/manager/hiredAndBacklogs",method=RequestMethod.POST)
	public  @ResponseBody String recruiterHiredAndBacklogs(@RequestParam(value="backlogsCount", required=false) int backlogsCount) {
	 logger.info("backlogsCount:"+backlogsCount);
	 List<Map<String, Object>> countHiredBacklogs=null;
	 int value=30;
	 int value1=7;
	 try {
		 if(value==backlogsCount){
			 countHiredBacklogs= dashBordService.recruiterHiredBacklogsThirty();
		 }else if(value1==backlogsCount){
			 countHiredBacklogs=dashBordService.recruiterHiredBacklogsSeven();
		 }else{
			 countHiredBacklogs=dashBordService.recruiterHiredBacklogsTillNow();
		 }
		
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(countHiredBacklogs); 
}
	
	
	@RequestMapping(value="/manager/statusAnalytics",method=RequestMethod.POST)
	public  @ResponseBody String statusAnalytics(@RequestParam(value="statusCount", required=false) int statusCount) {
	 logger.info("statusCount:"+statusCount);
	 List<Map<String, Object>> statusAnalytics=null;
	 int value=30;
	 int value1=7;
	 try {
		 if(value==statusCount){
			 statusAnalytics= dashBordService.statusAnalyticsThirty();
		 }else if(value1 == statusCount){
			 statusAnalytics=dashBordService.statusAnalyticsSeven();
			 System.out.println(statusAnalytics.size());
		 }else{
			    statusAnalytics=dashBordService.statusAnalyticsTillNow();
		   }
		
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(statusAnalytics); 
}
	
    @RequestMapping(value = "/manager/assignteam", method = RequestMethod.POST)	 
     public @ResponseBody String assignRecruiterToTeam(HttpSession session,@RequestParam("userid") int userid) {
    	 String teamname = (String)session.getAttribute("teamname"); 
    	 logger.info("id:"+userid);
    	 logger.info("teamname:"+ teamname);
    	 if(teamname!=null){
  		   boolean status = dashBordService.AssignManager(userid, teamname);
  		   if(status){
  		    return "success";
  		   }
  		  }
  		  return "fail";
  		 }
     
     @RequestMapping(value="/manager/getActiveUser",method=RequestMethod.POST)
     public  @ResponseBody String countActiveUsers(HttpSession session) {
      String teamname= (String)session.getAttribute("teamname"); 
      Integer userid= (Integer)session.getAttribute("userid");
      logger.info("userid:"+userid); 
      List<Map<String, Object>> countActiveUsers=null;
      try {
       countActiveUsers= dashBordService.getLiveUser(userid,teamname);
      }catch(Exception e){
       logger.error("Exception is: "+e);
      }
      return new Gson().toJson(countActiveUsers); 
    }
}

