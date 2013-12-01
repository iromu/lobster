package lobster.server.rest;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

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
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("lobster.persistence.jpa.repository")
@ImportResource({"classpath:spring-context.xml"})
public class ServicesConfiguration {

    @Bean
    public CacheManager cacheManager() throws Exception {
        SimpleCacheManager scm = new SimpleCacheManager();
        scm.setCaches(Arrays.asList(new ConcurrentMapCache("lobsters"), new ConcurrentMapCache("food")));
        return scm;
    }

}