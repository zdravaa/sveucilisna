package com.fitnet.sveucilisna.controllers;

import com.fitnet.sveucilisna.models.User;
import com.fitnet.sveucilisna.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zdrava on 26.1.2019.
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserRepository repo;

    @RequestMapping(method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return repo.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAllUsers()
    {
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUser(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public User updatePlayerUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return repo.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        repo.delete(id);
    }
}
