package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.dao.OfferDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import fi.helsinki.cs.validator.JobValidator;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
            //binder.setValidator(new JobValidator());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateFormat.setLenient(false);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
            //binder.setValidator(new UserValidator());
        }
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private OfferDao offerDao;
        
        @RequestMapping("/createJob")
	public String createJob(Model model, Principal principal) {
            System.out.println("createjob");            
            User user = userDao.findByEmail(principal.getName());
            model.addAttribute("user", user); 
            Job job = new Job();
            job.setUser(user);
            model.addAttribute("job", job);
            return "createjob";
	}
        
        @RequestMapping("/jobs")
        public String listJobs(Model model, Principal principal) {
            System.out.println("jobs");
            List<Job> jobs = jobDao.getActiveJobs();            
            User user = null;
            List<Offer> offers = null;
            if (principal!=null) {
                user = userDao.findByEmail(principal.getName());        
                offers = offerDao.getActiveOffersByUser(user);
                for (Job job : jobs) {
                    Long jobId = job.getId();
                    if (job.getUser().getId().equals(user.getId())) {
                        job.setShow(false);
                    } else {
                        for (Offer offer : offers) {
                            if (offer.getJob().getId().equals(jobId)) {
                                job.setShow(false);
                            }
                        }
                    }
                }
            }
            
            model.addAttribute("user", user); 
            model.addAttribute("jobs", jobs); 
            return "jobs";
	}
        
        @RequestMapping(value = "/addJob", method = { RequestMethod.GET, RequestMethod.POST })
        public String addJob(@ModelAttribute("job") Job job, BindingResult result, Principal principal, Model model) {
            System.out.println("addjob");
            User user = userDao.findByEmail(principal.getName());
            model.addAttribute("user", user);
            job.setUser(user);
            new JobValidator().validate(job, result);
            if (result.hasErrors()) {
                result.getAllErrors();
                return "createjob";
            } else {
                jobDao.save(job);
                return "redirect:jobs";
            }           
        }

}
