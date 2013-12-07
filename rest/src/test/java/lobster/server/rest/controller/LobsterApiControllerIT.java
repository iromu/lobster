package lobster.server.rest.controller;

import lobster.persistence.model.Food;
import lobster.persistence.model.Lobster;
import lobster.persistence.model.Status;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 */
public class LobsterApiControllerIT extends AbstractControllerTest {

    protected static int TOTAL = 6;

    @Test
    public void add() {
        Lobster lobster = new Lobster();
        lobster.setName("test");
        lobster.setEmail("email");

        Long id = restTemplate.postForObject(HTTP_API + "lobsters", lobster, Long.class);
        assertNotNull(id);
        TOTAL++;

        lobster = restTemplate.getForObject(HTTP_API + "lobsters/{lobsterId}", Lobster.class, id);
        assertThat(lobster, notNullValue());
        assertThat(lobster.getStatus(), notNullValue());
    }

    @Test
    public void getAll() {
        List<Food> list = restTemplate.getForObject(HTTP_API + "lobsters", List.class);
        assertThat(list.size(), is(TOTAL));
    }


    @Test
    public void giveFood() {
        Status status = restTemplate.postForObject(HTTP_API + "lobsters/1/givefood/2", null, Status.class);
        assertThat(status, notNullValue());
        assertThat(status.getVitamineAmountList().size(), is(3));

    }

    @Test
    public void doActivity() {
        restTemplate.postForLocation(HTTP_API + "lobsters/1/doActivity/1", null);
    }

}
