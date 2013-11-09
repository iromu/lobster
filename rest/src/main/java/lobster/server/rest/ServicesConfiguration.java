package lobster.server.rest;

import lobster.server.rest.model.*;
import lobster.server.rest.persistence.ActivityService;
import lobster.server.rest.persistence.FoodService;
import lobster.server.rest.persistence.LobsterService;
import lobster.server.rest.persistence.StatusService;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@EnableCaching
@EnableWebMvc
@EnableTransactionManagement
@ImportResource({"classpath:spring-quartz.xml"})
public class ServicesConfiguration {

    @Bean
    public LobsterService lobsterService() throws Exception {
        return new LobsterService(this.sessionFactory());
    }

    @Bean
    public FoodService foodService() throws Exception {
        return new FoodService(this.sessionFactory());
    }

    @Bean
    public ActivityService activityService() throws Exception {
        return new ActivityService(this.sessionFactory());
    }

    @Bean
    public StatusService statusService() throws Exception {
        return new StatusService(this.sessionFactory());
    }

    @Bean
    @SuppressWarnings("deprecation")
    public SessionFactory sessionFactory() throws Exception {
        Properties props = new Properties();

        Map<String, String> p = new HashMap<String, String>();
        p.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
        p.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import_h2.sql");
        p.put(org.hibernate.cfg.Environment.DIALECT, H2Dialect.class.getName());
        p.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        Map<String, String> propsMap = p;

        for (String k : propsMap.keySet())
            props.setProperty(k, propsMap.get(k));

        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(Lobster.class)
                .addAnnotatedClasses(Status.class)
                .addAnnotatedClasses(Activity.class)
                .addAnnotatedClasses(Vitamine.class)
                .addAnnotatedClasses(Food.class)
                .addAnnotatedClasses(StatusVitamine.class)
                .addProperties(props)
                .buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public DataSource dataSource() throws Exception {
        return new EmbeddedDatabaseBuilder()
                .setName("crm")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public CacheManager cacheManager() throws Exception {
        SimpleCacheManager scm = new SimpleCacheManager();
        scm.setCaches(Arrays.asList(new ConcurrentMapCache("lobsters"), new ConcurrentMapCache("food")));
        return scm;
    }

}