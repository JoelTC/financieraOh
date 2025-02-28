package com.financieraOh.crudEmpleado_webcore.infraestructure.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.financieraOh.crudEmpleado_webcore.application.api.EmpleadoService;
import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.application.dto.response.ResponseDTO;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;

@SpringBootTest
public class EmpleadoControllerTest {
	@InjectMocks
	private EmpleadoController controller;

	@Mock
	private EmpleadoService service;
	
	@Test
    void listarEmpleadosSuccess() {
        Empleado empleado = new Empleado();
        ResponseDTO<Empleado> responseMock = ResponseDTO.success(List.of(empleado), null, "OK");

        when(service.listarEmpleados()).thenReturn(responseMock);

        ResponseDTO<Empleado> response = controller.listarEmpleados();

        assertThat(response.getDataResponse()).isNotNull();
        assertThat(response.getMensaje()).isEqualTo("OK");
    }
	
	@Test
    void listarEmpleadosSinContenido() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.noContent("No hay empleados registrados");

        when(service.listarEmpleados()).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.listarEmpleados();

        assertThat(response.getCodigo()).isEqualTo("204");
        assertThat(response.getMensaje()).isEqualTo("No hay empleados registrados");
    }
	
	@Test
    void listarEmpleadosError() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.error("Error al listar empleados");

        when(service.listarEmpleados()).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.listarEmpleados();

        assertThat(response.getCodigo()).isEqualTo("999");
        assertThat(response.getMensaje()).isEqualTo("Error al listar empleados");
    }
		
	@Test
    void obtenerEmpleadoSuccess() throws Exception {
        Empleado empleado = new Empleado();
        ResponseDTO<Empleado> responseMock = ResponseDTO.success(null, empleado, "OK");

        when(service.obtenerEmpleado("123")).thenReturn(responseMock);

        ResponseDTO<Empleado> response = controller.obtenerEmpleado("123");
        assertThat(response.getCodigo()).isEqualTo("200");
        assertThat(response.getMensaje()).isEqualTo("OK");
    }
	
	@Test
    void obtenerEmpleadoNoElemento() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.noElement("Empleado no registrado");

        when(service.obtenerEmpleado("123")).thenReturn(responseMock);

        ResponseDTO<Empleado> response = controller.obtenerEmpleado("123");
        assertThat(response.getCodigo()).isEqualTo("205");
        assertThat(response.getMensaje()).isEqualTo("Empleado no registrado");
    }
	
	@Test
    void obtenerEmpleadoError() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.error("Error al obtener empleado");

        when(service.listarEmpleados()).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.listarEmpleados();

        assertThat(response.getCodigo()).isEqualTo("999");
        assertThat(response.getMensaje()).isEqualTo("Error al obtener empleado");
    }

    @Test
    void registrarEmpleadoSuccess() throws Exception {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000);
        ResponseDTO<Empleado> responseMock = ResponseDTO.success(null, null, "Empleado registrado correctamente");

        when(service.registrarEmpleado(any(EmpleadoDTO.class))).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.registrarEmpleado(empleadoDTO);
        assertThat(response.getCodigo()).isEqualTo("200");
        assertThat(response.getMensaje()).isEqualTo("Empleado registrado correctamente");
    }
    
    @Test
    void registrarEmpleadoError() throws Exception {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000);
        ResponseDTO<Empleado> responseMock = ResponseDTO.error("Error al registrar empleado");

        when(service.registrarEmpleado(any(EmpleadoDTO.class))).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.registrarEmpleado(empleadoDTO);
        assertThat(response.getCodigo()).isEqualTo("999");
        assertThat(response.getMensaje()).isEqualTo("Error al registrar empleado");
    }

    @Test
    void modificarEmpleadoSuccess() throws Exception {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 35, LocalDate.of(1988, 5, 12), 6000);
        ResponseDTO<Empleado> responseMock = ResponseDTO.success(null, new Empleado("123", "Juan", "Perez", "Gomez", 35, LocalDate.of(1988, 5, 12), 6000, null, null, null), "Empleado modificado correctamente");

        when(service.modificarEmpleado(any(EmpleadoDTO.class))).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.modificarEmpleado(empleadoDTO);
        assertThat(response.getCodigo()).isEqualTo("200");
        assertThat(response.getMensaje()).isEqualTo("Empleado modificado correctamente");
    }
    
    @Test
    void modificarEmpleadoError() throws Exception {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 35, LocalDate.of(1988, 5, 12), 6000);
        ResponseDTO<Empleado> responseMock = ResponseDTO.error("Error al modificar empleado");

        when(service.modificarEmpleado(any(EmpleadoDTO.class))).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.modificarEmpleado(empleadoDTO);
        assertThat(response.getCodigo()).isEqualTo("999");
        assertThat(response.getMensaje()).isEqualTo("Error al modificar empleado");
    }

    @Test
    void eliminarEmpleadoSuccess() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.success(null, null, "Empleado eliminado correctamente");

        when(service.eliminarEmpleado("123")).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.eliminarEmpleado("123");
        assertThat(response.getCodigo()).isEqualTo("200");
        assertThat(response.getMensaje()).isEqualTo("Empleado eliminado correctamente");
    }
    
    @Test
    void eliminarEmpleadoError() throws Exception {
        ResponseDTO<Empleado> responseMock = ResponseDTO.error("Error al eliminar empleado");

        when(service.eliminarEmpleado("123")).thenReturn(responseMock);
        
        ResponseDTO<Empleado> response = controller.eliminarEmpleado("123");
        assertThat(response.getCodigo()).isEqualTo("999");
        assertThat(response.getMensaje()).isEqualTo("Error al eliminar empleado");
    }
}
