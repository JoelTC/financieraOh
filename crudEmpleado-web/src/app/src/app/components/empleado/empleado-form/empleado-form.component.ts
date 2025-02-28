import { Component, Inject, inject, OnInit, Signal, signal, WritableSignal } from '@angular/core';
import { EmpleadoService } from '../../../service/empleado.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { IEmpleadoDTO } from '../../../models/empleadoDTO';
import { IResponseData } from '../../../models/IResponseData';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MensajeService } from '../../../service/mensaje.service';

@Component({
  selector: 'app-empleado-form',
  imports: [MatCardModule, MatIconModule, MatFormFieldModule, FormsModule, MatDatepickerModule, NgIf, MatButtonModule, MatInputModule],
  standalone: true,
  templateUrl: './empleado-form.component.html',
  styleUrl: './empleado-form.component.scss',
  providers: [provideNativeDateAdapter()],
})
export class EmpleadoFormComponent implements OnInit {
  private empleadoService = inject(EmpleadoService);
  private mensajeService = inject(MensajeService);

  private dialogRef = inject(MatDialogRef<EmpleadoFormComponent>);
  public finalizado: boolean = true;
  public tipoFormulario: string = '';

  empleado: WritableSignal<IEmpleadoDTO> = signal({
    documentoId: '',
    nombres: '',
    apellidoPat: '',
    apellidoMat: '',
    edad: '',
    fechaNacimiento: '',
    auxFechaNacimiento: new Date(),
    salario: 0
  });

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.dialogRef.disableClose = true;
  }

  ngOnInit(): void {
    this.finalizado = true;
    this.tipoFormulario = this.data.metodo;
    if (this.data.metodo != "1") {
      this.obtenerEmpleado();
    }
  }

  obtenerEmpleado() {
    this.empleadoService.obtenerEmpleado(this.data.idEmpleado).subscribe(response => {
      if (response.codigo === "200") {
        if (response.entity) {
          this.empleado.set(response.entity);
          this.empleado().auxFechaNacimiento = this.stringToDate(this.empleado().fechaNacimiento)
        }
      } else if (response.codigo === "205") {
        this.mensajeService.warningMessage(response.mensaje ?? "");
        this.dialogRef.close();
      } else {
        this.mensajeService.errorAlert(response.mensaje ?? "");
        console.error('Error al obtener empleado');
      }
    },
      error => {
        console.error('Error al obtener empleado:', error);
      });

  }

  async enviar(formulario: NgForm): Promise<void> {
    this.finalizado = false;
    this.empleado().fechaNacimiento = new Intl.DateTimeFormat('es-ES', { day: '2-digit', month: '2-digit', year: 'numeric' }).format(this.empleado().auxFechaNacimiento);
    const request = this.tipoFormulario != "1" ? this.empleadoService.modificarEmpleado(this.empleado()) : this.empleadoService.registrarEmpleado(this.empleado());

    request.subscribe(response => {
      if (response.codigo === "200") {
        this.mensajeService.successMessage(response.mensaje ?? "");
        this.dialogRef.close(true);
      } else if (response.codigo === "999") {
        this.mensajeService.errorAlert(response.mensaje ?? "");
        this.finalizado = true;
      } else {
        this.mensajeService.errorAlert(response.mensaje ?? "");
        this.finalizado = true;
      }
    },
      error => {
        console.error('Error al obtener empleado:', error);
        this.finalizado = true;
      });

  }

  cancelar() {
    this.dialogRef.close();
  }

  public stringToDate(dateString: string): Date {
    const [day, month, year] = dateString.split('/').map(part => parseInt(part, 10));
    return new Date(year, month - 1, day);
  }
}