package lobster.persistence.jpa.repository;

import lobster.persistence.model.Status;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 01/12/13
 * Time: 18:26
 *
 */
public interface StatusRepository extends CrudRepository<Status, Long> {
}
