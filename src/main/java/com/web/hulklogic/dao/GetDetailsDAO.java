package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.GetEmployee;
import com.web.hulklogic.entity.GetTokens;
import com.web.hulklogic.entity.JobTitle;
import com.web.hulklogic.entity.ParserText;
import com.web.hulklogic.entity.User;

public interface GetDetailsDAO {
	public CreateJob getJobDetailsByJobid(int jobid);
	public Applicants getApplicantsByApplicatId(int applicantid);
	public int enableUser(int userid);
	public int enableApplicant(int applicantid);
    public User getUserByUserId(int userid);
    public int saveOtp(User user);
    public List<ParserText> getParserTextByJobId(int jobid);
    public List<ParserText> getParserTextByApplicantId(int applicantid);
    public List<JobTitle> getJobTitleOfAutoComplete(String jobtitle);
    public List<JobTitle> getLocationleOfAutoComplete(String location);
    public List<GetTokens> getJobAndApplicantTokens(int jobid);
    public List<GetEmployee> getEmployeedetails(String designation,String organization);
    public int checkJobAssignToRecruiter(int jobid);
   
}
