$(document).ready(function() {
	$("#login").click(function(e) {
		$("#login").addClass("disabled");
		$.ajax({
			url : _ctx + "/web/to_login?userName=" + $("#userName").val() + "&password=" + $("#password").val(),
			cache : false,
			success : function(data, textStatus, jqXHR) {
				if(data =='NOUSER'){
					SessionCache.remove("userId");
					SessionCache.remove("userName");
					$("#login").removeClass("disabled");
					alert("不存在该用户");
				} else if (data == 'USERSTOP'){
					SessionCache.remove("userId");
					SessionCache.remove("userName");
					$("#login").removeClass("disabled");
					alert("用户已被停用");
				} else if (data == 'PWDWRONG'){
					SessionCache.remove("userId");
					SessionCache.remove("userName");
					$("#login").removeClass("disabled");
					alert("密码错误");
				} else {
					SessionCache.update("userId", data);
					SessionCache.update("userName", $("#userName").val());					
					window.location.href = _ctx + "/web/index?userId="+data;
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				SessionCache.remove("userId");
				SessionCache.remove("userName");
				alert("认证异常");
				$("#login").removeClass("disabled");
			}
		});
	});
});