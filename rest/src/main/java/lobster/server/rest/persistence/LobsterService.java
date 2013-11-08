package lobster.server.rest.persistence;

import lobster.server.rest.model.Lobster;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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

}
