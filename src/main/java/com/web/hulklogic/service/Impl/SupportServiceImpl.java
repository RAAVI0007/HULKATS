package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.dao.SurveyDao;
import com.web.hulklogic.dao.Impl.SupportDAOImpl;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.ChangeSource;
import com.web.hulklogic.entity.Message;
import com.web.hulklogic.entity.Support;
import com.web.hulklogic.entity.Survey;
import com.web.hulklogic.entity.SurveyFeedback;
import com.web.hulklogic.entity.Target;
import com.web.hulklogic.service.SupportService;

@Service
public class SupportServiceImpl implements SupportService {
	private static Logger logger = Logger.getLogger(SupportServiceImpl.class);

	@Autowired
	private SupportDAO supportDAO;
	@Autowired
	private SurveyDao surveydao;

	@Override
	public String saveSupportDetails(Support support) {
		int result = supportDAO.saveSupportDatails(support);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public List<Support> getSupportDetails() {
		return supportDAO.getSupportDetails();
	}

	@Override
	public String messagestore(Message message) {
		int result = supportDAO.messagestore(message);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String getEmailBySenderMessage(int userid) {

		return supportDAO.getEmailBySenderMessage(userid);
	}

	@Override
	public List<Message> getMessageDetails(String currentEmail) {

		return supportDAO.getmessageDetails(currentEmail);
	}

	@Override
	public List<Message> getMessagesSingleUser(int from_userid, int to_userid) {
		List<Message> getSingleUserMessage = null;

		getSingleUserMessage = supportDAO.getMessagesSingleUser(from_userid, to_userid);

		if (getSingleUserMessage != null) {
			supportDAO.updateSingleUserMessage(from_userid, to_userid);
		}

		return getSingleUserMessage;
	}

	@Override
	public List<Message> allMessages(int userid) {
		return supportDAO.allMessages(userid);
	}

	@Override
	public String managerEmailForSenderAlert(String teamname) {
		return supportDAO.managerEmailForSenderAlert(teamname);
	}

	@Override
	public String recruiterEmailForSenderAlert(int jobid) {
		return supportDAO.recruiterEmailForSenderAlert(jobid);
	}

	@Override
	public String recruiterEmailForSendAlert(int app_id) {

		return supportDAO.recruiterEmailForSendAlert(app_id);
	}

	@Override
	public List<AlertDetails> getApplicationAlert(String currentEmail) {

		return supportDAO.getApplicationAlert(currentEmail);
	}

	@Override
	public List<AlertDetails> getApplicantAlert(String currentEmail) {

		return supportDAO.getApplicantAlert(currentEmail);
	}

	@Override
	public List<AlertDetails> getJobAlerts(String currentEmail) {

		return supportDAO.getJobAlerts(currentEmail);
	}

	@Override
	public List<AlertDetails> getAlerts(int userid, String currentEmail) {

		List<AlertDetails> getAlertDetails = null;
		getAlertDetails = supportDAO.getAlerts(userid, currentEmail);

		if (getAlertDetails != null) {
			int reultstatu = supportDAO.updateSigleUserAlerts(currentEmail);
			logger.info("reultstatu:" + reultstatu);
		}

		return getAlertDetails;
	}

	@Override
	public String msgStatus(int message_id) {
		int result = supportDAO.msgStatus(message_id);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String alertStatus(int alert_id) {
		int result = supportDAO.alertStatus(alert_id);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	@Override
	public String getExistTemaname(int userId) {
		return supportDAO.getExistTemaname(userId);
	}

	@Override
	public Target getExistTargets(int userId) {
		return supportDAO.getExistTargets(userId);
	}

	@Override
	public AutoSignature getAutoSignatureDetails(int userId) {
		logger.info("userId:" + userId);

		return supportDAO.getAutoSignatureDetails(userId);
	}

	@Override
	public int totalMessages(int userid) {
		return supportDAO.totalMessages(userid);
	}

	@Override
	public int totalAlerts(String email) {

		return supportDAO.totalAlerts(email);
	}

	@Override
	public int surveyDetails(SurveyFeedback survey) {
		// TODO Auto-generated method stub
		return surveydao.surveyDetails(survey);
	}

	@Override
	public int saveSurvey(Survey survey) {
		return surveydao.saveSurvey(survey);
	}

	@Override
	public List<Survey> getAllSurvey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getbizcardemailCheck(String email) {
		int count = surveydao.getbizcardemailCheck(email);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getbizcardContactnoCheck(String contactno) {
		int count = surveydao.getbizcardContactnoCheck(contactno);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public String saveCard(CardDetails carddetails) {
		// TODO Auto-generated method stub
		int savecarddetails = surveydao.saveCard(carddetails);
		if (savecarddetails > 0) {
			int cardstatus = surveydao.cardstatus();
			if (cardstatus > 0) {

				return "success";
			}
		}
		return "fail";
	}

	@Override
	public ChangeSource changeSource(int sourceid) {
		
		return supportDAO.changeSource(sourceid);
	}

}
