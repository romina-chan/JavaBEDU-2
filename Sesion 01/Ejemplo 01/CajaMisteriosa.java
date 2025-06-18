// PascaLCase -> Primera letra de c/palabra mayuscula
// camelCase -> La primera letra es minùscula y luego la de c/palbra mayùscula

public class CajaMisteriosa <X> {
    private X valor;

    public void guardar(X nuevoValor) { 
        this.valor = nuevoValor;

    }

    public X sacar() { 
        return valor;
    }

    public boolean estaVacia() { 
        return valor == null;
    }
 }

 // new CajaMisteriosa(); -> Ejecuta el 