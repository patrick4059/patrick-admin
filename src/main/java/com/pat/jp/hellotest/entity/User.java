package com.pat.jp.hellotest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 17:30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    int age;
    String sex;
}
