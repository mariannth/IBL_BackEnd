package com.infinitybyteleague.app.model;


import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre", length = 45, nullable = false)
    private String nombreCat;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria() {
    	
    }

	public Categoria(Integer idCategoria, String nombreCat, String descripcion, List<Producto> productos) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCat = nombreCat;
		this.descripcion = descripcion;
		this.productos = productos;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCat() {
		return nombreCat;
	}

	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, idCategoria, nombreCat, productos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(idCategoria, other.idCategoria)
				&& Objects.equals(nombreCat, other.nombreCat) && Objects.equals(productos, other.productos);
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombreCat=" + nombreCat + ", descripcion=" + descripcion
				+ ", productos=" + productos + "]";
	}
	
	
	
   
}

