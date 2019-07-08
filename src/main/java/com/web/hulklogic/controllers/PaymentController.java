package com.web.hulklogic.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.model.Charge;
import com.web.hulklogic.entity.Applicants;
import com.web.hulklogic.entity.StripePayment;
import com.web.hulklogic.service.StripeClient;
import com.web.hulklogic.utility.ResponseObj;

@RestController
public class PaymentController {
	static Gson gson = new Gson();
	static ResponseObj responseObj = new ResponseObj();

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @RequestMapping(value = "/user/charge", method = RequestMethod.POST)
    public Charge chargeCard(HttpServletRequest request, @RequestParam("applicantsCreations") String jobStr) throws Exception {
    	StripePayment stripepayment = new Gson().fromJson(jobStr, StripePayment.class);
    	
    	if(stripepayment.getSubtype() == "enterprise"){
    	   stripepayment.setAmount((long) 999);
    	   
       }else if(stripepayment.getSubtype() == "basic"){
    	   
    	   stripepayment.setAmount((long) 500);
    	   
       }
    	Charge charge = this.stripeClient.chargeNewCard(stripepayment);
    	
        return charge;
    }
}
