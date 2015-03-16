<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<aside id="left-panel">

	<!-- NAVIGATION : This navigation is also responsive-->
	<nav>
		<ul>
			<li>
            	<span id="logo"><img src="" alt="bigeye"></span>
			</li>
			<li <c:if test="${menuSectionName eq 'home'}">class="active"</c:if>>
				<a href="<c:url value="/home.html"/>"><span class="menu-item-parent">Home</span></a>
			</li>
			<li <c:if test="${menuSectionName eq 'users'}">class="active"</c:if>>
				<a href="<c:url value="/users/list.html"/>"><span class="menu-item-parent">Users</span></a>
			</li>
			<li <c:if test="${menuSectionName eq 'companies'}">class="active"</c:if>>
				<a href="<c:url value="/company/list.html"/>"><span class="menu-item-parent">Company</span></a>
			</li>
		</ul>
	</nav>
	<span class="minifyme" data-action="minifyMenu"> 
		<i class="fa fa-arrow-circle-left hit"></i> 
	</span>
</aside>