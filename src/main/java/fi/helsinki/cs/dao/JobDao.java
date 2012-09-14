package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;
	
        @Transactional(readOnly=true)
	public Job find(Long id) {
		return entityManager.find(Job.class, id);
	}
        
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobs() {
		return entityManager.createQuery("select j from Jobs j").getResultList();
	}
        
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobsByUser(User user) {
                Query query = entityManager.createQuery("select j from Job j where j.user = :user");
                query.setParameter("user", user);
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getActiveJobsByUser(User user) {
                Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 1");
                query.setParameter("user", user);
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobHistoryByUser(User user) {
                Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 2");
                query.setParameter("user", user);
		return query.getResultList();
	}
	
	@Transactional
	public Job save(Job job) {
		if (job.getId() == null) {
			entityManager.persist(job);
			return job;
		} else {
			return entityManager.merge(job);
		}
	}
	
}