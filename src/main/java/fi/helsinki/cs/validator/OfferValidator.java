/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.validator;

import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author tesuomin
 */
public class JobValidator implements Validator {
/**
    * This Validator validates just Job instances
    */
    public boolean supports(Class clazz) {
        return Job.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "title", "title.empty");
        ValidationUtils.rejectIfEmpty(e, "description", "description.empty");
    }
}