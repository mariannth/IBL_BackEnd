package com.infinitybyteleague.app.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estados")
    private int idEstado;

    @Column(name = "nombre_estados", nullable = false, length = 100)
    private String nombreEstado;

    @ManyToOne
    @JoinColumn(name = "paises_id_pais", nullable = false)
    private Pais pais;

    public Estado() {}

	public Estado(int idEstado, String nombreEstado, Pais pais) {
		super();
		this.idEstado = idEstado;
		this.nombreEstado = nombreEstado;
		this.pais = pais;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEstado, nombreEstado, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return idEstado == other.idEstado && Objects.equals(nombreEstado, other.nombreEstado)
				&& Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", nombreEstado=" + nombreEstado + ", pais=" + pais + "]";
	}

	
    
}
	

