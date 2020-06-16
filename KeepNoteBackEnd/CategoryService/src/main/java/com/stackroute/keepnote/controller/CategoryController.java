package com.stackroute.keepnote.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.exception.CategoryNotCreatedException;

import com.stackroute.keepnote.model.Category;

import com.stackroute.keepnote.service.CategoryService;


/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@CrossOrigin("*")
public class CategoryController {

	/*
	 * Autowiring should be implemented for the CategoryService. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword
	 */
	CategoryService catService;
	@Autowired
	public CategoryController(CategoryService catService) {
		this.catService=catService;
	}
	/*
	 * Define a handler method which will create a category by reading the
	 * Serialized category object from request body and save the category in
	 * database. Please note that the careatorId has to be unique.This
	 * handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 201(CREATED - In case of successful creation of the category
	 * 2. 409(CONFLICT) - In case of duplicate categoryId
	 *
	 * 
	 * This handler method should map to the URL "/api/v1/category" using HTTP POST
	 * method".
	 */
	@PostMapping("/api/v1/category")
	public ResponseEntity<?> register(@RequestBody Category category){
		try{
			category.setCategoryCreationDate(new Date());
			catService.createCategory(category);
			return new ResponseEntity<Category>(category,HttpStatus.CREATED);
			
		}catch(CategoryNotCreatedException e) 
		{
			return new ResponseEntity<String>("Already Exists",HttpStatus.CONFLICT);
			
		}
	}
	
	/*
	 * Define a handler method which will delete a category from a database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the category deleted successfully from
	 * database. 2. 404(NOT FOUND) - If the category with specified categoryId is
	 * not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/category/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid categoryId without {}
	 */
	@DeleteMapping("/api/v1/category/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
        	boolean categoryDel=catService.deleteCategory(id);
       
                if(categoryDel !=false)
                {
                    return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
            }
            else {
            	 return new ResponseEntity<String>("reminder not found", HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            return new ResponseEntity<String>("ReminderNotFoundException", HttpStatus.NOT_FOUND);
        }
    }
	
	/*
	 * Define a handler method which will update a specific category by reading the
	 * Serialized object from request body and save the updated category details in
	 * database. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the category updated
	 * successfully. 2. 404(NOT FOUND) - If the category with specified categoryId
	 * is not found. 
	 * This handler method should map to the URL "/api/v1/category/{id}" using HTTP PUT
	 * method.
	 */
	@PutMapping("/api/v1/category/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id,@RequestBody Category category) {
        try {
        	category.setCategoryCreationDate(new Date());
            Category catUpdated = catService.updateCategory(category, id);
            if (catUpdated != null) {
                return new ResponseEntity<Category>(category, HttpStatus.OK);
            }
            else
                return new ResponseEntity<String>("Not Found", HttpStatus.CONFLICT);
        }  catch (Exception e) {
            return new ResponseEntity<String>("Not Found", HttpStatus.CONFLICT);
        }
    }
	/*
	 * Define a handler method which will get us the category by a userId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the category found successfully. 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/category" using HTTP GET method
	 */
	@GetMapping("/api/v1/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") String id) {
        try {
           
            	Category cat1=catService.getCategoryById(id);
                if(cat1==null)
                	 return new ResponseEntity<String>("category not found", HttpStatus.NOT_FOUND);
                else
                    return new ResponseEntity<Category>(cat1,HttpStatus.OK);
            }
           
        catch(Exception e) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
