package com.wdc.web.wdc.Request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponsableRequest {

    private String nom;
    @NotNull
    @Size(min =3)
    private String prenom;
    @NotNull
    @Size(min = 8 , max=12)
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8 , max=12)
    private String telephone;
    private boolean etat = true;
    @NotNull
    @Size(min =3)
    private String domaine;
    @NotNull
    @Size(min =3)
    private String structure;
    private Long typeResponsable;
}
