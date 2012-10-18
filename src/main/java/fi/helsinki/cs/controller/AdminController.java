package fi.helsinki.cs.controller;

import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class AdminController {

        @Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        
        @RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin(ModelMap model, Principal principal) {
                System.out.println("AdminController");
                User user = userDao.findByEmail(principal.getName());        
                List<Job> jobs = jobDao.getJobs();
                model.addAttribute("jobs", jobs);
                model.addAttribute("user", user);
		return "admin";
	}
        
        @RequestMapping(value="/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		return "accessDenied";
	}
        
        @RequestMapping(value = "/deleteJobAdmin/{jobId}", method = RequestMethod.GET) 
        public String deleteJob(@PathVariable Long jobId) {
            System.out.println("deleteJobAdmin");
            jobDao.delete(jobId);
            return "redirect:../admin";
        }

}
