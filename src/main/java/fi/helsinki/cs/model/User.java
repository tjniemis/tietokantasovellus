package fi.helsinki.cs.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Person") //In Postgres 'User' is reserved word.... 
public class User extends PersistentObject {
        
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        
        public static final Integer ACTIVE_USER = new Integer(0);
        public static final Integer INACTIVE_USER = new Integer(1);
        
        @Column
	private String name;
        
        @Column
	private String email;
        
        @Column
	private String password;
        
        private transient String password_confirm;
        
        private transient Integer average_rating;
        
        @Column
        private String role = ROLE_USER;
        
        @Column
        private Integer status = ACTIVE_USER; //0 = active, 1 = inactive
        
	public User() {}

        public String getStars() {
            String s = "";
            if (average_rating==null) return s;
            else {
                for (int i=0; i<average_rating.intValue(); i++) {
                    s += "&#9733;";
                }
            }
            return s;
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
				+ " id = " + getId();
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

    /**
     * @return the average_rating
     */
    public Integer getAverage_rating() {
        return average_rating;
    }

    /**
     * @param average_rating the average_rating to set
     */
    public void setAverage_rating(Integer average_rating) {
        this.average_rating = average_rating;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
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

}
