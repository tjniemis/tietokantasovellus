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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {

	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private ReviewDao reviewDao;

        /**
         * Adds single review about a user. 
         * 
         * @param review Review object which is constructed straight from JSON in Request Body
         * @param userId ID of the user this review is about
         * @param jobId ID of the job this review is about
         * @param principal User data
         * @return Returns empty JSON since feedback back to client is not really needed
         */
        @RequestMapping(value = "/reviewUser/{userId}/{jobId}", method = { RequestMethod.GET, RequestMethod.POST })
        public @ResponseBody String reviewUser(@RequestBody Review review, @PathVariable Long userId, @PathVariable Long jobId, Principal principal) {
            User reviewer = userDao.findByEmail(principal.getName());
            User user = userDao.find(userId);
            Job job = jobDao.find(jobId);
            review.setUser(user);
            review.setReviewer(reviewer);
            job.setReview(review);
            jobDao.save(job);
            return "";
        }
        
        /**
         * Retrieves all reviews of one user.
         * 
         * @param userId ID of the user who's reviews are retrieved
         * @return Returns user's reviews as JSON
         */
        @RequestMapping(value = "/getReviews/{userId}", method = { RequestMethod.GET, RequestMethod.POST })
        public @ResponseBody Map getReviews(@PathVariable Long userId) {
            Map map = new HashMap();
            List<Review> reviews = reviewDao.getReviewsByUser(userId);
            map.put("reviews", reviews);
            User user = userDao.find(userId);
            map.put("user", user);
            return map;
        }

}
