package com.web.hulklogic.utility;

import org.apache.log4j.Logger;

public class CompareJobswithEmployees {
	
	private static Logger logger = Logger.getLogger(CompareJobswithEmployees.class);
	public boolean compareEmplyeeWord(String designation,String jobtitle){
		boolean data = (designation.toLowerCase()).equals(jobtitle.toLowerCase());
		
		return data;
	}
	

}
