export interface ImpactAnalysisHistoryRequest {
  analysisId: string;
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  componentsSnapshot: Record<string, unknown>;
  modifiedAt: string;
}

export interface ImpactAnalysisHistoryResponse {
  id: number;
  analysisId: string;
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  componentsSnapshot: Record<string, unknown>;
  modifiedAt: string;
}

export interface ImpactAnalysisHistoryUpdate {
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  componentsSnapshot: Record<string, unknown>;
  modifiedAt: string;
}
