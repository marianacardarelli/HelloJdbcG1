package org.example.data;
/*DAO es una interfaz general que usaran las distintas clases. Tiene que ser mínimo
como producto, estudiantes, prestamos, clientes, etc. Luego
todas las clases implementan esta plantilla
T es un objeto generico
entity: es un objeto que persiste con el tiempo y que luego puedo recuperar.
Tiene que ser el objeto completo armado
Toda entidad tiene un id, get, set
el id lo crea la base de datos porque es auto-incremental o puede que el objeto cree el ID
con su propio método.
* */

import java.util.List;

public interface DAO<T> {
    //metodo de lectura
    T findById(Long id); //T significa que me devulve un objeto
    List<T> findAll();


    T save(T obj); //devuelve el mismo objeto que guardé
    void upDate(T entity);
    void deleteById(Long id);


}
