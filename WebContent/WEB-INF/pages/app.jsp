<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="meta/config.jsp"%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>美团外卖开发者后台</title>
 <%@ include file="meta/meta.jsp"%>
<body class="page-header-fixed"> 
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<div class="pl20">
					<a class="brand" href="/">
						美团外卖开发者后台
					</a>
				</div>
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<img src="/static/image/menu-toggler.png" alt="">
				</a>
				<ul class="nav pull-right">
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="username">Tee966770839</span>
							<i class="icon-angle-down"></i>
						</a>
		
						<ul class="dropdown-menu">
							<!--
							<li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>
							<li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>
							<li class="divider"></li>
							-->
							<li><a href="/logout"><i class="icon-key"></i> 退出登录</a></li>
						</ul>
		
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	
	<div class="page-container" id="">
		
		<div class="page-sidebar nav-collapse collapse">
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="start active">
					<a href="/">
						<i class="icon-home"></i> 
						<span class="title">首页</span>
						<span class="selected"></span>
					</a>
				</li>
				
					<!-- 开发文档和SDK -->
					<li>
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i> 
							<span class="title">开发文档和SDK</span>
							<span class="arrow open"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/doc/show" target="_brank">开发文档</a>
							</li>
							<li>
								<a href="/sdk/download">SDK下载</a>
							</li>
							
						</ul>
					</li>

					<!-- 基础设置 -->
					<li>
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i> 
							<span class="title">基础设置</span>
							<span class="arrow open"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/basis/callback/get">回调接口设置</a>
							</li>

							<li>
                            	<a href="/basis/notifySet/get" data-intro="点击这里可为该账号已注册的手机号设置门店营业状态变化的通知短信哦~" data-step="1" data-position="right">推送通知设置</a>
                            </li>
                            <li>
								<a href="/basis/notify/get" data-intro="点击可查看消息通知的记录~" data-step="2" data-position="right">推送通知记录</a>
							</li>
						</ul>
					</li>

					<!-- 门店管理 -->
					<li>
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i> 
							<span class="title">门店管理</span>
							<span class="arrow open"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/poi/list">门店信息</a>
							</li>
							<!--
							<li>
								<a href="#">门店列表</a>
							</li>
							-->
						</ul>
					</li>
				
					<!-- 订单管理 -->
					<li>
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i> 
							<span class="title">订单查询</span>
							<span class="arrow open"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/order/detail">订单信息查询</a>
							</li>
							<li>
                            	<a href="/order/settlement">订单结算查询</a>
                            </li>
                            <li>
                                <a href="/record/show">订单记录查询</a>
                            </li>
						</ul>
					</li>
				
					<!-- 退款订单管理 -->
					<li name="refundLi">
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i>
							<span class="title">退单查询</span>
							<span class="arrow open"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/refund/list">待处理<span class="badge" style="background-color:red">0</span></a>
							</li>
							<li>
								<a href="/refund/handlelist">已处理</a>
							</li>
						</ul>
					</li>
				
				
					<!-- 活动设置 -->
				
					<!-- 常用工具 -->
					<li>
						<a href="javascript:;">
							<i class="icon-bookmark-empty"></i> 
							<span class="title">常用工具</span>
							<span class="arrow open"> </span>
							<span class="selected"> </span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="/tools/poishipping">绘制门店坐标及配送范围</a>
							</li>
								<li>
									<a href="/tools/thirdsparea">APP配送方支持配送范围</a>
								</li>
							<li>
                                <a href="/tools/apitest">API测试工具</a>
                            </li>
						</ul>
					</li>
					<!-- end 常用工具-->
				
				<!-- 管理员面板 -->
				
				
				
				
			</ul>
		</div>
		
		<div class="page-content">
	
	



<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title">
				欢迎访问开发者后台
			</h3>
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="javascript:void(0)">首页</a> 
				</li>
			</ul>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="span6">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-briefcase"></i>拥有的App权限</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>App名称</th>
								<th>上线时间</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>1</td>
									<td>乐速面疙瘩</td>
									<td>2015-9-6</td>
									<td><a href="/poi/list?app_id=123" class="btn mini green">进入审核门店</a></td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</div>


	</div>
</div>

<div class="footer" id="">
	

	<div class="footer-tools">
		<span class="go-top"><i class="icon-angle-up"></i></span>
	</div>


	<!-- 页面的启动脚本文件 start-->
		    <script type="text/javascript" data-main="/static/js/page/home" src="/static/js/lib/r.js"></script>
	<!-- 页面的启动脚本文件 end-->

</div>



		
</body></html>