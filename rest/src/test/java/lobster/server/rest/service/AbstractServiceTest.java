package lobster.server.rest.service;

import lobster.server.rest.context.PersistenceConfig;
import lobster.server.rest.context.ServiceConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wantez on 07/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {ServiceConfig.class, PersistenceConfig.class}, loader = AnnotationConfigContextLoader.class)
public abstract class AbstractServiceTest {
}
