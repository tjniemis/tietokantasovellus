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

	@Test
	public void testSaveAndGetUser() {
            User p = new User();
            p.setName("Al Bundy");
            userDao.save(p);
            Long id = p.getId();
            Assert.assertNotNull(id);
            
            User p2 = userDao.find(id);
            Assert.assertEquals(id, p2.getId());
	}

        @Test
	public void testListUsers() {
            List<User> users = userDao.getUsers();
            Assert.assertTrue(users.size()>0);
	}
        
        

}
