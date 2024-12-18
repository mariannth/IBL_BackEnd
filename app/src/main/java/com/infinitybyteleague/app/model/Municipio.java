package com.infinitybyteleague.app.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipios")
    private int idMunicipio;

    @Column(name = "nombre_municipios", nullable = false, length = 100)
    private String nombreMunicipio;

    @ManyToOne
    @JoinColumn(name = "estados_id_estados", nullable = false)
    private Estado estado;

    public Municipio() {}

	public Municipio(int idMunicipio, String nombreMunicipio, Estado estado) {
		super();
		this.idMunicipio = idMunicipio;
		this.nombreMunicipio = nombreMunicipio;
		this.estado = estado;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, idMunicipio, nombreMunicipio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		return Objects.equals(estado, other.estado) && idMunicipio == other.idMunicipio
				&& Objects.equals(nombreMunicipio, other.nombreMunicipio);
	}

	@Override
	public String toString() {
		return "Municipio [idMunicipio=" + idMunicipio + ", nombreMunicipio=" + nombreMunicipio + ", estado=" + estado
				+ "]";
	}

	
}