import { Routes } from '@angular/router';

import { Dashboard } from './components/dashboard/dashboard';

import { StudentList } from './components/components/student/student-list/student-list';
import { StudentAdd } from './components/components/student/student-add/student-add';
import { StudentEdit } from './components/components/student/student-edit/student-edit';

export const routes: Routes = [

  // Default
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },

  // Dashboard
  {
    path: 'dashboard',
    component: Dashboard
  },

  // Student List
  {
    path: 'students',
    component: StudentList
  },

  // Add Student
  {
    path: 'students/add',
    component: StudentAdd
  },

  // Edit Student
  {
    path: 'students/edit/:id',
    component: StudentEdit
  }

];