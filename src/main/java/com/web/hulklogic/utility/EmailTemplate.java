package com.web.hulklogic.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.web.hulklogic.entity.AutoSignature;
import com.web.hulklogic.entity.SenderEmail;

public class EmailTemplate {

	private static Logger logger = Logger.getLogger(EmailTemplate.class);
	private static String serverUrl = "https://hulkats.com/hulkatsapp";
	private static String imageUrl = "https://hulkats.com/Hulkats/images/HulkATS_logo.png";
	public EmailTemplate() {
		try {

		} catch (Exception e) {
			logger.error("Exception is: " + e);
		}
	}

	public boolean sendMail(SenderEmail senderemail, AutoSignature autoSignature) {
		try {
			Properties prop = new Properties();
			InputStream input = null;

		

				input = new FileInputStream("/opt/hulkats/propertiesfiles/" + autoSignature.getPropertyname());

				// load a properties file
				prop.load(input);
			 String from = prop.getProperty("fromMail");
			 String username = prop.getProperty("username");
			 String password = prop.getProperty("password");
			 String host = prop.getProperty("host");
			 String	port = prop.getProperty("port");

			logger.info("mailId:" + senderemail.getEmail());
			logger.info("mailId:" + autoSignature.getAutosin_companyname());
			logger.info(autoSignature.getSightml());

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
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(senderemail.getEmail()));

				// Set Subject: header field

				message.setSubject(senderemail.getSubject());
				String msg = null;

				/*
				 * msg ="<b> Hello </b>"; msg +=
				 * "<b>"+" "+senderemail.getName()+"</b><br>"; msg += "<br>";
				 * msg += "<b>"+senderemail.getBody()+"</b><br>"; msg += "<br>";
				 * msg += "<b>Warm Regards,</b><br>"; msg +=
				 * autoSignature.getSightml();
				 */
				if(autoSignature.getTemplatedesignid() == 1){
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
						+ "<h3 mc:edit='header' style='color:#5F5F5F;line-height:125%;font-family:Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;margin-top:0;margin-bottom:3px;'>Hello "
						+ ' ' + senderemail.getName() + ',' + "</b><br></h3><br />"
						+ "<div mc:edit='body' style='font-family:Helvetica,Arial,sans-serif;font-size:15px;margin-bottom:0;color:#5F5F5F;line-height:135%;'><p class='justify'>";
				msg += "<b>" + senderemail.getBody() + "</b><br>";
				msg += "</div></td></tr><tr></tr><tr></tr></table></td></tr><td align='center' valign='top'>"
						+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'><tr style='padding-top:0;'>"
						+ "<td align='center' valign='top'><table border='0' cellpadding='30' cellspacing='0' width='500' class='flexibleContainer'>"
						+ "<tr><td style='padding-top:0;' valign='top' width='500' class='flexibleContainerCell'>";
				msg += "<b>Warm Regards,</b><br>";
				msg += autoSignature.getSightml();
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
				}else if(autoSignature.getTemplatedesignid() == 2){
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
							+ "Hello"+  senderemail.getName() + ","+"</td></tr><tr>"
							+ "<td data-color='text' data-size='size text' data-min='10' data-max='26' data-link-color='link text color' data-link-style='font-weight:bold; text-decoration:underline; color:#40aceb;' align='center' style='font:bold 16px/25px Arial, Helvetica, sans-serif; color:#888; padding:0 0 23px;'>";
							msg += "<b>"+ senderemail.getBody() +"</b><br>";
							msg += "</td></tr><tr><td style='padding:0 0 20px;'>";
							msg += "<b>Warm Regards,</b><br>";
							msg += autoSignature.getSightml();
							msg+= "</td></tr></table></td></tr>"
							+ "<tr><td height='28'></td></tr></table></td></tr></table>"
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
				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(msg, "text/html");

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				// get attachement
				String attachment[] = senderemail.getTotalDocuments();
				logger.info("file 1 is getting in BulkEmail:" + attachment[0]);
				logger.info("file  2 is getting in BulkEmail:" + attachment[1]);
				logger.info("file 3  is getting in BulkEmail:" + attachment[2]);
				logger.info("file  4 is getting in BulkEmail:" + attachment[3]);

				// adds attachments
				if (attachment != null && attachment.length > 0) {
					for (String filePath : attachment) {
						if (filePath != null) {
							MimeBodyPart attachPart = new MimeBodyPart();

							try {
								attachPart.attachFile(filePath);
							} catch (IOException ex) {
								ex.printStackTrace();
							}

							multipart.addBodyPart(attachPart);
						}
					}
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
