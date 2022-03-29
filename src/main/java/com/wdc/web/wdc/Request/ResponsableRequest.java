package com.wdc.web.wdc.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponsableRequest {
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;
    private boolean etat;
    private String domaine;
}
