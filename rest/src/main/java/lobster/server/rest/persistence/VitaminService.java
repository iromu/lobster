package lobster.server.rest.persistence;

import lobster.server.rest.model.Vitamine;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Josep
 * Date: 9/11/13
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class VitaminService {
    public VitaminService()
    {}

    public VitaminService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    static private final String VITAMIN_REGION = "vitamin";

    private SessionFactory sessionFactory;

    public List<Vitamine> getAll(){
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Vitamine.class);
        return crit.list();
    }

}
