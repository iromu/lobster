package lobster.server.rest.controller;

import org.junit.Before;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by wantez on 07/12/13.
 */
public abstract class AbstractControllerTest {
    protected static final String HTTP_API = "http://localhost:8080/api/";
    RestTemplate restTemplate;

    @Before
    public void restTemplate() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(new MappingJacksonHttpMessageConverter()));
    }

}
