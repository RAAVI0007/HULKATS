<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="#" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- jQuery -->

<script type="text/javascript" src="https://js.stripe.com/v2/"></script>

</head>
<body class="">


	
        <div class="container bodyBck">
    <div class="row">
        <div class="col-lg-12">
            <div class="col-xs-12 col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Charge $55 with Stripe</h3>
                    </div>
                    <div class="panel-body">
                        <!-- display errors returned by createToken -->
                        <p class="payment-status"></p>
                        <form method="POST" id="paymentFrm">
                        <div class="form-group">
                            <label>NAME</label>
                            <input type="text" class="form-control name" id="name" name="name" placeholder="Enter name" required autofocus />
                        </div>
                        <div class="form-group">
                            <label>EMAIL</label>
                            <input type="email" class="form-control email" id="email" name="email" placeholder="Enter email" required />
                        </div>
                        <div class="form-group">
                            <label>CARD NUMBER</label>
                            <input type="text" class="form-control card-number" name="card_num" placeholder="Valid card number" required />
                        </div>
                        <div class="row">
                            <div class="col-xs-7">
                                <div class="form-group">
                                    <label>EXPIRY DATE</label>
                                    <div class="col-xs-6">
                                        <input type="text" class="form-control card-expiry-month" name="exp_month" placeholder="MM" required />
                                    </div>
                                    <div class="col-xs-6">
                                        <input type="text" class="form-control card-expiry-year" name="exp_year" placeholder="YY" required />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-5 pull-right">
                                <div class="form-group">
                                    <label>CVC CODE</label>
                                    <input type="text" class="form-control card-cvc" name="cvc" placeholder="CVC" required />
                                </div>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-success btn-lg btn-block" id="payBtn" value="Submit Payment">
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-4">
                <h2>Test Card Details</h2>
                <p>To test the payment process, you need test card details. Use any of the following test card numbers, a valid future expiration date, and any random CVC number, to test Stripe payment gateway integration in PHP</p>
                <ul>
                <li>4242424242424242	Visa</li>
                <li>4000056655665556	Visa (debit)</li>
                <li>5555555555554444	Mastercard</li>
                <li>5200828282828210	Mastercard (debit)</li>
                <li>378282246310005	American Express</li>
                <li>6011111111111117	Discover</li>
                </ul>
            </div>
		</div>
    </div>
</div>
	<script>
// set your stripe publishable key
Stripe.setPublishableKey('pk_test_Lf1YqfGSAKMKaiY1U30s82Bg');

 //callback to handle the response from stripe
    function stripeResponseHandler(status, response) {
    	console.log(JSON.stringify(response));
    	var user ={};
    	console.log(response['id']);
        if (response.error) {
            //enable the submit button
            $('#payBtn').removeAttr("disabled");
            //display the errors on the form
            $(".payment-status").html(response.error.message);
        } else {
           
            //get token id
            var token = response['id'];
            //insert the token into the form
           
        	alert("hello");
            user.name = $('#name').val();
            user.email =$('#email').val();
            user.tokenid=token;
            user.subtype="basic";
        	console.log(user);
            console.log(JSON.stringify(user));
            $.ajax({
                type:'POST',
                url:'${pageContext.servletContext.contextPath}/user/charge',
                dataType : "json",
                data:{applicantsCreations:JSON.stringify(user)},
                success:function(msg){
                	console.log(msg);
                   /* if(msg == 'trr'){
                        $(".payment-status").html('<span style="color:red;">Transaction has been failed.</span>');
                    }else if(msg == 'err'){
                        $(".payment-status").html('<span style="color:red;">Some problem occurred, please try again.</span>');
                    }else if(msg == 'frr'){
                        $(".payment-status").html('<span style="color:red;">Form submission error.......</span>');
                    }else{
                        $('.name').val('');
                        $('.email').val('');
                        $('.card-number').val('');
                        $('.card-expiry-month').val('');
                        $('.card-expiry-year').val('');
                        $('.card-cvc').val('');
                        $(".payment-status").html('<span style="color:green;">Your payment was successful. TXN ID: '+msg+'</span>');
                    }*/
                   
                }
            });
            //submit form to the server
            //form$.get(0).submit();
        }
    }
   
    $(document).ready(function() {
        //on form submit
        $("#paymentFrm").submit(function(event) {
            //disable the submit button to prevent repeated clicks
            $('#payBtn').attr("disabled", "disabled");
            $('#paymentFrm').css('opacity', '.5');
            
            //create single-use token to charge the user
            Stripe.createToken({
                number: $('.card-number').val(),
                cvc: $('.card-cvc').val(),
                exp_month: $('.card-expiry-month').val(),
                exp_year: $('.card-expiry-year').val()
            }, stripeResponseHandler);
            
            //submit from callback
            return false;
        });
    });
 

</script>
</body>
</html>


