package org.example.models;

import lombok.Data;
//producto es una entidad
@Data
public class Producto {
    private Long id;
    private String nombre;
    private String categoria;
    private Boolean disponible;
}
