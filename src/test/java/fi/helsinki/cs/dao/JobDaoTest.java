/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.controller.DataInitializer;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
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
    private DataInitializer dataInitializer;

    @Before
    public void prepareData() {
            dataInitializer.initData();
    }
    
    /**
     * Test of save method, of class JobDao.
     */
    @Test
    public void testSave() {
        System.out.println("save Job");
        List<User> list = userDao.getUsers();
        System.out.println("userlist.size(): "+list.size());
        User user = list.get(0);
        Job job = new Job();
        job.setUser(user);
        job.setTitle("työ 1");
        job.setDescription("työn 1 kuvaus");
        job.setExpires(new Date());
        Job result = jobDao.save(job);
        
        assertEquals(job, result);
        assertEquals(result.getUser().getName(), user.getName());
        
        List<Job> jobs = jobDao.getJobsByUser(user);
        assertNotNull(jobs);
        System.out.println("jobs.size: "+jobs.size());
    }
    
    
}
