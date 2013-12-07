package lobster.server.rest.controller;

import lobster.persistence.jpa.repository.StatusRepository;
import lobster.persistence.model.Lobster;
import lobster.persistence.model.Status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 *
 */
public class LobsterApiControllerIT {

    @Autowired
    private StatusRepository statusRepository;

    private RestTemplate restTemplate;

    @Before
    public void restTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));
    }

    @Test
    public void add() {
        Lobster lobster = new Lobster();
        lobster.setName("test");
        lobster.setEmail("email");

        Long id = restTemplate.postForObject("http://localhost:8080/api/lobsters", lobster, Long.class);
        assertNotNull(id);

        lobster = restTemplate.getForObject("http://localhost:8080/api/lobsters/{lobsterId}", Lobster.class, id);
        assertNotNull(lobster);
        assertNull(lobster.getStatus());
    }

    @Test
    public void getAll() {

        ResponseEntity<List> l;
        l = restTemplate.getForEntity("http://localhost:8080/api/lobsters", List.class);

        System.out.println(l.toString());
    }


    @Test
    public void giveFood() {
        Status status = restTemplate.postForObject("http://localhost:8080/api/lobster/1/givefood/1", null, Status.class);
        status = restTemplate.postForObject("http://localhost:8080/api/lobsters/1/givefood/2", null, Status.class);

        assertNotNull(status);
        assertThat(status.getVitamineAmountList().size(), is(4));

    }

    @Test
    public void doActivity() {
        restTemplate.postForLocation("http://localhost:8080/api/lobsters/1/doActivity/1", null);
    }

}
