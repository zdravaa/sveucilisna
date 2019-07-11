package com.fitnet.sveucilisna.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 26.01.2018..
 */
@Entity
@Table(name = "team")
public class Team {

    public interface All {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(All.class)
    private Long id;

    @Column(name = "teamName")
    @JsonView(All.class)
    private String teamName;

    @Column(name = "points")
    @JsonView(All.class)
    private Integer points;

    @Column(name = "goalsScored")
    @JsonView(All.class)
    private Integer goalsScored;

    @Column(name = "goalsConceded")
    @JsonView(All.class)
    private Integer goalsConceded;

    @Column(name = "gamesPlayed")
    @JsonView(All.class)
    private Integer gamesPlayed;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(Integer goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
