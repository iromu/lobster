package lobster.server.rest.controller;

import lobster.persistence.model.Food;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 */
public class FoodApiControllerIT extends AbstractControllerTest {

    @Test
    public void getAll() {
        List<Food> list = restTemplate.getForObject(HTTP_API + "food", List.class);
        assertThat(list.size(), is(8));
    }
}
