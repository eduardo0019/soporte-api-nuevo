package pe.edu.idat.soporte.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Solicitud {
    private Long id;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;
    private Tecnico tecnico;
}