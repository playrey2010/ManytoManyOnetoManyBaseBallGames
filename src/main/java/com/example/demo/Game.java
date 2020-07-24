package com.example.demo;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameid;

    private String date;

    @ManyToMany
    @JoinTable(name = "games_team", joinColumns = @JoinColumn(name = "games_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> matchup = new HashSet<>();

    private String teamIds;

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Team> getMatchup() {
        return matchup;
    }

    public void setMatchup(Set<Team> matchup) {
        this.matchup = matchup;
    }

    public String getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(String teamIds) {
        this.teamIds = teamIds;
    }
}
