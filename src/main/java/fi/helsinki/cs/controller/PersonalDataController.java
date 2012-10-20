package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.OfferDao;
import fi.helsinki.cs.dao.ReviewDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for Personalized calls. That means calls where application needs to retrieve 
 * specific user related information
 * 
 * @author tesuomin
 */
@Controller
public class PersonalDataController {
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private OfferDao offerDao;
        
        @Autowired
	private ReviewDao reviewDao;
        
        /**
         * Retrieves active offers made by User. That means offers where Job's expiry date is has not yet been reached and 
         * where no offer has been selected as winner yet. 
         * 
         * @param model Model of the page
         * @param principal User data
         * @return Returns personal.jsp
         */
        @RequestMapping("/personalData")
        public String personalData(Model model, Principal principal) {
            System.out.println("personalData");       
            User user = null;
            user = userDao.findByEmail(principal.getName());            
            List<Offer> offers = offerDao.getActiveOffersByUser(user);
            for (Offer offer : offers) {
                Job job = jobDao.find(offer.getJob().getId());
                offer.setJob(job);
            }
            model.addAttribute("user", user); 
            model.addAttribute("offers", offers);
            model.addAttribute("count", jobDao.getCounts(user));
            return "personal";
	}
        
        /**
         * Returns active Jobs by user. That means Jobs where user is owner and he/she has not yet chosen winning offer. 
         * 
         * @param model Model of the page
         * @param principal User data
         * @return 
         */
        @RequestMapping("/personalJobs")
        public String personalJobs(Model model, Principal principal) {
            System.out.println("personalJobs");       
            User user = null;
            user = userDao.findByEmail(principal.getName());                
            List<Job> jobs = jobDao.getActiveJobsByUser(user);
            for (Job job : jobs) {
                List<Offer> offers = job.getOffers();
                for (Offer offer : offers) {
                    User user2 = offer.getUser();
                    Integer rating = reviewDao.getAverageRatingByUser(user2.getId());
                    user2.setAverage_rating(rating);
                }
            }
            model.addAttribute("user", user); 
            model.addAttribute("jobs", jobs);
            model.addAttribute("count", jobDao.getCounts(user));
            return "personalJobs";
	}
        
        /**
         * Retrieves users job history. That means all jobs where user had the winning offer. 
         * 
         * @param model Model of the page
         * @param principal User data
         * @return Returns jobHistory.jsp
         */
        @RequestMapping("/jobHistory")
        public String jobHistory(Model model, Principal principal) {
            System.out.println("jobHistory");       
            User user = null;
            user = userDao.findByEmail(principal.getName());
            List<Job> jobs = jobDao.getJobHistoryByUser(user);
            model.addAttribute("user", user); 
            model.addAttribute("jobs", jobs);
            model.addAttribute("count", jobDao.getCounts(user));
            return "jobHistory";
	}
        
        /**
         * Retrieves all posted Jobs by user. That means all Jobs where user was Job's owner. 
         * 
         * @param model Model of the page
         * @param principal User data
         * @return Returns personalHistory.jsp
         */
        @RequestMapping("/personalHistory")
        public String personalHistory(Model model, Principal principal) {
            System.out.println("personalHistory");       
            User user = null;
            user = userDao.findByEmail(principal.getName());               
            List<Job> jobs = jobDao.getJobsByUser(user);
            for (Job job : jobs) {
                Offer offer = offerDao.find(job.getWinningOffer().getId()); //User mukana
                job.setWinningOffer(offer);
            }
            model.addAttribute("user", user); 
            model.addAttribute("jobs", jobs);
            model.addAttribute("count", jobDao.getCounts(user));
            return "personalHistory";
	}
        
        /**
         * Retrieves all offers made by user. This includes all offers which have already expired and were not selected
         * as winning offers. 
         * 
         * @param model Model of the page
         * @param principal User data
         * @return Returns offerHistory.jsp
         */
        @RequestMapping("/offerHistory")
        public String offerHistory(Model model, Principal principal) {
            System.out.println("offerHistory");       
            User user = userDao.findByEmail(principal.getName());
            List<Offer> offers = offerDao.getOffersByUser(user);

            model.addAttribute("user", user); 
            model.addAttribute("offers", offers);
            model.addAttribute("count", jobDao.getCounts(user));
            return "offerHistory";
	}

}
