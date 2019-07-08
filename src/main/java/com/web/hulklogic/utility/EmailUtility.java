package com.web.hulklogic.utility;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.QuoteMessages;
import com.web.hulklogic.entity.Sharejobs;
import com.web.hulklogic.entity.User;

public class EmailUtility {

	private static Logger logger = Logger.getLogger(EmailUtility.class);

	private static Properties prop = null;
	private static String from = null;
	private static String username = null;
	private static String password = null;
	private static String host = null;
	private static String subject = null;
	private static String serverUrl = null;
	private static String serverUrl1 = null;
	private static String serverUrl2 = null;
	private static String port = null;
	private static String imageUrl = null;
	static {
		if (null == prop) {
			try {
				prop = PropertiesLoaderUtils.loadAllProperties("mail.properties");
				from = prop.getProperty("fromMail");
				username = prop.getProperty("username");
				password = prop.getProperty("password");
				host = prop.getProperty("host");
				subject = prop.getProperty("subject");
				serverUrl = prop.getProperty("serverUrl");
				serverUrl1 = prop.getProperty("serverUrl1");
				serverUrl2 = prop.getProperty("serverUrl2");
				port = prop.getProperty("port");
				imageUrl = prop.getProperty("imageUrl");
			} catch (IOException e) {
				logger.error("Exception is: " + e);
			}
		}
	}

	public EmailUtility() {
		try {

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
	}

	public boolean sendreferrallink(GetEmployee getemployee) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + getemployee.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getemployee.getEmail()));

				// Set Subject: header field
				message.setSubject("New Job for referral");
				String msg = null;
				if (getemployee.getTemplatedesignid() == 1) {

					msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
							+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
							+ "<tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
							+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
							+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
							+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
							+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
							+ "line-height:135%;'>to</h2>"
							+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
							+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
							+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
							+ "<tr><td valign='top' class='textContent'>"
							+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
							+ ' ' + getemployee.getFirstname() + ' ' + getemployee.getLastname() + ',' + " </h3><br />"
							+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";

					msg += " A new job has been added, please refer friends and colleagues by following   <a href='"
							+ serverUrl2 + "'>login link.</a><br>";
					msg += "2. To login use your email id: <b>" + getemployee.getEmail() + "</b><br>";

					msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
					msg += "<b>Warm Regards,</b><br>";
					msg += "<b>Technical Support Team,</b><br>";
					msg += "<b>Hulklogic LLC.</b><br>";
					msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
					msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
							+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
							+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>"
							+ "<td valign='top'>"
							+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
							+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
							+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
							+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				} else if (getemployee.getTemplatedesignid() == 2) {
					msg += "<body style='margin:0; padding:0;' bgcolor='#eaeced'><table style='min-width:320px;' width='100%' cellspacing='0' cellpadding='0' bgcolor='#eaeced'>"
							+ "<tr><td class='hide'><table width='600' cellpadding='0' cellspacing='0' style='width:600px !important;'>"
							+ "<tr><td style='min-width:600px; font-size:0; line-height:0;'>&nbsp;</td>"
							+ "</tr></table></td></tr><tr><td class='wrapper' style='padding:0 10px;'>"
							+ "<table data-module='module-1'  width='100%' cellpadding='0' cellspacing='0'><tr>"
							+ "<td data-bgcolor='bg-module' bgcolor='#eaeced'><table class='flexible' width='600' align='center' style='margin:0 auto;' cellpadding='0' cellspacing='0'>"
							+ "<tr><td style='padding:29px 0 30px;'><table width='100%' cellpadding='0' cellspacing='0'><tr><th class='flex' width='113' align='center' style='padding:0;'>"
							+ "<table class='center' cellpadding='0' cellspacing='0'><tr><td style='line-height:0;'>"
							+ "<a target='_blank' style='text-decoration:none;' href='https://www.hulkats.com/'><img src=\' "
							+ imageUrl
							+ "\' border='0' style='font:bold 12px/12px Arial, Helvetica, sans-serif; color:#606060;' align='center' vspace='0' hspace='0' width='200' height='100' alt='hulkats.COM' /></div>"
							+ "</a></td></tr></table></th></tr></table></td></tr></table></td></tr></table>"
							+ "<table data-module='module-2'  width='100%' cellpadding='0' cellspacing='0'><tr>"
							+ "<td data-bgcolor='bg-module' bgcolor='#eaeced'><table class='flexible' width='600' align='center' style='margin:0 auto;' cellpadding='0' cellspacing='0'>"
							+ "<tr><td data-bgcolor='bg-block' class='holder' style='padding:58px 60px 52px;' bgcolor='#f9f9f9'>"
							+ "<table width='100%' cellpadding='0' cellspacing='0'><tr>"
							+ "<td data-color='title' data-size='size title' data-min='25' data-max='45' data-link-color='link title color' data-link-style='text-decoration:none; color:#292c34;' class='title' align='center' style='font:35px/38px Arial, Helvetica, sans-serif; color:#292c34; padding:0 0 24px;'>"
							+ "Hello" + ' ' + getemployee.getFirstname() + ' ' + getemployee.getLastname() + ','
							+ "+  </td></tr><tr>"
							+ "<td data-color='text' data-size='size text' data-min='10' data-max='26' data-link-color='link text color' data-link-style='font-weight:bold; text-decoration:underline; color:#40aceb;' align='center' style='font:bold 16px/25px Arial, Helvetica, sans-serif; color:#888; padding:0 0 23px;'>";
					msg += " A new job has been added, please refer friends and colleagues by following   <a href='"
							+ serverUrl2 + "'>login link.</a><br>";
					msg += "2. To login use your email id: <b>" + getemployee.getEmail() + "</b><br>"
							+ "</td></tr><tr><td style='padding:0 0 20px;'>";
					msg += "<b>Warm Regards,</b><br>";
					msg += "<b>Technical Support Team,</b><br>";
					msg += "<b>Hulklogic LLC.</b><br>";
					msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
					msg += "</td></tr></table></td></tr>" + "<tr><td height='28'></td></tr></table></td></tr></table>"
							+ "<table data-module='module-7'  width='100%' cellpadding='0' cellspacing='0'>"
							+ "<tr><td data-bgcolor='bg-module' bgcolor='#eaeced'>"
							+ "<table class='flexible' width='600' align='center' style='margin:0 auto;' cellpadding='0' cellspacing='0'>"
							+ "<tr><td class='footer' style='padding:0 0 10px;'>"
							+ "<table width='100%' cellpadding='0' cellspacing='0'>" + "<tr class='table-holder'>"
							+ "<th class='tfoot' width='400' align='left' style='vertical-align:top; padding:0;'>"
							+ "<table width='100%' cellpadding='0' cellspacing='0'>"
							+ "<tr><td data-color='text' data-link-color='link text color' data-link-style='text-decoration:underline; color:#797c82;' class='aligncenter' style='font:12px/16px Arial, Helvetica, sans-serif; color:#797c82; padding:0 0 10px;'>"
							+ "	HULKLOGIC TECHNOLOGIES PVT LTD. &nbsp; All Rights Reserved. "
							+ "</td></tr></table></th></tr></table></td></tr></table></td></tr></table></td></tr>"

							+ "<tr><td style='line-height:0;'><div style='display:none; white-space:nowrap; font:15px/1px courier;'>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div></td>"
							+ "</tr></table></body>";

				}
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendMail(User user, String[] details, String activate) {

		logger.info("activate:" + activate);

		try {
			logger.info("from:" + from);
			logger.info("mailId:" + user.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				if (activate.equalsIgnoreCase("activated")) {

					msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
							+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
							+ "<tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
							+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
							+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
							+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
							+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
							+ "line-height:135%;'>to</h2>"
							+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
							+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
							+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
							+ "<tr><td valign='top' class='textContent'>"
							+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
							+ ' ' + user.getFirstname() + ' ' + user.getLastname() + ',' + " </h3><br />"
							+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
					msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";
					msg += "1. Please click on this link to  <a href='" + serverUrl + details[1]
							+ "'>activate and choose the password.</a><br>";
					msg += "2. To login use your email id and the choose the password in above link: <b>"
							+ user.getEmail() + "</b><br>";
					msg += "<font color=red>* For security reasons we strongly recommend you to change your current password after login.</font> ";
					msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
					msg += "<b>Warm Regards,</b><br>";
					msg += "<b>Technical Support Team,</b><br>";
					msg += "<b>Hulklogic LLC.</b><br>";
					msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
					msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
							+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
							+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>"
							+ "<td valign='top'>"
							+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
							+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
							+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
							+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";

				} else {
					/*
					 * msg = "<b> Hello </b>"; msg += "<b>"+" "+
					 * user.getFirstname() +" "+user.getLastname()+','
					 * +"</b><br>"; msg += "<br>"; msg += "<b>Please  </b>"; msg
					 * += "<a href='"+serverUrl+details[1]+"'>click here</a>";
					 * msg += "<b> to reset your password.</b>"; msg += "<br>";
					 * 
					 * msg += "<br>"; msg += "<br>"; msg += "<br>"; msg +=
					 * "<b>Warm Regards,</b><br>"; msg +=
					 * "<b>Technical Support Team,</b><br>"; msg +=
					 * "<b>Hulklogic LLC.</b><br>"; msg +=
					 * "<b><a href=www.hulklogic.com>www.hulklogic.com</a></b><br>"
					 * ; msg +=
					 * "<font color=red>*Please don't reply to this message.</font>"
					 * ;
					 */
					msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
							+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
							+ "<tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
							+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
							+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
							+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
							+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
							+ "line-height:135%;'>to</h2>"
							+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
							+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
							+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
							+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
							+ "<tr><td valign='top' class='textContent'>"
							+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
							+ ' ' + user.getFirstname() + ' ' + user.getLastname() + ',' + " </h3><br />"
							+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
					msg += "<b>Please  </b>";
					msg += "<a href='" + serverUrl + details[1] + "'>click here</a>";
					msg += "<b> to reset your password.</b>";
					msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
							+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
							+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
					msg += "<b>Warm Regards,</b><br>";
					msg += "<b>Technical Support Team,</b><br>";
					msg += "<b>Hulklogic LLC.</b><br>";
					msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
					msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
							+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
							+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
							+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>"
							+ "<td valign='top'>"
							+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
							+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
							+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
							+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";

				}

				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendapplicantMail(Applicants applicant) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(applicant.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				/*
				 * msg = "<b>Welcome</b>"; msg += "<b>"+" "
				 * +applicant.getFirstname()+' '+ applicant.getLastname()+','
				 * +"</b><br>"; msg += "<br>"; msg += "<br>"; msg +=
				 * "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>"
				 * ;
				 * 
				 * msg +=
				 * " To login use your email id and the current password: <b>"+
				 * applicant.getPassword() + "</b><br>"; msg +=
				 * "<font color=red>* For security reasons we strongly recommend you to change your current password after login.</font> "
				 * ; msg += "<br>"; msg += "<br>"; msg += "<br>"; msg +=
				 * "<b>Warm Regards,</b><br>"; msg +=
				 * "<b>Technical Support Team,</b><br>"; msg +=
				 * "<b>Hulklogic LLC.</b><br>"; msg +=
				 * "<b><a href=www.hulklogic.com>www.hulklogic.com</a></b>";
				 */
				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
						+ ' ' + applicant.getFirstname() + ' ' + applicant.getLastname() + ',' + " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";

				msg += " To login use your email id and the current password: <b>" + applicant.getPassword()
						+ "</b><br>";
				msg += "<font color=red>* For security reasons we strongly recommend you to change your current password after login.</font> ";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";

				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendbussinesscard(CardDetails carddetails) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + carddetails.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(carddetails.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Dear "
						+ ' ' + carddetails.getFirstname() + ' ' + carddetails.getLastname() + ',' + " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Your Business card details are as follows:  </b><br>";
				msg += "<b>Name: " + ' ' + carddetails.getFirstname() + ' ' + carddetails.getLastname() + "</b><br>";
				msg += "<b>Emailid: " + ' ' + carddetails.getEmail() + "</b><br>";
				msg += "<b>Mobile No: " + ' ' + carddetails.getContactno() + ' ' + carddetails.getLastname()
						+ "</b><br>";
				msg += "<b>Place/Location: " + ' ' + carddetails.getCity() + '/' + carddetails.getCountry()
						+ "</b><br>";
				msg += "<b>Zipcode: " + ' ' + carddetails.getPincode() + "</b><br>";
				msg += "<b>Designation: " + ' ' + carddetails.getDesignation() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendApplicantMail(Applicants createApplicants) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + createApplicants.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(createApplicants.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
						+ ' ' + createApplicants.getFirstname() + ' ' + createApplicants.getLastname() + ','
						+ " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";

				msg += "  To login use your email id and the current password: <b>" + createApplicants.getPassword()
						+ "</b><br>";
				msg += "<font color=red>* For security reasons we strongly recommend you to change your current password after login.</font> ";
				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendcontactMail(QuoteMessages quote) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + quote.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				String[] toEmails = { "contact@hulklogic.com", "debu.upadhyay@hulklogic.com", };
				for (int i = 0; i < toEmails.length; i++) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(toEmails[i]));
				}

				// Set Subject: header field
				message.setSubject("Customer Asking Quote for " + quote.getProduct());
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Hello  </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Contact Person Details are as follows:  </b><br>";
				msg += "<b>Name: " + ' ' + quote.getName() + "</b><br>";
				msg += "<b>Emailid: " + ' ' + quote.getEmail() + "</b><br>";
				msg += "<b>Mobile No: " + ' ' + quote.getContactno() + "</b><br>";
				msg += "<b>Timeslot: " + ' ' + quote.getTimeslot() + "</b><br>";
				msg += "<b>Organization: " + ' ' + quote.getOrganization() + "</b><br>";
				msg += "<b>Location: " + ' ' + quote.getLocation() + "</b><br>";
				msg += "<b>Subject: " + ' ' + quote.getSubject() + "</b><br>";
				msg += "<b>Message: " + ' ' + quote.getMessage() + "</b><br>";
				msg += "<b>Product: " + ' ' + quote.getProduct() + "</b><br>";
				msg += "<b>Volume: " + ' ' + quote.getVolume() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href=www.hulklogic.com>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendcontMail(QuoteMessages quote) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + quote.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				String[] toEmails = { "sales@hulklogic.com",
						"joy@hulklogic.com","madhava.rao@hulklogic.com" };
				
				for (int i = 0; i < toEmails.length; i++) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(toEmails[i]));
				}

				// Set Subject: header field
				message.setSubject("Customer wants to get information ");
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Hello  </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Contact Person Details are as follows:  </b><br>";
				msg += "<b>Name: " + ' ' + quote.getName() + "</b><br>";
				msg += "<b>Emailid: " + ' ' + quote.getEmail() + "</b><br>";
				msg += "<b>Mobile No: " + ' ' + quote.getContactno() + "</b><br>";
				msg += "<b>Timeslot: " + ' ' + quote.getTimeslot() + "</b><br>";
				msg += "<b>Organization: " + ' ' + quote.getOrganization() + "</b><br>";
				msg += "<b>Location: " + ' ' + quote.getLocation() + "</b><br>";
				msg += "<b>Subject: " + ' ' + quote.getSubject() + "</b><br>";
				msg += "<b>Message: " + ' ' + quote.getMessage() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendapplicantMail(Applicants applicant, String[] details, String activate) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(applicant.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
						+ ' ' + applicant.getFirstname() + ' ' + applicant.getLastname() + ',' + " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";

				msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";
				msg += "1. Please click on this link to choose password  <a href='" + serverUrl1 + details[1]
						+ "'>activate.</a><br>";
				msg += "2. To login use your email id and create password in above link: <b>" + applicant.getEmail()
						+ "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";

				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sendApplicantMail(Applicants createApplicants, String[] details, String activate) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + createApplicants.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(createApplicants.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
						+ ' ' + createApplicants.getFirstname() + ' ' + createApplicants.getLastname() + ','
						+ " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";
				msg += "1. Please click on this link to choose password  <a href='" + serverUrl1 + details[1]
						+ "'>activate.</a><br>";
				msg += "2. To login use your email id and create password in above link: <b>"
						+ createApplicants.getEmail() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean sharejob(Sharejobs sharejob) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + sharejob.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sharejob.getEmail()));

				// Set Subject: header field
				message.setSubject(subject);

				// Set Subject: header field
				message.setSubject(sharejob.getName().toUpperCase().concat(" send job link url"));
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Hello  </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>" + sharejob.getName() + " send job link  url:  </b><br>";
				msg += "<b>URL: " + ' ' + sharejob.getUrl() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href=www.hulklogic.com>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean senddemomessage(QuoteMessages quote) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + quote.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				String[] toEmails = { "contact@hulklogic.com", "debu.upadhyay@hulklogic.com", "joy@hulklogic.com",
						"madhava.rao@hulklogic.com" };
				for (int i = 0; i < toEmails.length; i++) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(toEmails[i]));
				}

				// Set Subject: header field
				message.setSubject("Customer scheduled demo on " + quote.getTimeslot());
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "

						+ " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>Contact Person Details are as follows:  </b><br>";
				msg += "<b>Name: " + ' ' + quote.getName() + "</b><br>";
				msg += "<b>Emailid: " + ' ' + quote.getEmail() + "</b><br>";
				msg += "<b>Timeslot: " + ' ' + quote.getTimeslot() + "</b><br>";
				msg += "<b>Organization: " + ' ' + quote.getOrganization() + "</b><br>";
				msg += "<b>Mobile Number: " + ' ' + quote.getContactno() + "</b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}

	public boolean senddemomessagetocustomer(QuoteMessages quote) {
		try {
			logger.info("from:" + from);
			logger.info("mailId:" + quote.getEmail());

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(quote.getEmail()));

				// Set Subject: header field

				// Set Subject: header field
				message.setSubject("Thank you for requesting a demo");
				String msg = null;

				msg = "<body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0'><center style='background-color:#E1E1E1;'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable' style='table-layout: fixed;max-width:100% !important;width: 100% !important;min-width: 100% !important;'><tr><td align='center' valign='top' id='bodyCell'>"
						+ "<table bgcolor='#FFFFFF'  border='0' cellpadding='0' cellspacing='0' width='500' id='emailBody'><tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='color:#FFFFFF;' bgcolor='#FFFFFF'>"
						+ "<tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr>"
						+ "<td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr><td align='center' valign='top' class='textContent'>"
						+ "<h1 style='color:#FFA500;line-height:100%;font-family:Helvetica,Arial,sans-serif;font-size:30px;"
						+ "font-weight:normal;margin-bottom:5px;text-align:center;'>WELCOME</h1>"
						+ "<h2 style='text-align:center;font-weight:normal;font-family:Helvetica,Arial,sans-serif;font-size:23px;margin-bottom:10px;color:#000;"
						+ "line-height:135%;'>to</h2>"
						+ "<div style='text-align:center;font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#FFFFFF;line-height:135%;'>"
						+ "<img src=\' " + imageUrl + "\' style='width: 300px;margin-left: 55px;' /></div>"
						+ "</td></tr></table></td></tr></table></td></tr></table></td></tr><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%' bgcolor='#F8F8F8'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td align='center' valign='top' width='500' class='flexibleContainerCell'><table border='0' cellpadding='30' cellspacing='0' width='100%'>"
						+ "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'>"
						+ "<tr><td valign='top' class='textContent'>"
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Welcome "
						+ ' ' + quote.getName() + ',' + " </h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>This email is to convey our sincere thanks to you. We guarantee a Hulklogician will get back to you with in 24 hours to share a meeting link.  </b><br>";

				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b>Technical Support Team,</b><br>";
				msg += "<b>Hulklogic LLC.</b><br>";
				msg += "<b><a href='www.hulklogic.com'>www.hulklogic.com</a></b>";
				msg += "</td></tr></table></td></tr></table></td></table></td></tr></table></td>"
						+ "</tr></table></td></tr><tr><td align='center' valign='top'></td></tr><tr><td align='center' valign='top'>"
						+ "</td> </tr></table><table bgcolor='#E1E1E1' border='0' cellpadding='0' cellspacing='0' width='500' id='emailFooter'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='500' class='flexibleContainer'><tr><td align='center' valign='top' width='500' class='flexibleContainerCell'>"
						+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>" + "<td valign='top'>"
						+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
						+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
						+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
						+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";
				message.setContent(msg, "text/html");
				Transport.send(message);
				logger.info("Sent mail successfully....");
				return true;
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
		return false;
	}
	
	

}
