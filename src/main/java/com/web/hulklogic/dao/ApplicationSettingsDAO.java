package com.web.hulklogic.dao;

import java.util.List;

import com.web.hulklogic.entity.CreateJob;
import com.web.hulklogic.entity.User;

public interface ApplicationSettingsDAO {

	public List<CreateJob> getCountries();

	public List<CreateJob> getCompanies();

	public List<CreateJob> getperCompanies(String company);

	public List<CreateJob> getAllStates(int country_id);

	public List<CreateJob> getAllCities(int state_id);

	public List<CreateJob> getCountriesByPhoneCode(int phoneCode);

	public int checkContactNoInProfile(String contactNo, int userId);

	public int updateAttachements1(int templateId);

	public int updateAttachements2(int templateId);

	public int updateUploadDocument1(int jobId);

	public int updateUploadDocument2(int jobId);

	public int updateUploadCv(int applicantId);

	public int updateOtheruploads(int applicantId);
	
	public User getpropertyname(String email);

}
