package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.EditDetailsDAO;
import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.dao.Impl.EditDetailsDAOIml;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.EditDetailsService;

@Service
public class EditDetailsServiceImpl  implements EditDetailsService{
	private static Logger logger = Logger.getLogger(EditDetailsServiceImpl.class);
     
	@Autowired
	private EditDetailsDAO editDetailsDao;
	@Autowired
	 private SupportDAO supportDAO;
	@Override
	public boolean updateUserDetails(User user) {
		 int result=editDetailsDao.updateUserDetails(user);
		 if(result>0){
			 return true;
		 }
		return false;
	}
	
	
	@Override
	public int deleteUser(int userid) {
		return editDetailsDao.deleteUser(userid);
	}
	
	@Override
	public int deleteJob(int jobid) {
		return editDetailsDao.deleteJob(jobid);
	}

	@Override
	public User getTeamIdAndMgId(String email) {
		return editDetailsDao.getTeamIdAndMgId(email);
	}
	@Override
	 public List<User> getAllManagers(int roleid,String organization) {
	  return editDetailsDao.getAllManagers(roleid,organization);
	 }

	 @Override
	 public List<User> getAllRecruiters(int roleid, String teamname,String organization) {
		 
	  return editDetailsDao.getAllRecruiters(roleid, teamname,organization);
	 }

	 @Override
	 public List<User> getAllNARecruiters(int roleid,String organization) {
	  return editDetailsDao.getAllNARecruiters(roleid,organization);
	 }
	 
	 @Override
	  @Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=SQLException.class)
	  public String changeJobStatus(AlertDetails alertDetails) {
	   int saveAlterDetails=0; 
	   int changeJobStatus=0;
	         saveAlterDetails=supportDAO.saveAlertDetails(alertDetails);  
	      changeJobStatus=editDetailsDao.changeJobStatus(alertDetails.getRef_jobid(), alertDetails.getJobstatus());
	      if(saveAlterDetails==0 || changeJobStatus==0){
	       return "fail";
	      }
	      return "success";
	     }
	 
	 @Override
	 public int getLastJobId() {
	  
	  return editDetailsDao.getLastJobId();
	 }
	 
	 @Override
	 public String updateJob(CreateJob createJob) {
		 
		 logger.info("fileType1 :"+createJob.getFiletype1());
		 logger.info("fileType2 :"+createJob.getFiletype2());
		 
		 int result=0;
		 
		 if(createJob.getFiletype1()!=null && createJob.getFiletype2()!=null ){
			 
			 result=editDetailsDao.updateJob(createJob);
			 
		 }else if(createJob.getFiletype1()==null && createJob.getFiletype2()==null){
			 result=editDetailsDao.updateJobWithoutDocs(createJob);
			 
		 }else if(createJob.getFiletype1()!=null){
			 
			 result=editDetailsDao.updateJobUpload1(createJob);
			 
		 }else if(createJob.getFiletype2()!=null){
			 
			 result=editDetailsDao.updateJobUpload2(createJob);
		 }
	
	  if(result>0){
	   return "success";
	  }
	  return  "fail";
	 }
	 
	 @Override
	 public String updateApplicant(Applicants applicant) {
		 logger.info("fileType1 :"+applicant.getFiletype1());
		 logger.info("fileType2 :"+applicant.getFiletype2());
		 int result=0;
		 
		 if(applicant.getFiletype1()!=null && applicant.getFiletype2()!=null){
			 
			 result =editDetailsDao.updateApplicant(applicant);
			 
		 }else if(applicant.getFiletype1()==null && applicant.getFiletype2()==null){
			 
			 result =editDetailsDao.updateApplicantWithoutDocs(applicant);
			 
		 }else if(applicant.getFiletype1()!=null){
			 result =editDetailsDao.updateApplicantUplaodcv(applicant);
			 
		 }else if(applicant.getFiletype2()!=null){
			 
			 result =editDetailsDao.updateApplicantotherUpload(applicant);
		 }
		 	 
	  if(result>0){
	   return "success";
	  }
	  return "fail";
	 }

	 @Override
	 public int getLastApplicantId() {
	  return editDetailsDao.getLastApplicantId();
	 }

	@Override
	public JobsDocuments getAllDocument(int jobid, int applicantid) {
		
		return editDetailsDao.getAllDocument(jobid, applicantid);
	}

	@Override
	public JobsDocuments getApplicantDoc(int applicantid) {
		
		return editDetailsDao.getApplicantDoc(applicantid);
	}

	@Override
	public JobsDocuments getJobDoc(int jobid) {
		
		return editDetailsDao.getJobDoc(jobid);
	}

	@Override
	public int updateLogout(int userId) {
		logger.info("update logout :"+userId);
		return editDetailsDao.updateLogout(userId);
	}
	
	 @Override
	 public int saveLogoutInfo(int userid) {
	      return editDetailsDao.saveLogoutInfo(userid);
	 }
	 
	 
	 @Override
	 public int saveCurrentLoginInfo(int userid,String teamname) {
	  return editDetailsDao.saveCurrentLoginInfo(userid,teamname);
	 }


	@Override
	public int checkLoginUser(int userId) {
	
		
		return editDetailsDao.checkLoginUser(userId);
	}


	
	@Override
	public int insertLoginUser(int userId, String sessionId1, String teamname) {
	
		return editDetailsDao.insertLoginUser(userId, sessionId1, teamname);
	}


	@Override
	public User getSessions(int userId) {
		
		return editDetailsDao.getSessions(userId);
	}


	@Override
	public int insertSession1(int userId, String sessionId1) {
		return editDetailsDao.insertSession1(userId, sessionId1);
	}


	@Override
	public int insertSession2(String sessionId2,int userId) {
		return editDetailsDao.insertSession2(sessionId2, userId);
	}


	@Override
	public int logoutSession1(int userId) {
	
		return editDetailsDao.logoutSession1(userId);
	}


	@Override
	public int logoutSession2(int userId) {
		return editDetailsDao.logoutSession2(userId);
	}


	@Override
	public int idleStateSession1(int userId) {
		
		return editDetailsDao.idleStateSession1(userId);
	}


	@Override
	public int idleStateSessioon2(int userId) {
		
		return editDetailsDao.idleStateSessioon2(userId);
	}


	@Override
	public int deleteSessions(int userId) {
		return editDetailsDao.deleteSessions(userId);
	}


	
}
