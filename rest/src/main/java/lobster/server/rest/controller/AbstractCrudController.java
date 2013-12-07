package lobster.server.rest.controller;

import lobster.server.rest.service.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wantez on 06/12/13.
 */
public abstract class AbstractCrudController<Model> {

    ICrudService crudService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getAll() {
        return crudService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Model get(@PathVariable("id") Long id) {
        return (Model) crudService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long create(@RequestBody Model entity) {
        return crudService.create(entity);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Model entity) {
        crudService.update(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        crudService.deleteById(id);
    }
}
