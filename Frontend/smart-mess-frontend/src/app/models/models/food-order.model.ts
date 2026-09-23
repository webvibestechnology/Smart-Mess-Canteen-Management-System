export interface FoodOrder {
  id?: number;
  student?: any;
  canteen?: any;
  status?: 'PENDING' | 'CONFIRMED' | 'READY' | 'DELIVERED' | 'CANCELLED';
  totalAmount?: number;
  orderedAt?: string;
  orderItems?: any[];
}