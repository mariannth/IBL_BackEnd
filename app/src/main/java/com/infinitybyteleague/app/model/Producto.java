package com.infinitybyteleague.app.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "sku", length = 60, nullable = false)
    private String sku;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "url", length = 200, nullable = false)
    private String url;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;

    @Column(name = "nombre_producto", length = 200, nullable = false)
    private String nombreProducto;

    @ManyToOne
    @JoinColumn(name = "categoria_id_categoria")
    @JsonIgnore
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    // Constructor vacío para JPA
    public Producto() {
    }

	public Producto(int idProducto, String sku, String descripcion, double precio, String url, int stock, String marca,
			String nombreProducto, Categoria categoria, List<Pedido> pedidos) {
		super();
		this.idProducto = idProducto;
		this.sku = sku;
		this.descripcion = descripcion;
		this.precio = precio;
		this.url = url;
		this.stock = stock;
		this.marca = marca;
		this.nombreProducto = nombreProducto;
		this.categoria = categoria;
		this.pedidos = pedidos;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, descripcion, idProducto, marca, nombreProducto, pedidos, precio, sku, stock,
				url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descripcion, other.descripcion)
				&& idProducto == other.idProducto && Objects.equals(marca, other.marca)
				&& Objects.equals(nombreProducto, other.nombreProducto) && Objects.equals(pedidos, other.pedidos)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(sku, other.sku) && stock == other.stock && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", sku=" + sku + ", descripcion=" + descripcion + ", precio="
				+ precio + ", url=" + url + ", stock=" + stock + ", marca=" + marca + ", nombreProducto="
				+ nombreProducto + ", categoria=" + categoria + ", pedidos=" + pedidos + "]";
	}


	
}
