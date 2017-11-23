package aed.hibernateolimpiadas.tablas;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="medallas")
public class Medallas {
	
	@Id
	@JoinColumn(name="codDeportista")
	private String codDeportista;
	
	@Id
	@JoinColumn(name="codDeportista")
	private String codPrueba;
	
	
	
}
