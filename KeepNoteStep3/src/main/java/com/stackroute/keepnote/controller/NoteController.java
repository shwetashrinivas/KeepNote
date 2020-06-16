package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;


/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 
	 * 2. Add a new note which should contain the note id, title, content and status. 
	 * 3. Delete an existing note 
	 * 4. Update an existing note
	 * 
	 */
	
	/*
	 * Autowiring should be implemented for the NoteDAO.
	 * Create a Note object.
	 * 
	 */
	@Autowired
	public NoteDAO notedao;
	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */

	public NoteController(NoteDAO noteDao) {
	}

	@RequestMapping("/")
	public String getAllNotes(ModelMap modelMap) {
		modelMap.addAttribute("noteList", notedao.getAllNotes());
		return "index";
	}

	/*
	 * Define a handler method which will read the Note data from request parameters
	 * and save the note by calling the addNote() method of NoteRepository class.
	 * Please note that the createdAt field should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing notes. Hence, reading notes
	 * has to be done here again and the retrieved notes object should be sent back
	 * to the view using ModelMap. This handler method should map to the URL
	 * "/saveNote".
	 */
	@PostMapping("/add")
	public String addNote(ModelMap modelMap, @RequestParam String noteTitle, @RequestParam String noteContent, @RequestParam String noteStatus) {
		Note note = new Note();
		note.setNoteTitle(noteTitle);
		note.setNoteContent(noteContent);
		note.setNoteStatus(noteStatus);
		note.setCreatedAt(LocalDateTime.now());
		if(noteTitle.isEmpty() || noteContent.isEmpty() || noteStatus.isEmpty()){
			modelMap.addAttribute("errorMessage", "No fields can be empty");
			modelMap.addAttribute("noteList", notedao.getAllNotes());
			return "index";
		} else {
			notedao.saveNote(note);
			modelMap.addAttribute("noteList", notedao.getAllNotes());
			return "redirect:/";
		}
		
	}
	/*
	 * Define a handler method to delete an existing note by calling the
	 * deleteNote() method of the NoteRepository class This handler method should
	 * map to the URL "/deleteNote"
	 */
	@GetMapping("/delete")
	public String deleteNote(ModelMap modelMap, @RequestParam int noteId) {
		notedao.deleteNote(noteId);
		modelMap.addAttribute("noteList", notedao.getAllNotes());
		return "redirect:/";
	}
	
	@RequestMapping("/update")
	public String updateNote(ModelMap modelMap, @RequestParam int noteId, @RequestParam String noteTitle, @RequestParam String noteContent, @RequestParam String noteStatus) {
		Note note = new Note();
		note.setNoteId(noteId);
		note.setNoteContent(noteContent);
		note.setNoteStatus(noteStatus);
		note.setNoteTitle(noteTitle);
		note.setCreatedAt(LocalDateTime.now());
		notedao.UpdateNote(note);
		modelMap.addAttribute("noteList", notedao.getAllNotes());
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public String getAll(ModelMap modelmap,@RequestParam int noteId)
	{
		Note note1 =notedao.getNoteById(noteId);
		modelmap.addAttribute("noteList",note1);
		return "update";
	}

}