/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

/**
 *
 * @author tesuomin
 */
@Entity
public class Offer extends PersistentObject {
    
    @Column
    private Double price;
    
    @Column
    private String description;
    
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="job_id")
    private Job job;
    
    @PreRemove
    public void preRemove() {
        System.out.println("Offer.preRemove()");
        setUser(null);
        setJob(null);
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }
    
    
    
}
