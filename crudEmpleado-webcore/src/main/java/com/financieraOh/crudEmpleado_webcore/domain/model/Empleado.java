package com.financieraOh.crudEmpleado_webcore.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLEADO")
public class Empleado {

	@Id
	@Column(name = "documentoId", nullable = false, unique = true)
	private String documentoId;

	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidoPat", nullable = false)
	private String apellidoPat;

	@Column(name = "apellidoMat", nullable = false)
	private String apellidoMat;

	@Column(name = "edad", nullable = false)
	private int edad;

	@Column(name = "fechaNacimiento", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaNacimiento;

	@Column(name = "salario", nullable = false)
	private double salario;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "fechaCreacion", nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "fechaModificacion", nullable = true)
	private LocalDateTime fechaModificacion;

	@Override
	public String toString() {
		return "Empleado [documentoId=" + documentoId + ", nombres=" + nombres + ", apellidoPat=" + apellidoPat
				+ ", apellidoMat=" + apellidoMat + ", edad=" + edad + ", fechaNacimiento=" + fechaNacimiento
				+ ", salario=" + salario + ", estado=" + estado + "]";
	}
}