package com.wdc.web.wdc.response;

import lombok.*;

import javax.persistence.Column;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponsableResponse {
    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Boolean etat;
}
