export interface EstimationHistoryRequest {
  estimationId: string;
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  modifiedAt: string;
}

export interface EstimationHistoryResponse {
  id: number;
  estimationId: string;
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  modifiedAt: string;
}

export interface EstimationHistoryUpdate {
  frozenVersion: number;
  snapshotData: Record<string, unknown>;
  modifiedAt: string;
}
