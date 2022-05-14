package com.wdc.web.wdc.response;

import com.wdc.web.wdc.entities.TypeActivity;
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
    private Long idResponsable;
    private Long idExercise;
    private String idTypeActivity;
}
