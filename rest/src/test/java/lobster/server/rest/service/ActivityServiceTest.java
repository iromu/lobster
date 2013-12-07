package lobster.server.rest.service;

import lobster.persistence.model.Activity;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by wantez on 07/12/13.
 */
public class ActivityServiceTest extends AbstractServiceTest {

    @Inject
    ActivityService service;

    @Test
    public void testGetAll() throws Exception {
        List<Activity> all = service.getAll();
        assertThat(all.size(),is(5));
    }
}
