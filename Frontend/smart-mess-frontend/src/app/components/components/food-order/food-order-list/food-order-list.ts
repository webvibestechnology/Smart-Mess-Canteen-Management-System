import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { FoodOrder } from '../../../../models/models/food-order.model';
import { FoodOrderService } from '../../../../services/services/food-order';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-food-order-list',
  standalone: true,
 imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './food-order-list.html',
  styleUrl: './food-order-list.css'
})
export class FoodOrderList implements OnInit {

  orders: FoodOrder[] = [];

  filteredOrders: FoodOrder[] = [];

  selectedStatus: string = 'ALL';

  statuses: string[] = [
    'PENDING',
    'CONFIRMED',
    'READY',
    'DELIVERED'
  ];

  constructor(
    private foodOrderService: FoodOrderService
  ) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {

    this.foodOrderService.getAll().subscribe({
      next: (data: FoodOrder[]) => {
        this.orders = data;
        this.filteredOrders = data;
      },

      error: (error: any) => {
        console.error('Error loading orders:', error);
      }
    });

  }

  filterOrders(): void {

    if (this.selectedStatus === 'ALL') {
      this.filteredOrders = this.orders;
    } else {
      this.filteredOrders = this.orders.filter(
        order => order.status === this.selectedStatus
      );
    }

  }

  updateStatus(id: number, status: string): void {

    this.foodOrderService.updateStatus(id, status).subscribe({

      next: () => {
        alert('Order status updated successfully!');
        this.loadOrders();
      },

      error: (error: any) => {
        console.error('Error updating order status:', error);
        alert('Failed to update order status.');
      }

    });

  }

}