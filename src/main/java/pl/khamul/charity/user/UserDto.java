package pl.khamul.charity.user;


import pl.khamul.charity.validator.ValidEmail;
import pl.khamul.charity.validator.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ValidPassword
public class UserDto {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
 /*   @Pattern(regexp = "(^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$)")*/
    private String password;

    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
