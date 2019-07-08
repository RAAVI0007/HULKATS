package com.web.hulklogic.service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class AuthenticationService {

	private static Logger logger = Logger.getLogger(AuthenticationService.class);

	public boolean authenticate(String authCredentials) {
		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			logger.error(e);
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService
		boolean authenticationStatus = "hulkuser".equals(username) && "mZoBAT0x5SH1".equals(password);
		logger.info("authenticationStatus:" + authenticationStatus);
		return authenticationStatus;
	}
}
