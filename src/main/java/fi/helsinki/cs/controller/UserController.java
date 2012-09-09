package fi.helsinki.cs.controller;

import java.util.List;

import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/user/get/{userId}")
	public ModelAndView get(@PathVariable("userId") Long userId) {		
		logger.debug("Get user id : "+userId);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("user");
 		User user = userDao.find(userId);		
 		mav.addObject("user", user);
		return mav;
		
	}
	
	@RequestMapping("/user/add/{userId}")
	public ModelAndView add(@ModelAttribute User user) {
		logger.debug("Received postback on person "+user);		
		User user2 = userDao.save(user);
                ModelAndView mav = new ModelAndView();		
 		mav.setViewName("user");
                mav.addObject("user", user2);
		return mav;
		
	}
	
	@RequestMapping("/user/list")
	public ModelAndView list() {
		logger.debug("List users");
		ModelAndView mav = new ModelAndView();
		List<User> users = userDao.getUsers();
		logger.debug("User Listing count = "+users.size());
		mav.addObject("users",users);
		mav.setViewName("list");
		return mav;
		
	}

}
