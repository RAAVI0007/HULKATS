package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.EditDetailsService;
import com.web.hulklogic.service.GetDetailsService;
import com.web.hulklogic.service.UserService;


@Controller
@Scope("session")
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	 UserService userService;
	@Autowired
	 private  GetDetailsService  getDetailsService;
	@Autowired
	 private ApplicantService applicantservice;
	@Autowired
	private EditDetailsService editDetailService;


	
	@RequestMapping(value = "/login/forgetpwd", method = RequestMethod.GET)
	public String forgetPassword(ModelMap model) {
		return "forgetPwdPage";
	}
	
	@RequestMapping(value = "/login/loginFailed", method = RequestMethod.GET)
	public String loginFailed(Model map, HttpSession session) {
		logger.info("********************** loginFailed() ****************************END");
       return "login_hulk";
}
	

	@RequestMapping(value = "/login/loginPass", method = RequestMethod.GET)
	public String loginPass(Model map,HttpSession session,  HttpServletRequest req) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		req.getSession().setAttribute("userName", userName);
		session.setAttribute("userName", userName);
		Collection<? extends GrantedAuthority> roleNames = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 logger.info("roleNames:"+roleNames);
		String roleName = "";
		for (GrantedAuthority role : roleNames) {
			roleName = role.getAuthority();
			 logger.info("roleName:"+roleName);
			break;
		}
		
		User user = userService.getUserByEmail(userName);
		req.getSession().setAttribute("userid", user.getUserid());
		req.getSession().setAttribute("teamname", user.getTeamname());
		req.getSession().setAttribute("roleName",roleName);
		req.getSession().setAttribute("teamname1", user.getTeamname1());
		req.getSession().setAttribute("firstname", user.getFirstname());
		req.getSession().setAttribute("lastname", user.getLastname());
		req.getSession().setAttribute("mobile",user.getContactno());
	    logger.info("teamid"+user.getTeamname());
	    logger.info("userid"+user.getUserid());
	    
		String retstr = "";
		if("ROLE_MANAGER".equalsIgnoreCase(roleName)){
			
			retstr="redirect:../manager/dashBord";
		}
		else if("ROLE_RECRUITER".equalsIgnoreCase(roleName)){
			retstr="redirect:../recruiter/dashboard";
		}
		return retstr;
		}
	
	@RequestMapping(value = "/login/forgetPwd", method = RequestMethod.POST)
	 public @ResponseBody String sendPwdLink(@RequestParam("mailId") String mailId, Model map) {
	  logger.info("mailId:"+mailId);
	  User user = userService.getUserByEmail(mailId);
	  if(user ==null){
	   return "nouser";
	  }else if(user.getStatus()==0){
	   return "disabled";
	  }else{
	   logger.info("userid:"+user.getUserid());
	   userService.sendForgetPwdLink(user);
	   return "success";
	  }
	 }
  
	@RequestMapping(value="/user/updatePassword",method=RequestMethod.POST)
	 public @ResponseBody String updatePassword(HttpSession session,@RequestParam(value="oldPassword",required=false) String oldPassword,
	          @RequestParam(value="newPassword",required=false) String newPassword) {
	  logger.info("oldPassword: "+oldPassword);
	  logger.info("newPassword: "+newPassword);
	  String currentEmail = (String)session.getAttribute("userName");
	  logger.info("currentEmail: "+currentEmail);
	  
	  String result=null;
	  try{
	   result=getDetailsService.updatePassword(currentEmail, oldPassword, newPassword);

	  }catch(Exception e){
		  logger.error("Exception is: "+e);
	  }
	  return result;
	 }
	
	@RequestMapping(value="/user/logosetting",method=RequestMethod.POST)
	 public @ResponseBody Customer logosetting(HttpSession session) {
	  String logourl = (String)session.getAttribute("logourl");
	  logger.info("currentEmail: "+logourl);
	  Customer customer = new Customer();
	  
	  File destinationFile = new File("/opt/hulkats/images/"  + logourl);
	
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(destinationFile);
			byte[] bytes = new byte[(int) destinationFile.length()];
			fileInputStreamReader.read(bytes);
			String encodedupload1 = new String(Base64.encodeBase64(bytes), "UTF-8");
			System.out.println(encodedupload1);
			customer.setLogoencoded(encodedupload1);
			customer.setLogoname(logourl);
			if (fileInputStreamReader != null)
				fileInputStreamReader.close();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
	  return customer;
	 }
	
	@RequestMapping(value = "/login/changepwd", method = RequestMethod.POST)
	 public @ResponseBody String changePwd(@RequestParam("id") int id,@RequestParam("pwd") String pwd,Model map) {
	  logger.info("id:"+id);
	  logger.info("pwd:"+pwd);
	  if(id>0 && pwd!=null){
	   int count = applicantservice.updatepassword(id, pwd);
	   if(count>0){
	    return "success";
	   }
	  }
	  return "fail";
	 }
	
	@RequestMapping(value = "/login/createpwd", method = RequestMethod.POST)
	 public @ResponseBody String createPwd(@RequestParam("id") int id,@RequestParam("pwd") String pwd,Model map) {
	  logger.info("id:"+id);
	  logger.info("pwd:"+pwd);
	  if(id>0 && pwd!=null){
	   int count = applicantservice.createpassword(id, pwd);
	   if(count>0){
	    return "success";
	   }
	  }
	  return "fail";
	 }
	
		
	
	@RequestMapping(value = "/login/activate/{id}", method = RequestMethod.GET)
	  public String activate(ModelMap model,@PathVariable int id) {
	   logger.info("id: "+id);
	   if(id>0){ 
	    User user = getDetailsService.getUserByUserId(id);
	    if(null!=user){
	     try {
	      int count=getDetailsService.enableUser(id);
	      logger.info("count: "+count);
	      if(count>0){
	       model.addAttribute("activationDone", "Hi "+user.getFirstname()+", Account has been activated. Please ");  
	       return "activated";
	      } 
	     } catch (Exception e) {
	    	 logger.error("Exception is: "+e);
	     }
	    }
	   }
	   return "nouser";  
	  }
	
	@RequestMapping(value = "/login/applicantactivate/{id}", method = RequestMethod.GET)
	  public String applicantactivate(ModelMap model,@PathVariable int id) {
	   logger.info("id: "+id);
	   if(id>0){ 
		   Applicants applicant = getDetailsService.getApplicantsByApplicatId(id);
	  
	    if(null!=applicant){
	     try {
	      int count=getDetailsService.enableApplicant(id);
	      logger.info("count: "+count);
	      if(count>0){
	       model.addAttribute("activationDone", "Hi "+applicant.getFirstname()+", Account has been activated. Please ");  
	       return "activated";
	      } 
	     } catch (Exception e) {
	    	 logger.error("Exception is: "+e);
	     }
	    }
	   }
	   return "nouser";  
	  }
	
}


