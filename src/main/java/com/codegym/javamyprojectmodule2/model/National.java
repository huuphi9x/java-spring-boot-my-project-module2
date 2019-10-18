package com.codegym.javamyprojectmodule2.model;


import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "national")
public class National {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Tournaments.class)
    private List<Tournaments> tournamentsList;

    @OneToMany(targetEntity = Player.class)
    private List<Player> playerList;

    public National() {
    }

    public National(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tournaments> getTournamentsList() {
        return tournamentsList;
    }

    public void setTournamentsList(List<Tournaments> tournamentsList) {
        this.tournamentsList = tournamentsList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
