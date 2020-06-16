package com.stackroute.keepnote.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NoteServiceImpl implements NoteService{

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	  @Autowired
	    private NoteRepository noteRepository;
	    private NoteUser noteUser = null;
	    private List<Note> notes = null;

	    @Autowired
	    public NoteServiceImpl(NoteRepository noteRepository) {
	        this.noteRepository = noteRepository;

	    }
	/*
	 * This method should be used to save a new note.
	 */
	  @Override
	    public boolean createNote(Note note) {
	        int counter = 1;
	        boolean status = false;
	        noteUser = new NoteUser();
	        notes = new ArrayList<>();
	        note.setNoteCreationDate(new Date());
	        if (noteRepository.existsById(note.getNoteCreatedBy())) {
	            notes = noteRepository.findById(note.getNoteCreatedBy()).get().getNotes();


	            Iterator iterator = notes.iterator();
	            Note note1 = new Note();
	            while (iterator.hasNext()) {

	                note1 = (Note) iterator.next();
	            }
	            note.setNoteId(note1.getNoteId() + counter);
	            notes.add(note);
	            noteUser.setUserId(note.getNoteCreatedBy());
	            noteUser.setNotes(notes);
	            if (noteRepository.save(noteUser) != null) {

	                status = true;
	            }
	        } else {

	            note.setNoteId(counter);
	            notes.add(note);
	            noteUser.setUserId(note.getNoteCreatedBy());
	            noteUser.setNotes(notes);

	            if (noteRepository.insert(noteUser) != null) {
	                status = true;
	            }
	        }
	        return status;
	    }	
	/* This method should be used to delete an existing note. */

	
	  @Override
	    public boolean deleteNote(String userId, int noteId) {

	        boolean status = false;
	        noteUser = new NoteUser();
	        notes = noteRepository.findById(userId).get().getNotes();

	        if (!notes.isEmpty()) {

	            Iterator iterator = notes.listIterator();
	            while (iterator.hasNext()) {

	                Note note = (Note) iterator.next();
	                if (note.getNoteId() == noteId)
	                    iterator.remove();

	            }

	            noteUser.setUserId(userId);
	            noteUser.setNotes(notes);
	            noteRepository.save(noteUser);
	            status = true;
	        }


	        return status;
	    }


	
	/* This method should be used to delete all notes with specific userId. */

	
	   @Override
	    public boolean deleteAllNotes(String userId) throws NoteNotFoundExeption {

	        boolean status = false;
	        noteUser = new NoteUser();
	        try {
	            notes = noteRepository.findById(userId).get().getNotes();
	            if (notes != null) {

	                Iterator iterator = notes.listIterator();
	                while (iterator.hasNext()) {

	                    iterator.next();
	                    iterator.remove();

	                }

	                noteUser.setUserId(userId);
	                noteUser.setNotes(notes);
	                noteRepository.save(noteUser);
	                status = true;

	            }

	        } catch (NoSuchElementException exception) {

	            throw new NoteNotFoundExeption("Note not found");
	        }


	        return status;
	    }

	/*
	 * This method should be used to update a existing note.
	 */
	   @Override
	    public Note updateNote(Note note, int noteId, String userId) throws NoteNotFoundExeption {

	        Note fetchedNote = null;
	        noteUser = new NoteUser();
	        try {

	            notes = noteRepository.findById(userId).get().getNotes();
	            if (!notes.isEmpty()) {

	                Iterator iterator = notes.listIterator();
	                while (iterator.hasNext()) {

	                    fetchedNote = (Note) iterator.next();
	                    if (fetchedNote.getNoteId() == noteId) {
	                        fetchedNote.setNoteId(fetchedNote.getNoteId());
	                        fetchedNote.setNoteTitle(note.getNoteTitle());
	                        fetchedNote.setNoteContent(note.getNoteContent());
	                        fetchedNote.setNoteCreationDate(fetchedNote.getNoteCreationDate());
	                        fetchedNote.setNoteCreatedBy(userId);
	                        fetchedNote.setCategory(note.getCategory());
	                        fetchedNote.setReminders(note.getReminders());
	                        break;
	                    }

	                }

	                if (fetchedNote.getNoteId() != noteId) {
	                    throw new NoteNotFoundExeption("Note does not exists");
	                } else {

	                    noteUser.setUserId(userId);
	                    noteUser.setNotes(notes);
	                    noteRepository.save(noteUser);
	                }


	            }

	        } catch (NoSuchElementException exception) {

	            throw new NoteNotFoundExeption("Note does not exists");
	        }

	        return fetchedNote;

	    }

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	   @Override
	    public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {

	        Note fetchedNote =new Note();


	        try {
	            notes = noteRepository.findById(userId).get().getNotes();

	            Iterator iterator = notes.listIterator();
	            while (iterator.hasNext()) {


	                fetchedNote = (Note) iterator.next();
	                if (fetchedNote.getNoteId() == noteId)
	                    break;

	            }

	            if (fetchedNote.getNoteId() != noteId) {

	                throw new NoteNotFoundExeption("Note does not exists");
	            }

	        } catch (NoSuchElementException exception) {
	            throw new NoteNotFoundExeption("Note Does not exists");
	        }


	        return fetchedNote;
	    }


	/*
	 * This method should be used to get all notes with specific userId.
	 */
	   @Override
	    public List<Note> getAllNoteByUserId(String userId) {


	        List<Note> allNotes = noteRepository.findById(userId).get().getNotes();

	        return allNotes;
	    }


}
