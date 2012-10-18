package fi.helsinki.cs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fi.helsinki.cs.model.User;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
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
            return query.setParameter("email", email).getSingleResult();
	}
        
        /**
         * Gets all Users in database. This method is only used during testing, not used in application. 
         * 
         * @return List<User> of all users in database. 
         */
	@SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<User> getUsers() {
		return entityManager.createQuery("select u from User u").getResultList();
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
	
}
