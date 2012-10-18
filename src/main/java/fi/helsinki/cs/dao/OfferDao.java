/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tesuomin
 */
@Repository
public class OfferDao {
 
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds single Offer by it's ID
     * 
     * @param id
     * @return found Offer or null if nothing was found
     */
    @Transactional(readOnly=true)
    public Offer find(Long id) {
            return entityManager.find(Offer.class, id);
    }
    
    /**
     * Removes offer from database
     * 
     * @param id ID of the offer to be removed
     * @return True if all went well.
     */
    @Transactional
    public Boolean remove(Long id) {
            entityManager.createNativeQuery("delete from Offer where id="+id).executeUpdate();
            return new Boolean(true);
    }

    /**
     * Tarjoushistoria 
     * 
     * @param user
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getOffersByUser(User user) {
            Query query = entityManager.createQuery("select j from Offer j where j.user = :user");
            query.setParameter("user", user);
            return query.getResultList();
    }
    
    /**
     * All offers made on one Job
     * 
     * @param job Job in question
     * @return List<Offer> of all offers made to this Job
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getOffersByJob(Job job) {
            Query query = entityManager.createQuery("select j from Offer j where j.job = :job and j.job.status = 1");
            query.setParameter("job", job);
            return query.getResultList();
    }
    
    /**
     * Finds all active offers made by single User
     * 
     * @param user User who's offers are searched
     * @return List<Offer> of all active offers by this User
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getActiveOffersByUser(User user) {
            Query query = entityManager.createQuery("select o from Offer o, Job j where o.job = j.id and o.user = :user and j.status = 0");
            query.setParameter("user", user);
            return query.getResultList();
    }

    /**
     * Stores a single Offer into database
     * 
     * @param offer Offer to be stored
     * @return Offer which was stored
     */
    @Transactional
    public Offer save(Offer offer) {
        return entityManager.merge(offer);
    }
    
}
