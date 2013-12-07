package lobster.server.rest.controller;

import lobster.persistence.model.*;

import lobster.server.rest.service.LobsterService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 21:59
 */
@RequestMapping("lobsters")
@Controller
@Transactional
public class LobsterApiController extends AbstractCrudController<Lobster> {

    @Inject
    LobsterService lobsterService;

    @PostConstruct
    public void setupService() {
        crudService = lobsterService;
    }

    @ResponseBody
    @RequestMapping(value = "{id}/givefood/{foodId}", method = RequestMethod.POST)
    public Status giveFood(@PathVariable("id") Long id, @PathVariable("foodId") Long foodId) {
        return lobsterService.giveFood(id, foodId);
    }

    @ResponseBody
    @RequestMapping(value = "{id}/doActivity/{actvtId}", method = RequestMethod.POST)
    public boolean doActivity(@PathVariable("id") Long id, @PathVariable("actvtId") Long actvtId) {
        return lobsterService.doActivity(id, actvtId);
    }

    @ResponseBody
    @RequestMapping(value = "getName/{lobsterId}", method = RequestMethod.GET)
    public String getName(@PathVariable("lobsterId") Long lobsterId) {
        return lobsterService.getName(lobsterId);
    }
}
