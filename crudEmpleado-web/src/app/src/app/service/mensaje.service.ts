import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class MensajeService {
  constructor(private router: Router) {
  }

  successMessage(mensaje: string) {
    Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    }).fire({
      icon: 'success',
      title: mensaje
    })
  }

  errorMessage(mensaje: string) {
    Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    }).fire({
      icon: 'error',
      title: mensaje
    })
  }

  warningMessage(mensaje: string) {
    Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    }).fire({
      icon: 'warning',
      title: mensaje
    })
  }

  errorAlert(mensaje: string) {
    Swal.fire({
      title: mensaje,
      icon: 'error',
      showCancelButton: false,
      confirmButtonText: 'Aceptar',
    })
  }

  confirmationAler(mensaje: string, labelBoton:string, ruta: string) {
    Swal.fire({
      title: mensaje,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#919191',
      cancelButtonColor: '#096ac6',
      cancelButtonText: labelBoton,
      confirmButtonText: 'Cancelar',
    }).then((result) => {
      if (!result.isConfirmed) {
        this.router.navigate([ruta]);
      }
    })
  }

}
