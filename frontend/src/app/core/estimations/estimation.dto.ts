export interface EstimationRequest {
  analysisId: string;
  versionNumber: number;
  fiability?: number;
  hoursAn?: number;
  hoursAs?: number;
  hoursDe?: number;
  totalHours?: number;
  actualHoursFeedback?: number;
  justification?: string;
  updatedAt: string;
}

export interface EstimationResponse {
  id: string;
  analysisId: string;
  versionNumber: number;
  fiability?: number;
  hoursAn?: number;
  hoursAs?: number;
  hoursDe?: number;
  totalHours?: number;
  actualHoursFeedback?: number;
  justification?: string;
  createdAt?: string;
  updatedAt: string;
}

export interface EstimationUpdate {
  versionNumber: number;
  fiability?: number;
  hoursAn?: number;
  hoursAs?: number;
  hoursDe?: number;
  totalHours?: number;
  actualHoursFeedback?: number;
  justification?: string;
  updatedAt: string;
}
