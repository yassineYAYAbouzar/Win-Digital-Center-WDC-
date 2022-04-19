package com.wdc.web.wdc.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "exercice")
public class Exercice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annee", nullable = false)
    private Date annee;

    @Column(name = "date_debuit", nullable = false)
    private Date dateDebuit;

    @Column(name = "date_fin", nullable = false)
    private Date dateFin;

    @Column(name = "status", nullable = false)
    private Boolean status;

}
