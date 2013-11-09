package quartz;

import lobster.server.rest.model.*;
import lobster.server.rest.persistence.ActivityService;
import lobster.server.rest.persistence.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 07:18
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTask {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LobsterService lobsterService;

    public void printCurrentTime() {
        // printing current system time

        List<Lobster> list = lobsterService.getAll();
        for (Lobster lob : list) {
            System.out.println("Lobster ID: " +  lob.getId());
            Status status = lob.getStatus();

            Set<StatusVitamine> updated = new HashSet<StatusVitamine>();
            if (status != null) {
                Set<StatusVitamine> vits = status.getStatusVitamineList();
                for (StatusVitamine sv : vits) {
                    if (sv.getAmount() != null) {
                        int amount = sv.getAmount() - 1;
                        if (amount < 0)
                            amount = 0;

                        StatusVitamine svit = new StatusVitamine();
                        svit.setId(sv.getId());
                        svit.setAmount(amount);

                        svit.setVitamine(sv.getVitamine());

                        updated.add(svit);
                    }

                }
                if (updated != null && updated.size() > 0)
                    status.setStatusVitamineList(updated);

                int happiness = status.getHappiness() - 1;
                if (happiness < 0)
                    status.setHappiness(0);
                else
                    status.setHappiness(happiness);


                if (status.getTotalCalories() != null) {
                    int cals = status.getTotalCalories() - 1;
                    if (cals < 0)
                        status.setTotalCalories(0);
                    else
                        status.setTotalCalories(cals);
                } else {
                    status.setTotalCalories(0);
                }

                lob.setStatus(status);
                lobsterService.update(lob);
            }

        }
    }

}
