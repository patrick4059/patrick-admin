package com.pat.jp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: huangwei
 * @date: 2023-06-22
 * @time: 17:19
 **/

@SpringBootTest
public class MybatisTest {

    @Autowired
    DataSource dataSource;

    @Test
    void mybatis() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }
}
