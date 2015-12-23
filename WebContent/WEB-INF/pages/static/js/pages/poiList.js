 $(function () {
    var carId = 1;
    $.ajax({
      url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+carId,
      success: function (data) {
        if (data != null) {
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
            $("#list").append('<table id="data_table" class="table table-hover"');
            $("#list").append('<thead>');
            $("#list").append('<tr>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center">Id</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店ID</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店名称</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>所属APP</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店电话号码</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店地址</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店有效期</th>');
            $("#list").append('<th width="35" rowspan="2" style="text-align:center>操作</th>');
            $("#list").append('<th> </th>');
            $("#list").append('</tr>');
            $("#list").append('</thead>');
            $("#list").append('<tbody>');
            $("#list").append('<tr>');
            $("#list").append('<td text-align:center><div class="th-inner">' + index+ '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.app_poi_code + '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_name + '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.appid + '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_phone + '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_address + '</div></td>');
            $("#list").append('<td text-align:center><div class="th-inner">' + item.expiredate + '</div></td>');
            $("#list").append('<td>');
            $("#list").append('<button class="btn red edit" onclick="editPoi(' + item.app_poi_code + ' );">修改</button>');
            $("#list").append('<button class="btn red delete" onclick="deletePoi(' + item.app_poi_code + ' );">删除</button>');
            $("#list").append('</td>');
            $("#list").append('</tr>');
            $("#list").append('</tbody>');
            $("#list").append('</table>');
          });
          var pageCount = eval("(" + data + ")").pageCount; //取到pageCount的值(把返回数据转成object类型)
          var currentPage = eval("(" + data + ")").CurrentPage; //得到urrentPage
          var options = {
            bootstrapMajorVersion: 2, //版本
            currentPage: currentPage, //当前页数
            totalPages: pageCount, //总页数
            itemTexts: function (type, page, current) {
              switch (type) {
                case "first":
                  return "首页";
                case "prev":
                  return "上一页";
                case "next":
                  return "下一页";
                case "last":
                  return "末页";
                case "page":
                  return page;
              }
            },//点击事件，用于通过Ajax来刷新整个list列表
            onPageClicked: function (event, originalEvent, type, page) {
              $.ajax({
            	url: _ctx + "/Api/web/poiList?userId="+SessionCache.get("userId")+"&pageId="+page,
                success: function (data1) {
                  if (data1 != null) {
                    $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
                    	 $("#list").append('<table id="data_table" class="table table-hover">');
                         $("#list").append('<thead>');
                         $("#list").append('<tr>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center">Id</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店ID</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店名称</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>所属APP</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店电话号码</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店地址</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>门店有效期</th>');
                         $("#list").append('<th width="35" rowspan="2" style="text-align:center>操作</th>');
                         $("#list").append('<th> </th>');
                         $("#list").append('</tr>');
                         $("#list").append('</thead>');
                         $("#list").append('<tbody>');
                         $("#list").append('<tr>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.app_poi_code + '</div></td>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_name + '</div></td>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.appid + '</div></td>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_phone + '</div></td>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.wm_poi_address + '</div></td>');
                         $("#list").append('<td text-align:center><div class="th-inner">' + item.expiredate + '</div></td>');
                         $("#list").append('<td>');
                         $("#list").append('<button class="btn red edit" onclick="editPoi(' + item.app_poi_code + ' );">修改</button>');
                         $("#list").append('<button class="btn red delete" onclick="deletePoi(' + item.app_poi_code + ' );">删除</button>');
                         $("#list").append('</td>');
                         $("#list").append('</tr>');
                         $("#list").append('</tbody>');
                         $("#list").append('</table>');                 
                     });
                  }
                }
              });
            }
          };
          $('#example').bootstrapPaginator(options);
        }
      }
    });
  })