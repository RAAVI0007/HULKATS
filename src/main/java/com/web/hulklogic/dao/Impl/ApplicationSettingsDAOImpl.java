package com.web.hulklogic.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.hulklogic.dao.ApplicationSettingsDAO;
import com.web.hulklogic.entity.CreateJob;

import com.web.hulklogic.entity.User;

@Repository
public class ApplicationSettingsDAOImpl implements ApplicationSettingsDAO {

	private static Logger logger = Logger.getLogger(ApplicationSettingsDAOImpl.class);
	
	private static final String GET_COUNTRIES_NAMES="select id,name from countries";
	private static final String GET_COMPANIES_NAMES ="SELECT DISTINCT company FROM job ORDER BY company";
	private static final String GET_COMPANIESPER_NAMES = "select * from jobassign ja inner join job j on ja.jobid = j.jobid where company=? and status='OPEN' ";
	private static final String GET_STATES_NAMES="select statename,id from allstates where country_id=?";
	private static final String GET_CITIES_NAMES="select citiesname from allcities where state_id=?";
	private static final String GET_COUNTRIES_BY_PHONECODE="select id,name from countries where phonecode=?";
	private static final String GET_CONTACT_NO_IN_PROFILE="select contactno from user where contactno=? and userId!=?";
	private static final String UPDATE_ATTACHEMENT1="update templates set attachement=? where template_id=?";
	private static final String UPDATE_ATTACHEMENT2="update templates set attachement1=? where template_id=?";
	private static final String UPDATE_UPLOADDOCUMENT1="update job set uploaddocument1=? where jobid=?";
	private static final String UPDATE_UPLOADDOCUMENT2="update job set uploaddocument2=? where jobid=?";
	private static final String UPDATE_UPLOADCV="update applicant set uplaodcv=? where applicantid=?";
	private static final String UPDATE_OTHERUPLOADCV="update applicant set otheruploads=? where applicantid=?";
	private static final String GET_PROPERTYNAME="select propertyname from user where email=?";
	
	
	@Autowired
	 private JdbcTemplate getJdbcTemplate;
	 @Override
	public List<CreateJob> getCountries() {
	     return getJdbcTemplate.query(GET_COUNTRIES_NAMES,new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
	}
	 
	 public List<CreateJob> getCompanies() {
	     return getJdbcTemplate.query(GET_COMPANIES_NAMES,new BeanPropertyRowMapper<CreateJob>(CreateJob.class));
	}
	 
	 public List<CreateJob> getperCompanies(String company) {
		 logger.info(company);
	     return getJdbcTemplate.query(GET_COMPANIESPER_NAMES,new BeanPropertyRowMapper<CreateJob>(CreateJob.class),company);
	}
	 
	@Override
	public List<CreateJob> getAllStates(int country_id) {
		 logger.info("country_id:"+country_id);
		 return getJdbcTemplate.query(GET_STATES_NAMES,new BeanPropertyRowMapper<CreateJob>(CreateJob.class),country_id);
	}
	
	@Override
	public List<CreateJob> getAllCities(int state_id) {
		 logger.info("state_id:"+state_id);
		 return getJdbcTemplate.query(GET_CITIES_NAMES,new BeanPropertyRowMapper<CreateJob>(CreateJob.class),state_id);
	}
	
	@Override
	public List<CreateJob> getCountriesByPhoneCode(int phoneCode) {
		  logger.info("phoneCode:"+phoneCode);
		 return getJdbcTemplate.query(GET_COUNTRIES_BY_PHONECODE,new BeanPropertyRowMapper<CreateJob>(CreateJob.class),phoneCode);
	}
	@Override
	public int checkContactNoInProfile(String contactNo,int userId) {
		    logger.info("contactNo:"+contactNo);
		    logger.info("userId:"+userId);
		    List<Map<String, Object>> data = getJdbcTemplate.queryForList(GET_CONTACT_NO_IN_PROFILE,contactNo,userId);
		    int count=data.size();
		    return count;
	}
	@Override
	public int updateAttachements1(int templateId) {
		logger.info("templateId:"+templateId);
		String attachment=null;
	    return getJdbcTemplate.update(UPDATE_ATTACHEMENT1, attachment,templateId);
	}
	@Override
	public int updateAttachements2(int templateId) {
		logger.info("templateId:"+templateId);
		String attachment=null;
	    return getJdbcTemplate.update(UPDATE_ATTACHEMENT2, attachment,templateId);
	}
	@Override
	public int updateUploadDocument1(int jobId) {
		logger.info("jobId:"+jobId);
		String uploadDocument1=null;
	    return getJdbcTemplate.update(UPDATE_UPLOADDOCUMENT1, uploadDocument1,jobId);
	}
	@Override
	public int updateUploadDocument2(int jobId) {
		logger.info("jobId:"+jobId);
		String uploadDocument2=null;
	    return getJdbcTemplate.update(UPDATE_UPLOADDOCUMENT2, uploadDocument2,jobId);
	}
	@Override
	public int updateUploadCv(int applicantId) {
		logger.info("applicantId:"+applicantId);
		String uploadCv=null;
	    return getJdbcTemplate.update(UPDATE_UPLOADCV, uploadCv,applicantId);
	}
	@Override
	public int updateOtheruploads(int applicantId) {
		logger.info("applicantId:"+applicantId);
		String otherUploadCv=null;
	    return getJdbcTemplate.update(UPDATE_OTHERUPLOADCV, otherUploadCv,applicantId);
	}

	@Override
	public User getpropertyname(String email) {
		 logger.info("email :"+email);
		 User user=null;
	   try{
		   user=(User) getJdbcTemplate.queryForObject(GET_PROPERTYNAME,  new Object[] {email },new BeanPropertyRowMapper(User.class));
	   
	   }catch(EmptyResultDataAccessException e){
		   User user1=new User();
		   logger.info("Hello");
		   return user;
		   
	   }catch(Exception e){
	       logger.error("Exception is: "+e);
	    }
	   return user;
	}
	
}
