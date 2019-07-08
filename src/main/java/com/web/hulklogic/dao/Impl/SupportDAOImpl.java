package com.web.hulklogic.dao.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.web.hulklogic.dao.SupportDAO;
import com.web.hulklogic.entity.AlertDetails;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.ChangeSource;
import com.web.hulklogic.entity.Message;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.Support;
import com.web.hulklogic.entity.Target;

@Repository
public class SupportDAOImpl implements SupportDAO {
	private static Logger logger = Logger.getLogger(SupportDAOImpl.class);

	private static final String INSERT_SUPPORT_DETAILS = "insert into support(organization,email,comment,problemurl,prbimg,prbimg1,status) "
			+ "values(:organization,:email,:comment,:problemurl,:prbimg,:prbimg1,:status)";
	private static final String INSERT_MESSAGE = "insert into messages(message, userid, from_email,to_email,to_userid) "
			+ "values(:message,:userid,:from_email,:to_email,:to_userid)";
	private static final String GET_EMAIL_BY_SENDER_MESSAGE = "select email from user where userid=?";
	private static final String GET_MESSAGE_DETAILS = "select m.message_id,m.status,m.message,m.created_at,concat(u.firstname,' ',u.lastname) as Name,m.userid from messages m inner join user u on m.userid = u.userid where m.to_email=:currentEmail order by message_id desc LIMIT 3";
	private static final String GET_MESSGAE_SINGLE_USER = "select distinct m.message_id, m.message,m.created_at,concat(u.firstname,' ',u.lastname) as Name from messages m inner join user u on m.userid = u.userid where m.userid in (:from_userid,:to_userid) and m.to_userid in(:from_userid,:to_userid) order by m.message_id";
	private static final String GET_ALL_MESSGAE = "select distinct m.message_id,m.message,m.created_at,concat(u.firstname,' ',u.lastname) as Name from messages m inner join user u on m.userid = u.userid where m.userid =:userid or m.to_userid=:userid";
	private static final String INSERT_ALERT_DETAILS = "insert into alerts(alert_type,created_alert,userid,ref_jobid,ref_app_id,ref_applicantid,sender_email)"
			+ "values(:alert_type,:created_alert,:userid,:ref_jobid,:ref_app_id,:ref_applicantid,:sender_email)";
	private static final String GET_MANAGER_EMAIL_FOR_ALERT = "select email from user where teamname=? and roleid=1";
	private static final String GET_RECRUITER_EMAIL_FOR_ALERT = "select recuiteremail from jobassign where jobid=?";
	private static final String GET_RECRUITER_EMAIL_FOR_ALERT_APP = "select j.recuiteremail from applicationstart a inner join  jobassign j on a.jobid=j.jobid where a.app_id=?";

	private static final String GET_JOB_ALERT_DETAILS = "select concat(u.firstname,' ',u.lastname) as Name,a.status,a.alert_id, a.alert_type,a.created_at,a.ref_jobid from alerts a inner join user u on u.userid = a.userid where a.sender_email=? and a.alert_type='Job Alert' order by a.alert_id desc limit 1";
	private static final String GET_APPLICANTION_ALERT_DETAILS = "select concat(u.firstname,' ',u.lastname) as Name,a.status,a.alert_id, a.alert_type,a.created_at, a.ref_app_id from alerts a inner join user u on u.userid = a.userid where a.sender_email = ? and a.alert_type='Application Alert' order by a.alert_id desc limit 1";
	private static final String GET_APPLICANT_ALERT_DETAILS = "select concat(u.firstname,' ',u.lastname) as Name,a.status,a.alert_id, a.alert_type,a.created_at,a.ref_applicantid from alerts a inner join user u on u.userid = a.userid where a.created_alert != ? and a.alert_type='Applicant Alert' order by a.alert_id desc limit 1";
	private static final String GET_ALERTS = "select a.status, a.alert_type,a.alert_id,a.userid,a.created_at,a.ref_app_id,a.ref_applicantid,a.ref_jobid,a.sender_email,concat(u.firstname,'',u.lastname) as Name from alerts a inner join user u on a.userid = u.userid where a.userid=? or sender_email=?";
	private static final String UPDATE_MESSAGE_STATUS = "update messages set status = 1 where message_id=?";
	private static final String UPDATE_ALERT_STATUS = "update alerts set status=1 where alert_id=?";
	private static final String GET_EXIST_TEAMNAME = "select teamname1 from user where userid=?";
	private static final String GET_EXIST_TARGET = "select *from target where userid=?";
	private static final String GET_AUTO_SIGNATURE_DETAILS = "select autosin_companyname,autosin_designation,autosin_companyurl,sightml,address from user where userid=?";
	private static final String GET_TOTAL_MESSAGE = "select count(*) from messages where to_userid=? and status=0";
	private static final String GET_TOTAL_ALERTS = "select count(*) from alerts where sender_email=? and status=0";
	private static final String UPDATE_SINGLER_USER_MESSAGE_STATUS = "update messages set status=1 where  userid=? and to_userid=?";
	private static final String UPDATE_SINGLER_USER_ALERTS_STATUS = "update alerts set status=1 where sender_email=?";
	private static final String GET_SOURCEDATA = "select * from applicationsource where id =? ";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Override
	public int saveSupportDatails(Support support) {
		return namedParameterJdbcTemplate.update(INSERT_SUPPORT_DETAILS, getSqlParameterBySupport(support));
	}

	private SqlParameterSource getSqlParameterBySupport(Support support) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("email", support.getEmail());
		paramSource.addValue("comment", support.getComment());
		paramSource.addValue("organization", support.getOrganization());
		paramSource.addValue("prbimg", support.getPrbimg());
		paramSource.addValue("prbimg1", support.getPrbimg1());
		paramSource.addValue("problemurl", support.getProblemurl());
		paramSource.addValue("status", "processing");
		return paramSource;
	}

	@Override
	public List<Support> getSupportDetails() {

		return null;
	}

	@Override
	public int messagestore(Message message) {
		return namedParameterJdbcTemplate.update(INSERT_MESSAGE, getSqlParameterByMessage(message));
	}

	private SqlParameterSource getSqlParameterByMessage(Message message) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("message", message.getMessage());
		paramSource.addValue("userid", message.getUserid());
		paramSource.addValue("from_email", message.getFrom_email());
		paramSource.addValue("to_email", message.getTo_email());
		paramSource.addValue("to_userid", message.getTo_userid());
		return paramSource;
	}

	@Override
	public String getEmailBySenderMessage(int userid) {
		String name = null;
		try {
			name = (String) getJdbcTemplate.queryForObject(GET_EMAIL_BY_SENDER_MESSAGE, new Object[] { userid },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return name;
	}

	@Override
	public List<Message> getmessageDetails(String currentEmail) {
		logger.info("currentEmail :" + currentEmail);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("currentEmail", currentEmail);
		List<Message> getMessages = namedParameterJdbcTemplate.query(GET_MESSAGE_DETAILS, pss,
				new BeanPropertyRowMapper(Message.class));
		return getMessages;
	}

	@Override
	public List<Message> getMessagesSingleUser(int from_userid, int to_userid) {
		logger.info("from_userid :" + from_userid);
		logger.info("to_userid :" + to_userid);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("from_userid", from_userid);
		pss.addValue("to_userid", to_userid);
		List<Message> getMessages = namedParameterJdbcTemplate.query(GET_MESSGAE_SINGLE_USER, pss,
				new BeanPropertyRowMapper(Message.class));
		return getMessages;
	}

	@Override
	public List<Message> allMessages(int userid) {
		logger.info("to_userid :" + userid);
		MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("userid", userid);
		List<Message> getMessages = namedParameterJdbcTemplate.query(GET_ALL_MESSGAE, pss,
				new BeanPropertyRowMapper(Message.class));
		return getMessages;
	}

	@Override
	public int saveAlertDetails(AlertDetails alertDetails) {
		return namedParameterJdbcTemplate.update(INSERT_ALERT_DETAILS, getSqlParameterByAlert(alertDetails));
	}

	private SqlParameterSource getSqlParameterByAlert(AlertDetails alertDetails) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("created_alert", alertDetails.getCreated_alert());
		logger.info("check userid :" + alertDetails.getUserid());
		paramSource.addValue("userid", alertDetails.getUserid());
		paramSource.addValue("ref_jobid", alertDetails.getRef_jobid());
		paramSource.addValue("ref_app_id", alertDetails.getRef_app_id());
		paramSource.addValue("ref_applicantid", alertDetails.getRef_applicantid());
		paramSource.addValue("alert_type", alertDetails.getAlert_type());
		paramSource.addValue("sender_email", alertDetails.getSender_email());
		return paramSource;
	}

	@Override
	public String managerEmailForSenderAlert(String teamname) {
		logger.info("teamname:" + teamname);
		String managerEmail = null;
		try {
			managerEmail = (String) getJdbcTemplate.queryForObject(GET_MANAGER_EMAIL_FOR_ALERT,
					new Object[] { teamname }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return managerEmail;
	}

	@Override
	public String recruiterEmailForSenderAlert(int jobid) {
		logger.info("jobid:" + jobid);
		String recruiterEmail = null;
		try {
			recruiterEmail = (String) getJdbcTemplate.queryForObject(GET_RECRUITER_EMAIL_FOR_ALERT,
					new Object[] { jobid }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return recruiterEmail;
	}

	@Override
	public String recruiterEmailForSendAlert(int app_id) {
		logger.info("app_id:" + app_id);
		String recruiterEmail = null;
		try {
			recruiterEmail = (String) getJdbcTemplate.queryForObject(GET_RECRUITER_EMAIL_FOR_ALERT_APP,
					new Object[] { app_id }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return recruiterEmail;
	}

	@Override
	public List<AlertDetails> getApplicationAlert(String currentEmail) {
		logger.info("currentEmail :" + currentEmail);
		List<AlertDetails> getApplicationAlert = getJdbcTemplate.query(GET_APPLICANTION_ALERT_DETAILS,
				new BeanPropertyRowMapper(AlertDetails.class), currentEmail);
		return getApplicationAlert;

	}

	@Override
	public List<AlertDetails> getApplicantAlert(String currentEmail) {
		logger.info("currentEmail :" + currentEmail);
		List<AlertDetails> getApplicationAlert = getJdbcTemplate.query(GET_APPLICANT_ALERT_DETAILS,
				new BeanPropertyRowMapper(AlertDetails.class), currentEmail);
		return getApplicationAlert;
	}

	@Override
	public List<AlertDetails> getJobAlerts(String currentEmail) {
		logger.info("currentEmail :" + currentEmail);
		List<AlertDetails> getApplicationAlert = getJdbcTemplate.query(GET_JOB_ALERT_DETAILS,
				new BeanPropertyRowMapper(AlertDetails.class), currentEmail);
		return getApplicationAlert;
	}

	@Override
	public List<AlertDetails> getAlerts(int userid, String currentEmail) {
		logger.info("currentEmail :" + currentEmail);
		List<AlertDetails> getAlert = getJdbcTemplate.query(GET_ALERTS, new BeanPropertyRowMapper(AlertDetails.class),
				userid, currentEmail);
		return getAlert;
	}

	@Override
	public int msgStatus(int message_id) {
		return getJdbcTemplate.update(UPDATE_MESSAGE_STATUS, message_id);
	}

	@Override
	public int alertStatus(int alert_id) {
		return getJdbcTemplate.update(UPDATE_ALERT_STATUS, alert_id);
	}

	@Override
	public String getExistTemaname(int userId) {
		logger.info("userId:" + userId);
		String teamName = null;
		try {
			teamName = (String) getJdbcTemplate.queryForObject(GET_EXIST_TEAMNAME, new Object[] { userId },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return teamName;

	}

	@Override
	public Target getExistTargets(int userId) {
		logger.info("userId:" + userId);
		Target existTargets = null;
		try {
			existTargets = (Target) getJdbcTemplate.queryForObject(GET_EXIST_TARGET, new Object[] { userId },
					new BeanPropertyRowMapper(Target.class));

		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return existTargets;
	}

	@Override
	public AutoSignature getAutoSignatureDetails(int userId) {
		logger.info("userId:" + userId);
		AutoSignature existAutoSign = null;
		try {
			existAutoSign = (AutoSignature) getJdbcTemplate.queryForObject(GET_AUTO_SIGNATURE_DETAILS,
					new Object[] { userId }, new BeanPropertyRowMapper(AutoSignature.class));

		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return existAutoSign;
	}

	@Override
	public int totalMessages(int userid) {
		logger.info("userid:" + userid);
		int totalMessage = 0;
		try {
			totalMessage = getJdbcTemplate.queryForObject(GET_TOTAL_MESSAGE, new Object[] { userid }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			logger.error("Exception is: " + e);

		}
		return totalMessage;

	}

	@Override
	public int totalAlerts(String email) {
		logger.info("email:" + email);
		int countAlerts = 0;
		try {
			countAlerts = getJdbcTemplate.queryForObject(GET_TOTAL_ALERTS, new Object[] { email }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			logger.error("Exception is: " + e);

		}
		return countAlerts;

	}

	@Override
	public int updateSingleUserMessage(int from_userid, int to_userid) {
		logger.info("from_userid:" + from_userid);
		logger.info("to_userid:" + to_userid);
		return getJdbcTemplate.update(UPDATE_SINGLER_USER_MESSAGE_STATUS, to_userid, from_userid);
	}

	@Override
	public int updateSigleUserAlerts(String email) {
		logger.info("email:" + email);
		return getJdbcTemplate.update(UPDATE_SINGLER_USER_ALERTS_STATUS, email);
	}

	@Override
	public ChangeSource changeSource(int sourceid) {
		logger.info("sourceid:" + sourceid);
		ChangeSource changeSource = null;
		try {
			changeSource = (ChangeSource) getJdbcTemplate.queryForObject(GET_SOURCEDATA, new Object[] { sourceid },
					new BeanPropertyRowMapper(ChangeSource.class));
		} catch (EmptyResultDataAccessException e) {

			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return changeSource;
	}
}