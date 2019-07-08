package com.web.hulklogic.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.Customer;
import com.web.hulklogic.entity.EmailSettings;
import com.web.hulklogic.entity.Features;
import com.web.hulklogic.entity.User;
import com.web.hulklogic.service.CustomerService;
import com.web.hulklogic.service.EditDetailsService;
import com.web.hulklogic.service.GetDetailsService;
import com.web.hulklogic.service.UserService;

@Controller
public class CustomerRegistration {

	private static Logger logger = Logger.getLogger(CustomerRegistration.class);
	@Autowired
	CustomerService customerService;
	@Autowired
	private ServletContext context;
	@Autowired
	private UserService userService;
	@Autowired
	private GetDetailsService getDetailsService;
	@Autowired
	private EditDetailsService editDetailService;

	@RequestMapping(value = "/admin/customerregistration", method = RequestMethod.POST)
	public @ResponseBody String saveUser(HttpSession session, @RequestParam("customerInfo") String customerStr,
			Model map) {
		if (null == customerStr || customerStr.trim().isEmpty()) {
			return "failure";
		}

		Customer customer = new Gson().fromJson(customerStr, Customer.class);
		boolean status = customerService.getEmailCheck(customer.getEmail());

		if (status) {
			return "Email is already registered! Please choose another one";
		}

		boolean statusconcat = customerService.getContactCheck(customer.getContactno());

		if (statusconcat) {
			return "Mobile Number is registered! Please choose another one";
		}

		if (null != customer.getEmail() && !customer.getEmail().trim().isEmpty()) {

			try {
				Date todayDate = new Date();

				if (customer.getLogoname() != null) {

					byte[] documentdecode1 = DatatypeConverter.parseBase64Binary(customer.getLogoencoded());

					String document1 = customer.getLogoname() + "_" + todayDate.getYear() + "_" + todayDate.getMonth()
							+ "_" + todayDate.getDay() + "_" + todayDate.getHours() + "_" + todayDate.getSeconds() + "."
							+ customer.getLogotype();
					File destinationVideo = new File("/opt/hulkats/images/" + document1);

					FileOutputStream foisvideo = new FileOutputStream(destinationVideo);

					foisvideo.write(documentdecode1);
					customer.setLogoname(document1);
					logger.info(document1);
				} else {
					customer.setLogoname("NA");

				}

				String org = customer.getOrganization().toLowerCase().replace(' ', '-');
				customer.setOrganization(org);
				boolean status_Of_DB = customerService.saveCustomer(customer);
				System.out.println(status_Of_DB);
				if (status_Of_DB) {

					User getUser = userService.getUserByEmail(customer.getEmail());
					if (getUser.getUserid() != null) {
						String scriptName = "/opt/hulkats/sample.sh";
						String commands[] = new String[] { scriptName, org };

						Runtime rt = Runtime.getRuntime();
						Process process = null;
						try {
							process = rt.exec(commands);
							process.waitFor();
							StringBuffer output = new StringBuffer();
							BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String line = "";
							while ((line = reader.readLine()) != null) {
								output.append(line + "\n");
							}
							System.out.println("### " + output);
						} catch (Exception e) {
							e.printStackTrace();
						}
						boolean linkStatus = userService.sendActivationLink(getUser);
						logger.info("linkStatus:" + linkStatus);
						if (!linkStatus) {
							int deleterecord = editDetailService.deleteUser(getUser.getUserid());
							logger.info("deleterecord:" + deleterecord);
							return "email";
						}
						return "success";
					}
					return "nouser";

				} else {
					return "user exists";
				}

			} catch (Exception e) {
				logger.error("Exception is: " + e);

			}
		}
		return "fail";
	}

	/*
	 * @RequestMapping(value = "/manager/uploadContractDocument/", method =
	 * RequestMethod.POST) public @ResponseBody String
	 * uploadContractDocument(HttpSession
	 * session,@RequestParam("contractDocument") String contractDocument) { int
	 * userid= (Integer)session.getAttribute("userid");
	 * logger.info("userid:"+userid); String result="fail"; try {
	 * 
	 * //String
	 * filename="/home/mahava/contractDocument/SOFTWARELICENSEAGREEMENT.pdf";
	 * String filename=("D:\\documents\\")+"SOFTWARELICENSEAGREEMENT.docx";
	 * byte[] byteArrayContractDocument
	 * =Base64.getDecoder().decode(contractDocument);
	 * Files.write(Paths.get(filename), byteArrayContractDocument); int
	 * dbResult=getDetailsService.enableUser(userid); if(dbResult==1) { return
	 * "successup"; }
	 * 
	 * }catch(Exception e) { logger.error("Exception is: "+e);
	 * 
	 * } return result; }
	 */

	@RequestMapping(value = "/login/uploadContractDocument", method = RequestMethod.POST)
	public @ResponseBody String uploadContractDocument(@RequestParam("customer") String customerStr) {

		Customer customer = new Gson().fromJson(customerStr, Customer.class);
		String result = "fail";
		try {

			logger.info("file type:" + customer.getDocType());

			String encoded = customer.getDocuname();
			String documentName = null;

			if (customer.getDocType().equals("vnd.openxmlformats-officedocument.wordprocessingml.document")) {

				documentName = encoded + ".docx";

			}

			documentName = encoded + ".pdf";

			logger.info("document name:" + documentName);

			String filename = "/home/madhava/contractDocument/" + documentName;
			// String
			// filename=("C:\\Users\\user\\Desktop\\KUMAR\\")+documentName;

			byte[] byteArrayContractDocument = Base64.getDecoder().decode(customer.getEncodeDocument());

			logger.info("byteArrayContractDocument name:" + byteArrayContractDocument);

			FileOutputStream stream = new FileOutputStream(filename);
			try {
				stream.write(byteArrayContractDocument);
			} finally {
				stream.close();
			}

			int dbResult = getDetailsService.enableUser(customer.getCustomerid());
			if (dbResult == 1) {
				return "success";
			}

		} catch (Exception e) {
			logger.error("Exception is: " + e);

		}
		return result;
	}

	@RequestMapping(value = "/admin/getCompanies", method = RequestMethod.POST)
	public @ResponseBody List<User> getJobTitle(HttpSession session) {

		List<User> listCompany = null;
		try {

			listCompany = userService.getCompanyList();
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return listCompany;
	}

	@RequestMapping(value = "/admin/emailsettingscreation", method = RequestMethod.POST)
	public @ResponseBody String emailsettingscreation(HttpSession session,
			@RequestParam("emailcreation") String emailStr) {

		if (null == emailStr || emailStr.trim().isEmpty()) {
			return "failure";
		}

		EmailSettings emailsettings = new Gson().fromJson(emailStr, EmailSettings.class);
		String property = null;
		Properties prop = new Properties();
		property = emailsettings.getOrganization().toLowerCase() + ".properties";
		prop.setProperty("host",     emailsettings.getHost());
		prop.setProperty("port",     emailsettings.getPort());
		prop.setProperty("username", emailsettings.getUsername());
		prop.setProperty("password", emailsettings.getPassword());
		prop.setProperty("fromMail", emailsettings.getUsername());
		File destinationVideo = new File("/opt/hulkats/propertiesfiles/" + property);
		FileOutputStream foisvideo = null;
		try {
			foisvideo = new FileOutputStream(destinationVideo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.store(foisvideo, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result = customerService.saveproperty(emailsettings.getOrganization(), property);
		if (result == true) {
			return "success";
		} else {

			return "fail";
		}

	}

	@RequestMapping({ "/admin/addons" })
	public String addons(Model map, @RequestParam(value = "companyname", required = false) String companyname) {
		String addons = "1";

		return addons;
	}

	@RequestMapping(value = "/admin/featuressettings", method = RequestMethod.POST)
	public @ResponseBody String featuressettings(HttpSession session, @RequestParam("featuresInfo") String customerStr,
			Model map) {
		Features features = new Gson().fromJson(customerStr, Features.class);
		boolean result = customerService.savefeatures(features);
		if (result == true) {
			return "success";
		} else {

			return "fail";
		}
		
		
	}
	
	@RequestMapping(value = "/admin/getfeatures", method = RequestMethod.POST)
	public @ResponseBody Features getfeatures(@RequestParam(value = "companyname", required = false) String companyname) {

		Features listCompany = null;
		try {

			listCompany = customerService.getfeatures(companyname);
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return listCompany;
	}

}
