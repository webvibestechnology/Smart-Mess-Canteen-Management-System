import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../../models/models/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl = 'http://localhost:8080/api/students';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getById(id: number) {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  create(student: Student) {
    return this.http.post<Student>(this.apiUrl, student);
  }

  update(id: number, student: Student) {
    return this.http.put<Student>(
      `${this.apiUrl}/${id}`,
      student
    );
  }

  delete(id: number) {
    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }
}