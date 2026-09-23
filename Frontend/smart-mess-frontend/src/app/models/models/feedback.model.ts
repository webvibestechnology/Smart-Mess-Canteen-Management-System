export interface Feedback {
  id?: number;
  student?: any;
  mess?: any;
  rating?: number;
  comment?: string;
  category?: 'FOOD_QUALITY' | 'CLEANLINESS' | 'SERVICE' | 'TIMING';
  createdAt?: string;
}