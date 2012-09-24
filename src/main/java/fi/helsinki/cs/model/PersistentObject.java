/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tesuomin
 */
@MappedSuperclass
public class PersistentObject {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        if (created==null) 
            created = new Date();
    }

}
