/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Review;
import fi.helsinki.cs.model.User;
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
    
    @Autowired
    private UserDao userDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public Review find(Long id) {
        return entityManager.find(Review.class, id);
    }
    
    @Transactional
    public Boolean delete(Long id) {
        entityManager.createNativeQuery("delete from Review where id="+id).executeUpdate();
        return new Boolean(true);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Review> getReviews() {
            return entityManager.createQuery("select r from Review r").getResultList();
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

    @Transactional
    public Review save(Review review) {
        return entityManager.merge(review);
    }
    
}
