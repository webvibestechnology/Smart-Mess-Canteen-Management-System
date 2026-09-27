import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { StudentService } from '../../../../services/services/student';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css'
})
export class StudentList implements OnInit {

  students: any[] = [];

  constructor(
    private studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit(): void {

    console.log('STUDENT LIST COMPONENT LOADED');

    this.studentService.getAll().subscribe({

      next: (data: any[]) => {

        console.log('STUDENTS FROM API:', data);

        this.students = data;

      },

      error: (error) => {

        console.error('STUDENT API ERROR:', error);

      }

    });

  }

  addStudent(): void {
    this.router.navigate(['/students/add']);
  }

  editStudent(id: number): void {
    this.router.navigate(['/students/edit', id]);
  }

  deleteStudent(id: number): void {

    if (!confirm('Are you sure you want to delete this student?')) {
      return;
    }

    this.studentService.delete(id).subscribe({

      next: () => {

        alert('Student deleted successfully');

        this.studentService.getAll().subscribe({

          next: (data: any[]) => {
            this.students = data;
          },

          error: (error) => {
            console.error(error);
          }

        });

      },

      error: (error) => {

        console.error('DELETE ERROR:', error);

        alert('Failed to delete student');

      }

    });

  }

}