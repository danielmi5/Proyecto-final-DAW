import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Page, PageParams } from '../dtos/pagination.dto';
import { UserRequest, UserResponse, UserUpdate } from './user.dto';

@Injectable({ providedIn: 'root' })
export class UsersApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/users`;

  getAll(params?: PageParams): Observable<Page<UserResponse>> {
    let httpParams = new HttpParams();
    if (params?.page !== undefined) httpParams = httpParams.set('page', params.page);
    if (params?.size !== undefined) httpParams = httpParams.set('size', params.size);
    if (params?.sort) httpParams = httpParams.set('sort', params.sort);
    return this.http.get<Page<UserResponse>>(this.baseUrl, { params: httpParams });
  }

  getById(id: string): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}/${id}`);
  }

  create(body: UserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(this.baseUrl, body);
  }

  update(id: string, body: UserUpdate): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.baseUrl}/${id}`, body);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
