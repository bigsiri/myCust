$(document).ready(function(){
	
	$("#accountSelect" ).change(function() {
		if($("#accountSelect option:selected").val() != ""){
			 loadMatriceItemsTable();		  
		}
		
		//Show content
		$('#contentTable').show();
		
		
		});
	
	  
});


function loadMatriceItemsTable() {
	var accountId = $("#accountSelect").val();
	var url = "/ws/bibliotheque/list/pagineable/accountid";
	
	if (table != undefined) {
		table.fnClearTable();
	};
	
	var table = $('#datatable_fixed_column').dataTable();
	table.fnDestroy();
	var responsiveHelper_datatable_fixed_column = undefined;
	var breakpointDefinition = {
			desk : 1600,
			tablet : 1024,
			phone : 480
		};
  table = $('#datatable_fixed_column').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""f>t<"F"lp>',
		"iDisplayLength": 25,
		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Tout"]],
        "serverSide": true,
        "order": [[ 5, "asc" ]],
        "ajax": {
            "url": url,
            "type": "GET",
            "data": function ( d ) {
                d.accountid = accountId;
            }
        },
		"autoWidth" : true,
		"preDrawCallback" : function() {
			if (!responsiveHelper_datatable_fixed_column) {
				responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_fixed_column.respond();
		},
		"oLanguage": {
            "oPaginate": {
                "sFirst": "Premier",
                "sPrevious": "Précédent", 
                "sNext": "Suivant", 
                "sLast": "Dernier" 
            },
            "sEmptyTable": "Aucune donnée à afficher",
            "sZeroRecords": "Aucune donnée trouvée"
        },
        "aoColumns": [
      				{
						    "mData": null,
						    "bSortable": true,
						   "mRender": function (o) {   if(!o.infosMethode){value="";}else {value=o.infosMethode.remplacePar;} return value; }
						
						},
	     				{
						    "mData": null,
						    "bSortable": true,
						   "mRender": function (o) {   if(!o.infosMethode){value="";}else {value=o.infosMethode.autorite;} return value; }
						
						},
	     				{
						    "mData": null,
						    "bSortable": true,
						   "mRender": function (o) {   if(!o.infosMethode){value="";}else {value=o.infosMethode.findNumber;} return value; }
						
						},
                      
                      { "mData": "quantite" }
                  ]
});
  
}