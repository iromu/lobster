package lobster.server.rest.controller;

import lobster.persistence.jpa.repository.StatusRepository;
import lobster.persistence.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private StatusRepository statusRepository;


    @ResponseBody
    @RequestMapping(value = "getStatus/{lobsterId}", method = RequestMethod.GET)
    public Status getStatus(@PathVariable("lobsterId") Long lobsterId) {
        return statusRepository.findOne(lobsterId);
    }


}
