package com.infinitybyteleague.app.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idPedido;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "estado_de_pedido", nullable = false)
    private String estadoDePedido;

    @Column(name = "total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id_producto")
    @JsonIgnore
    private Producto producto;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Pago> pagos;

	// Constructor vacío para JPA
	public Pedido() {

	}

	public Pedido(int idPedido, Date fecha, String estadoDePedido, double total, Usuario usuario, Producto producto,
			List<Pago> pagos) {
		super();
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.estadoDePedido = estadoDePedido;
		this.total = total;
		this.usuario = usuario;
		this.producto = producto;
		this.pagos = pagos;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstadoDePedido() {
		return estadoDePedido;
	}

	public void setEstadoDePedido(String estadoDePedido) {
		this.estadoDePedido = estadoDePedido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estadoDePedido, fecha, idPedido, pagos, producto, total, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(estadoDePedido, other.estadoDePedido) && Objects.equals(fecha, other.fecha)
				&& idPedido == other.idPedido && Objects.equals(pagos, other.pagos)
				&& Objects.equals(producto, other.producto)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", estadoDePedido=" + estadoDePedido + ", total="
				+ total + ", usuario=" + usuario + ", producto=" + producto + ", pagos=" + pagos + "]";
	}

	
	
}
