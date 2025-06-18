import java.util.ArrayList;
import java.util.List;

public class MainCurso {
    public static void main(String[] args) {
        // Lista general de materiales
        List<MaterialCurso> materiales = new ArrayList<>();

        // Videos
        Video v1 = new Video("Introducción a Java", "Mario", 15);
        Video v2 = new Video("POO en Java", "Carlos", 20);

        // Artículos
        Articulo a1 = new Articulo("Historia de Java", "Ana", 1200);
        Articulo a2 = new Articulo("Tipos de datos", "Luis", 800);

        // Ejercicios
        Ejercicio e1 = new Ejercicio("Variables y tipos", "Luis");
        Ejercicio e2 = new Ejercicio("Condicionales", "Mario");

        // Agregar a la lista general
        materiales.add(v1);
        materiales.add(v2);
        materiales.add(a1);
        materiales.add(a2);
        materiales.add(e1);
        materiales.add(e2);

        // Mostrar todos
        Plataforma.mostrarMateriales(materiales);

        // Solo videos
        List<Video> soloVideos = new ArrayList<>();
        soloVideos.add(v1);
        soloVideos.add(v2);
        Plataforma.contarDuracionVideos(soloVideos);

        // Marcar ejercicios
        Plataforma.marcarEjerciciosRevisados(materiales);

        // 🔍 Filtrar materiales por autor "Mario"
        System.out.println("🔍 Filtrando materiales por autor:");
        for (MaterialCurso m : materiales) {
            if (m.autor.equals("Mario")) {
                m.mostrarResumen();
            }
        }
    }
}

