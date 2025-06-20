import java.util.Scanner;
import java.util.concurrent.*;
import java.util.Random;

public class MovilidadApp {

    // Simula cálculo de ruta con latencia
    public static CompletableFuture<String> calcularRuta(String origen, String destino) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                Thread.sleep(2000 + new Random().nextInt(1000));
                return origen + " -> " + destino;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al calcular la ruta", e);
            }
        });
    }

    // Simula estimación de tarifa basada en km
    public static CompletableFuture<Double> estimarTarifa(double km) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                Thread.sleep(1000 + new Random().nextInt(1000));
                double tarifaPorKm = 2.5;
                return km * tarifaPorKm;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al estimar tarifa", e);
            }
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String origen = "Centro";
        String destino = "Destino Final"; // Puedes hacer que el usuario también lo ingrese

        System.out.println("🚗 Bienvenido a la app de movilidad.");
        System.out.println("Escribe la distancia en km a recorrer. Escribe cualquier letra para salir.\n");

        while (true) {
            System.out.print("🛣️ Distancia en km: ");
            String input = scanner.nextLine();

            try {
                double km = Double.parseDouble(input);

                // Lanzamos las tareas asíncronas
                CompletableFuture<String> rutaFuture = calcularRuta(origen, destino);
                CompletableFuture<Double> tarifaFuture = estimarTarifa(km);

                // Combinamos los resultados
                CompletableFuture<Void> resultado = rutaFuture
                        .thenCombine(tarifaFuture, (ruta, tarifa) ->
                                "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + String.format("%.2f", tarifa))
                        .exceptionally(ex -> "❌ Ocurrió un error: " + ex.getMessage())
                        .thenAccept(System.out::println);

                // Esperamos a que se imprima antes de continuar el bucle
                resultado.get(); // Esto hace una pausa solo mientras termina la operación

            } catch (NumberFormatException e) {
                System.out.println("👋 Entrada no válida. Terminando programa...");
                break;
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}