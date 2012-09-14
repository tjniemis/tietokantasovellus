/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author tesuomin
 */
@MappedSuperclass
public class PersistentObject {
    
    @Column(insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp", nullable=true)
    @Generated(value=GenerationTime.INSERT)
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


}
