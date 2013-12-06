package lobster.server.rest.controller;

import lobster.persistence.jpa.repository.*;
import lobster.persistence.model.*;
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
    private LobsterRepository lobsterRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private VitaminRepository vitaminRepository;

    @ResponseBody
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Integer addLobster(@Valid @RequestBody Lobster lobster) {
        lobster.setStatus(new Status());
        // Fill the list of StatusVitamin of Status
        Iterable<Vitamine> vitamines = vitaminRepository.findAll();
        Iterator<Vitamine> it = vitamines.iterator();
        while (it.hasNext()) {
            Vitamine vit = it.next();
            VitamineAmount vitamineAmount = new VitamineAmount();
            vitamineAmount.setVitamine(vit);
            vitamineAmount.setAmount(50);
            lobster.getStatus().getVitamineAmountList().add(vitamineAmount);
        }

        lobster = lobsterRepository.save(lobster);
        return lobster.getId();
    }


    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Lobster> getLobsters() {
        Iterable<Lobster> lobsters = lobsterRepository.findAll();

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
    public Status giveFood(@PathVariable("id") Long id, @PathVariable("foodId") Long foodId) {

        Lobster lobster = lobsterRepository.findOne(id);
        Status status = lobster.getStatus();


        Food food = foodRepository.findOne(foodId);

        updateVitamines(status, food);

        updateCalories(status, food);

        updateFatLevel(status, food);

        status.setLastEat(new Date());
        lobsterRepository.save(lobster);

        return status;
    }

    private void updateVitamines(Status status, Food food) {
        Set<VitamineAmount> vitamineAmountList = status.getVitamineAmountList();
        Set<VitamineAmount> foodVitamines = food.getVitamines();

        for (VitamineAmount foodVitamine : foodVitamines) {
            boolean found = false;
            for (VitamineAmount vitamineAmount : vitamineAmountList) {
                if (vitamineAmount.getVitamine().equals(foodVitamine.getVitamine())) {
                    Integer amount = vitamineAmount.getAmount();

                    int i = amount + foodVitamine.getAmount();
                    vitamineAmount.setAmount(i > 100 ? 100 : i);

                    found = true;
                    continue;
                }
            }
            if (!found) {
                VitamineAmount nstatusVitamineAmount = new VitamineAmount();
                nstatusVitamineAmount.setVitamine(foodVitamine.getVitamine());
                nstatusVitamineAmount.setAmount(foodVitamine.getAmount());
                vitamineAmountList.add(nstatusVitamineAmount);
            }
        }
    }

    private void updateCalories(Status status, Food food) {
        Integer totalCalories = status.getTotalCalories();
        totalCalories = totalCalories == null ? 0 : totalCalories;
        int calories = totalCalories + food.getCalories();
        status.setTotalCalories(calories < 100 ? calories : 100);
    }

    private void updateFatLevel(Status status, Food food) {
        Integer fatLevel = status.getFatLevel();
        fatLevel = fatLevel == null ? 0 : fatLevel;
        int foodFatLevel = food.getFatLevel();
        int newFatLevel = fatLevel + foodFatLevel;
        status.setFatLevel(newFatLevel < 100 ? newFatLevel : 100);
    }

    @ResponseBody
    @RequestMapping(value = "{id}/doActivity/{actvtId}", method = RequestMethod.POST)
    public boolean doActivity(@PathVariable("id") Long id, @PathVariable("actvtId") Long actvtId) {
        Lobster lbs = lobsterRepository.findOne(id);
        Status status = lbs.getStatus();
        Activity activity = activityRepository.findOne(actvtId);

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

        int fat = status.getFatLevel() + activity.getFatLevel();
        if (fat < 0)
            status.setFatLevel(0);
        else if (fat > 100)
            status.setFatLevel(100);
        else
            status.setFatLevel(fat);

        lbs.setStatus(status);
        lobsterRepository.save(lbs);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "getName/{lobsterId}", method = RequestMethod.GET)
    public String getName(@PathVariable("lobsterId") Long lobsterId) {
        Lobster l = lobsterRepository.findOne(lobsterId);

        return l.getName();
    }
}
