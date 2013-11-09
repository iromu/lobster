package lobster.server.rest.controller;

import lobster.server.rest.model.Food;
import lobster.server.rest.persistence.FoodService;
import lobster.server.rest.persistence.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    private FoodService foodService;

    @ResponseBody
    @RequestMapping(value = "getFood", method = RequestMethod.GET)
    public List<Food> getFood(Integer lobsterID)
    {
        return foodService.getAll();
    }
}
