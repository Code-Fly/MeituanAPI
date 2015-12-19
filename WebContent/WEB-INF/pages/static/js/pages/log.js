$(document).ready(function() {
	$("#export-excel").click(function(e) {
		var grid = $("#grid").data("kendoGrid");
		grid.saveAsExcel();
	});

	$("#reset-dp").click(function() {
		end.max(new Date(2099, 11, 31));
		end.min(new Date(1900, 0, 1));
		end.value(null);
		start.max(new Date(2099, 11, 31));
		start.min(new Date(1900, 0, 1));
		start.value(null);
	});

	
	$("#submit-dp").click(function() {
		reloadGrid();
	});

	var today = new Date();

	var start = $("#start").kendoDateTimePicker({
		change : startChange,
		format : "yyyy-MM-dd HH:mm:ss",
		culture : "zh-CN"
	}).data("kendoDateTimePicker");
	//start.value(new Date(today.getFullYear(),today.getMonth(),today.getDate(),"00","00","00"));
	
	var end = $("#end").kendoDateTimePicker({
		change : endChange,
		format : "yyyy-MM-dd HH:mm:ss",
		culture : "zh-CN"
	}).data("kendoDateTimePicker");
	//end.value(today);
	
	$("#grid").kendoGrid({
		excel : {
			fileName : "Export.xlsx",
			filterable : true,
			allPages : true
		},
		autoBind: false,
		sortable : true,
		filterable : true,
		pageable : {
			refresh : true,
			pageSizes : true,
			buttonCount : 5
		},
		selectable : "row",
		dataBound : function(e) {
			var data = this.dataSource.data();
			$.each(data, function(i, row) {
				if (row.stopFlag == 1) {
					$('tr[data-uid="' + row.uid + '"] ').css("color", "red");
				}
			});
		},
		columns : [ {
			locked : true,
			field : "logID",
			title : "日志ID",
			width : 100
		},{
			field : "logTime",
			title : "日志时间",
			width : 250
		}, {
			field : "logInfo",
			title : "日志信息",
			width : 550
		}, {
			field : "sim",
			title : "号码",
			width : 100
		}],

	});	

	function reloadGrid() {
		if (!validate()) {
			return;
		}
		var grid = $("#grid").data("kendoGrid");
		grid.setOptions({
			dataSource : {
				transport : {
					read : {
						url : _ctx + "/api/log/query?beginDate=" + $("#start").val() + "&endDate=" + $("#end").val(),
						dataType : "jsonp"
					}
				},
				pageSize : 20,
			}
		});
		grid.dataSource.read();
	}

	function startChange() {
        var startDate = start.value(),
        endDate = end.value();

        if (startDate) {
            startDate = new Date(startDate);
            startDate.setDate(startDate.getDate());
            end.min(startDate);
        } else if (endDate) {
            start.max(new Date(endDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }

    function endChange() {
        var endDate = end.value(),
        startDate = start.value();

        if (endDate) {
            endDate = new Date(endDate);
            endDate.setDate(endDate.getDate());
            start.max(endDate);
        } else if (startDate) {
            end.min(new Date(startDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }
	
	function validate() {
		if ( $("#end").val() == "" || $("#start").val() == "") {
			return false;
		}
		return true;
	}
});