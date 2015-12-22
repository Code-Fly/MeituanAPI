require.config({
    baseUrl :  MT.STATIC_ROOT,
    paths:{
        'jquery':"lib/jquery-1.10.1.min",
        'basemodule':'modules/baseModule',
        'introjs':'intro/intro'
    },
    shim: {
        'jquery':{ exports: "$"},
        'basemodule':{
            'deps': ['jquery'],
            'exports': 'jQuery.fn.basemodule'
        }

    }
});
require(['basemodule','introjs'],
		function(basemodule,introjs){
            var version = getCookie('intro_version');
            if(!version || version != '1.0'){
               setCookie('intro_version','1.0',365);
                $(".page-sidebar-menu").children().eq(3).find('.sub-menu').show();
                introjs().start();
            }

});


function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1)
        {
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
}

function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+
                    ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

function checkCookie()
{
    username=getCookie('username')
    if (username!=null && username!="")
    {alert('Welcome again '+username+'!')}
    else
    {
        username=prompt('Please enter your name:',"")
        if (username!=null && username!="")
        {
            setCookie('username',username,365)
        }
    }
}