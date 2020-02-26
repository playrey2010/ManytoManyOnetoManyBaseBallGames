package com.example.demo;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameid;

    private String date;

    @ManyToMany
    private Set<Team> matchup;

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
}
