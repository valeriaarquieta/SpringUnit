package org.generation.ecomerce.controller;
//CRUD CREATE- READ- UPDATE -Delete
//HTTP post   get    put    delete 

import java.util.List;

import org.generation.ecomerce.model.Producto;
import org.generation.ecomerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/productos/")
public class ProductoContoller {
	private final ProductoService productoService;
@Autowired	
public ProductoContoller(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

@GetMapping
public List<Producto> getAllProducts(){
	return productoService.getAllProductos();
}//getallprod
@GetMapping (path= "{prodId}")
public Producto getProduct(@PathVariable("prodId")Long id) {
	return productoService.getProducto(id);
}
@DeleteMapping (path= "{prodId}")
public Producto deleteProduct(@PathVariable("prodId")Long id) {
	return productoService.deleteProducto(id);
}//delete
@PostMapping
public Producto addProduct(@RequestBody Producto producto) {
	return productoService.addProducto(producto);
}
@PutMapping (path= "{prodId}")
public Producto updateProduct(@PathVariable ("prodId")Long id,
		@RequestParam(required=false)String nombre,
		@RequestParam(required=false)String descripcion,
		@RequestParam(required=false)String imagen,
		@RequestParam(required=false)Double precio) {
	return productoService.updateProducto(id,nombre,descripcion,imagen,precio);
}//updateP
}//class
