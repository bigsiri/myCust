<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
		<div class="well no-padding">
			<form action="<c:url value='/loginProcess'/>" id="valid" class="smart-form client-form" method="post">
				<span class="logo"> <img src="<c:url value="/img/bigeye/logo-login.png"/>" alt="bigeye">
				</span>
				<header> Ouverture de session </header>
				<fieldset>
					<section>
						<label class="label" for="req1">Nom d'utilisateur</label> 
						<label class="input"> 
							<i class="icon-append fa fa-user"></i>
							<input type="text" name="j_username" class="validate[required]" id="req1" />
							<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Entrez votre nom d'utilisateur</b>
						</label>
					</section>
					<section>
						<label class="label" for="req2">Mot de passe</label>
						<label class="input">
							<i class="icon-append fa fa-lock"></i>
							<input type="password" name="j_password" class="validate[required]" id="req2" /> 
							<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Entrez votre mot de passe</b>
						</label>
					</section>
				</fieldset>
				<footer>
					<button type="submit" class="btn btn-primary">Entrer</button>
				</footer>
			</form>

			<c:if test="${not empty param.login_error}">
				<div class="nNote nFailure hideit">
					<div class="alert alert-danger fade in">
						<button class="close" data-dismiss="alert">×</button>
						<i class="fa-fw fa fa-times"></i>
						<p>
							<strong>Error!</strong>
							<fmt:message key="login.label.fail" />
						</p>
					</div>
				</div>
			</c:if>

		</div>
	</div>
</div>
