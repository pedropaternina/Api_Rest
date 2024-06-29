package com.api_rest.demo.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_rest.demo.Entities.Producto;
import com.api_rest.demo.Repositories.ProductoRepeository;
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepeository productoRepeository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepeository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getOneProductoByID(@PathVariable Long id){
        return productoRepeository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el prodructo por el id: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepeository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoRepeository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepeository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepeository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));

        productoRepeository.delete(producto);
        return "Se elimino el producto con el ID: " + producto.getId() + "Fue eliminado correctamente";
    }

}
