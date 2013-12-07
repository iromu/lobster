package lobster.server.rest.service;

import lobster.persistence.jpa.repository.*;
import lobster.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by wantez on 06/12/13.
 */
@Service
@Transactional
public class LobsterService implements ICrudService<Lobster> {

    @Inject
    private LobsterRepository lobsterRepository;

    @Inject
    private FoodRepository foodRepository;

    @Inject
    private ActivityRepository activityRepository;

    @Inject
    private VitaminRepository vitaminRepository;

    public List<Lobster> getAll() {
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

    @Override
    public Lobster getById(Long id) {
        return lobsterRepository.findOne(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED, rollbackFor = Throwable.class)
    public Long create(Lobster lobster) {
        lobster.setStatus(new Status());
        // Fill the list of StatusVitamin of Status
        Iterable<Vitamine> vitamines = vitaminRepository.findAll();
        Iterator<Vitamine> it = vitamines.iterator();
        while (it.hasNext()) {
            Vitamine vit = it.next();
            VitamineAmount vitamineAmount = new VitamineAmount();
            vitamineAmount.setVitamine(vit);
            vitamineAmount.setAmount(50L);
            lobster.getStatus().getVitamineAmountList().add(vitamineAmount);
        }

        lobster = lobsterRepository.save(lobster);
        return lobster.getId();
    }

    @Override
    public void update(Lobster entity) {

    }

    @Override
    public void deleteById(Long id) {

    }


    public Status giveFood(Long id, Long foodId) {

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
                    Long amount = vitamineAmount.getAmount();

                    Long i = amount + foodVitamine.getAmount();
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
        Long totalCalories = status.getTotalCalories();
        totalCalories = totalCalories == null ? 0 : totalCalories;
        Long calories = totalCalories + food.getCalories();
        status.setTotalCalories(calories < 100 ? calories : 100L);
    }

    private void updateFatLevel(Status status, Food food) {
        Long fatLevel = status.getFatLevel();
        fatLevel = fatLevel == null ? 0 : fatLevel;
        Long foodFatLevel = food.getFatLevel();
        Long newFatLevel = fatLevel + foodFatLevel;
        status.setFatLevel(newFatLevel < 100 ? newFatLevel : 100L);
    }


    public boolean doActivity(Long id, Long actvtId) {
        Lobster lbs = lobsterRepository.findOne(id);
        Status status = lbs.getStatus();
        Activity activity = activityRepository.findOne(actvtId);

        Long happiness = status.getHappiness() + activity.getHappiness();
        if (happiness < 0)
            status.setHappiness(0L);
        else if (happiness > 100)
            status.setHappiness(100L);
        else
            status.setHappiness(happiness);

        Long cals = status.getTotalCalories() + activity.getCalories();
        if (cals < 0)
            status.setTotalCalories(0L);
        else if (cals > 100)
            status.setTotalCalories(100L);
        else
            status.setTotalCalories(cals);

        Long fat = status.getFatLevel() + activity.getFatLevel();
        if (fat < 0)
            status.setFatLevel(0L);
        else if (fat > 100)
            status.setFatLevel(100L);
        else
            status.setFatLevel(fat);

        lbs.setStatus(status);
        lobsterRepository.save(lbs);
        return true;
    }

    public String getName(Long lobsterId) {
        Lobster l = lobsterRepository.findOne(lobsterId);

        return l.getName();
    }
}
