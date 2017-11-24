package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "deportista")
public class Deportistas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codDep;
	
	@Column(columnDefinition ="char(12)")
	private String dniDeportista;
	
	@Column(length = 60)
	private String nombreDeportista;
	
	@ManyToMany
	@JoinTable(name="medallas",
	joinColumns= {@JoinColumn(name="codDeportista")},
	inverseJoinColumns={@JoinColumn(name="codPrueba")})
	private List<Pruebas> pruebas = new ArrayList<Pruebas>();
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	private Licencias licencias;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codPais")
	private Paises deportistaPais;

	// Get and Set
	
	public int getCodDep() {
		return codDep;
	}

	public void setCodDep(int codDep) {
		this.codDep = codDep;
	}

	public String getDniDeportista() {
		return dniDeportista;
	}

	public void setDniDeportista(String dniDeportista) {
		this.dniDeportista = dniDeportista;
	}

	public String getNombreDeportista() {
		return nombreDeportista;
	}

	public void setNombreDeportista(String nombreDeportista) {
		this.nombreDeportista = nombreDeportista;
	}

	public List<Pruebas> getPruebas() {
		return pruebas;
	}

	public void setPruebas(List<Pruebas> pruebas) {
		this.pruebas = pruebas;
	}

	public Licencias getLicencias() {
		return licencias;
	}

	public void setLicencias(Licencias licencias) {
		this.licencias = licencias;
	}

	public Paises getDeportistaPais() {
		return deportistaPais;
	}

	public void setDeportistaPais(Paises deportistaPais) {
		this.deportistaPais = deportistaPais;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}