import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentService } from '../../../services/services/student';
import { SubscriptionService } from '../../../services/services/subscription';
import { FoodOrderService } from '../../../services/services/food-order';
import { ComplaintService } from '../../../services/services/complaint';
import { NoticeService } from '../../../services/services/notice';

import { Student } from '../../../models/models/student.model';
import { Subscription } from '../../../models/models/subscription.model';
import { FoodOrder } from '../../../models/models/food-order.model';
import { Complaint } from '../../../models/models/complaint.model';
import { Notice } from '../../../models/models/notice.model';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  // ============================
  // DASHBOARD COUNTS
  // ============================

  totalStudents: number = 0;

  activeSubscriptions: number = 0;

  pendingOrders: number = 0;

  openComplaints: number = 0;


  // ============================
  // RECENT NOTICES
  // ============================

  recentNotices: Notice[] = [];


  // ============================
  // CONSTRUCTOR
  // ============================

  constructor(
    private studentService: StudentService,
    private subscriptionService: SubscriptionService,
    private foodOrderService: FoodOrderService,
    private complaintService: ComplaintService,
    private noticeService: NoticeService
  ) {}


  // ============================
  // ON INIT
  // ============================

  ngOnInit(): void {

    this.loadStudents();

    this.loadSubscriptions();

    this.loadOrders();

    this.loadComplaints();

    this.loadRecentNotices();

  }


  // ============================
  // LOAD STUDENTS
  // ============================

  loadStudents(): void {

    this.studentService.getAll().subscribe({

      next: (data: Student[]) => {

        console.log(
          'DASHBOARD STUDENTS:',
          data
        );

        this.totalStudents = data.length;

        console.log(
          'TOTAL STUDENTS COUNT:',
          this.totalStudents
        );

      },

      error: (error: any) => {

        console.error(
          'DASHBOARD STUDENT ERROR:',
          error
        );

        this.totalStudents = 0;

      }

    });

  }


  // ============================
  // LOAD SUBSCRIPTIONS
  // ============================

  loadSubscriptions(): void {

    this.subscriptionService.getAll().subscribe({

      next: (data: Subscription[]) => {

        console.log(
          'DASHBOARD SUBSCRIPTIONS:',
          data
        );

        this.activeSubscriptions =
          data.filter(
            subscription =>
              subscription.status === 'ACTIVE'
          ).length;

        console.log(
          'ACTIVE SUBSCRIPTIONS COUNT:',
          this.activeSubscriptions
        );

      },

      error: (error: any) => {

        console.error(
          'DASHBOARD SUBSCRIPTION ERROR:',
          error
        );

        this.activeSubscriptions = 0;

      }

    });

  }


  // ============================
  // LOAD FOOD ORDERS
  // ============================

  loadOrders(): void {

    this.foodOrderService.getAll().subscribe({

      next: (data: FoodOrder[]) => {

        console.log(
          'DASHBOARD ORDERS:',
          data
        );

        this.pendingOrders =
          data.filter(
            order =>
              order.status === 'PENDING'
          ).length;

        console.log(
          'PENDING ORDERS COUNT:',
          this.pendingOrders
        );

      },

      error: (error: any) => {

        console.error(
          'DASHBOARD ORDER ERROR:',
          error
        );

        this.pendingOrders = 0;

      }

    });

  }


  // ============================
  // LOAD COMPLAINTS
  // ============================

  loadComplaints(): void {

    this.complaintService.getAll().subscribe({

      next: (data: Complaint[]) => {

        console.log(
          'DASHBOARD COMPLAINTS:',
          data
        );

        this.openComplaints =
          data.filter(
            complaint =>
              complaint.status === 'OPEN'
          ).length;

        console.log(
          'OPEN COMPLAINTS COUNT:',
          this.openComplaints
        );

      },

      error: (error: any) => {

        console.error(
          'DASHBOARD COMPLAINT ERROR:',
          error
        );

        this.openComplaints = 0;

      }

    });

  }


  // ============================
  // LOAD RECENT NOTICES
  // ============================

  loadRecentNotices(): void {

  this.noticeService.getAll().subscribe({

    next: (data: Notice[]) => {

      console.log(
        'DASHBOARD NOTICES:',
        data
      );

      // Sort latest notices first
      const sortedNotices = [...data].sort(
        (a, b) => {

          const dateA = a.createdAt
            ? new Date(a.createdAt).getTime()
            : 0;

          const dateB = b.createdAt
            ? new Date(b.createdAt).getTime()
            : 0;

          return dateB - dateA;

        }
      );

      // Show latest 3 notices
      this.recentNotices =
        sortedNotices.slice(0, 3);

      console.log(
        'RECENT NOTICES:',
        this.recentNotices
      );

    },

    error: (error: any) => {

      console.error(
        'DASHBOARD NOTICE ERROR:',
        error
      );

      this.recentNotices = [];

    }

  });

  }
  }

