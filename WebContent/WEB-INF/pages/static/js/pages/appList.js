
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
                	window.location.reload();
                 },500);
            }
            else{
                alert(result);
                setTimeout(function(){
                	window.location.reload();
                 },500);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	 alert(textStatus);
        }
     
    });
	
}
$(function() {
	 $("#modify_password").bind().click(function(){
         if ($("#J-callback-url").is(":hidden")) {
        	$("#J-oldpass-text").val("");
            $("#J-newpass-text").val("");
            $("#J-newpass2-text").val("");
         };
         $("#J-callback-url").modal();
       
          var oldPass = $(this).attr("date-message");
          $("#J-submit-btn").unbind().click(function(){
                        var old_pass = $.trim($("#J-oldpass-text").val());
                        var new_pass = $.trim($("#J-newpass-text").val());
                        if(old_pass!=oldPass){
                        	alert("旧密码错误");
                        	return;
                        } 
                        if(''== new_pass || null == new_pass){
                        	alert("新密码不能为空");
                        	return;
                        } 
                        if(new_pass == old_pass){
                        	alert("新密码不能与旧密码相同");
                        	return;
                        }
                        var new_pass2 = $.trim($("#J-newpass2-text").val());
                        if(new_pass != new_pass2){
                        	alert("两次输入的密码不一致");
                        	return;
                        }
                      
                        $.ajax({
                            url:  _ctx + "/web/updatePwd?userId="+SessionCache.get("userId")+"&password="+new_pass,
                            success: function(result){
                                if("OPSUCCESS" == result){
                                    alert("保存成功");
                                    setTimeout(function(){
                                    	window.location.href = _ctx + "/web/login";
                                     },500);
                                }
                                else{
                                    alert(result);
                                    setTimeout(function(){
                                         window.location.reload();
                                     },500);
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


	
});
   