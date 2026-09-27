import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { Subscription } from '../../../../models/models/subscription.model';
import { SubscriptionService } from '../../../../services/services/subscription';

@Component({
  selector: 'app-subscription-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './subscription-list.html',
  styleUrl: './subscription-list.css'
})
export class SubscriptionList implements OnInit {

  subscriptions: Subscription[] = [];

  constructor(
    private subscriptionService: SubscriptionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadSubscriptions();
  }

  loadSubscriptions(): void {

    this.subscriptionService.getAll().subscribe({

      next: (data: Subscription[]) => {

        this.subscriptions = data;

        console.log('Subscriptions loaded:', data);

      },

      error: (error: any) => {

        console.error(
          'Error loading subscriptions:',
          error
        );

      }

    });

  }

  addSubscription(): void {
    this.router.navigate(['/subscriptions/add']);
  }

}