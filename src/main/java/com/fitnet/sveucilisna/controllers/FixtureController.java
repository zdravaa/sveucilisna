package com.fitnet.sveucilisna.controllers;

import com.fitnet.sveucilisna.models.Fixture;
import com.fitnet.sveucilisna.models.Player;
import com.fitnet.sveucilisna.models.Team;
import com.fitnet.sveucilisna.services.FixtureRepository;
import com.fitnet.sveucilisna.services.PlayerRepository;
import com.fitnet.sveucilisna.services.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 25.02.2018..
 */
@RestController
@RequestMapping("api/v1/fixtures")
public class FixtureController {
    @Autowired
    FixtureRepository repo;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Fixture createFixture(@RequestBody Fixture fixture){
        Team updateHomeTeam = teamRepository.findOne(fixture.getHomeTeamId());
        Team updateAwayTeam = teamRepository.findOne(fixture.getAwayTeamId());
        updateHomeTeam.setGoalsScored(updateHomeTeam.getGoalsScored() + fixture.getHomeTeamGoalsScored());
        updateHomeTeam.setGoalsConceded(updateHomeTeam.getGoalsConceded() + fixture.getAwayTeamGoalsScored());
        updateAwayTeam.setGoalsScored(updateAwayTeam.getGoalsScored() + fixture.getAwayTeamGoalsScored());
        updateAwayTeam.setGoalsConceded(updateAwayTeam.getGoalsConceded() + fixture.getHomeTeamGoalsScored());
        if ( fixture.getHomeTeamGoalsScored() > fixture.getAwayTeamGoalsScored() ) {
            updateHomeTeam.setPoints(updateHomeTeam.getPoints() + 3);
        } else if ( fixture.getHomeTeamGoalsScored() == fixture.getAwayTeamGoalsScored() ) {
            updateHomeTeam.setPoints(updateHomeTeam.getPoints() + 1);
            updateAwayTeam.setPoints(updateAwayTeam.getPoints() + 1);
        }
        else {
            updateAwayTeam.setPoints(updateAwayTeam.getPoints() + 3);
        }
        updateHomeTeam.setGamesPlayed(updateHomeTeam.getGamesPlayed() + 1);
        updateAwayTeam.setGamesPlayed(updateAwayTeam.getGamesPlayed() + 1);
        teamRepository.save(updateHomeTeam);
        teamRepository.save(updateAwayTeam);
        fixture.setAwayTeamName(updateAwayTeam.getTeamName());
        fixture.setHomeTeamName(updateHomeTeam.getTeamName());
        return repo.save(fixture);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Fixture> getAllFixtures(){
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Fixture getFixture(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Fixture updateFixture(@PathVariable Long id, @RequestBody Fixture fixture) {
        fixture.setId(id);
        Team updateHomeTeam = teamRepository.findOne(fixture.getHomeTeamId());
        Team updateAwayTeam = teamRepository.findOne(fixture.getAwayTeamId());
        updateHomeTeam.setGoalsScored(updateHomeTeam.getGoalsScored() + fixture.getHomeTeamGoalsScored());
        updateHomeTeam.setGoalsConceded(updateHomeTeam.getGoalsConceded() + fixture.getAwayTeamGoalsScored());
        updateAwayTeam.setGoalsScored(updateAwayTeam.getGoalsScored() + fixture.getAwayTeamGoalsScored());
        updateAwayTeam.setGoalsConceded(updateAwayTeam.getGoalsConceded() + fixture.getHomeTeamGoalsScored());
        teamRepository.save(updateHomeTeam);
        teamRepository.save(updateAwayTeam);
        fixture.setAwayTeamName(updateAwayTeam.getTeamName());
        fixture.setHomeTeamName(updateHomeTeam.getTeamName());
        return repo.save(fixture);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/player/{id}")
    public void updateFixturePlayer(@PathVariable Long id, @RequestBody Player player){
        Player updatePlayer = playerRepository.findOne(id);
        updatePlayer.setGamesPlayed(updatePlayer.getGamesPlayed() + 1);

        if (player.getGoalsScored() == null ) {
            updatePlayer.setGoalsScored(updatePlayer.getGoalsScored());
        } else {
            updatePlayer.setGoalsScored(updatePlayer.getGoalsScored() + player.getGoalsScored());
            System.out.println("Broj novih crvenih kartona igraca je:" + updatePlayer.getGoalsScored());
        }

        if (player.getRedCards() == null ) {
            updatePlayer.setRedCards(updatePlayer.getRedCards());
        } else {
            updatePlayer.setRedCards(updatePlayer.getRedCards() + player.getRedCards());
            System.out.println("Broj novih crvenih kartona igraca je:" + updatePlayer.getRedCards());
        }

        if (player.getYellowCards() == null ) {
            updatePlayer.setYellowCards(updatePlayer.getYellowCards());
        } else {
            updatePlayer.setYellowCards(updatePlayer.getYellowCards() + player.getYellowCards());
            System.out.println("Broj novih zutih kartona igraca je:" + updatePlayer.getYellowCards());
        }
        playerRepository.save(updatePlayer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteFixture(@PathVariable Long id){
        repo.delete(id);
    }
}
