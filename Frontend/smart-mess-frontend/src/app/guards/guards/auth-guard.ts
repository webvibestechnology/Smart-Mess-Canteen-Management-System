import { inject } from '@angular/core';
import {
  CanActivateFn,
  Router
} from '@angular/router';

export const authGuard: CanActivateFn = () => {

  const adminId =
    localStorage.getItem('adminId');


  if (adminId) {

    return true;

  }


  inject(Router).navigate([
    '/login'
  ]);

  return false;

};