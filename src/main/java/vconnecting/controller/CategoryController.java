package vconnecting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import vconnecting.model.Category;
import vconnecting.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
@Api(tags = "categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/")
	@ApiOperation(value = "${CategoryController.getAllCategories}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong") })
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@PostMapping("/category")
	@ApiOperation(value = "${CategoryController.getAllCategories}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong") })
	public ResponseEntity<String> addCategory(//
			@ApiParam("name") @RequestParam String name) {
		Category category = new Category();
		category.setName(name);
		categoryRepository.save(category);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("/subcategory")
	@ApiOperation(value = "${CategoryController.getAllCategories}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong") })
	public ResponseEntity<String> addSubCategory(//
			@ApiParam("name") @RequestParam String name, @ApiParam("parentId") @RequestParam int parentId) {
		Category category = new Category();
		category.setName(name);
		category.setParentCategoryId(parentId);
		categoryRepository.save(category);
		return new ResponseEntity<String>(HttpStatus.CREATED);

	}

	@DeleteMapping("/category")
	@ApiOperation(value = "${CategoryController.getAllCategories}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong") })
	public ResponseEntity<String> deleteCategory(//
			@ApiParam("id") @RequestParam int id) {
		categoryRepository.delete(id);
		categoryRepository.deleteByParentCategoryId(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
