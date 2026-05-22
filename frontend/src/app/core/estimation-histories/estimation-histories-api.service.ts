import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { EstimationHistoryRequest, EstimationHistoryResponse, EstimationHistoryUpdate } from './estimation-history.dto';

@Injectable({ providedIn: 'root' })
export class EstimationHistoriesApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/estimation-histories`;

  getAll(params?: PageParams): Observable<Page<EstimationHistoryResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<EstimationHistoryResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: number): Observable<EstimationHistoryResponse> {
    return this.http.get<EstimationHistoryResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: EstimationHistoryRequest): Observable<EstimationHistoryResponse> {
    return this.http.post<EstimationHistoryResponse>(this.baseUrl, body);
  }

  update(id: number, body: EstimationHistoryUpdate): Observable<EstimationHistoryResponse> {
    return this.http.put<EstimationHistoryResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
