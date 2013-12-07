package lobster.server.rest.service;

import lobster.persistence.model.Status;
import org.hamcrest.core.Is;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertThat;

/**
 * Created by wantez on 07/12/13.
 */
public class StatusServiceTest extends AbstractServiceTest {

    @Inject
    StatusService service;

    @Test
    public void testFindByLobsterId() throws Exception {
        final Status status = service.findByLobsterId(1L);
        assertThat(status.getId(), Is.is(1L));
    }
}
