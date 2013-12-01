package lobster.server.rest.controller;

import lobster.persistence.jpa.repository.FoodRepository;
import lobster.persistence.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/food/")
@Controller
public class FoodApiController {

    @Autowired
    private FoodRepository foodRepository;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Food> getFood() {
        return foodRepository.findAll();
    }
}
