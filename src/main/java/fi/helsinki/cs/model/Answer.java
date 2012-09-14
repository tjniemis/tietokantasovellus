/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author tesuomin
 */
@Entity
public class Answer extends PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String answer;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Question question;

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
         * @return the answer
         */
        public String getAnswer() {
            return answer;
        }

        /**
         * @param answer the answer to set
         */
        public void setAnswer(String answer) {
            this.answer = answer;
        }

        /**
         * @return the question
         */
        public Question getQuestion() {
            return question;
        }

        /**
         * @param question the question to set
         */
        public void setQuestion(Question question) {
            this.question = question;
        }


}
