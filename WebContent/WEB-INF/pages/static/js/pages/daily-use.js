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
		end.max(new Date(2099, 11, 31));
		end.min(new Date(1900, 0, 1));
		end.value(null);
		start.max(new Date(2099, 11, 31));
		start.min(new Date(1900, 0, 1));
		start.value(null);
		user1.text(null);
		user1.value(null);
		user2.text(null);
		user2.value(null);
		user3.text(null);
		user3.value(null);
	});

	$("#tab-chart").on('shown.bs.tab', function(e) {
		reloadChart();
	})

	$("#tab-data").on('shown.bs.tab', function(e) {
		reloadGrid();
	})

	$("#submit-dp").click(function() {
		if ($("#tab-content-chart").hasClass("active")) {
			reloadChart();
		}
		if ($("#tab-content-data").hasClass("active")) {
			reloadGrid();
		}
	});

	var today = new Date();

	var start = $("#start").kendoDatePicker({
		change : startChange,
		format : "yyyy-MM-dd",
		culture : "zh-CN"
	}).data("kendoDatePicker");
	// start.value(new
	// Date(today.getFullYear(),today.getMonth(),today.getDate()-1));
	var end = $("#end").kendoDatePicker({
		change : endChange,
		format : "yyyy-MM-dd",
		culture : "zh-CN"
	}).data("kendoDatePicker");
	// end.value(today);

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
		dataSource : null,
		change : function(e) {
			var user2 = $("#user-2").data("kendoComboBox");
			var user3 = $("#user-3").data("kendoComboBox");
			user3.setDataSource({
				transport : {
					read : {
						url : _ctx + "/api/tree/area/query?opID=" + opID + "&infoID=" + user2.value(),
						dataType : "jsonp"
					}
				}
			});
			user3.text(null);
			user3.value(null);
		}
	});

	$("#user-3").kendoComboBox({
		placeholder : "请选择",
		dataTextField : "nm",
		dataValueField : "infoID",
		dataSource : null
	});

	$("#chart").kendoChart({
		autoBind : false,
		series : [ {
			field : "dayNumber",
			name : "日用量 [kwh]",
			style : "smooth",
			type : "line",
			categoryField : "startTime",
			markers : {
				visible : false
			},
			color : "#007eff",
			axis : "dayNumber"
		} ],
		valueAxes : [ {
			name : "dayNumber",
			color : "#007eff"
		} ],
		legend : {
			position : "bottom"
		},
		title : {
			text : $("#start").val() + " ~ " + $("#end").val()
		},
		categoryAxis : {
			majorGridLines : {
				visible : false
			},
			majorTicks : {
				visible : false
			},
			type : "date",
			baseUnit : "days",
			labels : {
				rotation : "auto",
				dateFormats : {
					days : "MM-dd"
				}
			}
		// axisCrossingValues : [ 999999 ],
		},
		tooltip : {
			visible : true,
			template : "<div>#= series.name #: #= value #</div><div> #= dataItem.startTime#</div>"
		},
		chartArea : {
			background : "transparent"
		},
		dataBound : function(e) {
			// alert($("#user-3").data("kendoComboBox").value())
		}
	});

	$("#grid").kendoGrid({
		excel : {
			fileName : "Export.xlsx",
			filterable : true,
			allPages : true
		},
		autoBind : false,
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
			width : 250
		}, {
			field : "collectDay",
			title : "日期",
			width : 200
		}, {
			field : "curNumber",
			title : "当前表数",
			width : 200
		}, {
			field : "dayNumber",
			title : "日流量",
			width : 200
		}, {
			field : "theoryFlag",
			title : "数据状态",
			values : [ {
				text : "正常",
				value : 0
			}, {
				text : "超上限",
				value : 2
			}, {
				text : "超下限",
				value : 1
			} ],
			width : 200
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

	function reloadChart() {
		if (!validate()) {
			alert("请输入查询参数");
			return;
		}
		var chart = $("#chart").data("kendoChart");
		chart.setOptions({
			dataSource : {
				transport : {
					read : {
						url : _ctx + "/api/dayaccount/query?mID=" + $("#user-3").data("kendoComboBox").value() + "&beginDate=" + $("#start").val() + "&endDate=" + $("#end").val(),
						dataType : "jsonp"
					}
				}
			},
			title : {
				text : $("#start").val() + " ~ " + $("#end").val()
			},
		});
		chart.dataSource.read();
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
						url : _ctx + "/api/dayaccount/query?mID=" + $("#user-3").data("kendoComboBox").value() + "&beginDate=" + $("#start").val() + "&endDate=" + $("#end").val(),
						dataType : "jsonp"
					}
				},
				pageSize : 20,
			}
		});
		grid.dataSource.read();
	}

	function validate() {
		if ($("#user-3").data("kendoComboBox").value() == "" || $("#start").val() == "" || $("#end").val() == "") {
			return false;
		}
		return true;
	}
});