package com.web.hulklogic.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.hulklogic.dao.SurveyDao;
import com.web.hulklogic.entity.CardDetails;
import com.web.hulklogic.entity.Survey;
import com.web.hulklogic.service.SurveyService;

public class SurveyServiceImpl implements SurveyService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private SurveyDao surveydao;

}
