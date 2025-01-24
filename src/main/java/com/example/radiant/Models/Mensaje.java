package com.example.radiant.Models;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class Mensaje {
    private Long id;
    private Long remitenteId;
    private Long destinatarioId;
    private String contenido;
    private Boolean leido;
    private ZonedDateTime enviadoEn = ZonedDateTime.now();
}
