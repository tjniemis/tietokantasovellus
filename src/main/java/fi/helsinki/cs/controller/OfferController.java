package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.OfferDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import fi.helsinki.cs.util.MailService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for handling calls related to offers. 
 * 
 * @author tesuomin
 */
@Controller
public class OfferController {
	
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private OfferDao offerDao;
        
        @Autowired
	private MailService mailService;
        
        /**
         * Adds one Offer object to database. Offer object is constructed from JSON which is received from client.  
         * 
         * @param offer Offer which is constructed from JSON in Request Body
         * @param principal User data
         * @param model Model of the page
         * @param jobId ID of the Job this offer is made
         * @return Response is written as JSON directly to Response Body. Response contains only information if method call was succesful. 
         */
        @RequestMapping(value = "/addOffer/{jobId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map addOffer(@RequestBody Offer offer, Principal principal, Model model, @PathVariable Long jobId) {
            System.out.println("addOffer");
            Map results = new HashMap();
            User user = userDao.findByEmail(principal.getName());
            Job job = jobDao.find(jobId);
            offer.setJob(job);
            offer.setUser(user);
            offerDao.save(offer);
            results.put("root", "success");
            return results;
        }
        
        /**
         * Accepts selected offer. 
         * 
         * @param principal User data
         * @param model Model of the page
         * @param offerId ID of the offer which is selected as winning offer. 
         * @return Return is redirected to "/personalJobs" call handler
         */
        @RequestMapping(value = "/acceptOffer/{offerId}", method = RequestMethod.GET) 
        public String acceptOffer(Principal principal, Model model, @PathVariable Long offerId) {
            System.out.println("acceptOffer");
            User user = userDao.findByEmail(principal.getName());
            Offer offer = offerDao.find(offerId);
            Job job = jobDao.find(offer.getJob().getId());
            job.setWinningOffer(offer);
            job.setStatus(new Integer(1));
            jobDao.save(job);
            mailService.acceptOfferMails(user, job, offerId);
            return "redirect:../personalJobs";
        }
        
        /**
         * Removes single offer from database. 
         * 
         * @param offerId ID of the Offer which is to be removed
         * @return Return is redirected to "/jobs" call
         */
        @RequestMapping(value = "/removeOffer/{offerId}", method = RequestMethod.GET) 
        public String removeOffer(@PathVariable Long offerId) {
            System.out.println("removeOffer");
            offerDao.remove(offerId);
            return "redirect:../jobs";
        }

}
