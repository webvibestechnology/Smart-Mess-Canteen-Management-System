import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { Feedback } from '../../../../models/models/feedback.model';
import { FeedbackService } from '../../../../services/services/feedback';

@Component({
  selector: 'app-feedback-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './feedback-list.html',
  styleUrl: './feedback-list.css'
})
export class FeedbackList implements OnInit {

  feedbacks: Feedback[] = [];

  constructor(
    private feedbackService: FeedbackService
  ) {}

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  loadFeedbacks(): void {

    this.feedbackService.getAll().subscribe({

      next: (data: Feedback[]) => {

        this.feedbacks = data;

        console.log(
          'Feedbacks loaded:',
          data
        );

      },

      error: (error: any) => {

        console.error(
          'Error loading feedbacks:',
          error
        );

      }

    });

  }

  getStars(rating: number = 0): string {

    return '★'.repeat(rating) +
           '☆'.repeat(5 - rating);

  }

}