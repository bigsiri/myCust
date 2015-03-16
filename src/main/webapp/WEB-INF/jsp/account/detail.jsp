<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="main" role="main">
	<div id="ribbon">

	</div>
	<div id="content">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<h1 class="page-title txt-color-blueDark">
					<i class="fa fa-table fa-fw "></i>
					<fmt:message key="label.bibliotheque.assemblage" />
				</h1>
			</div>
		</div>

		<section id="widget-grid" class="">

			<!-- row -->
			<div class="row">

				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="well">
						<form>
							<fieldset>
								<div class="form-group col-sm-6 col-md-6 col-lg-5">
									<label class="control-label col-md-2"><h3>
											<fmt:message key="label.account" />
										</h3></label>
									<div class="col-md-10">
										<select id="accountselect" name="accounts" style="width: 100%" class="select2">
											<option value=""><fmt:message key="label.select.account" /></option>
											<c:forEach items="${accounts}" var="account">
												<option value="${account.identifiantString}">${account.nom}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</article>


			</div>

			<!-- row -->
			<div id="contentTable" style="display: none;" class="row">

				<article
					class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">

					<div
						class="jarviswidget jarviswidget-color-blueDark jarviswidget-sortable"
						id="wid-id-1" data-widget-editbutton="false"
						data-widget-colorbutton="false" data-widget-deletebutton="false"
						data-widget-collapsed="true" data-widget-fullscreenbutton="false"
						role="widget">
						<header id="tableHeader" role="heading">
							<div class="jarviswidget-ctrls" role="menu"></div>
							<span class="widget-icon"> <i class="fa fa-table"></i>
							</span>
							<h2></h2>

							<span class="jarviswidget-loader"><i
								class="fa fa-refresh fa-spin"></i></span>
						</header>

						<!-- widget div-->
						<div role="content">

							<!-- widget edit box -->
							<div class="jarviswidget-editbox">
								<!-- This area used as dropdown edit box -->

							</div>
							<!-- end widget edit box -->

							<!-- widget content -->
							<div class="widget-body no-padding">
								<div class="bcontrol col-xs-6 col-sm-6 col-md-6 col-lg-6">
									<a class="btn btn-primary" href="#" data-toggle="modal"
										data-target="#popupAssociation"
										onclick="populatePopupCreerPiece()"><fmt:message
											key="label.piece.create" /></a>
								</div>
								<table id="datatable_fixed_column"
									class="table table-striped table-bordered" width="100%">

									<thead>
										<tr>
											<th><fmt:message key="label.elementid" /></th>
											<th data-class="expand"><fmt:message
													key="label.numeroitem" /></th>
											<th data-hide="phone"><fmt:message
													key="label.description" /></th>
											<th data-hide="phone"><fmt:message
													key="label.materialspec" /></th>
											<th data-hide="phone"><fmt:message
													key="label.materialdesc" /></th>
											<th data-hide="phone"><fmt:message
													key="label.basenumber" /></th>
											<th data-hide="phone"><fmt:message
													key="label.remplacepar" /></th>
						
										</tr>
									</thead>


								</table>

							</div>
							<!-- end widget content -->

						</div>
						<!-- end widget div -->

					</div>
					<!-- end widget -->

				</article>
				<!-- WIDGET END -->

			</div>

		</section>


	</div>
</div>




<script
	src="<c:url value="/js/plugin/datatables/dataTables.colVis.min.js"/>"></script>
<script
	src="<c:url value="/js/plugin/datatables/dataTables.tableTools.min.js"/>"></script>
<script
	src="<c:url value="/js/plugin/datatables/dataTables.bootstrap.min.js"/>"></script>
<script
	src="<c:url value="/js/plugin/datatable-responsive/datatables.responsive.min.js"/>"></script>

<script src="<c:url value="/js/account/detail.js"/>"></script>