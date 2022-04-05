package com.wdc.web.wdc.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciceResponse {
    private Date annee;
    private Date dateDebuit;
    private Date dateFin;
    private Boolean status;
}

