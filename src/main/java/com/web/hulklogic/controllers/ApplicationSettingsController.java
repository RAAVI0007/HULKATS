package com.web.hulklogic.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicationSettingsService;
import com.web.hulklogic.service.TemplateService;
import com.web.hulklogic.service.UserService;
import com.web.hulklogic.utility.DocumentDeleteUtility;




@Controller
public class ApplicationSettingsController {
	private static Logger logger = Logger.getLogger(ApplicationSettingsController.class);	
	@Autowired
	private ApplicationSettingsService applicationSettingsService; 
	@Autowired
	private TemplateService templateService;
	@Autowired
	private UserService userService;
	
	
	@Autowired
    private ServletContext context;
	
	    @RequestMapping(value="/user/getCountries",method=RequestMethod.POST)
	    public @ResponseBody List<CreateJob> getAllCountries(){
		List<CreateJob> listcoutries=null;
	      try{
		       listcoutries=applicationSettingsService.getCountries();
	       }catch(Exception e) {
		           logger.error("Exception is: "+e);
	      }
	    return listcoutries;
}
	    @RequestMapping(value="/user/getStates",method=RequestMethod.POST)
	    public @ResponseBody List<CreateJob> getAllStates(@RequestParam(value="countryId", required=false) int countryId){
	    	    logger.info("countryId:"+countryId);	
		        List<CreateJob> liststates=null;
	            try{
	    	     liststates=applicationSettingsService.getAllStates(countryId);
	         }catch(Exception e) {
		              logger.error("Exception is: "+e);
	             }
	    return liststates;
}
	    
	    @RequestMapping(value="/user/getAllCities",method=RequestMethod.POST)
	    public @ResponseBody List<CreateJob> getAllCities(@RequestParam(value="state_id", required=false) int state_id){
	    	    logger.info("state_id:"+state_id);	
		        List<CreateJob> listcities=null;
	            try{
	            	listcities=applicationSettingsService.getAllCities(state_id);
	         }catch(Exception e) {
		              logger.error("Exception is: "+e);
	             }
	    return listcities;
}
	    @RequestMapping(value="/user/getAllCountries",method=RequestMethod.POST)
	    public @ResponseBody List<CreateJob> getAllCountriesByPhoneCode(@RequestParam(value="phoneCode", required=false) int phoneCode){
	    	    logger.info("phoneCode:"+phoneCode);	
		        List<CreateJob> listCountries=null;
	            try{
	            	listCountries=applicationSettingsService.getCountriesByPhoneCode(phoneCode);
	         }catch(Exception e) {
		              logger.error("Exception is: "+e);
	             }
	    return listCountries;
}
	    @RequestMapping(value="/user/deleteTemplateFile",method=RequestMethod.POST)
	    public @ResponseBody String deleteFiles(@RequestParam(value="templateId", required=false) int templateId,
	    		                @RequestParam(value="filename", required=false) String fileName,
	    		                @RequestParam(value="fileNumber", required=false) int fileNumber) {
	    	
	    	logger.info("filename:"+fileName);
	    	logger.info("templateId:"+templateId);
	    	logger.info("fileNumber:"+fileNumber);
	    	DocumentDeleteUtility documentDelete=null;
	    	String result="fail";
	    	boolean deleteFileStatus;
	    	File destinationFile=null;
	 
	    	if(fileNumber==1) {
	    		CreateTemplate getAttachements=templateService.getTempalteDocumentsById(templateId);
	    		 logger.info("atta1:"+getAttachements.getAttachement());
	    	 if(getAttachements.getAttachement().equals(fileName)) {
	    		 //   destinationFile = new File(context.getRealPath("/WEB-INF/template"));
	    		 destinationFile = new File("/opt/hulkats/template/");
			    	documentDelete=new DocumentDeleteUtility();
			    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
			    	logger.info("deleteFileStatus:"+deleteFileStatus);
			    	if(deleteFileStatus) {
			    		applicationSettingsService.updateAttachements1(templateId);
			    	}
	    	 }
	           
	    	}else if(fileNumber==2) {
	    		CreateTemplate getAttachements=templateService.getTempalteDocumentsById(templateId);
	    		logger.info("atta2:"+getAttachements.getAttachement1());
	    		 if(getAttachements.getAttachement1().equals(fileName)) {
		    		//    destinationFile = new File(context.getRealPath("/WEB-INF/template"));
	    			 destinationFile = new File("/opt/hulkats/template/");
				    	documentDelete=new DocumentDeleteUtility();
				    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
				    	logger.info("deleteFileStatus:"+deleteFileStatus);
				    	
				    	if(deleteFileStatus) {
				    		applicationSettingsService.updateAttachements2(templateId);
				    	}
		    	 }
	    	}else {
	    		return "file name is does not exist";
	    	}
	      
	    	
	   
	         return result;
	    }
	    
	    
	    @RequestMapping(value="/user/sendAutoSignature",method=RequestMethod.POST)
	   public @ResponseBody String sendAutoSignatureToEmail(HttpSession session) {
		    String currentemail= (String)session.getAttribute("userName");
		    Integer templatedesignid= (Integer)session.getAttribute("templatedesignid");
		    
		    User user = applicationSettingsService.getpropertyname(currentemail);
		   
		    logger.info("currentemail:"+currentemail);
		    logger.info("propertyfile:"+user.getPropertyname());
		    AutoSignature autoSignature = templateService.geAutoSignatureByEmail(currentemail);
		    autoSignature.setTemplatedesignid(templatedesignid);
		    autoSignature.setPropertyname(user.getPropertyname());
		    
		    String result="fail";
		     if(autoSignature==null) {
	        	 return "no signature";
	         }
		     boolean status=applicationSettingsService.sendAutoSignatureToEmail(currentemail, autoSignature);
		     
		     if(status) {
		    	 return "success";
		     }
		   
		   return result;
	   }
	    
	    
	    
	    @RequestMapping(value="/user/deleteJobFile",method=RequestMethod.POST)
	    public @ResponseBody String deleteFilesInJobs(@RequestParam(value="jobId", required=false) int jobId,
	    		                @RequestParam(value="filename", required=false) String fileName,
	    		                @RequestParam(value="fileNumber", required=false) int fileNumber) {
	    	
	    	logger.info("filename:"+fileName);
	    	logger.info("jobId:"+jobId);
	    	logger.info("fileNumber:"+fileNumber);
	    	DocumentDeleteUtility documentDelete=null;
	    	String result="fail";
	    	boolean deleteFileStatus;
	    	File destinationFile=null;
	 
	    	if(fileNumber==1) {
	    		JobsDocuments getJobDocuments=userService.getJobsDocuments(jobId);
	    		 logger.info("atta1:"+getJobDocuments.getUploaddocument1());
	    	 if(getJobDocuments.getUploaddocument1().equals(fileName)) {
	    		    destinationFile = new File("/opt/hulkats/uploaded/");
			    	documentDelete=new DocumentDeleteUtility();
			    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
			    	logger.info("deleteFileStatus:"+deleteFileStatus);
			    	if(deleteFileStatus) {
			    		applicationSettingsService.updateUploadDocument1(jobId);
			    	}
	    	 }
	           
	    	}else if(fileNumber==2) {
	    		JobsDocuments getJobDocuments=userService.getJobsDocuments(jobId);
	    		 logger.info("atta1:"+getJobDocuments.getUploaddocument2());
	    		 if(getJobDocuments.getUploaddocument2().equals(fileName)) {
		    		    destinationFile =new File("/opt/hulkats/uploaded/");
				    	documentDelete=new DocumentDeleteUtility();
				    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
				    	logger.info("deleteFileStatus:"+deleteFileStatus);
				    	
				    	if(deleteFileStatus) {
				    		applicationSettingsService.updateUploadDocument2(jobId);
				    	}
		    	 }
	    	}else {
	    		return "file name is does not exist";
	    	}
	      
	    	
	   
	         return result;
	    }
	    
	    
	    @RequestMapping(value="/user/deleteApplicantFile",method=RequestMethod.POST)
	    public @ResponseBody String deleteFilesInApplicant(@RequestParam(value="applicantId", required=false) int applicantId,
	    		                @RequestParam(value="filename", required=false) String fileName,
	    		                @RequestParam(value="fileNumber", required=false) int fileNumber) {
	    	
	    	logger.info("filename:"+fileName);
	    	logger.info("applicantId:"+applicantId);
	    	logger.info("fileNumber:"+fileNumber);
	    	DocumentDeleteUtility documentDelete=null;
	    	String result="fail";
	    	boolean deleteFileStatus;
	    	File destinationFile=null;
	 
	    	if(fileNumber==1) {
	    	
	    		Applicants getApplicantDocument=userService.getapplicantdetails(applicantId);
	    		 logger.info("atta1:"+getApplicantDocument.getUplaodcv());
	    	 if(getApplicantDocument.getUplaodcv().equals(fileName)) {
	    		 //   destinationFile = new File(context.getRealPath("/WEB-INF/cvs"));
	    		     destinationFile = new File("/opt/hulkats/cvs/");
			    	documentDelete=new DocumentDeleteUtility();
			    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
			    	logger.info("deleteFileStatus:"+deleteFileStatus);
			    	if(deleteFileStatus) {
			    		applicationSettingsService.updateUploadCv(applicantId);
			    	}
	    	 }
	           
	    	}else if(fileNumber==2) {
	    		Applicants getApplicantDocument=userService.getapplicantdetails(applicantId);
	    		 logger.info("atta1:"+getApplicantDocument.getOtheruploads());
	    		 if(getApplicantDocument.getOtheruploads().equals(fileName)) {
		    		  //  destinationFile = new File(context.getRealPath("/WEB-INF/cvs"));
	    			 destinationFile = new File("/opt/hulkats/cvs/");
				    	documentDelete=new DocumentDeleteUtility();
				    	deleteFileStatus=documentDelete.deleteDocument(destinationFile, fileName);
				    	logger.info("deleteFileStatus:"+deleteFileStatus);
				    	
				    	if(deleteFileStatus) {
				    		applicationSettingsService.updateOtheruploads(applicantId);
				    	}
		    	 }
	    	}else {
	    		return "file name is does not exist";
	    	}
	      
	    	
	   
	         return result;
	    }
	    
}