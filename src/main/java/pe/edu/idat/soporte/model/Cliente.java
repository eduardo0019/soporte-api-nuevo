package pe.edu.idat.soporte.model;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String nombre;
    private String correo;
}