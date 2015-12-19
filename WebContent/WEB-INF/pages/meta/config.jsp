<%
	response.setContentType("text/html;charset=UTF-8");
	String path = request.getContextPath();
	int port = request.getServerPort();
	String basePath = null;
	if (80 == port) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + port + path;
	}
	application.setAttribute("ctx", basePath);	
%>

