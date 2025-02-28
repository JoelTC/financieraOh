package com.financieraOh.crudEmpleado_webcore.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

	@Mapping(target = "estado", constant = "1")
	@Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "fechaModificacion", ignore = true)
	Empleado createempleadoToEmpleadoDTO(EmpleadoDTO dto);

	@Mapping(target = "fechaModificacion", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "fechaCreacion", ignore = true)
	@Mapping(target = "estado", ignore = true)
	void updateEmpleadoToEmpleadoDTO(EmpleadoDTO dto, @MappingTarget Empleado empleado);

	@Mapping(target = "fechaModificacion", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "fechaCreacion", ignore = true)
	@Mapping(target = "estado", constant = "0")
	void deleteEmpleadoToEmpleadoDTO(Empleado dto, @MappingTarget Empleado empleado);
}