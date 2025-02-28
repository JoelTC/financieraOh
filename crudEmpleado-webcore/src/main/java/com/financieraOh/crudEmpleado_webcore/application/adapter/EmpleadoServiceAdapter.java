package com.financieraOh.crudEmpleado_webcore.application.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.financieraOh.crudEmpleado_webcore.application.api.EmpleadoService;
import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.application.dto.response.ResponseDTO;
import com.financieraOh.crudEmpleado_webcore.application.mapper.EmpleadoMapper;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;
import com.financieraOh.crudEmpleado_webcore.domain.repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmpleadoServiceAdapter implements EmpleadoService {

	private static Logger log = LoggerFactory.getLogger(EmpleadoServiceAdapter.class);

	@Autowired
	EmpleadoRepository empleadoRepositorio;

	@Autowired
	EmpleadoMapper empleadoMapper;

	@Override
	public ResponseDTO<Empleado> listarEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		try {
			empleados = this.empleadoRepositorio.findByEstadoOrderByFechaCreacionDesc("1");

			if (empleados.isEmpty()) {
				return ResponseDTO.noContent("No hay empleados registrados");
			}

		} catch (Exception e) {
			log.error("Error: ", e);
			return ResponseDTO.error("Error al listar empleados");
		}
		return ResponseDTO.success(empleados, null, "OK");
	}

	@Override
	public ResponseDTO<Empleado> obtenerEmpleado(String documentoId) {
		Optional<Empleado> empleado = Optional.empty();
		try {
			empleado = this.buscarEmpleadoId(documentoId);

			if (!empleado.isPresent()) {
				return ResponseDTO.noElement("Empleado no registrado");
			}
		} catch (Exception e) {
			log.error("Error: ", e);
			return ResponseDTO.error("Error al obtener empleado");
		}
		return ResponseDTO.success(null, empleado.get(), "OK");
	}

	@Transactional
	@Override
	public ResponseDTO<Empleado> registrarEmpleado(EmpleadoDTO empleadoDTO) {
		try {
			Optional<Empleado> optionalEmpleado = this.buscarEmpleadoId(empleadoDTO.getDocumentoId());
			if (optionalEmpleado.isPresent()) {
				return ResponseDTO.error("Empleado ya existe");
			}

			Empleado empleado = empleadoMapper.createempleadoToEmpleadoDTO(empleadoDTO);

			this.empleadoRepositorio.guardar(empleado);
		} catch (Exception e) {
			log.error("Error: ", e);
			return ResponseDTO.error("Error al registrar empleado");
		}
		return ResponseDTO.success(null, null, "Empleado registrado correctamente");
	}

	@Transactional
	@Override
	public ResponseDTO<Empleado> modificarEmpleado(EmpleadoDTO empleadoDTO) {
		Empleado empleado = new Empleado();
		try {
			Optional<Empleado> optionalEmpleado = this.buscarEmpleadoId(empleadoDTO.getDocumentoId());
			if (!optionalEmpleado.isPresent()) {
				return ResponseDTO.noElement("Empleado no registrado");
			}

			empleado = optionalEmpleado.get();
			empleadoMapper.updateEmpleadoToEmpleadoDTO(empleadoDTO, empleado);
			this.empleadoRepositorio.guardar(empleado);
		} catch (Exception e) {
			log.error("Error: ", e);
			return ResponseDTO.error("Error al modificar empleado");
		}
		return ResponseDTO.success(null, empleado, "Empleado modificado correctamente");
	}

	@Transactional
	@Override
	public ResponseDTO<Empleado> eliminarEmpleado(String documentoId) {
		Empleado empleado = new Empleado();
		try {
			Optional<Empleado> optionalEmpleado = this.buscarEmpleadoId(documentoId);
			if (!optionalEmpleado.isPresent()) {
				return ResponseDTO.noElement("Empleado no registrado");
			}

			empleado = optionalEmpleado.get();
			empleadoMapper.deleteEmpleadoToEmpleadoDTO(empleado, empleado);
			
			this.empleadoRepositorio.guardar(empleado);
		} catch (Exception e) {
			log.error("Error: ", e);
			return ResponseDTO.error("Error al eliminar empleado");
		}
		return ResponseDTO.success(null, null, "Empleado eliminado correctamente");
	}

	private Optional<Empleado> buscarEmpleadoId(String documentoId) {
		try {
			return this.empleadoRepositorio.buscarPorId(documentoId);
		} catch (Exception e) {
			log.error("Error: ", e);
			return Optional.empty();
		}
	}
}