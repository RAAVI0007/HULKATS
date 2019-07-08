package com.web.hulklogic.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.TemplateDAO;
import com.web.hulklogic.entity.ApplicationHistory;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.SenderEmail;

@Repository
public class TemplateDAOImpl implements TemplateDAO {

	private static Logger logger = Logger.getLogger(TemplateDAOImpl.class);

	private static final String GET_TEMPLATE_DOCUMENTS_BY_TEMPLATEID = "select attachement,attachement1 from templates where template_id=?";
	private static final String CHECk_TEMPLATE_NAME = "select attachement from  templates where attachement=?";
	private static final String INSERT_INDIVIDUAL_HISTORY = "insert into applicationhistory(app_id,createdemail,particulars,subject,body,attachement1,attachement2,attachement3,attachement4)values(:app_id,"
			+ ":createdemail,:particulars,:subject,:body,:attachement1,:attachement2,:attachement3,:attachement4)";

	private static final String INSERT_BULKEMAIL_HISTORY = "insert into applicationhistory(app_id,createdemail,particulars,subject,body,attachement1,attachement2,attachement3,attachement4)values(:app_id,"
			+ ":createdemail,:particulars,:subject,:body,:attachement1,:attachement2,:attachement3,:attachement4)";
	private static final String GET_APPLICATION_HISTORY_BY_APP_ID = "select *from applicationhistory where app_id=:appId";
	private static final String GET_ALL_EMAIL_BY_APP_ID = "select ap.email from applicant ap inner join applicationstart a on ap.applicantid=a.applicantid where a.app_id IN (:appIds)";
	private static final String GET_APPLICATION_HISTORY_BY_HISTORY_ID = "select *from applicationhistory where historyid=?";
	private static final String UPDATE_ATTACHEMENT = "update templates set attachement=?   where template_id=?";
	private static final String UPDATE_ATTACHEMENT1 = "update templates set attachement1=?  where template_id=?";
	private static final String UPDATE_ATTACHMENT_IN_HISTORY_TABLE = "update applicationhistory set attachement1=?  where historyid=?";
	private static final String UPDATE_ATTACHMENT_IN_HISTORY_TABLE1 = "update applicationhistory set attachement2=?  where historyid=?";
	private static final String UPDATE_ATTACHMENT_IN_HISTORY_TABLE2 = "update applicationhistory set attachement3=?  where historyid=?";
	private static final String UPDATE_ATTACHMENT_IN_HISTORY_TABLE3 = "update applicationhistory set attachement4=?  where historyid=?";
	private static final String UPDATE_TEAMNAME = "update user set teamname1=? where teamname=?";
	private static final String GET_AUTO_SIGNATURE_BY_EMAIL = "select autosin_name,autosin_companyname,autosin_designation,autosin_companyurl,autosin_contact,style,sightml from user where email=?";
	private static final String INSERT_TERMS_CONDITION = "Update user set terms=? where userid=?";
	private static final String GET_TERMS_CONDITION_VALUE = "select terms from user where userid=?";
	private static final String GET_TEMPLATES_DETAILS = "select *from templates where template_id=?";

	@Autowired
	private JdbcTemplate getJdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public CreateTemplate getTempalteDocumentsById(int tempalteId) {
		CreateTemplate getTempalteDocuments = null;
		try {
			getTempalteDocuments = (CreateTemplate) getJdbcTemplate.queryForObject(GET_TEMPLATE_DOCUMENTS_BY_TEMPLATEID,
					new Object[] { tempalteId }, new BeanPropertyRowMapper(CreateTemplate.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getTempalteDocuments;

	}

	@Override
	public int checkTemplateName(String templateName) {
		logger.info("templateName :" + templateName);
		int count = 0;
		List<Map<String, Object>> checkjob = getJdbcTemplate.queryForList(CHECk_TEMPLATE_NAME, templateName);
		count = checkjob.size();
		return count;

	}

	@Override
	public int updateTemplateAttachement(String attachement, int template_id) {
		logger.info("attachement :" + attachement);
		logger.info("template_id :" + template_id);

		return getJdbcTemplate.update(UPDATE_ATTACHEMENT, attachement, template_id);
	}

	@Override
	public int updateTemplateAttachement1(String attachement, int template_id) {
		logger.info("attachement :" + attachement);
		logger.info("template_id :" + template_id);
		return getJdbcTemplate.update(UPDATE_ATTACHEMENT1, attachement, template_id);
	}

	@Override
	public int saveIndividualHistroy(SenderEmail senderEmail) {
		return namedParameterJdbcTemplate.update(INSERT_INDIVIDUAL_HISTORY, getSqlParameterByMode(senderEmail));
	}

	private SqlParameterSource getSqlParameterByMode(SenderEmail senderEmail) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("app_id", senderEmail.getApplicationid());
		paramSource.addValue("createdemail", senderEmail.getCurrentemail());
		paramSource.addValue("particulars", "Email");
		paramSource.addValue("subject", senderEmail.getSubject());
		paramSource.addValue("body", senderEmail.getBody());
		paramSource.addValue("attachement1", senderEmail.getCreateAttachment());
		paramSource.addValue("attachement2", senderEmail.getCreateAttachment1());
		paramSource.addValue("attachement3", senderEmail.getAttachment2());
		paramSource.addValue("attachement4", senderEmail.getAttachment3());
		return paramSource;
	}

	@Override
	public int[] saveBulkEmailHistory(List<Integer> appId, BulkEmailSend bulkEmailSend) {
		List<Map<String, Object>> batchValues = null;
		int[] updateCounts = null;
		try {
			batchValues = new ArrayList<>(appId.size());
			for (Integer getTheValue : appId) {
				batchValues.add(new MapSqlParameterSource("app_id", getTheValue)
						.addValue("body", bulkEmailSend.getBody()).addValue("subject", bulkEmailSend.getSubject())
						.addValue("particulars", "Email").addValue("createdemail", bulkEmailSend.getCurrentemail())
						.addValue("attachement1", bulkEmailSend.getCreateAttachment())
						.addValue("attachement2", bulkEmailSend.getCreateAttachment1())
						.addValue("attachement3", bulkEmailSend.getAttachment2())
						.addValue("attachement4", bulkEmailSend.getAttachment3()).getValues());
			}
			updateCounts = namedParameterJdbcTemplate.batchUpdate(INSERT_BULKEMAIL_HISTORY,
					batchValues.toArray(new Map[appId.size()]));
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return updateCounts;
	}

	@Override
	public List<ApplicationHistory> getApplicationHistoryByappId(int appId) {
		logger.info("appIds:" + appId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("appId", appId);
		List<ApplicationHistory> applicationHistory = namedParameterJdbcTemplate.query(
				GET_APPLICATION_HISTORY_BY_APP_ID, parameters, new BeanPropertyRowMapper(ApplicationHistory.class));
		return applicationHistory;
	}

	@Override
	public List<String> getEmailByAppId(List<Integer> appIds) {
		logger.info(appIds);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("appIds", appIds);
		logger.info(parameters.getValue("appIds"));
		List<String> getEmail = namedParameterJdbcTemplate.query(GET_ALL_EMAIL_BY_APP_ID, parameters,
				new EmailMapRow());
		return getEmail;
	}

	private static class EmailMapRow implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int index) throws SQLException {
			return rs.getString("email");
		}
	}

	@Override
	public ApplicationHistory getApplicationHistoryByhistoryId(int historyId) {
		logger.info("historyId :" + historyId);
		ApplicationHistory applicationHistory = null;
		try {
			applicationHistory = (ApplicationHistory) getJdbcTemplate.queryForObject(
					GET_APPLICATION_HISTORY_BY_HISTORY_ID, new Object[] { historyId },
					new BeanPropertyRowMapper(ApplicationHistory.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return applicationHistory;
	}

	@Override
	public int updateAttachments(String attachement, int historyId) {
		logger.info("attachement :" + attachement);
		logger.info("historyId :" + historyId);
		return getJdbcTemplate.update(UPDATE_ATTACHMENT_IN_HISTORY_TABLE, attachement, historyId);
	}

	@Override
	public int updateAttachments1(String attachement, int historyId) {
		logger.info("attachement :" + attachement);
		logger.info("historyId :" + historyId);
		return getJdbcTemplate.update(UPDATE_ATTACHMENT_IN_HISTORY_TABLE1, attachement, historyId);
	}

	@Override
	public int updateAttachments2(String attachement, int historyId) {
		logger.info("attachement :" + attachement);
		logger.info("historyId :" + historyId);
		return getJdbcTemplate.update(UPDATE_ATTACHMENT_IN_HISTORY_TABLE2, attachement, historyId);
	}

	@Override
	public int updateAttachments3(String attachement, int historyId) {
		logger.info("attachement :" + attachement);
		logger.info("historyId :" + historyId);
		return getJdbcTemplate.update(UPDATE_ATTACHMENT_IN_HISTORY_TABLE3, attachement, historyId);
	}

	@Override
	public int updateTeamname(String teamname, String oldTeamname) {
		logger.info("teamname :" + teamname);
		logger.info("oldTeamname :" + oldTeamname);
		return getJdbcTemplate.update(UPDATE_TEAMNAME, teamname, oldTeamname);
	}

	@Override
	public AutoSignature geAutoSignatureByEmail(String email) {
		logger.info("email :" + email);
		AutoSignature autosignature = null;
		try {
			autosignature = (AutoSignature) getJdbcTemplate.queryForObject(GET_AUTO_SIGNATURE_BY_EMAIL,
					new Object[] { email }, new BeanPropertyRowMapper(AutoSignature.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return autosignature;
	}

	@Override
	public int saveTermsAndCondition(int userid) {
		return getJdbcTemplate.update(INSERT_TERMS_CONDITION, 1, userid);

	}

	@Override
	public int getTerms(int userid) {
		Integer id = null;
		try {
			id = (Integer) getJdbcTemplate.queryForObject(GET_TERMS_CONDITION_VALUE, new Object[] { userid },
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 3;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return id;

	}

	@Override
	public CreateTemplate getTemplatesDetails(int templateId) {
		logger.info("templateId :" + templateId);
		CreateTemplate getTempaltes = null;
		try {
			getTempaltes = (CreateTemplate) getJdbcTemplate.queryForObject(GET_TEMPLATES_DETAILS,
					new Object[] { templateId }, new BeanPropertyRowMapper(CreateTemplate.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return getTempaltes;
	}

}
