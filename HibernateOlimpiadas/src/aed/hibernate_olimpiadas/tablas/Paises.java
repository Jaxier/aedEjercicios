package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
public class Paises implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(4)")
	private String codPais;
	
	@Column(length = 30)
	private String nombrePais;
	
	@OneToMany(targetEntity = Deportistas.class, mappedBy="deportistaPais",fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Deportistas> deportistas = new ArrayList<Deportistas>();
	
	// Geter And Setter
	
	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public List<Deportistas> getDeportistas() {
		return deportistas;
	}

	public void setDeportistas(List<Deportistas> deportistas) {
		this.deportistas = deportistas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}
