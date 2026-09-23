export interface Payment {
  id?: number;
  student?: any;
  amount?: number;
  type?: 'MESS_FEE' | 'CANTEEN_ORDER' | 'SUBSCRIPTION';
  status?: 'PENDING' | 'COMPLETED' | 'FAILED' | 'REFUNDED';
  transactionId?: string;
  paymentMethod?: string;
  paidAt?: string;
}