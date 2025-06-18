public class CajaMisteriosaMain  { 
    public static void main(String ... args)
    { 
        CajaMisteriosa <String> cajita = new CajaMisteriosa<>();
        cajita.guardar("Hola Mundo");

        String valor = cajita.sacar(); // <--- ERROR: El tipo de object no se puede convertir a String

        system.out.println("El valor de la caja es => " + valor);

    }
}

/* Herencia -> Especializaciòn

Polimorfismo -> Generalizaciòn

Animal -> Mamìfero -> Canino -> Perros
                            -> Lobo
                    -> Felinos
                        -> Gato

Object -> String

Object o = new String(); Polimorfismo
String s = new Object(); No funciona

 */