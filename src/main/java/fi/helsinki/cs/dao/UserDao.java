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
	
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
        @SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
        public User findByEmail(String email) {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            return query.setParameter("email", email).getSingleResult();
	}
        
	@SuppressWarnings("unchecked")
        @Transactional(readOnly=true)
	public List<User> getUsers() {
		return entityManager.createQuery("select u from User u").getResultList();
	}
	
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
