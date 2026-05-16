import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { ImpactAnalysisRequest, ImpactAnalysisResponse, ImpactAnalysisUpdate } from './impact-analysis.dto';

@Injectable({ providedIn: 'root' })
export class ImpactAnalysesApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/impact-analyses`;

  getAll(params?: PageParams): Observable<Page<ImpactAnalysisResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<ImpactAnalysisResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: string): Observable<ImpactAnalysisResponse> {
    return this.http.get<ImpactAnalysisResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: ImpactAnalysisRequest): Observable<ImpactAnalysisResponse> {
    return this.http.post<ImpactAnalysisResponse>(this.baseUrl, body);
  }

  update(id: string, body: ImpactAnalysisUpdate): Observable<ImpactAnalysisResponse> {
    return this.http.put<ImpactAnalysisResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
