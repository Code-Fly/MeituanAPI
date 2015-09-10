/**
 * 
 */
package com.base.common;

/**
 * @author zhangqw 常量
 */
public interface MeituanConst {
	String SIG = "sig";
	String APP_ID = "app_id";
	String TIME_STAMP = "timestamp";

	String CODE = "code";
	String MSG = "msg";
	String DATA = "data";

	interface MEITUAN_RETURN_CODE {
		int CODE_703 = 703;
		int CODE_701 = 701;
		int CODE_600 = 600;
	}

	String RETURN_OK = "ok";
	String RETURN_NG = "ng";
}
