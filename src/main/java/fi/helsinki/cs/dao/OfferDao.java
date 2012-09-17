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

    @Transactional(readOnly=true)
    public Offer find(Long id) {
            return entityManager.find(Offer.class, id);
    }

    /**
     * Kaikki tarjoukset
     * 
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getOffers() {
            return entityManager.createQuery("select j from Offer j").getResultList();
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
     * Kaikki työn tarjoukset
     * 
     * @param job
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getOffersByJob(Job job) {
            Query query = entityManager.createQuery("select j from Offer j where j.job = :job");
            query.setParameter("job", job);
            return query.getResultList();
    }
    
    /**
     * Aktiiviset tarjoukset
     * 
     * @param user
     * @return 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Offer> getActiveOffersByUser(User user) {
            Query query = entityManager.createQuery("select o from Offer o, Job j where o.job = j.id and o.user = :user and j.status = 0");
            query.setParameter("user", user);
            return query.getResultList();
    }

    @Transactional
    public Offer save(Offer offer) {
            if (offer.getId() == null) {
                    entityManager.persist(offer);
                    return offer;
            } else {
                    return entityManager.merge(offer);
            }
    }
    
}
