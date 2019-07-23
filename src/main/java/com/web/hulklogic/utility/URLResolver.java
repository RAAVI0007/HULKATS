package com.web.hulklogic.utility;

import javax.servlet.http.HttpServletRequest;

public class URLResolver {

	public static String getBaseURl(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    url.append(":").append(serverPort);
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
	
}
