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

import com.web.hulklogic.dao.CustomerDAO;
import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.Features;
import com.web.hulklogic.entity.SenderEmail;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
private static Logger logger = Logger.getLogger(CustomerDAOImpl .class);
	
	private static final String INSERT_CUSTOMER_DATA="insert into customer(firstname,lastname,email,organization,password,contactno,logourl,videourl,roleid)"
			+ "values(:firstname,:lastname,:email,:organization,:password,:contactno,:logourl,:videourl,:roleid)";
	 private static final String CHECK_EMAIL="select email from customer where email = ?";
    private static final String CHECK_CONTACTNO="select contactno from customer where contactno = ?";
    private static final String GET_CUSTOMER_DETAILS_BY_EMAIL="select *from customer where email=:username";
    private static final String INSERT_CUSTOMER_DETAILS_IN_USER="insert into user(firstname,lastname,email,password,contactno,roleid,organization,teamname,logourl,teamname1,addrline1,city,templatedesignid)"
			+ "values(:firstname,:lastname,:email,:password,:contactno,:roleid,:organization,:teamname,:logourl,:teamname1,:addrline1,:city,:templatedesignid"
			+ ")";
    private static final String GET_LAST_INSERT_CUSOMERID="SELECT customerid FROM customer ORDER BY customerid DESC LIMIT 1";
    private static final String UPDATE_PROPERTY_NAME ="update user set propertyname=? where organization=?";
    private static final String GET_VIDEO_URL ="select * from customer where organization = ?";
    private static final String INSERT_FEATURES ="insert into companyaddons(companyname, subscriptiontype, sourcing, api, email, emailtemplates, careerpage)"
    		+"values(:companyname, :subscriptiontype, :sourcing, :api, :email, :emailtemplates, :careerpage)";
    private static final String  GET_FEATURES = "select * from companyaddons from organization = ?";
    
    @Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate getJdbcTemplate;

	@Override
	public int saveCustomer(Customer customer) {
		return namedParameterJdbcTemplate.update(INSERT_CUSTOMER_DATA, getSqlParameterByMode(customer));
	}
	
	private SqlParameterSource getSqlParameterByMode(Customer customer){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
		paramSource.addValue("firstname", customer.getFirstname());
		paramSource.addValue("lastname", customer.getLastname());
		paramSource.addValue("email", customer.getEmail());
		paramSource.addValue("organization", customer.getOrganization());
		paramSource.addValue("password", customer.getPassword());
		paramSource.addValue("contactno", customer.getContactno());
		paramSource.addValue("logourl", customer.getLogoname());
		paramSource.addValue("videourl", customer.getVideourl());
		paramSource.addValue("roleid", customer.getRoleid());
		return paramSource;
	}

	@Override
	public int getCustomerByEmail(String email) {
		 logger.info("email :"+email);
		  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_EMAIL,email);
		  int count=data.size();
		  return count;
		 }
	

	@Override
	public int getContactCheck(String contactno) {
		logger.info("contactno :"+contactno);
		  List<Map<String, Object>> data = getJdbcTemplate.queryForList(CHECK_CONTACTNO,contactno);
		  int count=data.size();
		  return count;
	}

	@Override
	public List<Customer> getCustomerInfoByEmail(String username) {
		logger.info("username :"+username);
	    MapSqlParameterSource pss = new MapSqlParameterSource();
		pss.addValue("username",username );
		List<Customer> customer= namedParameterJdbcTemplate.query(GET_CUSTOMER_DETAILS_BY_EMAIL,pss,new BeanPropertyRowMapper(Customer.class));
        return customer;
	}

	@Override
	public int saveCustomerInUser(Customer customer) {
		return namedParameterJdbcTemplate.update(INSERT_CUSTOMER_DETAILS_IN_USER, getSqlParameterByModeInUser(customer));
	}
	
	private SqlParameterSource getSqlParameterByModeInUser(Customer customer){
		MapSqlParameterSource paramSource=new MapSqlParameterSource();
		paramSource.addValue("firstname", customer.getFirstname());
		paramSource.addValue("lastname", customer.getLastname());
		paramSource.addValue("email", customer.getEmail());
		paramSource.addValue("password", customer.getPassword());
		paramSource.addValue("contactno", customer.getContactno());
		paramSource.addValue("roleid", customer.getRoleid());
		paramSource.addValue("organization", customer.getOrganization());
		paramSource.addValue("teamname", customer.getTeamname());
		paramSource.addValue("logourl", customer.getLogoname());
		paramSource.addValue("teamname1", customer.getTeamname());
		paramSource.addValue("addrline1", customer.getAddrline1());
		paramSource.addValue("city", customer.getLocation());
		paramSource.addValue("templatedesignid", 1);
		return paramSource;
	}

	@Override
	public int getLastCustomerId() {
		Integer getTemplateId=null;
		  try {
			  getTemplateId=(Integer) getJdbcTemplate.queryForObject(GET_LAST_INSERT_CUSOMERID, Integer.class);
		  }catch(EmptyResultDataAccessException e) {
			  return 0;
		  }
		  return getTemplateId;
	}

	@Override
	public int saveproperty(String organization, String propertyname) {
		 return getJdbcTemplate.update(UPDATE_PROPERTY_NAME, propertyname,organization);
	}

	@Override
	public Customer getvideourl(String company) {
		 logger.info("company :"+company);
		 Customer customer=null;
	   try{
		   customer=(Customer) getJdbcTemplate.queryForObject(GET_VIDEO_URL,  new Object[] {company },new BeanPropertyRowMapper(Customer.class));
	   
	   }catch(EmptyResultDataAccessException e){
		   Customer customer1=new Customer();
		   logger.info("Hello");
		   return customer1;
		   
	   }catch(Exception e){
	       logger.error("Exception is: "+e);
	    }
	   return customer;
	}

	@Override
	public int savefeatures(Features features) {
			return namedParameterJdbcTemplate.update(INSERT_FEATURES, getSqlParameterByModeInfeatures(features));
		}
		
		private SqlParameterSource getSqlParameterByModeInfeatures(Features features){
			MapSqlParameterSource paramSource=new MapSqlParameterSource();
			paramSource.addValue("companyname", features.getCompanyname());
			paramSource.addValue("subscriptiontype", features.getSubscriptiontype());
			paramSource.addValue("sourcing", features.getSourcing());
			paramSource.addValue("api", features.getApi());
			paramSource.addValue("email", features.getEmail());
			paramSource.addValue("emailtemplates", features.getEmailtemplates());
			paramSource.addValue("careerpage", features.getCareerpage());
			
			return paramSource;
		}

		@Override
		public Features getfeatures(String organization) {
			 logger.info("company :"+organization);
			 Features customer=null;
		   try{
			   customer=(Features) getJdbcTemplate.queryForObject(GET_FEATURES,  new Object[] {organization },new BeanPropertyRowMapper(Features.class));
		   
		   }catch(EmptyResultDataAccessException e){
			   Features customer1=new Features();
			   logger.info("Hello");
			   return customer1;
			   
		   }catch(Exception e){
		       logger.error("Exception is: "+e);
		    }
		   return customer;
		}

}
