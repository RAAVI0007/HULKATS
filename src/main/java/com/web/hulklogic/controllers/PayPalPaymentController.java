package com.web.hulklogic.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.web.hulklogic.service.PayPalClient;
import com.web.hulklogic.utility.URLResolver;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalPaymentController {

	private static Logger logger = Logger.getLogger(PayPalPaymentController.class);
	
	public static final String PAYPAL_SUCCESS_URL ="/success";
	public static final String PAYPAL_CANCEL_URL = "/cancel";
	
	@Autowired
	private PayPalClient payPalClient ;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/pay")
	public String pay(HttpServletRequest request){
		String cancelUrl = URLResolver.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLResolver.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		try {
			Payment payment = payPalClient.createPayment(
					4.00,  //This should it get from Request Object
					"USD", //This should it get from Request Object
					"paypal",
					"sale",
					"payment description",  //This should it get from Request Object
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					System.out.println("links.getRel()>>>>>"+links.getRel().toString());
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			logger.error(e.getMessage());
		}
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = payPalClient.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			logger.error(e.getMessage());
		}
		return "redirect:/";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "cancel";
	}

	
}
