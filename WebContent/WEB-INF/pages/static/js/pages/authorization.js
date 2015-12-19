$(document).ready(function() {
	var opID = SessionCache.get("opID");

	$("#export-excel").click(function(e) {
		var grid = $("#grid").data("kendoGrid");
		grid.saveAsExcel();
	});

	$("#reset-dp").click(function() {
		var user1 = $("#user-1").data("kendoComboBox");
		user1.text(null);
		user1.value(null);
	});

	$("#submit-dp").click(function() {
		reloadGrid();
	});

	$("#user-1").kendoComboBox({
		placeholder : "请选择",
		dataTextField : "opNm",
		dataValueField : "opID",
		dataSource : {
			transport : {
				read : {
					url : _ctx + "/api/op/query",
					dataType : "jsonp"
				}
			}
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
		dataBound : function(e) {
			var data = this.dataSource.data();
			$.each(data, function(i, row) {
				if (null == row.infoID || "" == row.infoID) {
					$('tr[data-uid="' + row.uid + '"] ').find(".btn-remove").hide();
				} else {
					$('tr[data-uid="' + row.uid + '"] ').find(".btn-add").hide();
				}
				$('tr[data-uid="' + row.uid + '"] ').find(".k-button").removeClass("k-button k-button-icontext").addClass("btn btn-default");
			});
		},
		columns : [ {
			field : "nm",
			title : "表具名称",
		}, {
			field : "mem",
			title : "用户名"
		}, {
			command : [ {
				name : "add",
				text : "&nbsp;添加",
				className : "btn-add fa fa-plus",
				click : function(e) {
					// e.target is the DOM element representing the button
					var tr = $(e.target).closest("tr");
					// get the current table row (tr)
					// get the data bound to the current table row
					var data = this.dataItem(tr);
					// alert("Details for: " + data.mID);

					$.ajax({
						url : _ctx + "/api/lience/add?opID=" + $("#user-1").data("kendoComboBox").value() + "&mID=" + data.mID,
						cache : false,
						type : "PUT",
						success : function(data, textStatus, jqXHR) {
							reloadGrid();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("操作异常");
						}
					});

				}
			}, {
				name : "remove",
				text : "&nbsp;删除",
				className : "btn-remove fa fa-remove",
				click : function(e) {
					// e.target is the DOM element representing the button
					var tr = $(e.target).closest("tr");
					// get the current table row (tr)
					// get the data bound to the current table row
					var data = this.dataItem(tr);
					$.ajax({
						url : _ctx + "/api/lience/delete?opID=" + $("#user-1").data("kendoComboBox").value() + "&mID=" + data.mID,
						cache : false,
						type : "DELETE",
						success : function(data, textStatus, jqXHR) {
							reloadGrid();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("操作异常");
						}
					});
				}
			} ],
			title : "&nbsp;",
			width : "100px"
		} ],
		editable : "inline"
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
						url : _ctx + "/api/lience/query?opID=" + $("#user-1").data("kendoComboBox").value(),
						dataType : "jsonp"
					},
					update : {
						url : "/Products/Update",
						dataType : "jsonp"
					},
					destroy : {
						url : "/Products/Destroy",
						dataType : "jsonp"
					},
					create : {
						url : "/Products/Create",
						dataType : "jsonp"
					},
					parameterMap : function(options, operation) {
						if (operation !== "read" && options.models) {
							return {
								models : kendo.stringify(options.models)
							};
						}
					}
				},
				pageSize : 20,
			}
		});
		grid.dataSource.read();
	}

	function validate() {
		if ($("#user-1").data("kendoComboBox").value() == "") {
			return false;
		}
		return true;
	}
});