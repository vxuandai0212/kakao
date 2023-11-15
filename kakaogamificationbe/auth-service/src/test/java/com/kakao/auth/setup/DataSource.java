package com.kakao.auth.setup;

import org.testcontainers.containers.MySQLContainer;

public class DataSource {
    private static MySQLContainer<?> mySQLContainer;
    static {
        mySQLContainer = (MySQLContainer<?>) new MySQLContainer("mysql:latest")
            .withDatabaseName("demo")
            .withUsername("root")
            .withPassword("root");
        mySQLContainer.start();
    }
}
