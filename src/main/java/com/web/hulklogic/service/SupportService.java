package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.ChangeSource;
import com.web.hulklogic.entity.Message;
import com.web.hulklogic.entity.Support;
import com.web.hulklogic.entity.Survey;
import com.web.hulklogic.entity.SurveyFeedback;
import com.web.hulklogic.entity.Target;

public interface SupportService {

	public String saveSupportDetails(Support support);

	public List<Support> getSupportDetails();

	public String messagestore(Message message);

	public String getEmailBySenderMessage(int userid);

	public List<Message> getMessageDetails(String currentEmail);

	public List<Message> getMessagesSingleUser(int from_userid, int to_userid);

	public List<Message> allMessages(int userid);

	public String managerEmailForSenderAlert(String teamname);

	public String recruiterEmailForSenderAlert(int jobid);

	public String recruiterEmailForSendAlert(int app_id);

	public List<AlertDetails> getApplicationAlert(String currentEmail);

	public List<AlertDetails> getApplicantAlert(String currentEamil);

	public List<AlertDetails> getJobAlerts(String currentEmail);

	public List<AlertDetails> getAlerts(int userid, String currentEmail);

	public String msgStatus(int message_id);

	public String alertStatus(int alert_id);

	public String getExistTemaname(int userId);

	public Target getExistTargets(int userId);
	
	public ChangeSource changeSource(int sourceid);

	public AutoSignature getAutoSignatureDetails(int userId);

	public int totalMessages(int userid);

	public int totalAlerts(String email);

	public int surveyDetails(SurveyFeedback survey);

	public int saveSurvey(Survey survey);

	public List<Survey> getAllSurvey();

	public boolean getbizcardemailCheck(String email);

	public boolean getbizcardContactnoCheck(String contactno);

	public String saveCard(CardDetails carddetails);

}
