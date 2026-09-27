import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  email: string = '';

  password: string = '';

  errorMessage: string = '';

  loading: boolean = false;


  constructor(
    private http: HttpClient,
    private router: Router
  ) {}


  login(): void {

    this.errorMessage = '';

    if (!this.email || !this.password) {

      this.errorMessage =
        'Please enter email and password.';

      return;
    }


    this.loading = true;


    const credentials = {

      email: this.email,

      password: this.password

    };


    this.http.post<any>(
      'http://localhost:8080/api/admins/login',
      credentials
    )
    .subscribe({

      next: (response) => {

        console.log(
          'LOGIN SUCCESS:',
          response
        );


        localStorage.setItem(
          'adminId',
          response.adminId
        );


        this.loading = false;


        this.router.navigate([
          '/dashboard'
        ]);

      },


      error: (error) => {

        console.error(
          'LOGIN ERROR:',
          error
        );


        this.loading = false;


        if (error.status === 401) {

          this.errorMessage =
            'Invalid email or password.';

        } else {

          this.errorMessage =
            'Unable to connect to server.';

        }

      }

    });

  }

}