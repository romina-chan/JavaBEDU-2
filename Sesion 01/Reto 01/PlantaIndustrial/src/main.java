import java.util.*;

public class main {
    public static void main(String[] args) {

        // Crear listas
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar las órdenes
        mostrarOrdenes.mostrarOrdenes(ordenesMasa);
        mostrarOrdenes.mostrarOrdenes(ordenesPersonalizadas);
        mostrarOrdenes.mostrarOrdenes(ordenesPrototipo);

        // Procesar órdenes personalizadas
        mostrarOrdenes.procesarPersonalizadas(new ArrayList<OrdenProduccion>(ordenesPersonalizadas), 200);

        // Conteo total
        System.out.println("📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + ordenesMasa.size());
        System.out.println("🛠️ Personalizadas: " + ordenesPersonalizadas.size());
        System.out.println("🧪 Prototipos: " + ordenesPrototipo.size());
    }
}

