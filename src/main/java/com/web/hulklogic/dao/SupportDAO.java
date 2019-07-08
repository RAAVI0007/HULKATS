package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.ChangeSource;
import com.web.hulklogic.entity.Message;
import com.web.hulklogic.entity.Support;
import com.web.hulklogic.entity.Target;

public interface SupportDAO {

	public int saveSupportDatails(Support support);

	public List<Support> getSupportDetails();

	public int messagestore(Message message);

	public String getEmailBySenderMessage(int userid);

	public List<Message> getmessageDetails(String currentEmail);

	public List<Message> getMessagesSingleUser(int from_userid, int to_userid);

	public List<Message> allMessages(int userid);

	public int saveAlertDetails(AlertDetails alertDetails);

	public String managerEmailForSenderAlert(String teamname);

	public String recruiterEmailForSenderAlert(int jobid);

	public String recruiterEmailForSendAlert(int app_id);

	public List<AlertDetails> getApplicationAlert(String currentEmail);

	public List<AlertDetails> getApplicantAlert(String currentEamil);

	public List<AlertDetails> getJobAlerts(String currentEmail);

	public List<AlertDetails> getAlerts(int userid, String currentEmail);

	public int msgStatus(int message_id);

	public int alertStatus(int alert_id);

	public String getExistTemaname(int userId);

	public Target getExistTargets(int userId);

	public AutoSignature getAutoSignatureDetails(int userId);

	public int totalMessages(int userid);

	public int totalAlerts(String email);

	public int updateSingleUserMessage(int from_userid, int to_userid);

	public int updateSigleUserAlerts(String email);
	
	public ChangeSource changeSource(int sourceid);

}
