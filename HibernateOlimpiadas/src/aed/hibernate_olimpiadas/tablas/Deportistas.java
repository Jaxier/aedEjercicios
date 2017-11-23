package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(columnDefinition="char(4)")
	private String paisDeportista;
	
	@ManyToMany
	@JoinTable(name="medallas",
	joinColumns= {@JoinColumn(name="codDeportista")},
	inverseJoinColumns={@JoinColumn(name="codPrueba")})
	private List<Pruebas> pruebas = new ArrayList<Pruebas>();
		
	//Get and Set
	public long getId() {
		return codDep;
	}
	public void setId(int id) {
		this.codDep = id;
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
	public String getPaisDeportista() {
		return paisDeportista;
	}
	public void setPaisDeportista(String paisDeportista) {
		this.paisDeportista = paisDeportista;
	}		
	
}