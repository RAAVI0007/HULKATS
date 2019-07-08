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
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.service.ManagerService;
import com.web.hulklogic.service.TemplateService;
@Controller
public class ManagerController {
	private static Logger logger = Logger.getLogger(ManagerController.class);
	 
	 @Autowired
	 private ManagerService managerService;
	 @Autowired
	 private TemplateService tempalteService;
	 
	 
	    @RequestMapping(value="/manager/sourceAnalyticsPer",method=RequestMethod.POST)
		public  @ResponseBody String sourceAnalytics(@RequestParam(value="days", required=false) int days,
				@RequestParam(value="recruiteremail", required=false) String recruiteremail,HttpSession session) {
		 logger.info("days:"+days);
		 logger.info("recruiteremail:"+recruiteremail);
		 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname); 
		 List<Map<String, Object>> countSourceAnalytics=null;
		 try {
			 if(recruiteremail.equalsIgnoreCase("All")) {
				 countSourceAnalytics= managerService.sourceAnalyticsAllRecruiters(days, teamname);
			 }else {
				 countSourceAnalytics= managerService.sourceAnalytics(days, recruiteremail);
			 }
			
		 }catch(Exception e){
			 logger.error("Exception is: "+e);
		 }
		 return new Gson().toJson(countSourceAnalytics); 
	}
	        
	  
	    
	    @RequestMapping(value="/manager/sourceAnalyticstill",method=RequestMethod.POST)
	   	public  @ResponseBody String sourceAnalyticsTillNowAllRecruiters(HttpSession session,@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname); 
	   	 List<Map<String, Object>> countSourceAnalytics=null;
	   	 try {
	   		 if(recruiteremail.equalsIgnoreCase("All")) { 
	   		countSourceAnalytics= managerService.sourceAnalyticsTillAllRecruiter(teamname);
	   		 }else {
	   			countSourceAnalytics= managerService.sourceAnalyticsTillPerRecruiter(recruiteremail);
	   		 }
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countSourceAnalytics); 
	   }
	    

	   
	    @RequestMapping(value="/manager/statusAnalyticsPer",method=RequestMethod.POST)
		public  @ResponseBody String statusAnalytics(@RequestParam(value="days", required=false) int days,
				@RequestParam(value="recruiteremail", required=false) String recruiteremail,HttpSession session) {
		 logger.info("days:"+days);
		 logger.info("recruiteremail:"+recruiteremail);
		 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname); 
		 List<Map<String, Object>> countStatusAnalytics=null;
		 try {
			 if(recruiteremail.equalsIgnoreCase("All")) {
				 countStatusAnalytics= managerService.statusAnalyticsAllRecruiters(days, teamname);
				 
			 }else {
				 countStatusAnalytics= managerService.statusAnalytics(days, recruiteremail);
			 }
			
		 }catch(Exception e){
			 logger.error("Exception is: "+e);
		 }
		 System.out.println(countStatusAnalytics);
		 return new Gson().toJson(countStatusAnalytics); 
	}

	    
	    @RequestMapping(value="/manager/statusAnalyticstill",method=RequestMethod.POST)
	   	public  @ResponseBody String statusAnalyticsTillNowAllRecruiters(HttpSession session,@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname); 
	 	 logger.info("recruiteremail:"+recruiteremail); 
	   	 List<Map<String, Object>> countStatusAnalytics=null;
	   	 try {
	   		 if(recruiteremail.equalsIgnoreCase("All")) {
	   			countStatusAnalytics= managerService.statusAnalyticsTillAllRecruiters(teamname); 
	   		 }else {
	   			countStatusAnalytics= managerService.statusAnalyticsTillPerRecruiter(recruiteremail);
	   		 }
	   	
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countStatusAnalytics); 
	   }
	   
	   
	    @RequestMapping(value="/manager/applicationprocessNew",method=RequestMethod.POST)
		public  @ResponseBody String applicationprocessNew(HttpSession session,@RequestParam(value="days", required=false) int days,
				@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
		 logger.info("days:"+days);
		 logger.info("recruiteremail:"+recruiteremail);
		 String teamname= (String)session.getAttribute("teamname"); 
		 logger.info("teamname:"+teamname); 
		 List<Map<String, Object>> countapplicationprocessNew=null;
		 try {
			 if(recruiteremail.equalsIgnoreCase("All")) {
			     countapplicationprocessNew= managerService.applicationInProcessNewAllRecruiters(days, teamname);
				
			 }else {
				countapplicationprocessNew= managerService.applicationInProcessNew(days, recruiteremail);
			 }
			
			 }catch(Exception e){
			 logger.error("Exception is: "+e);
		 }
		 return new Gson().toJson(countapplicationprocessNew); 
	}
	    
	    
	    
	    @RequestMapping(value="/manager/applicationprocessNewtill",method=RequestMethod.POST)
	   	public  @ResponseBody String applicationprocessNewTillNowAllRecruiters(HttpSession session,@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname);
		 logger.info("recruiteremail:"+recruiteremail); 
	   	 List<Map<String, Object>> countapplicationprocessNew=null;
	   	 try {
	   		 if(recruiteremail.equalsIgnoreCase("All")) {
	   			countapplicationprocessNew= managerService.applicationInProcessNewTillAllRecruiters(teamname);
	   		 }else {
	   			countapplicationprocessNew= managerService.applicationInProcessNewTillPerRecruiter(recruiteremail);
	   		 }
	   		
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countapplicationprocessNew); 
	   }
	   
	    
	    
	    @RequestMapping(value="/manager/applicantHiredAndTarget",method=RequestMethod.POST)
	  		public  @ResponseBody String applicantHiredAndTarget(HttpSession session,@RequestParam(value="days", required=false) int days,
	  				@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	  		 logger.info("days:"+days);
	  		 logger.info("recruiteremail:"+recruiteremail);
	  		 String teamname= (String)session.getAttribute("teamname"); 
	  		 logger.info("teamname:"+teamname); 
	  		 List<Map<String, Object>> countApplicantHiredAndTarget=null;
	  		 try {
	  			 if(recruiteremail.equalsIgnoreCase("All")) {
	  				
	  				countApplicantHiredAndTarget= managerService.countApplicantHiredAndTargetAll(days, teamname);
	  				
	  			 }else {
	  				countApplicantHiredAndTarget= managerService.countApplicantHiredAndTarget(days, recruiteremail,teamname);
	  			 }
	  			
	  			 }catch(Exception e){
	  			 logger.error("Exception is: "+e);
	  		 }
	  		 return new Gson().toJson(countApplicantHiredAndTarget); 
	  	}
	    
	    @RequestMapping(value="/manager/applicantHiredAndTargettill",method=RequestMethod.POST)
	   	public  @ResponseBody String applicantHiredAndTargettill(HttpSession session,@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname);
		 logger.info("recruiteremail:"+recruiteremail); 
	   	 List<Map<String, Object>> countApplicantHiredAndTarget=null;
	   	 try {
	   		 if(recruiteremail.equalsIgnoreCase("All")) {
	   			 
	   			countApplicantHiredAndTarget= managerService.countApplicantHiredAndTargetTillAll(teamname);
	   			
	   		 }else {
	   			countApplicantHiredAndTarget= managerService.countApplicantHiredAndTargetTillPer(recruiteremail, teamname);
	   			
	   			logger.info("countApplicantHiredAndTarget:"+countApplicantHiredAndTarget);
	   		 }
	   		
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countApplicantHiredAndTarget); 
	   }
	    
	    @RequestMapping(value="/manager/topRecruiter",method=RequestMethod.POST)
	   	public  @ResponseBody String topRecruiters(HttpSession session,@RequestParam(value="days", required=false) int day){
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname);
		 logger.info("day:"+day); 
	     Target target=managerService.getDetailsByTeam(teamname);
	      List<Map<String, Object>> countTopRecruitres=null; 
	   	 try {
	   		 if(day == 3) {
	   			countTopRecruitres= managerService.topRecruitersTillNow(target.getStartdate(),teamname);
	   		 }else {
	   			countTopRecruitres=managerService.topRecruiters(teamname, day);
	   		 }
	   		
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countTopRecruitres); 
	   }
	    
	    
	    @RequestMapping(value="/manager/lessRecruiter",method=RequestMethod.POST)
	   	public  @ResponseBody String lessRecruiters(HttpSession session,@RequestParam(value="days", required=false) int day){
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname);
		 logger.info("day:"+day); 
	      Target target= managerService.getDetailsByTeam(teamname);
	      List<Map<String, Object>> countTopRecruitres=null; 
	   	 try {
	   		 if(day == 3) {
	   			countTopRecruitres= managerService.lessRecruitersTillNow(target.getStartdate(),teamname);
	   		 }else {
	   			countTopRecruitres=managerService.lessRecruiters(teamname, day);
	   		 }
	   		
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countTopRecruitres); 
	   }
	    
	    @RequestMapping(value = "/user/termsconditions", method = RequestMethod.POST)
		public @ResponseBody String saveTermsAndConditions(HttpSession session) {
			Integer userid = (Integer)session.getAttribute("userid");
			logger.info("userid:"+userid);
			int result;
		    result =tempalteService.saveTermsAndCondition(userid);
		    if(result>0){
		    	return "success";
		    }
			
			return "fail";	
		} 
	    
	    
	    @RequestMapping(value = "/user/getTermsConditions", method = RequestMethod.POST)
		public @ResponseBody String getTermsConditionsValue(HttpSession session) {
	    	Integer userid = (Integer)session.getAttribute("userid");
			logger.info("userid:"+userid);
			Integer status=tempalteService.getTerms(userid);
		   return new Gson().toJson(status); 	
		}
	    

	    
	    @RequestMapping(value="/manager/jobOpenAndClosedPer",method=RequestMethod.POST)
	  		public  @ResponseBody String jobOpenAndClosedPer(HttpSession session,@RequestParam(value="days", required=false) int days,
	  				@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	  		 logger.info("days:"+days);
	  		 logger.info("recruiteremail:"+recruiteremail);
	  		 String teamname= (String)session.getAttribute("teamname"); 
	  		 logger.info("teamname:"+teamname); 
	  		 List<Map<String, Object>> countJobOpenAndClosed=null;
	  		 try {
	  			 if(recruiteremail.equalsIgnoreCase("All")) {
	  				countJobOpenAndClosed= managerService.countJobOpenClosedAllRecruiters(days, teamname);
	  				
	  			 }else {
	  				countJobOpenAndClosed= managerService.countJobOpenClosed(days, recruiteremail);
	  			 }
	  			
	  			 }catch(Exception e){
	  			 logger.error("Exception is: "+e);
	  		 }
	  		 return new Gson().toJson(countJobOpenAndClosed); 
	  	}
	  	    
	    @RequestMapping(value="/manager/jobOpenClosedTillNow",method=RequestMethod.POST)
	   	public  @ResponseBody String jobOpenClosedTillNowAllRecruiters(HttpSession session,@RequestParam(value="recruiteremail", required=false) String recruiteremail) {
	   	 String teamname= (String)session.getAttribute("teamname"); 
	 	 logger.info("teamname:"+teamname);
		 logger.info("recruiteremail:"+recruiteremail); 
	   	 List<Map<String, Object>> countJobOpenClosed=null;
	   	 try {
	   		 if(recruiteremail.equalsIgnoreCase("All")) {
	   			countJobOpenClosed= managerService.countJobOpenClosedTillAllRecruiters(teamname);
	   		 }else {
	   			countJobOpenClosed= managerService.countJobOpenClosedTillPerRecruiters(recruiteremail);
	   		 }
	   		
	   	 }catch(Exception e){
	   		 logger.error("Exception is: "+e);
	   	 }
	   	 return new Gson().toJson(countJobOpenClosed); 
	   }
	   
}
