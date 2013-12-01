package lobster.server.rest.persistence;

import lobster.server.rest.model.Lobster;
import lobster.server.rest.model.Status;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 3:57
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class StatusService {
    public StatusService(){}

    public StatusService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    static private final String STATUS_REGION = "lobsters";

    private SessionFactory sessionFactory;

    public Status get(Integer lobsterID) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Lobster.class);
        Lobster lobster = (Lobster)crit.add(Restrictions.naturalId()
                .set("id", lobsterID)).uniqueResult();
        return lobster.getStatus();
    }
}
