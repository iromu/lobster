package lobster.server.rest.persistence;

import lobster.server.rest.model.Activity;
import lobster.server.rest.model.Lobster;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 02:59
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class ActivityService {

    public ActivityService() {
    }

    public ActivityService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    static private final String ACTIVITIES_REGION = "activities";

    private SessionFactory sessionFactory;

    public Activity create(Activity activity) {
        sessionFactory.getCurrentSession().save(activity);
        return activity;
    }

    public List<Activity> getAll(){
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Activity.class);
        return crit.list();
    }

    public Activity getActivity(Integer id){
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Activity.class);
        return (Activity)crit.add(Restrictions.naturalId()
                .set("id", id)).uniqueResult();
    }

}
