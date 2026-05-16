export interface ComponentAnalysisRequest {
  analysisId?: string;
  componentId?: string;
}

export interface ComponentAnalysisResponse {
  id: string;
  analysisId: string;
  componentId: string;
  createdAt: string;
}

export type ComponentAnalysisUpdate = Record<string, never>;
