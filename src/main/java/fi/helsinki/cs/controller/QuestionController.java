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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {
    
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private QuestionDao questionDao;
        
        @RequestMapping(value = "/addQuestion/{jobId}", method = RequestMethod.POST, produces="application/json") 
        public @ResponseBody Map addQuestion(@RequestBody Question question, Principal principal, @PathVariable Long jobId) {
            System.out.println("addQuestion");

            Map results = new HashMap();
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
        public @ResponseBody Map addAnswer(@RequestBody Question question, @PathVariable Long questionId) {
            System.out.println("addQuestion");

            Map results = new HashMap();
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
        public @ResponseBody Map deleteQuestion(@PathVariable Long questionId) {
            System.out.println("deleteQuestion");
            Map results = new HashMap();
            questionDao.delete(questionId);
            results.put("success", "1");
            return results;
        }

}
