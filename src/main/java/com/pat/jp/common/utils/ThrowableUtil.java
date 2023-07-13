package com.pat.jp.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description: 异常工具
 * @author: huangwei
 * @date: 2023-07-13
 * @time: 18:40
 **/
public class ThrowableUtil {

	/**
	 * 获取堆栈信息
	 */
	public static String getStackTrace(Throwable throwable){
		StringWriter sw = new StringWriter();
		try (PrintWriter pw = new PrintWriter(sw)) {
			throwable.printStackTrace(pw);
			return sw.toString();
		}
	}
}
