package com.web.hulklogic.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.GetDetailsDAO;
import com.web.hulklogic.dao.JobDAO;
import com.web.hulklogic.dao.UserDAO;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.ParserText;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.GetDetailsService;

@Service
public class GetDetailsServiceImpl implements GetDetailsService{
	@Autowired
	private GetDetailsDAO getDetailsDao;
	@Autowired
	private JobDAO jobdao;
	@Override
	public CreateJob getJobDetailsByJobid(int jobid) {
	return getDetailsDao.getJobDetailsByJobid(jobid);
	}

	@Override
	public Applicants getApplicantsByApplicatId(int applicantid) {
		return getDetailsDao.getApplicantsByApplicatId(applicantid);
	}

	@Override
	public String updatePassword(String email, String oldPassword,String newPassword) {
		if(jobdao.selectPassword(email, oldPassword)>0){
			if(jobdao.updatePassword(email, newPassword)>0){
				  return "Password updated";
				}else{
					return "Password updated fail";
					}
			}
		else{
			return "Password doesn't match";
		}
		
	}

	@Override
	public int enableUser(int userid) {
		return getDetailsDao.enableUser(userid);
	}
	
	@Override
	public int enableApplicant(int applicantid) {
		return getDetailsDao.enableApplicant(applicantid);
	}

	@Override
	public User getUserByUserId(int userid) {
		return getDetailsDao.getUserByUserId(userid);
	}

	@Override
	public String saveOtp(User user) {
		int count= getDetailsDao.saveOtp(user);
		if(count>0){
			return "success";
		}
		return "fail";
	}
	@Override
	  public List<ParserText> getParserTextByJobId(int jobid) {
	    return getDetailsDao.getParserTextByJobId(jobid);
	  }

	  @Override
	  public List<ParserText> getParserTextByApplicantId(int applicantid) {
	   return getDetailsDao.getParserTextByApplicantId(applicantid);
	  }
	  
	 @Override
	 public List<JobTitle> getJobTitleOfAutoComplete(String jobtitle) {
	  return  getDetailsDao.getJobTitleOfAutoComplete(jobtitle);
	 }

	 @Override
	 public List<JobTitle> getLocationleOfAutoComplete(String location) {
	  return getDetailsDao.getLocationleOfAutoComplete(location);
	 }
	
	 @Override
	 public List<GetTokens> getJobAndApplicantTokens(int jobid) { 
	  return getDetailsDao.getJobAndApplicantTokens(jobid);
	 }
	 @Override
	 public boolean checkJobAssignToRecruiter(int jobid) {
	  int result=getDetailsDao.checkJobAssignToRecruiter(jobid);
	  if(result>0){
	   
	   return true;
	  }
	  
	  return false;
	 }
	 
	 @Override
	 public List getJobKeywords(int jobid) {
	  return jobdao.getJobKeywords(jobid);
	 }

	@Override
	public List<GetEmployee> getemployeedetails(String designation, String organization) {
		return getDetailsDao.getEmployeedetails(designation, organization);
	}

}
