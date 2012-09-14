package fi.helsinki.cs.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import fi.helsinki.cs.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserControllerTest {
	
	@Autowired
	private DataInitializer dataInitializer;
	
	@Autowired
	private UserController userController;
		
	@Before
	public void before() {
		dataInitializer.initData();
	}
	
	@Test
	public void userList() {
		ModelAndView mav = userController.list();
		assertEquals("list",mav.getViewName());
		List<User> users = (List<User>) mav.getModelMap().get("users");
		assertNotNull(users);		
	}
	
	@Test
	public void getUser() {
		Long template = dataInitializer.users.get(1);
		ModelAndView mav = userController.get(template);
		assertNotNull(mav);
		assertEquals("user", mav.getViewName());
		Object object = mav.getModel().get("user");
		assertTrue(User.class.isAssignableFrom(object.getClass()));
		User user = (User)object;
		assertEquals(template,user.getId());
	}
	
}
