package fi.helsinki.cs.dao;

import java.util.List;

import fi.helsinki.cs.model.User;
import java.util.Date;
import org.junit.Assert;
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
            p.setEmail("bundy@email.com");
            p.setPassword("password");
            System.out.println("p.created: "+p.getCreated());
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
        
        @Test
        public void testFindByEmail() {
            User user = userDao.findByEmail("bond@email.com");
            Assert.assertEquals("James Bond", user.getName());
        }
        
        

}
