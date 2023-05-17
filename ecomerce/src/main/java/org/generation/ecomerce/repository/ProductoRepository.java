/**
 * 
 */
package org.generation.ecomerce.repository;

import java.util.Optional;

import org.generation.ecomerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository 
extends JpaRepository<Producto, Long>{
Optional<Producto>findByNombre(String Nombre);
}//INTERFACE PROD
