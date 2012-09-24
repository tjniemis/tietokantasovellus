package fi.helsinki.cs.controller;


import fi.helsinki.cs.dao.AnswerDao;
import fi.helsinki.cs.dao.JobDao;
import fi.helsinki.cs.dao.UserDao;
import fi.helsinki.cs.model.Answer;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Question;
import fi.helsinki.cs.model.User;
import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

        @InitBinder
        protected void initBinder(WebDataBinder binder) {
            //binder.setValidator(new UserValidator());
        }
        
	@Autowired
	private UserDao userDao;
        
        @Autowired
	private JobDao jobDao;
        
        @Autowired
	private AnswerDao answerDao;
        
        @RequestMapping("/createJob")
	public String createJob(Model model, Principal principal) {
            model.addAttribute("job", new Job());
            User user = userDao.findByEmail(principal.getName());
            model.addAttribute("user", user.getName()); 
            return "createjob";
	}
        
        @RequestMapping("/jobs")
        public String listJobs(Model model, Principal principal) {
            List<Job> jobs = jobDao.getActiveJobs();
            User user = userDao.findByEmail(principal.getName());
            for (Job job : jobs) {
                List<Question> questions = job.getQuestions();
                for (Question question : questions) {
                    Answer answer = answerDao.getAnswerByQuestion(question);
                    question.setAnswer(answer);
                }
            }
            model.addAttribute("user", user); 
            model.addAttribute("jobs", jobs); 
            return "jobs";
	}

}
