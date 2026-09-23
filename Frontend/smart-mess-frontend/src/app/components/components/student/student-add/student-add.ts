import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';

import { StudentService } from '../../../../services/services/student';

@Component({
  selector: 'app-student-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student-add.html',
  styleUrl: './student-add.css'
})
export class StudentAdd {

  studentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private studentService: StudentService,
    private router: Router
  ) {
    this.studentForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      phone: [''],
      rollNumber: [''],
      department: [''],
      hostelName: [''],
      roomNumber: ['']
    });
  }

  submitForm(): void {

    if (this.studentForm.invalid) {
      this.studentForm.markAllAsTouched();
      return;
    }

    this.studentService.create(this.studentForm.value).subscribe({
      next: () => {
        alert('Student added successfully!');
        this.router.navigate(['/students']);
      },
      error: (error: any) => {
        console.error('Error adding student:', error);
        alert('Failed to add student.');
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/students']);
  }
}
