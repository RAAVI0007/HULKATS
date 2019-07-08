package com.web.hulklogic.service;

import java.util.List;

import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.Features;

public interface CustomerService {

	public boolean saveCustomer(Customer customer);

	public boolean getEmailCheck(String email);

	public boolean getContactCheck(String contactno);

	public Customer getCustomerInfoByEmail(String username);

	public boolean sendActivationLinkAndDocument(Customer customer);

	public boolean sendContractDocument(Customer customer);
	
	public boolean saveproperty(String organization,String propertyname);
	
	public Customer getvideourl(String company);
	
	public boolean savefeatures(Features features);
	
	public Features getfeatures(String organization);

}
