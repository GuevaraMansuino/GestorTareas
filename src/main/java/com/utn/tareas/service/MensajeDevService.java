package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   🚀 BIENVENIDO AL SISTEMA DE GESTIÓN DE TAREAS  ║");
        System.out.println("║           🔧 MODO DESARROLLO (DEV) 🔧            ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println("👨‍💻 Estás en modo desarrollo - Logs detallados activados");
        System.out.println("📝 Puedes agregar hasta el límite configurado de tareas\n");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║       👋 ¡HASTA PRONTO DESARROLLADOR! 👋         ║");
        System.out.println("║     Gracias por usar el Sistema de Tareas        ║");
        System.out.println("║          🔧 Modo Desarrollo (DEV) 🔧             ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println("💡 Recuerda: Este es un entorno de desarrollo");
        System.out.println("🐛 Happy debugging!\n");
    }
}