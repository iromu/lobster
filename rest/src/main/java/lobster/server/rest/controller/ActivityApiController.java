package lobster.server.rest.controller;

import lobster.persistence.model.Activity;
import lobster.server.rest.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 09/11/13
 * Time: 03:04
 */

@RequestMapping("activity")
@Controller
public class ActivityApiController {

    @Inject
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Activity> getAll() {
        return activityService.getAll();
    }
}
