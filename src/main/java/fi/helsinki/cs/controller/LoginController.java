package fi.helsinki.cs.controller;

import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for login attempts, logout and also access to home page(or start page)
 */
@Controller
public class LoginController {

        @Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
	/**
	 * Forwards call to startPage method. 
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
                return startPage(model, principal);
	}
        
        /**
         * Handler for "/login" call. 
         * 
         * @param model
         * @return Returns login.jsp
         */
        @RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
        
        /**
         * Handler for failed login attempts. 
         * 
         * @param model
         * @return Returns login.jsp
         */
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 		model.addAttribute("error", "true");
		return "login";
 
	}
	
        /**
         * Handler for "/logout" calls.
         * 
         * @param model
         * @return Returns login.jsp
         */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 		return "login";
	}
        
        /**
         * Handles "/start" calls. Since startpage is one of the pages which can be accessed without login, 
         * method checks wheter user has logged in. If user exists, then user data along some other information
         * is retrieved from database and added to page model.
         * 
         * @param model Model of the page
         * @param principal User data
         * @return Returns start.jsp
         */
        @RequestMapping(value="/start", method = RequestMethod.GET)
	public String startPage(Model model, Principal principal) {
                if (principal!=null ) {
                    System.out.println("Principal.name: "+principal.getName());
                    User user = userDao.findByEmail(principal.getName());
                    model.addAttribute("user", user); 
                    model.addAttribute("count", jobDao.getCounts(user));
                    if (user.getRole().equals("ROLE_USER")) {
                        model.addAttribute("count", jobDao.getCounts(user));
                    } else if (user.getRole().equals("ROLE_ADMIN")) {
                        return "redirect:admin";
                    }
                }
                return "start";		
	}

}
