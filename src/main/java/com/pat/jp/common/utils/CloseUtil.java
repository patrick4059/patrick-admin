package com.pat.jp.common.utils;

import java.io.Closeable;

/**
 * @author: huangwei
 * @date: 2023-07-14
 * @time: 22:16
 **/
public class CloseUtil {

	public static void close(Closeable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (Exception e) {
				// 静默关闭
			}
		}
	}

	public static void close(AutoCloseable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (Exception e) {
				// 静默关闭
			}
		}
	}
}
