package com.wdc.web.wdc.response;

import lombok.*;

import javax.persistence.Column;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponsableResponse {
    private Long id;
    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Boolean etat;
    private String typeResponsable;
}
