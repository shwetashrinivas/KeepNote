import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from './category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http: HttpClient) { }
  url = 'http://localhost:8084';
  createCategory(category): Observable<Category>{
    return this._http.post<Category>(`${this.url}/api/v1/category`,category);
  }
  getCategoryById(id): Observable<Category>{
    return this._http.get<Category>(`${this.url}/api/v1/category/`+id);
  }

}
