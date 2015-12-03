/**
 * 
 */
package com.meituan.common;

import com.meituan.api.entity.ApiData;
import com.meituan.api.entity.ApiError;

/**
 * @author zhangqw 常量
 */
public interface MeituanConst {
	int CODE_803 = 803; // 门店不存在
	int CODE_703 = 703; // 签名错误
	int CODE_701 = 701; // 缺少参数，数据不完整
	int CODE_702 = 702; // app_id不存在
	int CODE_600 = 600; // 内部错误
	int CODE_2000 = 2000; // 门店过期
	
	String RETURN_OK = "ok";
	String RETURN_NG = "ng";

	public interface OrderStatus {
		// 用户已提交订单
		int USER_SUBMIT = 1;
		// 可推送到APP方平台也可推送到商家
		int PUSHED = 2;
		// 商家已收到
		int APP_RECEIVED = 3;
		// 商家已确认
		int APP_CONFIRMD = 4;
		// 已配送
		int DELIVERIED = 6;
		// 已完成
		int COMPLETED = 8;
		// 已取消
		int CANCELLED = 9;
	}
	
	/**
	 * 美团整体的错误码
	 * @author VM
	 *
	 */
	public interface MeituanError{
		ApiError ERROR_600 = new ApiError(MeituanConst.CODE_600, "内部错误");
		ApiError ERROR_702 = new ApiError(MeituanConst.CODE_702, "app_id不存在");
		ApiError ERROR_703 = new ApiError(MeituanConst.CODE_703, "签名验证错误");
		ApiError ERROR_803 = new ApiError(MeituanConst.CODE_803, "不存在此门店 ");
		ApiError ERROR_2000= new ApiError(MeituanConst.CODE_2000, "超过授权使用期 ");
	}
	
	/**
	 * 美团返回结果
	 * 调用成功返回值：{"data" : "ok"}，其余情况请参考美团 "快速开始-返回参数格式说明"
	 * @author VM
	 */
	public interface MeituanResponse{
		ApiData RESPONSE_OK = new ApiData(MeituanConst.RETURN_OK);
		ApiData RESPONSE_600 = new ApiData(MeituanConst.RETURN_NG, MeituanError.ERROR_600);
		ApiData RESPONSE_702 = new ApiData(MeituanConst.RETURN_NG, MeituanError.ERROR_702);
		ApiData RESPONSE_703 = new ApiData(MeituanConst.RETURN_NG, MeituanError.ERROR_703);
		ApiData RESPONSE_803 = new ApiData(MeituanConst.RETURN_NG, MeituanError.ERROR_803);
		ApiData RESPONSE_2000 = new ApiData(MeituanConst.RETURN_NG, MeituanError.ERROR_2000);
	}
	
}
