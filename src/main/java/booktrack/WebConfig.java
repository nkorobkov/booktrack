package booktrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class WebConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("booktrack.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        //won't work automaticaly for some reason

        dataSource.setUrl(env.getProperty("SPRING_DATASOURCE_URL"));
        dataSource.setPassword(env.getProperty("SPRING_DATASOURCE_PASSWORD"));
        dataSource.setUsername(env.getProperty("SPRING_DATASOURCE_USERNAME"));

        return dataSource;
    }


    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.setProperty(
                "hibernate.show_sql", "true");
        hibernateProperties.setProperty(
                "hibernate.dialect", env.getProperty("SPRING_JPA_DATABASE_PLATFORM"));
        hibernateProperties.setProperty(
                "hibernate.current_session_context_class", "thread");
        return hibernateProperties;
    }
}
