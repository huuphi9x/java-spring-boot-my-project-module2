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

    @ManyToOne
    @JoinColumn(name = "national_id")
    private National national;

    @OneToMany(targetEntity = Club.class)
    private List<Club> clubList;

    public Tournaments() {
    }

    public Tournaments(String name) {
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

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    public List<Club> getClubList() {
        return clubList;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }
}
