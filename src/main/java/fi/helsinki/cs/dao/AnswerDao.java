/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Answer;
import fi.helsinki.cs.model.Question;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tesuomin
 */
public class AnswerDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    public Answer find(Long id) {
            return entityManager.find(Answer.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Answer> getAnswers() {
            return entityManager.createQuery("select j from Answer j").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<Answer> getAnswerByQuestion(Question question) {
            Query query = entityManager.createQuery("select j from Answer j where j.question = :question");
            query.setParameter("question", question);
            return query.getResultList();
    }

    @Transactional
    public Answer save(Answer answer) {
            if (answer.getId() == null) {
                    entityManager.persist(answer);
                    return answer;
            } else {
                    return entityManager.merge(answer);
            }
    }
}