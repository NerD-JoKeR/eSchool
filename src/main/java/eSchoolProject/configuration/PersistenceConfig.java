package hello.configuration;

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
@MapperScan("hello.persistence")
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
        jdbcTemplate.update("INSERT INTO students(groupId, name, surname, phoneNumber, email) values ('11', 'Mike', 'Lanyon', '+78889994455', 'lanyonm@gmail.com')");
        jdbcTemplate.update("INSERT INTO students(groupId, name, surname, phoneNumber, email) values ('11', 'Mike', 'Lanyon', '+78889994455', 'lanyonm@gmail.com')");

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
        sessionFactory.setTypeAliasesPackage("hello");
        return sessionFactory;
    }
}