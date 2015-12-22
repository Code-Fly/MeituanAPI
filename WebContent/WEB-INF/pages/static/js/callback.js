$(function() {
	 $("#modify_password").bind().click(function(){
         if ($("#J-callback-url").is(":hidden")) {
            $("#J-url-text").val("");
         };
         $("#J-callback-url").modal();
       
          var oldPass = $(this).attr("date-message");
          $("#J-submit-btn").unbind().click(function(){
                        var old_pass = $.trim($("#J-oldpass-text").val());
                        var new_pass = $.trim($("#J-newpass-text").val());
                        if(old_pass!=oldPass){
                        	alert("旧密码错误");
                        } 
                        if(new_pass == old_pass){
                        	alert("新密码不能与旧密码相同");
                        }
                        var paramData = {};
                        paramData["app_id"]=app_id;
                        paramData[url_type]=res_url;
                        $.ajax({
                            url: "/basis/callback/url_edit",
                            type: 'post',
                            data: paramData,
                            dataType: 'json',
                            success: function(result){
                                if(result.code == 0){
                                    alert("保存成功");
                                    setTimeout(function(){
                                         window.location.reload();
                                     },500);
                                }
                                else{
                                    alert(result.msg);
                                    setTimeout(function(){
                                         window.location.reload();
                                     },500);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                               // wmHeadertip.error(errorThrown,true,1500);
                            }
                         
                        });
                    });
    });
    $("#J-btn-search").click(function(){
         $("#form").submit();
    });


	
});
   