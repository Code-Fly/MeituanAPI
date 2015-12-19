$(document).ready(function() {
	var opID = SessionCache.get("opID");
	
	$("#export-img").click(function() {
		var chart = $("#chart").getKendoChart();
		chart.exportImage().done(function(data) {
			kendo.saveAs({
				dataURI : data,
				fileName : "chart.png",
			});
		});
	});

	$("#export-svg").click(function() {
		var chart = $("#chart").getKendoChart();
		chart.exportSVG().done(function(data) {
			kendo.saveAs({
				dataURI : data,
				fileName : "chart.svg",
			});
		});
	});

	$("#export-excel").click(function(e) {
		var grid = $("#grid").data("kendoGrid");
		grid.saveAsExcel();
	});

	$("#reset-dp").click(function() {
		var user1 = $("#user-1").data("kendoComboBox");
		var user2 = $("#user-2").data("kendoComboBox");
		var user3 = $("#user-3").data("kendoComboBox");
		start.value(null);
		user1.text(null);
		user1.value(null);
		user2.text(null);
		user2.value(null);
		user3.text(null);
		user3.value(null);
	});

	$("#submit-dp").click(function() {
		reloadGrid();
	});

	var today = new Date();

	var start = $("#start").kendoDatePicker({
		depth : "decade",
		start : "decade",
		format : "yyyy",
		culture : "zh-CN"
	}).data("kendoDatePicker");
	// start.value(new Date());
	$("#user-1").kendoComboBox({
		placeholder : "请选择",
		dataTextField : "nm",
		dataValueField : "infoID",
		dataSource : {
			transport : {
				read : {
					url : _ctx + "/api/tree/area/query",
					dataType : "jsonp"
				}
			}
		},
		change : function(e) {
			var user1 = $("#user-1").data("kendoComboBox");
			var user2 = $("#user-2").data("kendoComboBox");
			user2.setDataSource({
				transport : {
					read : {
						url : _ctx + "/api/tree/area/query?infoID=" + user1.value(),
						dataType : "jsonp"
					}
				}
			});
			user2.text(null);
			user2.value(null);
		}
	});

	$("#user-2").kendoComboBox({
		placeholder : "请选择",
		dataTextField : "nm",
		dataValueField : "infoID",
		dataSource : null
	});

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
			field : "mID",
			title : "表具ID",
			width : 200
		}, {
			field : "janNumber",
			title : "一月",
			width : 100
		}, {
			field : "febNumber",
			title : "二月",
			width : 100
		}, {
			field : "marNumber",
			title : "三月",
			width : 100
		}, {
			field : "aprNumber",
			title : "四月",
			width : 100
		}, {
			field : "mayNumber",
			title : "五月",
			width : 100
		}, {
			field : "junNumber",
			title : "六月",
			width : 100
		}, {
			field : "julNumber",
			title : "七月",
			width : 100
		}, {
			field : "augNumber",
			title : "八月",
			width : 100
		}, {
			field : "sepNumber",
			title : "九月",
			width : 100
		}, {
			field : "octNumber",
			title : "十月",
			width : 100
		}, {
			field : "novNumber",
			title : "十一月",
			width : 100
		}, {
			field : "decNumber",
			title : "十二月",
			width : 100
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
						url : _ctx + "/api/yearaccount/query?opID=" + opID + "&infoID=" + $("#user-2").data("kendoComboBox").value() + "&year=" + $("#start").val(),
						dataType : "jsonp"
					}
				},
				pageSize : 20,
			}
		});
		grid.dataSource.read();
	}

	function validate() {
		if ($("#user-2").data("kendoComboBox").value() == "" || $("#start").val() == "") {
			return false;
		}
		return true;
	}
});