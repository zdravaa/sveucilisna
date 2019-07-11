package com.fitnet.sveucilisna.controllers;

import com.fitnet.sveucilisna.models.UpcomingFixtures;
import com.fitnet.sveucilisna.services.UpcomingFixturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zdrava on 27.1.2019.
 */
@RestController
@RequestMapping("api/v1/upcomingFixtures")
public class UpcomingFixturesController {
    @Autowired
    UpcomingFixturesRepository repo;

    @RequestMapping(method = RequestMethod.POST)
    public UpcomingFixtures addUpcomingFixture(@RequestBody UpcomingFixtures upcomingFixtures) {
        return repo.save(upcomingFixtures);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UpcomingFixtures> getAllUpcomingFixtures()
    {
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UpcomingFixtures getUpcomingFixtures(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public UpcomingFixtures updateUpcomingFictures(@PathVariable Long id, @RequestBody UpcomingFixtures upcomingFixtures) {
        upcomingFixtures.setId(id);
        return repo.save(upcomingFixtures);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUpcomingFixtures(@PathVariable Long id){
        repo.delete(id);
    }
}
