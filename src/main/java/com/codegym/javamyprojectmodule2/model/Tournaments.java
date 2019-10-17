package com.codegym.javamyprojectmodule2.model;


import javax.persistence.*;

@Entity
@Table(name = "tournaments")
public class Tournaments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String national;
}
