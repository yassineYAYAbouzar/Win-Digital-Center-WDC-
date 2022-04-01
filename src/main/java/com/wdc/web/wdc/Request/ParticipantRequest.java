package com.wdc.web.wdc.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipantRequest {
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;
    private boolean etat = true;
    private String domaine;
    private String structure;
}
