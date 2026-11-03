package pe.edu.idat.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.edu.idat.soporte.model.Cliente;
import pe.edu.idat.soporte.model.Tecnico;
import java.time.LocalDateTime;

@Data
public class SolicitudDTO {
    private Long id;

    @NotBlank(message = "La descripción de la solicitud no puede estar vacía")
    private String descripcion;

    private String estado;
    private LocalDateTime fechaCreacion;

    @NotNull(message = "Los datos del cliente son obligatorios")
    private Cliente cliente;

    @NotNull(message = "Los datos del técnico son obligatorios")
    private Tecnico tecnico;
}