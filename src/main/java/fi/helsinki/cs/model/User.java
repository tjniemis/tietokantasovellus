package fi.helsinki.cs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="Person") //In Postgres 'User' is reserved word.... 
public class User implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
        
        @Column
	private String name;
        
        @Column
	private String email;
        
        @Column
	private String password;
        
        @Column(insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp", nullable=true)
        @Generated(value=GenerationTime.INSERT)
        @Temporal(TemporalType.TIMESTAMP)
        private Date created;
        
	public User() {}

	public Long getId() {
            return id;
	}

	public void setId(Long id) {
            this.id = id;
	}

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password the password to set
         */
        public void setPassword(String password) {
            this.password = password;
        }
    
    	@Override
	public String toString() {
            return super.toString() + " name = " + name
				+ " id = " + id;
	}

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
