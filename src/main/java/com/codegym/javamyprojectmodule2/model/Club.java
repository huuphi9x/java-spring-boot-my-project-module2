package com.codegym.javamyprojectmodule2.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.persistence.*;



@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String stadium;

    private String tournaments;

    private String national;

    public Club() {
    }

    public Club(String name, String stadium, String tournaments, String national) {
        this.name = name;
        this.stadium = stadium;
        this.tournaments = tournaments;
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

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTournaments() {
        return tournaments;
    }

    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

}
