package lobster.server.rest.controller;

import lobster.persistence.model.Status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 4:13
 */
public class StatusApiControllerIT extends AbstractControllerTest {

    @Test
    public void get() {
        Status status = restTemplate.getForObject(HTTP_API + "status/{lobsterId}", Status.class, 1L);
        assertThat(status.getId(), is(1L));
    }
}
