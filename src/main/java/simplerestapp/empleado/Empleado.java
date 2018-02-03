package simplerestapp.empleado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import simplerestapp.models.Direccion;
import simplerestapp.models.Factura;
import simplerestapp.util.JsonLocalDateTimeDeserializer;
import simplerestapp.util.JsonLocalDateTimeSerializer;

@Entity
@Table
public class Empleado implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEmpleado;
	
	@NotNull
	private String nombres;
	
	@NotNull
	private String apellidos;
	
	@JsonProperty
	@Column(updatable = false)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime fechaAlta;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_direccion")
	@JsonManagedReference
	private Direccion direccion;
	
	//@JsonInclude(Include.NON_NULL)
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL) //Automaticamente es LAZY
	private List<Factura> facturas;
	
	public Empleado(){}
	
	public Empleado(long id, String nombres, String apellidos, LocalDateTime fechaAlta, Direccion direccion) {
		super();
		this.idEmpleado = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
		this.direccion = direccion;
	}
	
	public Empleado(long id, String nombres, String apellidos, LocalDateTime fechaAlta) {
		super();
		this.idEmpleado = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
	}
	
	public Empleado(String nombres, String apellidos, Direccion direccion) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}
	
	@PrePersist
	protected void onCreate(){
		this.fechaAlta = LocalDateTime.now();
	}

	public long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public List<Factura> getFacturas(){
		return facturas;
	}
	
	public void setFacturas(List<Factura> facturas){
		this.facturas = facturas;
	}
	
}
