import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Creamos un pool de hilos para representar múltiples profesionales
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Creamos tareas simulando médicos
        Runnable draSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfLopez = () -> salaCirugia.usar("Enf. López");
        Runnable drRamirez = () -> salaCirugia.usar("Dr. Ramírez");

        // Ejecutamos las tareas
        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfLopez);
        executor.execute(drRamirez);

        // Cerramos el executor
        executor.shutdown();
    }
}

