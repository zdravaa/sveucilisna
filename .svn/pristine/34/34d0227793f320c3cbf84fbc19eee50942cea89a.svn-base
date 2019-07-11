package com.fitnet.sveucilisna.controllers;

import com.fitnet.sveucilisna.models.Player;
import com.fitnet.sveucilisna.services.PlayerRepository;
import com.fitnet.sveucilisna.services.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zdrava on 26.01.2018..
 */
@RestController
@RequestMapping("api/v1/players")
public class PlayerController {

    @Autowired
    PlayerRepository repo;

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Player createPlayer(@RequestBody Player player){
        player.setId(null);
        player.setYellowCards(0);
        player.setRedCards(0);
        player.setGoalsScored(0);
        player.setGamesPlayed(0);
        return repo.save(player);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Player> getAllPlayers(){
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        player.setId(id);
        return repo.save(player);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deletePlayer(@PathVariable Long id){
        repo.delete(id);
    }
}
