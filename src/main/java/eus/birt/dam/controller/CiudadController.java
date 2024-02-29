/**
 * Controlador de la entidad Ciudad
 */
package eus.birt.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eus.birt.dam.domain.Ciudad;
import eus.birt.dam.repository.CiudadRepository;
import eus.birt.dam.repository.PaisRepository;

@Controller
@RequestMapping ("/ciudades")
public class CiudadController {
	
	@Autowired
	CiudadRepository ciudadRepo;
	
	@Autowired
	PaisRepository paisRepo;
	
	/**
	 * Metodo que enseña la lista de cidades
	 * @param model para añadirle atributos
	 * @return html con la lista de ciudades
	 */
	@GetMapping("")
	public String listaCiudad(Model model) {
		model.addAttribute("listaCiudad", ciudadRepo.findAll());
		return ("ciudades");
	}

	/**
	 * Metodo que envia al formulario para crear una nueva ciudad
	 * @param model para añadir atributos
	 * @return con el html del formulario para crear una ciudad
	 */
	@GetMapping("/new")
	public String nuevaCiudad(Model model) {
		Ciudad ciudad = new Ciudad();
		model.addAttribute("ciudad", ciudad);
		model.addAttribute("pais", paisRepo.findAll());
		return("ciudadesForm");
	}
	
	/**
	 * Metodo que guarda la nueva ciudad creada
	 * @param ciudad con los valores de los atributos que manda el formulario
	 * @return html con la lista de ciudades
	 */
	@PostMapping("/new/submit")
	public String submitCreacionCiudad(@ModelAttribute Ciudad ciudad) {
		ciudadRepo.save(ciudad);
		return ("redirect:/ciudades");
	}
	
	/**
	 * Metodo que borra un ciudad
	 * @param id con el id de la ciudad que se quiere borrar
	 * @return redirige a la lista de ciudades
	 */
	@GetMapping("/borrar/{id}")
	public String borrarCiudad(@PathVariable("id") int id) {
		ciudadRepo.deleteById(id);
		return "redirect:/ciudades";
	}
	
	/**
	 * Metodo que edita una ciudad
	 * @param id con el id de la ciudad que queremos editar
	 * @param model para añadir atributos
	 * @return envia al formulario html de ciudades
	 */
	@GetMapping("/editar/{id}")
	public String editaCiudad(@PathVariable("id") int id, Model model) {	
		model.addAttribute("ciudad", ciudadRepo.findById(id));
		model.addAttribute("pais", paisRepo.findAll());
		return "ciudadesForm";
	}
}
