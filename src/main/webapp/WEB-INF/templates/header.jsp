<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- HEADER -->
<header id="header">

	<div class="pull-right">

		<div id="hide-menu" class="btn-header pull-right">
			<span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
		</div>
		<ul id="mobile-profile-img" class="header-dropdown-list hidden-xs padding-5">
			<li class="">
				<a href="#" class="dropdown-toggle no-margin userdropdown" data-toggle="dropdown">
					<img src="<c:url value="/img/mycust/ico-profil-A.png"/>" alt="Profil" class="online" />
				</a>
				<ul class="dropdown-menu pull-right">
					
					<sec:authorize access="isAuthenticated()">
							<li>
    								<i class="fa fa-user fa-lg"></i> <sec:authentication property="principal.firstname" /><sec:authentication property="principal.lastname" />
    						</li>
    						<li>
    							<i class="fa fa-clock-o fa-lg"></i> <sec:authentication property="principal.lastLoginTime" />		
    						</li> 
					</sec:authorize>
						
					
					<li>
						<a href="<c:url value="/logout"/>" class="" data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i> <strong>Logout</strong></a>
					</li>
				</ul>
			</li>
		</ul>

		<div id="logout" class="btn-header transparent pull-right">
			<span> <a href="<c:url value="/logout"/>" title="Sign Out" data-action="userLogout"><i class="fa fa-sign-out"></i></a></span>
		</div>


	</div>

</header>