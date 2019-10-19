package com.smallwonders.controller;


import com.smallwonders.auth.TokenHandler;
import com.smallwonders.model.auth.SignupForm;
import com.smallwonders.service.SecurityContextService;
import com.smallwonders.service.UserService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private SecurityContextService securityContextService;

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @RequestMapping(value = {"/api/auth"}, method = RequestMethod.POST)
    public AuthResponse login(@RequestBody(required = false) AuthParams params) throws AuthenticationException {

        if (params != null) {
            final UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
            securityContextService.authenticate(loginToken);
        }
        return securityContextService.currentUser().map(u -> {
            String token = tokenHandler.createTokenForUser(u);
            return new AuthResponse(token);
        }).orElseThrow(RuntimeException::new); // it does not happen.
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @RequestMapping(value = {"/api/auth/create"}, method = RequestMethod.POST)
    public AuthResponse create(@RequestBody(required = false) SignupForm params) throws AuthenticationException {

        if (params != null) {
            this.userService.signup(params);
            final UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
            securityContextService.authenticate(loginToken);
        }
        return securityContextService.currentUser().map(u -> {
            String token = tokenHandler.createTokenForUser(u);
            return new AuthResponse(token);
        }).orElseThrow(RuntimeException::new); // it does not happen.
    }


    @RequestMapping(value = {"/", "/login"})
    public AuthResponse auth() {
        return securityContextService.currentUser().map(u -> {
            final String token = tokenHandler.createTokenForUser(u);
            return new AuthResponse(token);
        }).orElseThrow(RuntimeException::new); // it does not happen.
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.OPTIONS)
    public ResponseEntity authOption() {
        return ResponseEntity.ok().build();
    }


    @Value
    private static final class AuthParams {
        private final String username;
        private final String password;
        private final String provider;
        private final String token;


        UsernamePasswordAuthenticationToken toAuthenticationToken() {
            return new UsernamePasswordAuthenticationToken(username, password);
        }
    }

    @Value
    private static final class AuthResponse {
        private final String token;
    }

}
