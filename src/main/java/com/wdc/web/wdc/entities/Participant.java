package com.wdc.web.wdc.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "participant")
public class Participant extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "structure", nullable = false)
    private String structure;

    @Column(name = "domaine", nullable = false)
    private String domaine;

    public Participant() {
        super();
    }

    public Participant(String structure, String domaine) {
        this.structure = structure;
        this.domaine = domaine;
    }

    public Participant(String nom, String prenom, String password, String email, String telephone, Boolean etat) {
        super(nom, prenom, password, email, telephone, etat);
    }
}
