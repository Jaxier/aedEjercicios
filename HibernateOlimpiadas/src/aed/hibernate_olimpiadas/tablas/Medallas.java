package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medallas")
public class Medallas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "fechaMedalla")
	private LocalDate fechaMedalla;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "codDeportista")
	private Deportistas codDeportista;

	@Id
	@ManyToOne
	@JoinColumn(name = "codPrueba")
	private Pruebas codPrueba;

	

	@Column(columnDefinition = "char(1)")
	private String puestoDeportista;

	
	public Deportistas getCodDeportista() {
		return codDeportista;
	}

	public void setCodDeportista(Deportistas codDeportista) {
		this.codDeportista = codDeportista;
	}

	public Pruebas getCodPrueba() {
		return codPrueba;
	}

	public void setCodPrueba(Pruebas codPrueba) {
		this.codPrueba = codPrueba;
	}

	public LocalDate getFechaMedalla() {
		return fechaMedalla;
	}

	public void setFechaMedalla(LocalDate fechaMedalla) {
		this.fechaMedalla = fechaMedalla;
	}

	public String getPuestoDeportista() {
		return puestoDeportista;
	}

	public void setPuestoDeportista(String puestoDeportista) {
		this.puestoDeportista = puestoDeportista;
	}

}
