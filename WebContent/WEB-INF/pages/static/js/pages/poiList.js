function editPoi(num){
	if ($("#J-callback-url").is(":hidden")) {
  	 	$("#J-name-text").val("");
  	 	$("#J-phone-text").val("");
  	 	$("#J-address-text").val("");
   };
   $("#J-callback-url").modal();
   // 初始化
       $("#J-name-text").val($.trim($("#secret_"+appId).text()));
	   $("#J-phone-text").val($.trim($("#price_"+appId).text()));
	   $("#J-address-text").val($.trim($("#desc_"+appId).text()));
       $("#J-submit-btn").unbind().click(function(){
       var name = $.trim($("#J-name-text").val());
       var phone = $.trim($("#J-phone-text").val());
       var address = $.trim($("#J-address-text").val());
       if(''== name || null == name){
       		alert("门店名称不能为空");
       		return;
       }
       
       $.ajax({
           url:  _ctx + "/Api/web/updatePoi?app_id="+appId+"&price="+price+"&descption="+description+"&secret="+secret,
           success: function(result){
               if("OPSUCCESS" == result){
                   alert("保存成功");
                   setTimeout(function(){
                	   window.location.reload();
                    },500);
               }
               else{
                   alert(result);
               }
           },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
           	 alert(textStatus);
           }
        
       });
   });
	   

}

function deletePoi(num){
	if (!confirm("确定要删除该门店吗？")) return false;
	var app_id = $.trim($("#appid_"+num).text());
	var poi_code = $.trim($("#poi_code"+num).text());
    $.ajax({
        url:  _ctx + "/Api/web/deletePoi?app_id="+app_id+"&poi_code="+poi_code,
        success: function(result){
            if("OPSUCCESS" == result){
                alert("删除成功");
                setTimeout(function(){
                	window.location.reload();
                 },500);
            } 
            else{
                alert(result);
                setTimeout(function(){
                	return;
                 },500);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	 alert(textStatus);
        }
     
    });
}


$(function () {
    var carId = 1;
    $("#list").html(""),
    $.ajax({
      url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+carId,
    
      success: function (data) {
        if (data != null) {
        	
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
        	  var num = index+1;
            $("#list").append('<tr>');
            $("#list").append('<td>' + num+ '</td>');
            $("#list").append('<td id=poi_code'+num+'>' + item.app_poi_code + '</td>');
            $("#list").append('<td id=poi_name_'+num+'>' + item.wm_poi_name + '</td>');
            $("#list").append('<td id=appid_'+num+'>' + item.appid + '</td>');
            $("#list").append('<td id=poi_phone_'+num+'>' + item.wm_poi_phone + '</td>');
            $("#list").append('<td id=poi_address_'+num+'>' + item.wm_poi_address + '</td>');
            $("#list").append('<td id=expiredate_'+num+'>' + item.expiredate + '</td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn red edit" onclick="editPoi(' + item.app_poi_code + ' );">修改</button>');
            $("#list").append('</td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn red delete" onclick="deletePoi(' + item.app_poi_code + ' );">删除</button>');
            $("#list").append('</td>');
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
            	url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+page,
                success: function (data1) {
                	$("#list").html("");
                	if (data1 != null) {
                     $.each(eval("(" + data1 + ")").list, function (index, item) { //遍历返回的json
                       var num = index+1;
                       $("#list").append('<tr>');
                       $("#list").append('<td>' + num + '</td>');
                       $("#list").append('<td id=poi_code'+num+'>' + item.app_poi_code + '</td>');
                       $("#list").append('<td id=poi_name_'+num+'>' + item.wm_poi_name + '</td>');
                       $("#list").append('<td id=appid_'+num+'>' + item.appid + '</td>');
                       $("#list").append('<td id=poi_phone_'+num+'>' + item.wm_poi_phone + '</td>');
                       $("#list").append('<td id=poi_address_'+num+'>' + item.wm_poi_address + '</td>');
                       $("#list").append('<td id=expiredate_'+num+'>' + item.expiredate + '</td>');
                       $("#list").append('<td>');
                       $("#list").append('<button class="btn red edit" onclick="editPoi(' + num + ' );">修改</button>');
                       $("#list").append('</td>');
                       $("#list").append('<td>');
                       $("#list").append('<button class="btn red delete" onclick="deletePoi(' + num + ' );">删除</button>');
                       $("#list").append('</td>');
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
  })