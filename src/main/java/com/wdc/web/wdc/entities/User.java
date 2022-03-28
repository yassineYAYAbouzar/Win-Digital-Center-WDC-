package com.wdc.web.wdc.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

    public User(String nom, String prenom, String password, String email, String telephone, Boolean etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.etat = etat;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "etat", nullable = false)
    private Boolean etat;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authorities_id")}
    )
    private List<Authorities> authoritiesList= new ArrayList<>();

    public User() {

    }
}
