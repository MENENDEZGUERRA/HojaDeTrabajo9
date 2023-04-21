// La interfaz CustomMap define los métodos que deben ser implementados
// por las clases de mapeo que se utilizarán en el proyecto.
public interface CustomMap<K, V> {

    // Método put: inserta un elemento en el mapeo asociándolo a una clave.
    // Si la clave ya está presente en el mapeo, se reemplaza el valor asociado.
    void put(K key, V value);

    // Método get: devuelve el valor asociado a una clave en el mapeo.
    // Si la clave no está presente en el mapeo, devuelve null.
    V get(K key);

    // Método size: devuelve el número de elementos en el mapeo.
    int size();

    // Método isEmpty: devuelve true si el mapeo no contiene elementos, false en caso contrario.
    boolean isEmpty();
}
