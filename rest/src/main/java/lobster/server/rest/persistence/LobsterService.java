package lobster.server.rest.persistence;

import lobster.server.rest.model.Lobster;
import lobster.server.rest.model.Status;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class LobsterService {

    public LobsterService() {
    }

    public LobsterService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    static private final String LOBSTERS_REGION = "lobsters";

    private SessionFactory sessionFactory;

    public Lobster create(Lobster lobster) {
        sessionFactory.getCurrentSession().save(lobster);
        return lobster;
    }

    public List<Lobster> getAll() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Lobster.class);
        return crit.list();
    }

    @Cacheable(LOBSTERS_REGION)
    @Transactional(readOnly = true)
    public Lobster getById(Integer id) {
        return (Lobster) sessionFactory.getCurrentSession().get(Lobster.class, id);
    }

    @CacheEvict(value = LOBSTERS_REGION, key = "#lobster")
    public void update(Lobster lobster) {
        sessionFactory.getCurrentSession().update(lobster);
    }
}
