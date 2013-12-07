package lobster.server.rest.service;

import java.util.List;

/**
 * Created by wantez on 06/12/13.
 */
public interface ICrudService<Model> {
    List<Model> getAll();

    Model getById(Long id);

    Long create(Model entity);

    void update(Model entity);

    void deleteById(Long id);
}
