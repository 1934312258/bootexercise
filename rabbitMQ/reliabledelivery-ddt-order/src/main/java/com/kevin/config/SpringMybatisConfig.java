package com.kevin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.kevin.support.DruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author kevin
 * @date 2019-11-11 16:53
 * @description todo
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties
@MapperScan(basePackages = {"com.kevin.mapper"})
public class SpringMybatisConfig {
    @Autowired
    private DruidDataSourceProperties properties;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUrl(properties.getJdbcUrl());
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMaxWait(properties.getMaxWait());
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setFilters(properties.getFilters());
        dataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        return dataSource;
    }

    @Bean   //此步操作框架会自动配置，无需手动添加，如果要配置多个事物管理器则需要手动配置
    public PlatformTransactionManager transactionManager() throws SQLException {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        return transactionManager;
    }
}
