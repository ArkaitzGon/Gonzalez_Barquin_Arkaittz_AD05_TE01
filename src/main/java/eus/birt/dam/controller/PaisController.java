/**
 * Controlador de la entidad Pais
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
import eus.birt.dam.domain.Pais;
import eus.birt.dam.repository.PaisRepository;

@Controller
@RequestMapping("/paises")
public class PaisController {

	@Autowired
	PaisRepository paisRepo;
	
	/**
	 * Metodo que enseña la lista de paises
	 * @param model para añadirle atributos
	 * @return html con la lista de paises
	 */
	@GetMapping("")
	public String listaCiudad(Model model) {
		model.addAttribute("listaPais", paisRepo.findAll());
		return ("paises");
	}
	
	/**
	 * Metodo que envia al formulario para crear una nueva ciudad
	 * @param model para añadir atributos
	 * @return con el html del formulario para crear una ciudad
	 */
	@GetMapping("/new")
	public String nuevoPais(Model model) {
		Pais pais = new Pais();
		model.addAttribute("pais", pais);
		//model.addAttribute("pais", paisRepo.findAll());
		return("paisesForm");
	}
	
	/**
	 * Metodo que guarda la nueva ciudad creada
	 * @param ciudad con los valores de los atributos que manda el formulario
	 * @return html con la lista de ciudades
	 */
	@PostMapping("/new/submit")
	public String submitCreacionCiudad(@ModelAttribute Pais pais) {
		paisRepo.save(pais);
		return ("redirect:/paises");
	}
	
	/**
	 * Metodo que borra un ciudad
	 * @param id con el id de la ciudad que se quiere borrar
	 * @return redirige a la lista de ciudades
	 */
	@GetMapping("/borrar/{id}")
	public String borrarPais(@PathVariable("id") int id) {
		paisRepo.deleteById(id);
		return "redirect:/paises";
	}
	
	/**
	 * Metodo que edita una ciudad
	 * @param id con el id de la ciudad que queremos editar
	 * @param model para añadir atributos
	 * @return envia al formulario html de ciudades
	 */
	@GetMapping("/editar/{id}")
	public String editaPais(@PathVariable("id") int id, Model model) {	
		model.addAttribute("pais", paisRepo.findById(id));
		//model.addAttribute("pais", paisRepo.findAll());
		return "paisesForm";
	}
	
	@GetMapping("/ver/{id}")
	public String verPais(@PathVariable("id") int id, Model model) {
		model.addAttribute("ciudadPais", paisRepo.findById(id));
		return ("verPais");
	}
}
