package com.codegym.javamyprojectmodule2.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String stadium;


    @OneToMany(targetEntity = Player.class)
    private List<Player> playerList;

    @ManyToOne
    @JoinColumn(name = "national_id")
    private National national;

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    @ManyToOne
    @JoinColumn(name = "tournaments_id")
    private Tournaments tournaments;

    public Tournaments getTournaments() {
        return tournaments;
    }

    public void setTournaments(Tournaments tournaments) {
        this.tournaments = tournaments;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Club() {
    }

    public Club(String name, String stadium) {
        this.name = name;
        this.stadium = stadium;
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

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

}
