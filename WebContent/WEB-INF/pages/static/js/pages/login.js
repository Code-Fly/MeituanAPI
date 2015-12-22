$(document).ready(function() {
	$("#login").click(function(e) {
		$("#login").addClass("disabled");
		$.ajax({
			url : _ctx + "/web/to_login?userName=" + $("#userName").val() + "&password=" + $("#password").val(),
			cache : false,
			success : function(data, textStatus, jqXHR) {
				
				if ("" != data && null != data) {
					SessionCache.update("userId", data);
					SessionCache.update("userName", $("#userName").val());					
					window.location.href = _ctx + "/web/index?userId="+data;
				} else {
					SessionCache.remove("userId");
					SessionCache.remove("userName");
					alert("用户名或密码错误");
					$("#login").removeClass("disabled");
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