package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.QuestionDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Question;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
        }
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private QuestionDao questionDao;
        
        @RequestMapping(value = "/addQuestion/{jobId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map addQuestion(@RequestBody Question question, Principal principal, Model model, @PathVariable Long jobId) {
            System.out.println("addQuestion");

            Map results = new HashMap();
            if (principal==null) {
                results.put("exception", "No user logged in");
                results.put("success", "0");
                return results;
            }
            User user = userDao.findByEmail(principal.getName());
            Job job = jobDao.find(jobId);
            question.setJob(job);
            question.setUser(user);
            questionDao.save(question);
            
            results.put("success", "1");
            results.put("question", question.getQuestion());
            return results;
        }
        
        @RequestMapping(value = "/addAnswer/{questionId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map addAnswer(@RequestBody Question question, Principal principal, Model model, @PathVariable Long questionId) {
            System.out.println("addQuestion");

            Map results = new HashMap();
            if (principal==null) {
                results.put("exception", "No user logged in");
                results.put("success", "0");
                return results;
            }
            String answer = question.getAnswer();
            Question question2 = questionDao.find(questionId);
            question2.setAnswer(answer);
            
            System.out.println("question2.id: "+question2.getId());
            
            questionDao.save(question2);
            
            results.put("success", "1");
            results.put("answer", question.getAnswer());
            return results;
        }
        
        @RequestMapping(value = "/deleteQuestion/{questionId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map deleteQuestion(@RequestBody Question question, Principal principal, Model model, @PathVariable Long questionId) {
            System.out.println("deleteQuestion");

            Map results = new HashMap();
            if (principal==null) {
                results.put("root", "no user");
                return results;
            }
            User user = userDao.findByEmail(principal.getName());
            questionDao.delete(questionId);
            results.put("root", "success");
            return results;
        }

}
