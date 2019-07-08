package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.web.hulklogic.dao.CustomerDAO;
import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.Features;
import com.web.hulklogic.service.CustomerService;
import com.web.hulklogic.utility.CustomerEmailUtility;
import com.web.hulklogic.utility.EmailUtility;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO customerdao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = SQLException.class)
	public boolean saveCustomer(Customer customer) {

		int result_of_Customer = customerdao.saveCustomer(customer);
		logger.info(result_of_Customer);
		int getLastCustomerId = customerdao.getLastCustomerId();
		String numbers = "0123456789";
		String values = numbers;
		int len = 6;
		Random rndm_method = new Random();
		char[] password = new char[len];
		for (int i = 0; i < len; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
		}

		String text = String.valueOf(password);
		String team = "Team";
		String teamname = team.concat(text);
		customer.setTeamname(teamname);
		
		int result_of_User = customerdao.saveCustomerInUser(customer);

		if (result_of_Customer == 0 || result_of_User == 0) {
			return false;
		}

		return true;
	}

	@Override
	public boolean getEmailCheck(String email) {
		int result = customerdao.getCustomerByEmail(email);
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean getContactCheck(String contactno) {
		int result = customerdao.getContactCheck(contactno);
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Customer getCustomerInfoByEmail(String username) {
		List<Customer> listCustomer = customerdao.getCustomerInfoByEmail(username);
		Customer customer = listCustomer.get(0);
		return customer;
	}

	@Override
	public boolean sendActivationLinkAndDocument(Customer customer) {
		logger.info("Sending activation mail to:" + customer.getEmail());
		CustomerEmailUtility emailUtility = new CustomerEmailUtility();
		String[] details = new String[2];
		details[1] = "/login/createpassword" + "?" + "id:" + customer.getCustomerid();
		String activate = "activated";
		boolean status = emailUtility.sendMail(customer, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public boolean sendContractDocument(Customer customer) {
		logger.info("Sending activation mail to:" + customer.getEmail());
		CustomerEmailUtility emailUtility = new CustomerEmailUtility();
		String[] details = new String[2];
		details[1] = "/login/createpassword" + "?" + "id:" + customer.getCustomerid();
		String activate = "activated";
		boolean status = emailUtility.sendMail(customer, details, activate);
		if (status) {
			return true;
		}
		logger.info("status Service to:" + status);
		return false;
	}

	@Override
	public boolean saveproperty(String organization, String propertyname) {
		int result = customerdao.saveproperty(organization, propertyname);
		if (result == 1) {
			return true;
		}
		return false;	
	}

	@Override
	public Customer getvideourl(String company) {
		
		return customerdao.getvideourl(company);
	}

	@Override
	public boolean savefeatures(Features features) {
		int result = customerdao.savefeatures(features);
		if (result == 1) {
			return true;
		}
		return false;	
	}

	@Override
	public Features getfeatures(String organization) {
		// TODO Auto-generated method stub
		return null;
	}

}
