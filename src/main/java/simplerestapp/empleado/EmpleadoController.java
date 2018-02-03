package simplerestapp.empleado;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simplerestapp.handlers.EntityNotFoundException;
import simplerestapp.handlers.SuccessfulMessage;

@RestController
public class EmpleadoController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@RequestMapping({"/empleados", "/empleado"})
	public List<Empleado> getEmpleados(){
		return empleadoService.getAllEmpleados();
	}
	
	@GetMapping("/empleado/{id}")
	public Empleado getEmpleado(@PathVariable long id) throws EntityNotFoundException{
		logger.info("Peticion entrante get / id");
		
		return empleadoService.getEmpleado(id);
	}

	@DeleteMapping("/empleado/{id}")
	public void deleteEmpleado(@PathVariable long id) throws EntityNotFoundException{
		empleadoService.deleteEmpleado(id);
	}
	
	@PutMapping("/empleado/{id}")
	public void updateEmpleado(@PathVariable long id, @RequestBody Empleado empleado){
		empleadoService.updateEmpleado(empleado);
	}
	
	@PostMapping("/empleado")
	public ResponseEntity<SuccessfulMessage> addEmpleado(@RequestBody Empleado empleado){
		empleadoService.addEmpleado(empleado);
		
		SuccessfulMessage m = new SuccessfulMessage(HttpStatus.CREATED, "Empleado guardado correctamente.");
		return new ResponseEntity<>(m, m.getStatus());
	}
}
