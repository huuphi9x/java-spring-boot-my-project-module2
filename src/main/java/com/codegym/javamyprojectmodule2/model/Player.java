package com.codegym.javamyprojectmodule2.model;


import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "national_id")
    private National national;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Player() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position=" + position +
                ", national=" + national +
                ", club=" + club +
                '}';
    }
}
