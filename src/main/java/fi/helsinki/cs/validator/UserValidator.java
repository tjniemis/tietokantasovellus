/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.validator;

import fi.helsinki.cs.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validate class for Users
 * 
 * @author tesuomin
 */
public class UserValidator implements Validator {
    
    /**
    * This Validator validates just User instances
    */
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }
    
    /**
     * Validates User instance. Rejects instance if name, email or password are empty. 
     * Also rejects if password confirmation does not match password. 
     */
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
        ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
        User u = (User) obj;
        if (!u.getPassword().equals(u.getPassword_confirm())) {
            e.rejectValue("password_confirm", "not_same");
        }
    }
}