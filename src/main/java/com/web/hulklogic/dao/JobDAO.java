package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.JobsDocuments;

public interface JobDAO {
	public int createJob(CreateJob createjob);

	public List<CreateJob> getAllJobs(String currentemail);

	public List<CreateJob> getalljobs();

	public List<CreateJob> getallrecentjobs(String company);

	public int createApplicants(Applicants applicants);

	public List<Applicants> getAllApplicants(String organization);

	public JobsDocuments getDocumentsByJobId(int jobid);

	public int updatePassword(String currentEmail, String newPassword);

	public int selectPassword(String currentEmail, String oldPassword);

	public List getJobKeywords(int jobid);

	public int jobassignRecruiter(String email, int jobid);

	public CreateJob jobdetails(int jobid);

	public Applicants getapplicantdetails(int applicantid);

}
