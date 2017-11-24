package lab.application;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
/*import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;*/
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource("classpath:/jdbc.properties")
@ComponentScan("lab")
//@EnableJpaRepositories
public class AppConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "rDataSource")
    public DataSource dataSource() {
        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put("eclipselink.weaving", "false");
        jpaProperties.put("jpaDialect"," org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect");
        factory.setJpaPropertyMap(jpaProperties);

        factory.setPackagesToScan("com.trail.jpatest");
        factory.setDataSource(dataSource());
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.setJpaDialect(new EclipseLinkJpaDialect());

        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    /*@Bean
    EclipseLinkJpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.FirebirdPlatform");
        jpaVendorAdapter.setGenerateDdl(Boolean.FALSE);

        return jpaVendorAdapter;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaDialect(new EclipseLinkJpaDialect());

        // Instead of persistence.xml
        entityManagerFactoryBean.setPersistenceUnitName("yourPersistenceUnitName");
        entityManagerFactoryBean.setPackagesToScan(
                "package.with.entities1",
                "package.with.entities2"
        );

        Properties jpaProperties = new Properties();
        jpaProperties.put(PersistenceUnitProperties.WEAVING, "static");
        jpaProperties.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        entityManagerFactoryBean.afterPropertiesSet();
        entityManagerFactoryBean.setLoadTimeWeaver(new ReflectiveLoadTimeWeaver());

        return entityManagerFactoryBean;
    }
*/
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
/*	@Bean
    public DataSource dataSource() {
        //This uses JNDI, you could create the data source in any way you want
        try {
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("jdbc:firebirdsql://192.168.99.100:32770/c:/database/books.fdb");
            return (DataSource) envCtx.lookup("jdbc/yourDS");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to lookup datasource", e);
        }
    }*/
}
