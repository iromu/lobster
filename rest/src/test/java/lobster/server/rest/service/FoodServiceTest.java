package lobster.server.rest.service;

import lobster.persistence.model.Food;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by wantez on 07/12/13.
 */
public class FoodServiceTest extends AbstractServiceTest {

    @Inject
    FoodService service;

    @Test
    public void testGetAll() throws Exception {
        List<Food> all = service.getAll();
        assertThat(all.size(), is(8));
    }
}
