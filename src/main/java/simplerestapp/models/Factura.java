package simplerestapp.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import simplerestapp.empleado.Empleado;

@Entity
@Table
public class Factura implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_factura")
	private long idFactura;
	
	@Column(updatable = false)
	private LocalDateTime fechaAlta;
	
	private String detalles;
	
	@ManyToOne(fetch = FetchType.LAZY) //ManyToOne si hay que especificarlo
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;
	
	public Factura(){}

	public Factura(long idFactura, String detalles) {
		super();
		this.idFactura = idFactura;
		this.detalles = detalles;
	}
	
	public Factura(String detalles) {
		super();
		this.idFactura = idFactura;
		this.detalles = detalles;
	}

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	
	public LocalDateTime getFechaAlta() {
		return this.fechaAlta;
	}
	
	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	public Empleado getEmpleado(){
		return this.empleado;
	}
	
	public void setEmpleado(Empleado empleado){
		this.empleado = empleado;
	}
}
