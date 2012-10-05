package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;
        
        @Autowired
        private UserDao userDao;
	
        @Transactional(readOnly=true)
	public Job find(Long id) {
            return entityManager.find(Job.class, id);
	}
        
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobs() {
            return entityManager.createQuery("select j from Job j").getResultList();
	}
        
        /*
         * Aktiiviset työt
         * 
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getActiveJobs() {
            return entityManager.createQuery("select j from Job j where j.status = 0").getResultList();
	}
        
        /**
         * Ilmoitushistoria
         * 
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobsByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 1");
            query.setParameter("user", user);
            return query.getResultList();
	}
        
        /**
         * Aktiiviset työt 
         * 
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getActiveJobsByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 0");
            query.setParameter("user", user);
            return query.getResultList();
	}
        
        /**
         * Työhistoria (kaikki voitetut työt)
         * 
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobHistoryByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j, Offer o where j.winningOffer=o.id and o.user=:user and j.status = 1");
            query.setParameter("user", user);
            return query.getResultList();
	}
	
        @SuppressWarnings("unchecked")
	@Transactional
	public Job save(Job job) {
            return entityManager.merge(job);
	}
	
}
