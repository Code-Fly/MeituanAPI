<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="meta/config.jsp"%>
<html  class="no-js"> 

<!-- BEGIN HEAD -->

<head>

	
	<title>美团外卖管理后台</title>
 	<%@ include file="meta/meta.jsp"%>
 	<script  src="${ctx}/static/js/pages/userList.js" type="text/javascript"></script>


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

 <%@ include file="meta/navigator.jsp"%>

	<!-- BEGIN CONTAINER -->

	<div class="page-container">

 	<%@ include file="meta/menu.jsp"%>

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div id="portlet-config" class="modal hide">

				<div class="modal-header">

					<button data-dismiss="modal" class="close" type="button"></button>

					<h3>Widget Settings</h3>

				</div>

				<div class="modal-body">

					Widget settings form goes here

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->

				<div class="row-fluid">

					<div class="span12">

						  

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							用户一览<small></small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a id="home_href" href="#">Home</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">用户管理</a></li>

							<li class="pull-right no-text-shadow">

								<div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">

									<i class="icon-calendar"></i>

									<span></span>

									<i class="icon-angle-down"></i>

								</div>

							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<div id="dashboard">

					<div class="row-fluid">
		<div class="span10">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-briefcase"></i>用户一览</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
					<div class="actions">
						<button onclick="" id ="addUser" class="btn red edit">增 加 用 户</button>
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>登陆名</th>
								<th>年费单价</th>
								<th>状态</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="user" items="${users}" varStatus="st">
								<tr>
									<td>${st.count}</td>
									<td>${user.login_id}</td>
									<td>${user.nfdj}</td>
									
									<td><c:choose><c:when test="${user.status==1}">启用</c:when> <c:otherwise>停用</c:otherwise></c:choose></td>
									<td>${user.descption}</td>
									<td>
										<c:choose>
											<c:when test="${user.status ==1}">
											<button id="stop_${user.user_id}" type="button" onclick="stop(${user.user_id})" class="btn red delete" name="edit_cancel_url" >停用账号</button>
											</c:when>
										<c:otherwise>
											<button id="start_${user.user_id}" type="button" onclick="start(${user.user_id})" class="btn red delete" name="edit_cancel_url" >启用账号</button>
										</c:otherwise>
										</c:choose>
											<button id="reset_pwd_${user.user_id}" type="button" onclick="resetPwd(${user.user_id})"  class="btn red edit" name="edit_cancel_url">重置密码</button>
										
									</td>
									
								</tr>
						</c:forEach>
								
						</tbody>
					</table>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
			<!-- url -->
			<div class="modal fade" id="J-callback-url" style="display:block">
              <div class="modal-dialog" style="display:block">
                <div class="modal-content" style="display:block">
                  <div class="modal-header">
                    <button type="button" class="pull-right" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="glyphicon glyphicon-remove "></span></button>
                    <h4 class="modal-title">增加用户</h4>
                  </div>
                  <div class="modal-body">
                    <form role="form" action="#">
                    	<div class="form-group">
                    		<div class="alert alert-danger" role = "alert">
                    		提示：请用户尽快修改默认密码
                    		</div>
                    	</div>
                       <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-login_id-text" placeholder="登陆名"/>
                       </div>
                        <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-nfdj-text" placeholder="年费单价"/>
                       </div>
                        <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-descption-text" placeholder="描述"/>
                       </div>
                      </form>                           
                  </div>
                  <div class="modal-footer">                      
                      <button type="button" class="btn btn-primary" id="J-submit-btn">确认</button>
                      <button type="button" class="btn btn-default" id="J-cancle-btn" data-dismiss="modal">取消</button>
                  </div>

                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div>
		</div>
	</div>

			</div>

			<!-- END PAGE CONTAINER-->    

		</div>

		<!-- END PAGE -->

	</div>
   </div>
	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			2013 &copy; Metronic by keenthemes.

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>



	<script>

		jQuery(document).ready(function() {    

		   App.init(); // initlayout and core plugins

		   Index.init();
		   

		   /* Index.initJQVMAP(); // init index page's custom scripts

		   Index.initCalendar(); // init index page's custom scripts

		   Index.initCharts(); // init index page's custom scripts

		   Index.initChat();

		   Index.initMiniCharts();

		   Index.initDashboardDaterange();

		   Index.initIntro(); */

		});

	</script>




</html>