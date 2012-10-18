/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Review;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tesuomin
 */
@Repository
public class ReviewDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
    * Finds Review by it's ID
    * 
    * @param id ID of the Review 
    * @return Review if it was found, otherwise null
    */
    @Transactional(readOnly=true)
    public Review find(Long id) {
        return entityManager.find(Review.class, id);
    }
    
    /**
     * Deletes single review from database
     * 
     * @param id ID of the Review which is to be deleted
     * @return True if all went well
     */
    @Transactional
    public Boolean delete(Long id) {
        entityManager.createNativeQuery("delete from Review where id="+id).executeUpdate();
        return new Boolean(true);
    }
    
    /**
     * Fetch all reviews from specific user
     * 
     * @param user User of whom review is written
     * @return List of reviews about given user
     * 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Review> getReviewsByUser(Long userId) {
        Query query = entityManager.createQuery("select r from Review r where r.user = "+userId);
        return query.getResultList();
    }
    
    /**
     * Fetch average review score by User
     * 
     * @param user User of whom review is written
     * @return List of reviews about given user
     * 
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public Integer getAverageRatingByUser(Long userId) {
        List<Review> reviews = getReviewsByUser(userId);
        int sum = 0, counter = 0;
        for (Review review : reviews) {
            sum += review.getRating();
            counter++;
        }
        if (counter>0) {
            int average = sum / counter;
            return new Integer(average);
        }
        return new Integer(0);
    }

    /**
     * Stores Review to database
     * 
     * @param review Review to be stored
     * @return Review which was stored
     */
    @Transactional
    public Review save(Review review) {
        return entityManager.merge(review);
    }
    
}
