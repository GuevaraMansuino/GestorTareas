package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // genera el constructor con los campos final
public class TareaService {

    private final TareaRepository tareaRepository;

    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    // Agregar una nueva tarea
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        List<Tarea> tareasActuales = tareaRepository.obtenerTodas();

        if (tareasActuales.size() >= maxTareas) {
            throw new RuntimeException("No se pueden agregar más tareas. Límite máximo: " + maxTareas);
        }

        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.guardar(nuevaTarea);
    }

    // Listar todas las tareas
    public List<Tarea> listarTodas() {
        return tareaRepository.obtenerTodas();
    }

    // Listar tareas pendientes
    public List<Tarea> listarPendientes() {
        return tareaRepository.obtenerTodas().stream()
                .filter(tarea -> !tarea.isCompletada())
                .collect(Collectors.toList());
    }

    // Listar tareas completadas
    public List<Tarea> listarCompletadas() {
        return tareaRepository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    // Marcar una tarea como completada
    public boolean marcarComoCompletada(Long id) {
        return tareaRepository.buscarPorId(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    return true;
                })
                .orElse(false);
    }

    // Obtener estadísticas
    public String obtenerEstadisticas() {
        List<Tarea> todasLasTareas = tareaRepository.obtenerTodas();
        long total = todasLasTareas.size();
        long completadas = todasLasTareas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format("""
                === ESTADÍSTICAS ===
                Total de tareas: %d
                Completadas: %d
                Pendientes: %d
                """, total, completadas, pendientes);
    }

    // Mostrar configuración
    public void mostrarConfiguracion() {
        System.out.println("=== CONFIGURACIÓN ===");
        System.out.println("Nombre de la aplicación: " + nombreApp);
        System.out.println("Máximo de tareas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
        System.out.println("====================\n");
    }
}
