import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Payment } from '../../../../models/models/payment.model';
import { PaymentService } from '../../../../services/services/payment';

@Component({
  selector: 'app-payment-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment-list.html',
  styleUrl: './payment-list.css'
})
export class PaymentList implements OnInit {

  payments: Payment[] = [];
  filteredPayments: Payment[] = [];

  selectedStudent: string = 'ALL';
  selectedStatus: string = 'ALL';

  students: any[] = [];

  statuses: string[] = [
    'PENDING',
    'COMPLETED',
    'FAILED',
    'REFUNDED'
  ];

  constructor(
    private paymentService: PaymentService
  ) {}

  ngOnInit(): void {
    this.loadPayments();
  }

  loadPayments(): void {

    this.paymentService.getAll().subscribe({

      next: (data: Payment[]) => {

        this.payments = data;
        this.filteredPayments = data;

        this.loadStudents();

        console.log('Payments loaded:', data);

      },

      error: (error: any) => {

        console.error('Error loading payments:', error);

      }

    });

  }

  loadStudents(): void {

    const studentMap = new Map<number, any>();

    this.payments.forEach(payment => {

      if (payment.student?.id) {

        studentMap.set(
          payment.student.id,
          payment.student
        );

      }

    });

    this.students = Array.from(
      studentMap.values()
    );

  }

  filterPayments(): void {

    this.filteredPayments =
      this.payments.filter(payment => {

        const studentMatch =
          this.selectedStudent === 'ALL' ||
          payment.student?.id?.toString() ===
          this.selectedStudent;

        const statusMatch =
          this.selectedStatus === 'ALL' ||
          payment.status === this.selectedStatus;

        return studentMatch && statusMatch;

      });

  }

}