package lobster.server.rest.controller;

import lobster.server.rest.model.Activity;
import lobster.server.rest.model.Status;
import lobster.server.rest.persistence.ActivityService;
import lobster.server.rest.persistence.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ferni
 * Date: 9/11/13
 * Time: 3:50
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/status/")
@Controller
public class StatusApiController {
    @Autowired
    private StatusService statusService;

    @ResponseBody
    @RequestMapping(value = "getStatus/{lobsterId}", method = RequestMethod.GET)
    public Status getActivities(@PathVariable("lobsterId") Integer lobsterId) {
        return statusService.get(lobsterId);
    }
}
