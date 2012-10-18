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
 * Sample controller for going to the home page with a message
 */
@Controller
public class LoginController {

        @Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
                return startPage(model, principal);
	}
        
        @RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
        
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 		return "login";
	}
        
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
