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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OfferController {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferController.class);

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
        }
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private OfferDao offerDao;
        
        @Autowired
	private MailService mailService;
        
        @RequestMapping(value = "/addOffer/{jobId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map addOffer(@RequestBody Offer offer, Principal principal, Model model, @PathVariable Long jobId) {
            System.out.println("addOffer");

            Map results = new HashMap();
            if (principal==null) {
                results.put("root", "no user");
                return results;
            }
            User user = userDao.findByEmail(principal.getName());
            Job job = jobDao.find(jobId);
            offer.setJob(job);
            offer.setUser(user);
            offerDao.save(offer);
            
            results.put("root", "success");
            return results;
        }
        
        @RequestMapping(value = "/acceptOffer/{offerId}", method = RequestMethod.GET) 
        public String acceptOffer(Principal principal, Model model, @PathVariable Long offerId) {
            System.out.println("acceptOffer");
            if (principal==null) {
                return "redirect:../logout";
            }
            User user = userDao.findByEmail(principal.getName());
            Offer offer = offerDao.find(offerId);
            Job job = jobDao.find(offer.getJob().getId());
            job.setWinningOffer(offer);
            job.setStatus(new Integer(1));
            jobDao.save(job);
            mailService.acceptOfferMails(user, job, offerId);
            return "redirect:../jobs";
        }
        
        @RequestMapping(value = "/removeOffer/{offerId}", method = RequestMethod.GET) 
        public String removeOffer(Principal principal, Model model, @PathVariable Long offerId) {
            System.out.println("removeOffer");
            if (principal==null) {
                return "redirect:../logout";
            }
            //User user = userDao.findByEmail(principal.getName());
            offerDao.remove(offerId);
            return "redirect:../jobs";
        }

}
