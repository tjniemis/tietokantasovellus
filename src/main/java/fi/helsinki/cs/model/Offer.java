/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import javax.persistence.Column;

/**
 *
 * @author tesuomin
 */
public class Offer {
    
    @Column
    private Double price;
    
    @Column
    private String description;
    
}
