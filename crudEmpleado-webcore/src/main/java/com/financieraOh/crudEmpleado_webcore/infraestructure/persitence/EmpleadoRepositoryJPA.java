package com.financieraOh.crudEmpleado_webcore.infraestructure.persitence;

import org.springframework.stereotype.Repository;

import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;
import com.financieraOh.crudEmpleado_webcore.domain.repository.EmpleadoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepositoryJPA extends JpaRepository<Empleado, String>, EmpleadoRepository {

	@Override
	default List<Empleado> listarTodos() {
		return findAll();
	}
	
	List<Empleado> findByEstadoOrderByFechaCreacionDesc(String estado);

	@Override
	default Optional<Empleado> buscarPorId(String documentoId) {
		return findById(documentoId);
	}

	@Override
	default Empleado guardar(Empleado empleado) {
		return save(empleado);
	}

	@Override
	default void eliminar(String documentoId) {
		deleteById(documentoId);
	}
}