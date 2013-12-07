package lobster.persistence.jpa.repository;

import lobster.persistence.model.Vitamine;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 01/12/13
 * Time: 18:27
 *
 */
public interface VitaminRepository extends CrudRepository<Vitamine, Long> {
}
