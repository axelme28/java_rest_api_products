package com.init.products.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductsDAO;
import com.init.products.entitys.Product;


@RestController
@RequestMapping("products")
public class ProductsREST {
	@Autowired 
	private ProductsDAO productDAO;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	//servicio para buscar un articulo por el id
	@RequestMapping(value = "{id_producto}")
	public ResponseEntity<Product> getProduct(@PathVariable("id_producto") int productid){
		Optional<Product> opcionalProduct = productDAO.findById(productid);
		if(opcionalProduct.isPresent()) {
			return ResponseEntity.ok(opcionalProduct.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	//Servicio para agregar un nuevo producto
	@PostMapping // /products (POST)
	public ResponseEntity<Product> createProduct (@RequestBody Product product){
		Product newproduct = productDAO.save(product);
		return ResponseEntity.ok(newproduct);
	}
	
	//Servicio para borrar un producto
	@DeleteMapping(value = "{id_producto}") // /products (DELETE)
	public ResponseEntity<Void> deleteProduct (@PathVariable("id_producto") int productid){
		productDAO.deleteById(productid);
		return ResponseEntity.ok(null);
		
	}
	
	//Servicio para actualizar un producto
	@PutMapping // /products (UPDATE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setNo_modelo(product.getNo_modelo());
			updateProduct.setNom_provedor(product.getNom_provedor());
			updateProduct.setNombre(product.getNombre());
			updateProduct.setStock(product.getStock());
			return ResponseEntity.ok(updateProduct);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
		 
	
}
