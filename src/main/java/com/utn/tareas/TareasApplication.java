package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	// Inyección por constructor
	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 1. Mostrar mensaje de bienvenida
		mensajeService.mostrarBienvenida();

		// 2. Mostrar la configuración actual
		tareaService.mostrarConfiguracion();

		// 3. Listar todas las tareas iniciales
		System.out.println("📋 === TODAS LAS TAREAS INICIALES ===");
		List<Tarea> todasLasTareas = tareaService.listarTodas();
		todasLasTareas.forEach(System.out::println);
		System.out.println();

		// 4. Agregar una nueva tarea
		System.out.println("➕ === AGREGANDO NUEVA TAREA ===");
		Tarea nuevaTarea = tareaService.agregarTarea("Implementar CommandLineRunner", Prioridad.ALTA);
		System.out.println("Tarea agregada: " + nuevaTarea);
		System.out.println();

		// 5. Listar tareas pendientes
		System.out.println("⏳ === TAREAS PENDIENTES ===");
		List<Tarea> pendientes = tareaService.listarPendientes();
		pendientes.forEach(System.out::println);
		System.out.println();

		// 6. Marcar una tarea como completada
		System.out.println("✅ === MARCANDO TAREA COMO COMPLETADA ===");
		Long idParaCompletar = 2L; // ID de una tarea existente
		boolean marcada = tareaService.marcarComoCompletada(idParaCompletar);
		if (marcada) {
			System.out.println("Tarea con ID " + idParaCompletar + " marcada como completada");
		} else {
			System.out.println("No se pudo marcar la tarea con ID " + idParaCompletar);
		}
		System.out.println();

		// 7. Mostrar estadísticas
		System.out.println(tareaService.obtenerEstadisticas());
		System.out.println();

		// 8. Listar tareas completadas
		System.out.println("✔️ === TAREAS COMPLETADAS ===");
		List<Tarea> completadas = tareaService.listarCompletadas();
		completadas.forEach(System.out::println);
		System.out.println();

		// 9. Mostrar mensaje de despedida
		mensajeService.mostrarDespedida();
	}
}