

function deletePoi(num){
	if (!confirm("确定要删除该门店吗？")) return false;
	var app_id = $.trim($("#appid_"+num).text());
	var poi_code = $.trim($("#poi_code_"+num).text());
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



function initEdit(){
	// 清空 
	$(".edit").bind().click(function(){
		if ($("#J-callback-url").is(":hidden")) {
	  	 	$("#J-name1-text").val("");
	  	 	$("#J-phone1-text").val("");
	  	 	$("#J-address1-text").val("");
	   };
	   $("#J-callback-url").modal();
	   var num= $(this).attr("date-message");
	   // 初始化
       $("#J-name1-text").val($.trim($("#poi_name_"+num).text()));
	   $("#J-phone1-text").val($.trim($("#poi_phone_"+num).text()));
	   $("#J-address1-text").val($.trim($("#poi_address_"+num).text()));
       $("#J-submit-btn").unbind().click(function(){
       var name = $.trim($("#J-name1-text").val());
       var phone = $.trim($("#J-phone1-text").val());
       var address = $.trim($("#J-address1-text").val());
       if(''== name || null == name){
       		alert("门店名称不能为空");
       		return;
       }
       var appId =$.trim($("#appid_"+num).text());
       var poiCode =$.trim($("#poi_code_"+num).text());
       $.ajax({
           url:  _ctx + "/Api/web/updatePoi?app_id="+appId+"&name="+name+"&phone="+phone+"&poi_code="+poiCode+"&address="+address,
               success: function(result){
                   if("OPSUCCESS" == result){
                       alert("修改成功");
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
 	   
	});
	 $("#J-btn-search").click(function(){
         $("#form").submit();
    });
}

function initAdd(){
	// 清空 
	$("#addPoi").bind().click(function(){
		if ($("#J-add-poi").is(":hidden")) {
			$("#J-app_poi_code-text").val("");
	  	 	$("#J-wm_poi_name-text").val("");
	  	 	$("#J-appid-text").val("");
	  	 	$("#J-price-text").val("");
	  	 	$("#J-wm_poi_address-text").val("");
	  	 	$("#J-wm_poi_phone-text").val("");
	  	 	$("#J-descption-text").val("");
	   };
	   $("#J-add-poi").modal();
       $("#J-submit-btn1").unbind().click(function(){
    	  var app_poi_code =  $.trim($("#J-app_poi_code-text").val());
    	  var wm_poi_name =	$.trim($("#J-wm_poi_name-text").val());
    	  var appid =	$.trim($("#J-appid-text").val());
    	  var wm_poi_address =	$.trim($("#J-wm_poi_address-text").val());
    	  var wm_poi_phone =	$.trim($("#J-wm_poi_phone-text").val());
    	  var descption =	$.trim($("#J-descption-text").val());
    	  var price = $.trim($("#J-price-text").val());
       if(''== app_poi_code || null == app_poi_code){
       		alert("门店CODE不能为空");
       		return;
       }
       if(''== appid || null == appid){
      		alert("APPID不能为空");
      		return;
      }
       if(''!= price && isNaN(price)){
   	    	alert("年费输入有误！");
     		return;
      }
       $.ajax({
           url:  _ctx + "/Api/web/addPoi?userId="+SessionCache.get("userId")+"&appid="+appid+"&app_poi_code="+app_poi_code+"&wm_poi_name="+wm_poi_name+"&wm_poi_address="+wm_poi_address+"&wm_poi_phone="+wm_poi_phone+"&descption="+descption+"&price="+price,
               success: function(result){
                   if("OPSUCCESS" == result){
                       alert("增加门店成功");
                       setTimeout(function(){
                    	   window.location.reload();
                        },500);
                   }
                   else{
                       alert(result);
                   }
               },
               error: function (XMLHttpRequest, textStatus, errorThrown) {
               	 alert("输入有误");
               }
            
           });
       });
 	   
	});
	 $("#J-btn-search").click(function(){
         $("#form").submit();
    });
}

function pageGo(){
    var carId = 1;
    $("#list").html("");
    var wm_poi_name = $.trim($("#wm_poi_name").val());
    var wm_poi_phone = $.trim($("#wm_poi_phone").val());
    $.ajax({
      url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+carId+"&wm_poi_name="+wm_poi_name+"&wm_poi_phone="+wm_poi_phone,
      success: function (data) {
        if (data != null && data!='NODATA') {
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
        	  var num = index+1;
            $("#list").append('<tr>');
            $("#list").append('<td>' + num+ '</td>');
            $("#list").append('<td id=poi_name_'+num+'>' + item.wm_poi_name + '</td>');
            $("#list").append('<td id=poi_price_'+num+'>' + item.price + '</td>');
            $("#list").append('<td id=poi_code_'+num+'>' + item.app_poi_code + '</td>');
            $("#list").append('<td id=appid_'+num+'>' + item.appid + '</td>');
            $("#list").append('<td id=poi_phone_'+num+'>' + item.wm_poi_phone + '</td>');
            $("#list").append('<td id=poi_address_'+num+'>' + item.wm_poi_address + '</td>');
            $("#list").append('<td id=expiredate_'+num+'>' + item.expiredate + '</td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn red edit" date-message='+num+'>修改</button>');
            $("#list").append('</td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn red delete" onclick="deletePoi('+num+');">删除</button>');
            $("#list").append('</td>');
            $("#list").append('</tr>');
          });
          initAdd();
          initEdit();
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
            	  url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+page+"&wm_poi_name="+wm_poi_name+"&wm_poi_phone="+wm_poi_phone,
                success: function (data1) {
                	$("#list").html("");
                	if (data1 != null) {
                     $.each(eval("(" + data1 + ")").list, function (index, item) { //遍历返回的json
                       var num = index+1;
                       $("#list").append('<tr>');
                       $("#list").append('<td>' + num + '</td>');
                       $("#list").append('<td id=poi_name_'+num+'>' + item.wm_poi_name + '</td>');
                       $("#list").append('<td id=poi_price_'+num+'>' + item.price + '</td>');
                       $("#list").append('<td id=poi_code_'+num+'>' + item.app_poi_code + '</td>');
                       $("#list").append('<td id=appid_'+num+'>' + item.appid + '</td>');
                       $("#list").append('<td id=poi_phone_'+num+'>' + item.wm_poi_phone + '</td>');
                       $("#list").append('<td id=poi_address_'+num+'>' + item.wm_poi_address + '</td>');
                       $("#list").append('<td id=expiredate_'+num+'>' + item.expiredate + '</td>');
                       $("#list").append('<td>');
                       $("#list").append('<button class="btn red edit" date-message='+num+'>修改</button>');
                       $("#list").append('</td>');
                       $("#list").append('<td>');
                       $("#list").append('<button class="btn red delete" onclick="deletePoi('+num+');">删除</button>');
                       $("#list").append('</td>');
                       $("#list").append('</tr>');
                     });
                     initAdd();
                     initEdit();
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
