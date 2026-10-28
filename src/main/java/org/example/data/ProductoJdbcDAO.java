package org.example.data;

import org.example.models.Producto;

import java.util.List;

public class ProductoJdbcDAO implements DAO<Producto> {


    @Override
    public Producto findById(Long id) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return List.of();
    }

    @Override
    public Producto save(Producto obj) {
        return null;
    }

    @Override
    public void upDate(Producto entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
