package lobster.server.rest.controller;

import lobster.persistence.jpa.repository.ActivityRepository;
import lobster.persistence.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Date: 09/11/13
 * Time: 03:04
 * To change this template use File | Settings | File Templates.
 */

@RequestMapping("/activity/")
@Controller
public class ActivityApiController {

    @Autowired
    private ActivityRepository activityRepository;


    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Activity> getActivities() {
        return activityRepository.findAll();
    }


}
