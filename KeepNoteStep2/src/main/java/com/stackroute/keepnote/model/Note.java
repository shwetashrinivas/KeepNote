package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */

@Entity
@Table(name="Note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int Noteid;
	
    @Column(name = "noteTitle")
    private String noteTitle;
   
    @Column(name = "noteContent")
    private String noteContent;
    
    @Column(name = "noteStatus")
    private String noteStatus;
    
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
    
	public Note() {

	}

	public Note(int id, String noteTitle, String noteContent, String noteStatus, LocalDateTime noteCreatedAt) {
		super();
		this.Noteid = id;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = noteCreatedAt;
	}

	public int getNoteId() {
		return Noteid;
	}

	public void setNoteId(int id) {
		this.Noteid = id;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime noteCreatedAt) {
		this.createdAt = noteCreatedAt;
	}

	
}
