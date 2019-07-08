package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.ApplicantDAO;
import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.ApplicantDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.Sharejobs;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.utility.BulkEmail;

@Service
public class ApplicantServiceImpl implements ApplicantService  {
	
	private static Logger logger = Logger.getLogger(ApplicantServiceImpl.class);
	
	@Autowired
	private ApplicantDAO applicantsdao;
	@Autowired
	 private SupportDAO supportDAO;
	@Override
	public List<CreateTemplate> autoCompleteTemplate(String templatename) {
		return applicantsdao.autoCompleteTemplate(templatename);
	}
	@Override
	 public List<CreateTemplate> getSubjectBody(String templatename) {
	  return applicantsdao.getSubjectBody(templatename);
	 }
	
	@Override
	 public boolean sendBulkEmailToApplicant(BulkEmailSend bulkEmailSend, AutoSignature autoSignature) {
	  logger.info("Sending BulkEmailSend mail to:");
	  logger.info(bulkEmailSend.getTemplatedesignid());
	  logger.info(bulkEmailSend.getEmails());
	  BulkEmail bulkemail=new BulkEmail();
	  logger.info(bulkEmailSend.getTemplatedesignid());
	  boolean status= bulkemail.sendMail(bulkEmailSend,autoSignature);
	  if(status) {
		   return true;
		}
	  logger.info("status Service to:"+status);
	  return false;
     }

	@Override
	 public int updatepassword(int userid, String password) {
	  return applicantsdao.updatepassword(userid, password);
	 }
	
	@Override
	 public int createpassword(int userid, String password) {
	  return applicantsdao.createpassword(userid, password);
	 }
	
	 @Override
	  @Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=SQLException.class)
	  public boolean updateAndInsertStatus(ApplicantInfo applicant) {
	   AlertDetails alertDetails=new AlertDetails();
	   alertDetails.setUserid(applicant.getUserid());
	   alertDetails.setRef_app_id(applicant.getApp_id());
	   alertDetails.setAlert_type("Application Alert");  
	   alertDetails.setSender_email(applicant.getSenderEmail());
	   int insertstatus=applicantsdao.insertStatus(applicant);
	   int updatestatus=applicantsdao.updateStatus(applicant.getApp_id(), applicant.getStatus(),applicant.getEmail());
	   int saveAlterDetails=supportDAO.saveAlertDetails(alertDetails); 
	   if(insertstatus==0 || updatestatus==0 || saveAlterDetails==0 ){
	    return false;
	   }
	   return true;
	  }
	
	@Override
	 public List<JobTitle> getStatusByApplicationid(int applicationid) {
	  return applicantsdao.getStatusByApplicationid(applicationid);
	 }
	@Override
	public int updateCvsDocument(String documentname, int applicantid) {
		return applicantsdao.updateCvsDocument(documentname, applicantid);
	}
	@Override
	public int updateCvsDocument1(String documentname, int applicantid) {
		return applicantsdao.updateCvsDocument1(documentname, applicantid);
	}
	@Override
	 public int updateDocument1(String documentname, int applicantid) {
	  
	  return applicantsdao.updateDocument1(documentname, applicantid);
	 }
	 @Override
	 public int updateDocument2(String documentname, int applicantid) {
		
	  return applicantsdao.updateDocument2(documentname, applicantid);
	 }
	 @Override
	 public int updateDocument3(String documentname, int applicantid) {
	  
	  return applicantsdao.updateDocument3(documentname, applicantid);
	 }
	 @Override
	 public int updateDocuemnt4(String documentname, int applicantid) {
	  return applicantsdao.updateDocuemnt4(documentname, applicantid);
	 }
	 @Override
	 public int updateDocument5(String documentname, int applicantid) {
	  
	  return applicantsdao.updateDocument5(documentname, applicantid);
	 }
	@Override
	public boolean getappemailCheck(String email) {
		 int count=applicantsdao.getappemailCheck(email);
		  if(count>0){
		   return true;
		  }
		  return false;
	}
	
	@Override
	public boolean getappemailpassCheck(String email, String password){
		
		 int count=applicantsdao.getappemailpassCheck(email,password);
		  if(count>0){
		   return true;
		  }
		  return false;
		
		
	}
	
	
	@Override
	public boolean getappmobileCheck(String contactno) {
		  int count=applicantsdao.getappmobileCheck(contactno);
		  if(count>0){
		   return true;
		  }
		  return false;
		 }
	
	
	@Override
	public int getapplicantid(String email) {
	
		return applicantsdao.getapplicantid(email);
	}
	@Override
	public boolean appchecking(int jobid, int applicantid) {
		int count=applicantsdao.appchecking(jobid,applicantid);
		  if(count>0){
		   return true;
		  }
		  return false;
	}
	
	@Override
	public boolean appcheckinghold(int jobid, int applicantid) {
		int count=applicantsdao.appcheckinghold(jobid,applicantid);
		  if(count>0){
		   return true;
		  }
		  return false;
	}
	
	@Override
	public ApplicantDetails profile(int applicantdetails_id) {
		return applicantsdao.profile(applicantdetails_id);
	}
	@Override
	public boolean shareemail(Sharejobs sharejob) {
		int count=applicantsdao.insertshareemail(sharejob);
		  if(count>0){
		   return true;
		  }
		  return false;
	}
}
