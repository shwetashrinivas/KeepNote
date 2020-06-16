import { Component, OnInit } from '@angular/core';
import { Reminder } from '../reminder';
import { ReminderService } from '../reminder.service';
import { Category } from '../category';
import  {CategoryService} from '../category.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private reminderService:ReminderService, private categoryService: CategoryService) { }

  public reminderId: string;
    public reminderDescription: string;
    public reminderName: string;
    public reminderCreatedBy: string;
    public reminderType: string;
reminder: Reminder = new Reminder();

public categoryId: string;
public categoryName: string;
public categoryDescription: string;
public categoryCreatedBy: string;
category: Category = new Category();

onSubmit(){
  this.reminder.reminderId = this.reminderId;
  this.reminder.reminderName = this.reminderName;
  this.reminder.reminderCreatedBy = this.reminderCreatedBy;
  this.reminder.reminderDescription = this.reminderDescription;
  this.reminder.reminderType = this.reminderType;
  this.reminderService.createReminder(this.reminder).subscribe(
    data=> console.log(data)
  );
}

onCreate(){
  this.category.id = this.categoryId;
  this.category.categoryName = this.categoryName;
  this.category.categoryCreatedBy = this.categoryCreatedBy;
  this.category.categoryDescription = this.categoryDescription;
  this.categoryService.createCategory(this.category).subscribe(
    data => console.log(data)
  );
}
  ngOnInit() {
  }

}
