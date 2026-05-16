export interface ComponentRequest {
  name: string;
  category: string;
  active: boolean;
}

export interface ComponentResponse {
  id: string;
  name: string;
  category: string;
  active: boolean;
  createdAt: string;
}

export interface ComponentUpdate {
  name: string;
  category: string;
  active: boolean;
}
