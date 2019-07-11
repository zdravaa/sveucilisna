package com.fitnet.sveucilisna.controllers;

import com.fitnet.sveucilisna.models.User;
import com.fitnet.sveucilisna.services.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by User on 10.6.2018.
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final User login)
            throws ServletException {
        User user = userRepository.findByUserName(login.getUserName());
        if (user != null && user.getPassword().equals(login.getPassword())) {
            return new LoginResponse(Jwts.builder().setSubject(login.getUserName())
                    .claim("roles", Arrays.asList("user", "admin")).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        }
        else throw new ServletException("Invalid login");

    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}