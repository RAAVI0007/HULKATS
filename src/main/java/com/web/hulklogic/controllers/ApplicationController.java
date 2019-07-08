package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.ApplicantDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.ApplicationService;
import com.web.hulklogic.service.TemplateService;

@Controller
public class ApplicationController {

	private static Logger logger = Logger.getLogger(ApplicationController.class);
	@Autowired
	private ApplicationService  applicantionService;
	@Autowired
	private  ApplicantService    applicantService;
	@Autowired
	private TemplateService templateService;
	
	
	
	
	@RequestMapping(value="/user/getCities",method=RequestMethod.POST)
	   public @ResponseBody List<User> getCitiesByAutoComplete(@RequestParam(value="state", required=false) String stateStr,
			   @RequestParam(value="phonecode", required=false) int phonecode){
	   logger.info("stateStr:"+stateStr);
	   logger.info("phonecode:"+phonecode);
	   String upperCaseCity=stateStr.toUpperCase();
	   logger.info("upperCaseCity:"+upperCaseCity);
	   List<User> listcities=null;
	   try{
	   listcities=applicantionService.autoCompleteCities(phonecode, upperCaseCity);
	   }catch(Exception e) {
		   logger.error("Exception is: "+e);
	   }
	  return listcities;
}
	
	
	@RequestMapping(value="/user/getTemplate",method=RequestMethod.POST)
    public @ResponseBody List<CreateTemplate> getTemplateByAutoComplete(@RequestParam(value="template", required=false) String tmeplateStr){
    logger.info("tmeplateStr:"+tmeplateStr);
    String upperCaseTemplate=tmeplateStr.toUpperCase();
    logger.info("upperCaseCity:"+upperCaseTemplate);
    List<CreateTemplate> listtemplate=null;
    try{
     listtemplate=applicantService.autoCompleteTemplate(upperCaseTemplate);
    }catch(Exception e) {
    	 logger.error("Exception is: "+e);
    }
   return listtemplate;
}
	
	
	@RequestMapping(value="/manager/getSubjectBody",method=RequestMethod.POST)
    public @ResponseBody List<CreateTemplate> getSubjectBodyBytemplate(@RequestParam(value="templatename", required=false) String templateStr){
    logger.info("templateStr:"+templateStr);
    List<CreateTemplate> listsubjectbody=null;
    try{
     listsubjectbody=applicantService.getSubjectBody(templateStr);
    }catch(Exception e) {
     logger.error("Exception is: "+e);
    }
   return listsubjectbody;
}
	
	  @RequestMapping(value="/user/getApplicants",method=RequestMethod.POST)
	   public @ResponseBody  List<Applicants> getApplicants(HttpSession session,@RequestParam(value="jobid", required=false) int jobid){
	     
	   String teamname= (String)session.getAttribute("teamname");
	   logger.info("teamname:"+teamname);
	   List<Applicants> listapplicants=null;
	   try{
	    listapplicants=applicantionService.getApplicantDetails(jobid,teamname);
	   
	   }catch(Exception e){
		   logger.error("Exception is: "+e);
	   }
	   return listapplicants;
}
	  
	  @RequestMapping(value="/manager/getApplication",method=RequestMethod.POST)
	   public @ResponseBody  List<Applicants> getApplication(HttpSession session,@RequestParam(value="jobid", required=false) int jobid){
	   logger.info("applicantdetails:");   
	   String teamname= (String)session.getAttribute("teamname");
	   logger.info("teamname:"+teamname);
	   List<Applicants> listapplicants=null;
	   try{
	    listapplicants=applicantionService.getApplicationDetails(jobid, teamname);
	    logger.info("size:"+listapplicants.size());
	   }catch(Exception e){
		   logger.error("Exception is: "+e);
	   }
	   return listapplicants;
}
	
	  @RequestMapping(value = "/user/createTemplate", method = RequestMethod.POST)
	   public @ResponseBody String createTemplate(HttpSession session,  @RequestParam("createtemplate") String createtemplateStr) {
	    
	     if (null == createtemplateStr || createtemplateStr.trim().isEmpty()) {
	     return "failure";
	    }
	     
	     CreateTemplate createTemplate = new Gson().fromJson(createtemplateStr, CreateTemplate.class);
	     boolean status = applicantionService.getLastTemplatename(createTemplate.getTemplate_name());
	     if(status){
	  	   return "Template Name is already registered! Please choose another one";
	  	  }
	     
	     String templatename=createTemplate.template_name;
	     String uppercaseTemplatename=templatename.toUpperCase();
	     createTemplate.setTemplate_name(uppercaseTemplatename);
	     String currentemail= (String)session.getAttribute("userName");
	     logger.info("currentemail:"+currentemail);
	     String organization= (String)session.getAttribute("organization");
		  logger.info(organization);
		  createTemplate.setOrganization(organization);
	     createTemplate.setEmail( currentemail);
	     String result="fail";
	     String document1=null;
	     String document2=null;
	     FileOutputStream fois1=null;
	 
	 try{ 
	   Date todayDate=new Date();
	   
	     if(createTemplate.getFileType()!=null){
	    	 
	     byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(createTemplate.getAttachement());
	     
	     if(createTemplate.getFileType().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
	    	 
	     document1=createTemplate.getDocumentName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx";
	     
	     }else{
	     document1=createTemplate.getDocumentName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";
	     }
	  //   File destinationFile1 = new File(context.getRealPath("/WEB-INF/template")+  File.separator +document1);
	     File destinationFile1 = new File("/opt/hulkats/template/" +document1);
	   try {
		   
	   fois1=new FileOutputStream(destinationFile1);
	   
	   }catch (FileNotFoundException e1) {
	         logger.error("Exception is: "+e1);
	   }try {
	    fois1.write(documentdecode1);
	   } catch (IOException e1) {
	     logger.error("Exception is: "+e1);
	   }   
	     try 
	        {
	          if(fois1!=null)  
	          fois1.close();
	       }catch(Exception e) {
	       logger.error("Exception is: "+e);
	       }
	       createTemplate.setAttachement(document1);   
	      }else{
	      createTemplate.setAttachement(null);
	    }
	     
	     
	     if(createTemplate.getFileType1()!=null){
	        	
	     byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(createTemplate.getAttachement1());
	       
		 if(createTemplate.getFileType1().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
		    	 
			 document2=createTemplate.getDocumentName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx";
		   }else{
		    	 
		      document2=createTemplate.getDocumentName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";
		     }
		    // File destinationFile1 = new File(context.getRealPath("/WEB-INF/template")+  File.separator +document2);
		 File destinationFile1 = new File("/opt/hulkats/template/" +document2);
		   try {
		   fois1=new FileOutputStream(destinationFile1);
		   }catch (FileNotFoundException e1) {
		         logger.error("Exception is: "+e1);
		   }
		      try {
		    fois1.write(documentdecode1);
		   } catch (IOException e1) {
		     logger.error("Exception is: "+e1);
		   }   
		     try 
		        {
		          if(fois1!=null)  
		          fois1.close();
		       }catch(Exception e) {
		       logger.error("Exception is: "+e);
		       }
		       createTemplate.setAttachement1(document2);   
		      }else{
		      createTemplate.setAttachement1(null);
		    }
	      result=applicantionService.saveTemplate(createTemplate);
	   }catch(Exception e){
	    logger.error("Exception is: "+e);
	   }
	 
	 return result;
	 }
	  
	  
	  
	  
	  @RequestMapping(value = "/user/updateTemplate", method = RequestMethod.POST)
	   public @ResponseBody String updateTemplate(HttpSession session,  @RequestParam("createtemplate") String createtemplateStr) {
	    
	     if (null == createtemplateStr || createtemplateStr.trim().isEmpty()) {
	     return "failure";
	    }
	     
	     CreateTemplate createTemplate = new Gson().fromJson(createtemplateStr, CreateTemplate.class);
	     
	     logger.info("update createTemplate id:"+createTemplate.getTemplate_id());
	     
	     String currentemail= (String)session.getAttribute("userName");
	     logger.info("currentemail:"+currentemail); 
	     createTemplate.setEmail( currentemail);
	     String result="fail";
	     String document1=null;
	     String document2=null;
	     FileOutputStream fois1=null;
	 
	   try{ 
		   
	     java.util.Date todayDate=new java.util.Date();
	     if(createTemplate.getFileType()!=null){
	     
	      byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(createTemplate.getAttachement());
	      
	      if(createTemplate.getFileType().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
	    	  
	        document1=createTemplate.getDocumentName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx";
	      
	      }else{
	    	  document1=createTemplate.getDocumentName()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";
	          
	      }
	   //   File destinationFile1 = new File(context.getRealPath("/WEB-INF/template")+  File.separator +document1);
	      File destinationFile1 = new File("/opt/hulkats/template/" +document1);
	    
	      try {
	    fois1=new FileOutputStream(destinationFile1);
	   } catch (FileNotFoundException e1) {
	         logger.error("Exception is: "+e1);
	   }
	      try {
	    fois1.write(documentdecode1);
	   } catch (IOException e1) {
	     logger.error("Exception is: "+e1);
	   }   
	     try 
	        {
	          if(fois1!=null)  
	               fois1.close();
	         }catch(Exception e) {
	          
	          logger.error("Exception is: "+e);
	           }
	       createTemplate.setAttachement(document1);   
	    }
	     
	    
	     
     if(createTemplate.getFileType1()!=null){
	    
           byte[] documentdecode2 = DatatypeConverter.parseBase64Binary(createTemplate.getAttachement1());
	      
          if(createTemplate.getFileType1().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
	    	  
            document2=createTemplate.getDocumentName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx";
	      
	      }else{
	    	 
	    	  document2=createTemplate.getDocumentName1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";  
	      }
	   //   File destinationFile2= new File(context.getRealPath("/WEB-INF/template")+  File.separator +document2);
          File destinationFile2= new File("/opt/hulkats/template/" +document2);
	    
	      try {
	    	  
	        fois1=new FileOutputStream(destinationFile2);
	        
	       } catch (FileNotFoundException e1) {
	         logger.error("Exception is: "+e1);
	     }
	      try {
	    	  
	     fois1.write(documentdecode2);
	     
	   } catch (IOException e1) {
	     logger.error("Exception is: "+e1);
	   }   
	     try 
	        {
	          if(fois1!=null)  
	               fois1.close();
	         }catch(Exception e) {
	          
	          logger.error("Exception is: "+e);
	           }
	       createTemplate.setAttachement1(document2);   
	    }
	       result=applicantionService.updateTemplate(createTemplate);
	       
	   }catch(Exception e){
	    logger.error("Exception is: "+e);
	   }
	 
	 return result;
	  
	   }
	  
	   @RequestMapping(value="/manager/sendTemplates",method=RequestMethod.POST)
	    public @ResponseBody List<CreateTemplate> getAllTemapltes(@RequestParam(value="template_id", required=false) int template_id){
		   logger.info("template_id:"+template_id); 
	    List<CreateTemplate> listtemplates=null;
	    try{
	     listtemplates=applicantionService.getAllTemplates(template_id);
	    }catch(Exception e){
	     logger.error("Exception is: "+e);
	    }
	    return listtemplates;
	     }
	   
	   @RequestMapping(value="/user/getAttachments",method=RequestMethod.POST)
	    public @ResponseBody List<CreateTemplate> getTemplateTemapltes(@RequestParam(value="template_id", required=false) int template_id){
		   logger.info("template_id:"+template_id); 
	    List<CreateTemplate> listtemplates=null;
	    try{
	     listtemplates=applicantionService.getAllTemplates(template_id);
	    }catch(Exception e){
	     logger.error("Exception is: "+e);
	    }
	    return listtemplates;
	     }
	   
	   
	   
	   @RequestMapping(value="/manager/sendTemplateIdName",method=RequestMethod.POST)
	    public @ResponseBody List<CreateTemplate> getTemplateNameId(){
	     List<CreateTemplate> listtemplateIdName=null;
	      try{
	     listtemplateIdName=applicantionService.getTemplateNameId();
	    }catch(Exception e){
	     logger.error("Exception is: "+e);
	    }
	    return listtemplateIdName;
	    }
	   
	   
	   @RequestMapping(value="/manager/sendApplicant",method=RequestMethod.POST)
	    public @ResponseBody List<Applicants> getApplicantNameEmail(){
	   List<Applicants> applicannameemails=null;
	    try{
	     applicannameemails=applicantionService.getApplicantsNameEmail();
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return applicannameemails;
	}
	   
	   
	   @RequestMapping(value = "/user/sendemail", method = RequestMethod.POST)
	   public @ResponseBody String sendEmailToApplicnation(HttpSession session,@RequestParam("applicants") String senderEmailStr) {
	     
	     if (null == senderEmailStr || senderEmailStr.trim().isEmpty()) {
	     return "failure";
	    }
	     
	     SenderEmail senderEmail = new Gson().fromJson(senderEmailStr, SenderEmail.class);
	     int applicantid=senderEmail.getApplicationid();
	     String currentemail= (String)session.getAttribute("userName");
	     logger.info("currentemail:"+currentemail); 
	     String[] attachFiles = new String[4];
	     String templateName=null;
	     String filename=null;
	     String pdffile=null;
	     String pdffile1=null;
	     String result="fail";
	     try{
	    	  
	    java.util.Date todayDate=new java.util.Date();
		 FileOutputStream fois1=null;  
		      
	     if(senderEmail.getFileType()!=null){
	    	
	     byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(senderEmail.getTemplateDocument());
	        
	     if(senderEmail.getFileType().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
	    		
	         templateName=senderEmail.getAttachment()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx"; 
	         
	     }else{
	          templateName=senderEmail.getAttachment()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";       
	      }
	      senderEmail.setCreateAttachment(templateName);  
	     // File destinationFile1 = new File(context.getRealPath("/WEB-INF/template")+  File.separator +templateName);
	      
	      File destinationFile1 = new File("/opt/hulkats/template/" +templateName);
	      fois1=new FileOutputStream(destinationFile1);
	      fois1.write(documentdecode1);  
	      try 
	        {
	    	    if(fois1!=null)  
	    	     fois1.close();
	    	    
	    	}catch(Exception e) {
	    	    logger.error("Exception is: "+e);
	    	    }
	    	     
	  //    String  filename1 = context.getRealPath("/WEB-INF/template/")+templateName;
	      String  filename1 = "/opt/hulkats/template/"+templateName;
	      logger.info("file is getting from template folder:"+filename1);  
	      attachFiles[0]=filename1;
	       } 
	        
	       if(senderEmail.getFileType1()!=null){
	         
	       byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(senderEmail.getTemplateDocument1());
	            
	       if(senderEmail.getFileType1().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")){
	        		
	        templateName=senderEmail.getAttachment1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".docx"; 
	             
	        }else{
	        		templateName=senderEmail.getAttachment1()+"_"+todayDate.getYear()+"_"+todayDate.getMonth()+"_"+todayDate.getDay()+"_"+todayDate.getHours()+"_"+todayDate.getSeconds()+".pdf";       
	         }
	       senderEmail.setCreateAttachment1(templateName); 
	        // File destinationFile1 = new File(context.getRealPath("/WEB-INF/template")+  File.separator +templateName);
	       File destinationFile1 = new File("/opt/hulkats/template/" +templateName);
	         fois1=new FileOutputStream(destinationFile1);
	         fois1.write(documentdecode1);  
	                
	         try 
	        {
	          if(fois1!=null)  
	          fois1.close();
	          }catch(Exception e) {
	          logger.error("Exception is: "+e);
	         }
	        	     
	               // String  filename2 = context.getRealPath("/WEB-INF/template/")+templateName;
	         String  filename2 = "/opt/hulkats/template/"+templateName;
	        	    logger.info("file is getting from template folder:"+filename2);  
	        	    attachFiles[1]=filename2;
	                }  
	        
	        
	            if(senderEmail.getAttachment2()!=null){
	            	  
	            	    //boolean status=templateService.checkTemplateName(bulkEmailSend.getAttachement2());
	            	 //   String filename3 = context.getRealPath("/WEB-INF/template/")+senderEmail.getAttachment2();
	            	String filename3 = "/opt/hulkats/template/"+senderEmail.getAttachment2();
	            	    logger.info("file is getting as docx :"+filename3);  
	       	        	 attachFiles[2]=filename3;
	            	    }
	            	    
	          if(senderEmail.getAttachment3()!=null){
	            	 
	          	      //boolean status=templateService.checkTemplateName(bulkEmailSend.getAttachement3());
	          	    //    String filename4 = context.getRealPath("/WEB-INF/template/")+senderEmail.getAttachment3();  
	          	      String filename4 = "/opt/hulkats/template/"+senderEmail.getAttachment3();
	     	        	logger.info("file is getting as docx :"+filename4);   
	     	        	attachFiles[3]=filename4;
	          	       
	               }	   
	        
	     AutoSignature autoSignature = templateService.geAutoSignatureByEmail(currentemail);
	     if(autoSignature==null) {
        	 return "no signature";
         }
	     senderEmail.setTotalDocuments(attachFiles);
	     senderEmail.setCurrentemail(currentemail);
	     SenderEmail getEmailAndName =applicantionService.getAppllicantDetailsById(applicantid);
	     logger.info("to address email:"+getEmailAndName.getName());
	     logger.info("to adress name:"+getEmailAndName.getEmail());
	     senderEmail.setName(getEmailAndName.getName());
	     senderEmail.setEmail(getEmailAndName.getEmail()); 
	     boolean status =applicantionService.sendEmailToApplicant(senderEmail,autoSignature);
	     if(status){
	      result= templateService.saveIndividualHistroy(senderEmail);
	     }
	     }catch(Exception e){
	    	  logger.error("Exception is: "+e);
	      }
	    return result;
	  }
	   
	   
	   @RequestMapping(value="/user/getjobtitle",method=RequestMethod.POST)
	   public @ResponseBody JobTitle getjobtitle(@RequestParam(value="jobid", required=false) int jobid) {
		   logger.info("jobtitle:"+jobid); 
		    JobTitle listtitle=null;
		    try{
		    	listtitle=applicantionService.getjobtitle(jobid);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return listtitle;
	   }
	   
	   
	   @RequestMapping(value="/user/updateSource",method=RequestMethod.POST)
	   public @ResponseBody String updateSourceInAppStart(@RequestParam("application") String applicantStr,HttpSession session) {
		     ApplicantInfo applicant = new Gson().fromJson(applicantStr, ApplicantInfo.class);
		     String currentemail= (String)session.getAttribute("userName");
		     logger.info("currentemail:"+currentemail);
		     logger.info("appId:"+applicant.getApp_id());
		     logger.info("source:"+applicant.getSource());
		     applicant.setEmail(currentemail);
		     String status="fail";
		     try{
		    	status=applicantionService.updateSourceAndInsertSource(applicant);
		        }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return status;
	   }
	   
	   
	   @RequestMapping(value="/user/getAppStatus",method=RequestMethod.POST)
	   public @ResponseBody String getAppStatusByAppId(@RequestParam("appId") int appId,HttpSession session) {
		     
		     logger.info("appId:"+appId);
		     
		     String appStatus="fail";
		     try{
		    	 appStatus=applicantionService.getApplicationStatusByApp_Id(appId);
		        }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return appStatus;
	   }
	   
	   @RequestMapping(value="/user/getprofiledetails",method=RequestMethod.POST)
	   public @ResponseBody ApplicantDetails getjobapplicantdetails(@RequestParam(value="applicantdetails_id", required=false) int applicantdetails_id) {
		   logger.info("applicantdetails_id:"+applicantdetails_id); 
		   ApplicantDetails getjobapplicantdetails=null;
		    try{
		    	getjobapplicantdetails=applicantService.profile(applicantdetails_id);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return getjobapplicantdetails;
	   }
	     
	   
}