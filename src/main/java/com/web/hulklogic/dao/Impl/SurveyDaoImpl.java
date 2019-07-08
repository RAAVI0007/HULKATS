package com.web.hulklogic.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.web.hulklogic.dao.SurveyDao;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.Survey;
import com.web.hulklogic.entity.SurveyFeedback;
import com.web.hulklogic.entity.User;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	private static Logger logger = Logger.getLogger(SurveyDaoImpl.class);

	private static final String INSERT_SURVEY_DATA = "insert into user(que1,que2,que3,que4,que5,que6,que7,que8,que9,que10)"
			+ "values(:que1,:que2,:que3,:que4,:que5,:que6,:que7,:que8,:que9,:que10)";
	private static final String GET_ALL_Survey = "select * from survey s INNER JOIN surveyanswers sa  ON s.id = sa.queid  ";

	private static final String INSERT_SURVEYFEEDBACK_DATA = "insert into surveyanswers(name,email,organization,question1,question2,"
			+ "question3,question4,question5,question6,"
			+ "question7,question8,question9,question10,question10a,question10b,"
			+ "question11,question11a,question12,question12a,question13,question13a,question14)"
			+ "values(:name,:email,:organization,:question1,:question2,:question3,:question4,"
			+ ":question5,:question6,:question7,:question8,:question9,:question10,:question10a,:question10b,"
			+ ":question11,:question11a,:question12,:question12a,:question13,:question13a,:question14)";

	private static final String CHECK_CARD_EMAIL = "select email from bussinesscarddetails where email = ?";

	private static final String CHECK_BIZCARD_CONTACTNO = "select contactno from bussinesscarddetails where contactno = ?";
	private static final String INSERT_CARD_DATA = "insert into bussinesscarddetails(firstname,lastname,address,city,country,state,email,contactno,organization,pincode,card_id)"
			+ "values(:firstname,:lastname,:address,:city,:country,:state,:email,:contactno,:organization,:pincode,:card_id)";
	private static final String STATUS_CARD = "update  bussinesscard set status=0";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Override
	public int saveSurvey(Survey survey) {
		return namedParameterJdbcTemplate.update(INSERT_SURVEY_DATA, getSqlParameterByMode(survey));
	}

	private SqlParameterSource getSqlParameterByMode(Survey survey) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("que1", survey.getQue1());
		paramSource.addValue("que2", survey.getQue2());
		paramSource.addValue("que3", survey.getQue3());
		paramSource.addValue("que4", survey.getQue4());
		paramSource.addValue("que5", survey.getQue5());
		paramSource.addValue("que6", survey.getQue6());
		paramSource.addValue("que7", survey.getQue7());
		paramSource.addValue("que8", survey.getQue8());
		paramSource.addValue("que9", survey.getQue9());
		paramSource.addValue("que10", survey.getQue10());

		return paramSource;
	}

	@Override
	public List<Survey> getAllSurvey() {
		List<Survey> survey = getJdbcTemplate.query(GET_ALL_Survey, new BeanPropertyRowMapper(Survey.class));
		return survey;
	}

	@Override
	public int surveyDetails(SurveyFeedback survey) {
		// TODO Auto-generated method stub
		return namedParameterJdbcTemplate.update(INSERT_SURVEYFEEDBACK_DATA, getSqlParameter(survey));
	}

	private SqlParameterSource getSqlParameter(SurveyFeedback survey) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", survey.getName());
		paramSource.addValue("email", survey.getEmail());
		paramSource.addValue("organization", survey.getOrganization());
		paramSource.addValue("question1", survey.getQuestion1());
		paramSource.addValue("question2", survey.getQuestion2());
		paramSource.addValue("question3", survey.getQuestion3());
		paramSource.addValue("question4", survey.getQuestion4());
		paramSource.addValue("question5", survey.getQuestion5());
		paramSource.addValue("question6", survey.getQuestion6());
		paramSource.addValue("question7", survey.getQuestion7());
		paramSource.addValue("question8", survey.getQuestion8());
		paramSource.addValue("question9", survey.getQuestion9());
		paramSource.addValue("question10", survey.getQuestion10());
		paramSource.addValue("question10a", survey.getQuestion10a());
		paramSource.addValue("question10b", survey.getQuestion10b());
		paramSource.addValue("question11", survey.getQuestion11());
		paramSource.addValue("question11a", survey.getQuestion11a());
		paramSource.addValue("question12", survey.getQuestion12());
		paramSource.addValue("question12a", survey.getQuestion12a());
		paramSource.addValue("question13", survey.getQuestion13());
		paramSource.addValue("question13a", survey.getQuestion13a());
		paramSource.addValue("question14", survey.getQuestion14());

		return paramSource;
	}

	@Override
	public int getbizcardemailCheck(String email) {
		logger.info("email :" + email);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_CARD_EMAIL, email);
		int count = data.size();
		return count;
	}

	@Override
	public int getbizcardContactnoCheck(String contactno) {
		logger.info("contactno :" + contactno);
		List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_BIZCARD_CONTACTNO, contactno);
		int count = data.size();
		return count;
	}

	@Override
	public int saveCard(CardDetails carddetails) {
		return namedParameterJdbcTemplate.update(INSERT_CARD_DATA, getSqlParameterByMode(carddetails));
	}

	private SqlParameterSource getSqlParameterByMode(CardDetails carddetails) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("firstname", carddetails.getFirstname());
		paramSource.addValue("lastname", carddetails.getLastname());
		paramSource.addValue("address", carddetails.getAddress());
		paramSource.addValue("city", carddetails.getCity());
		paramSource.addValue("email", carddetails.getEmail());
		paramSource.addValue("contactno", carddetails.getContactno());
		paramSource.addValue("pincode", carddetails.getPincode());
		paramSource.addValue("state", carddetails.getState());
		paramSource.addValue("country", carddetails.getCountry());
		paramSource.addValue("card_id", carddetails.getCard_id());
		paramSource.addValue("organization", carddetails.getOrganization());
		return paramSource;
	}

	@Override
	public int cardstatus() {

		return getJdbcTemplate.update(STATUS_CARD);
	}

}
