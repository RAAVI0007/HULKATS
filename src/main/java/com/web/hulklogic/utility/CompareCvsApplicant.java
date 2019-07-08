package com.web.hulklogic.utility;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class CompareCvsApplicant {
	private static Logger logger = Logger.getLogger(CompareCvsApplicant.class);

	public float compareCvsApplicantWord(String[] jobStrt, String[] applicantStr) {

		logger.info("jobStrt:" + jobStrt);
		logger.info("applicantStr:" + applicantStr);
		ArrayList<String> addStr = new ArrayList<String>();
		String[] jobStrtSplict = jobStrt;
		String[] applicantStrSplict = applicantStr;
		int jobLength = jobStrtSplict.length;
		int applicantLength = applicantStrSplict.length;
		int addStrSize = 0;
		float tokenpercentage = 0f;
		logger.info("jobLength:" + jobLength);
		logger.info("applicantLength:" + applicantLength);
		for (String s : jobStrtSplict) {
			if (applicantLength == 0) {

			} else {
				for (String ss : applicantStrSplict) {
					if ((ss.toLowerCase()).equals(s.toLowerCase())) {
						addStr.add(ss);
					}
				}
				
			}
			
		}
		addStrSize = addStr.size();
		logger.info(addStrSize);
		tokenpercentage = (float) ((addStrSize * 100) / jobLength);
		logger.info(tokenpercentage);
		return tokenpercentage;
	}

}
