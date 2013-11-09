package lobster.server.rest.controller;

import lobster.server.rest.model.Activity;
import lobster.server.rest.model.Lobster;
import lobster.server.rest.model.Status;
import lobster.server.rest.model.StatusVitamine;
import lobster.server.rest.persistence.LobsterService;
import lobster.server.rest.persistence.StatusService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class LobsterApiControllerIT {

    @Autowired
    private StatusService statusService;

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

        Integer id = restTemplate.postForObject("http://localhost:8080/api/lobster/new", lobster, Integer.class);
        lobster.setId(id);
        lobster.setEmail("email");

        //assertThat(id, is(7));
        System.out.println(id);

        // Retrieve the status of the added element and check that the list of vitamins is filled
        Status status = restTemplate.getForObject("http://localhost:8080/api/status/getStatus/{lobsterId}", Status.class, id);
        assertNotNull(status);
        assertEquals(5, status.getStatusVitamineList().size());
    }

    @Test
    public void getAll() {

        List<Lobster> list;
        ResponseEntity<List> l;
        l = restTemplate.getForEntity("http://localhost:8080/api/lobster/list", List.class);

        System.out.println(l.toString());
    }


    @Test
    public void giveFood() {
        Status status = restTemplate.postForObject("http://localhost:8080/api/lobster/1/givefood/1", null, Status.class);
        status = restTemplate.postForObject("http://localhost:8080/api/lobster/1/givefood/2", null, Status.class);

        assertNotNull(status);
        assertThat(status.getStatusVitamineList().size(), is(3));
        for (StatusVitamine statusVitamine : status.getStatusVitamineList()) {
            assertThat(statusVitamine.getAmount(), is(2));
        }

    }

    @Test
    public void doActivity() {
        restTemplate.postForLocation("http://localhost:8080/api/lobster/1/doActivity/1", null);
    }

}
