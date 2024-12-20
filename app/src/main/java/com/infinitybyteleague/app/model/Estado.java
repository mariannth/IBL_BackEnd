package com.infinitybyteleague.app.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @JoinColumn(name = "paises_id_pais")
    private Pais pais;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Municipio> municipios;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public Estado() {}

	public Estado(int idEstado, String nombreEstado, Pais pais, List<Municipio> municipios, List<Usuario> usuarios) {
		super();
		this.idEstado = idEstado;
		this.nombreEstado = nombreEstado;
		this.pais = pais;
		this.municipios = municipios;
		this.usuarios = usuarios;
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

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	

