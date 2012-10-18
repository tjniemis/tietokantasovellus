/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.validator;

import fi.helsinki.cs.model.Question;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for validating Questions 
 * 
 * @author tesuomin
 */
public class QuestionValidator implements Validator {
    
    /**
    * This Validator validates just Question instances
    */
    public boolean supports(Class clazz) {
        return Question.class.equals(clazz);
    }

    /**
     * Validates Question. Only validation error comes if question is left empty.
     */
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "question", "question.empty");
    }
}