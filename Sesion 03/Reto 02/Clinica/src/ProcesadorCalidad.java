import java.util.*;
import java.util.stream.*;

public class ProcesadorCalidad {
    public static void main(String[] args) {
        // Crear encuestas por sucursal
        Sucursal centro = new Sucursal("Centro", Arrays.asList(
                new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                new Encuesta("Luis", null, 4)
        ));

        Sucursal norte = new Sucursal("Norte", Arrays.asList(
                new Encuesta("Pedro", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Lucía", null, 1)
        ));

        Sucursal sur = new Sucursal("Sur", Arrays.asList(
                new Encuesta("Laura", null, 5),
                new Encuesta("Carlos", "Muy ruidoso", 2)
        ));

        List<Sucursal> sucursales = Arrays.asList(centro, norte, sur);

        // Procesamiento
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3)
                                .map(e -> e.getComentario()
                                        .map(c -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + c + "\"")
                                )
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                )
                .forEach(System.out::println);
    }
}
