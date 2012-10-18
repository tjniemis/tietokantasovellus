/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.validator;

import fi.helsinki.cs.model.Offer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for validating Offers 
 * 
 * @author tesuomin
 */
public class OfferValidator implements Validator {
    
    /**
    * This Validator validates just Offer instances
    */
    public boolean supports(Class clazz) {
        return Offer.class.equals(clazz);
    }

    /**
     * Validates Offer. Only validation error comes if price is left empty.
     */
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "price", "price.empty");
    }
}