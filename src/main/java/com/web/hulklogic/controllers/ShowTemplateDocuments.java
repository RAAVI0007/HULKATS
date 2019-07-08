package com.web.hulklogic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hulklogic.conversion.WordConvertPDF;
import com.web.hulklogic.entity.ApplicationHistory;
import com.web.hulklogic.entity.CreateTemplate;
import com.web.hulklogic.entity.JobsDocuments;
import com.web.hulklogic.service.TemplateService;
import com.web.hulklogic.utility.DocumentDeleteUtility;

@Controller
public class ShowTemplateDocuments {

	private static Logger logger = Logger.getLogger(ShowTemplateDocuments.class);
	@Autowired
    private ServletContext context;
	@Autowired
	private TemplateService templateService;
	
	
	@RequestMapping(value="/user/sendTemplateDocument",method=RequestMethod.POST)
	public @ResponseBody  CreateTemplate  sendJobsDocuments(@RequestParam(value="templateId", required=false) int templateId){
		
		logger.info("templateId:"+templateId);
		String pdffile=null;
		String pdffile1=null;
		CreateTemplate  getTemplateName=null;
		String encodedTemplate=null;
		DocumentDeleteUtility documentDelete=null;
		String beforeDot=null;
		
       try{
    	              getTemplateName=templateService.getTempalteDocumentsById(templateId);
				      if(getTemplateName.getAttachement()!=null){
				    	  
		                   //String extensionRemoved1 = getTemplateName.getAttachement().split("\\.")[1];
				    	    int index =  getTemplateName.getAttachement().lastIndexOf(".");
			       		    int addvalue=index+1;
			       		    String lastDot= getTemplateName.getAttachement().substring(addvalue);
			       		    logger.info("lastDot:"+lastDot);
			       		    
					  if( lastDot.equals("docx"))
		        	  {      
		       
		                //String filename = context.getRealPath("/WEB-INF/template/")+getTemplateName.getAttachement();
		                String filename = "/opt/hulkats/template/"+getTemplateName.getAttachement();
		                
		                int lastIndxDot =  getTemplateName.getAttachement().lastIndexOf('.');
		        	    if (lastIndxDot != -1) {
		        	    	beforeDot =  getTemplateName.getAttachement().substring(0, lastIndxDot);
		        	    	 logger.info("afterDot:"+beforeDot);
		        	    }
		        	    
		               // pdffile=context.getRealPath("/WEB-INF/template/")+beforeDot+".pdf";
		        	    pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
		        	    pdffile1 =beforeDot+".pdf";
		               
		                WordConvertPDF wordconvertpdf=new WordConvertPDF();
		                wordconvertpdf.ConvertToPDF(filename, pdffile);
		                File destinationFile = new File(pdffile);
		                FileInputStream fileInputStreamReader = null;
		          try{
		                fileInputStreamReader = new FileInputStream(destinationFile);
		                byte[] bytes = new byte[(int)destinationFile.length()];
		                fileInputStreamReader.read(bytes);
		                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
		                getTemplateName.setAttachement(encodedTemplate);   
		            }catch (Exception e) {
		            	  
		                logger.error("Exception is: "+e);
		            }
		               try {
		            	   
		                  if(fileInputStreamReader!=null)
		                  fileInputStreamReader.close();  
		                  }catch (Exception e) {
		    	          logger.error("Exception is: "+e);
		           } 
		             }else {
		        	
		    	       //File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator + getTemplateName.getAttachement());
		            	 File destinationFile = new File("/opt/hulkats/template"+ getTemplateName.getAttachement());
		    	       pdffile1=getTemplateName.getAttachement();
	                   FileInputStream fileInputStreamReader = null;
	            
	              try{
	            	  
	            	  fileInputStreamReader = new FileInputStream(destinationFile);
	                  byte[] bytes = new byte[(int)destinationFile.length()];
	                  fileInputStreamReader.read(bytes);
	                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
	                  getTemplateName.setAttachement(encodedTemplate);   
	               }
	                 catch (Exception e) {
	            	 logger.error("Exception is: "+e);
				}
	            
				try {
					    if(fileInputStreamReader!=null)
						fileInputStreamReader.close();
					    
				}catch (Exception e) {
					
			           logger.error("Exception is: "+e);
	            } 
		       }
					int result=templateService.updateTemplateAttachement(pdffile1, templateId);
				//	File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
					File destinationFile = new File("/opt/hulkats/template");
		              String docXFileName=beforeDot+".docx";
		              String docFileName=beforeDot+".doc";
		               if(result>0){
		               documentDelete=new DocumentDeleteUtility();
		                  documentDelete.deleteDocument(destinationFile, docXFileName);
		             }
					  
		      }
		                if(getTemplateName.getAttachement1()!=null){
		                	
			             //String extensionRemoved1 = getTemplateName.getAttachement1().split("\\.")[1];
		                 int index = getTemplateName.getAttachement1().lastIndexOf(".");
				       	 int addvalue=index+1;
				       	 String lastDot=getTemplateName.getAttachement1().substring(addvalue);
				       	 logger.info("lastDot:"+lastDot);	
			             
						 if( lastDot.equals("docx"))
			        	 {      
			       
			                String filename = context.getRealPath("/WEB-INF/template/")+getTemplateName.getAttachement1();
			                int lastIndxDot = getTemplateName.getAttachement1().lastIndexOf('.');
			        	    if (lastIndxDot != -1) {
			        	    	beforeDot = getTemplateName.getAttachement1().substring(0, lastIndxDot);
			        	    	 logger.info("afterDot:"+beforeDot);
			        	    }
			              //  pdffile=context.getRealPath("/WEB-INF/template/")+beforeDot+".pdf";
			        	    pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
			                pdffile1 =beforeDot+".pdf";
			               
			                WordConvertPDF wordconvertpdf=new WordConvertPDF();
			                wordconvertpdf.ConvertToPDF(filename, pdffile);
			                File destinationFile = new File(pdffile);
			                FileInputStream fileInputStreamReader = null;
			          try{
			                fileInputStreamReader = new FileInputStream(destinationFile);
			                byte[] bytes = new byte[(int)destinationFile.length()];
			                fileInputStreamReader.read(bytes);
			                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
			                getTemplateName.setAttachement1(encodedTemplate);   
			            }catch (Exception e) {
			            	  
			                logger.error("Exception is: "+e);
			            }
			               try {
			            	   
			                  if(fileInputStreamReader!=null)
			                  fileInputStreamReader.close();  
			                  }catch (Exception e) {
			    	          logger.error("Exception is: "+e);
			           } 
			             }else {
			        	
			    	    //   File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator +getTemplateName.getAttachement1());
			            	 File destinationFile = new File("/opt/hulkats/template" +getTemplateName.getAttachement1());
			            	 pdffile1=getTemplateName.getAttachement1();
		                   FileInputStream fileInputStreamReader = null;
		            
		              try{
		            	  
		            	  fileInputStreamReader = new FileInputStream(destinationFile);
		                  byte[] bytes = new byte[(int)destinationFile.length()];
		                  fileInputStreamReader.read(bytes);
		                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
		                  getTemplateName.setAttachement1(encodedTemplate);   
		               }
		                 catch (Exception e) {
		            	 logger.error("Exception is: "+e);
					}
		            
					try {
						    if(fileInputStreamReader!=null)
							fileInputStreamReader.close();
						    
					}catch (Exception e) {
						
				           logger.error("Exception is: "+e);
		            } 
			       }
						 
						 int result= templateService.updateTemplateAttachement1(pdffile1, templateId);			
						// File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
						 File destinationFile = new File("/opt/hukats/template");
			             String docXFileName=beforeDot+".docx";
			             String docFileName=beforeDot+".doc";
			               if(result>0){
			               documentDelete=new DocumentDeleteUtility();
			                  documentDelete.deleteDocument(destinationFile, docXFileName);
			             }
			      }	      
       }catch(Exception e){
    	   logger.error("Exception is: "+e);
       }
       
       return getTemplateName;
       
	}
	
	
	    @RequestMapping(value="/user/getApplicationHistory",method=RequestMethod.POST)
	    public @ResponseBody List<ApplicationHistory> applicationHistoryByAppId(@RequestParam(value="app_id", required=false) int app_id){
	    logger.info("app_id:"+app_id);
	    List<ApplicationHistory> applicationHistory=null;
	    try{
	    	applicationHistory=templateService.getApplicationHistoryByappId(app_id);
	    }catch(Exception e) {
	     logger.error("Exception is: "+e);
	    }
	   return applicationHistory;
	}
	 
	   @RequestMapping(value="/user/emailHistory",method=RequestMethod.POST)
	   public @ResponseBody ApplicationHistory emailHistoryByHistoryId(@RequestParam(value="historyId", required=false) int historyId) {
		   logger.info("historyId:"+historyId); 
		   ApplicationHistory applicationHistory=null;
		    try{
		    	applicationHistory=templateService.getApplicationHistoryByhistoryId(historyId);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return applicationHistory;
	   }
	   
	   
	   @RequestMapping(value="/user/statusHistory",method=RequestMethod.POST)
	   public @ResponseBody ApplicationHistory statusHistoryByHistoryId(@RequestParam(value="historyId", required=false) int historyId) {
		   logger.info("historyId:"+historyId); 
		   ApplicationHistory applicationHistory=null;
		    try{
		    	applicationHistory=templateService.getApplicationHistoryByhistoryId(historyId);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return applicationHistory;
	   }
	   
	   @RequestMapping(value="/user/commentHistory",method=RequestMethod.POST)
	   public @ResponseBody ApplicationHistory commentHistoryByHistoryId(@RequestParam(value="historyId", required=false) int historyId) {
		   logger.info("historyId:"+historyId); 
		   ApplicationHistory applicationHistory=null;
		    try{
		    	applicationHistory=templateService.getApplicationHistoryByhistoryId(historyId);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return applicationHistory;
	   }
	   
	   
	   @RequestMapping(value="/user/sourceHistory",method=RequestMethod.POST)
	   public @ResponseBody ApplicationHistory sourceHistoryByHistoryId(@RequestParam(value="historyId", required=false) int historyId) {
		   logger.info("historyId:"+historyId); 
		   ApplicationHistory applicationHistory=null;
		    try{
		    	applicationHistory=templateService.getApplicationHistoryByhistoryId(historyId);
		    }catch(Exception e){
		     logger.error("Exception is: "+e);
		    }
		    return applicationHistory;
	   }
	   
	    @RequestMapping(value="/user/getAttachment1",method=RequestMethod.POST)
		public @ResponseBody String sendAttachment1(@RequestParam(value="attachment", required=false) String attachment,
				@RequestParam(value="historyid", required=false) int historyId){
			logger.info("attachment:"+attachment);
			logger.info("historyId:"+historyId);
			String pdffile=null;
			String pdffile1=null;
			String encodedTemplate=null;
			DocumentDeleteUtility documentDelete=null;
			String beforeDot=null;
			
	       try{
				
			             
			                int index = attachment.lastIndexOf(".");
			       		    int addvalue=index+1;
			       		    String lastDot= attachment.substring(addvalue);
			       		    logger.info("lastDot:"+lastDot);
			              
						 if( lastDot.equals("docx"))
			        	  {      
			       
			             //   String filename = context.getRealPath("/WEB-INF/template/")+attachment;
			                String filename = "/opt/hulkats/template/"+attachment;
			                int lastIndxDot = attachment.lastIndexOf('.');
			        	    if (lastIndxDot != -1) {
			        	    	beforeDot = attachment.substring(0, lastIndxDot);
			        	    	 logger.info("afterDot:"+beforeDot);
			        	    }
			               // pdffile=context.getRealPath("/WEB-INF/template/")+beforeDot+".pdf";
			                pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
			                pdffile1 =beforeDot+".pdf";
			               
			                WordConvertPDF wordconvertpdf=new WordConvertPDF();
			                wordconvertpdf.ConvertToPDF(filename, pdffile);
			                File destinationFile = new File(pdffile);
			                FileInputStream fileInputStreamReader = null;
			          try{
			                fileInputStreamReader = new FileInputStream(destinationFile);
			                byte[] bytes = new byte[(int)destinationFile.length()];
			                fileInputStreamReader.read(bytes);
			                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
			            }catch (Exception e) {
			            	  
			                logger.error("Exception is: "+e);
			            }
			               try {
			            	   
			                  if(fileInputStreamReader!=null)
			                  fileInputStreamReader.close();  
			                  }catch (Exception e) {
			    	          logger.error("Exception is: "+e);
			           } 
			             }else {
			        	
			    	       //File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator +attachment);
			            	 File destinationFile = new File("/opt/hulkats/template/" +attachment);
			            	 pdffile1=attachment;
		                   FileInputStream fileInputStreamReader = null;
		            
		              try{
		            	  
		            	  fileInputStreamReader = new FileInputStream(destinationFile);
		                  byte[] bytes = new byte[(int)destinationFile.length()];
		                  fileInputStreamReader.read(bytes);
		                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8"); 
		               }
		                 catch (Exception e) {
		            	 logger.error("Exception is: "+e);
					}
		            
					try {
						    if(fileInputStreamReader!=null)
							fileInputStreamReader.close();
						    
					}catch (Exception e) {
						
				           logger.error("Exception is: "+e);
		            } 
			       }
						int result=  templateService.updateAttachments(attachment, historyId);
                       // File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
                        File destinationFile = new File("/opt/hulkats/template/");
						String docXFileName=beforeDot+".docx";
						String docFileName=beforeDot+".doc";
						  if(result>0){
						     documentDelete=new DocumentDeleteUtility();
						        documentDelete.deleteDocument(destinationFile, docXFileName);
						   }
						     
			   }catch(Exception e){
	    	   logger.error("Exception is: "+e);
	       }
	       
	       return encodedTemplate;
	       
		}
		
	   
	    @RequestMapping(value="/user/getAttachment2",method=RequestMethod.POST)
		public @ResponseBody String sendAttachment2(@RequestParam(value="attachment", required=false) String attachment,
				@RequestParam(value="historyid", required=false) int historyId){
			logger.info("attachment:"+attachment);
			logger.info("historyId:"+historyId);
			
			String pdffile=null;
			String pdffile1=null;
		    String encodedTemplate=null;
			DocumentDeleteUtility documentDelete=null;
			String beforeDot=null;
			
	       try{
				
			              
	    	             //String extensionRemoved1 = attachment.split("\\.")[1];
	    	                int index = attachment.lastIndexOf(".");
			       		    int addvalue=index+1;
			       		    String lastDot= attachment.substring(addvalue);
			       		    logger.info("lastDot:"+lastDot);
			              
			              
						  if( lastDot.equals("docx"))
			        	  {      
			       
			              //  String filename = context.getRealPath("/WEB-INF/template/")+attachment;
							  String filename = "/opt/hulkats/template/"+attachment;
			                int lastIndxDot = attachment.lastIndexOf('.');
			        	    if (lastIndxDot != -1) {
			        	    	beforeDot = attachment.substring(0, lastIndxDot);
			        	    	 logger.info("afterDot:"+beforeDot);
			        	    }
			             //   pdffile=context.getRealPath("/WEB-INF/template/")+beforeDot+".pdf";
			                
			                pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
			                pdffile1 =beforeDot+".pdf";
			               
			                WordConvertPDF wordconvertpdf=new WordConvertPDF();
			                wordconvertpdf.ConvertToPDF(filename, pdffile);
			                File destinationFile = new File(pdffile);
			                FileInputStream fileInputStreamReader = null;
			          try{
			                fileInputStreamReader = new FileInputStream(destinationFile);
			                byte[] bytes = new byte[(int)destinationFile.length()];
			                fileInputStreamReader.read(bytes);
			                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
			            }catch (Exception e) {
			            	  
			                logger.error("Exception is: "+e);
			            }
			               try {
			            	   
			                  if(fileInputStreamReader!=null)
			                  fileInputStreamReader.close();  
			                  }catch (Exception e) {
			    	          logger.error("Exception is: "+e);
			           } 
			             }else {
			        	
			    	    //   File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator +attachment);
			    	       File destinationFile = new File("/opt/hulkats/template"+ File.separator +attachment);
			    	       pdffile1=attachment;
		                   FileInputStream fileInputStreamReader = null;
		            
		              try{
		            	  
		            	  fileInputStreamReader = new FileInputStream(destinationFile);
		                  byte[] bytes = new byte[(int)destinationFile.length()];
		                  fileInputStreamReader.read(bytes);
		                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8"); 
		               }
		                 catch (Exception e) {
		            	 logger.error("Exception is: "+e);
					}
		            
					try {
						    if(fileInputStreamReader!=null)
							fileInputStreamReader.close();
						    
					}catch (Exception e) {
						
				           logger.error("Exception is: "+e);
		            } 
			       }
						int result=  templateService.updateAttachments1(attachment, historyId);
						  
					//	File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
						File destinationFile = new File("/opt/hulkats/template");
						String docXFileName=beforeDot+".docx";
						String docFileName=beforeDot+".doc";
						  if(result>0){
						     documentDelete=new DocumentDeleteUtility();
						        documentDelete.deleteDocument(destinationFile, docXFileName);
						   }
						     
			   }catch(Exception e){
	    	   logger.error("Exception is: "+e);
	       }
	       
	       return encodedTemplate;
	       
		}
	    

	    @RequestMapping(value="/user/getAttachment3",method=RequestMethod.POST)
		public @ResponseBody String sendAttachment3(@RequestParam(value="attachment", required=false) String attachment,
				@RequestParam(value="historyid", required=false) int historyId){
			logger.info("attachment:"+attachment);
			logger.info("historyId:"+historyId);
			String pdffile=null;
			String pdffile1=null;
			String encodedTemplate=null;
			DocumentDeleteUtility documentDelete=null;
			String beforeDot=null;
			
	       try{
				
			              //String extensionRemoved1 = attachment.split("\\.")[1];
	    	                int index = attachment.lastIndexOf(".");
      		                int addvalue=index+1;
      		                String lastDot= attachment.substring(addvalue);
      		                logger.info("lastDot:"+lastDot);
						   if( lastDot.equals("docx"))
			        	   {      
			       
			             //   String filename = context.getRealPath("/WEB-INF/template/")+attachment;
							   String filename = "/opt/hulkats/template/"+attachment;
			                int lastIndxDot = attachment.lastIndexOf('.');
			        	    if (lastIndxDot != -1) {
			        	    	beforeDot = attachment.substring(0, lastIndxDot);
			        	    	 logger.info("afterDot:"+beforeDot);
			        	    }
			                pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
			                pdffile1 =beforeDot+".pdf";
			               
			                WordConvertPDF wordconvertpdf=new WordConvertPDF();
			                wordconvertpdf.ConvertToPDF(filename, pdffile);
			                File destinationFile = new File(pdffile);
			                FileInputStream fileInputStreamReader = null;
			          try{
			                fileInputStreamReader = new FileInputStream(destinationFile);
			                byte[] bytes = new byte[(int)destinationFile.length()];
			                fileInputStreamReader.read(bytes);
			                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
			            }catch (Exception e) {
			            	  
			                logger.error("Exception is: "+e);
			            }
			               try {
			            	   
			                  if(fileInputStreamReader!=null)
			                  fileInputStreamReader.close();  
			                  }catch (Exception e) {
			    	          logger.error("Exception is: "+e);
			           } 
			             }else {
			        	
			    	     //  File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator +attachment);
			            	 File destinationFile = new File("/opt/hulkats/template"+ File.separator +attachment);
			    	       pdffile1=attachment;
		                   FileInputStream fileInputStreamReader = null;
		            
		              try{
		            	  
		            	  fileInputStreamReader = new FileInputStream(destinationFile);
		                  byte[] bytes = new byte[(int)destinationFile.length()];
		                  fileInputStreamReader.read(bytes);
		                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8"); 
		               }
		                 catch (Exception e) {
		            	 logger.error("Exception is: "+e);
					}
		            
					try {
						    if(fileInputStreamReader!=null)
							fileInputStreamReader.close();
						    
					}catch (Exception e) {
						
				           logger.error("Exception is: "+e);
		            } 
			       }
						     int result= templateService.updateAttachments2(attachment, historyId);
						     //File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
						     File destinationFile = new File("/opt/hulkats/template");
						     String docXFileName=beforeDot+".docx";
						     String docFileName=beforeDot+".doc";
						     if(result>0){
						     documentDelete=new DocumentDeleteUtility();
						        documentDelete.deleteDocument(destinationFile, docXFileName);
						   }
						  
			   }catch(Exception e){
	    	   logger.error("Exception is: "+e);
	       }
	       
	       return encodedTemplate;
	       
		}
	    
	    
	    @RequestMapping(value="/user/getAttachment4",method=RequestMethod.POST)
	  		public @ResponseBody String sendAttachment4(@RequestParam(value="attachment", required=false) String attachment,
	  				@RequestParam(value="historyid", required=false) int historyId){
	  			logger.info("attachment:"+attachment);
	  			logger.info("historyId:"+historyId);
	  			String pdffile=null;
	  			String pdffile1=null;
	  			String encodedTemplate=null;
	  			DocumentDeleteUtility documentDelete=null;
	  			String beforeDot=null;
	  	       try{
	  				
	  			             // String extensionRemoved1 = attachment.split("\\.")[1];
	  	    	                int index = attachment.lastIndexOf(".");
	                            int addvalue=index+1;
	                            String lastDot= attachment.substring(addvalue);
	                            logger.info("lastDot:"+lastDot);
	  						  
	                        if( lastDot.equals("docx"))
	  			        	  {      
	  			       
	  			               // String filename = context.getRealPath("/WEB-INF/template/")+attachment;
	  			              String filename = "/opt/hulkats/template/"+attachment;
	  			                 int lastIndxDot = attachment.lastIndexOf('.');
				        	     if (lastIndxDot != -1) {
				        	    	beforeDot = attachment.substring(0, lastIndxDot);
				        	    	 logger.info("afterDot:"+beforeDot);
				        	    }
	  			              //  pdffile=context.getRealPath("/WEB-INF/template/")+beforeDot+".pdf";
	  			                pdffile="/opt/hulkats/template/"+beforeDot+".pdf";
	  			                pdffile1 =beforeDot+".pdf";
	  			               
	  			                WordConvertPDF wordconvertpdf=new WordConvertPDF();
	  			                wordconvertpdf.ConvertToPDF(filename, pdffile);
	  			                File destinationFile = new File(pdffile);
	  			                FileInputStream fileInputStreamReader = null;
	  			          try{
	  			                fileInputStreamReader = new FileInputStream(destinationFile);
	  			                byte[] bytes = new byte[(int)destinationFile.length()];
	  			                fileInputStreamReader.read(bytes);
	  			                encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8");
	  			            }catch (Exception e) {
	  			            	  
	  			                logger.error("Exception is: "+e);
	  			            }
	  			               try {
	  			            	   
	  			                  if(fileInputStreamReader!=null)
	  			                  fileInputStreamReader.close();  
	  			                  }catch (Exception e) {
	  			    	          logger.error("Exception is: "+e);
	  			           } 
	  			             }else {
	  			        	
	  			    	       //File destinationFile = new File(context.getRealPath("/WEB-INF/template")+ File.separator +attachment);
	  			            	File destinationFile = new File("/opt/hulkats/template/"+attachment);
	  			    	       pdffile1=attachment;
	  		                   FileInputStream fileInputStreamReader = null;
	  		            
	  		              try{
	  		            	  
	  		            	  fileInputStreamReader = new FileInputStream(destinationFile);
	  		                  byte[] bytes = new byte[(int)destinationFile.length()];
	  		                  fileInputStreamReader.read(bytes);
	  		                  encodedTemplate = new String(Base64.encodeBase64(bytes), "UTF-8"); 
	  		               }
	  		                 catch (Exception e) {
	  		            	 logger.error("Exception is: "+e);
	  					}
	  		            
	  					try {
	  						    if(fileInputStreamReader!=null)
	  							fileInputStreamReader.close();
	  						    
	  					}catch (Exception e) {
	  						
	  				           logger.error("Exception is: "+e);
	  		            } 
	  			       }
	  						int result= templateService.updateAttachments3(attachment, historyId);
	  					  //  File destinationFile = new File(context.getRealPath("/WEB-INF/template"));
	  						File destinationFile = new File("/opt/hulkats/template");
	  					    String docXFileName=beforeDot+".docx";
	  					    String docFileName=beforeDot+".doc";
	  					    if(result>0){
	  					     documentDelete=new DocumentDeleteUtility();
	  					        documentDelete.deleteDocument(destinationFile, docXFileName);
	  					   }
	  			   }catch(Exception e){
	  	    	   logger.error("Exception is: "+e);
	  	       }
	  	       return encodedTemplate;
	  		}
	    
	     @RequestMapping(value="/manager/changeTeamname",method=RequestMethod.POST)
	     public @ResponseBody String  changeTeamname(HttpSession session,@RequestParam(value="teamname", required=false) String teamname) {  
	     logger.info("teamname:"+teamname); 
	     String oldTeamname= (String)session.getAttribute("teamname");
	     logger.info("oldTeamname:"+oldTeamname);
	     String result="fail";
	     try{
	            result=templateService.updateTeamname(teamname, oldTeamname);
	    }catch(Exception e){
	      logger.error("Exception is: "+e);
	        }
	    return result;
	 }
	
	    
	      @RequestMapping(value="/user/getTemplatesDocs",method=RequestMethod.POST)
	      public  @ResponseBody CreateTemplate getTemplateDetails(@RequestParam("templateId") int templateId) {
	      logger.info("templateId:"+templateId); 
	      CreateTemplate getTemplates=null; 
	        try 
	        {
	        	getTemplates= templateService.getTemplatesDetails(templateId);
	        }catch(Exception e) {
	          logger.error("Exception is: "+e);
	        }
	    return getTemplates; 
	 }
	  
	    
	    
	      
	    
}	
	
       