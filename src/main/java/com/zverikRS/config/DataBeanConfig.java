package com.zverikRS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

@Configuration
//TODO
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class DataBeanConfig {
    private Environment environment;

    // -------------------------------------------------- spring-jdbc --------------------------------------------------
    /**
     * Основной класс через который производим запросы в БД
     *
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(this.dataSource());
        return jdbcTemplate;
    }

    /**
     * Настройки данных для подключения к базе данных
     * в файле resources/util.properties настройки по подключению к базе данных
     * подключаем настройки по подключению к базе данных по средством анотации на уровне класса @PropertySource(value = "classpath:util.properties")
     * далее с файла util.properties получаем данные через вспомагательный класс Environment (org.springframework.core.env.Environment)
     *
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(this.dataSource());
    }

    // ------------------------------------------------ spring-security ------------------------------------------------
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Подключение Spring-security через запросы БД
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl dao = new JdbcDaoImpl();
        dao.setDataSource(this.dataSource());
        dao.setUsersByUsernameQuery(this.environment.getRequiredProperty("usersByQuery"));
        dao.setAuthoritiesByUsernameQuery(this.environment.getRequiredProperty("rolesByQuery"));
        return dao;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
