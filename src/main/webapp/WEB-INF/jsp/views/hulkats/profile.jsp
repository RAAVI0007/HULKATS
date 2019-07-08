<!-- Navigation-->
<%@include file="./shared/nav.jsp"%>

<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			<sec:authorize access="hasRole('ROLE_MANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/manager/managerdashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_RECRUITER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/recruiter/recruiterdashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_BASICMANAGER')">
				<li class="breadcrumb-item"><a
					href="${pageContext.servletContext.contextPath}/basicmanager/dashboard">Dashboard</a>
				</li>
			</sec:authorize>
			<li class="breadcrumb-item"><a
				href="${pageContext.servletContext.contextPath}/manager/applications">Application
					Overview</a></li>
		</ol>
		<button class="print-link" onclick="jQuery.print('#wrap')">
			Print Profile</button>
		<div id="wrap">
			<main class="cd-main-content">
			<div id="content">
				<div class="resume">
					<div class="container">
					<h2 id="namedetails" style="text-align: center;"></h2>
						<div class="row">

							<!-- Sidebar -->
							<div class="col-md-4">
								<div class="side-bar">

									<!-- Professional Details -->
									<h5 class="tittle">Personal Details</h5>
									<ul class="personal-info">
									<li>
											<p>
												<span> Phone</span> <span id="mobile"></span>
											</p>
										</li>
										<li>
											<p>
												<span> E-mail</span><span id="email"></span>
											</p>
										</li>
										
										<li>
											<p>
												<span> Location</span><span id="location"></span>
											</p>
										</li>
										
									</ul>
								</div>
							</div>
							<div class="col-md-1"></div>

							<div class="col-md-7">
								<div role="tabpanel" class="tab-pane" id="resume">
									<div class="inside-sec">
											<h5 class="tittle">Professional Experience</h5>
											<div class="padding-30 exp-history" id="professional">
											</div>
										</div>
										<div class="inside-sec margin-top-30">
											<!-- Academic Background -->
											<h5 class="tittle">Academic Background</h5>
											<div class="padding-30 exp-history" id="academic"></div>
										</div>
										<div class="inside-sec margin-top-30">
											<!-- Academic Background -->
											<h5 class="tittle">Other Skills</h5>
											<div class="padding-30 exp-history" id="otherskills"></div>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</div>




<link href="<c:url value='/resources/css/main.css' />" rel="stylesheet">
<style>
#wrap {
	position: relative;
	width: 100%;
	overflow: hidden;
}

.cd-main-content {
	/* set a min-height and a z-index to be sure that the main element completely covers the lateral menu */
	min-height: 100%;
	position: relative;
	z-index: 2;
	/* Force Hardware Acceleration in WebKit */
	-webkit-transform: translateZ(0);
	-webkit-backface-visibility: hidden;
	-webkit-transition-property: -webkit-transform;
	-moz-transition-property: -moz-transform;
	transition-property: transform;
	-webkit-transition-duration: 0.4s;
	-moz-transition-duration: 0.4s;
	transition-duration: 0.4s;
}

.cd-main-content.lateral-menu-is-open {
	/* translate to show the lateral menu - all content needs to be put in the .cd-main-content to translate*/
	-webkit-transform: translateX(-320px);
	-moz-transform: translateX(-320px);
	-ms-transform: translateX(-320px);
	-o-transform: translateX(-320px);
	transform: translateX(-320px);
}

#cd-logo {
	display: block;
	float: left;
	margin: 12px 0 0 20px;
}

#cd-logo img {
	display: block;
}

@media only screen and (min-width: 768px) {
	#cd-logo {
		margin: 22px 0 0 30px;
	}
}

#cd-top-nav {
	position: absolute;
	top: 0;
	right: 120px;
	height: 100%;
	display: none;
}

#cd-top-nav ul {
	height: 100%;
	padding-top: 18px;
}

#cd-top-nav li {
	display: inline-block;
	margin-right: 1em;
}

#cd-top-nav a {
	display: inline-block;
	padding: .5em;
	color: #FFF;
	text-transform: uppercase;
	font-weight: 600;
}

#cd-top-nav a.current {
	background-color: #242e30;
}

.no-touch #cd-top-nav a:hover {
	color: rgba(255, 255, 255, 0.7);
}

@media only screen and (min-width: 768px) {
	#cd-top-nav {
		display: block;
	}
}

#cd-menu-trigger {
	position: absolute;
	right: 15px;
	top: 0;
	height: 40px;
	width: 40px;
	border: 1px solid rgba(0, 0, 0, 0.09);
	background-color: #fff;
	padding: 0px;
}

#cd-menu-trigger .cd-menu-text {
	height: 100%;
	text-transform: uppercase;
	color: #FFF;
	font-weight: 600;
	display: none;
}

#cd-menu-trigger .cd-menu-icon {
	/* this span is the central line in the menu menu */
	display: inline-block;
	position: absolute;
	left: 50%;
	top: 18px;
	bottom: auto;
	right: auto;
	-webkit-transform: translateX(-50%) translateY(-50%);
	-moz-transform: translateX(-50%) translateY(-50%);
	-ms-transform: translateX(-50%) translateY(-50%);
	-o-transform: translateX(-50%) translateY(-50%);
	transform: translateX(-50%) translateY(-50%);
	width: 18px;
	height: 2px;
	background-color: #337ab7;
	/* these are the upper and lower lines in the menu menu */
}

#cd-menu-trigger .cd-menu-icon::before, #cd-menu-trigger .cd-menu-icon:after
	{
	content: '';
	width: 100%;
	height: 100%;
	position: absolute;
	background-color: inherit;
	left: 0;
}

#cd-menu-trigger .cd-menu-icon::before {
	bottom: 5px;
}

#cd-menu-trigger .cd-menu-icon::after {
	top: 5px;
}

#cd-menu-trigger.is-clicked .cd-menu-icon {
	background-color: rgba(255, 255, 255, 0);
}

#cd-menu-trigger.is-clicked .cd-menu-icon::before, #cd-menu-trigger.is-clicked .cd-menu-icon::after
	{
	background-color: #337ab7;
}

#cd-menu-trigger.is-clicked .cd-menu-icon::before {
	bottom: 0;
	-webkit-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-o-transform: rotate(45deg);
	transform: rotate(45deg);
}

#cd-menu-trigger.is-clicked .cd-menu-icon::after {
	top: 0;
	-webkit-transform: rotate(-45deg);
	-moz-transform: rotate(-45deg);
	-ms-transform: rotate(-45deg);
	-o-transform: rotate(-45deg);
	transform: rotate(-45deg);
}

#cd-menu-trigger:hover {
	background: #346abb;
}

#cd-menu-trigger:hover .cd-menu-icon {
	
}

@media only screen and (min-width: 768px) {
	#cd-menu-trigger .cd-menu-text {
		display: inline-block;
		line-height: 70px;
	}
	#cd-menu-trigger .cd-menu-icon {
		left: auto;
		right: 10px;
		-webkit-transform: translateX(0);
		-moz-transform: translateX(0);
		-ms-transform: translateX(0);
		-o-transform: translateX(0);
		transform: translateX(0);
	}
}

#cd-lateral-nav {
	position: fixed;
	height: 100%;
	right: 0;
	top: 0;
	visibility: hidden;
	/* the secondary navigation is covered by the main element */
	z-index: 1;
	width: 320px;
	background-color: #fff;
	overflow-y: auto;
	/* Force Hardware Acceleration in WebKit */
	-webkit-transform: translateZ(0);
	-webkit-backface-visibility: hidden;
	-webkit-transition: -webkit-transform .4s 0s, visibility 0s .4s;
	-moz-transition: -moz-transform .4s 0s, visibility 0s .4s;
	transition: transform .4s 0s, visibility 0s .4s;
	/* this creates the subtle slide in animation of the navigation */
	-webkit-transform: translateX(80px);
	-moz-transform: translateX(80px);
	-ms-transform: translateX(80px);
	-o-transform: translateX(80px);
	transform: translateX(80px);
}

#cd-lateral-nav .cd-navigation {
	margin: 10px 0 16px;
}

#cd-lateral-nav .sub-menu {
	padding: 0 10px 20px 15px;
	display: none;
}

.cd-navigation li a {
	display: block;
	line-height: 36px;
	padding: 0 0px 0 20px;
	text-transform: uppercase;
	letter-spacing: 0.5px;
	font-size: 16px;
	font-weight: bold;
}

#cd-lateral-nav .sub-menu li a {
	text-transform: none;
	font-weight: normal;
}

.cd-navigation li a.current {
	background-color: #337ab7;
	color: #FFF;
}

.no-touch .cd-navigation a:hover {
	color: #FFF;
	background: #337ab7;
}

@media only screen and (min-width: 768px) {
	#cd-lateral-nav .cd-navigation {
		margin: 20px 0;
	}
}

#cd-lateral-nav.lateral-menu-is-open {
	-webkit-transform: translateX(0);
	-moz-transform: translateX(0);
	-ms-transform: translateX(0);
	-o-transform: translateX(0);
	transform: translateX(0);
	visibility: visible;
	-webkit-transition: -webkit-transform .4s 0s, visibility 0s 0s;
	-moz-transition: -moz-transform .4s 0s, visibility 0s 0s;
	transition: transform .4s 0s, visibility 0s 0s;
	/* smooth the scrolling on touch devices - webkit browsers */
	-webkit-overflow-scrolling: touch;
}
/* style menu items which have a submenu  */
#cd-lateral-nav .item-has-children>a {
	position: relative;
}

#cd-lateral-nav .item-has-children>a::after {
	content: '';
	display: block;
	height: 11px;
	width: 8px;
	position: absolute;
	top: 50%;
	bottom: auto;
	-webkit-transform: translateY(-50%);
	-moz-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	-o-transform: translateY(-50%);
	transform: translateY(-50%);
	right: 1em;
	background: url("../img/cd-arrow.svg") no-repeat center center;
	background-size: 8px 11px;
	-webkit-transition-property: -webkit-transform;
	-moz-transition-property: -moz-transform;
	transition-property: transform;
	-webkit-transition-duration: 0.2s;
	-moz-transition-duration: 0.2s;
	transition-duration: 0.2s;
}

#cd-lateral-nav .item-has-children>a.submenu-open::after {
	-webkit-transform: translateY(-50%) rotate(90deg);
	-moz-transform: translateY(-50%) rotate(90deg);
	-ms-transform: translateY(-50%) rotate(90deg);
	-o-transform: translateY(-50%) rotate(90deg);
	transform: translateY(-50%) rotate(90deg);
}

#cd-lateral-nav .socials {
	padding: 0 32px;
}

#cd-lateral-nav .socials:after {
	content: "";
	display: table;
	clear: both;
}

#cd-lateral-nav .socials a {
	height: 32px;
	width: 32px;
	float: left;
	padding: 0;
	background-image: url("../img/cd-socials.svg");
	background-repeat: no-repeat;
	background-size: 128px 64px;
	background-color: #FFF;
	margin-right: .5em;
	border-radius: 0.25em;
}

#cd-lateral-nav .socials a.cd-twitter {
	background-position: 0 0;
}

#cd-lateral-nav .socials a.cd-github {
	background-position: -32px 0;
}

#cd-lateral-nav .socials a.cd-facebook {
	background-position: -64px 0;
}

#cd-lateral-nav .socials a.cd-google {
	background-position: -96px 0;
}

.no-touch #cd-lateral-nav .socials a:hover {
	background-color: #4e6361;
}

.no-touch #cd-lateral-nav .socials a:hover.cd-twitter {
	background-position: 0 -32px;
}

.no-touch #cd-lateral-nav .socials a:hover.cd-github {
	background-position: -32px -32px;
}

.no-touch #cd-lateral-nav .socials a:hover.cd-facebook {
	background-position: -64px -32px;
}

.no-touch #cd-lateral-nav .socials a:hover.cd-google {
	background-position: -96px -32px;
}

.resume {
	padding: 0px;
}

#content {
	
}

#content.bg {
	background: url(../images/parallax-bg.jpg) fixed center top no-repeat;
	display: inline-block;
	width: 100%;
	padding-top: 100px;
}

#content.bg .resume {
	background: none;
	padding: 0px;
	position: relative;
}

#content.bg .resume:before {
	content: "";
	position: absolute;
	height: 100%;
	width: 100%;
	left: 0px;
	top: 225px;
}

.resume {
	padding: 100px 0;
}

#content.bg .resume {
	background: none;
	padding: 0px;
	position: relative;
}

#content.bg .resume:before {
	content: "";
	position: absolute;
	height: 100%;
	width: 100%;
	left: 0px;
	top: 225px;
}

.rb-resume-block {
	padding: 0;
}

.side-bar {
	
}

.side-bar li {
	
}

.side-bar .personal-info {
	padding: 25px;
	border-bottom: 1px solid #e6e6e6;
}

.side-bar .personal-info li span {
	width: 50%;
	float: left;
	text-transform: uppercase;
	font-weight: bold;
}

.side-bar .personal-info li p {
	margin: 0px;
	line-height: 30px;
}

.side-bar .personal-info li a {
	color: #346abb;
}

.bor-btm {
	border-bottom: 1px solid #e6e6e6;
}

.side-bar .attach li p {
	margin: 0px;
	color: #346abb;
	line-height: 40px;
	font-weight: bold;
}

.side-bar .attach li p a {
	margin: 0px;
	color: #346abb;
	font-weight: bold;
	float: right;
	margin-left: 10px;
}

.side-bar .attach li p a:hover {
	color: #333;
}

.side-bar .attach li img {
	float: left;
	margin-right: 10px;
	line-height: 40px;
	margin-top: 12px;
}

.side-bar .attach li {
	background: #f5f5f5;
	border: 1px solid #e6e6e6;
	line-height: 40px;
	padding: 0 15px;
	margin-bottom: 20px;
}

.tab-content {
	margin-bottom: 20px;
}

.tab-content p {
	font-size: 14px;
	line-height: 24px;
	margin-bottom: 0px;
}

.inside-sec {
	padding-bottom: 20px;
}

.inside-sec.blog {
	padding-bottom: 0;
}

.non-inside-sec .inside-sec {
	display: block;
}

.non-inside-sec .inside-sec .bio-info .skills h5.margin-top-30 {
	margin-top: 0 !important;
}

.non-inside-sec .inside-sec .bio-info {
	padding-top: 0 !important;
}

.bio-info p {
	line-height: 24px;
	letter-spacing: 0px;
}

.about-skills .bio-info {
	padding: 0 !important;
}

.about-skills .bio-info .skills .panel-group.accordion {
	padding-left: 30px;
	padding-right: 30px;
}

.padding-30 {
	padding: 30px !important;
}

.inside-sec {
	width: 100%;
	display: inline-block;
}

.inside-sec {
	padding-bottom: 20px;
}

.margin-top-30 {
	margin-top: 30px !important;
}

.exp-history .sun {
	font-size: 15px;
	font-weight: bold;
	display: inline-block;
	width: 130px;
}

.exp-history .company-logo {
	position: absolute;
	right: 0px;
}

.exp-history a {
	color: #346abb;
}

.exp-history a:hover {
	color: #333;
}

.exp-history .exp {
	margin-bottom: 40px;
}

.exp-history h6 {
	margin-top: 0px;
	letter-spacing: 1px;
	font-size: 15px;
	font-weight: bold;
}

.exp-history p {
	margin-bottom: 0px;
	font-size: 15px;
	line-height: 24px;
}

.exp-history .media-body {
	position: relative;
}

.exp-history .diploma {
	color: #346abb;
	font-size: 13px;
}

.exp-history .diploma i {
	height: 30px;
	width: 30px;
	border: 1px solid #e6e6e6;
	text-align: center;
	line-height: 28px;
	margin-right: 5px;
}

.resune-edit .exp-history p {
	max-width: 430px;
}

.post-type-small .media-left a {
	height: 70px;
	width: 70px;
	display: inline-block;
	margin-right: 10px;
}

.latest-post li .media-left a {
	width: 60px;
}

.latest-post li .media-left a img {
	width: 100%;
}

h1, h2, h3, h4, h5, h6 {
	color: #262626;
	font-weight: bold;
	text-transform: uppercase;
	font-family: 'Raleway', sans-serif;
}

h1 {
	font-size: 48px;
}

h2 {
	font-size: 40px;
	font-weight: bold;
}

h3 {
	font-size: 36px;
}

h4 {
	font-size: 30px;
}

h5 {
	font-size: 18px;
	padding: 20px 0;
	margin: 0px;
	color: #333333;
	letter-spacing: 1px;
}

h6 {
	font-size: 16px;
}

p {
	color: #333333;
	font-size: 13px;
	line-height: 20px;
	letter-spacing: 0.5px;
	text-rendering: optimizeLegibility;
}

a {
	-webkit-transition: all 0.4s ease-in-out;
	-moz-transition: all 0.4s ease-in-out;
	-o-transition: all 0.4s ease-in-out;
	-ms-transition: all 0.4s ease-in-out;
	transition: all 0.4s ease-in-out;
	text-decoration: none !important;
}

img {
	-webkit-transition: 0.4s ease-in-out;
	-moz-transition: 0.4s ease-in-out;
	-ms-transition: 0.4s ease-in-out;
	-o-transition: 0.4s ease-in-out;
	transition: 0.4s ease-in-out;
	display: inline-block !important;
}

ul {
	margin-bottom: 0px;
}

li {
	list-style: none;
}

a {
	text-decoration: none;
	color: #141414;
}

.position-center-center {
	left: 50%;
	position: absolute;
	top: 50%;
	-webkit-transform: translate(-50%, -50%);
	-moz-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.margin-top-10 {
	margin-top: 10px !important;
}
</style>
<%@include file="./shared/footer.jsp"%>
<script src="<c:url value='/resources/js/jQuery.print.js' />"></script>
<script>

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

	var name_details;
	var x = window.location.href;
	var applicantdetails_id =getUrlParameter('applicantdetails_id');
	var app_id = getUrlParameter('app_id');
	var jobtitle;

	$(document)
			.ready(
					function() {
						
						 $.ajax({
			                 type: "POST",
			                 url: "${pageContext.servletContext.contextPath}/user/getByAppId",
			                 dataType: "json",
			                 data:{app_id:app_id},
			                 complete: function(data) {
			                	 jobtitle = data.responseJSON.jobtitle;
			                	 obj = JSON.parse(data.responseJSON.profilejson);
                                 console.log(obj);
								name_details = obj.firstname+ " "+ obj.lastname + "--- "  +jobtitle;
								$('#namedetails').text(name_details);
								$()
										
										$('#email').text(
												obj.email);
										$('#mobile').text(
												obj.contactno);
										$('#location').text(
												obj.locations);

										if (obj.jobtitle0 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration0
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle0
																	+ '</h6><p>'
																	+ obj.companyname0
																	+ '</p>'
																	+ '<p>'
																	+ obj.location0
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details0
																	+ '</p></div></div>');
										}
										if (obj.jobtitle1 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration1
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle1
																	+ '</h6><p>'
																	+ obj.companyname1
																	+ '</p>'
																	+ '<p>'
																	+ obj.location1
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details1
																	+ '</p></div></div>');
										}
										if (obj.jobtitle2 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration2
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle2
																	+ '</h6><p>'
																	+ obj.companyname2
																	+ '</p>'
																	+ '<p>'
																	+ obj.location2
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details2
																	+ '</p></div></div>');
										}
										if (obj.jobtitle3 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration3
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle3
																	+ '</h6><p>'
																	+ obj.companyname3
																	+ '</p>'
																	+ '<p>'
																	+ obj.location3
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details3
																	+ '</p></div></div>');
										}
										if (obj.jobtitle4 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration4
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle4
																	+ '</h6><p>'
																	+ obj.companyname4
																	+ '</p>'
																	+ '<p>'
																	+ obj.location4
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details4
																	+ '</p></div></div>');
										}
										if (obj.jobtitle5 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration5
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle5
																	+ '</h6><p>'
																	+ obj.companyname5
																	+ '</p>'
																	+ '<p>'
																	+ obj.location5
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details5
																	+ '</p></div></div>');
										}
										if (obj.jobtitle6 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration6
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle6
																	+ '</h6><p>'
																	+ obj.companyname6
																	+ '</p>'
																	+ '<p>'
																	+ obj.location6
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details6
																	+ '</p></div></div>');
										}
										if (obj.jobtitle7 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration7
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle7
																	+ '</h6><p>'
																	+ obj.companyname7
																	+ '</p>'
																	+ '<p>'
																	+ obj.location7
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details7
																	+ '</p></div></div>');
										}
										if (obj.jobtitle8 != "") {

											$("#professional")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.duration8
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.jobtitle8
																	+ '</h6><p>'
																	+ obj.companyname8
																	+ '</p>'
																	+ '<p>'
																	+ obj.location8
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.details8
																	+ '</p></div></div>');
										}

										if (obj.obtention0 != "") {
											$("#academic")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.obtention0
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.qualification0
																	+ '</h6><p>'
																	+ obj.university0
																	+ '</p>'
																	+ '<p>'
																	+ obj.degree0
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.edudetails0
																	+ '</p></div></div>');

										}
										if (obj.obtention1 != "") {
											$("#academic")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.obtention1
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.qualification1
																	+ '</h6><p>'
																	+ obj.university1
																	+ '</p>'
																	+ '<p>'
																	+ obj.degree1
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.edudetails1
																	+ '</p></div></div>');

										}
										if (obj.obtention2 != "") {
											$("#academic")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.obtention1
																	+ '</span></div><div class="media-body"><h6>'
																	+ obj.qualification2
																	+ '</h6><p>'
																	+ obj.university2
																	+ '</p>'
																	+ '<p>'
																	+ obj.degree2
																	+ '</p><div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.edudetails2
																	+ '</p></div></div>');

										}
										if (obj.skills0 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills0
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages0
																	+ '</p></div></div>');

										}
										if (obj.skills1 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills1
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages1
																	+ '</p></div></div>');

										}
										if (obj.skills2 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills2
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages2
																	+ '</p></div></div>');

										}
										if (obj.skills3 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills3
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages3
																	+ '</p></div></div>');

										}
										if (obj.skills4 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills4
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages4
																	+ '</p></div></div>');

										}
										if (obj.skills5 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills5
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages5
																	+ '</p></div></div>');

										}
										if (obj.skills6 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills6
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages6
																	+ '</p></div></div>');

										}
										if (obj.skills7 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills7
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages7
																	+ '</p></div></div>');

										}
										if (obj.skills8 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills8
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages8
																	+ '</p></div></div>');

										}
										if (obj.skills8 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills8
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages8
																	+ '</p></div></div>');

										}
										if (obj.skills9 != "") {
											$("#otherskills")
													.append(
															'<div class="exp"><div class="media-left"><span class="sun">'
																	+ obj.skills9
																	+ '</span></div><div class="media-body">'
																	+ '<div class="company-logo"><img src="" alt=""></div><p class="margin-top-10">'
																	+ obj.languages9
																	+ '</p></div></div>');

										}

									}

								});

					});
</script>