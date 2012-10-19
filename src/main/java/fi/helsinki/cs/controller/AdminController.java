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
 * Controller for various administrator functionalities. Also contains methods for generic errorhandling. 
 * 
 */
@Controller
public class AdminController {

        @Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        
        /**
         * Controller method for admin call. This method retrieves all possible entitities which administrator can
         * maintain and returns call to admin.jsp page.
         * 
         * @param model Model of the page
         * @param principal User data
         * @return admin.jsp
         */
        @RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin(ModelMap model, Principal principal) {
                System.out.println("AdminController");
                User user = userDao.findByEmail(principal.getName());        
                List<Job> jobs = jobDao.getJobs();
                model.addAttribute("jobs", jobs);
                model.addAttribute("user", user);
		return "admin";
	}
        
        /**
         * Method for handling unauthorized access attempts. When logged user tries to access page which he/she has no 
         * access rights, call is forwarded to this method and accessDenied page is displayed instead. 
         * 
         * @param model Model of the page
         * @return accessDenied.jsp 
         */
        @RequestMapping(value="/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		return "accessDenied";
	}
        
        /**
         * Deletes one job. 
         * 
         * @param jobId ID of the job which is to be deleted
         * @return Redirection to admin-call in this controller
         */
        @RequestMapping(value = "/deleteJobAdmin/{jobId}", method = RequestMethod.GET) 
        public String deleteJob(@PathVariable Long jobId) {
            System.out.println("deleteJobAdmin");
            jobDao.delete(jobId);
            return "redirect:../admin";
        }

}
