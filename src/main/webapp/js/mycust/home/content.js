$(document).ready(function(){
	
	$("#userSelect" ).change(function() {
		if($("#userSelect option:selected").val() != ""){
			loadAccountsTable();		  
		}
	});
	
	
	$("#accountSelect" ).change(function() {
		if($("#accountSelect option:selected").val() != ""){
			loadUsersTable();		  
		}
	});



});

function loadAccountsTable() {
	var userId = $("#userSelect").val();
	var url = _contextPath + "/ws/secure/account/user/"+userId;
	
	
	var table = $('#accountsTable').dataTable();
	
	if (table != undefined) {
		table.fnClearTable();
	};

	table.fnDestroy();
	var responsiveHelper_datatable_fixed_column = undefined;
	var breakpointDefinition = {
			desk : 1600,
			tablet : 1024,
			phone : 480
		};
  table = $('#accountsTable').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""f>t<"F"lp>',
		"iDisplayLength": 25,
		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Tout"]],
        "serverSide": false,
        "order": [[ 0, "asc" ]],
        "ajax": {
            "url": url,
            "type": "GET"
        },
		"autoWidth" : true,
		"preDrawCallback" : function() {
			if (!responsiveHelper_datatable_fixed_column) {
				responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#accountsTable'), breakpointDefinition);
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
                      { "mData": "accountIdentifier" },
                      { "mData": "status" },
                      { "mData": "order.editionCode" },
                      { "mData": "order.pricingDuration" }
                  ]
});
  
}


function loadUsersTable() {
	var accountId = $("#accountSelect").val();
	var url = _contextPath + "/ws/secure/user/account/"+accountId;
	
	
	var table = $('#usersTable').dataTable();
	
	if (table != undefined) {
		table.fnClearTable();
	};

	table.fnDestroy();
	var responsiveHelper_datatable_fixed_column = undefined;
	var breakpointDefinition = {
			desk : 1600,
			tablet : 1024,
			phone : 480
		};
  table = $('#usersTable').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""f>t<"F"lp>',
		"iDisplayLength": 25,
		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Tout"]],
        "serverSide": false,
        "order": [[ 0, "asc" ]],
        "ajax": {
            "url": url,
            "type": "GET"
        },
		"autoWidth" : true,
		"preDrawCallback" : function() {
			if (!responsiveHelper_datatable_fixed_column) {
				responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#usersTable'), breakpointDefinition);
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
                
                      { "mData": "firstname" },
                      { "mData": "lastname" },
                      { "mData": "username" },
                      { "mData": "uuid" },
                      { "mData": "openId" },
                      { "mData": "lastLoginTime" }
                  ]
});
  
}
