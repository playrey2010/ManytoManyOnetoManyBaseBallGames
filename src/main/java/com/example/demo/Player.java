package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playerid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;

    public long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
