export interface Complaint {
  id?: number;
  student?: any;
  title: string;
  description?: string;
  status?: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED';
  category?: 'FOOD' | 'SERVICE' | 'HYGIENE' | 'BILLING' | 'OTHER';
  resolution?: string;
  createdAt?: string;
  resolvedAt?: string;
}