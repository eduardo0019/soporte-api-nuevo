package pe.edu.idat.soporte.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.soporte.dto.SolicitudDTO;
import pe.edu.idat.soporte.model.Solicitud;
import pe.edu.idat.soporte.repository.SolicitudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    // 1. Mapeadores (Traductores)
    private Solicitud mapearAEntidad(SolicitudDTO dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setCliente(dto.getCliente());
        solicitud.setTecnico(dto.getTecnico());
        return solicitud;
    }

    private SolicitudDTO mapearADto(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(solicitud.getId());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaCreacion(solicitud.getFechaCreacion());
        dto.setCliente(solicitud.getCliente());
        dto.setTecnico(solicitud.getTecnico());
        return dto;
    }

    // 2. Lógica de Negocio usando DTOs
    public SolicitudDTO registrarSolicitud(SolicitudDTO solicitudDto) {
        Solicitud nuevaSolicitud = mapearAEntidad(solicitudDto);
        nuevaSolicitud.setEstado("Pendiente");
        nuevaSolicitud.setFechaCreacion(LocalDateTime.now());

        Solicitud guardada = repository.save(nuevaSolicitud);
        return mapearADto(guardada);
    }

    public List<SolicitudDTO> obtenerTodas() {
        return repository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public SolicitudDTO obtenerPorId(Long id) {
        Solicitud solicitud = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con el ID: " + id));
        return mapearADto(solicitud);
    }

    public SolicitudDTO actualizarEstado(Long id, String nuevoEstado) {
        Solicitud solicitudExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con el ID: " + id));

        solicitudExistente.setEstado(nuevoEstado);
        Solicitud actualizada = repository.save(solicitudExistente);
        return mapearADto(actualizada);
    }

    public void eliminar(Long id) {
        boolean eliminado = repository.deleteById(id);
        if (!eliminado) {
            throw new RuntimeException("No se pudo eliminar: Solicitud no encontrada con el ID: " + id);
        }
    }
}