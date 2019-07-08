package com.web.hulklogic.dao;
import java.util.List;

import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.Survey;
import com.web.hulklogic.entity.SurveyFeedback;
import com.web.hulklogic.entity.User;

public interface SurveyDao {
	
	public int saveSurvey(Survey survey);
	public List<Survey> getAllSurvey();
	public int surveyDetails(SurveyFeedback survey);
	public int getbizcardemailCheck(String email);
	 public int getbizcardContactnoCheck(String contactno);
	 public int saveCard(CardDetails carddetails);
	 public int cardstatus();

}
