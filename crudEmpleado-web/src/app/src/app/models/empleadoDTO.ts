import { IAuditoriaDTO } from "./auditoriaDTO";

export interface IEmpleadoDTO extends IAuditoriaDTO{
    documentoId: string,
    nombres: string,
    apellidoPat: string,
    apellidoMat: string,
    edad: string,
    fechaNacimiento: string,
    auxFechaNacimiento: Date,
    salario: number
}