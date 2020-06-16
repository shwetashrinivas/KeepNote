import { CategoryService } from '../category.service';
import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { Reminder } from '../reminder';
import { Note } from '../note';
import { NoteService } from '../note.service';
import { ReminderService } from '../reminder.service';


@Component({
  selector: 'app-createnote',
  templateUrl: './createnote.component.html',
  styleUrls: ['./createnote.component.css']
})
export class CreatenoteComponent implements OnInit {

  constructor(private noteService:NoteService, private categoryService:CategoryService, private reminderService:ReminderService) { }

  
  public noteTitle: string;
  public noteContent: string;
  public noteStatus: string;
  public noteCreatedBy: string;
  // public category: Category;
  //public reminders: Reminder[];
  reminders: Reminder[] = new Array();
  public id: string;
  public rid: string;
  note: Note = new Note();

  category: Category = new Category();
  reminder: Reminder = new Reminder();
  onClick(id){
    this.categoryService.getCategoryById(id).subscribe(data => this.category = data);
    
  }

  onClickReminder(rid){
  this.reminderService.getReminderById(rid).subscribe(data => this.reminder = data);
  
  this.reminders.push(this.reminder);
  }
  ngOnInit() {
  }

  onSubmit(){

    this.note.noteTitle = this.noteTitle;
    this.note.noteContent = this.noteContent;
    this.note.noteStatus = this.noteStatus;
    this.note.noteCreatedBy = this.noteCreatedBy;
    this.note.category = this.category;
    this.note.reminders = this.reminders;
    this.noteService.createNote(this.note).subscribe(
      data => console.log(data)
    );
  }
  getCategoryId(){

  }
}
