package fi.helsinki.cs.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Person") //In Postgres 'User' is reserved word.... 
public class User extends PersistentObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
        
        @Column
	private String name;
        
        @Column
	private String email;
        
        @Column
	private String password;
        
        private String password_confirm;
        
        @Column
        private String role = "ROLE_USER";
        
	public User() {}

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
				+ " id = " + getId();
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
     * @return the password_confirm
     */
    public String getPassword_confirm() {
        return password_confirm;
    }

    /**
     * @param password_confirm the password_confirm to set
     */
    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

}
