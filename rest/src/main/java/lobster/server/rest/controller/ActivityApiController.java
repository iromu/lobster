package lobster.server.rest.controller;

import lobster.server.rest.model.Activity;
import lobster.server.rest.model.Food;
import lobster.server.rest.model.Lobster;
import lobster.server.rest.persistence.ActivityService;
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
 * User: spawn
 * Date: 09/11/13
 * Time: 03:04
 * To change this template use File | Settings | File Templates.
 */

@RequestMapping("/activity/")
@Controller
public class ActivityApiController {

    @Autowired
    private ActivityService activityService;


    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Activity> getActivities() {
        return activityService.getAll();
    }



}
