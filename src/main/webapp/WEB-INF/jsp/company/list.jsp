<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="main" role="main"> 
<div id="ribbon">

				<span class="ribbon-button-alignment"> 
					<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh" rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
						<i class="fa fa-refresh"></i>
					</span> 
				</span>

			</div>
	<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 
					<fmt:message key="label.companies.list" />
			</h1>
		</div>
	</div>

<section id="widget-grid" class="">
                
                	<!-- row -->
					<div class="row">
				
                    </div>
				
					<!-- row -->
					<div class="row">
				
						<!-- NEW WIDGET START -->
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blueDark jarviswidget-sortable" id="wid-id-1" data-widget-editbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" role="widget">
								<header role="heading"><div class="jarviswidget-ctrls" role="menu"> </div>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2><fmt:message key="label.companies.list" /></h2>
				
								<span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>
				
								<!-- widget div-->
								<div role="content">
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>

									<div class="widget-body no-padding">
										<table id="datatable_fixed_column" class="table table-striped table-bordered" width="100%">
					
										<thead>
											<tr>
												<th><fmt:message key="label.company.name" /></th>
												<th><fmt:message key="label.company.email" /></th>
												<th><fmt:message key="label.company.uuid" /></th>
												<th><fmt:message key="label.company.phoneNumber" /></th>
												<th><fmt:message key="label.company.website" /></th>
											</tr>
										</thead>
										<tbody>
												<c:forEach var="company" items="${companies}">
													<tr>
														<td><c:out value="${company.name}"/></td>
														<td><c:out value="${company.email}"/></td>
														<td><c:out value="${company.uuid}"/></td>
														<td><c:out value="${company.phoneNumber}"/></td>
														<td><c:out value="${company.website}"/></td>
													</tr>
												</c:forEach>
												</tbody>
										</table>
				
									</div>
				
								</div>
				
							</div>

						</article>
				
					</div>
				
				</section>
	</div>
</div>

<script  src="<c:url value="/js/plugin/datatables/dataTables.colVis.min.js"/>"></script>
<script  src="<c:url value="/js/plugin/datatables/dataTables.tableTools.min.js"/>"></script>
<script  src="<c:url value="/js/plugin/datatables/dataTables.bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/plugin/datatable-responsive/datatables.responsive.min.js"/>"></script>

<script src="<c:url value="/js/users/list.js"/>"></script>