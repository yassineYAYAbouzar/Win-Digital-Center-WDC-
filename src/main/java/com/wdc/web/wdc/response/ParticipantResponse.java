package com.wdc.web.wdc.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipantResponse {
    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Boolean etat;
    private String structure;
}
