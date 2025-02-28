package com.financieraOh.crudEmpleado_webcore.application.adapter;

import com.financieraOh.crudEmpleado_webcore.application.dto.request.EmpleadoDTO;
import com.financieraOh.crudEmpleado_webcore.application.dto.response.ResponseDTO;
import com.financieraOh.crudEmpleado_webcore.application.mapper.EmpleadoMapper;
import com.financieraOh.crudEmpleado_webcore.domain.model.Empleado;
import com.financieraOh.crudEmpleado_webcore.domain.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmpleadoServiceAdapterTest {

	@InjectMocks
    private EmpleadoServiceAdapter empleadoService;

    @Mock
    private EmpleadoRepository empleadoRepositorio;

    @Mock
    private EmpleadoMapper empleadoMapper;

    // 🟢 Test: Listar empleados (con datos)
    @Test
    void listarEmpleadosSuccess(){
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);
        when(empleadoRepositorio.findByEstadoOrderByFechaCreacionDesc("1")).thenReturn(List.of(empleado));

        ResponseDTO<Empleado> response = empleadoService.listarEmpleados();

        assertThat(response.getMensaje()).isEqualTo("OK");
        assertThat(response.getDataResponse()).isNotNull().isNotEmpty();
    }

    @Test
    void listarEmpleadosSinContenido() {
        when(empleadoRepositorio.findByEstadoOrderByFechaCreacionDesc("1")).thenReturn(List.of());

        ResponseDTO<Empleado> response = empleadoService.listarEmpleados();

        assertThat(response.getMensaje()).isEqualTo("No hay empleados registrados");
        assertEquals(response.getDataResponse(), null);
    }

    @Test
    void obtenerEmpleadoSuccess() {
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);
        when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.of(empleado));

        ResponseDTO<Empleado> response = empleadoService.obtenerEmpleado("123");

        assertThat(response.getMensaje()).isEqualTo("OK");
        assertThat(response.getEntity()).isNotNull();
    }

    @Test
    void obtenerEmpleadoSinElemento() {
        when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.empty());

        ResponseDTO<Empleado> response = empleadoService.obtenerEmpleado("123");

        assertThat(response.getMensaje()).isEqualTo("Empleado no registrado");
        assertNull(response.getEntity());
    }
    
    @Test
    void registrarEmpleadoSuccess() {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000);
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);

        when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.empty());
        when(empleadoMapper.createempleadoToEmpleadoDTO(any(EmpleadoDTO.class))).thenReturn(empleado);
        when(empleadoRepositorio.guardar(any(Empleado.class))).thenReturn(new Empleado());

        ResponseDTO<Empleado> response = empleadoService.registrarEmpleado(empleadoDTO);

        assertThat(response.getMensaje()).isEqualTo("Empleado registrado correctamente");
    }

    @Test
    void registrarEmpleadoExistente() {
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000);

        when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.of(empleado));

        ResponseDTO<Empleado> response = empleadoService.registrarEmpleado(empleadoDTO);

        assertThat(response.getMensaje()).isEqualTo("Empleado ya existe");
    }

    @Test
    void modificarEmpleadoSuccess() {
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 35, LocalDate.of(1988, 5, 12), 6000);

        Mockito.when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.of(empleado));
        Mockito.doNothing().when(empleadoMapper).updateEmpleadoToEmpleadoDTO(any(EmpleadoDTO.class), any(Empleado.class));
        when(empleadoRepositorio.guardar(any(Empleado.class))).thenReturn(new Empleado());

        ResponseDTO<Empleado> response = empleadoService.modificarEmpleado(empleadoDTO);

        assertThat(response.getMensaje()).isEqualTo("Empleado modificado correctamente");
    }

    @Test
    void modificarEmpleadoNoExiste() {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO("123", "Juan", "Perez", "Gomez", 35, LocalDate.of(1988, 5, 12), 6000);

        Mockito.when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.empty());

        ResponseDTO<Empleado> response = empleadoService.modificarEmpleado(empleadoDTO);

        assertThat(response.getMensaje()).isEqualTo("Empleado no registrado");
    }
    
    @Test
    void eliminarEmpleadoSuccess() {
        Empleado empleado = new Empleado("123", "Juan", "Perez", "Gomez", 30, LocalDate.of(1993, 5, 12), 5000, null, null, null);

        Mockito.when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.of(empleado));
        Mockito.doNothing().when(empleadoMapper).deleteEmpleadoToEmpleadoDTO(any(Empleado.class), any(Empleado.class));
        when(empleadoRepositorio.guardar(any(Empleado.class))).thenReturn(new Empleado());

        ResponseDTO<Empleado> response = empleadoService.eliminarEmpleado("123");

        assertThat(response.getMensaje()).isEqualTo("Empleado eliminado correctamente");
    }

    @Test
    void eliminarEmpleadoNoExiste() {
        Mockito.when(empleadoRepositorio.buscarPorId("123")).thenReturn(Optional.empty());

        ResponseDTO<Empleado> response = empleadoService.eliminarEmpleado("123");

        assertThat(response.getMensaje()).isEqualTo("Empleado no registrado");
    }
}