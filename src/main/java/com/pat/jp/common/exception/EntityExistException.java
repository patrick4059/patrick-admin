package com.pat.jp.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author: huangwei
 * @date: 2023-07-13
 * @time: 18:46
 **/
public class EntityExistException extends RuntimeException {

	public EntityExistException(Class clazz, String field, String val) {
		super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
	}

	private static String generateMessage(String entity, String field, String val) {
		return StringUtils.capitalize(entity)
				+ " with " + field + " "+ val + " existed";
	}
}
