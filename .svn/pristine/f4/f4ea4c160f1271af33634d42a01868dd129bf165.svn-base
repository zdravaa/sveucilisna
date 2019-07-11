package com.fitnet.sveucilisna.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fitnet.sveucilisna.models.Player;
import com.fitnet.sveucilisna.models.Team;
import com.fitnet.sveucilisna.services.PlayerRepository;
import com.fitnet.sveucilisna.services.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 26.01.2018..
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

    @Autowired
    TeamRepository repo;

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Team createTeam(@RequestBody Team team){
        team.setGoalsConceded(0);
        team.setGoalsScored(0);
        team.setGamesPlayed(0);
        team.setPoints(0);
        return repo.save(team);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Team.All.class)
    public Iterable<Team> getAllTeams(){
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/players/{teamId}")
    public Iterable<Player> getAllTeamPlayers(@PathVariable Long teamId){
        return playerRepository.findByTeam_Id(teamId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Team getTeam(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        team.setId(id);
        return repo.save(team);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTeam(@PathVariable Long id){
        repo.delete(id);
    }
}
