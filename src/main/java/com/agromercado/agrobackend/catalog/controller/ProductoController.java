package com.agromercado.agrobackend.catalog.controller;

import com.agromercado.agrobackend.catalog.model.Producto;
import com.agromercado.agrobackend.catalog.service.ProductoService;
import com.agromercado.agrobackend.catalog.model.MensajeCatalogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/catalog/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeCatalogo.PRODUCTO_AGREGADO);
        response.put("producto", nuevoProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeCatalogo.LISTA_PRODUCTOS);
        response.put("cantidad", productos.size());
        response.put("productos", productos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (productoService.eliminarProducto(id)) {
            response.put("mensaje", MensajeCatalogo.PRODUCTO_ELIMINADO);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", MensajeCatalogo.PRODUCTO_NO_ENCONTRADO);
            return ResponseEntity.status(404).body(response);
        }
    }
}
