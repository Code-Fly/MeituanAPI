$(document).ready(function() {
	$("#login").click(function(e) {
		$("#login").addClass("disabled");
		$.ajax({
			url : _ctx + "/api/login/query?opNm=" + $("#userName").val() + "&opPwd=" + $("#password").val(),
			cache : false,
			success : function(data, textStatus, jqXHR) {
				
				if ("" != data && null != data) {
					SessionCache.update("opID", data);
					SessionCache.update("userName", $("#userName").val());					
					window.location.href = _ctx + "/web/index";
				} else {
					SessionCache.remove("opID");
					SessionCache.remove("userName");
					alert("用户名或密码错误");
					$("#login").removeClass("disabled");
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				SessionCache.remove("opID");
				SessionCache.remove("userName");
				alert("认证异常");
				$("#login").removeClass("disabled");
			}
		});
	});
});