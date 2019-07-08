package com.web.hulklogic.dao.Impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.ApplicantDAO;
import com.web.hulklogic.entity.ApplicantDetails;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.ApplicationHistory;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.Sharejobs;
@Repository
public class ApplicantDAOImpl implements  ApplicantDAO{
	
	private static Logger logger = Logger.getLogger(ApplicationDAOImpl.class);
	
	private static final String GET_AUTOCOMPLETE_TEMPLATE="select  template_name,template_id,subject,body,hyperlink,attachement from templates where template_name like :pname";
	private static final String GET_SUBJECT_BODY_BY_TEMPLATE="select subject,body from templates where template_name=?";
	private static final String UPDATE_PASSWORD="update user set password =? where userid=?";
	private static final String CREATE_PASSWORD="update user set password =?,status =1 where userid=?";
	
	private static final String INSERT_STATUS="insert into applicationhistory(status,app_id,createdemail,statuscomment,particulars) values(:status,:app_id,:createdemail,:statuscomment,:particulars)";
	private static final String UPDATE_STATUS="update applicationstart set status=?,recruiteremail=? where app_id=?";
	
	private static final String GET_STATUS_BY_APPLICATIONID="select concat(u.firstname, ' ',u.lastname) as name, a.status,a.created_at, a.email from applicationstatus a inner join user u where a.email = u.email and a.app_id=?";
	private static final String Update_CVS_DOCUMENT="update applicant set uplaodcv=? where applicantid=?";
	private static final String Update_CVS_DOCUMENT1="update applicant set otheruploads=? where applicantid=?";
	private static final String UPDATE_DOCUMENT1="update job set uploaddocument1=? where jobid=? ";
    private static final String UPDATE_DOCUMENT2="update job set uploaddocument2=? where jobid=? ";
	private static final String UPDATE_DOCUMENT3="update job set uploaddocument3=? where jobid=? ";
    private static final String UPDATE_DOCUMENT4="update job set uploaddocument4=? where jobid=? ";
    private static final String UPDATE_DOCUMENT5="update job set uploaddocument5=? where jobid=? ";
    private static final String CHECK_EMAIL="select email from applicant where email = ?";
    private static final String CHECK_EMAIL_PASSWORD = "select * from applicant where email = ? and password =?";
	private static final String CHECK_CONTACTNO="select contactno from applicant where contactno = ?";
	private static final String GET_APPLICANTID="select applicantid from applicant where email = ?";
	private static final String CHECK_JOBID_APPLICANTID ="select * from applicationstart where jobid =? and applicantid =? ";
	
	private static final String CHECKHOLD_JOBID_APPLICANTID ="select * from applicationhold where jobid =? and applicantid =? ";
	private static final String GET_APPLICANT_DETAILS ="select * from applicantdetails where applicantdetails_id = ? ";
	private static final String INSERT_SHARE_STATUS ="insert into shareemail(name, email, url) values(:name, :email, :url)";
	 @Autowired
	 private JdbcTemplate getJdbcTemplate;
	 @Autowired
	 private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<CreateTemplate> autoCompleteTemplate(String templatename) {
		 logger.info("templatename:"+templatename);
		 String template=templatename + "%";
	     MapSqlParameterSource pss = new MapSqlParameterSource();
	     pss.addValue("pname",template );
	     List<CreateTemplate> getAutoCompleteTemplate  = namedParameterJdbcTemplate.query(GET_AUTOCOMPLETE_TEMPLATE, pss,new BeanPropertyRowMapper(CreateTemplate.class));
	     return getAutoCompleteTemplate;
	}
	@Override
	 public List<CreateTemplate> getSubjectBody(String templatename) {
		 logger.info("templatename:"+templatename);
	     List<CreateTemplate> getSubjectBody  = getJdbcTemplate.query(GET_SUBJECT_BODY_BY_TEMPLATE,new BeanPropertyRowMapper(CreateTemplate.class),templatename);
	     return getSubjectBody;
	  
	 }
	@Override
	 public int updatepassword(int userid, String password) {
		logger.info("userid:"+userid);
		logger.info("password:"+password);
	   return getJdbcTemplate.update(UPDATE_PASSWORD, password,userid);
	 }
	
	@Override
	 public int createpassword(int userid, String password) {
		logger.info("userid:"+userid);
		logger.info("password:"+password);
	   return getJdbcTemplate.update(CREATE_PASSWORD, password,userid);
	 }
	
	@Override
	 public int insertStatus(ApplicantInfo applicant) {
		logger.info("enter into the applicationstatus");
		return namedParameterJdbcTemplate.update(INSERT_STATUS, getSqlParameterByStatus(applicant));
	 }
	
	private SqlParameterSource getSqlParameterByStatus(ApplicantInfo applicant){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
		paramSource.addValue("status", applicant.getStatus());
		paramSource.addValue("app_id", applicant.getApp_id());
		paramSource.addValue("createdemail", applicant.getEmail());
		paramSource.addValue("statuscomment", applicant.getComment());
		paramSource.addValue("particulars", "change status");
		return paramSource;
	}

	 @Override
	 public int updateStatus(int applicationid, String status,String email) {
		 logger.info("applicationid:"+applicationid);
		logger.info("status:"+status);
		logger.info("email:"+email);
	  return getJdbcTemplate.update(UPDATE_STATUS, status,email,applicationid);
	 }
	 
	 @Override
	 public List<JobTitle> getStatusByApplicationid(int app_id) {
		 logger.info("applicationid:"+app_id);
	     return getJdbcTemplate.query(GET_STATUS_BY_APPLICATIONID,new BeanPropertyRowMapper(JobTitle.class), app_id);
	 }
	@Override
	public int updateCvsDocument(String documentname, int applicantid) {
		 logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
		
		return getJdbcTemplate.update(Update_CVS_DOCUMENT, documentname,applicantid);
	}
	@Override
	public int updateCvsDocument1(String documentname, int applicantid) {
		logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
		 return getJdbcTemplate.update(Update_CVS_DOCUMENT1, documentname,applicantid);
	}
	@Override
	 public int updateDocument1(String documentname, int applicantid) {
		logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
	  return getJdbcTemplate.update(UPDATE_DOCUMENT1, documentname,applicantid);
	 }


	 @Override
	 public int updateDocument2(String documentname, int applicantid) {
		 logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
	  return getJdbcTemplate.update(UPDATE_DOCUMENT2, documentname,applicantid);
	 }


	 @Override
	 public int updateDocument3(String documentname, int applicantid) {
		 logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
	  return getJdbcTemplate.update(UPDATE_DOCUMENT3, documentname,applicantid);
	 }


	 @Override
	 public int updateDocuemnt4(String documentname, int applicantid) {
		 logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
	  return getJdbcTemplate.update(UPDATE_DOCUMENT4, documentname,applicantid);
	 }


	 @Override
	 public int updateDocument5(String documentname, int applicantid) {
		 logger.info("documentname:"+documentname);
		 logger.info("applicantid:"+applicantid);
	  return getJdbcTemplate.update(UPDATE_DOCUMENT5, documentname,applicantid);
	 }
	 @Override
		public int getappemailCheck(String email) {
			 logger.info("email :"+email);
			  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_EMAIL,email);
			  int count=data.size();
			  return count;
		}
	 
	 @Override
	 public int getappemailpassCheck(String email, String password){
		 logger.info("email :"+email);
		  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_EMAIL_PASSWORD,email,password);
		  int count=data.size();
		  return count;
		 
	 }

		@Override
		public int getappmobileCheck(String contactno) {
			logger.info("contactno :"+contactno);
			  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_CONTACTNO,contactno);
			  int count=data.size();
			  return count;
		}
		@Override
		public int getapplicantid(String email) {
			Integer getApplicantid=null;
			try {
				getApplicantid = (Integer) getJdbcTemplate.queryForObject(GET_APPLICANTID, Integer.class, email );
			   }catch(EmptyResultDataAccessException e) {
			    return 0;
			   }
			 return getApplicantid;
			  }
		@Override
		public int appchecking(int jobid, int applicantid) {
			 List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_JOBID_APPLICANTID,jobid,applicantid);
			  int count=data.size();
			  return count;
		}
		@Override
		public int appcheckinghold(int jobid, int applicantid) {
			 List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECKHOLD_JOBID_APPLICANTID,jobid,applicantid);
			  int count=data.size();
			  return count;
		}
		@Override
		public ApplicantDetails profile(int applicantdetails_id) {
			logger.info("applicantdetails_id :" + applicantdetails_id);
			ApplicantDetails applicacantdetails = null;
			try {
				applicacantdetails = (ApplicantDetails) getJdbcTemplate.queryForObject(
						GET_APPLICANT_DETAILS, new Object[] { applicantdetails_id },
						new BeanPropertyRowMapper(ApplicantDetails.class));

			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				logger.error("Exception is: " + e);
			}
			return applicacantdetails;
		}
		@Override
		public int insertshareemail(Sharejobs sharejob) {
			logger.info("enter into the applicationstatus");
			return namedParameterJdbcTemplate.update(INSERT_SHARE_STATUS, getSqlParameterByShare(sharejob));
		 }
		
		private SqlParameterSource getSqlParameterByShare(Sharejobs sharejob){
			MapSqlParameterSource paramSource=new MapSqlParameterSource();
			paramSource.addValue("email",sharejob.getEmail());
			paramSource.addValue("name",sharejob.getName());
			paramSource.addValue("url",sharejob.getUrl());
			
			return paramSource;
		}

		
		
}
