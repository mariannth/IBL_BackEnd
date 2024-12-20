package com.infinitybyteleague.app.model;

import java.sql.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "pago")
public class Pago {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descuento", nullable = false)
    private double descuento;

    @Column(name = "tipo_de_pago", length = 50, nullable = false)
    private String tipoDePago;

    @Column(name = "iva", nullable = false)
    private double iva;

    @Column(name = "folio_factura", length = 50, nullable = false)
    private String folioFactura;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id_pedido")
    private Pedido pedido;

    // Constructor vacío para JPA
    public Pago() {
    }

	public Pago(int idPago, Date fecha, double descuento, String tipoDePago, double iva, String folioFactura,
			Pedido pedido) {
		super();
		this.idPago = idPago;
		this.fecha = fecha;
		this.descuento = descuento;
		this.tipoDePago = tipoDePago;
		this.iva = iva;
		this.folioFactura = folioFactura;
		this.pedido = pedido;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getFolioFactura() {
		return folioFactura;
	}

	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descuento, fecha, folioFactura, idPago, iva, pedido, tipoDePago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(folioFactura, other.folioFactura)
				&& idPago == other.idPago && Double.doubleToLongBits(iva) == Double.doubleToLongBits(other.iva)
				&& Objects.equals(pedido, other.pedido) && Objects.equals(tipoDePago, other.tipoDePago);
	}

	@Override
	public String toString() {
		return "Pago [idPago=" + idPago + ", fecha=" + fecha + ", descuento=" + descuento + ", tipoDePago=" + tipoDePago
				+ ", iva=" + iva + ", folioFactura=" + folioFactura + ", pedido=" + pedido + "]";
	}

	
    
}
