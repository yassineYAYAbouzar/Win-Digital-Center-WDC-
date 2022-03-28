package com.wdc.web.wdc.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Getter
@Setter

@Entity
@Table(name = "responsable")
public class Responsable extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "domaine", nullable = false)
    private String domaine;

    @Column(name = "id_type_responsable")
    private Long idTypeResponsable;

    public Responsable(String nom, String prenom, String password, String email, String telephone, Boolean etat) {
        super(nom, prenom, password, email, telephone, etat);
    }

    public Responsable(String nom, String prenom, String password, String email, String telephone, Boolean etat, String domaine) {
        super(nom, prenom, password, email, telephone, etat);
        this.domaine = domaine;
    }

    public Responsable() {
        super();
    }
}
