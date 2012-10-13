/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Review;
import fi.helsinki.cs.model.User;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tesuomin
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class JobDaoTest {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JobDao jobDao;
    
    @Autowired
    private ReviewDao reviewDao;
    
    /**
     * Test of save method, of class JobDao.
     */
    @Test
    public void testSaveAndGet() {
        System.out.println("save Job");
        List<User> list = userDao.getUsers();
        System.out.println("userlist.size(): "+list.size());
        User user = list.get(0);
        Job job = new Job();
        job.setUser(user);
        job.setTitle("työ 1");
        job.setStatus(0);
        job.setDescription("työn 1 kuvaus");
        job.setExpires(new Date());
        Job result = jobDao.save(job);
        
        assertEquals(job.getTitle(), result.getTitle());
        assertEquals(result.getUser().getName(), user.getName());

    }
    
    @Test
    public void testGetJobsByUser() {
        User user = userDao.findByEmail("homer@email.com");
        List<Job> jobs = jobDao.getJobsByUser(user);
        assertTrue(true);
    }
    
    @Test
    public void testGetActiveJobsByUser() {
        User user = userDao.findByEmail("homer@email.com"); //Homer Simpson
        List<Job> jobs = jobDao.getActiveJobsByUser(user);
        assertTrue(true);
    }
    
    @Test
    public void testGetJobHistoryByUser() {
        User user = userDao.findByEmail("homer@email.com");
        List<Job> jobs = jobDao.getJobHistoryByUser(user);
        assertNotNull(jobs);
    }
    
    @Test
    @Rollback(false)
    public void testSaveAndDeleteReview() {
        User user = userDao.findByEmail("homer@email.com");
        List<Job> jobs = jobDao.getActiveJobsByUser(user);
        assertNotNull(jobs);
        Job job = jobs.get(0);        
        Review review = new Review();
        review.setRating(new Short("5"));
        review.setReview("ihan ok");
        review.setReviewer(user);
        review.setUser(user);
        job.setReview(review);
        job = jobDao.save(job);
        Long review_id = job.getReview().getId();
        Review review2 = reviewDao.find(review_id);
        assertEquals(review.getRating(), review2.getRating());
        job.setReview(null);
        jobDao.save(job);
        reviewDao.delete(review_id);
    }
    
}
