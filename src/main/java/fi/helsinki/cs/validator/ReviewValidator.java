/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.validator;

import fi.helsinki.cs.model.Review;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for validating Reviews 
 * 
 * @author tesuomin
 */
public class ReviewValidator implements Validator {
    
    /**
    * This Validator validates just Review instances
    */
    public boolean supports(Class clazz) {
        return Review.class.equals(clazz);
    }

    /**
     * Validates Review. Only validation error comes if rating is left empty.
     */
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "rating", "rating.empty");
    }
}