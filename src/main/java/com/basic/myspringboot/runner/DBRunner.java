package com.basic.myspringboot.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
public class DBRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments arg) throws Exception{
        System.out.println("DataSource 구현 클래스 이름 :" +dataSource.getClass().getName());
        Connection connection=dataSource.getConnection();
        DatabaseMetaData metaData=connection.getMetaData();
        System.out.println("DB URL "+metaData.getURL());
        System.out.println("DB Vendor "+metaData.getDatabaseProductName());
        System.out.println("DB username "+metaData.getUserName());
    }
}
