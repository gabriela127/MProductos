package com.gv.MProductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gv.MProductos.entity.Productos;

@Repository
public interface IProductosRepository extends JpaRepository<Productos, Long> {

}
