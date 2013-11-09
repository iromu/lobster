package lobster.server.rest.controller;

import lobster.server.rest.model.Food;
import lobster.server.rest.model.Lobster;
import lobster.server.rest.persistence.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/lobster/")
@Controller
public class LobsterApiController {

    @Autowired
    private LobsterService lobsterService;

    @ResponseBody
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Integer addLobster(@Valid @RequestBody Lobster lobster) {
        lobster = lobsterService.create(lobster);
        return lobster.getId();
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Lobster> getLobsters()
    {
        List<Lobster> lobsters = lobsterService.getAll();

        List<Lobster> response = new ArrayList<Lobster>();
        for (Lobster lobster : lobsters) {
            Lobster simpleLobster = new Lobster();
            simpleLobster.setId(lobster.getId());
            simpleLobster.setName(lobster.getName());
            response.add(simpleLobster);
        }


        return response;
    }

    @ResponseBody
    @RequestMapping(value = "getFood", method = RequestMethod.GET)
    public List<Food> getFood(Integer lobsterID)
    {

        return new ArrayList<Food>();
    }
}
