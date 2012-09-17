/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
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
public class OfferDaoTest {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JobDao jobDao;
    
    @Autowired
    private OfferDao offerDao;

    /**
     * Test of getOffersByUser method, of class OfferDao.
     */
    @Test
    public void testGetOffersByUser() {
        User user = userDao.find(new Long(1));
        List<Offer> offers = offerDao.getOffersByUser(user);
        assertNotNull(offers);
    }

    /**
     * Test of getOffersByJob method, of class OfferDao.
     */
    @Test
    public void testGetOffersByJob() {
        Job job = jobDao.find(new Long(1));
        List<Offer> offers = offerDao.getOffersByJob(job);
        assertNotNull(offers);
    }

    /**
     * Test of getActiveOffersByUser method, of class OfferDao.
     */
    @Test
    public void testGetActiveOffersByUser() {
        User user = userDao.find(new Long(1));
        List<Offer> offers = offerDao.getActiveOffersByUser(user);
        assertNotNull(offers);
    }

    /**
     * Test of save and find method, of class OfferDao.
     */
    @Test
    public void testSaveAndFind() {
        Offer offer = new Offer();
        offer.setDescription("Testi kuvaus 1");
        offer.setPrice(new Double(100));
        Job job = jobDao.find(new Long(1));
        offer.setJob(job);
        User user = userDao.find(new Long(1)); //Macgyver
        offer.setUser(user);
        Offer result = offerDao.save(offer);
        assertEquals(offer.getDescription(), result.getDescription());
    }
    
}
