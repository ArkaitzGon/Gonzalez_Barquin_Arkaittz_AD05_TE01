package eus.birt.dam.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name="pais")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="idioma")
	private String idioma;
	
	@Column(name="poblacion")
	private int poblacion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="pais_id")
	List<Ciudad> ciudades = new ArrayList<Ciudad>();
}
