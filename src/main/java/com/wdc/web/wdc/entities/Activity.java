package com.wdc.web.wdc.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_debuit")
    private Date dateDebuit;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "etat", nullable = false)
    private Boolean etat;

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "id_exercise")
    private Long idExercise;


    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private TypeActivity idTypeActivity;

}
