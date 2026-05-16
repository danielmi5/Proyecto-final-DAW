import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { EstimationRequest, EstimationResponse, EstimationUpdate } from './estimation.dto';

@Injectable({ providedIn: 'root' })
export class EstimationsApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/estimations`;

  getAll(params?: PageParams): Observable<Page<EstimationResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<EstimationResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: string): Observable<EstimationResponse> {
    return this.http.get<EstimationResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: EstimationRequest): Observable<EstimationResponse> {
    return this.http.post<EstimationResponse>(this.baseUrl, body);
  }

  update(id: string, body: EstimationUpdate): Observable<EstimationResponse> {
    return this.http.put<EstimationResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
