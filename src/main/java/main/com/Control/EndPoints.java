package main.com.Control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.com.model.Produto;

@RestController
@RequestMapping("/produtos")
public class EndPoints {

	@Autowired
	Crud crud;

	@GetMapping
	public List<Produto> listarTODOS() {
		return crud.findAll();
	}

	@PostMapping
	public void salvaUM(@RequestBody Produto produto, HttpServletResponse response) {
		if (crud.insert(produto))
			response.setStatus(201);
		else
			response.setStatus(400);
	}

	@GetMapping(value = "/{codigo}")
	public Produto listarUM(@PathVariable(value = "codigo") int codigo, HttpServletResponse response) {
		if (crud.findOne(codigo) != null) {
			response.setStatus(200);
			return crud.findOne(codigo);
		}
		response.setStatus(400);
		return null;
	}

	@PutMapping(value = "/{codigo}")
	public void updateUM(@PathVariable(value = "codigo") int codigo, @RequestBody Produto produto,HttpServletResponse response) {
		if (crud.updateUser(produto,codigo))
			response.setStatus(200);
		else
			response.setStatus(400);
	}

	@DeleteMapping(value = "/{codigo}")
	public void deletaUM(@PathVariable(value = "codigo") int codigo, HttpServletResponse response) {
		if (crud.deleteUser(codigo))
			response.setStatus(200);
		else
			response.setStatus(400);
	}

}
