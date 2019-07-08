package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.Features;

public interface CustomerDAO {

	public int saveCustomer(Customer customer);

	public int getCustomerByEmail(String email);

	public int getContactCheck(String contactno);

	public List<Customer> getCustomerInfoByEmail(String username);

	public int saveCustomerInUser(Customer customer);

	public int getLastCustomerId();
	
	public int saveproperty(String organization,String propertyname);
	
	public Customer getvideourl(String company);
	
	public int savefeatures(Features features);
	
	public Features getfeatures(String organization);

}
