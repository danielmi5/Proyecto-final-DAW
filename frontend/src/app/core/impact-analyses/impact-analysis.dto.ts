export interface ImpactAnalysisRequest {
  requestId?: string;
  userId?: string;
  versionNumber: number;
  complexity: string;
  documentData: Record<string, unknown>;
}

export interface ImpactAnalysisResponse {
  id: string;
  requestId: string;
  userId: string;
  versionNumber: number;
  complexity: string;
  documentData: Record<string, unknown>;
  updatedAt: string;
}

export interface ImpactAnalysisUpdate {
  requestId?: string;
  userId?: string;
  versionNumber: number;
  complexity: string;
  documentData: Record<string, unknown>;
}
