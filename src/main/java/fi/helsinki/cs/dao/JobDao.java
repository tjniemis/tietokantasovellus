package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JobDao {

	@PersistenceContext
	private EntityManager entityManager;
	
        /**
         * Finds single Job by id
         * 
         * @param id
         * @return Job
         */
        @Transactional(readOnly=true)
	public Job find(Long id) {
            return entityManager.find(Job.class, id);
	}
        
        /**
         * Finds all jobs (to be used by admin only!)
         * 
         * @return List of all active jobs
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobs() {
            Query query = entityManager.createQuery("select j from Job j order by j.created desc");
            return query.getResultList();
	}
        
        /**
         * Finds all active jobs (where staus=0)
         * 
         * @return List of all active jobs
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getActiveJobs() {
            Query query = entityManager.createQuery("select j from Job j where j.status = 0 and j.expires > :currentDate order by j.created desc");
            query.setParameter("currentDate", new Date());
            return query.getResultList();
	}
        
        /**
         * Finds single users non-active jobs (old expired jobs)
         * 
         * @return List of jobs
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobsByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 1 order by j.created desc");
            query.setParameter("user", user);
            return query.getResultList();
	}
        
        /**
         * Finds single users active jobs (=jobs where offers can be made)
         * 
         * @return List of jobs
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getActiveJobsByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j where j.user = :user and j.status = 0 order by j.created desc");
            query.setParameter("user", user);
            return query.getResultList();
	}
        
         /**
         * Gets users work history. This means all jobs where user's offer was winning offer. 
         * 
         * @return List of jobs
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<Job> getJobHistoryByUser(User user) {
            Query query = entityManager.createQuery("select j from Job j, Offer o where j.winningOffer=o.id and o.user=:user and j.status = 1 order by j.created desc");
            query.setParameter("user", user);
            return query.getResultList();
	}
	
        /**
         * Saves job entity to database
         * 
         * @param job
         * @return Job that was saved
         */
        @SuppressWarnings("unchecked")
	@Transactional
	public Job save(Job job) {
            return entityManager.merge(job);
	}
        
        /**
         * Deletes Job from database. Method also deletes all orphan offers and questions related to the Job.
         * 
         * @param job
         * @return Job that was saved
         */
        @SuppressWarnings("unchecked")
	@Transactional
	public Boolean delete(Long id) {
            //Job job = find(id);
            entityManager.createNativeQuery("delete from Offer where job_id="+id).executeUpdate();
            entityManager.createNativeQuery("delete from Question where job_id="+id).executeUpdate();
            entityManager.createNativeQuery("delete from Job where id="+id).executeUpdate();
            return new Boolean(true);
	}
        
        /**
         * Finds number of jobs available, number of active jobs and number of active offers. 
         * 
         * @param user User whos jobs method counts
         * @return Map object containing data
         */
        public Map getCounts(User user) {
            Map map = new HashMap();
            //First available jobs
            Query query = entityManager.createNativeQuery("select count(*) from Job j where j.status = 0 and j.expires > :currentDate and j.id not in (select j2.id from Offer o, Job j2 where o.job_id = j2.id and o.user_id = :user and j2.status = 0)");
            query.setParameter("currentDate", new Date());
            query.setParameter("user", user);
            BigInteger integer = (BigInteger)query.getSingleResult();
            System.out.println("Available: "+integer);
            map.put("available", integer);
            //Second own job announcements
            Query query2 = entityManager.createNativeQuery("select count(*) from Job j where j.status = 0 and j.owner_id = :user");
            query2.setParameter("user", user);
            BigInteger integer2 = (BigInteger)query2.getSingleResult();
            System.out.println("Jobs: "+integer2);
            map.put("jobs", integer2);
            //third own offers
            Query query3 = entityManager.createNativeQuery("select count(*) from Offer o, Job j where o.job_id = j.id and o.user_id = :user and j.status = 0");
            query3.setParameter("user", user);
            BigInteger integer3 = (BigInteger)query3.getSingleResult();
            System.out.println("Offers: "+integer3);
            map.put("offers", integer3);
            return map;
        }
	
}
