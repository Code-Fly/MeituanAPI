<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="meta/config.jsp"%>
<html>
<head>
    <title>登录 | 美团外卖后台管理</title>
    <%@ include file="meta/meta.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/pages/login.js"></script>
</head>

<body class="pg-unitive-login theme--waimai_developer">
	<header class="header cf">
    <a class="site-logo" href="http://waimai.meituan.com">美团外卖后台管理</a>
    <span class="site-des">美团外卖后台管理</span>
</header>



    <div class="site-body-wrapper">
    <div class="site-body cf">
        <div class="promotion-banner">
	    <img src="${ctx}/static/styles/image/s0.meituan.com.png" alt="美团外卖开发者" height="370" width="480">
</div>

        <div class="login-section">
    <div class="form form--stack" >
        <div class="validate-info" style="visibility:hidden"></div>

        <span class="login-type" data-mtevent="login.mobile.switch">
            	账号登录
        </span>

        <div class="form-field form-field--icon">
            <i class="icon icon-user"></i>
            <input id="username" class="f-text" name="email" placeholder="手机号/用户名/邮箱" value="" type="text">
        </div>
        <div class="form-field form-field--icon">
            <i class="icon icon-password"></i>
            <input id="password" class="f-text" name="password" placeholder="密码" type="password">
        </div>
       
        <div class="form-field form-field--ops">
            <input id="login" class="btn" name="commit" value="登录" type="submit">
        </div>
    </div>

  

</div>

    </div>
    </div>

	<footer class="footer">
   
</footer>



</body></html>