package simplerestapp.empleado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import simplerestapp.handlers.EntityNotFoundException;
import simplerestapp.handlers.SuccessfulMessage;

@Repository
@Transactional
@Service
public class EmpleadoService {

	@Autowired
	private EntityManager entityManager;
	
	public List<Empleado> getAllEmpleados(){
		return (List<Empleado>)entityManager.createQuery("FROM Empleado").getResultList();
	}
	
	public Empleado getEmpleado(long id) throws EntityNotFoundException{
		Empleado empleado = entityManager.find(Empleado.class, id);
		
		if(empleado == null){
			throw new EntityNotFoundException(Empleado.class, "id", Long.toString(id));
		}
		
		return empleado;
	}
	
	public void addEmpleado(Empleado empleado){
		entityManager.persist(empleado);
	}
	
	public void updateEmpleado(Empleado empleado){
		entityManager.merge(empleado);
	}
	
	public void deleteEmpleado(long id) throws EntityNotFoundException{
		Empleado empleado = getEmpleado(id);
		
		if(empleado != null)
			entityManager.remove(empleado);
	}
	
	public boolean empleadoExists(long id) throws EntityNotFoundException{
		return getEmpleado(id) != null;
	}
}
