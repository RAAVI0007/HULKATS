package com.web.hulklogic.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.JobTitleService;
import com.web.hulklogic.service.ManagerService;
import com.web.hulklogic.service.SupportService;

@Controller
public class JobTitleController {
	private static Logger logger = Logger.getLogger(JobTitleController.class);
	@Autowired
	private JobTitleService jobtitleService;
     @Autowired
	 private SupportService supportService;
     @Autowired
     private ApplicantService appservice;
	 
	@RequestMapping(value="/user/getJobtitleNames",method=RequestMethod.POST)
     public @ResponseBody List<JobTitle> getJobTitle(HttpSession session){
		String teamname= (String)session.getAttribute("teamname");
		logger.info("teamname:"+teamname);
		List<JobTitle> listJobTitle=null;
	   try{
		   listJobTitle=jobtitleService.getJobTitle(teamname);
	   }catch(Exception e) {
		   logger.error("Exception is: "+e);
	   }
	  return listJobTitle;
}
	
	@RequestMapping(value="/user/createApplicantDirectly",method=RequestMethod.POST)
    public @ResponseBody String createApplicantDirectly(HttpSession session,@RequestParam(value="jobid", required=false) int jobid,
      @RequestParam(value="applicantid", required=false) int applicantid){
    logger.info("applicantid:"+applicantid);
    logger.info("jobid:"+jobid);
    String teamname= (String)session.getAttribute("teamname");
    logger.info("teamname:"+teamname);
    String currentemail= (String)session.getAttribute("userName");
    logger.info("currentemail:"+currentemail); 
    Integer userid= (Integer)session.getAttribute("userid");
    logger.info("userid:"+userid); 
    String roleName= (String)session.getAttribute("roleName");
    logger.info("roleName:"+roleName);
    String result="fail";
    String sendAlertEmail=null;
    JobTitle jobtitle=new JobTitle();
    jobtitle.setApplicantid(applicantid);
    jobtitle.setJobid(jobid);
    jobtitle.setReferncedemail(currentemail);
    jobtitle.setTeamname(teamname);
    jobtitle.setUserid(userid);
	boolean checkinghold = appservice.appcheckinghold(applicantid,jobid);
	if(!checkinghold){
    if("ROLE_RECRUITER".equalsIgnoreCase(roleName)){
     sendAlertEmail=supportService.managerEmailForSenderAlert(teamname);
     logger.info("sendAlertEmail:"+sendAlertEmail);
 
  }else{
   sendAlertEmail=supportService.recruiterEmailForSenderAlert(jobid);
   logger.info("sendAlertEmail:"+sendAlertEmail);
  }
    jobtitle.setSender_email(sendAlertEmail);
   try{
    result=jobtitleService.createApplicantDirectly1(jobtitle);
   }catch(Exception e){
    logger.error("Exception is: "+e);
   }
   return result;
	}
	else{
		
		return "success";
	}
 }
	  @RequestMapping(value="/user/addComment",method=RequestMethod.POST)
	   public @ResponseBody String  addCommnet(HttpSession session,@RequestParam(value="addComment", required=false) String addCommentStr,
			   @RequestParam(value="app_id", required=false) int app_id   ) {  
		  logger.info("addCommnet:"+addCommentStr); 
		  logger.info("applicantionid:"+app_id); 
		  String currentemail= (String)session.getAttribute("userName");
		   logger.info("currentemail:"+currentemail);
	      String result="fail";
	      try{
	    	  result=jobtitleService.saveAddComment(addCommentStr, app_id,currentemail);
	      }catch(Exception e){
	    	  logger.error("Exception is: "+e);
	      }
	  return result;
}
	  @RequestMapping(value="/user/appaddComment",method=RequestMethod.POST)
	   public @ResponseBody String  appaddComment(HttpSession session, @RequestParam(value="addComment", required=false) String addCommentStr,
			   @RequestParam(value="applicantid", required=false) int applicantid   ) {  
		  logger.info("addComment:"+addCommentStr); 
		  logger.info("applicantid:"+applicantid); 
		  String currentemail= (String)session.getAttribute("userName");
		   logger.info("currentemail:"+currentemail); 
		   String result="fail";
		   try{
	    	 result=jobtitleService.addCommenthistory(addCommentStr, applicantid, currentemail);
		   }catch(Exception e){
		    	  logger.error("Exception is: "+e);
		      }
		  return result;
	  }
	  
	  @RequestMapping(value="/user/updateStatusApplicant",method=RequestMethod.POST)
	     public @ResponseBody String updateAndSaveApplicantDetails(HttpSession session,@RequestParam(value="jobid", required=false) int jobid,
	     @RequestParam(value="applicantid", required=false) int applicantid,@RequestParam(value="applicationId", required=false) int applicationId){
	     logger.info("applicantid:"+applicantid);
	     logger.info("jobid:"+jobid);
	     logger.info("applicationId:"+applicationId);
	     String teamname= (String)session.getAttribute("teamname");
	     logger.info("teamname:"+teamname);
	     String currentemail= (String)session.getAttribute("userName");
	     logger.info("currentemail:"+currentemail); 
	     Integer userid= (Integer)session.getAttribute("userid");
	     logger.info("userid:"+userid); 
	     String roleName= (String)session.getAttribute("roleName");
	  logger.info("roleName:"+roleName);
	     String result="fail";
	     String sendAlertEmail=null;
	     JobTitle jobtitle=new JobTitle();
	     jobtitle.setApplicantid(applicantid);
	     jobtitle.setJobid(jobid);
	     jobtitle.setReferncedemail(currentemail);
	     jobtitle.setTeamname(teamname);
	     jobtitle.setApplicationId(applicationId);
	     jobtitle.setUserid(userid);
	     if("ROLE_RECRUITER".equalsIgnoreCase(roleName)){
	      sendAlertEmail=supportService.managerEmailForSenderAlert(teamname);
	      logger.info("sendAlertEmail:"+sendAlertEmail);
	   }else{
	    sendAlertEmail=supportService.recruiterEmailForSenderAlert(jobid);
	    logger.info("sendAlertEmail:"+sendAlertEmail);
	   }
	     jobtitle.setSender_email(sendAlertEmail);
	    try{
	     result=jobtitleService.updateAndSaveApplicant(jobtitle);
	    
	    }catch(Exception e){
	     logger.error("Exception is: "+e);
	    }
	    return result;
	  }
	  
	  
	  @RequestMapping(value="/user/getDocumentsByAppId",method=RequestMethod.POST)
	     public  @ResponseBody JobsDocuments getDocumentsByAppId(@RequestParam("app_id") int app_id) {
	    logger.info("app_id:"+app_id); 
	        JobsDocuments jobdocuments=null; 
	        try 
	        {
	         jobdocuments= jobtitleService.getJobAndApplicantDocuments(app_id);
	        }catch(Exception e) {
	          logger.error("Exception is: "+e);
	        }
	    return jobdocuments; 
	 }
	  
	  @RequestMapping(value="/manager/getCommentByAppId",method=RequestMethod.POST)
	     public  @ResponseBody  List<JobTitle> getCommnetByAppId(@RequestParam("app_id") int app_id) {
	    logger.info("app_id:"+app_id); 
	    List<JobTitle> getCommentByAppId=null; 
	        try 
	        {
	         getCommentByAppId= jobtitleService.getAppCommnetByAppId(app_id);
	        }catch(Exception e) {
	          logger.error("Exception is: "+e);
	        }
	    return getCommentByAppId; 
	 }
	  
	  
	  @RequestMapping(value = "/manager/targets", method = RequestMethod.POST)
	  public @ResponseBody String saveTargets(HttpSession session, @RequestParam("target") String targetStr, Model map) {
	      Target target = new Gson().fromJson(targetStr, Target.class);
	      String result="fail";
	     
	      String currentemail= (String)session.getAttribute("userName");
	      logger.info("currentemail:"+currentemail);
	      String teamname= (String)session.getAttribute("teamname");
	      logger.info("teamname:"+teamname);
	      Integer userid=(Integer)session.getAttribute("userid");
	      logger.info("userid:"+userid);
	      Target existTargets=jobtitleService.getTargetsDetails(teamname);
	      target.setUserid(userid);
	      target.setEmail(currentemail);
	      target.setTeamname(teamname);
	     try{
	    	 if(existTargets==null){
	    		 result=jobtitleService.saveTarget(target);
	    		 
	    	 }else{
	    		 result=jobtitleService.updateTargetsDetails(target);
	    	 }
	    	     
	      }catch(Exception e){
	       logger.error("Exception is: "+e);
	      }
	     
	  return result;      
	}
	  
	  
	  @RequestMapping(value="/user/getByAppId",method=RequestMethod.POST)
	  public  @ResponseBody ApplicantInfo getApplicantInfoByAppId(@RequestParam("app_id") int app_id) {
	    logger.info("app_id:"+app_id); 
	    ApplicantInfo getApplicantInfoByAppId=null; 
	
	        try 
	        {
	         getApplicantInfoByAppId= jobtitleService.getApplicantInfoByAppId(app_id);
	         logger.info("app_id:"+getApplicantInfoByAppId.getContactno()); 
	        }catch(Exception e) {
	          logger.error("Exception is: "+e);
	        }
	    return getApplicantInfoByAppId; 
	 }
	  
	  @RequestMapping(value="/user/getByAppIdprofilename",method=RequestMethod.POST)
	  public  @ResponseBody ApplicantInfo getApplicantInfoByAppIdprofilename(@RequestParam("app_id") int app_id) {
	    logger.info("app_id:"+app_id); 
	    ApplicantInfo getApplicantInfoByAppId=null; 
	
	        try 
	        {
	         getApplicantInfoByAppId= jobtitleService.getProfileInfoByAppId(app_id);
	         logger.info("app_id:"+getApplicantInfoByAppId.getContactno()); 
	        }catch(Exception e) {
	          logger.error("Exception is: "+e);
	        }
	    return getApplicantInfoByAppId; 
	 }
	 
	  
	  
	   @RequestMapping(value="/user/autoSignature",method=RequestMethod.POST)
	   public  @ResponseBody String saveAutoSignature(HttpSession session,@RequestParam("auto_signature") String auto_signature) {
	   AutoSignature autoSingnature = new Gson().fromJson(auto_signature, AutoSignature.class);
	   String currentemail= (String)session.getAttribute("userName");
	   autoSingnature.setCurrentEmail(currentemail);
	    logger.info("currentemail:"+currentemail); 
	       String result="fail"; 
	       try 
	         {
	    	   logger.info("sightml:"+autoSingnature.getSightml()); 
	         result= jobtitleService.saveAutoSignature(autoSingnature);
	         logger.info("result:"+result); 
	         }catch(Exception e) {
	           logger.error("Exception is: "+e);
	         }
	         return result; 
	         }
	  
	  
	  @RequestMapping(value="/user/getAllTemplateNames",method=RequestMethod.POST)
	     public @ResponseBody List<CreateTemplate> getAllTemplateNames(HttpSession session){
			List<CreateTemplate> getTemplateNames=null;
			  String organization= (String)session.getAttribute("organization");
			  logger.info(organization);
		   try{
			   getTemplateNames=jobtitleService.getAllTemplateNames(organization);
		   }catch(Exception e) {
			   logger.error("Exception is: "+e);
		   }
		  return getTemplateNames;
}

}
