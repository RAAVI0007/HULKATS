package com.web.hulklogic.controllers;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.ParserText;
import com.web.hulklogic.service.ApplicationService;
import com.web.hulklogic.service.GetDetailsService;
import com.web.hulklogic.utility.CompareCvsApplicant;
import com.web.hulklogic.utility.CompareTokens;

@Controller
public class GetDetailsController {
	private static Logger logger = Logger.getLogger(GetDetailsController.class);
	
	@Autowired
	private GetDetailsService getDetailsService;
	@Autowired
	private ApplicationService  applicantionService;
	
	@RequestMapping(value = "/user/getjobKeywords", method = RequestMethod.POST)
	 public @ResponseBody String geJobKeyWords(@RequestParam("jobid") int  jobid) {
	  logger.info("jobid:"+jobid);
	  
	  List getJobKeyWords=null;
	  try{
	     getJobKeyWords=getDetailsService.getJobKeywords(jobid);
	  }catch(Exception e){
	        logger.error("Exception is: "+e);
	  }
	  
	  return new Gson().toJson(getJobKeyWords);  
	 }
	@RequestMapping(value="/manager/showJobDetails",method=RequestMethod.POST)
	public @ResponseBody  String getJobDetails(@RequestParam(value="jobid", required=false) int jobid) {
		logger.info("jobid:"+jobid);
		CreateJob jobsDetails=getDetailsService.getJobDetailsByJobid(jobid);
		return new Gson().toJson(jobsDetails);
	}	
	
	@RequestMapping(value="/manager/showApplicantDetails",method=RequestMethod.POST)
	public @ResponseBody  String getApplicantDetails(@RequestParam(value="applicantid", required=false) int applicantid) {
		logger.info("applicantid:"+applicantid);
		Applicants applicants=getDetailsService.getApplicantsByApplicatId(applicantid);
		return new Gson().toJson(applicants);
	}	
	 @RequestMapping(value="/user/getParserTextByJobId",method=RequestMethod.POST)
	  public @ResponseBody  String getParserTextDataByJobId(@RequestParam(value="jobid", required=false) int jobid) {
	   logger.info("jobid:"+jobid);
	   List<ParserText> parserText=null;
	   try{
	    parserText=getDetailsService.getParserTextByJobId(jobid);
	   }catch(Exception e){
	    logger.error("Exception is: "+e);
	   }
	   return new Gson().toJson(parserText);
	  } 
	  
	  @RequestMapping(value="/manager/getParserTextDataByApplicantId",method=RequestMethod.POST)
	  public @ResponseBody  String getParserTextDataByApplicantId(@RequestParam(value="applicantid", required=false) int applicantid) {
	   logger.info("applicantid:"+applicantid);
	   List<ParserText> parserText=null;
	   try{
	    parserText=getDetailsService.getParserTextByApplicantId(applicantid);
	   }catch(Exception e){
	    logger.error("Exception is: "+e);
	   }
	   return new Gson().toJson(parserText);
	  }
	 
	 
	 @RequestMapping(value="/manager/getJobtitle",method=RequestMethod.POST)
	   public @ResponseBody String getJobtitleByAutoCompleted(@RequestParam(value="jobtitle", required=false) String jobtitleStr){
	   logger.info("jobtitle:"+jobtitleStr);
	   String upperCaseLocation=jobtitleStr.toUpperCase();
	   logger.info("upperCaseLocation:"+upperCaseLocation);
	   List<JobTitle> retrieveJobTitle=null;
	   try {
		   retrieveJobTitle=getDetailsService.getJobTitleOfAutoComplete(upperCaseLocation);
		   
	   }catch(Exception e) {
		   logger.error("Exception is: "+e);
	   }
	   return new Gson().toJson( retrieveJobTitle);
	  }
	 
	 
	   @RequestMapping(value="/manager/getLocation",method=RequestMethod.POST)
	   public @ResponseBody String getLocationByAutoCompleted(@RequestParam(value="location", required=false) String locationStr){ 
	   logger.info("locationStr:"+locationStr);
	   String upperCaseLocation=locationStr.toUpperCase();
	   logger.info("upperCaseLocation:"+upperCaseLocation);
	   List<JobTitle> retrieveJobTitle=null;
	   try {
		   retrieveJobTitle =getDetailsService.getLocationleOfAutoComplete(upperCaseLocation);
	   }catch(Exception e) {
		   logger.error("Exception is: "+e);
	   }
	   return new Gson().toJson( retrieveJobTitle);
	  }
	
	 
	 @RequestMapping(value="/user/getJobAndApplicant",method=RequestMethod.POST)
	 public @ResponseBody  String getJobAndApplicants(HttpSession session,@RequestParam(value="jobid", required=false) int jobid) {
	  logger.info("jobid:"+jobid);
	  String userName = (String)session.getAttribute("userName");
	  String teamname = (String)session.getAttribute("teamname");
	  GetTokens gettokens = new GetTokens();
	  gettokens.setTeamname(teamname);
	  logger.info(gettokens.getTeamname());
	  logger.info("teamname:"+teamname);
	  logger.info("userName:"+userName);
	  CompareTokens comparetokens=null;
	  CompareCvsApplicant comparecvsapplicant=null;
	  String result="fail";
	  String words1[]=null;
	
	  String cvwords1[]=null;
	
	  Float percentage1=0.0f;
	 
	  Float totalPerCv1=0.0f;
   try
        {	  
	   if(!getDetailsService.checkJobAssignToRecruiter(jobid))
       {
      return new Gson().toJson("Please assign job to the recruiter !");
   }
	       List<GetTokens> getJobAndApplicant=getDetailsService.getJobAndApplicantTokens(jobid);  
	       
	       logger.info(getJobAndApplicant.get(0).getTeamname());
	       
	      for(int k=0;k< getJobAndApplicant.size(); k++) { 
	    	  totalPerCv1 = 0.0f;
			  percentage1 = 0.0f;
		   if(getJobAndApplicant.get(k).getJobtags()!=null ) {
			  String tokens = getJobAndApplicant.get(k).getJobtags();
			  comparetokens=new CompareTokens();
			  words1 = comparetokens.compareJobAndApplicant(tokens); 
			}
		  
		  if(getJobAndApplicant.get(k).getLanguages0()!=null||getJobAndApplicant.get(k).getLanguages0()!="") {
			  String token0 =getJobAndApplicant.get(k).getLanguages0();
			
			  if(getJobAndApplicant.get(k).getLanguages1()!=null ||getJobAndApplicant.get(k).getLanguages1()!="") {
			  String token1 = getJobAndApplicant.get(k).getLanguages1();
			  token0 = token0+","+token1;
			  }else{
				    
			  }
			  if(getJobAndApplicant.get(k).getLanguages2()!=null ||getJobAndApplicant.get(k).getLanguages2()!="") {
				  String token2 = getJobAndApplicant.get(k).getLanguages2();
				  token0 = token0+","+token2;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages3()!=null ||getJobAndApplicant.get(k).getLanguages3()!="") {
				  String token3 = getJobAndApplicant.get(k).getLanguages3();
				  token0 = token0+","+token3;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages4()!=null ||getJobAndApplicant.get(k).getLanguages4()!="") {
				  String token4 = getJobAndApplicant.get(k).getLanguages4();
				  token0 = token0+","+token4;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages5()!=null ||getJobAndApplicant.get(k).getLanguages5()!="") {
				  String token5 = getJobAndApplicant.get(k).getLanguages5();
				  token0 = token0+","+token5;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages6()!=null ||getJobAndApplicant.get(k).getLanguages6()!="") {
				  String token6 = getJobAndApplicant.get(k).getLanguages6();
				  token0 = token0+","+token6;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages7()!=null ||getJobAndApplicant.get(k).getLanguages7()!="") {
				  String token7 = getJobAndApplicant.get(k).getLanguages7();
				  token0 = token0+","+token7;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages8()!=null ||getJobAndApplicant.get(k).getLanguages8()!="") {
				  String token8 = getJobAndApplicant.get(k).getLanguages8();
				  token0 = token0+","+token8;
				  }else{
					    
				  }
			  if(getJobAndApplicant.get(k).getLanguages9()!=null ||getJobAndApplicant.get(k).getLanguages9()!="") {
				  String token9 = getJobAndApplicant.get(k).getLanguages9();
				  token0 = token0+","+token9;
				  }else{
					    
				  }
			 
			  
			  
			  logger.info(token0);
			  comparetokens=new CompareTokens(); 
			  cvwords1 = comparetokens.compareJobAndApplicant(token0); 
			  logger.info(cvwords1);
			  System.out.println(Arrays.toString(cvwords1));  
			  comparecvsapplicant=new CompareCvsApplicant();
			
		if(words1!=null && cvwords1 != null){  
			percentage1 =comparecvsapplicant.compareCvsApplicantWord(words1,cvwords1);
			  //logger.info("percentage1:"+percentage1);
		}
	 } 
		   totalPerCv1=percentage1;
		
	  logger.info("totalPerCvs:"+totalPerCv1);
	  GetTokens getTokens=new GetTokens();
	  getTokens.setJobid(getJobAndApplicant.get(k).getJobid());
	  getTokens.setApplicantid(getJobAndApplicant.get(k).getApplicantid());
	  getTokens.setTeamname(teamname);
	  getTokens.setEmail(userName);
	  
	  		if(totalPerCv1 != 0f){
		
	     result=applicantionService.saveJobidApplicantId(getTokens);
	  		}
	 }
	     
       }catch(Exception e){
	       logger.error("Exception is: "+e);
      }		 
        return result;

}

}
