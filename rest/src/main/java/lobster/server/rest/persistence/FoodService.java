package lobster.server.rest.persistence;

import lobster.server.rest.model.Food;
import lobster.server.rest.model.Lobster;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 2:54
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class FoodService {

    public FoodService(){
    }

    public FoodService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    static private final String FOOD_REGION = "food";

    private SessionFactory sessionFactory;

    public List<Food> getAll(){
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Food.class);
        List<Food> aux = crit.list();
        HashMap<Integer, Food> map = new HashMap<Integer, Food>();

        for(Food f : aux){
            if(!map.keySet().contains(f.getId())){
                map.put(f.getId(), f);
            }
        }
        List<Food> total = new ArrayList<Food>(map.values());

        return total;
    }

    @Cacheable(FOOD_REGION)
    @Transactional(readOnly = true)
    public Food getById(Integer id) {
        return (Food) sessionFactory.getCurrentSession().get(Food.class, id);
    }
}
