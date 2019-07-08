package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.JobTitleDAO;
import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.service.JobTitleService;

@Service
public class JobTitleServiceImpl implements JobTitleService {

	private static Logger logger = Logger.getLogger(JobTitleServiceImpl.class);
	@Autowired
	private JobTitleDAO jobTitleDao;
	@Autowired
	private SupportDAO supportDAO;

	@Override
	public List<JobTitle> getJobTitle(String teamname) {
		return jobTitleDao.getJobTitle(teamname);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String createApplicantDirectly(JobTitle jobtitle) {
		int saveInApplicantionStart = jobTitleDao.createApplicantDirectly(jobtitle);
		logger.info(saveInApplicantionStart);
		int lastappId = jobTitleDao.getLastApp_Id();
		logger.info("lastappId :" + lastappId);
		jobtitle.setAppId(lastappId);
		AlertDetails alertDetails = getAlertDetails(jobtitle);
		int saveAlterDetails = supportDAO.saveAlertDetails(alertDetails);
		logger.info(saveAlterDetails);
		int saveInApplicantion = jobTitleDao.saveJobApplicantIdInApplication(jobtitle);
		logger.info(saveInApplicantion);
		int saveInApplicationHistory = jobTitleDao.insertAppIdInAppHistory(lastappId, jobtitle.getReferncedemail());
		logger.info(saveInApplicationHistory);
		if (saveInApplicantionStart == 0 || saveInApplicantion == 0 || saveInApplicationHistory == 0
				|| saveAlterDetails == 0) {
			return "fail";
		}
		return "success";
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String createApplicantDirectly1(JobTitle jobtitle) {
		int saveInApplicantionStart = jobTitleDao.createApplicantDirectly1(jobtitle);
		logger.info(saveInApplicantionStart);
		int lastappId = jobTitleDao.getLastApp_Id();
		logger.info("lastappId :" + lastappId);
		jobtitle.setAppId(lastappId);
		
		if (saveInApplicantionStart == 0 ) {
			return "fail";
		}
		return "success";
	}

	@Override
	public String saveAddComment(String addComment, int app_id, String email) {
		int result = jobTitleDao.saveAddComment(addComment, app_id, email);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String addCommenthistory(String addComment, int applicantid, String email) {
		int result = jobTitleDao.addCommentHistory(addComment, applicantid, email);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String updateAndSaveApplicant(JobTitle jobtitle) {
		int updateStatus = 0;
		int saveApplicantDetails = 0;
		int saveAppCreationInAppHistory = 0;
		saveApplicantDetails = jobTitleDao.createApplicantDirectly(jobtitle);
		int lastappId = jobTitleDao.getLastApp_Id();
		logger.info("get last appId:" + lastappId);
		jobtitle.setAppId(lastappId);
		AlertDetails alertDetails = getAlertDetails(jobtitle);
		int saveAlterDetails = supportDAO.saveAlertDetails(alertDetails);
		updateStatus = jobTitleDao.updateStatusApplicant(jobtitle.getApplicationId());
		saveAppCreationInAppHistory = jobTitleDao.insertAppIdInAppHistory(lastappId, jobtitle.getReferncedemail());
		if (updateStatus == 0 || saveApplicantDetails == 0 || saveAppCreationInAppHistory == 0
				|| saveAlterDetails == 0) {
			return "fail";
		}
		return "success";
	}

	@Override
	public JobsDocuments getJobAndApplicantDocuments(int app_id) {
		return jobTitleDao.getJobAndApplicantDocuments(app_id);
	}

	@Override
	public List<JobTitle> getAppCommnetByAppId(int appId) {
		return jobTitleDao.getAppCommnetByAppId(appId);
	}

	@Override
	public String saveTarget(Target target) {
		int result = 0;
		if (target.getPeriodic().equals("MONTH")) {

			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.saveTargetMonthly(target);

		} else if (target.getPeriodic().equals("YEAR")) {

			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.saveTargetYearly(target);

		} else {
			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.saveTargetQuarterly(target);
		}

		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public ApplicantInfo getApplicantInfoByAppId(int appId) {
		return jobTitleDao.getApplicantInfoByAppId(appId);
	}
	
	@Override
	public ApplicantInfo getProfileInfoByAppId(int appId) {
		return jobTitleDao.getProfileInfoByAppId(appId);
	}

	@Override
	public String saveAutoSignature(AutoSignature autoSignature) {
		int count = jobTitleDao.saveAutoSignature(autoSignature);
		if (count > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public CreateTemplate getTemplateName(String templateName) {

		return jobTitleDao.getTemplateName(templateName);
	}

	@Override
	public List<CreateTemplate> getAllTemplateNames(String organization) {
		return jobTitleDao.getAllTemplateNames(organization);
	}

	public AlertDetails getAlertDetails(JobTitle jobtitle) {
		logger.info("alert details service methode :");
		AlertDetails alertDetails = new AlertDetails();
		alertDetails.setCreated_alert(jobtitle.getEmail());
		alertDetails.setUserid(jobtitle.getUserid());
		alertDetails.setAlert_type("Application Alert");
		alertDetails.setRef_app_id(jobtitle.getAppId());
		alertDetails.setSender_email(jobtitle.getSender_email());
		return alertDetails;

	}

	@Override
	public Target getTargetsDetails(String teamname) {
		return jobTitleDao.getTargetsDetails(teamname);
	}

	@Override
	public String updateTargetsDetails(Target target) {
		int result = 0;
		if (target.getPeriodic().equals("MONTH")) {

			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.updateTargetsMonthly(target);

		} else if (target.getPeriodic().equals("YEAR")) {

			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.updateTargetsYearly(target);

		} else {
			logger.info("Periodic :" + target.getPeriodic());

			result = jobTitleDao.updateTargetsQuarterly(target);
		}
		logger.info("result :" + result);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}
}
