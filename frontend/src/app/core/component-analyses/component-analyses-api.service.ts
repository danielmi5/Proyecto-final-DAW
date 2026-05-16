import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { ComponentAnalysisRequest, ComponentAnalysisResponse, ComponentAnalysisUpdate } from './component-analysis.dto';

@Injectable({ providedIn: 'root' })
export class ComponentAnalysesApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/component-analyses`;

  getAll(params?: PageParams): Observable<Page<ComponentAnalysisResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<ComponentAnalysisResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: string): Observable<ComponentAnalysisResponse> {
    return this.http.get<ComponentAnalysisResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: ComponentAnalysisRequest): Observable<ComponentAnalysisResponse> {
    return this.http.post<ComponentAnalysisResponse>(this.baseUrl, body);
  }

  update(id: string, body: ComponentAnalysisUpdate): Observable<ComponentAnalysisResponse> {
    return this.http.put<ComponentAnalysisResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
