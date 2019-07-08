package com.web.hulklogic.service.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.hulklogic.dao.ApplicationSettingsDAO;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicationSettingsService;
import com.web.hulklogic.utility.AutoSignatureEmailUtility;
@Service
public class ApplicationSettingsServiceImpl implements ApplicationSettingsService{
	
	private static Logger logger = Logger.getLogger(ApplicationSettingsServiceImpl.class);	

	 @Autowired
	 private ApplicationSettingsDAO applicationSettingsDao;
	 
	@Override
	public List<CreateJob> getCountries() {
		
		return applicationSettingsDao.getCountries();
	}
	
	@Override
	public List<CreateJob> getCompanies() {
		
		return applicationSettingsDao.getCompanies();
	}
	
	@Override
	public List<CreateJob> getperCompanies(String company) {
		logger.info(company);
		return applicationSettingsDao.getperCompanies(company);
	}

	@Override
	public List<CreateJob> getAllStates(int country_id) {
		  return applicationSettingsDao.getAllStates(country_id);
	}

	@Override
	public List<CreateJob> getAllCities(int state_id) {
		
		return applicationSettingsDao.getAllCities(state_id);
	}

	@Override
	public List<CreateJob> getCountriesByPhoneCode(int phoneCode) {
		
		return applicationSettingsDao.getCountriesByPhoneCode(phoneCode);
	}

	@Override
	public boolean checkContactNoInProfile(String contactNo, int userId) {
		int count=applicationSettingsDao.checkContactNoInProfile(contactNo,  userId);
		  if(count>0){
		   return true;
		  }
		  return false;

}

	@Override
	public int updateAttachements1(int templateId) {
		// TODO Auto-generated method stub
		return applicationSettingsDao.updateAttachements1(templateId);
	}

	@Override
	public int updateAttachements2(int templateId) {
		// TODO Auto-generated method stub
		return applicationSettingsDao.updateAttachements2(templateId);
	}

	@Override
	public boolean sendAutoSignatureToEmail(String currentEmail, AutoSignature autoSignature) {
		AutoSignatureEmailUtility autoSignatureToEmail=new AutoSignatureEmailUtility();
		boolean status= autoSignatureToEmail.sendMail(currentEmail,autoSignature);
		  if(status) {
			   return true;
			}
		  logger.info("status Service to:"+status);
		  return false;
	}

	@Override
	public int updateUploadDocument1(int jobId) {
		return applicationSettingsDao.updateUploadDocument1(jobId);
	}

	@Override
	public int updateUploadDocument2(int jobId) {
		return applicationSettingsDao.updateUploadDocument2(jobId);
	}

	@Override
	public int updateUploadCv(int applicantId) {
		// TODO Auto-generated method stub
		return applicationSettingsDao.updateUploadCv(applicantId);
	}

	@Override
	public int updateOtheruploads(int applicantId) {
		// TODO Auto-generated method stub
		return applicationSettingsDao.updateOtheruploads(applicantId);
	}

	@Override
	public User getpropertyname(String email) {
		
		return applicationSettingsDao.getpropertyname(email);
	}
}