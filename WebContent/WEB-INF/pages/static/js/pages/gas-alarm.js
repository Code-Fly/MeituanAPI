$(document).ready(function() {
	var opID = SessionCache.get("opID");
	
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
		change: startChange,
		format : "yyyy-MM-dd HH:mm:ss",
		culture : "zh-CN"
	}).data("kendoDateTimePicker");

	var end = $("#end").kendoDateTimePicker({
		change: endChange,
		format : "yyyy-MM-dd HH:mm:ss",
		culture : "zh-CN"
	}).data("kendoDateTimePicker");

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
		// change : function(e) {
		// var selectedRows = this.select();
		// var selectedDataItems = [];
		// for (var i = 0; i < selectedRows.length; i++) {
		// var dataItem = this.dataItem(selectedRows[i]);
		// selectedDataItems.push(dataItem);
		// }
		// // selectedDataItems contains all selected data items
		// // alert(JSON.stringify(selectedDataItems));
		// this.dataSource.read();
		// },
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
			field : "nm",
			title : "表具名称",
			width : 200
		}, {
			field : "curNumber",
			title : "当前数值",
			width : 200
		}, {
			field : "cd",
			title : "帐号",
			width : 200
		}, {
			field : "opName",
			title : "操作员",
			width : 200
		}, {
			template : function(dataItem) {
				return 24;
			},
			field : "data",
			title : "停气检测值",
			width : 200
		}, {
			field : "processTime",
			title : "处理时间",
			width : 200
		}, {
			field : "readTime",
			title : "停气检测时间",
			width : 200
		}, {
			field : "comTime",
			title : "停气时间",
			width : 200
		} ],

	});

	function reloadGrid() {
		if (!validate()) {
			alert("请输入查询参数");
			return;
		}
		var grid = $("#grid").data("kendoGrid");
		grid.setOptions({
			dataSource : {
				transport : {
					read : {
						url : _ctx + "/api/readerr/query?opID=" + opID + "&" + "beginDate=" + $("#start").val() + "&endDate=" + $("#end").val() + "&errType=3",
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
		if ($("#start").val() == "" || $("#end").val() == "") {
			return false;
		}
		return true;
	}

});