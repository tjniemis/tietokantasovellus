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

    @Transactional(readOnly=true)
    public Question find(Long id) {
            return entityManager.find(Question.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Question> getQuestions() {
            return entityManager.createQuery("select j from Question j").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Question> getQuestionsByJob(Job job) {
            Query query = entityManager.createQuery("select j from Question j where j.job = :job");
            query.setParameter("job", job);
            return query.getResultList();
    }

    @Transactional
    public Question save(Question question) {
            if (question.getId() == null) {
                    entityManager.persist(question);
                    return question;
            } else {
                    return entityManager.merge(question);
            }
    }
    
}
