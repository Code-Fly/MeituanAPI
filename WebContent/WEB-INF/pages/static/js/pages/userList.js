
function updateUser(userId,type){
    $.ajax({
        url:  _ctx + "/web/updateUser?user_id="+userId+"&type="+type,
        success: function(result){
            if("OPSUCCESS" == result){
                alert("操作成功");
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
        	 alert("请联系系统管理员");
        }
     
    });
	
}

function stop(userId){
	if (!confirm("确定要停用该用户吗？")) return false;
	updateUser(userId,0);
}

function start(userId){
	updateUser(userId,1);
}

function resetPwd(userId){
	updateUser(userId,2);
}




$(function() {
	// 清空 
	$("#addUser").bind().click(function(){
		if ($("#J-callback-url").is(":hidden")) {
	  	 	$("#J-login_id-text").val("");
	  	 	$("#J-nfdj-text").val("");
	  	 	$("#J-descption-text").val("");
	   };
	   $("#J-callback-url").modal();
	   // 初始化
       $("#J-submit-btn").unbind().click(function(){
    	var login_id =  $.trim($("#J-login_id-text").val());
	  	 var nfdj = $.trim($("#J-nfdj-text").val());
	  	 var descption =$.trim($("#J-descption-text").val());
           if(''== login_id || null == login_id){
           		alert("登陆名不能为空");
           		return;
           }
           if(''!= nfdj && isNaN(nfdj)){
        	    alert("年费单价输入有误！");
          		return;
           }
           $.ajax({
               url:  _ctx + "/web/addUser?login_id="+login_id+"&nfdj="+nfdj+"&descption="+descption,
               success: function(result){
                   if("OPSUCCESS" == result){
                       alert("保存成功");
                       setTimeout(function(){
                    	   window.location.reload();
                        },500);
                   } else if("HASUSER" == result ){
                	   alert("用户（"+login_id+"）已经存在");
                   }
                   else{
                       alert(result);
                   }
               },
               error: function (XMLHttpRequest, textStatus, errorThrown) {
               	 alert("输入错误");
               }
            
           });
       });
 	   
	});
	
});
   