package com.financieraOh.crudEmpleado_webcore.application.api;

import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.application.dto.response.ResponseDTO;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;

public interface EmpleadoService {

	public ResponseDTO<Empleado> listarEmpleados();

	public ResponseDTO<Empleado> obtenerEmpleado(String documentoId);

	public ResponseDTO<Empleado> registrarEmpleado(EmpleadoDTO empleado);

	public ResponseDTO<Empleado> modificarEmpleado(EmpleadoDTO empleado);

	public ResponseDTO<Empleado> eliminarEmpleado(String documentoId);
}