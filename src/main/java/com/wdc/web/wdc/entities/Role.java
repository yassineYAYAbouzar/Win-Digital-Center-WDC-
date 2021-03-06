package com.wdc.web.wdc.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;
    @OneToMany(mappedBy = "role" ,cascade = CascadeType.REMOVE ,fetch = FetchType.LAZY)
    Set<User> users = new HashSet<>();

    public Role(String name, String description, Boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Role() {

    }
}
