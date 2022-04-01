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

    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_debuit", nullable = false)
    private Date dateDebuit;

    @Column(name = "date_fin", nullable = false)
    private Date dateFin;

    @Column(name = "etat", nullable = false)
    private Boolean etat;

    @Column(name = "id_responsable", nullable = false)
    private Long idResponsable;

    @Column(name = "id_exercise", nullable = false)
    private Long idExercise;


    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private TypeActivity idTypeActivity;

}
