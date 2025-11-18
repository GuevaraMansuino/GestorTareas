package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE TAREAS ===");
        System.out.println("Modo: PRODUCCIÓN");
        System.out.println("Iniciando aplicación...\n");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("\n=== FIN DE EJECUCIÓN ===");
        System.out.println("Sistema finalizado correctamente.\n");
    }
}