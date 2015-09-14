# MeituanAPI

###	Callback API
##### Order Push Callback API
http://www.mydomain.com/Api/orderPushCallback<br/>
method: POST<br/>
param: "app_id", required = true<br/>
param: "timestamp", required = true<br/>
param: "order_id", required = true<br/>
param: "app_poi_code", required = true<br/>
param: "sig", required = true<br/>

###	Order API
##### Get order List
http://www.mydomain.com/Api/doGetOrder<br/>
method: GET<br/>
param: "app_id", required = true<br/>
param: "timestamp", required = true<br/>
param: "app_poi_code", required = true<br/>
param: "sig", required = true<br/>
##### Confirm orders
http://www.mydomain.com/Api/orderConfirm<br/>
method: POST<br/>
param: "app_id", required = true<br/>
param: "timestamp", required = true<br/>
param: "order_id_list", required = true<br/>
param: "sig", required = true<br/>