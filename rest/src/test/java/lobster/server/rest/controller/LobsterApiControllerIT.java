package lobster.server.rest.controller;

import lobster.server.rest.model.Lobster;
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
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class LobsterApiControllerIT {

    private RestTemplate restTemplate;

    @Before
    public void restTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));


    }

    @org.junit.Test
    public void add() {
        Lobster lobster = new Lobster();
        lobster.setName("test");

        Integer id = restTemplate.postForObject("http://localhost:8080/api/lobster/new", lobster, Integer.class);
        lobster.setId(id);
        lobster.setEmail("email");
        lobster.setStatus(null);

        assertThat(id, is(1));
        System.out.println(id);

    }



    @org.junit.Test
    public void getAll() {

        List < Lobster > list;
        ResponseEntity<List> l;
        l = restTemplate.getForEntity("http://localhost:8080/api/lobster/list", List.class);

        System.out.println(l.toString());
    }
}