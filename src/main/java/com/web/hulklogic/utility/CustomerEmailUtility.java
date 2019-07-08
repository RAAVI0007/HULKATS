package com.web.hulklogic.utility;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.web.hulklogic.entity.Customer;

public class CustomerEmailUtility {

	private static Logger logger = Logger.getLogger(CustomerEmailUtility.class);

	private static Properties prop = null;
	private static String from = null;
	private static String username = null;
	private static String password = null;
	private static String host = null;
	private static String subject = null;
	private static String serverUrl = null;
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
				port = prop.getProperty("port");
				imageUrl = prop.getProperty("imageUrl");
			} catch (IOException e) {
				logger.error("Exception is: " + e);
			}
		}
	}

	public CustomerEmailUtility() {
		try {

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
	}

	public boolean sendMail(Customer customer, String[] details, String activate) {

		logger.info("activate:" + activate);

		try {
			logger.info("from:" + from);
			logger.info("mailId:" + customer.getEmail());

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
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail()));

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
							+ ' ' + customer.getFirstname() + ' ' + customer.getLastname() + ',' + " </h3><br />"
							+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
					msg += "<b>Your Hulkats account has been created successfully and to access your account:  </b><br>";
					msg += "1. Please click on this link to  <a href='" + serverUrl + details[1]
							+ "'>activate and choose the password.</a><br>";
					msg += "2. To login use your email id and the choose the password in above link: <b>" + customer.getEmail()
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
							+ "<table border='0' cellpadding='30' cellspacing='0' width='100%'><tr>"
							+ "<td valign='top'>"
							+ "<div style='font-family:Helvetica,Arial,sans-serif;font-size:13px;color:#828282;text-align:center;line-height:120%;'>"
							+ "<div>Copyright &#169; 2018 <a href='http://www.hulklogic.com' target='_blank' style='text-decoration:none;color:#828282;'><span"
							+ "style='color:#828282;'>HULKLOGIC LLC</span></a>. All&nbsp;rights&nbsp;reserved.</div></div></td>"
							+ "</tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></body>";

				} else {
					
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
							+ ' ' + customer.getFirstname() + ' ' + customer.getLastname() + ',' + " </h3><br />"
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


	public boolean sendDocumentToEmail(Customer customer, String link) {
		try {

			logger.info("mailId:" + customer.getEmail());

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
				message.setFrom(new InternetAddress(username));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail()));

				String msg = null;
				msg = "<b> Hello </b>";
				/// msg += "<b>"+" "+senderemail.getName()+"</b><br>";
				msg += "<br>";
				msg += "1. Please click on this link to  <a href='" + serverUrl + link + "'>uploadUrl.</a><br>";
				msg += "<b>Warm Regards,</b><br>";
				msg += "<b> HULKLOGIC TEAM </b>";

				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msg, "text/html");

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				logger.info("contractDocument:" + customer.getFilename());
				// adds attachments
				if (customer.getFilename() != null) {

					MimeBodyPart attachPart = new MimeBodyPart();

					try {
						attachPart.attachFile(customer.getFilename());
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					multipart.addBodyPart(attachPart);
				}

				// sets the multi-part as e-mail's content
				message.setContent(multipart);

				// Send message
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
