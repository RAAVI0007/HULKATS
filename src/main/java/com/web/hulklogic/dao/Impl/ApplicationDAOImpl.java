package com.web.hulklogic.dao.Impl;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.web.hulklogic.dao.ApplicantionDAO;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.SenderEmail;
import com.web.hulklogic.entity.User;

@Repository
public class ApplicationDAOImpl implements ApplicantionDAO{
	private static Logger logger = Logger.getLogger(ApplicationDAOImpl.class);
	
	private static final String INSERT_JOBID_APPLICANTID="insert into application (jobid,applicantid,teamname,appemail) select * from (select :jobid, :applicantid,:teamname,:appemail) as tmp"
	         +" where :applicantid not in (select a.applicantid from application a where jobid=:jobid )";
	
	private static final String INSERT_EMPLOYEE_REFERRAL = "insert into employeereferral (employeeid, jobtitle, employeeemail,organization,jobid,location) values(:employeeid, :jobtitle, :employeeemail,:organization,:jobid,:location)";
	
	private static final String GET_AUTOCOMPLETE_CITIES="select c.sortname, s.statename,ac.citiesname from countries c inner join allstates s on c.id= s.country_id inner join allcities ac on s.id = ac.state_id where phonecode=:phonecode and s.statename like :Statenames  limit 8";
	private static final String GET_APPLICANT_DETAILS="select a.firstname, a.lastname, a.location, a.email,a.contactno,a.lastjobtitle,a.highesteducation,a.applicantid,ap.applicationid,"
			+ " ap.status,ap.source from applicant a inner join application ap on ap.applicantid = a.applicantid inner join job j on ap.jobid = j.jobid where ap.jobid =? and ap.teamname = ?";
	private static final String GET_APPLICATION_DETAILS="select a.firstname, a.lastname, a.location, a.email,a.contactno,a.lastjobtitle,a.highesteducation,a.applicantid,ap.app_id,"
			+ " ap.status,ap.source from applicant a inner join applicationstart ap on ap.applicantid = a.applicantid inner join job j on ap.jobid = j.jobid where ap.jobid =? and ap.teamname = ?";
	
	 private static final String INSERT_TEMPLATE="insert into templates(template_name,subject,body,email,hyperlink,attachement,attachement1,organization)values(:template_name,:subject,:body,:email,:hyperlink,:attachement,:attachement1,:organization)";
	 private static final String GET_ALL_TEMPLATE="select *from templates where template_id=?";
	 private static final String GET_ID_TEMPLATENAME="select template_id,template_name from templates";
	 private static final String GET_APPLICANT_DETAILS_BY_ID="select concat(firstname,' ',lastname) as Name ,  a.email from applicant a inner join applicationstart ap on ap.applicantid = a.applicantid where ap.app_id=?";
	 private static final String GET_APPLICANT_UPLOAD_DOCUMENT_BY_ID="select uplaodcv from applicant where applicantid=?";
	 private static final String GET_APPLICANT_OTHERUPLOAD_DOCUMENT_BY_ID="select otheruploads from applicant where applicantid=?";
	 private static final String GET_APPLICANT_NAME_EMAIL="select a.firstname,a.email from applicant a inner join application ap on ap.applicantid = a.applicantid";
	 private static final String GET_LAST_TEMPLATE_ID="SELECT template_id FROM templates ORDER BY template_id DESC LIMIT 1";
	 private static final String CHECK_TEMPLATENAME="select template_name from templates where template_name = ?";
	 private static final String CHECK_JOBTITLE = "select jobtitle from job where jobid=?"; 
	 private static final String UPDATE_TEMPLATE_WITH_OUT_ATTACHEMENT="update templates set subject=:subject,body=:body,email=:email where template_id = :template_id";
	 private static final String UPDATE_TEMPLATE="update templates set subject=:subject,body=:body,email=:email,attachement=:attachement,attachement1=:attachement1 where template_id = :template_id";
	 private static final String UPDATE_TEMPLATE_WITH_ATTACHEMENT="update templates set subject=:subject,body=:body,email=:email,attachement=:attachement where template_id = :template_id";
	 private static final String UPDATE_TEMPLATE_WITH_ATTACHEMENT1="update templates set subject=:subject,body=:body,email=:email,attachement1=:attachement1 where template_id = :template_id";
	 private static final String UPDATE_SOURCE_APPLICATIONSTART="update applicationstart set source=? ,created_by=? where app_id=?";
	 private static final String INSERT_SOURCE_APPLICATIONHISTORY="insert into applicationhistory(source,app_id,createdemail,particulars) values(:source,:app_id,:createdemail,:particulars)";
	 private static final String GET_STATUS_IN_APPLICATIONSTART_TABLE="select status from applicationstart where app_id=?";
	 private static final String GET_ASSIGN_EMAIL ="select recuiteremail from jobassign where jobid =?";
	 
	  @Autowired
	 private JdbcTemplate getJdbcTemplate;
	 @Autowired
	 private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
      
	@Override
	public int saveJobIdApplicantId(GetTokens gettokens) {
		int result=0;
		
		try {
			
			result= namedParameterJdbcTemplate.update(INSERT_JOBID_APPLICANTID, getSqlParameterByApplicant(gettokens));
			
		}catch(BadSqlGrammarException e) {
			return 5;
		}
		
		
		
		return result;
}
	private SqlParameterSource getSqlParameterByApplicant(GetTokens gettokens){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
	    logger.info("applicantid:"+gettokens.getApplicantid());
		paramSource.addValue("jobid",gettokens.getJobid());
		paramSource.addValue("applicantid",gettokens.getApplicantid());
		paramSource.addValue("teamname",gettokens.getTeamname());
		paramSource.addValue("appemail",gettokens.getEmail());
		return paramSource;
}
	
	
	public int saveemployeereferraljobs(GetEmployee getemployee){
int result=0;
		
		try {
			
			result= namedParameterJdbcTemplate.update(INSERT_EMPLOYEE_REFERRAL, getSqlParameterByReferral(getemployee));
			
		}catch(BadSqlGrammarException e) {
			return 5;
		}
		
		
		
		return result;
}
	private SqlParameterSource getSqlParameterByReferral(GetEmployee getemployee){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
		
	    paramSource.addValue("employeeid", getemployee.getEmployeeid());
		paramSource.addValue("jobtitle", getemployee.getDesignation());
		paramSource.addValue("employeeemail", getemployee.getEmail());
		paramSource.addValue("organization", getemployee.getOrganization());
		paramSource.addValue("jobid", getemployee.getJobid());
		paramSource.addValue("location", getemployee.getLocation());
		return paramSource;
}
	
	 public List<User> autoCompleteCities(int phonecode,String statenames){
	 logger.info("phonecode :"+phonecode);
	 logger.info("statenames :"+statenames);
     String Statenames=statenames + "%";
     MapSqlParameterSource pss = new MapSqlParameterSource();
     pss.addValue("Statenames",Statenames );
     pss.addValue("phonecode",phonecode );
     List<User> getAutoCompleteCity  = namedParameterJdbcTemplate.query(GET_AUTOCOMPLETE_CITIES, pss,new BeanPropertyRowMapper(User.class));
     return getAutoCompleteCity;
}

	@Override
	public List<Applicants>getApplicantDetails(int jobid,String teamname) {
		List<Applicants> getListApplicants  = getJdbcTemplate.query(GET_APPLICANT_DETAILS,new BeanPropertyRowMapper(Applicants.class),jobid,teamname);
		return getListApplicants;
	}
	@Override
	 public int saveTemplate(CreateTemplate createtemplate) {
		
	   return namedParameterJdbcTemplate.update(INSERT_TEMPLATE, getSqlParameterTemplate(createtemplate));
	 }
	 
	 private SqlParameterSource getSqlParameterTemplate(CreateTemplate createtemplate){
	  MapSqlParameterSource paramSource=new MapSqlParameterSource();
	  paramSource.addValue("template_name",createtemplate.template_name);
	  paramSource.addValue("body",createtemplate.getBody());
	  paramSource.addValue("subject",createtemplate.getSubject());
	  paramSource.addValue("email",createtemplate.getEmail());
	  paramSource.addValue("hyperlink",createtemplate.getHyperLink());
	  paramSource.addValue("attachement",createtemplate.getAttachement());
	  paramSource.addValue("attachement1",createtemplate.getAttachement1());
	  paramSource.addValue("organization",createtemplate.getOrganization());
	  return paramSource;
	 }
	 @Override
	 public List<CreateTemplate> getAllTemplates(int template_id) {
		 logger.info("template_id :"+template_id);
	  List<CreateTemplate> getTemplates  = getJdbcTemplate.query(GET_ALL_TEMPLATE,new BeanPropertyRowMapper(CreateTemplate.class),template_id);
	  return getTemplates;
	 }
	 @Override
	 public List<CreateTemplate> getTemplateNameId() {
	  List<CreateTemplate> getTemplatesNameId = getJdbcTemplate.query(GET_ID_TEMPLATENAME,new BeanPropertyRowMapper(CreateTemplate.class)); 
	  return getTemplatesNameId;
	 }
	 
	 @Override
	 public SenderEmail getApplicantDetailsById(int applicantid) {
		 logger.info("applicantid :"+applicantid);
		 SenderEmail senderEmail=null;
	   try{
		   senderEmail=(SenderEmail) getJdbcTemplate.queryForObject(GET_APPLICANT_DETAILS_BY_ID,  new Object[] {applicantid },new BeanPropertyRowMapper(SenderEmail.class));
	   
	   }catch(EmptyResultDataAccessException e){
		   SenderEmail senderEmailnull=new SenderEmail();
	    return senderEmailnull;
	   }catch(Exception e){
	       logger.error("Exception is: "+e);
	    }
	   return senderEmail;
	 }
	 
	 
	 @Override
	 public Applicants getUploadDocuments(int applicantid) {
		 logger.info("applicantid :"+applicantid);
	  Applicants getCvsDocuments=null;
	    try{
	     getCvsDocuments = (Applicants)getJdbcTemplate.queryForObject(GET_APPLICANT_UPLOAD_DOCUMENT_BY_ID, new Object[] { applicantid },new BeanPropertyRowMapper(Applicants.class));
	    }catch(EmptyResultDataAccessException e){
	     Applicants getCvsDocumentsnull=new  Applicants();
	     return getCvsDocumentsnull;
	    }catch(Exception e){
	       logger.error("Exception is: "+e);
	    }
	    return getCvsDocuments;  
	}
	 
	 
	 
	 
	 @Override
	 public List<Applicants> getApplicantsNameEmail() {
	  List<Applicants> getApplicantNameEmail = getJdbcTemplate.query(GET_APPLICANT_NAME_EMAIL,new BeanPropertyRowMapper(Applicants.class)); 
	  return getApplicantNameEmail;
	 }


	@Override
	public Applicants getOtherUploadDocuments(int applicantid) {
		 logger.info("applicantid :"+applicantid);
		Applicants getCvsDocuments=null;
	    try{
	     getCvsDocuments = (Applicants)getJdbcTemplate.queryForObject(GET_APPLICANT_OTHERUPLOAD_DOCUMENT_BY_ID, new Object[] { applicantid },new BeanPropertyRowMapper(Applicants.class));
	    }catch(EmptyResultDataAccessException e){
	     Applicants getCvsDocumentsnull=new  Applicants();
	     return getCvsDocumentsnull;
	    }catch(Exception e){
	       logger.error("Exception is: "+e);
	    }
	    return getCvsDocuments;  
	}
	
	 @Override
	 public int getLastTemplateId() {
	  Integer getTemplateId=null;
	  try {
		  getTemplateId=(Integer) getJdbcTemplate.queryForObject(GET_LAST_TEMPLATE_ID, Integer.class);
	  }catch(EmptyResultDataAccessException e) {
		  return 0;
	  }
	  return getTemplateId;
	 }
	
	@Override
	public int getLastTemplatename(String template_name) {
		logger.info("email :"+template_name);
		  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_TEMPLATENAME,template_name);
		  int count=data.size();
		  return count;
	}
	
	    	@Override
			public JobTitle getjobtitle(int jobid) {
				 logger.info("jobid :"+jobid);
					JobTitle jobtitle=null;
				    try{
				    	jobtitle = (JobTitle)getJdbcTemplate.queryForObject(CHECK_JOBTITLE, new Object[] { jobid },new BeanPropertyRowMapper(JobTitle.class));
				    }catch(EmptyResultDataAccessException e){
				    	JobTitle jobtitlenull=new  JobTitle();
				     return jobtitlenull;
				    }catch(Exception e){
				       logger.error("Exception is: "+e);
				    }
				    return jobtitle;  
				}
			@Override
			public List<Applicants> getApplicationDetails(int jobid, String teamname) {
				List<Applicants> getListApplication  = getJdbcTemplate.query(GET_APPLICATION_DETAILS,new BeanPropertyRowMapper(Applicants.class),jobid,teamname);
				return getListApplication;
			}
			

			@Override
			public int updateTemplate(CreateTemplate createtemplate) {
				  return namedParameterJdbcTemplate.update(UPDATE_TEMPLATE, getSqlParameterupdateTemplate(createtemplate));
					 }
					 
					 private SqlParameterSource getSqlParameterupdateTemplate(CreateTemplate createtemplate){
						 logger.info("attachement1 :"+createtemplate.getAttachement1());
						 logger.info("attachement :"+createtemplate.getAttachement());
					  MapSqlParameterSource paramSource=new MapSqlParameterSource();
					  paramSource.addValue("body",createtemplate.getBody());
					  paramSource.addValue("subject",createtemplate.getSubject());
					  paramSource.addValue("email",createtemplate.getEmail());
					  paramSource.addValue("template_id",createtemplate.getTemplate_id());
					  paramSource.addValue("attachement",createtemplate.getAttachement());
					  paramSource.addValue("attachement1",createtemplate.getAttachement1());
					  return paramSource;
					 }
					 
					 
					 
					@Override
					public int updateTemplateWithOutAttachement(CreateTemplate createtemplate) {
						     logger.info("template_id :"+createtemplate.getTemplate_id());
				            return namedParameterJdbcTemplate.update(UPDATE_TEMPLATE_WITH_OUT_ATTACHEMENT,  getSqlParameterupdateTemplateNoAttachements(createtemplate));
					 }
					 
					  private SqlParameterSource  getSqlParameterupdateTemplateNoAttachements(CreateTemplate createtemplate){
					  MapSqlParameterSource paramSource=new MapSqlParameterSource();
					  paramSource.addValue("body",createtemplate.getBody());
					  paramSource.addValue("subject",createtemplate.getSubject());
					  paramSource.addValue("email",createtemplate.getEmail());
					  paramSource.addValue("template_id",createtemplate.getTemplate_id());
					  return paramSource;
			
			
			}
					@Override
					public int updateTemplateAttachement(CreateTemplate createtemplate) {
						 logger.info("template_id :"+createtemplate.getTemplate_id());
						return namedParameterJdbcTemplate.update(UPDATE_TEMPLATE_WITH_ATTACHEMENT, getSqlParameterupdateTemplateWithAttachement(createtemplate));
					 }
					 
					 private SqlParameterSource getSqlParameterupdateTemplateWithAttachement(CreateTemplate createtemplate){
						 logger.info("attachement :"+createtemplate.getAttachement());
					  MapSqlParameterSource paramSource=new MapSqlParameterSource();
					  paramSource.addValue("body",createtemplate.getBody());
					  paramSource.addValue("subject",createtemplate.getSubject());
					  paramSource.addValue("email",createtemplate.getEmail());
					  paramSource.addValue("template_id",createtemplate.getTemplate_id());
					  paramSource.addValue("attachement",createtemplate.getAttachement());
					  return paramSource;
					 }
					 
					 
					@Override
					public int updateTemplateAttachement1(CreateTemplate createtemplate) {
						 logger.info("template_id :"+createtemplate.getTemplate_id());
						 return namedParameterJdbcTemplate.update(UPDATE_TEMPLATE_WITH_ATTACHEMENT1, getSqlParameterupdateTemplateWithAttachement1(createtemplate));
					 }
					 
					 private SqlParameterSource getSqlParameterupdateTemplateWithAttachement1(CreateTemplate createtemplate){
						 logger.info("attachement1 :"+createtemplate.getAttachement1());
					  MapSqlParameterSource paramSource=new MapSqlParameterSource();
					  paramSource.addValue("body",createtemplate.getBody());
					  paramSource.addValue("subject",createtemplate.getSubject());
					  paramSource.addValue("email",createtemplate.getEmail());
					  paramSource.addValue("template_id",createtemplate.getTemplate_id());
					  paramSource.addValue("attachement1",createtemplate.getAttachement1());
					  return paramSource;
}
					@Override
					public int updateSourceInAppStart(int appId,String source,String createdEmail) {
						 logger.info("appId :"+appId);
						 logger.info("source:"+source);
						 logger.info("createdEmail:"+createdEmail);
						 return getJdbcTemplate.update(UPDATE_SOURCE_APPLICATIONSTART, source,createdEmail,appId);
					}
					
					
					@Override
					public int updateSourceInAppHistory(ApplicantInfo applicant) {
						logger.info("enter into the applicationsource");
						return namedParameterJdbcTemplate.update(INSERT_SOURCE_APPLICATIONHISTORY, getSqlParameterBySource(applicant));
					}
					 
					
					private SqlParameterSource getSqlParameterBySource(ApplicantInfo applicant){
						MapSqlParameterSource paramSource=new MapSqlParameterSource();
						paramSource.addValue("source", applicant.getSource());
						paramSource.addValue("app_id", applicant.getApp_id());
						paramSource.addValue("createdemail", applicant.getEmail());
						paramSource.addValue("particulars", "Source Update");
						return paramSource;
					}
					
					@Override
					public String getApplicationStatusByApp_Id(int app_id) {
						String name =null;
						try {
						    name=(String)getJdbcTemplate.queryForObject(GET_STATUS_IN_APPLICATIONSTART_TABLE, new Object[] { app_id }, String.class);
						}catch(EmptyResultDataAccessException e) {
							return null;
						}catch(Exception e){
					         logger.error("Exception is: "+e);
					      }
						return name;
					}
					
					
					@Override
					public String getAssignEmail(int jobid) {
						String email =null; 
						logger.info(jobid);
						try {
							email=(String)getJdbcTemplate.queryForObject(GET_ASSIGN_EMAIL, new Object[] { jobid }, String.class);
							logger.info(email);
						}catch(EmptyResultDataAccessException e) {
							return null;
						}catch(Exception e){
					         logger.error("Exception is: "+e);
					      }
						return email;
					}
}					 
					 