package fi.helsinki.cs.dao;

import fi.helsinki.cs.model.Job;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fi.helsinki.cs.model.User;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
        
        @Autowired
        private JobDao jobDao;
	
        /**
         * Finds User by it's ID
         * 
         * @param id
         * @return Found User, null if User was not found
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public User find(Long id) {
            return entityManager.find(User.class, id);
	}
	
        /**
         * Finds user by email. 
         * 
         * @param email Email parameter
         * @return Found User, null if User was not found
         */
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
        public User findByEmail(String email) {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            List<User> users = query.getResultList();
            if (users.size()>0) return users.get(0);
            else return null;
	}
        
        /**
         * Gets all Users in database. This method is only used during testing and by admin, not used in application. 
         * 
         * @return List<User> of all users in database. 
         */
	@SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<User> getUsers() {
            return entityManager.createQuery("select u from User u where u.status=0").getResultList();
	}
	
        /**
         * Stores user into database
         * 
         * @param user User to be stored
         * @return Stored user
         */
	@Transactional
	public User save(User user) {
            if (user.getId() == null) {
                    entityManager.persist(user);
                    return user;
            } else {
                    return entityManager.merge(user);
            }
	}
        
        /**
         * User shouldn't be deleted from system since some of the user inputted data needs to stay. That includes reviews, questions and Jobs which someone
         * had done for them. That is why User shouldn't be deleted but rather "archived". That means their status is updated as inactive. 
         * 
         * @param userId
         * @return Boolean if all was succesfull
         */
	@Transactional
	public Boolean delete(Long userId) {       
            User user = find(userId);
            user.setStatus(User.INACTIVE_USER);
            save(user);
            //TODO: Active jobs should be removed
            List<Job> jobs = jobDao.getActiveJobsByUser(user);
            for (Job job : jobs) {
                jobDao.delete(job.getId());
            }
            return new Boolean(true);
	}
}
