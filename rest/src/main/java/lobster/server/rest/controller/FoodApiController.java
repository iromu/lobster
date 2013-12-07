package lobster.server.rest.controller;

import com.google.common.collect.Lists;
import lobster.persistence.jpa.repository.FoodRepository;
import lobster.persistence.model.Activity;
import lobster.persistence.model.Food;
import lobster.server.rest.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 3:05
 *
 */
@RequestMapping("food")
@Controller
public class FoodApiController {

    @Inject
    private FoodService foodService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Food> getAll() {
        return foodService.getAll();
    }
}
