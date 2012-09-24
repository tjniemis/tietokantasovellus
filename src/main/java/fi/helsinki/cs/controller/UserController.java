package fi.helsinki.cs.controller;

import java.util.List;

import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.User;
import fi.helsinki.cs.validator.UserValidator;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
            binder.setValidator(new UserValidator());
        }
        
	@Autowired
	private UserDao userDao;

        @RequestMapping("/register")
	public ModelAndView reqister() {
		return new ModelAndView("register", "user", new User());
	}
        
        @RequestMapping(value = "/addUser", method = { RequestMethod.PUT, RequestMethod.POST })
        public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
            if (result.hasErrors()) {
                return "register";
            } else {
                userDao.save(user);
                return "login";
            }           
        }

}
