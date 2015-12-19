$(document).ready(function() {
	var opID = SessionCache.get("opID");
	
	$("#export-excel").click(function(e) {
		var grid = $("#grid").data("kendoGrid");
		grid.saveAsExcel();
	});

	$("#grid").kendoGrid({
		excel : {
			fileName : "Export.xlsx",
			filterable : true,
			allPages : true
		},
		dataSource : {
			transport : {
				read : {
					url : _ctx + "/api/meter/query?opID=" + opID,
					dataType : "jsonp"
				}
			},
			pageSize : 20,
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
				if (row.stopFlag == 2) {
					$('tr[data-uid="' + row.uid + '"] ').css("color", "red");
				}
			});
		},
		columns : [ {
			locked : true,
			field : "nm",
			title : "用户名称",
			width : 150
		}, {
			field : "adr",
			title : "表具编号",
			width : 150
		}, {
			field : "tFlow",
			title : "瞬时流量",
			width : 150
		}, {
			field : "curNumber",
			title : "累计流量",
			width : 150
		}, {
			field : "t",
			title : "温度",
			width : 100
		}, {
			field : "p",
			title : "压力",
			width : 100
		}, {
			field : "dayNum",
			title : "日用量",
			width : 100
		}, {
			field : "monthNum",
			title : "月用量",
			width : 100
		}, {
			field : "readTime",
			title : "采集时间",
			width : 200
		}, {
			field : "range_Low",
			title : "量程下限",
			width : 150
		}, {
			field : "range_Hi",
			title : "量程上限",
			width : 150
		}, {
			field : "meterRange_Low",
			title : "瞬时流量下限",
			width : 150
		}, {
			field : "meterRange_Hi",
			title : "瞬时流量上限",
			width : 150
		}, {
			field : "dayDosage_hi",
			title : "日用量上限",
			width : 150
		}, {
			field : "dayDosage_low",
			title : "日用量下限",
			width : 150
		}, {
			field : "monDosage_hi",
			title : "月用量上限",
			width : 150
		}, {
			field : "monDosage_low",
			title : "月用量下限",
			width : 150
		}, {
			field : "state",
			title : "通讯状态",
			width : 150
		}, {
			field : "stopFlag",
			title : "停气报警",
			values : [ {
				text : "正常",
				value : 0
			}, {
				text : "停气",
				value : 2
			} ],
			width : 150
		}, {
			field : "meterType",
			title : "表型号",
			width : 150
		}, {
			field : "ch",
			title : "通道号",
			width : 100
		}, {
			field : "mode",
			title : "通讯模式",
			width : 150
		} ]

	});
});