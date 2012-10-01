package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.OfferDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import java.security.Principal;
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
        
        @RequestMapping(value = "/addOffer/{jobId}", method = RequestMethod.POST) 
        public @ResponseBody Offer addOffer(@RequestBody Offer offer, Principal principal, Model model, @PathVariable Long jobId) {
            System.out.println("addOffer");
            if (principal==null) return new Offer();
            User user = userDao.findByEmail(principal.getName());
            Job job = jobDao.find(jobId);
            offer.setJob(job);
            offer.setUser(user);
            offerDao.save(offer);
            return offer;
        }

}
