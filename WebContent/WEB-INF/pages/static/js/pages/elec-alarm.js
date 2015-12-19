$(document).ready(function() {
	var opID = SessionCache.get("opID");

	$("#export-excel").click(function(e) {
		var grid = $("#grid").data("kendoGrid");
		grid.saveAsExcel();
	});

	$("#reset-dp").click(function() {
		var user1 = $("#errClass").data("kendoComboBox");
		end.max(new Date(2099, 11, 31));
		end.min(new Date(1900, 0, 1));
		end.value(null);
		start.max(new Date(2099, 11, 31));
		start.min(new Date(1900, 0, 1));
		start.value(null);
		user1.value(200);

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
	// start.value(new
	// Date(today.getFullYear(),today.getMonth(),today.getDate()-1));
	var end = $("#end").kendoDateTimePicker({
		change : endChange,
		format : "yyyy-MM-dd HH:mm:ss",
		culture : "zh-CN"
	}).data("kendoDateTimePicker");
	// end.value(today);

	$("#errClass").kendoComboBox({
		placeholder : "请选择",
		dataTextField : "errText",
		dataValueField : "errClass",
		dataSource : [ {
			errClass : 200,
			errText : "全部报警"
		}, {
			errClass : 201,
			errText : "低电压"
		}, {
			errClass : 202,
			errText : "超低电压"
		}, {
			errClass : 210,
			errText : "掉电，电压正常"
		}, {
			errClass : 211,
			errText : "掉电，低电压"
		}, {
			errClass : 212,
			errText : "掉电，电压超低"
		} ],
		index : 0
	});
	
	$("#grid").kendoGrid({
		excel : {
			fileName : "Export.xlsx",
			filterable : true,
			allPages : true
		},
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
				if (row.errProcessFlag == 0) {
					$('tr[data-uid="' + row.uid + '"] ').css("color", "red");
				}
			});
		},
		columns : [ {
			field : "nm",
			title : "用户名称",
			width : 150
		}, {
			field : "info",
			title : "故障信息",
			width : 500
		}, {
			field : "readTime",
			title : "通讯时间",
			width : 200
		}, {
			field : "errProcessFlag",
			title : "状态",
			values : [ {
				text : "未确认",
				value : 0
			}, {
				text : "已确认",
				value : 1
			} ],
			width : 100
		} ],

	});

	function startChange() {
		var endDateMax = new Date(start.value().getFullYear() + 1, 0, 0, 23, 59, 59);

		var startDate = start.value(), endDate = end.value();

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
		end.max(endDateMax);
	}

	function endChange() {
		var endDate = end.value(), startDate = start.value();
		var startDateMin = new Date(end.value().getFullYear(), 0, 1);

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
		start.min(startDateMin);
	}

	function initDateTimePicker() {
		start.max(end.value());
		end.min(start.value());
		var endDateMax = new Date(start.value().getFullYear() + 1, 0, 0, 23, 59, 59);
		var startDateMin = new Date(end.value().getFullYear(), 0, 1);
		end.max(endDateMax);
		start.min(startDateMin);
	}

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
						url : _ctx + "/api/error/query?opID=" + opID + "&errClass=" + $("#errClass").data("kendoComboBox").value() + "&beginDate=" + $("#start").val() + "&endDate=" + $("#end").val(),
						dataType : "jsonp"
					}
				},
				pageSize : 20,
			}
		});
	}

	function validate() {
		if ($("#errClass").data("kendoComboBox").value() == "" || $("#start").val() == "" || $("#end").val() == "") {
			return false;
		}
		return true;
	}
});