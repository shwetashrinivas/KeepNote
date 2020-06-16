import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reminder } from './reminder';



@Injectable({
  providedIn: 'root'
})
export class ReminderService {

  constructor(private _http: HttpClient) { }

  url = 'http://localhost:8083';
  createReminder(reminder): Observable<Reminder>{
    return this._http.post<Reminder>(`${this.url}/api/v1/reminder`,reminder);

  }
  getReminderById(id): Observable<Reminder>{
    return this._http.get<Reminder>(`${this.url}/api/v1/reminder/`+id)
  }
}
