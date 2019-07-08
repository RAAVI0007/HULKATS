package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.ChangeSource;
import com.web.hulklogic.entity.Message;
import com.web.hulklogic.entity.Support;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.SupportService;


@Controller
public class SupportController {
	
	private static Logger logger = Logger.getLogger( SupportController.class);
	
	@Autowired
	private SupportService supportService;
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value = "/user/saveSupportInfo", method = RequestMethod.POST)
	public @ResponseBody String saveUser(HttpSession session, @RequestParam("supportInfo") String supportStr) {
	Support support = new Gson().fromJson(supportStr, Support.class);
	String currenLoginEmail = (String)session.getAttribute("userName");
	logger.info("currenLoginEmail:"+currenLoginEmail);
	support.setEmail(currenLoginEmail);
	String imageName=null;
	String imageName1=null;
	String result="fail";
	
	try{
		
		java.util.Date todayDate=new java.util.Date();
		byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(support.getUploadImgEncoded());
		
		if(support.getImageType().equals("jpg")){
			 imageName=support.getImageName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+"."+"jpg";
	      
		}else{
			imageName=support.getImageName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+"."+"png";
		}
		
	    File destinationFile1 = new File(context.getRealPath("/WEB-INF/support")+  File.separator +imageName);
	    FileOutputStream fois1=new FileOutputStream(destinationFile1);
	    fois1.write(documentdecode1);
	    try 
         {
             if(fois1!=null)  
                fois1.close();
         }catch(Exception e) {
              logger.error("Exception is: "+e);
         }
	    
	    support.setPrbimg(imageName);
	    
        byte[] imageDecoded = DatatypeConverter.parseBase64Binary(support.getUploadImgEncoded1());
		
		if(support.getImageType1().equals("jpg")){
			imageName1=support.getImageName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+"."+"jpg";
	      
		}else{
			imageName1=support.getImageName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+"."+"png";
		}
		
	    File destinationFile2 = new File(context.getRealPath("/WEB-INF/support")+  File.separator +imageName1);
	    FileOutputStream fois2=new FileOutputStream(destinationFile2);
	    fois2.write(imageDecoded);
	    try 
         {
             if(fois2!=null)  
                fois2.close();
         }catch(Exception e) {
              logger.error("Exception is: "+e);
         }
	    
	    support.setPrbimg1(imageName1);
	    result=supportService.saveSupportDetails(support);	
	}catch(Exception e){
		 logger.error("Exception is: "+e);
	}

	return result;
	}	
	

	@RequestMapping(value="/user/getSupports",method=RequestMethod.POST)
	public  @ResponseBody String getSupportDetails() {
	List<Support> supportDetails=supportService.getSupportDetails();
	return new Gson().toJson(supportDetails);
	}

	
	@RequestMapping(value="/user/sendImage",method=RequestMethod.POST)
	public @ResponseBody String getSupportImage(@RequestParam(value="imageName", required=false) String ImageName){
		logger.info("ImageName:"+ImageName);
		String encodeImage=null;
	
       try{
    	         File destinationFile = new File(context.getRealPath("/WEB-INF/support")+ File.separator + ImageName);
		    	 FileInputStream fileInputStreamReader = null;
	             try{
	            	  fileInputStreamReader = new FileInputStream(destinationFile);
	                  byte[] bytes = new byte[(int)destinationFile.length()];
	                  fileInputStreamReader.read(bytes);
	                  encodeImage = new String(Base64.encodeBase64(bytes), "UTF-8");
	               }
	                 catch (Exception e) {
	            	 logger.error("Exception is: "+e);
				  }
				 try {
					    if(fileInputStreamReader!=null)
						 fileInputStreamReader.close(); 
				    }catch (Exception e) {
					 logger.error("Exception is: "+e);
	            } 
		        }catch(Exception e){
			  logger.error("Exception is: "+e);
		}
            return encodeImage;
	}
	@RequestMapping(value = "/user/teamname", method = RequestMethod.GET)
	   public String activate(Model model,HttpSession session) {
	   String teamname1 = (String)session.getAttribute("teamname1");
	   logger.info("teamname1:"+teamname1);
	   model.addAttribute("teamname1",teamname1);  
	    return "userspage";   
	   }
	
	@RequestMapping(value = "/user/messagesending", method = RequestMethod.POST)
	public @ResponseBody String messageSending(HttpSession session, @RequestParam("messageinfo") String messageStr) {
	Message message = new Gson().fromJson(messageStr, Message.class);
	String currenLoginEmail = (String)session.getAttribute("userName");
	logger.info("currenLoginEmail:"+currenLoginEmail);
	Integer userid = (Integer)session.getAttribute("userid");
	String senderMessageEmail=supportService.getEmailBySenderMessage(message.getTo_userid());
	logger.info("senderMessageEmail:"+senderMessageEmail);
	message.setFrom_email(currenLoginEmail);
	message.setUserid(userid);
	message.setTo_email(senderMessageEmail);
	String result="fail";
	try {
		result=supportService.messagestore(message);
	}catch(Exception e) {
		 logger.error("Exception is: "+e);
	}
	return result;
	}
	
	@RequestMapping(value="/user/getMessage",method=RequestMethod.POST)
	public  @ResponseBody String getMessageInfo(HttpSession session) {
	String currenLoginEmail = (String)session.getAttribute("userName");
	logger.info("currenLoginEmail:"+currenLoginEmail);
	List<Message> getMessagesDetails=null;
	try {
		getMessagesDetails=supportService.getMessageDetails(currenLoginEmail);
	}catch(Exception e) {
		 logger.error("Exception is: "+e);
	}
	
	return new Gson().toJson(getMessagesDetails);
	}	
	

	@RequestMapping(value="/user/getUserMessage",method=RequestMethod.POST)
	public  @ResponseBody String getSignleUserMessage(HttpSession session, @RequestParam("userid") int to_userid) {
	Integer from_userid = (Integer)session.getAttribute("userid");
	logger.info("userid:"+from_userid);
	List<Message> getMessagesDetails=null;
	try {
	 getMessagesDetails=supportService.getMessagesSingleUser(from_userid, to_userid);
	}catch(Exception e) {
		 logger.error("Exception is: "+e);
	}
	return new Gson().toJson(getMessagesDetails);
	}	
	
	
	@RequestMapping(value="/user/getAllMessages",method=RequestMethod.POST)
	public  @ResponseBody String getAllMessages(HttpSession session) {
	Integer userid = (Integer)session.getAttribute("userid");
	logger.info("userid:"+userid);
	List<Message> getMessagesDetails=null;
	try {
	 getMessagesDetails=supportService.allMessages(userid);
	}catch(Exception e) {
		 logger.error("Exception is: "+e);
	}
	return new Gson().toJson(getMessagesDetails);
	}	
	
	
	@RequestMapping(value="/user/getjobAlerts",method=RequestMethod.POST)
	 public  @ResponseBody String getJobAlertDetail(HttpSession session) {
	 String currentEmail = (String)session.getAttribute("userName");
	    logger.info("currentEmail:"+currentEmail); 
	    List<AlertDetails> jobAlertDetails=supportService.getJobAlerts(currentEmail);
	 return new Gson().toJson(jobAlertDetails);
	 }


	 @RequestMapping(value="/user/getappAlerts",method=RequestMethod.POST)
	 public  @ResponseBody String getApplicationAlertDetail(HttpSession session) {
	 String currentEmail = (String)session.getAttribute("userName");
	    logger.info("currentEmail:"+currentEmail); 
	    List<AlertDetails> applicationAlertDetails=supportService.getApplicationAlert(currentEmail);
	 return new Gson().toJson(applicationAlertDetails);
	 }
	 
	 @RequestMapping(value="/user/getapplicantAlerts",method=RequestMethod.POST)
	 public  @ResponseBody String getApplicantAlertDetail(HttpSession session) {
	 String currentEmail = (String)session.getAttribute("userName");
	  List<AlertDetails> applicantAlertDetails=supportService.getApplicantAlert(currentEmail);
	 return new Gson().toJson(applicantAlertDetails);
	 }
	 
	
	 @RequestMapping(value="/user/getallAlertslist",method=RequestMethod.POST)
	 public  @ResponseBody String getAlertslist(HttpSession session) {
		 String currentEmail = (String)session.getAttribute("userName");
		 logger.info("currentEmail:"+currentEmail); 
		 Integer userid = (Integer)session.getAttribute("userid");
		 logger.info("userid:"+userid);
	     List<AlertDetails> AlertDetails=supportService.getAlerts(userid,currentEmail);
	    return new Gson().toJson(AlertDetails);
	 }
	 
	 
	 @RequestMapping(value="/user/msgstatus",method=RequestMethod.POST)
		public  @ResponseBody String updatemsgstatus( @RequestParam("message_id") int message_id) {
		 logger.info("message_id:"+message_id); 
		String message = supportService.msgStatus(message_id);
		return new Gson().toJson(message);
		}	
		
	 
	 
	 @RequestMapping(value="/user/alertStatus",method=RequestMethod.POST)
		public  @ResponseBody String updateAlertStatus( @RequestParam("alert_id") int alert_id) {
		 logger.info("alert_id:"+alert_id); 
		 String result="fail";
		 result=supportService.alertStatus(alert_id);
		 return result;
		
		}	
		
	 
	    @RequestMapping(value="/manager/existTemaname",method=RequestMethod.POST)
		public  @ResponseBody String getExistTeamname(HttpSession session) {
		  Integer userId=(Integer)session.getAttribute("userid");
		  logger.info("userId:"+userId); 
		  String getTeamname = supportService.getExistTemaname(userId);
		  return getTeamname;
		}	
	
	     @RequestMapping(value="/manager/existTargets",method=RequestMethod.POST)
	 	   public @ResponseBody Target getCitiesByAutoComplete(HttpSession session){
	    	 Integer userId=(Integer)session.getAttribute("userid");
		  	 logger.info("userId:"+userId); 
	 	     Target getExistTargets=null;
	 	     getExistTargets=supportService.getExistTargets(userId);
	 	     return getExistTargets;
	 	   
	 }
	     
	     @RequestMapping(value="/manager/sourcedata",method=RequestMethod.POST)
	 	   public @ResponseBody ChangeSource sourcedata(HttpSession session){
	    	 Integer sourceid=(Integer)session.getAttribute("sourceid");
		  	 logger.info("sourceid:"+sourceid); 
		  	ChangeSource changesource=null;
		  	changesource= supportService.changeSource(sourceid);
		  	logger.info(changesource.getStep1());
	 	     return changesource;   
	 }
	      
	     @RequestMapping(value="/user/getdetails",method=RequestMethod.POST)
	     public  @ResponseBody String getDetail(HttpSession session) {
	     String currentEmail = (String)session.getAttribute("userName");
	     String lastname = (String)session.getAttribute("lastname");
	     String firstname = (String)session.getAttribute("firstname");
	     String mobile =  (String)session.getAttribute("mobile");
	        logger.info("currentEmail:"+currentEmail); 
	        logger.info("lastname:"+lastname);
	        logger.info("firstname:"+firstname);
	        logger.info("mobile:"+mobile);
	      User user = new User();
	      user.setFirstname(firstname);
	      user.setLastname(lastname);
	      user.setEmail(currentEmail);
	      user.setContactno(mobile);
	     return new Gson().toJson(user);
	     }
	     
	       @RequestMapping(value="/user/existAutoSign",method=RequestMethod.POST)
	 	   public @ResponseBody AutoSignature getAutoSignature(HttpSession session){
	    	 Integer userId=(Integer)session.getAttribute("userid");
		  	 logger.info("userId:"+userId); 
		  	 AutoSignature getExistAutoSign=null;
		  	 getExistAutoSign=supportService.getAutoSignatureDetails(userId);
	 	     return getExistAutoSign;
	 	   
	 }
	       
	        @RequestMapping(value="/user/totalMessages",method=RequestMethod.POST)
	 	    public @ResponseBody String getTotalMessage(HttpSession session){
	    	 Integer userId=(Integer)session.getAttribute("userid");
		  	 logger.info("userId:"+userId); 
		  	 int  totalMessage=0;
		  	 totalMessage=supportService.totalMessages(userId);
		  	 return new Gson().toJson(totalMessage);
	 	   
	 }
	        
	        @RequestMapping(value="/user/totalAlerts",method=RequestMethod.POST)
	 	    public @ResponseBody String getTotalAlerts(HttpSession session){
	    	   String email=(String)session.getAttribute("userName");
		  	   logger.info("email:"+email); 
		  	   int  totalAlerts=0;
		   	   totalAlerts=supportService.totalAlerts(email);
		   	  return new Gson().toJson(totalAlerts);
	 	   
	 }    
	        
}
