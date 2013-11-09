package lobster.server.rest.controller;

import lobster.server.rest.model.*;
import lobster.server.rest.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    @Autowired
    private ActivityService activityService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private VitaminService vitaminService;

    @ResponseBody
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Integer addLobster(@Valid @RequestBody Lobster lobster) {
        lobster.setStatus(new Status());
        // Fill the list of StatusVitamin of Status
        List<Vitamine> vitamines = vitaminService.getAll();
        Iterator<Vitamine> it = vitamines.iterator();
        while (it.hasNext())
        {
            Vitamine vit = it.next();
            StatusVitamine statusVitamine = new StatusVitamine();
            statusVitamine.setVitamine(vit);
            statusVitamine.setAmount(50);
            lobster.getStatus().getStatusVitamineList().add(statusVitamine);
        }

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
    public Status giveFood(@PathVariable("id") Integer id, @PathVariable("foodId") Integer foodId) {

        Lobster lobster = lobsterService.getById(id);
        Status status = lobster.getStatus();


        Food food = foodService.getById(foodId);

        updateVitamines(status, food);

        updateCalories(status, food);

        status.setLastEat(new Date());
        lobsterService.update(lobster);

        return status;
    }

    private void updateVitamines(Status status, Food food) {
        Set<StatusVitamine> statusVitamineList = status.getStatusVitamineList();
        List<Vitamine> foodVitamines = food.getVitamines();

        assert (foodVitamines != null && foodVitamines.size() == 3);
        for (Vitamine foodVitamine : foodVitamines) {
            boolean found = false;
            for (StatusVitamine statusVitamine : statusVitamineList) {
                if (statusVitamine.getVitamine().equals(foodVitamine)) {
                    Integer amount = statusVitamine.getAmount();
                    if (amount < 100)
                        statusVitamine.setAmount(amount + 1);
                    found=true;
                    continue;
                }
            }
            if (!found) {
                StatusVitamine nstatusVitamine = new StatusVitamine();
                nstatusVitamine.setVitamine(foodVitamine);
                nstatusVitamine.setAmount(1);
                statusVitamineList.add(nstatusVitamine);
            }
        }
    }

    private void updateCalories(Status status, Food food) {
        Integer totalCalories = status.getTotalCalories();
        totalCalories = totalCalories == null ? 0 : totalCalories;
        int calories = totalCalories + food.getCalories();
        status.setTotalCalories(calories < 100 ? calories : 100);
    }

    @ResponseBody
    @RequestMapping(value = "{id}/doActivity/{actvtId}", method = RequestMethod.POST)
    public void doActivity(@PathVariable("id") Integer id, @PathVariable("actvtId") Integer actvtId) {
        Lobster lbs = lobsterService.getById(id);
        Status status = lbs.getStatus();
        Activity activity = activityService.getActivity(actvtId);

        System.out.println("Status happ ---->" + status.getHappiness());
        System.out.println("Activity happ ---->" + activity.getHappiness());
        int happiness = status.getHappiness() + activity.getHappiness();
        if (happiness < 0)
            status.setHappiness(0);
        else if (happiness > 100)
            status.setHappiness(100);
        else
            status.setHappiness(happiness);

        int cals = status.getTotalCalories() + activity.getCalories();
        if (cals < 0)
            status.setTotalCalories(0);
        else if (cals > 100)
            status.setTotalCalories(100);
        else
            status.setTotalCalories(cals);

        lbs.setStatus(status);
        lobsterService.update(lbs);
    }
}
