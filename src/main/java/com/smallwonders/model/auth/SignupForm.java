package com.smallwonders.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupForm {

    @NotNull
    @Size(min = 1, max = 255)
    @Email
    private String email;

    private String username;

    @NotNull
    @Size(min = 1, max = 100, message = "{nameSizeError}")
    private String firstName;
    @NotNull
    @Size(min = 1, max = 30)
    private String password;
    private String accessToken;
    private String roleName;

    private Double latitude;
    private Double longitude;
    private String base64;

    private String organizationType;
    private String name;
    private Integer maxDailyCapacity;
    private Double pricePerUnit;
    private Double totalPrice;


    @Override
    public String toString() {
        return "SignupForm [email=" + email + ", firstName=" + firstName + ", password="
                + password + "]";
    }


    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
