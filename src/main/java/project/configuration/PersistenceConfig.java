package project.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by JoKeR on 27.12.2016.
 */
@Configuration
@MapperScan("project.persistence")
public class PersistenceConfig {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUsername("root");
        dataSource.setUrl("jdbc:mysql://localhost/e_school");
        dataSource.setPassword("");

        // create a table and populate some data
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Creating tables");
        jdbcTemplate.execute("DROP TABLE IF EXISTS students");
        jdbcTemplate.execute("create table students(id serial, groupId int(50), name varchar(256), surname varchar(256), phoneNumber varchar(256), eMail varchar(256))");
        jdbcTemplate.update("INSERT INTO students(groupId, name, surname, phoneNumber, email) values ('11', 'Ruslan', 'Lee', '87779994455', 'rusleon@gmail.com')");
        jdbcTemplate.update("INSERT INTO students(groupId, name, surname, phoneNumber, email) values ('12', 'Nursultan', 'Zholakov', '89997775544', 'nurlyzhol@gmail.com')");

        jdbcTemplate.execute("DROP TABLE IF EXISTS addresses");
        jdbcTemplate.execute("create table addresses(id serial, country varchar(256), city varchar(256), street varchar(256), home int(10), studentId int(10))");
        jdbcTemplate.update("INSERT INTO addresses(country, city, street, home, studentId) values ('Kazakhstan', 'Almaty', 'Zhankozha Batyr', '14', '1')");
        jdbcTemplate.update("INSERT INTO addresses(country, city, street, home, studentId) values ('England', 'London', 'London', '15', '2')");
        jdbcTemplate.update("INSERT INTO addresses(country, city, street, home, studentId) values ('Russia', 'Moscow', 'Red Square', '16', '1')");
        jdbcTemplate.update("INSERT INTO addresses(country, city, street, home, studentId) values ('Turkey', 'Ankara', 'Centre', '17', '2')");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("project");
        return sessionFactory;
    }
}