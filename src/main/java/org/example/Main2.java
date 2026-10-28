package org.example;

import org.example.data.ProductoJdbcDAO;
import org.example.models.Producto;

public class Main2 {
    public static void main(String[] args) {
        var dao= new ProductoJdbcDAO();
        Producto p = dao.findById(1L);
        System.out.println("Imprimimos desde el Main2: " + p);
    }
}
