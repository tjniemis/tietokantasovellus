package fi.helsinki.cs.dao;

import java.util.List;

import fi.helsinki.cs.controller.DataInitializer;
import fi.helsinki.cs.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DataInitializer dataInitializer;

	@Before
	public void prepareData() {
		dataInitializer.initData();
	}

	@Test
	public void saveUser() {
		User p = new User();
		p.setName("Al Bundy");
		userDao.save(p);
		Long id = p.getId();
		Assert.assertNotNull(id);
	}

	@Test
	public void getUser() {
		Long template = dataInitializer.users.get(0);
		User p = userDao.find(template);
		Assert.assertNotNull("User not found!", p);
		Assert.assertEquals(template, p.getId());
	}

        @Test
	public void listUsers() {
		List<User> users = userDao.getUsers();
		Assert.assertEquals(DataInitializer.USER_COUNT, users.size());
	}

}
