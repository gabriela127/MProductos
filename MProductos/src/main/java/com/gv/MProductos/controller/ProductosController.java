package com.gv.MProductos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gv.MProductos.entity.Productos;
import com.gv.MProductos.repository.IProductosRepository;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductosController {

    private final IProductosRepository productosRepository;

    public ProductosController(IProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @PostMapping
    public ResponseEntity<Productos> crearProducto(@Validated @RequestBody Productos producto) {
        Productos nuevoProducto = productosRepository.save(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Productos> listarProductos() {
        return productosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProducto(@PathVariable Long id) {
        Optional<Productos> producto = productosRepository.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @Validated @RequestBody Productos productoActualizado) {
        if (!productosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoActualizado.setId(id);
        Productos producto = productosRepository.save(productoActualizado);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
