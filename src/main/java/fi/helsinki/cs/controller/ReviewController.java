package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.ReviewDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Review;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
        }
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private ReviewDao reviewDao;

        
        @RequestMapping(value = "/reviewUser/{userId}/{jobId}", method = { RequestMethod.GET, RequestMethod.POST })
        public @ResponseBody String reviewUser(@RequestBody Review review, @PathVariable Long userId, @PathVariable Long jobId, Principal principal) {
            Map results = new HashMap();
            if (principal==null) {
                results.put("exception", "No user logged in");
                results.put("success", "0");
                return "";
            }
            User reviewer = userDao.findByEmail(principal.getName());
            User user = userDao.find(userId);
            Job job = jobDao.find(jobId);
            review.setUser(user);
            review.setReviewer(reviewer);
            job.setReview(review);
            jobDao.save(job);
            return "";
        }
        
        @RequestMapping(value = "/getReviews/{userId}", method = { RequestMethod.GET, RequestMethod.POST })
        public @ResponseBody Map getReviews(@PathVariable Long userId) {
            //User user = userDao.find(userId);
            //System.out.println("user: "+user);
            Map map = new HashMap();
            List<Review> reviews = reviewDao.getReviewsByUser(userId);
            map.put("reviews", reviews);
            return map;
        }

}
