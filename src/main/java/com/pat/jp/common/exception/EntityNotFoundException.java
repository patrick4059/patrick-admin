package com.pat.jp.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 11:20
 **/
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
