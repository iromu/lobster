package quartz;

import lobster.persistence.jpa.repository.ActivityRepository;
import lobster.persistence.jpa.repository.LobsterRepository;
import lobster.persistence.model.Lobster;
import lobster.persistence.model.Status;
import lobster.persistence.model.VitamineAmount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 07:18
 *
 */
public class PlayerTask {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private LobsterRepository lobsterRepository;

    public void printCurrentTime() {
        // printing current system time

        Iterable<Lobster> list = lobsterRepository.findAll();
        for (Lobster lob : list) {

            Status status = lob.getStatus();

            if (status != null) {
                Set<VitamineAmount> vits = status.getVitamineAmountList();
                for (VitamineAmount sv : vits) {
                    if (sv.getAmount() != null) {
                        Long amount = sv.getAmount() - 1;
                        if (amount < 0)
                            amount = 0L;

                        sv.setAmount(amount);
                    }
                }

                Long happiness = status.getHappiness() - 1;
                if (happiness < 0)
                    status.setHappiness(0L);
                else
                    status.setHappiness(happiness);


                if (status.getTotalCalories() != null) {
                    Long cals = status.getTotalCalories() - 1;
                    if (cals < 0)
                        status.setTotalCalories(0L);
                    else
                        status.setTotalCalories(cals);
                } else {
                    status.setTotalCalories(0L);
                }

                lob.setStatus(status);
                lobsterRepository.save(lob);
            }

        }
    }

}
