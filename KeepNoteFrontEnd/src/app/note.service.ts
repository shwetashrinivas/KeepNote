import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from './note';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private _http:HttpClient) { }
  url = 'http://localhost:8086';
  createNote(note): Observable<Note>{
    return this._http.post<Note>(`${this.url}/api/v1/note`,note);
  }
}
