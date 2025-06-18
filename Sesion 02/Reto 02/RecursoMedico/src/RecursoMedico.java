import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private String nombre;
    private ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println("🔄 " + profesional + " quiere acceder a " + nombre);
        lock.lock(); // Bloquea el recurso para este hilo
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); // Simula el tiempo que el recurso está en uso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("❌ " + profesional + " fue interrumpido.");
        } finally {
            lock.unlock(); // Libera el recurso
        }
    }
}

