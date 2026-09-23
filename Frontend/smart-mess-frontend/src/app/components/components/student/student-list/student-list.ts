import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { Student } from '../../../../models/models/student.model';
import { StudentService } from '../../../../services/services/student';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css'
})
export class StudentList implements OnInit {

  students: Student[] = [];

  constructor(
    private studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.studentService.getAll().subscribe({
      next: (data: Student[]) => {
        this.students = data;
      },
      error: (error: any) => {
        console.error('Error loading students:', error);
      }
    });
  }

  deleteStudent(id: number): void {
    if (confirm('Are you sure you want to delete this student?')) {
      this.studentService.delete(id).subscribe({
        next: () => this.loadStudents(),
        error: (error: any) => console.error(error)
      });
    }
  }

  editStudent(id: number): void {
    this.router.navigate(['/students/edit', id]);
  }
}