package lobster.server.rest.service;

import com.google.common.collect.Lists;
import lobster.persistence.jpa.repository.ActivityRepository;
import lobster.persistence.jpa.repository.FoodRepository;
import lobster.persistence.model.Food;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by wantez on 07/12/13.
 */
@Service
@Transactional
public class FoodService {

    @Inject
    private FoodRepository foodRepository;

    public List<Food> getAll() {
        return Lists.newArrayList(foodRepository.findAll());
    }
}
