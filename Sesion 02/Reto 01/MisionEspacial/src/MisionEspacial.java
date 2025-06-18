import java.util.concurrent.*;

public class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviamos tareas
        Future<String> futuroNavegacion = executor.submit(new SistemaNavegacion());
        Future<String> futuroSoporte = executor.submit(new SistemaSoporteVital());
        Future<String> futuroTermico = executor.submit(new SistemaControlTermico());
        Future<String> futuroComunicaciones = executor.submit(new SistemaComunicaciones());

        try {
            // Mostramos resultados cuando estén listos
            System.out.println(futuroComunicaciones.get());
            System.out.println(futuroSoporte.get());
            System.out.println(futuroTermico.get());
            System.out.println(futuroNavegacion.get());

            System.out.println("✅ Todos los sistemas reportan estado operativo.");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("❌ Error durante la simulación: " + e.getMessage());
        } finally {
            executor.shutdown(); // Muy importante cerrar el pool
        }
    }
}

