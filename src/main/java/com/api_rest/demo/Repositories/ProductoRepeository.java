package com.api_rest.demo.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api_rest.demo.Entities.Producto;



public interface ProductoRepeository extends JpaRepository<Producto, Long>{
    
}
