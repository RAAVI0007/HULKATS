package com.web.hulklogic.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

	@RequestMapping(value = "/admin/admindashboard", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/admindashboard");
		return model;
	}
	
	@RequestMapping(value = "/admin/features", method = RequestMethod.GET)
	public ModelAndView featuressettings() {
		ModelAndView model = new ModelAndView();
		model.setViewName("featuressettings");
		return model;
	}
	
	@RequestMapping(value = "/admin/emailsettings", method = RequestMethod.GET)
	public ModelAndView adminemailsettings() {
		ModelAndView model = new ModelAndView();
		model.setViewName("companyemail");
		return model;
	}
	
	@RequestMapping(value = "/user/profilepage", method = RequestMethod.GET)
	public ModelAndView profile(HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		ModelAndView model = new ModelAndView();
		model.setViewName(path +"/profile");
		return model;
	}
	
	@RequestMapping(value = "/login/createpassword", method = RequestMethod.GET)
	public ModelAndView createpassword() {
		ModelAndView model = new ModelAndView();
		model.setViewName("createpassword");
		return model;
	}
	
	@RequestMapping(value = "/user/stripepayment", method = RequestMethod.GET)
	public ModelAndView stripepaymentgateway() {
		ModelAndView model = new ModelAndView();
		model.setViewName("stripepayment");
		return model;
	}

	@RequestMapping(value = "/manager/managerdashboard", method = RequestMethod.GET)
	public ModelAndView managerDashboard(HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		ModelAndView model = new ModelAndView();
		model.setViewName(path + "/managerdashbord");
		return model;
	}

	@RequestMapping(value = "/recruiter/recruiterdashboard", method = RequestMethod.GET)
	public ModelAndView recruiterDashboard(HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		ModelAndView model = new ModelAndView();
		model.setViewName(path + "/recruiterdashboard");
		return model;
	}
	
	
	
	@RequestMapping(value = "/admin/adminregistration", method = RequestMethod.GET)
	public ModelAndView adminregister(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("admindashboard");
		return model;
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hulk() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login_hulk");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView hulklogic() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login_hulk");
		return model;
	}
	// /login

	@RequestMapping(value = "/manager/register", method = RequestMethod.GET)
	public ModelAndView register(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		model.setViewName(path+"/registerpage");
		return model;
	}
	
	@RequestMapping(value = "/manager/sourcesettings", method = RequestMethod.GET)
	public ModelAndView sourcesettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		model.setViewName(path+"/statussettings");
		return model;
	}

	@RequestMapping(value = "/login/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetpassword(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("resetpwd");
		return model;
	}

	@RequestMapping(value = "/manager/appHistory", method = RequestMethod.GET)
	public String applicationHistory(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/applicationHistory";
	}

	@RequestMapping(value = "/user/jobcreation", method = RequestMethod.GET)
	public String jobcreation(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/jobcreationpage";
	}

	@RequestMapping(value = "/manager/joblist", method = RequestMethod.GET)
	public String joblist(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/jobslist";
	}

	@RequestMapping(value = "/manager/users", method = RequestMethod.GET)
	public String users(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/userspage";
	}

	@RequestMapping(value = "/user/applicants", method = RequestMethod.GET)
	public String applicants(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/applicantcreation";
	}

	@RequestMapping(value = "/user/applicantslist", method = RequestMethod.GET)
	public String applicantslist(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/applicantslist";
	}

	@RequestMapping(value = "/manager/applications", method = RequestMethod.GET)
	public String application(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/application";
	}

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String changepassword(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/changePwdPage";
	}

	@RequestMapping(value = "/manager/activation", method = RequestMethod.GET)
	public String activation(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/activated";
	}

	@RequestMapping(value = "/recruiter/dashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/recruiterdashboard";
	}

	@RequestMapping(value = "/user/editdetails", method = RequestMethod.GET)
	public String edit(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/Editdetails";
	}

	@RequestMapping(value = "/user/template", method = RequestMethod.GET)
	public String template(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/Template";
	}

	@RequestMapping(value = "/user/templateview", method = RequestMethod.GET)
	public String templatelist(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/Templatelist";
	}

	@RequestMapping(value = "/manager/settings", method = RequestMethod.GET)
	public String setting(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/Settings";
	}

	@RequestMapping(value = "/manager/commenthistory", method = RequestMethod.GET)
	public String commhistory(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/applicanthistory";
	}

	@RequestMapping(value = "/user/applicationsettings", method = RequestMethod.GET)
	public String applicationsettings(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/applicationsettings";
	}

	@RequestMapping(value = "/user/selectapplicant", method = RequestMethod.GET)
	public String selectapplicant(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/Selectapplicant";
	}

	@RequestMapping(value = "/recruiter/rssettings", method = RequestMethod.GET)
	public String recruiterSettings(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/rssetting";
	}

	@RequestMapping(value = "/recruiter/teams", method = RequestMethod.GET)
	public String recruitersTeams(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/teammembers";
	}

	@RequestMapping(value = "/recruiter/jobview", method = RequestMethod.GET)
	public String recruitersjob(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/recjobslist";
	}

	@RequestMapping(value = "/recruiter/application", method = RequestMethod.GET)
	public String recruitersapplication(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/recapplication";
	}

	@RequestMapping(value = "/user/support", method = RequestMethod.GET)
	public String support(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/support";
	}

	@RequestMapping(value = "/user/alerts", method = RequestMethod.GET)
	public String alert(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/alertlist";
	}

	@RequestMapping(value = "/user/showmessages", method = RequestMethod.GET)
	public String messages(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/showmessages";
	}

	@RequestMapping(value = "/user/allmessages", method = RequestMethod.GET)
	public String allmessages(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/allmessages";
	}

	@RequestMapping(value = "/recruiter/allmessages", method = RequestMethod.GET)
	public String recallmessages(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/recallmsg";
	}

	@RequestMapping(value = "/user/alertjob", method = RequestMethod.GET)
	public String alertjob(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/alertjob";
	}

	@RequestMapping(value = "/user/alertapplicant", method = RequestMethod.GET)
	public String alertapplicant(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/alertapplicant";
	}

	@RequestMapping(value = "/login/registration", method = RequestMethod.GET)
	public String registration(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		System.out.println("hello");
		return path + "/registration";
	}

	@RequestMapping(value = "/login/success", method = RequestMethod.GET)
	public String success(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/successmsg";
	}

	@RequestMapping(value = "/login/uploading", method = RequestMethod.GET)
	public String upload(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/uploading";
	}
	
	
	@RequestMapping(value = "/basicmanager/dashboard", method = RequestMethod.GET)
	public String basicdashboard(ModelMap model, HttpSession session) {
		String organization = (String) session.getAttribute("organization");
		String path = organization.toLowerCase();
		return path + "/dashboard";
	}
}
