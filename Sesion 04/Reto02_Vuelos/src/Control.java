import java.util.concurrent.*;
import java.util.Random;

public class Control {

    static Random random = new Random();

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean disponible = random.nextDouble() < 0.8; // 80% probabilidad de éxito
                System.out.println("🛣️ Pista disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean favorable = random.nextDouble() < 0.85;
                System.out.println("🌦️ Clima favorable: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean despejado = random.nextDouble() < 0.9;
                System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar tráfico aéreo");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + random.nextInt(1000));
                boolean disponible = random.nextDouble() < 0.95;
                System.out.println("👷‍♂️ Personal disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal en tierra");
            }
        });
    }

    public static void main(String[] args) {

        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        // Ejecutamos las 4 verificaciones en paralelo
        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> all = CompletableFuture.allOf(pista, clima, trafico, personal);

        all.thenRun(() -> {
            boolean resultadoFinal = pista.join() && clima.join() && trafico.join() && personal.join();

            if (resultadoFinal) {
                System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
            } else {
                System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
            }

        }).exceptionally(ex -> {
            System.out.println("\n❌ Error durante el proceso de verificación: " + ex.getMessage());
            return null;
        });

        // Evitar que el programa termine antes de que termine la tarea asincrónica
        try {
            all.get(); // Espera a que todo termine
        } catch (Exception e) {
            System.out.println("❌ Error al esperar los resultados.");
        }
    }
}
