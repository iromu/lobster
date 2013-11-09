package lobster.server.rest.controller;

import lobster.server.rest.model.*;
import lobster.server.rest.persistence.FoodService;
import lobster.server.rest.persistence.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/lobster/")
@Controller
@Transactional
public class LobsterApiController {

    @Autowired
    private LobsterService lobsterService;


    @Autowired
    private FoodService foodService;

    @ResponseBody
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Integer addLobster(@Valid @RequestBody Lobster lobster) {
        lobster = lobsterService.create(lobster);
        return lobster.getId();
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Lobster> getLobsters() {
        List<Lobster> lobsters = lobsterService.getAll();

        List<Lobster> response = new ArrayList<Lobster>();
        for (Lobster lobster : lobsters) {
            Lobster simpleLobster = new Lobster();
            simpleLobster.setId(lobster.getId());
            simpleLobster.setName(lobster.getName());
            response.add(simpleLobster);
        }


        return response;
    }

    @ResponseBody
    @RequestMapping(value = "{id}/givefood/{foodId}", method = RequestMethod.POST)
    public void giveFood(@PathVariable("id") Integer id, @PathVariable("foodId") Integer foodId) {

        Lobster lobster = lobsterService.getById(id);
        Status status = lobster.getStatus();
        List<StatusVitamine> statusVitamineList = status.getStatusVitamineList();

        Food food = foodService.getById(id);

        List<Vitamine> foodVitamines = food.getVitamines();

        assert (foodVitamines != null && foodVitamines.size() == 3);
        for (Vitamine foodVitamine : foodVitamines) {

            int i = statusVitamineList.indexOf(foodVitamine);
            if (i != -1) {
                StatusVitamine statusVitamine = statusVitamineList.get(i);
                statusVitamine.setAmount(statusVitamine.getAmount() + 1);
            } else {
                StatusVitamine statusVitamine = new StatusVitamine();
                statusVitamine.setAmount(1);
                statusVitamineList.add(statusVitamine);
            }
        }

        Integer totalCalories = status.getTotalCalories();
        totalCalories = totalCalories == null ? 0 : totalCalories;
        status.setTotalCalories(totalCalories + food.getCalories());

        status.setLastEat(new Date());
        lobsterService.update(lobster);

        assert (statusVitamineList != null && statusVitamineList.size() == 3);
    }
}
