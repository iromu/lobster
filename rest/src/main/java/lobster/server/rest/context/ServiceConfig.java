package lobster.server.rest.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wantez on 07/12/13.
 */
@Configuration
@ComponentScan({"lobster.server.rest.service"})
public class ServiceConfig {
}
