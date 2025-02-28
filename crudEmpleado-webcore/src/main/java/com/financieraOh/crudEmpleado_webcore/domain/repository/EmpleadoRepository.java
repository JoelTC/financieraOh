package com.financieraOh.crudEmpleado_webcore.domain.repository;

import java.util.List;
import java.util.Optional;

import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;

public interface EmpleadoRepository {
	List<Empleado> listarTodos();
	
	List<Empleado> findByEstadoOrderByFechaCreacionDesc(String estado);

	Optional<Empleado> buscarPorId(String documentoId);

	Empleado guardar(Empleado empleado);

	void eliminar(String documentoId);
}