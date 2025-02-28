import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IEmpleadoTable } from '../models/empleadoTable';
import { IResponseData } from '../models/IResponseData';
import { IEmpleadoDTO } from '../models/empleadoDTO';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://127.0.0.1:8080/api/empleado';

  private http = inject(HttpClient);

  listarEmpleados(): Observable<IResponseData<IEmpleadoTable>> {
    return this.http.get<IResponseData<IEmpleadoTable>>(`${this.apiUrl}/getAll`);
  }

  obtenerEmpleado(documentoId: string): Observable<IResponseData<IEmpleadoDTO>> {
    return this.http.get<IResponseData<IEmpleadoDTO>>(`${this.apiUrl}/getDetalle/${documentoId}`);
  }

  registrarEmpleado(empleado: IEmpleadoDTO): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, empleado);
  }

  modificarEmpleado(empleado: IEmpleadoDTO): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, empleado);
  }

  eliminarEmpleado(documentoId: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/delete/${documentoId}`, {});
  }
}