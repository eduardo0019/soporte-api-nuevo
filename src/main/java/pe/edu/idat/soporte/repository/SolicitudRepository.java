package pe.edu.idat.soporte.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.soporte.model.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SolicitudRepository {

    // Tabla de base de datos en memoria
    private final List<Solicitud> solicitudes = new ArrayList<>();
    private Long idContador = 1L;

    // Método para crear o actualizar solicitud
    public Solicitud save(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            // Nueva solicitud, Se asigna ID incremental
            solicitud.setId(idContador++);
            solicitudes.add(solicitud);
        } else {
            // Si tiene ID, buscar y actualizar existente
            for (int i = 0; i < solicitudes.size(); i++) {
                if (solicitudes.get(i).getId().equals(solicitud.getId())) {
                    solicitudes.set(i, solicitud);
                    break;
                }
            }
        }
        return solicitud;
    }

    // Método para obtener todas las solicitudes
    public List<Solicitud> findAll() {
        return solicitudes;
    }

    // Método para buscar una solicitud específica por su ID
    public Optional<Solicitud> findById(Long id) {
        return solicitudes.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    // Método para eliminar una solicitud
    public boolean deleteById(Long id) {
        return solicitudes.removeIf(s -> s.getId().equals(id));
    }
}