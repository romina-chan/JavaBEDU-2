import java.util.*;
import java.util.stream.*;

public class ProcesadorPedido {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Ana", "domicilio", "555-1234"),
                new Pedido("Luis", "local", "555-0000"),
                new Pedido("María", "domicilio", null),
                new Pedido("Pedro", "domicilio", "555-5678"),
                new Pedido("Laura", "local", null)
        );

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))   // solo pedidos a domicilio
                .map(Pedido::getTelefono)                                       // obtenemos Optional<String>
                .flatMap(Optional::stream)                                      // eliminamos los Optional vacíos
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}

