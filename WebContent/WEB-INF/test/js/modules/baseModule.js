require.config({
    baseUrl :  MT.STATIC_ROOT,
    paths:{
        'jquery':"lib/jquery-1.10.1.min",
        'jquery-ui': 'lib/jquery-ui-1.10.1.custom.min',
        'bootstrap':'lib/bootstrap.min',
        'appjs':'lib/app',
        'uniformjs':'lib/jquery.uniform.min',
    },
    shim: {
        'jquery':{ exports: "$"},
        'jquery-ui': {
            'deps': ['jquery'],
            'exports': 'jQuery.fn.jquery_ui'
        },
        'bootstrap':{
            'deps': ['jquery-ui'],
            'exports': 'jQuery.fn.bootstrap'
        },
        'appjs':{
            'deps': ['jquery'],
            'exports': 'jQuery.fn.app'
        },
        'uniformjs':{
        	'deps': ['appjs'],
            'exports': 'jQuery.fn.uniformjs'
        }
    }
});
require(['bootstrap','jquery-ui','appjs','uniformjs'],
		function(bootstrap,jquery_ui,appjs,uniformjs){
	
	
	var baseModule = {
	
	
		init : function(){
			App.init();
			baseModule.detectLogin();
		},
		
		detectLogin : function(){
			setInterval(function(){
				$.get("/logindetect",{},function(response){
					if(response.code == 1){
						
						if(!$("#loginWin") || $("#loginWin").length == 0){
							var loginPoiWin = '<div id="loginWin" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">' +
								'<div class="modal-body">' +
								'<p>您的登录已经超时，请点击 <a class="btn mini blue" href="javascript:void(0)" id="btn_hidden_login" onclick="window.open(\'/notLogin?lr=loginPopWin\',\'重新登录\',\'height=400,width=800,top=-20,left=-30,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no,alwaysRaised=yes\');$(\'#loginWin\').modal(\'hide\')">重新登录</a> ，不会影响现在的操作</p>' +
								'</div>' + 
								'<div class="modal-footer">' +
								'</div>' +
								'</div>';
							$("body").append(loginPoiWin);
						}
						$("#loginWin").modal("show");
					}
				});
			}, 300000);
		
		}
	}
	
	
	
	return baseModule.init();
});