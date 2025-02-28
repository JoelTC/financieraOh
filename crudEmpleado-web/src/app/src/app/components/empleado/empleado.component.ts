import { Component, inject, OnInit, signal } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { EmpleadoService } from '../../service/empleado.service';
import { IEmpleadoTable } from '../../models/empleadoTable';
import { SelectionModel } from '@angular/cdk/collections';
import { RouterModule } from '@angular/router';
import { CommonModule, NgFor } from '@angular/common';
import { EmpleadoFormComponent } from './empleado-form/empleado-form.component';
import { MensajeService } from '../../service/mensaje.service';

@Component({
  selector: 'app-empleado',
  standalone: true,
  imports: [MatDialogModule, RouterModule, CommonModule, NgFor,],
  templateUrl: './empleado.component.html',
  styleUrl: './empleado.component.scss'
})
export class EmpleadoComponent implements OnInit {
  private empleadoService = inject(EmpleadoService);
  private mensajeService = inject(MensajeService);
  private dialog = inject(MatDialog);

  public selection = new SelectionModel<IEmpleadoTable>(true, []);

  empleados = signal<IEmpleadoTable[]>([]);

  constructor() {
  }

  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados() {
    this.empleadoService.listarEmpleados().subscribe(response => {
      if (response?.codigo === "200") {
        this.empleados.set(response.dataResponse ?? []);
      } else if (response?.codigo === "204") {
        this.empleados.set([]);
      } else {
      }
    }, error => {
      console.error('Error al obtener empleados:', error);
    });
  }

  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.empleados().forEach(row => this.selection.select(row));
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.empleados().length;
    return numSelected === numRows;
  }

  abrirModal(operacion: string, id?: string) {
    const dialogRef = this.dialog.open(
      EmpleadoFormComponent,
      {
        data: { metodo: operacion, idEmpleado: id },
        autoFocus: false,
        width: '95%',
        maxWidth: '800px'
      });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.cargarEmpleados();
      }
    });
  }

  eliminarSeleccionados(): void {
    if (this.selection.hasValue()) {
      const empleadosAEliminar = this.selection.selected;

      empleadosAEliminar.forEach(empleado => {
        this.empleadoService.eliminarEmpleado(empleado.documentoId).subscribe(response => {
          if (response.codigo !== "200") {
            this.mensajeService.errorAlert(response.mensaje);
          } else {
            this.cargarEmpleados();
          }
        }, error => {
          console.error('Error al eliminar empleados:', error);
        });
      });
      this.selection.clear();
    } else {
      this.mensajeService.warningMessage("No hay empleados seleccionados para eliminar");
    }
  }
}