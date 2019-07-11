package com.fitnet.sveucilisna.services;

import com.fitnet.sveucilisna.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 10.6.2018.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
