<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="meta/config.jsp"%>
<html  class="no-js"> 

<!-- BEGIN HEAD -->

<head>

	
	<title>美团外卖管理后台</title>
 	<%@ include file="meta/meta.jsp"%>
 	<script  src="${ctx}/static/js/pages/poiList.js" type="text/javascript"></script>
<script src="${ctx}/static/media/js/bootstrap-paginator.js" type="text/javascript"></script>

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

							门店一览<small></small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a id="home_href" href="#">Home</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">门店管理</a></li>

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
        <div class="portlet box yellow">
            <div class="portlet-title">
                <div class="caption"><i class="icon-briefcase"></i>门店查询</div>
                <div class="tools">
                    <a href="javascript:;" class="collapse"></a>
                </div>
            </div>
            <div class="portlet-body">
                <div>
                    <form id="form" class="form-horizontal">
                        <div class="row-fluid">
                            <div class="span3" style="width: 200px">
                                <div class="control-group">
                                    <label class="span4 pt5 tr pr5">门店名称</label>
                                    <input name="wm_poi_name" class="m-wrap span7" placeholder="门店名称" value="" type="text">
                                </div>
                            </div>
                            <div class="span3" style="width: 200px">
                                <div class="control-group">
                                    <label class="span4 pt5 tr pr5">门店名称</label>
                                    <input name="wm_poi_name" class="m-wrap span7" placeholder="门店名称" value="" type="text">
                                </div>
                            </div>
                            <div class="span3">
                                <label class="span4 pt5 tr pr5">门店CODE</label>
                                <input name="app_poi_code" class="m-wrap span7" placeholder="门店CODE" value="" type="text">
                            </div>
                           

                        </div>
                        <div class="row-fluid">
                            <div class="span7">&nbsp;
                            </div>
                            <div class="span1">
                                <a href="javascript:void(0)" id="J-btn-search" class="btn blue btn-block">查询</a>
                            </div>
                            <div class="span2">
                                <a href="javascript:void(0)" id="J-btn-export" class="btn blue btn-block">下载列表</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-briefcase"></i>门店一览</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				 <div class="portlet-body"  >
				 <table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>门店ID</th>
								<th>门店名称</th>
								<th>所属APP</th>
								<th>电话号码</th>
								<th>地址</th>
								<th>有效期</th>
								<th colspan=2>操作</th>
							</tr>
						</thead>
						<tbody id="list">
						</tbody>
						</table>
				 </div>
			</div>
			<div id="example" class="pagination pagination-centered" style="text-align: center;"></div>
			<!-- END SAMPLE TABLE PORTLET-->
			<!-- url -->
			<div class="modal fade" id="J-callback-url" style="display:block">
              <div class="modal-dialog" style="display:block">
                <div class="modal-content" style="display:block">
                  <div class="modal-header">
                    <button type="button" class="pull-right" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="glyphicon glyphicon-remove "></span></button>
                    <h4 class="modal-title">修改APP</h4>
                  </div>
                  <div class="modal-body">
                    <form role="form" action="#">
                    	<div class="form-group">
                    		<div class="alert alert-danger" role = "alert">
                    		提示：不要将密钥告诉他人
                    		</div>
                    	</div>
                       <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-secret-text" placeholder="密钥"/>
                       </div>
                        <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-price-text" placeholder="年费"/>
                       </div>
                        <div class="input-group input-group-lg">
                           <input type="text" class="m-wrap span12" id="J-desc-text" placeholder="描述"/>
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