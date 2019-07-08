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
import com.web.hulklogic.service.ApplicationSettingsService;
import com.web.hulklogic.service.EditDetailsService;
import com.web.hulklogic.service.ManagerDashBordService;
import com.web.hulklogic.service.UserService;

@Controller
public class ManagerDashBordController {

	private static Logger logger = Logger.getLogger(ManagerDashBordController.class);
	@Autowired
	private ManagerDashBordService managerDashBordService;
	@Autowired
	 private EditDetailsService editDetailsService;
	@Autowired
	 private UserService userService;
	@Autowired
	 private ApplicationSettingsService applicationSettingsService;
	
	
	@RequestMapping(value="/manager/dashBoard",method=RequestMethod.POST)
	public  @ResponseBody String  getDashBord() {
	 List<Map<String, Object>> dashBordResult=null;
	 try {
	   dashBordResult= managerDashBordService.getCountValues();
	 }catch(Exception e){
		 logger.error("Exception is: "+e);
	 }
	 return new Gson().toJson(dashBordResult); 
}
	
	   @RequestMapping(value="/manager/applicantcountdays",method=RequestMethod.POST)
	    public  @ResponseBody List<Map<String, Object>> getcountApplicantDays() {
		List<Map<String, Object>> countApplicantDayas=null;
		try{
	     countApplicantDayas= managerDashBordService.getCountApplicantDays();
		}catch(Exception e){
			logger.error("Exception is: "+e);
		}
	    return countApplicantDayas; 
}
	
	  @RequestMapping(value="/manager/countstatus",method=RequestMethod.POST)
      public  @ResponseBody List<Map<String, Object>> getcountStatus() {
	  List<Map<String, Object>> countstatus=null;
	  try{
       countstatus= managerDashBordService.getCountStatus();
	  }catch(Exception e){
		  logger.error("Exception is: "+e);
	  }
      return countstatus; 
}
	  @RequestMapping(value="/manager/countsource",method=RequestMethod.POST)
	     public  @ResponseBody List<Map<String, Object>> getcountSource() {
	  List<Map<String, Object>> countSource=null;
	  try{
	   countSource= managerDashBordService.getCountSource();
	  }catch(Exception e){
	   logger.error("Exception is: "+e);
	  }
	     return countSource; 

	    }
	  
	   @RequestMapping(value="/manager/countsourcehired",method=RequestMethod.POST)
	     public  @ResponseBody List<Map<String, Object>> getcountHired() {
	  List<Map<String, Object>> countHired=null;
	  try{
	   countHired= managerDashBordService.getCountHired();
	  }catch(Exception e){
	   logger.error("Exception is: "+e);
	  }
	     return countHired; 

	}
	   
	   @RequestMapping(value="/manager/counttoprecruiters",method=RequestMethod.POST)
	     public  @ResponseBody List<Map<String, Object>> getTopRecruiters() {
	  List<Map<String, Object>> countHired=null;
	  try{
	   countHired= managerDashBordService.getTopRecruiterApp();
	  }catch(Exception e){
	   logger.error("Exception is: "+e);
	  }
	     return countHired; 
	 }
	   
	   @RequestMapping(value="/manager/getRecruiterNames",method=RequestMethod.POST)
	    public @ResponseBody  List<User> getRecruiterNamesByAutoComplete(@RequestParam(value="recruiternames", required=false) String recruiternames){
	    logger.info("recruiternames:"+recruiternames);
	    String upperCaseName=recruiternames.toUpperCase();
	    logger.info("recruiternamesUpper:"+upperCaseName);
	    List<User> recruitername=null;
	    try{
	     recruitername=managerDashBordService.getRecruitereNamesByAutoComplate(upperCaseName);
	    
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return recruitername;
	}
	   
	   
	   @RequestMapping(value="/manager/recruiterperformance",method=RequestMethod.POST)
	   public  @ResponseBody List<Map<String, Object>> getRecruiterPerformance(@RequestParam(value="email", required=false) String email,
	    @RequestParam(value="year", required=false) String year,
	    @RequestParam(value="month", required=false) String month) {
	    
	    logger.info("email:"+email); 
        logger.info("year:"+year);
        logger.info("month:"+month);
        
	   List<Map<String, Object>> recruiterstatus=null; 
	   try{
		   
	    recruiterstatus= managerDashBordService.getRecruiterPerformance(email,month,year);
	         
	     }catch(Exception e){
	    logger.error("Exception is: "+e);
	   }
	      return recruiterstatus; 
	  } 
	 
	   
	   
	   @RequestMapping(value="/user/updateUserInfo",method=RequestMethod.POST)
	   public @ResponseBody String  updateUserDetails(HttpSession session,@RequestParam(value="user", required=false) String userStr) {  
	    if (null == userStr || userStr.trim().isEmpty()) {
	    return "failure";
	     }
	    
	    User user = new Gson().fromJson(userStr, User.class); 
	    logger.info("user data");
	    boolean statusconcat = applicationSettingsService.checkContactNoInProfile(user.getContactno(), user.getUserid());
	    logger.info(statusconcat+"statusconcat");
		if(statusconcat){
		   return "Mobile Number is registered! Please choose another one";
		  }
	     
	    boolean status= editDetailsService.updateUserDetails(user);
	    
	    if(status){
	     return "success";
	    }
	   return "fail"; 
	   }
	
	   
	   @RequestMapping(value="/manager/getManagerNames",method=RequestMethod.POST)
	    public @ResponseBody List<User> geManagerNamesByAutoComplete(@RequestParam(value="managername", required=false) String managername){
	    String upperCaseName=managername.toUpperCase();
	    logger.info("upperCaseName:"+upperCaseName);
	    List<User> managernames=null;
	    try{
	      managernames=managerDashBordService.getManagerNamesByAutoComplete(upperCaseName);
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return managernames;
	}
	
	  
} 