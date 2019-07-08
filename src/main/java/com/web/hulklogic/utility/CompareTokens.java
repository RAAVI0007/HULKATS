package com.web.hulklogic.utility;

import org.apache.log4j.Logger;

public class CompareTokens {
	private static Logger logger = Logger.getLogger(CompareTokens.class);

	public String[] compareJobAndApplicant(String tokens) {
		logger.debug("compare Job Applicant tokens");

		String token = " ";

		String[] words = tokens.split(",");
		int wrc = 1;

		/*
		 * for (int i = 0; i < words.length; i++) { /*for (int j = i + 1; j <
		 * words.length; j++) {
		 * 
		 * if (words[i].equals(words[j])) { wrc = wrc + 1; words[j] = "0"; } }
		 * if (words[i] != "0")
		 * 
		 * token += " "; token += words[i]; wrc = 1;
		 * 
		 * }
		 */
		return words;
	}

}
