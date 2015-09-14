/**
 * 
 */
package com.meituan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.utils.HttpClientUtil;

/**
 * @author zhangqw
 * @version 2015年9月9日
 *
 * @description 系统工具类
 */

public class SigUtil {
	private static final Logger logger = LoggerFactory.getLogger(SigUtil.class);


	public static String sign(String serverUrl, Map<String, Object> params, String appSecret, String algorithm) {
		List<NameValuePair> nameValueParams = new ArrayList<NameValuePair>();
		// nameValueParams.add(new BasicNameValuePair("app_id", app_id));
		if (params != null && !params.isEmpty()) {
			for (String paramName : params.keySet()) {
				nameValueParams.add(new BasicNameValuePair(paramName, params.get(paramName).toString()));
			}
		}
		String paramForSig = sortParams(nameValueParams);
		if (null == serverUrl || serverUrl.isEmpty()) {
			return sum(paramForSig + appSecret, algorithm);
		} else {
			return sum(serverUrl + "?" + paramForSig + appSecret, algorithm);
		}
		// return signRequest(serverUrl, paramForSig, appSecret);
	}

	/**
	 * 针对参数排序
	 * 
	 * @param params
	 *            参数集合
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	private static String sortParams(List<NameValuePair> params) {
		List<String> keyList = new ArrayList<String>();
		// params.add(new BasicNameValuePair("timestamp", timestamp + ""));
		Collections.sort(params, new Comparator<NameValuePair>() {
			public int compare(NameValuePair nameValuePair1, NameValuePair nameValuePair2) {
				return nameValuePair1.getName().compareTo(nameValuePair2.getName());
			}
		});
		for (int i = 0; i < params.size(); i++) {
			String value = params.get(i).getValue() == null ? "" : params.get(i).getValue();
			String keyValue = params.get(i).getName() + "=" + value;
			keyList.add(keyValue);
		}
		return StringUtils.join(keyList, "&");
	}

	private static String sum(String src, String algorithm) {
		logger.debug(src);
		logger.debug(algorithm);
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byte2hex(md.digest(src.getBytes()));
	}

	/**
	 * 二进制转化为大写的十六进制
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer buf = new StringBuffer();
		int i;

		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		String a = "http://120.26.103.47/MeituanAPI/Api/orderPushCallBack?app_id=123&app_poi_code=lesu_01&caution= 没零钱&city_id=110100&ctime=1442228861&delivery_time=0&detail=[{\"app_food_code\":\"83e3a2702f01469ca537b3ddcd1e97d2\",\"box_num\":0,\"box_price\":0,\"food_discount\":1,\"food_name\":\"二块香辣鸡翅M\",\"price\":11.1,\"quantity\":1,\"sku_id\":\"83e3a2702f01469ca537b3ddcd1e97d2\",\"unit\":\"份\"},{\"app_food_code\":\"e21393cf73094f49a2b07584be6ca0a6\",\"box_num\":0,\"box_price\":0,\"food_discount\":1,\"food_name\":\"楚味烤翅2块\",\"price\":11,\"quantity\":1,\"sku_id\":\"e21393cf73094f49a2b07584be6ca0a6\",\"unit\":\"份\"},{\"app_food_code\":\"3e4427521e884e34b8e9756e119aa119\",\"box_num\":0,\"box_price\":0,\"food_discount\":1,\"food_name\":\"劲爆鸡米花(大)\",\"price\":12.5,\"quantity\":1,\"sku_id\":\"3e4427521e884e34b8e9756e119aa119\",\"unit\":\"份\"}]&extras=[]&has_invoiced=1&invoice_title=11&is_third_shipping=0&latitude=39.903849&longitude=116.397731&order_id=6124977&original_price=44.1&pay_type=1&recipient_address=天安门广场 (111)&recipient_name=张三 (先生)&recipient_phone=15950592210&shipper_phone=&shipping_fee=9.5&status=2&timestamp=1442228899&total=44.1&utime=1442228861&wm_order_id_view=1060443550696829&wm_poi_address=北京市西城区北三环中路18号&wm_poi_id=106044&wm_poi_name=BJN180马甸&wm_poi_phone=025-52668940c613fa5af229499caf01acf757919965";
		System.out.println(SigUtil.sum(a, "MD5"));
	}
}
