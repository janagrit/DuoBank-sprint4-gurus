package pojos;

import org.apache.commons.codec.digest.DigestUtils;

public class UserLoginPOJO {



    private String email;
    private String password;

    public UserLoginPOJO(){}


    public UserLoginPOJO(String email, String password) {

        this.email = email;
        this.password = password;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserLoginPOJO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
