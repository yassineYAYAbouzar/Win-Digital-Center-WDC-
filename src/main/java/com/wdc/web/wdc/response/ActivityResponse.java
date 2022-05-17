package com.wdc.web.wdc.response;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityResponse {
    private Long id;
    private String description;
    private Date dateDebuit;
    private Date dateFin;
    private Boolean etat;
    private String idResponsable;
    private String idExercise;
    private String idTypeActivity;
}
