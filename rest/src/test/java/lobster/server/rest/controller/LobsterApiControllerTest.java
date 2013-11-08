package lobster.server.rest.controller;

import lobster.server.rest.model.Lobster;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class LobsterApiControllerTest {

    private RestTemplate restTemplate;

    @Before
    public void restTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));

    }

    @org.junit.Test
    public void addCustomer() {
        Lobster lobster = new Lobster();
        lobster.setName("test");

        Integer id = restTemplate.postForObject("http://localhost:8080/api/lobsters", lobster, Integer.class);
        lobster.setId(id);

        System.out.println(id);

    }
}
