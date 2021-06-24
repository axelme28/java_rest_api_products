package com.init.products.entitys;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Product { 

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = " nom_producto", nullable = false, length = 30)
	private String name;
	
	@Column(name = "id_provedor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_provedor;
	
	@Column(name = "id_modelo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_modelo;

}
