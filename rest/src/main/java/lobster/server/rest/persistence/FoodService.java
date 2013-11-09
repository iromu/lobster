package lobster.server.rest.persistence;

import lobster.server.rest.model.Food;
import lobster.server.rest.model.Lobster;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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
        return crit.list();
    }
}
