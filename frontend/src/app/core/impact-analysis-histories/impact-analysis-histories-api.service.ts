import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { ImpactAnalysisHistoryRequest, ImpactAnalysisHistoryResponse, ImpactAnalysisHistoryUpdate } from './impact-analysis-history.dto';

@Injectable({ providedIn: 'root' })
export class ImpactAnalysisHistoriesApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/impact-analysis-histories`;

  getAll(params?: PageParams): Observable<Page<ImpactAnalysisHistoryResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<ImpactAnalysisHistoryResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: number): Observable<ImpactAnalysisHistoryResponse> {
    return this.http.get<ImpactAnalysisHistoryResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: ImpactAnalysisHistoryRequest): Observable<ImpactAnalysisHistoryResponse> {
    return this.http.post<ImpactAnalysisHistoryResponse>(this.baseUrl, body);
  }

  update(id: number, body: ImpactAnalysisHistoryUpdate): Observable<ImpactAnalysisHistoryResponse> {
    return this.http.put<ImpactAnalysisHistoryResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
