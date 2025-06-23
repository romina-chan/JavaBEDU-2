import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

public class MonitoreoUCI {

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🏥 Iniciando monitoreo de signos vitales...");

        // Simulación de 3 pacientes
        Flux<String> paciente1 = generarFlujoPaciente(1);
        Flux<String> paciente2 = generarFlujoPaciente(2);
        Flux<String> paciente3 = generarFlujoPaciente(3);

        // Fusionar todos los flujos
        Flux<String> flujoCombinado = Flux.merge(paciente1, paciente2, paciente3)
                .delayElements(Duration.ofSeconds(1)) // Backpressure: simular procesamiento lento
                .publishOn(Schedulers.boundedElastic());

        // Suscribirse y mostrar alertas
        flujoCombinado.subscribe(alerta -> System.out.println("⚠️ " + alerta));

        // Mantener la app viva
        Thread.sleep(30000); // correr por 30 segundos
    }

    // Método que genera datos aleatorios para un paciente
    public static Flux<String> generarFlujoPaciente(int idPaciente) {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> generarEvento(idPaciente))
                .filter(alerta -> !alerta.isEmpty()); // solo eventos críticos
    }

    // Generar un evento aleatorio para un paciente
    public static String generarEvento(int pacienteId) {
        int fc = 40 + random.nextInt(100); // FC entre 40 y 140
        int sistolica = 80 + random.nextInt(80); // Sistólica entre 80 y 160
        int diastolica = 50 + random.nextInt(50); // Diastólica entre 50 y 100
        int spo2 = 85 + random.nextInt(15); // SpO2 entre 85% y 100%

        StringBuilder alerta = new StringBuilder();

        if (fc < 50 || fc > 120) {
            alerta.append("Paciente ").append(pacienteId).append(" - FC crítica: ").append(fc).append(" bpm\n");
        }

        if (sistolica < 90 || diastolica < 60 || sistolica > 140 || diastolica > 90) {
            alerta.append("Paciente ").append(pacienteId).append(" - PA crítica: ")
                    .append(sistolica).append("/").append(diastolica).append(" mmHg\n");
        }

        if (spo2 < 90) {
            alerta.append("Paciente ").append(pacienteId).append(" - SpO2 baja: ").append(spo2).append("%\n");
        }

        return alerta.toString();
    }
}
