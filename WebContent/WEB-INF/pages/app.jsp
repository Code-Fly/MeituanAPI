<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="meta/config.jsp"%>
<html  class="no-js"> 

<!-- BEGIN HEAD -->

<head>

	
	<title>美团外卖管理后台</title>
 	<%@ include file="meta/meta.jsp"%>
 	


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

						<!-- BEGIN STYLE CUSTOMIZER -->

						<div class="color-panel hidden-phone" style="display: none;">

							<div class="color-mode-icons icon-color"></div>

							<div class="color-mode-icons icon-color-close"></div>

							<div class="color-mode">

								<p>THEME COLOR</p>

								<ul class="inline">

									<li class="color-black current color-default" data-style="default"></li>

									<li class="color-blue" data-style="blue"></li>

									<li class="color-brown" data-style="brown"></li>

									<li class="color-purple" data-style="purple"></li>

									<li class="color-grey" data-style="grey"></li>

									<li class="color-white color-light" data-style="light"></li>

								</ul>

								<label>

									<span>Layout</span>

									<select class="layout-option m-wrap small">

										<option value="fluid" selected>Fluid</option>

										<option value="boxed">Boxed</option>

									</select>

								</label>

								<label>

									<span>Header</span>

									<select class="header-option m-wrap small">

										<option value="fixed" selected>Fixed</option>

										<option value="default">Default</option>

									</select>

								</label>

								<label>

									<span>Sidebar</span>

									<select class="sidebar-option m-wrap small">

										<option value="fixed">Fixed</option>

										<option value="default" selected>Default</option>

									</select>

								</label>

								<label>

									<span>Footer</span>

									<select class="footer-option m-wrap small">

										<option value="fixed">Fixed</option>

										<option value="default" selected>Default</option>

									</select>

								</label>

							</div>

						</div>

						<!-- END BEGIN STYLE CUSTOMIZER -->    

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							用户 <small></small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="index.html">Home</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">用户信息</a></li>

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
								<th>用户名</th>
								<th>年费单价</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>1</td>
									<td>${user.login_id}</td>
									<td>${user.nfdj}</td>
									<td>${user.descption}</td>
									<td><a href="login" class="btn mini green">进入审核门店</a></td>
									<td>	<button type="button" class="btn red edit" name="edit_cancel_url" date-message="http://120.26.103.47:8080/MeituanAPI/Api/cancelOrder">修改</button><td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
			<!-- url -->
			<div class="modal fade" id="J-callback-url">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="pull-right" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="glyphicon glyphicon-remove "></span></button>
                    <h4 class="modal-title">修改URL</h4>
                  </div>
                  <div class="modal-body">
                    <form role="form" action="#">
                    	<div class="form-group">
                    		<div class="alert alert-danger" role = "alert">
                    		请保证正式和测试环境的回调URL地址不同
                    		</div>
                    	</div>
                       <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-url-text" placeholder="请输入对应URL"/>
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