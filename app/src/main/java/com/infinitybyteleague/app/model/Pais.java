package com.infinitybyteleague.app.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private int idPais;

    @Column(name = "nombre_pais", nullable = false, length = 100)
    private String nombrePais;
    
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Estado> estados;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    // Constructor vacío para JPA
    public Pais() {
    }

	public Pais(int idPais, String nombrePais, List<Estado> estados, List<Usuario> usuarios) {
		super();
		this.idPais = idPais;
		this.nombrePais = nombrePais;
		this.estados = estados;
		this.usuarios = usuarios;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPais, nombrePais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return idPais == other.idPais && Objects.equals(nombrePais, other.nombrePais);
	}

	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", nombrePais=" + nombrePais + "]";
	}


	
    
    
	
}
