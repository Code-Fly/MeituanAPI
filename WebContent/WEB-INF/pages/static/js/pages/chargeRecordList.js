
function pageGo(){
    var carId = 1;
    $("#list").html("");
    var app_poi_code = $.trim($("#app_poi_code").val());
    var app_id = $.trim($("#app_id").val());
    var startTime = $.trim($("#startTime").val());
    var endTime = $.trim($("#endTime").val());
    $.ajax({
      url: _ctx + "/Api/web/chargeList?userId="+SessionCache.get("userId")+"&pageId="+carId+"&app_poi_code="+app_poi_code+"&app_id="+app_id+"&startTime="+startTime+"&endTime="+endTime,
      success: function (data) {
        if (data != null) {
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
        	  var num = index+1;
            $("#list").append('<tr>');
            $("#list").append('<td>' + num+ '</td>');
            $("#list").append('<td>' + item.app_id + '</td>');
            $("#list").append('<td>' + item.app_poi_code + '</td>');
            $("#list").append('<td>' + item.czje + '</td>');
            $("#list").append('<td>' + item.czns + '</td>');
            $("#list").append('<td>' + item.czfs + '</td>');
            $("#list").append('<td>' + item.czsj + '</td>');
            $("#list").append('</tr>');
          });
          var pageCount = eval("(" + data + ")").pageCount; //取到pageCount的值(把返回数据转成object类型)
          var currentPage = eval("(" + data + ")").CurrentPage; //得到urrentPage
          var options = {
            bootstrapMajorVersion: 2, //版本
            currentPage: currentPage, //当前页数
            totalPages: pageCount, //总页数
            itemTexts: function (type, page, current) {
              switch (type) {
                case "first":
                  return "首页";
                case "prev":
                  return "上一页";
                case "next":
                  return "下一页";
                case "last":
                  return "末页";
                case "page":
                  return page;
              }
            },//点击事件，用于通过Ajax来刷新整个list列表
            onPageClicked: function (event, originalEvent, type, page) {
              $.ajax({
            	url: _ctx + "/Api/web/chargeList?userId="+SessionCache.get("userId")+"&pageId="+page+"&app_poi_code="+app_poi_code+"&app_id="+app_id+"&startTime="+startTime+"&endTime="+endTime,
                success: function (data1) {
                	$("#list").html("");
                	if (data1 != null) {
                     $.each(eval("(" + data1 + ")").list, function (index, item) { //遍历返回的json
                    	  var num = index+1;
                          $("#list").append('<tr>');
                          $("#list").append('<td>' + num+ '</td>');
                          $("#list").append('<td>' + item.app_id + '</td>');
                          $("#list").append('<td>' + item.app_poi_code + '</td>');
                          $("#list").append('<td>' + item.czje + '</td>');
                          $("#list").append('<td>' + item.czns + '</td>');
                          $("#list").append('<td>' + item.czfs + '</td>');
                          $("#list").append('<td>' + item.czsj + '</td>');
                          $("#list").append('</tr>');
                     });
                  }
                }
              });
            
            }
          };
          $('#example').bootstrapPaginator(options);
        }
      }
    });
}

$(function () {
	pageGo();
	$("#J-btn-search").click(function(){
		 pageGo();
   });
 })
