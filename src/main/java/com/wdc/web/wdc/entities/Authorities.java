package com.wdc.web.wdc.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Authorities {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String authoritiesName;
    @ManyToMany(mappedBy = "authoritiesList")
    private List<User> userList = new ArrayList<>();

    public Authorities(String authoritiesName) {
        this.authoritiesName = authoritiesName;
    }
}
