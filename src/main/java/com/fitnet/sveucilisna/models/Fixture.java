package com.fitnet.sveucilisna.models;

import javax.persistence.*;

/**
 * Created by User on 25.02.2018..
 */
@Entity
@Table( name = "fixture" )
public class Fixture {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "id")
    private Long id;

    @Column( name = "roundNumber" )
    private Integer roundNumber;

    @Column( name = "homeTeamName" )
    private String homeTeamName;

    @Column( name = "awayTeamName" )
    private String awayTeamName;

    @Column( name = "homeTeamGoalsScored" )
    private Integer homeTeamGoalsScored;

    @Column( name = "awayTeamGoalsScored" )
    private Integer awayTeamGoalsScored;

    @Column( name = "homeTeamId" )
    private Long homeTeamId;

    @Column( name = "awayTeamId" )
    private Long awayTeamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeTeamGoalsScored() {
        return homeTeamGoalsScored;
    }

    public void setHomeTeamGoalsScored(Integer homeTeamGoalsScored) {
        this.homeTeamGoalsScored = homeTeamGoalsScored;
    }

    public Integer getAwayTeamGoalsScored() {
        return awayTeamGoalsScored;
    }

    public void setAwayTeamGoalsScored(Integer awayTeamGoalsScored) {
        this.awayTeamGoalsScored = awayTeamGoalsScored;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
