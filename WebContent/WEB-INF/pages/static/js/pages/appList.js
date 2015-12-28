
function deleteApp(appId){
	if (!confirm("确定要删除"+appId+"吗？")) return false;

    $.ajax({
        url:  _ctx + "/Api/web/deleteApp?app_id="+appId,
        success: function(result){
            if("OPSUCCESS" == result){
                alert("删除成功");
                setTimeout(function(){
                	window.location.reload();
                 },500);
            } else if ("HASPOI" == result){
            	alert("该APP下存在门店不能被删除！");
                setTimeout(function(){
                	return;
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

$(function() {
	// 清空 
	$(".edit").bind().click(function(){
		if ($("#J-callback-url").is(":hidden")) {
	  	 	$("#J-secret-text").val("");
	  	 	$("#J-price-text").val("");
	  	 	$("#J-desc-text").val("");
	   };
	   $("#J-callback-url").modal();
	   var appId= $(this).attr("date-message");
	   // 初始化
	   $("#J-secret-text").val($.trim($("#secret_"+appId).text()));
 	   $("#J-price-text").val($.trim($("#price_"+appId).text()));
 	   $("#J-desc-text").val($.trim($("#desc_"+appId).text()));
       $("#J-submit-btn").unbind().click(function(){
           var secret = $.trim($("#J-secret-text").val());
           var price = $.trim($("#J-price-text").val());
           var description = $.trim($("#J-desc-text").val());
           if(''== secret || null == secret){
           		alert("密钥不能为空");
           		return;
           }
           if(''!= price && isNaN(price)){
        	    alert("年费输入有误！");
          		return;
           }
           $.ajax({
               url:  _ctx + "/Api/web/updateApp?app_id="+appId+"&price="+price+"&descption="+description+"&secret="+secret,
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
 	   
	});
		// 清空 
		$("#addApp").bind().click(function(){
			if ($("#J-addapp-url").is(":hidden")) {
		  	 	$("#J-appid1-text").val("");
		  	 	$("#J-secret1-text").val("");
		  		$("#J-price1-text").val("");
		  	 	$("#J-descption1-text").val("");
		   };
		   $("#J-addapp-url").modal();
		   // 初始化
	       $("#J-submit-btn1").unbind().click(function(){
	    	 var appid =  $.trim($("#J-appid1-text").val());
		  	 var secret = $.trim($("#J-secret1-text").val());
		  	 var price =  $.trim($("#J-price1-text").val());
		  	 var descption =$.trim($("#J-descption1-text").val());
	           if(''== appid || null == appid){
	           		alert("APPID不能为空");
	           		return;
	           }
	           if(''== secret || null == secret){
	           		alert("密钥不能为空");
	           		return;
	           }
	           if(''!= price && isNaN(price)){
	        	    alert("单价输入有误！");
	          		return;
	           }
	           $.ajax({
	               url:  _ctx + "/Api/web/addApp?appid="+appid+"&secret="+secret+"&price="+price+"&descption="+descption+"&userid="+SessionCache.get("userId"),
	               success: function(result){
	                   if("OPSUCCESS" == result){
	                       alert("添加APP成功");
	                       setTimeout(function(){
	                    	   window.location.reload();
	                        },500);
	                   } else if("HASAPP" == result ){
	                	   alert("APP（"+appid+"）已经存在");
	                   }
	                   else{
	                       alert(result);
	                   }
	               },
	               error: function (XMLHttpRequest, textStatus, errorThrown) {
	               	 alert("表单输入错误");
	               }
	            
	           });
	       });
	 	   
		});
		
	 $("#J-btn-search").click(function(){
         $("#form").submit();
    });
});
   