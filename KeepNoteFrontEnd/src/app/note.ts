import { Category } from "./category";
import { Reminder } from "./reminder";

export class Note{
public noteTitle: string;
public noteContent: string;
public noteStatus: string;
public noteCreatedBy: string;
public category: Category;
public reminders : Array<Reminder>;
}