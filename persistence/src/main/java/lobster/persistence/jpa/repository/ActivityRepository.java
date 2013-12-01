package lobster.persistence.jpa.repository;

import lobster.persistence.model.Activity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 01/12/13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {
}
