package aed.hibernateolimpiadas.tablas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "deportista")
public class Deportistas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition ="char(12)")
	private String dniDeportista;
	@Column(length = 50)
	private String nombreDeportista;
	@Column()
	private String paisDeportista;
	
	@ManyToMany
	@JoinTable(name="medallas",
	joinColumns= {@JoinColumn(name="fechaMedalla")},
	inverseJoinColumns={@JoinColumn(name="codPrueba")})
	private Set <Pruebas> pruebas = new HashSet<Pruebas>();
		
	//Get and Set
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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