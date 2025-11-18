package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    {
        // Inicializar con tareas de ejemplo (bloque de inicialización)
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Estudiar Spring Boot", false, Prioridad.ALTA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Hacer el trabajo práctico", false, Prioridad.ALTA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Leer documentación de Spring", false, Prioridad.MEDIA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Practicar inyección de dependencias", true, Prioridad.MEDIA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Configurar profiles", false, Prioridad.BAJA));
    }

    // Guardar una tarea (genera ID automático)
    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(contadorId.getAndIncrement());
        }
        tareas.add(tarea);
        return tarea;
    }

    // Obtener todas las tareas
    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    // Buscar por ID
    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst();
    }

    // Eliminar por ID
    public boolean eliminarPorId(Long id) {
        return tareas.removeIf(tarea -> tarea.getId().equals(id));
    }
}
