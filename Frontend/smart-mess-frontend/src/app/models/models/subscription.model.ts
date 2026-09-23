export interface Subscription {
  id?: number;
  student?: any;
  mess?: any;
  startDate?: string;
  endDate?: string;
  plan?: 'MONTHLY' | 'QUARTERLY' | 'YEARLY';
  status?: 'ACTIVE' | 'EXPIRED' | 'CANCELLED';
  amount?: number;
  createdAt?: string;
}