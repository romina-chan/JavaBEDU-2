import java.util.List;

public class Plataforma {

    // Muestra cualquier tipo de material (extends MaterialCurso)
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarResumen();
        }
    }

    // Suma duración de todos los videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos\n");
    }

    // Marca ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) obj;
                e.marcarRevisado();
                System.out.println("✅ Ejercicio '" + e.titulo + "' marcado como revisado.");
            }
        }
        System.out.println();
    }
}

