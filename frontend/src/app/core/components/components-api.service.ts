import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { ComponentRequest, ComponentResponse, ComponentUpdate } from './component.dto';

@Injectable({ providedIn: 'root' })
export class ComponentsApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/components`;

  getAll(params?: PageParams): Observable<Page<ComponentResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<ComponentResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: string): Observable<ComponentResponse> {
    return this.http.get<ComponentResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: ComponentRequest): Observable<ComponentResponse> {
    return this.http.post<ComponentResponse>(this.baseUrl, body);
  }

  update(id: string, body: ComponentUpdate): Observable<ComponentResponse> {
    return this.http.put<ComponentResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
