package org.generation.ecomerce.service;


import java.util.List;

import org.generation.ecomerce.model.Producto;
import org.generation.ecomerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {

private final ProductoRepository productoRepository;
@Autowired
public ProductoService(ProductoRepository productoRepository) {

this.productoRepository=productoRepository;
}//constructor

public List<Producto> getAllProductos(){
	return productoRepository.findAll();
}//getallProd

public Producto getProducto(Long id) {
		return productoRepository.findById(id).orElseThrow(
()->new IllegalArgumentException("Ese producto no existe"+id));
			}//getprod
public Producto deleteProducto(Long id) {
	Producto tmpProd=null;
	if(productoRepository.existsById(id)) {
	tmpProd=productoRepository.findById(id).get();
			productoRepository.deleteById(id);
	}//if
	return tmpProd;
}//deleteprod

public Producto addProducto(Producto producto) {
	Producto tmpProd=null;
	if(productoRepository.findByNombre(producto.getNombre()).isEmpty()) {
	tmpProd=productoRepository.save(producto);
	}else {
		System.out.println("Ya existe un producto con el nombre: "+producto.getNombre()+"]");
	}return tmpProd;
	}
	
	
public Producto updateProducto(Long id, String nombre,
		String descripcion, String imagen, Double precio) {
	Producto tmpProd=null;
	if(productoRepository.existsById(id)) {
		tmpProd=productoRepository.findById(id).get();
	if(nombre!=null)tmpProd.setNombre(nombre);
	if(descripcion!=null)tmpProd.setDescripcion(descripcion);
	if(imagen!=null)tmpProd.setImagen(imagen);
	if(precio!=null)tmpProd.setPrecio(precio);
			}else {System.out.println("EL PRODUCTO CON ESE ID NO EXISTE");
			}//ELSE
return tmpProd;
	}//updateprod


}//class





