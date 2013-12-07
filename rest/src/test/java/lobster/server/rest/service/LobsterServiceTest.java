package lobster.server.rest.service;

import lobster.persistence.model.Lobster;
import lobster.server.rest.context.PersistenceConfig;
import lobster.server.rest.context.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by wantez on 07/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {ServiceConfig.class, PersistenceConfig.class}, loader = AnnotationConfigContextLoader.class)
public class LobsterServiceTest {

    @Inject
    LobsterService service;

    @Test
    public void testCreate() throws Exception {
        Lobster lobster = new Lobster();
        lobster.setName("test");
        lobster.setEmail("email");

        Long id = service.create(lobster);

        assertThat(id, notNullValue());

        lobster = service.getById(id);

        assertThat(lobster, notNullValue());
        assertThat(lobster.getStatus(), notNullValue());
        assertThat(lobster.getStatus().getVitamineAmountList().size(), is(5));
    }
}
