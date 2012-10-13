/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReviewDaoTest {
   

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ReviewDao reviewDao;

    /**
     * Test of getReviewsByUser method, of class ReviewDao.
     */
    @Test
    public void testGetReviewsByUser() {
        System.out.println("getReviewsByUser");
        List result = reviewDao.getReviewsByUser(5L);
        assertTrue(result.size()>0);
    }

   
}
