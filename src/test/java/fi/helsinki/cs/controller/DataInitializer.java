package fi.helsinki.cs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fi.helsinki.cs.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataInitializer {

	public static final int USER_COUNT = 3;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Long> users = new ArrayList<Long>();

	public void initData() {
            users.clear();// clear out the previous list of users
            addUser("James Bond");
            addUser("Dirty Harry");
            addUser("Indiana Jones");
            entityManager.flush();
            entityManager.clear();
	}

	public void addUser(String name) {
            User p = new User();
            p.setName(name);
            entityManager.persist(p);
            users.add(p.getId());
	}
	
	public EntityManager getEntityManager() {
            return entityManager;
	}
}
