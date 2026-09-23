export interface FoodItem {
  id?: number;
  name: string;
  description?: string;
  price: number;
  category?: 'VEG' | 'NON_VEG' | 'BEVERAGE' | 'SNACK';
  isAvailable?: boolean;
  imageUrl?: string;
  canteen?: any;
}