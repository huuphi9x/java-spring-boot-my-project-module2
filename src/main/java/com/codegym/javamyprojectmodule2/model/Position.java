package com.codegym.javamyprojectmodule2.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Player.class)
    private List<Player> players;

    public Position() {
    }

    public Position(String name) {
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

    public List<Player> getPlayerList() {
        return players;
    }

    public void setPlayerList(List<Player> playerList) {
        this.players = players;
    }
}
