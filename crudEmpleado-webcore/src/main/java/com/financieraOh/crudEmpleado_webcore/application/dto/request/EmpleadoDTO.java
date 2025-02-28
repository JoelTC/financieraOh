package com.financieraOh.crudEmpleado_webcore.application.dto.request;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmpleadoDTO {
	private String documentoId;
	private String nombres;
	private String apellidoPat;
	private String apellidoMat;
	private int edad;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaNacimiento;
	private double salario;
	
	public EmpleadoDTO(String documentoId, String nombres, String apellidoPat, String apellidoMat, int edad,
			LocalDate fechaNacimiento, double salario) {
		super();
		this.documentoId = documentoId;
		this.nombres = nombres;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.salario = salario;
	}
}