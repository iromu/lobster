package lobster.server.rest.controller;

import lobster.server.rest.model.Activity;
import lobster.server.rest.persistence.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private ActivityService activityService;

    @ResponseBody
    @RequestMapping(value = "getStatus", method = RequestMethod.GET)
    public List<Activity> getActivities(Integer lobsterID) {
        return activityService.getAll();
    }

}
