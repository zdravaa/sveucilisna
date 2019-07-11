package com.fitnet.sveucilisna.services;

import com.fitnet.sveucilisna.models.UpcomingFixtures;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User on 27.1.2019.
 */
public interface UpcomingFixturesRepository extends CrudRepository<UpcomingFixtures,Long> {
    UpcomingFixtures findById(Long id);
}
