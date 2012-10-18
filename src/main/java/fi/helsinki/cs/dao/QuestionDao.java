/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Question;
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
public class QuestionDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds question by it's ID
     * 
     * @param id ID of the Question 
     * @return Question if it was found, otherwise null
     */
    @Transactional(readOnly=true)
    public Question find(Long id) {
        return entityManager.find(Question.class, id);
    }
    
    /**
     * Deletes question from database
     * 
     * @param id ID of the Question to be removed
     * @return True if all went well
     */
    @Transactional
    public Boolean delete(Long id) {
        Question question =  entityManager.find(Question.class, id);
        entityManager.remove(question);
        return new Boolean(true);
    }
    
    /**
     * Finds questions by Job
     * 
     * @param job 
     * @return List<Question> of Questions which have been made to this Job
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Question> getQuestionsByJob(Job job) {
            Query query = entityManager.createQuery("select j from Question j where j.job = :job");
            query.setParameter("job", job);
            return query.getResultList();
    }

    /**
     * Stores Question into database
     * 
     * @param question Question to be Stored
     * @return Question which was stored
     */
    @Transactional
    public Question save(Question question) {
        return entityManager.merge(question);
    }
    
}
