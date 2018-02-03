package simplerestapp.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import simplerestapp.empleado.Empleado;

@Entity
@Table
public class Direccion implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_direccion")
	private long 	idDireccion;
	
	@OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
	@JsonBackReference
	private Empleado empleado;
	@JsonInclude(Include.NON_NULL)
	private String 	nombreCalle;
	private String 	municipio;
	private int 	numCasa;
	private int 	codigoPostal;
	private String	localidad;
	private String	estado;
	
	public Direccion(){}
	
	public Direccion(long idDireccion, String nombreCalle, String municipio, int numCasa, int codigoPostal,
			String localidad, String estado) {
		super();
		this.idDireccion = idDireccion;
		this.nombreCalle = nombreCalle;
		this.municipio = municipio;
		this.numCasa = numCasa;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.estado = estado;
	}
	
	public Direccion(String nombreCalle, String municipio, int numCasa, int codigoPostal,
			String localidad, String estado) {
		super();
		this.nombreCalle = nombreCalle;
		this.municipio = municipio;
		this.numCasa = numCasa;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.estado = estado;
	}
	
	public long getIdDireccion() {
		return idDireccion;
	}
	public void setIdDireccion(long idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getNombreCalle() {
		return nombreCalle;
	}
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public int getNumCasa() {
		return numCasa;
	}
	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Empleado getEmpleado(){
		return empleado;
	}
	public void setEmpleado(Empleado empleado){
		this.empleado = empleado;
	}
	
	
	
}
