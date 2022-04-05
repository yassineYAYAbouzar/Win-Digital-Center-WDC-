package com.wdc.web.wdc.Request;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityRequest {
    private String description;
    private Date dateDebuit;
    private Date dateFin;
    private Boolean etat;
    private Long idResponsable;
    private Long idExercise;
    private String typeActivity;

}
