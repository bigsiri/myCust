<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" scope="request">
	<c:out value="<%=RequestContextUtils.getLocale(request).getLanguage()%>"/>
</c:set>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

		<title> bigeye - myCust</title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/bootstrap.min.css"/>">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/font-awesome.min.css"/>">

		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/smartadmin-production-plugins.min.css"/>">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/smartadmin-production.min.css"/>">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/smartadmin-skins.min.css"/>">

		<!-- SmartAdmin RTL Support -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/smartadmin-rtl.min.css"/>">

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/my_style.css"/>">

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="<c:url value="/img/bigeye/favicon.ico"/>" type="image/x-icon">
		<link rel="icon" href="<c:url value="/img/bigeye/favicon.ico"/>" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

		<!-- Specifying a Webpage Icon for Web Clip
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
		<link rel="apple-touch-icon" href="<c:url value="/img/splash/sptouch-icon-iphone.png"/>">
		<link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/img/splash/touch-icon-ipad.png"/>">
		<link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/img/splash/touch-icon-iphone-retina.png"/>">
		<link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/img/splash/touch-icon-ipad-retina.png"/>">

		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">


		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<%-- 		<script data-pace-options='{ "restartOnRequestAfter": true }' src="<c:url value="js/plugin/pace/pace.min.js"/>"></script> --%>

		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script>
			if (!window.jQuery) {
				document.write('<script src="<c:url value="/js/libs/jquery-2.1.1.min.js"/>"><\/script>');
			}
		</script>

		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="<c:url value="/js/libs/jquery-ui-1.10.3.min.js"/>"><\/script>');
			}
		</script>


		<!-- IMPORTANT: APP CONFIG -->
		<script type="text/javascript" src="<c:url value="/js/app.config.js"/>"></script>

		<!-- BOOTSTRAP JS -->
	 	<script type="text/javascript" src="<c:url value="/js/bootstrap/bootstrap.min.js"/>"></script>

		<!-- JQUERY VALIDATE -->
		<script type="text/javascript" src="<c:url value="/js/plugin/jquery-validate/jquery.validate.min.js"/>"></script>

		<!-- JQUERY MASKED INPUT -->
		<script type="text/javascript" src="<c:url value="/js/plugin/masked-input/jquery.maskedinput.min.js"/>"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script type="text/javascript" src="<c:url value="/js/plugin/select2/select2.min.js"/>"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script type="text/javascript" src="<c:url value="/js/plugin/bootstrap-slider/bootstrap-slider.min.js"/>"></script>

		<script type="text/javascript" src="<c:url value="/js/plugin/datatables/jquery.dataTables.min.js"/>"></script>
		
		<script type="text/javascript" src="<c:url value="/js/plugin/jquery-form/jquery-form.min.js"/>"></script> 

		<!-- browser msie issue fix -->
		<script type="text/javascript" src="<c:url value="/js/plugin/msie-fix/jquery.mb.browser.min.js"/>"></script>

		<script type="text/javascript">
		    var _contextPath = "${pageContext.request.contextPath}";
		</script>
	</head>

	<body class="menu-on-top">
		<div class="pace  pace-inactive">
			<div class="pace-progress" data-progress-text="100%" data-progress="99" style="width: 100%;">
  				<div class="pace-progress-inner"></div>
			</div>
			<div class="pace-activity"></div>
		</div>

		<jsp:include page="/WEB-INF/templates/header.jsp" />
		<jsp:include page="/WEB-INF/templates/menu.jsp" />

		<!-- MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

				<span class="ribbon-button-alignment"> 
					<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
						<i class="fa fa-refresh"></i>
					</span> 
				</span>
			</div>
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

				<!-- row -->
				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
						<div class="row">
							<div class="col-sm-12">
								<div class="text-center error-box">
									<h1 class="error-text-2 bounceInDown animated"> Error 404 <span class="particle particle--c"></span><span class="particle particle--a"></span><span class="particle particle--b"></span></h1>
									<h2 class="font-xl"><strong><i class="fa fa-fw fa-warning fa-lg text-warning"></i> Page <u>Not</u> Found</strong></h2>
									<br />
									<p class="lead">
										The page you requested could not be found, either contact your administrator or try again. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from
									</p>
								</div>
				
							</div>
				
						</div>
				
					</div>

				<!-- end row -->

			</div>
			
			</div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->
	</body>
</html>