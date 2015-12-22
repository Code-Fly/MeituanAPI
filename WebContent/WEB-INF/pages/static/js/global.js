$(function() {
	if (window.location.protocol + "//" + window.location.host + window.location.pathname != _ctx + "/web/login") {
		sessionAuthentication();
		menuHerf();
	} else {
		SessionCache.remove("userId");
		SessionCache.remove("userName");
	}
	
});

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function menuHerf(){
	$("#home_href").attr("href",_ctx+"/web/index?userId="+ SessionCache.get("userId"));
	$("#applist_href").attr("href",_ctx+"/Api/web/appList?userId="+ SessionCache.get("userId"));
	$("#poilist_href").attr("href",_ctx+"/Api//web/poiList?userId="+ SessionCache.get("userId"));
}
function sessionAuthentication() {
	
	var userId = SessionCache.get("userId");
	if (null == userId || "" == userId) {
		SessionCache.remove("userId");
		SessionCache.remove("userName");
		window.location.href = _ctx + "/web/login";
	}else{
		$("#nav-userName").text(SessionCache.get("userName"));
	}
}

// session cache
var SessionCache = {
	set : function(key, value) {
		sessionStorage.setItem(key, value);
	},
	get : function(key) {
		return sessionStorage.getItem(key);
	},
	remove : function(key) {
		return sessionStorage.removeItem(key);
	},
	update : function(key, value) {
		sessionStorage.removeItem(key);
		sessionStorage.setItem(key, value);
	},
	clear : function() {
		return sessionStorage.clear();
	}
};

// local cache
var LocalCache = {
	set : function(key, value) {
		localStorage.setItem(key, value);
	},
	get : function(key) {
		return localStorage.getItem(key);
	},
	remove : function(key) {
		return localStorage.removeItem(key);
	},
	update : function(key, value) {
		localStorage.removeItem(key);
		localStorage.setItem(key, value);
	},
	clear : function() {
		return localStorage.clear();
	}
};

var HtmlElm = {
	Div : function() {
		return $(document.createElement("div")).clone();
	},
	Li : function() {
		return $(document.createElement("li")).clone();
	},
	Img : function() {
		return $(document.createElement("img")).clone();
	},
	A : function() {
		return $(document.createElement("a")).clone();
	},
	H2 : function() {
		return $(document.createElement("h2")).clone();
	},
	P : function() {
		return $(document.createElement("p")).clone();
	},
	Button : function() {
		return $(document.createElement("button")).clone();
	}
}
