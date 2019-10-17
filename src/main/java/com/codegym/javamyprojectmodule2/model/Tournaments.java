package com.codegym.javamyprojectmodule2.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournaments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String national;

    @OneToMany(targetEntity = Player.class)
    private List<Club> clubList;

    public Tournaments() {
    }

    public Tournaments(String name, String national) {
        this.name = name;
        this.national = national;
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

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public List<Club> getClubList() {
        return clubList;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }
}
