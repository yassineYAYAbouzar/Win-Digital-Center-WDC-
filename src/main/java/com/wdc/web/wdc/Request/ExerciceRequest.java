package com.wdc.web.wdc.Request;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciceRequest {
    private Date annee;
    private Date dateDebuit;
    private Date dateFin;
    private Boolean status;

}
