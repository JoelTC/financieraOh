package com.financieraOh.crudEmpleado_webcore.infraestructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.financieraOh.crudEmpleado_webcore.application.api.EmpleadoService;
import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.application.dto.response.ResponseDTO;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;

@RestController
@RequestMapping("/api/empleado")
@Tag(name = "Empleado", description = "Gestión de empleados")
public class EmpleadoController {

	@Autowired
	EmpleadoService empleadoService;

	/**
	 * API REST Listar Empleados
	 *
	 * @return
	 */
	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Listar empleados")
	public ResponseDTO<Empleado> listarEmpleados() {
		return this.empleadoService.listarEmpleados();
	}

	/**
	 * API REST Obtener empleado
	 *
	 * @PathVariable documentoId
	 * @return
	 */
	@GetMapping("/getDetalle/{documentoId}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Obtener empleado por ID")
	public ResponseDTO<Empleado> obtenerEmpleado(@PathVariable("documentoId") String documentoId) {
		return this.empleadoService.obtenerEmpleado(documentoId);
	}

	/**
	 * API REST Registrar empleado
	 *
	 * @RequestBody empleado
	 * @return
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Registrar un nuevo empleado")
	public ResponseDTO<Empleado> registrarEmpleado(@RequestBody EmpleadoDTO empleado) {
		return this.empleadoService.registrarEmpleado(empleado);
	}

	/**
	 * API REST Modificar empleado
	 *
	 * @RequestBody empleado
	 * @return
	 */
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Modificar empleado")
	public ResponseDTO<Empleado> modificarEmpleado(@RequestBody EmpleadoDTO empleado) {
		return this.empleadoService.modificarEmpleado(empleado);
	}

	/**
	 * API REST Eliminar empleado
	 *
	 * @PathVariable documentoId
	 * @return
	 */
	@PutMapping("/delete/{documentoId}")
	@Operation(summary = "Eliminar un empleado por ID")
	public ResponseDTO<Empleado> eliminarEmpleado(@PathVariable("documentoId") String documentoId) {
		return this.empleadoService.eliminarEmpleado(documentoId);
	}
}