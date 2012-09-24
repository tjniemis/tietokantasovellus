/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author tesuomin
 */
@Entity
public class Job extends PersistentObject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String title;
    
    @Column
    private String description;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;
    
    @Column
    private String review;
    
    @Column
    private Integer status;
    
    @Column
    private Integer rating;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="owner_id")
    private User user;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="winning_offer_id")
    private Offer winningOffer;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="job", fetch=FetchType.EAGER)
    private List<Question> questions;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="job")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Offer> offers;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the expires
     */
    public Date getExpires() {
        return expires;
    }

    /**
     * @param expires the expires to set
     */
    public void setExpires(Date expires) {
        this.expires = expires;
    }

    /**
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the winningOffer
     */
    public Offer getWinningOffer() {
        return winningOffer;
    }

    /**
     * @param winningOffer the winningOffer to set
     */
    public void setWinningOffer(Offer winningOffer) {
        this.winningOffer = winningOffer;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @return the offers
     */
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
