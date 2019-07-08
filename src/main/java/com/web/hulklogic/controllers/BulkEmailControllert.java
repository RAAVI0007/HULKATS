package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.ApplicantInfo;
import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.BulkEmailSend;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.ApplicantService;
import com.web.hulklogic.service.ApplicationSettingsService;
import com.web.hulklogic.service.JobTitleService;
import com.web.hulklogic.service.SupportService;
import com.web.hulklogic.service.TemplateService;
import com.web.hulklogic.service.UserService;

@Controller
public class BulkEmailControllert {

	private static Logger logger = Logger.getLogger(BulkEmailControllert.class);

	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private UserService userservice;
	@Autowired
	private ServletContext context;
	@Autowired
	private JobTitleService jobtitleService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SupportService supportService;
	@Autowired
	private ApplicationSettingsService applicationSettingsService; 

	@RequestMapping(value = "/user/sendbulkemail", method = RequestMethod.POST)
	public @ResponseBody String sendEmailToApplicnation(HttpSession session,
			@RequestParam(value = "appIds[]", required = false) List<Integer> appIds,
			@RequestParam(value = "bulk", required = false) String subjectBodyStr) {

		logger.info("appIds:" + appIds);
		BulkEmailSend bulkEmailSend = new Gson().fromJson(subjectBodyStr, BulkEmailSend.class);
		String currentemail = (String) session.getAttribute("userName");
		  User user = applicationSettingsService.getpropertyname(currentemail);
		Integer templatedesignid = (Integer) session.getAttribute("templatedesignid");
		logger.info("userName:" + currentemail);
		String[] attachFiles = new String[4];
		String templateName = null;
		AutoSignature autoSignature = null;
		String filename = null;
		String pdffile = null;
		String pdffile1 = null;
		String result = "fail";
		CreateTemplate getTemplateNmae = null;
		List<String> bulkemailStr = templateService.getEmailByAppId(appIds);
		logger.info("bulkemailStr:" + bulkemailStr);
		if (bulkemailStr.size() > 0) {
			bulkEmailSend.setEmails(bulkemailStr);
		} else {
			return "fail";
		}
		try {

			java.util.Date todayDate = new java.util.Date();
			FileOutputStream fois1 = null;

			if (bulkEmailSend.getFileType() != null) {

				byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(bulkEmailSend.getTemplateDocument());

				if (bulkEmailSend.getFileType().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

					templateName = bulkEmailSend.getAttachment() + "_" + todayDate.getYear() + "_"
							+ todayDate.getMonth() + "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_"
							+ todayDate.getSeconds() + ".docx";

				} else {
					templateName = bulkEmailSend.getAttachment() + "_" + todayDate.getYear() + "_"
							+ todayDate.getMonth() + "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_"
							+ todayDate.getSeconds() + ".pdf";
				}
				bulkEmailSend.setCreateAttachment(templateName);
				
				File destinationFile1 = new File("/opt/hulkats/template/" + templateName);
				fois1 = new FileOutputStream(destinationFile1);
				fois1.write(documentdecode1);

				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				String filename1 = "/opt/hulkats/template/" + templateName;
				logger.info("file is getting from template folder:" + filename1);
				attachFiles[0] = filename1;
			}

			if (bulkEmailSend.getFileType1() != null) {
				logger.info("file is type:" + bulkEmailSend.getFileType1());

				byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(bulkEmailSend.getTemplateDocument1());

				if (bulkEmailSend.getFileType1()
						.equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

					templateName = bulkEmailSend.getAttachment1() + "_" + todayDate.getYear() + "_"
							+ todayDate.getMonth() + "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_"
							+ todayDate.getSeconds() + ".docx";

				} else {
					logger.info("pdf file ");
					templateName = bulkEmailSend.getAttachment1() + "_" + todayDate.getYear() + "_"
							+ todayDate.getMonth() + "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_"
							+ todayDate.getSeconds() + ".pdf";
				}

				bulkEmailSend.setCreateAttachment1(templateName);
				
				File destinationFile1 = new File("/opt/hulkats/template/" + templateName);
				fois1 = new FileOutputStream(destinationFile1);
				fois1.write(documentdecode1);

				try {
					if (fois1 != null)
						fois1.close();
				} catch (Exception e) {
					logger.error("Exception is: " + e);
				}

				
				String filename2 = "/opt/hulkats/template/" + templateName;
				logger.info("file is getting from template folder:" + filename2);
				attachFiles[1] = filename2;
			}

			if (bulkEmailSend.getAttachment2() != null) {

				
				String filename3 = "/opt/hulkats/template/" + bulkEmailSend.getAttachment2();
				logger.info("file is getting as docx :" + filename3);
				attachFiles[2] = filename3;
			}

			if (bulkEmailSend.getAttachment3() != null) {

				
				String filename4 = "/opt/hulkats/template/" + bulkEmailSend.getAttachment3();
				logger.info("file is getting as docx :" + filename4);
				attachFiles[3] = filename4;

			}

			autoSignature = templateService.geAutoSignatureByEmail(currentemail);
			if (autoSignature.getSightml() == null || autoSignature.getSightml() == "") {

				return "no signature";
			}
			bulkEmailSend.setCurrentemail(currentemail);
			bulkEmailSend.setTotalDocuments(attachFiles);
			bulkEmailSend.setTemplatedesignid(templatedesignid);
			bulkEmailSend.setPropertyname(user.getPropertyname());
			logger.info(bulkEmailSend.getTemplatedesignid());
			logger.info(bulkEmailSend.getEmails());
			boolean status = applicantService.sendBulkEmailToApplicant(bulkEmailSend, autoSignature);
			logger.info("status:" + status);
			if (status) {
				result = templateService.saveBulkEmailHistory(appIds, bulkEmailSend);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
			
		
		return result;
	}

	@RequestMapping(value = "/user/changeStatus", method = RequestMethod.POST)
	public @ResponseBody String updateStatus(HttpSession session, @RequestParam("application") String applicantStr) {
		ApplicantInfo applicant = new Gson().fromJson(applicantStr, ApplicantInfo.class);
		String currentemail = (String) session.getAttribute("userName");
		logger.info("currentemail:" + currentemail);
		Integer userid = (Integer) session.getAttribute("userid");
		logger.info("userid:" + userid);
		String roleName = (String) session.getAttribute("roleName");
		logger.info("roleName:" + roleName);
		String teamname = (String) session.getAttribute("teamname");
		logger.info("teamname:" + teamname);
		applicant.setEmail(currentemail);
		applicant.setUserid(userid);
		String sendAlertEmail = null;
		if ("ROLE_RECRUITER".equalsIgnoreCase(roleName)) {
			sendAlertEmail = supportService.managerEmailForSenderAlert(teamname);
			logger.info("sendAlertEmail:" + sendAlertEmail);
		} else {
			sendAlertEmail = supportService.recruiterEmailForSendAlert(applicant.getApp_id());
			logger.info("sendAlertEmail:" + sendAlertEmail);
		}
		applicant.setSenderEmail(sendAlertEmail);
		boolean status = applicantService.updateAndInsertStatus(applicant);

		if (status) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(value = "/manager/getStatusById", method = RequestMethod.POST)
	public @ResponseBody List<JobTitle> getStautsByApplication(
			@RequestParam(value = "app_id", required = false) int app_id) {
		logger.info("applicationid:" + app_id);
		List<JobTitle> liststatus = null;
		try {
			liststatus = applicantService.getStatusByApplicationid(app_id);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return liststatus;
	}
}
