package com.infinitybyteleague.app.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "nombre", nullable = false, length = 60)
	private String nombre;

	@Column(name = "apellido_materno", nullable = false, length = 70)
	private String apellidoMaterno;

	@Column(name = "apellido_paterno", nullable = false, length = 50)
	private String apellidoPaterno;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 30)
	private String password;

	@Column(name = "telefono", nullable = false, length = 45)
	private String telefono;

	@Column(name = "direccion", nullable = false, length = 120)
	private String direccion;

	@Column(name = "rfc", nullable = false, length = 45)
	private String rfc;

	@Column(name = "tipo_usuario", nullable = false, length = 1)
	private String tipoUsuario;

	@ManyToOne
	@JoinColumn(name = "paises_id_pais", nullable = false)
	private Pais pais;

	@ManyToOne
	@JoinColumn(name = "estados_id_estados", nullable = false)
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "municipios_id_municipios", nullable = false)
	private Municipio municipio;

	// Constructor vacío para JPA
	public Usuario() {
	}

	public Usuario(int idUsuario, String nombre, String apellidoMaterno, String apellidoPaterno, String email,
			String password, String telefono, String direccion, String rfc, String tipoUsuario, Pais pais,
			Estado estado, Municipio municipio) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoMaterno = apellidoMaterno;
		this.apellidoPaterno = apellidoPaterno;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.rfc = rfc;
		this.tipoUsuario = tipoUsuario;
		this.pais = pais;
		this.estado = estado;
		this.municipio = municipio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidoMaterno, apellidoPaterno, direccion, email, estado, idUsuario, municipio, nombre,
				pais, password, rfc, telefono, tipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellidoMaterno, other.apellidoMaterno)
				&& Objects.equals(apellidoPaterno, other.apellidoPaterno) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(email, other.email) && Objects.equals(estado, other.estado)
				&& idUsuario == other.idUsuario && Objects.equals(municipio, other.municipio)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais)
				&& Objects.equals(password, other.password) && Objects.equals(rfc, other.rfc)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(tipoUsuario, other.tipoUsuario);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidoMaterno=" + apellidoMaterno
				+ ", apellidoPaterno=" + apellidoPaterno + ", email=" + email + ", password=" + password + ", telefono="
				+ telefono + ", direccion=" + direccion + ", rfc=" + rfc + ", tipoUsuario=" + tipoUsuario + ", pais="
				+ pais + ", estado=" + estado + ", municipio=" + municipio + "]";
	}

}
