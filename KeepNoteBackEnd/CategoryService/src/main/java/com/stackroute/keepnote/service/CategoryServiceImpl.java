package com.stackroute.keepnote.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;

import com.stackroute.keepnote.model.Category;

import com.stackroute.keepnote.repository.CategoryRepository;

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
public class CategoryServiceImpl implements CategoryService {

	/*
	 * Autowiring should be implemented for the CategoryRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	CategoryRepository catRep;
	@Autowired
	public  CategoryServiceImpl(CategoryRepository catRep) {
		this.catRep=catRep;
	}
	/*
	 * This method should be used to save a new category.Call the corresponding
	 * method of Respository interface.
	 */
	public Category createCategory(Category category) throws CategoryNotCreatedException {
         Category category1;
         boolean cat=catRep.existsById(category.getId());
         if(cat) {
        	 throw new CategoryNotCreatedException("CategoryNotCreatedException");
         }
         else {
        	 category1=catRep.insert(category);
        	 if(category1!=null) {
        		 return category1;
        	 }
        	 else {
        		 throw new CategoryNotCreatedException("CategoryNotCreatedException");
        	 }
         }
		
	}

	/*
	 * This method should be used to delete an existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException {

		Category category1= catRep.findById(categoryId).get();
		if(category1!=null) 
		{
			catRep.delete(category1);
			return true;			
		}
		else
			throw new CategoryDoesNoteExistsException("CategoryDoesNoteExistsException");
		
	}

	/*
	 * This method should be used to update a existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public Category updateCategory(Category category, String categoryId) {
/*
		Category category1= catRep.findById(categoryId).get();
		if(category1!=null) 
		{
*/			catRep.save(category);
			return category;	
	}
	/*	}
		else
			//throw new  CategoryNotFoundException("CategoryNotFoundException") ;
			return null;
	}
*/
	/*
	 * This method should be used to get a category by categoryId.Call the
	 * corresponding method of Respository interface.
	 */
	public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
		Category cat1=null;
		try {
		 cat1=catRep.findById(categoryId).get();
		}catch(NoSuchElementException e) {
			
		}
		
		if(cat1!=null) {
			return cat1;
		}else
			throw new  CategoryNotFoundException(" CategoryNotFoundException");
	}

	/*
	 * This method should be used to get a category by userId.Call the corresponding
	 * method of Respository interface.
	 */
	public List<Category> getAllCategoryByUserId(String userId) {

		List<Category>cat2=catRep.findAllCategoryByCategoryCreatedBy(userId);
		return cat2;
	}

}
