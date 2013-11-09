package lobster.server.rest.controller;

import lobster.server.rest.model.Food;
import lobster.server.rest.model.Lobster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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
public class FoodApiControllerIT {

    private RestTemplate restTemplate;

    @Before
    public void restTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));


    }

    @Test
    @Ignore
    public void getAll() {

        List<Food> list;
        ResponseEntity<List> l;
        Integer id = 1;
        l = restTemplate.getForEntity("http://localhost:8080/api/food/list", List.class, id);

        System.out.println(l.toString());

        Assert.assertEquals(7, l.getBody().size());
    }
}
