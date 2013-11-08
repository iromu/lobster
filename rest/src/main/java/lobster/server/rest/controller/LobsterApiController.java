package lobster.server.rest.controller;

import lobster.server.rest.model.Lobster;
import lobster.server.rest.persistence.LobsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 08/11/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/api/")
@Controller
public class LobsterApiController {

    @Autowired
    private LobsterService lobsterService;

    @ResponseBody
    @RequestMapping(value = "lobsters", method = RequestMethod.POST)
    public Integer addLobster(@Valid @RequestBody Lobster lobster) {
        lobster = lobsterService.create(lobster);
        return lobster.getId();
    }

    @ResponseBody
    @RequestMapping(value = "lobsters", method = RequestMethod.GET)
    public List<Lobster> getLobsters()
    {
        return lobsterService.getAll();
    }

}
