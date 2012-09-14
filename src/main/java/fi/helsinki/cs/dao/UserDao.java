package fi.helsinki.cs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fi.helsinki.cs.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
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
